package galmaegi.beercraft;

import android.content.Context;
import android.widget.TabHost;

/**
 * Created by jongsu on 2015. 11. 6..
 */
public class TabCreator {
    public static TabHost Create(Context context) {
        TabHost host = new TabHost(context);

        TabHost.TabSpec spec1 = host.newTabSpec("1").setContent(R.id.tab_view1).setIndicator("11");
        TabHost.TabSpec spec2 = host.newTabSpec("1").setContent(R.id.tab_view1).setIndicator("22");

        host.addTab(spec1);
        host.addTab(spec2);

        return host;
    }
}
