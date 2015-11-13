package galmaegi.beercraft;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import galmaegi.beercraft.R;


/**
 * Created by jongsu on 2015. 11. 13..
 */
public class BeerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_home, container, false);
    }
}
