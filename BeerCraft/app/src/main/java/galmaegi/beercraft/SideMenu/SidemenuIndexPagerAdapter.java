package galmaegi.beercraft.SideMenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import galmaegi.beercraft.GlobalVar;

public class SidemenuIndexPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;

    public SidemenuIndexPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return SidemenuIndexPagerFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return GlobalVar.foodtabTitles[position];
    }
}
