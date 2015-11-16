package galmaegi.beercraft.imageLoader;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 15.
 */
public class GetImage {
    ImageLoader imageloader;
    NetworkImageView currentView;
    public GetImage(NetworkImageView currentView){
        imageloader = AppController.getInstance().getImageLoader();
        this.currentView = currentView;

    }

    public void getImagebyurl(String url){
        ImageLoader.ImageContainer test = imageloader.get(url,ImageLoader.getImageListener(currentView, R.drawable.beer_def,R.drawable.beer_error));

    }

}
