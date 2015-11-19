package galmaegi.beercraft.common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import galmaegi.beercraft.GlobalVar;

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
    private String tastingNote;
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
        tastingNote = "";
        beerStory = "";
        entryDate = new Date();
        modifyDate = new Date();
        groupId = 0;

    }

    public BeerIndexItem(JSONObject object) throws JSONException{
        if(object.isNull("productID")) {
            productID = 0;
        } else {
            productID = Integer.parseInt(object.getString("productID"));
        }

        if(object.isNull("divisionID")) {
            divisionID = 0;
        } else {
            divisionID = Integer.parseInt(object.getString("divisionID"));
        }

        if(object.isNull("classificationCode")) {
            classificationCode = 0;
        } else {
            classificationCode = Integer.parseInt(object.getString("classificationCode"));
        }

        if(object.isNull("productName")) {
            productName = "Empty";
        } else {
            productName = object.getString("productName");
        }

        if(object.isNull("style")) {
            style = "Empty";
        } else {
            style = object.getString("style");
        }

        if(object.isNull("productDescription")) {
            productDescription = "Empty";
        } else {
            productDescription = object.getString("productDescription");
        }

        if(object.isNull("englishName")) {
            englishName = "Empty";
        } else {
            englishName = object.getString("englishName");
        }

        if(object.isNull("country")) {
            country = "Empty";
        } else {
            country = object.getString("country");
        }

        if(object.isNull("strength")) {
            strength = 0;
        } else {
            strength = Integer.parseInt(object.getString("strength"));
        }

        if(object.isNull("bitterTaste")) {
            bitterTaste = "";
        } else {
            bitterTaste = object.getString("bitterTaste");
        }

        if(object.isNull("volume")) {
            volume = 0;
        } else {
            volume = Integer.parseInt(object.getString("volume"));
        }

        if(object.isNull("rateBeerScore")) {
            rateBeerScore = 0;
        } else {
            rateBeerScore = Integer.parseInt(object.getString("rateBeerScore"));
        }

        if(object.isNull("price")) {
            price = 0;
        } else {
            price = Integer.parseInt(object.getString("price"));
        }

        if(object.isNull("sellingPrice")) {
            sellingPrice = 0;
        } else {
            sellingPrice = Integer.parseInt(object.getString("sellingPrice"));
        }

        if(object.isNull("productImage")) {
            productImage = "";
        } else {
            productImage = object.getString("proudctImage");
        }

        if(object.isNull("creamy")) {
            creamy = 0;
        } else {
            creamy = Integer.parseInt(object.getString("creamy"));
        }

        if(object.isNull("flavory")) {
            flavory = 0;
        } else {
            flavory = Integer.parseInt(object.getString("flavory"));
        }

        if(object.isNull("pure")) {
            pure = 0;
        } else {
            pure = Integer.parseInt(object.getString("pure"));
        }

        if(object.isNull("clumsy")) {
            clumsy = 0;
        } else {
            clumsy = Integer.parseInt(object.getString("clumsy"));
        }

        if(object.isNull("sweetness")) {
            sweetness = 0;
        } else {
            sweetness = Integer.parseInt(object.getString("sweetness"));
        }

        if(object.isNull("bitterness")) {
            bitterness = 0;
        } else {
            bitterness = Integer.parseInt(object.getString("bitterness"));
        }

        if(object.isNull("tastingNote")) {
            tastingNote = "Empty";
        } else {
            tastingNote = object.getString("tastingNote");
        }

        if(object.isNull("beerStory")) {
            beerStory = "";
        } else {
            beerStory = object.getString("beerStory");
        }

        if(object.isNull("entryDate")) {
            entryDate = new Date();
        } else {
            entryDate = GlobalVar.StringToDate(object.getString("entryDate"));
        }

        if(object.isNull("modifyDate")) {
            modifyDate = new Date();
        } else {
            modifyDate = GlobalVar.StringToDate(object.getString("modifyDate"));
        }

        if(object.isNull("grp_id")) {
            groupId = 0;
        } else {
            groupId = Integer.parseInt(object.getString("grp_id"));
        }
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
}
