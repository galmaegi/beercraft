package galmaegi.beercraft;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 13.
 */
public class FragmentOne extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_detail, container, false);
    }

}
