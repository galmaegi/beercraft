package galmaegi.beercraft.SideMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import galmaegi.beercraft.GlobalVar;

public class SidemenuIndexItem {
    private int sideMenuID;
    private int classificationCode;
    private String productName;
    private String englishName;
    private int price;
    private String proudctImage;
    private int creamy;
    private int flavory;
    private int pure;
    private int clumsy;
    private int sweetness;
    private int bitterness;
    private String tastingNote;
    private String beerStory;
    private Date entryDate;
    private Date modifyDate;
    private SidemenuIndexPagerFragment.TYPE type;

    public SidemenuIndexPagerFragment.TYPE getType() {
        return type;
    }

    public void setType(SidemenuIndexPagerFragment.TYPE type) {
        this.type = type;
    }

    public SidemenuIndexItem() {

        sideMenuID = 0;
        classificationCode = 0;
        productName = "";
        englishName = "";
        price = 0;
        proudctImage = "";
        creamy = 0;
        flavory = 0;
        pure = 0;
        clumsy = 0;
        sweetness = 0;
        bitterness = 0;
        tastingNote = "";
        beerStory = "";
        entryDate = new Date();
        modifyDate = new Date();
    }

    public SidemenuIndexItem(JSONObject object) throws JSONException{
        sideMenuID = GlobalVar.SafetyJSONStringToInt(object, "sideMenuID");
        classificationCode = GlobalVar.SafetyJSONStringToInt(object, "classificationCode");
        productName = object.getString("productName");
        englishName = object.getString("englishName");
        price = GlobalVar.SafetyJSONStringToInt(object, "price");
        proudctImage = object.getString("proudctImage");
        creamy = GlobalVar.SafetyJSONStringToInt(object, "creamy");
        flavory = GlobalVar.SafetyJSONStringToInt(object, "flavory");
        pure = GlobalVar.SafetyJSONStringToInt(object, "pure");
        clumsy = GlobalVar.SafetyJSONStringToInt(object, "clumsy");
        sweetness = GlobalVar.SafetyJSONStringToInt(object, "sweetness");
        bitterness = GlobalVar.SafetyJSONStringToInt(object, "bitterness");
        tastingNote = object.getString("tastingNote");
        beerStory = object.getString("beerStory");
        entryDate = GlobalVar.StringToDate(object.getString("entryDate"));
        modifyDate = GlobalVar.StringToDate(object.getString("modifyDate"));
    }

    public void setSideMenuID(int sideMenuID) {
        this.sideMenuID = sideMenuID;
    }

    public void setClassificationCode(int classificationCode) {
        this.classificationCode = classificationCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProudctImage(String proudctImage) {
        this.proudctImage = proudctImage;
    }

    public void setCreamy(int creamy) {
        this.creamy = creamy;
    }

    public void setFlavory(int flavory) {
        this.flavory = flavory;
    }

    public void setPure(int pure) {
        this.pure = pure;
    }

    public void setClumsy(int clumsy) {
        this.clumsy = clumsy;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    public void setBitterness(int bitterness) {
        this.bitterness = bitterness;
    }

    public void setTastingNote(String tastingNote) {
        this.tastingNote = tastingNote;
    }

    public void setBeerStory(String beerStory) {
        this.beerStory = beerStory;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getSideMenuID() {
        return sideMenuID;
    }

    public int getClassificationCode() {
        return classificationCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public int getPrice() {
        return price;
    }

    public String getProudctImage() {
        return proudctImage;
    }

    public int getCreamy() {
        return creamy;
    }

    public int getFlavory() {
        return flavory;
    }

    public int getPure() {
        return pure;
    }

    public int getClumsy() {
        return clumsy;
    }

    public int getSweetness() {
        return sweetness;
    }

    public int getBitterness() {
        return bitterness;
    }

    public String getTastingNote() {
        return tastingNote;
    }

    public String getBeerStory() {
        return beerStory;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }
}
