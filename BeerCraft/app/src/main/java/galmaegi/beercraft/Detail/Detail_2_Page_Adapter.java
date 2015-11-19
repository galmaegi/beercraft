package galmaegi.beercraft.Detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by root on 15. 11. 14.
 */
public class Detail_2_Page_Adapter extends FragmentPagerAdapter {


    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "1 DAY", "15 DAYS","1 MONTH"};

    public Detail_2_Page_Adapter(FragmentManager fm, String group_id) {
        super(fm);

    }
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        return Detail_2_PageFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
