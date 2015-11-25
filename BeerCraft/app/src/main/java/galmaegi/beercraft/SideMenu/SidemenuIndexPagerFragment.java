package galmaegi.beercraft.SideMenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.NewsItem;

public class SidemenuIndexPagerFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private ListView sidemenuListview;
    private SidemenuIndexAdapter sidemenuAdapter;
    private ArrayList<SidemenuIndexItem> items;

    enum TYPE {
        KBX,
        GUEST
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<>();
        sidemenuListview = (ListView) view.findViewById(R.id.lv_sidemenu_index);
        sidemenuAdapter = new SidemenuIndexAdapter(view.getContext(), items);

        sidemenuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SidemenuFragment.tastingNote != null) {
                    SidemenuFragment.tastingNote.setView(items.get(position));
                }
            }
        });
        sidemenuListview.setAdapter(sidemenuAdapter);

        if(mPage == 0) {
            getMenuIndex(TYPE.KBX);
        } else {
            getMenuIndex(TYPE.GUEST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sidemenu_listview, container, false);

        return view;
    }


    private void getMenuIndex(TYPE type) {
        final String testURL;

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