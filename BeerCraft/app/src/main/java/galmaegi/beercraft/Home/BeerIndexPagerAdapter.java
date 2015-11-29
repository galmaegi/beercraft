package galmaegi.beercraft.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BeerIndexPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Draft Beer Index", "Bottled Beer Index"};

    private BeerIndexPagerFragment DBIPage;

    public BeerIndexPagerFragment getBBIPage() {
        return BBIPage;
    }

    public BeerIndexPagerFragment getDBIPage() {
        return DBIPage;
    }

    private BeerIndexPagerFragment BBIPage;

    public BeerIndexPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            DBIPage = BeerIndexPagerFragment.newInstance(0);
            return DBIPage;
        }
        else{
            BBIPage = BeerIndexPagerFragment.newInstance(1);
            return BBIPage;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}