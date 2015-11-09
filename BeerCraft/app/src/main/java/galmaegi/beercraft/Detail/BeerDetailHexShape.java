package galmaegi.beercraft.Detail;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class BeerDetailHexShape {

    private Paint paint;
    private Path path;

    public BeerDetailHexShape() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
    }

    public void setCircle(float x, float y, float radius, Path.Direction dir){
        path.reset();
        path.addCircle(x, y, radius, dir);
    }

    public void setPolygon(float x, float y, float radius,int []values){

        double section = 2.0 * Math.PI/6;

        path.reset();
        path.moveTo(
                (float) (x + radius * Math.cos(0)),
                (float) (y + radius * Math.sin(0)));

//        for(int i=1; i<numOfPt; i++){
//            path.lineTo(
//                    (float)(x + radius * Math.cos(section * i)),
//                    (float)(y + radius * Math.sin(section * i)));
//        }
//        for(int i=0;i<5;i++){
//            path.lineTo(
//                    (float)(x + radius * Math.cos(section * i)),
//                    (float)(y + radius * Math.sin(section * i))+values[i-1]);
//        }
        path.lineTo(
                (float)(x + radius * Math.cos(section * 1)),
                (float)(y + radius * Math.sin(section * 1)));

        path.lineTo(
                (float)(x + radius * Math.cos(section * 2)),
                (float)(y + radius * Math.sin(section * 2)));

        path.lineTo(
                (float)(x + radius * Math.cos(section * 3)),
                (float)(y + radius * Math.sin(section * 3))+20);

        path.lineTo(
                (float)(x + radius * Math.cos(section * 4)),
                (float)(y + radius * Math.sin(section * 4))-20);

        path.lineTo(
                (float)(x + radius * Math.cos(section * 5)),
                (float)(y + radius * Math.sin(section * 5)));

//        path.lineTo(
//                (float)(x + radius * Math.cos(section * 4)),
//                (float)(y + radius * Math.sin(section * 4))-100);


        path.close();

    }

    public Path getPath(){
        return path;
    }

    public Paint getPaint(){
        return paint;
    }

}