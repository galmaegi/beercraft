package galmaegi.beercraft.Check;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import galmaegi.beercraft.GlobalVar;

public class CheckIndexItem {
    private int itemID;
    private int productID;
    private int classificationCode;
    private String productName;
    private int costPrice;
    private Double discountPrice;
    private int profitRate;
    private int orderAmount;
    private Date orderDate;
    private int orderID;
    private int qty;
    private boolean isclicked;

    public void setIsclicked(boolean isclicked) {
        this.isclicked = isclicked;
    }

    public boolean getisclicked() {
        return isclicked;
    }

    public CheckIndexItem() {
        itemID = 0;
        productID = 0;
        classificationCode = 0;
        productName = "";
        costPrice = 0;
        discountPrice = 0.0;
        profitRate = 0;
        orderAmount = 0;
        orderDate = new Date();
        orderID = 0;
        qty = 0;
        isclicked = false;
    }

    public CheckIndexItem(JSONObject object) throws JSONException {
        itemID = GlobalVar.SafetyJSONStringToInt(object, "itemID");
        productID = GlobalVar.SafetyJSONStringToInt(object, "productID");
        classificationCode = GlobalVar.SafetyJSONStringToInt(object, "classificationCode");
        productName = object.getString("productName");
        costPrice = GlobalVar.SafetyJSONStringToInt(object, "costPrice");
        discountPrice = GlobalVar.SafetyJSONStringToDouble(object, "discountPrice");
        profitRate = GlobalVar.SafetyJSONStringToInt(object, "profitRate");
        orderAmount = GlobalVar.SafetyJSONStringToInt(object, "orderAmount");
        orderDate = GlobalVar.StringToDate(object.getString("orderDate"));
        orderID = GlobalVar.SafetyJSONStringToInt(object, "orderID");
        qty = GlobalVar.SafetyJSONStringToInt(object, "qty");
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getClassificationCode() {
        return classificationCode;
    }

    public void setClassificationCode(int classificationCode) {
        this.classificationCode = classificationCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(int profitRate) {
        this.profitRate = profitRate;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
