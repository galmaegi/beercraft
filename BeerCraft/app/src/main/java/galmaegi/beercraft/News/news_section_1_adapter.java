package galmaegi.beercraft.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 15.
 */
public class news_section_1_adapter extends ArrayAdapter<news_section_1_item> {
    private ArrayList<news_section_1_item> arrayItem;
    private LayoutInflater mInflater;
    ViewHolder viewHolder = null;

    protected class ViewHolder{
        protected com.android.volley.toolbox.NetworkImageView news_listView_thumbnail;
        protected TextView news_listView_title;
        protected TextView news_listView_time;
    }

    public news_section_1_adapter(Context context, int resource, ArrayList<news_section_1_item> objects){
        super(context,resource,objects);
        this.arrayItem = arrayItem;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder holder;

        if(convertView==null){
            convertView = mInflater.inflate(R.layout.news_section_1_item,parent,false);
            holder = new ViewHolder();
            holder.news_listView_title = (TextView)convertView.findViewById(R.id.news_listView_title);
            holder.news_listView_time = (TextView)convertView.findViewById(R.id.news_listView_time);
            holder.news_listView_thumbnail = (NetworkImageView)convertView.findViewById(R.id.news_listView_thumbnail);

        }

        return convertView;
    }
}
