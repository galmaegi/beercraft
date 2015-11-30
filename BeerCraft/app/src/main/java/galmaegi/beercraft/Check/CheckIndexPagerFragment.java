package galmaegi.beercraft.Check;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

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

    TextView tv_total;
    TextView tv_wish_total;

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

        checkIndexAdapter = new CheckIndexAdapter(view.getContext(), items, isOnCheckBox);
        checkListView.setAdapter(checkIndexAdapter);

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
        }
        else{
            tv_total = (TextView)holder.findViewById(R.id.tv_wish_total);
        }
        check_checkbox.setOnClickListener(this);


        return holder;
    }
    public void setAccountBottomValue(){
        int total = 0;
        for(int i = 0; i < items.size(); i++){
            total += items.get(i).getCostPrice() * items.get(i).getQty();
        }
        tv_total.setText(total + "");
    }
    public void setWishBottomValue(){

    }

    private void getCheckIndex() {

        String testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-get_wishlist_product.php?tableNo=" + GlobalVar.currentTable;
        if(mPage==0)
            testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-get-order-item.php?tableNo="+GlobalVar.currentTable;

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

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
                        checkIndexAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    public void onClick(View v) {
        if(!check_checkbox.isSelected()) {
            check_checkbox.setSelected(true);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setIsclicked(true);
            }
        }
        else{
            check_checkbox.setSelected(false);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setIsclicked(false);
            }
        }
        checkIndexAdapter.notifyDataSetChanged();
    }
}
