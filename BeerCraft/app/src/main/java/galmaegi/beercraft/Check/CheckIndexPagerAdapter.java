package galmaegi.beercraft.Check;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import galmaegi.beercraft.GlobalVar;

/**
 * Created by jongsu on 2015. 11. 29..
 */
public class CheckIndexPagerAdapter  extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;


    public CheckIndexPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        CheckIndexPagerFragment fragment = CheckIndexPagerFragment.newInstance(position);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return GlobalVar.checktabTitles[position];
    }
}
