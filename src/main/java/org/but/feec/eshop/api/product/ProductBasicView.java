package org.but.feec.eshop.api.product;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductBasicView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty product = new SimpleStringProperty();
    private StringProperty detail = new SimpleStringProperty();
    private StringProperty hasprice = new SimpleStringProperty();
    private StringProperty price = new SimpleStringProperty();
    private StringProperty shopid = new SimpleStringProperty();
    private StringProperty categoryid = new SimpleStringProperty();
    private StringProperty currencyid = new SimpleStringProperty();


    public Long getId() {
        return idProperty().get();
    }

    public void setId(Long id) {
        this.idProperty().setValue(id);
    }

    public String getProduct(){
        return productProperty().get();
    }

    public void setProduct(String product){
        this.productProperty().setValue(product);
    }

    public String getDetail(){
        return detailProperty().get();
    }

    public void setDetail(String detail){
        this.detailProperty().setValue(detail);
    }

    public String getHasprice(){
        return haspriceProperty().get();
    }

    public void setHasprice(String hasprice){
        this.haspriceProperty().setValue(hasprice);
    }

    public String getPrice(){
        return priceProperty().get();
    }

    public void setPrice(String price){
        this.priceProperty().setValue(price);
    }

    public String getShopid(){
        return shopidProperty().get();
    }

    public void setShopid(String shopid){
        this.shopidProperty().setValue(shopid);
    }

    public String getCategoryid(){
        return categoryidProperty().get();
    }

    public void setCategoryid(String categoryid){
        this.categoryidProperty().setValue(categoryid);
    }

    public String getCurrencyid(){
        return currencyidProperty().get();
    }

    public void setCurrencyid(String currencyid){
        this.currencyidProperty().setValue(currencyid);
    }



    public LongProperty idProperty() {
        return id;
    }

    public StringProperty productProperty() {
        return product;
    }

    public StringProperty detailProperty() {
        return detail;
    }

    public StringProperty haspriceProperty() {
        return hasprice;
    }

    public StringProperty priceProperty() {
        return price;
    }

    public StringProperty shopidProperty() {
        return shopid;
    }

    public StringProperty categoryidProperty() {
        return categoryid;
    }

    public StringProperty currencyidProperty() {
        return currencyid;
    }



}

}
