package galmaegi.beercraft.News;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import galmaegi.beercraft.GlobalVar;

/**
 * Created by jongsu on 2015. 11. 18..
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;


    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsPagerFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return GlobalVar.newstabTitles[position];
    }
}