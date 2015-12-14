package galmaegi.beercraft.Detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 14.
 */
public class Detail_5_PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_PAGE_TEXT = "ARG_PAGE_TEXT";

    private int mPage;
    private String text;

    public static Detail_5_PageFragment newInstance(int page,String text) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(ARG_PAGE_TEXT, text);
        Detail_5_PageFragment fragment = new Detail_5_PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        text = getArguments().getString(ARG_PAGE_TEXT);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_section_5_page, container, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.detail_section_5_page_text);
        tvTitle.setText(text);
//        tvTitle.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}
