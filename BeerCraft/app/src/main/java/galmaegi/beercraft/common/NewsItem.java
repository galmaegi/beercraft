package galmaegi.beercraft.common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import galmaegi.beercraft.GlobalVar;

public class NewsItem {
    private int newsID;
    private int divisionID;
    private String newsTitle;
    private String content;
    private String content_newsImage;
    private Date startDate;
    private Date endDate;
    private Date entryDate;

    public NewsItem() {
        newsID = 0;
        divisionID = 0;
        newsTitle = "";
        content = "";
        content_newsImage = "";
        startDate = new Date();
        endDate = new Date();
        entryDate = new Date();
    }

    public NewsItem(JSONObject object) throws JSONException {
        newsID = object.getInt("newsID");
        divisionID = object.getInt("divisionID");
        newsTitle = object.getString("newsTitle");
        content = object.getString("content");
        content_newsImage = object.getString("content_newsImage");
        startDate = GlobalVar.StringToDate("startDate");
        endDate = GlobalVar.StringToDate("endDate");
        entryDate = GlobalVar.StringToDate("entryDate");
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_newsImage() {
        return content_newsImage;
    }

    public void setContent_newsImage(String content_newsImage) {
        this.content_newsImage = content_newsImage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}
