package galmaegi.beercraft.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;
import galmaegi.beercraft.common.NewsItem;

public class NewsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NewsItem> items;

    public NewsAdapter(Context context) {
        super();
        mContext = context;
        items = new ArrayList<>();
    }

    public NewsAdapter(Context context, ArrayList items) {
        super();
        mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsViewHolder holder;

        if(convertView == null) {
            holder = new NewsViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_news_listview1_item, null);

            holder.mTitle = (TextView) convertView.findViewById(R.id.tv_title);
//            holder.mDate = (TextView) convertView.findViewById(R.id.tv_date);
            holder.mThumbnail = (NetworkImageView) convertView.findViewById(R.id.iv_thumbnail);

            convertView.setTag(holder);
        } else {
            holder = (NewsViewHolder) convertView.getTag();
        }

        NewsItem item = items.get(position);

        holder.mTitle.setText(item.getNewsTitle());
//        holder.mDate.setText(GlobalVar.DateToString(item.getEntryDate()));
        holder.mThumbnail.setImageUrl(item.getContent_newsImage(), AppController.getInstance().getImageLoader());

        return convertView;
    }
}
