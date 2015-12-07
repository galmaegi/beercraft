package galmaegi.beercraft.Check;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

public class CheckIndexPagerFragment extends android.support.v4.app.Fragment implements View.OnClickListener{
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    ListView checkListView = null;
    CheckIndexAdapter checkIndexAdapter = null;
    ArrayList<CheckIndexItem> items = null;

    AccountBottomLayout accountBottomLayout;
    WishListBottomLayout wishListBottomLayout;

    View holder;
    CheckBox check_checkbox;
    //common
    TextView tv_total;
    TextView tv_per;
    TextView tv_profit;
    //for account
    TextView tv_account_cost;
    TextView tv_account_buy;
    //for wishlist
    CheckAccountAdapter checkAccountAdapter = null;
    Button btn_delete;
    Button btn_buy;

    @Override
    public void onResume() {
        super.onResume();

    }

    public static CheckIndexPagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CheckIndexPagerFragment fragment = new CheckIndexPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<>();
        checkListView = (ListView) view.findViewById(R.id.lv_check_index);
        checkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        boolean isOnCheckBox = (mPage == 0) ? false : true;

        if(mPage==0){
            checkAccountAdapter = new CheckAccountAdapter(view.getContext(), items, isOnCheckBox);
            checkListView.setAdapter(checkAccountAdapter);
        }
        else{
            checkIndexAdapter = new CheckIndexAdapter(view.getContext(), items, isOnCheckBox);
            checkListView.setAdapter(checkIndexAdapter);
        }

        accountBottomLayout = new AccountBottomLayout(view);
        wishListBottomLayout = new WishListBottomLayout(view);

        if (mPage == 0) {
            getCheckIndex();
            accountBottomLayout.setVisible(true);
            wishListBottomLayout.setVisible(false);
        } else {
            getCheckIndex();
            accountBottomLayout.setVisible(false);
            wishListBottomLayout.setVisible(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        holder = inflater.inflate(R.layout.layout_check_listview, container, false);
        Log.d("checkIndexPageFragment", "");
        check_checkbox = (CheckBox)holder.findViewById(R.id.check_checkbox);
        if(mPage==0) {
            check_checkbox.setVisibility(View.INVISIBLE);
            tv_total = (TextView)holder.findViewById(R.id.tv_account_total);

            tv_account_cost = (TextView)holder.findViewById(R.id.tv_account_cost);
            tv_account_buy = (TextView)holder.findViewById(R.id.tv_account_buy);
            tv_per = (TextView)holder.findViewById(R.id.tv_account_per);
            tv_profit = (TextView)holder.findViewById(R.id.tv_account_profit);
        }
        else{
            holder.findViewById(R.id.layout_check_listview_account_header).setVisibility(View.INVISIBLE);
            tv_total = (TextView)holder.findViewById(R.id.tv_wish_total);
            tv_profit = (TextView)holder.findViewById(R.id.tv_wish_profit);

            tv_per = (TextView)holder.findViewById(R.id.tv_wish_per);

            btn_buy = (Button)holder.findViewById(R.id.btn_buy);
            btn_delete = (Button)holder.findViewById(R.id.btn_delete);

            btn_buy.setOnClickListener(this);
            btn_delete.setOnClickListener(this);
        }

        check_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckBoxs();
            }
        });
        return holder;
    }

    public void setAccountBottomValue(){
        int total = 0;
        int costTotal=0;
        int buyTotal=0;
        for(int i = 0; i < items.size(); i++){
            total += items.get(i).getDiscountPrice() * items.get(i).getQty();
            costTotal += items.get(i).getCostPrice() * items.get(i).getQty();
            buyTotal += items.get(i).getDiscountPrice() * items.get(i).getQty();
        }
        tv_total.setText(GlobalVar.setComma(total));
        tv_profit.setText(GlobalVar.setComma(costTotal - total));
        tv_per.setText(String.format("%.2f %%", GlobalVar.Division(costTotal - total, costTotal)*100));

        if (mPage == 0){
            tv_account_cost.setText(GlobalVar.setComma(costTotal));
            tv_account_buy.setText(GlobalVar.setComma(buyTotal));
        }
    }
    private void getCheckIndex() {

        String testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-get_wishlist_product.php?tableNo=" + GlobalVar.currentTable;
        if(mPage==0)
            testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-get-order-item.php?tableNo="+GlobalVar.currentTable;

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        items.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                CheckIndexItem item = new CheckIndexItem(response.getJSONObject(i));
                                items.add(item);
                            } catch (JSONException e) {
                                CheckIndexItem item = new CheckIndexItem();
                                items.add(item);
                                e.printStackTrace();
                            }
                        }
                        setAccountBottomValue();
                        if(mPage==0) {
                            checkAccountAdapter.notifyDataSetChanged();
                        }
                        else{
                            checkIndexAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                items.clear();
                if(mPage==0) {
                    checkAccountAdapter.notifyDataSetChanged();
                }
                else{
                    checkIndexAdapter.notifyDataSetChanged();
                }

                error.printStackTrace();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void setCheckBoxs(){

        if(check_checkbox.isChecked()) {
//            check_checkbox.setSelected(true);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setIsclicked(true);
            }
        }
        else{
//            check_checkbox.setSelected(false);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setIsclicked(false);
            }
        }
        checkIndexAdapter.notifyDataSetChanged();
    }

    public ArrayList getCheckedItems() {
        ArrayList<CheckIndexItem> list = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getisclicked()) {
                list.add(items.get(i));
            }
        }

        return list;
    }

    public void sendRequest(String api) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, api, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    if(status.equals("1")){
//                        getCheckIndex();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
    public void sendRequestDelete(String api) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, api, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    if(status.equals("1")){
                        getCheckIndex();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public String deleteSelectedItem() {
        ArrayList<CheckIndexItem> items = getCheckedItems();
        if(items.size() == 0) {
            return null;
        }

        String query = "";
        for(int i = 0 ; i < items.size() ; i++) {
            CheckIndexItem item = items.get(i);
            query += String.format("&%d[0]=%d&%d[1]=%d", i, item.getProductID(),
                    i, item.getOrderID());
        }
        query = query.replaceFirst("&", "");

        String api = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-remove-wishlist.php?" +
                query;
//        sendRequest(api);
        return api;
    }

    public String buySelectedItem() {
        ArrayList<CheckIndexItem> items = getCheckedItems();
        if(items.size() == 0) {
            return null;
        }

        String query = "tableNo=" + GlobalVar.currentTable;
        for(int i = 0 ; i < items.size() ; i++) {
            CheckIndexItem item = items.get(i);
            query += String.format("&%d[0]=%d&%d[1]=%d&%d[2]=%d", i, item.getProductID(),
                    i, item.getQty(), i, item.getOrderAmount());
        }

        String api = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-addorder.php?" +
                query;
//        deleteSelectedItem();
//        sendRequest(api);
        return api;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_delete) {
            String deleteApi = deleteSelectedItem();
            if(deleteApi!=null)
                sendRequestDelete(deleteApi);

        } else if(v.getId() == R.id.btn_buy) {
            String buyApi = buySelectedItem();
            if(buyApi!=null)
                sendRequest(buyApi);
            String deleteApi = deleteSelectedItem();
            if(deleteApi!=null)
                sendRequestDelete(deleteApi);
        }
//        getCheckIndex();
    }
}
