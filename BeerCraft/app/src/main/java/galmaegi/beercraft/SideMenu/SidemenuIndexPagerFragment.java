package galmaegi.beercraft.SideMenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;
import galmaegi.beercraft.common.NewsItem;

public class SidemenuIndexPagerFragment extends Fragment implements View.OnClickListener{
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private ListView sidemenuListview;
    private SidemenuIndexAdapter sidemenuAdapter;
    private ArrayList<SidemenuIndexItem> items;

    Button side_list_btn_index;
    Button side_list_btn_current;

    ArrayList<Boolean> isSort;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.side_list_btn_index:
                this.sortList(0);
                break;
            case R.id.side_list_btn_current:
                this.sortList(1);
                break;
        }
    }

    enum TYPE {
        KBX,
        GUEST
    }

    static class sortSideMenu implements Comparator<SidemenuIndexItem> {
        public int sortArg;
        public SidemenuIndexItem lhs;
        public SidemenuIndexItem rhs;
        public boolean isSort;

        public sortSideMenu(int sortArg, boolean isSort){
            this.sortArg = sortArg;
            this.isSort = isSort;
        }
        @Override
        public int compare(SidemenuIndexItem lhs, SidemenuIndexItem rhs) {
            int returnValue = -1;
            if(isSort == true) {
                this.lhs = lhs;
                this.rhs = rhs;
            } else {
                this.rhs = lhs;
                this.lhs = rhs;
            }

            if(sortArg == 0){
                returnValue = sortByName();
            } else if(sortArg == 1){
                returnValue = sortByStyle();
            }
            return returnValue;
        }
        public int sortByName(){
            return lhs.getName().compareTo(rhs.getName());
        }
        public int sortByStyle(){
            return lhs.getPrice()-rhs.getPrice();
        }


    }

    public static SidemenuIndexPagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SidemenuIndexPagerFragment fragment = new SidemenuIndexPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    public void sortList(int sortArg){
        Collections.sort(this.items, new sortSideMenu(sortArg,isSort.get(sortArg)));
        isSort.set(sortArg,!isSort.get(sortArg));
        this.sidemenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<>();
        sidemenuListview = (ListView) view.findViewById(R.id.lv_sidemenu_index);
        sidemenuAdapter = new SidemenuIndexAdapter(view.getContext(), items);

        sidemenuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SidemenuFragment.side_simple_default.setVisibility(View.INVISIBLE);
                SidemenuFragment.sidetasting_simple_default.setVisibility(View.INVISIBLE);
                SidemenuFragment.sidemenuFragment.setSimpleView(items.get(position));
                SidemenuFragment.sidemenuFragment.setTastingNote(items.get(position));
            }
        });
        sidemenuListview.setAdapter(sidemenuAdapter);

        if(mPage == 0) {
            getMenuIndex(TYPE.KBX);
        } else {
            getMenuIndex(TYPE.GUEST);
        }

        side_list_btn_index = (Button)view.findViewById(R.id.side_list_btn_index);
        side_list_btn_current = (Button)view.findViewById(R.id.side_list_btn_current);

        side_list_btn_index.setOnClickListener(this);
        side_list_btn_current.setOnClickListener(this);

        isSort = new ArrayList<Boolean>();
        for(int i=0; i < 2; i++)
            isSort.add(i,true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sidemenu_listview, container, false);

        return view;
    }


    private void getMenuIndex(TYPE type) {
        final String testURL;
        final TYPE type1 = type;

        if(type == TYPE.KBX) {
            testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-side_menu.php?type=kbx";
        } else {
            testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-side_menu.php?type=guest";
        }

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0 ; i < response.length() ; i++) {
                            try {
                                SidemenuIndexItem item = new SidemenuIndexItem(response.getJSONObject(i));
                                item.setType(type1);
                                items.add(item);
                            } catch (JSONException e) {
                                SidemenuIndexItem item = new SidemenuIndexItem();
                                item.setProductName("JSONException");
                                item.setEntryDate(new Date());
                                items.add(item);
                                e.printStackTrace();
                            }
                        }
                        sidemenuAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}