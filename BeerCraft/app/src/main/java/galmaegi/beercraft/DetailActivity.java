package galmaegi.beercraft;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by kyoungmin on 2015-10-24.
 */
public class DetailActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //BaseActivity에서 Activity를 띄워주기 위한 작업
        super.currentContext = this;

        //공통뷰들을 찾아주기 위한 작업을 수행해야함
        findViewById(R.id.detail_left_side).findViewById(R.id.btn_home).setOnClickListener(this);
    }
}
