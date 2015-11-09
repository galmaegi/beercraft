package galmaegi.beercraft;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import galmaegi.beercraft.Detail.DetailActivity;

/**
 * Created by kyoungmin on 2015-10-24.
 */
public class BaseActivity extends Activity implements View.OnClickListener{
    //현재 BaseActivity를 상속한 엑티비티가 어딘지 확인
    public Context currentContext;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_back:
                break;
            case R.id.btn_home:
                Log.d("is clicked", "hello");
                Intent intent = new Intent(currentContext,DetailActivity.class);
                currentContext.startActivity(intent);
                this.finish();
                break;
            case R.id.btn_beer:
                break;
            case R.id.btn_sidemenu:
                break;
            case R.id.btn_news:
                break;
            case R.id.btn_check:
                break;

        }
    }

}
