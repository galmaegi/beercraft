package galmaegi.beercraft.Check;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import galmaegi.beercraft.R;

public class CheckIndexPagerFragment extends android.support.v4.app.Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    ListView checkListView = null;
    CheckIndexAdapter checkIndexAdapter = null;
    ArrayList<CheckIndexItem> items = null;

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
        checkListView = (ListView) view.findViewById(R.id.lv_beer_index);
        checkIndexAdapter = new CheckIndexAdapter(view.getContext(), items);

        checkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        checkListView.setAdapter(checkIndexAdapter);

        if (mPage == 0) {

        } else {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View holder = inflater.inflate(R.layout.layout_check_listview, container, false);

        return holder;
    }
}
