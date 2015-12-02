package galmaegi.beercraft.common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;

import galmaegi.beercraft.GlobalVar;

public class BeerIndexItem {
    private int productID;
    private int divisionID;
    private String classificationCode;
    private String productName;
    private String style;
    private String productDescription;
    private String englishName;
    private String country;
    private int strength;
    private String bitterTaste;
    private int volume;
    private int rateBeerScore;
    private int price;
    private int sellingPrice;
    private String productImage;
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
    private int groupId;
    private int last;
    private int increase;
    private double rate;

    public BeerIndexItem() {
        productID = 0;
        divisionID = 0;
        classificationCode = "";
        productName = "";
        style = "";
        productDescription = "";
        englishName = "";
        country = "";
        strength = 0;
        bitterTaste = "";
        volume = 0;
        rateBeerScore = 0;
        price = 0;
        sellingPrice = 0;
        productImage = "";
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
        groupId = 0;
        last = 0;

    }

    public int getIncrease() {
        return increase;
    }

    public double getRate() {
        return rate;
    }
    public String getBeerName(){
        if(GlobalVar.language== Locale.ENGLISH)
            return getEnglishName();
        else
            return getProductName();
    }

    public BeerIndexItem(JSONObject object) throws JSONException{
        productID = GlobalVar.SafetyJSONStringToInt(object, "productID");
        divisionID = GlobalVar.SafetyJSONStringToInt(object, "divisionID");
        classificationCode = object.getString("classificationCode");
        productName = object.getString("productName");
        style = object.getString("style");
        productDescription = object.getString("productDescription");
        englishName = object.getString("englishName");
        country = object.getString("country");
        strength = GlobalVar.SafetyJSONStringToInt(object, "strength");
        bitterTaste = object.getString("bitterTaste");
        volume = GlobalVar.SafetyJSONStringToInt(object, "volume");
        rateBeerScore = GlobalVar.SafetyJSONStringToInt(object, "rateBeerScore");
        price = GlobalVar.SafetyJSONStringToInt(object, "price");
        sellingPrice = GlobalVar.SafetyJSONStringToInt(object, "sellingPrice");
        productImage = object.getString("proudctImage");
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
        groupId = GlobalVar.SafetyJSONStringToInt(object, "grp_id");
        last = GlobalVar.SafetyJSONStringToInt(object, "last");

        increase = sellingPrice - last;
        rate = GlobalVar.Division(sellingPrice-last, sellingPrice)*100;

    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setBitterTaste(String bitterTaste) {
        this.bitterTaste = bitterTaste;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setRateBeerScore(int rateBeerScore) {
        this.rateBeerScore = rateBeerScore;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
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

    public void setEntryDate(String entryDate) {

    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setModifyDate(String modifyDate) {

    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getProductID() {
        return productID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getStyle() {
        return style;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getCountry() {
        return country;
    }

    public int getStrength() {
        return strength;
    }

    public String getBitterTaste() {
        return bitterTaste;
    }

    public int getVolume() {
        return volume;
    }

    public int getRateBeerScore() {
        return rateBeerScore;
    }

    public int getPrice() {
        return price;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public String getProductImage() {
        return productImage;
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

    public int getGroupId() {
        return groupId;
    }

    public int getLast() {
        return last;
    }
}
