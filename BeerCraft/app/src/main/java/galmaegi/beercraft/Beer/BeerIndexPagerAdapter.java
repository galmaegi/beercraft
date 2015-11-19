package galmaegi.beercraft.Beer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BeerIndexPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Bottled Beer Index", "Draft Beer Index"};
    private BeerIndexPagerFragment.ItemClickListener listener;

    public BeerIndexPagerAdapter(FragmentManager fm, BeerIndexPagerFragment.ItemClickListener listener) {
        super(fm);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        BeerIndexPagerFragment fragment = BeerIndexPagerFragment.newInstance(position );
        fragment.setItemClickListener(listener);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
