package galmaegi.beercraft.common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class BeerIndexItem {
    private int productID;
    private int divisionID;
    private int classificationCode;
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
    private String testingNote;
    private String beerStory;
    private Date entryDate;
    private Date modifyDate;
    private int groupId;

    public BeerIndexItem() {
        productID = 0;
        divisionID = 0;
        classificationCode = 0;
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
        testingNote = "";
        beerStory = "";
        entryDate = null;
        modifyDate = null;
        groupId = 0;

    }

    public BeerIndexItem(JSONObject object) throws JSONException{
        productID = Integer.parseInt(object.getString("productID"));
        divisionID = Integer.parseInt(object.getString("divisionID"));
        classificationCode = Integer.parseInt(object.getString("classificationCode"));
        productName = object.getString("productName");
        style = object.getString("style");
        productDescription = object.getString("productDescription");
        englishName = object.getString("englishName");
        country = object.getString("country");
        strength = Integer.parseInt(object.getString("strength"));
        bitterTaste = object.getString("bitterTaste");
        volume = Integer.parseInt(object.getString("volume"));
        rateBeerScore = Integer.parseInt(object.getString("rateBeerScore"));
        price = Integer.parseInt(object.getString("price"));
        sellingPrice = Integer.parseInt(object.getString("sellingPrice"));
        productImage = object.getString("productImage");
        creamy = Integer.parseInt(object.getString("creamy"));
        flavory = Integer.parseInt(object.getString("flavory"));
        pure = Integer.parseInt(object.getString("pure"));
        clumsy = Integer.parseInt(object.getString("clumsy"));
        sweetness = Integer.parseInt(object.getString("sweetness"));
        bitterness = Integer.parseInt(object.getString("bitterness"));
        testingNote = object.getString("testingNote");
        beerStory = object.getString("beerStory");
//        entryDate = object.getString("entryDate");
//        modifyDate = object.getString("modifyDate");
        groupId = Integer.parseInt(object.getString("grp_id"));
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public void setClassificationCode(int classificationCode) {
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

    public void setTestingNote(String testingNote) {
        this.testingNote = testingNote;
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

    public int getProductID() {
        return productID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public int getClassificationCode() {
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

    public String getTestingNote() {
        return testingNote;
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
}
