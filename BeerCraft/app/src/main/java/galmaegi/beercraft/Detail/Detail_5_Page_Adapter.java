package galmaegi.beercraft.Detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by root on 15. 11. 14.
 */
public class Detail_5_Page_Adapter extends FragmentPagerAdapter {

    String tastingnote,beerstory;
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Tasting Note", "Beer Story"};

    public Detail_5_Page_Adapter(FragmentManager fm,String tastingnote,String beerstory) {
        super(fm);
        this.tastingnote = tastingnote;
        this.beerstory = beerstory;
    }



    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        String argument = "";
        if(position==0)
            argument = tastingnote;
        else if(position==1)
            argument = beerstory;
        return Detail_5_PageFragment.newInstance(position + 1,argument);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
