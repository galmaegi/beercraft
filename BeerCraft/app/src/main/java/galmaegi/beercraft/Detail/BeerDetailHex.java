package galmaegi.beercraft.Detail;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

public class BeerDetailHex extends View {

    BeerDetailHexShape BeerDetailHexShape;
    float ratioRadius;
    int numberOfPoint = 3; //default
    HashMap values;

    public BeerDetailHex(Context context) {
        super(context);
        initBeerDetailView();
    }

    public BeerDetailHex(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBeerDetailView();
    }

    public BeerDetailHex(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBeerDetailView();
    }

    public void initBeerDetailView(){
        BeerDetailHexShape = new BeerDetailHexShape();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        if((w==0) || (h==0)){
            return;
        }

        float x = (float)w/2.0f;
        float y = (float)h/2.0f;
        float radius;
        if(w > h){
            radius = h * ratioRadius;
        }else{
            radius = w * ratioRadius;
        }

        BeerDetailHexShape.setPolygon(x, y, radius,values);
        canvas.drawPath(BeerDetailHexShape.getPath(), BeerDetailHexShape.getPaint());
    }

    public void setShapeRadiusRatio(float ratio){
        ratioRadius = ratio;
    }

    public void setNumberOfPoint(int pt){
        numberOfPoint = pt;
    }

    public void setValues(HashMap values){this.values = values;}

}