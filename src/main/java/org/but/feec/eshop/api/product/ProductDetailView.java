package org.but.feec.eshop.api.product;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductDetailView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty product = new SimpleStringProperty();
    private  LongProperty hasprice = new SimpleLongProperty();
    private  LongProperty price = new SimpleLongProperty();
    private  LongProperty currencyid = new SimpleLongProperty();
    private  LongProperty shopid = new SimpleLongProperty();
    private  LongProperty categoryid = new SimpleLongProperty();
    private StringProperty detail = new SimpleStringProperty() ;

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public StringProperty productProperty() {
        return product;
    }

    public void setProduct(String product) {
        this.product.set(product);
    }

    public LongProperty haspriceProperty() {
        return hasprice;
    }

    public void setHasprice(long hasprice) {
        this.hasprice.set(hasprice);
    }

    public LongProperty priceProperty() {
        return price;
    }

    public void setPrice(long price) {
        this.price.set(price);
    }

    public LongProperty currencyidProperty() {
        return currencyid;
    }

    public void setCurrencyid(long currencyid) {
        this.currencyid.set(currencyid);
    }

    public LongProperty shopidProperty() {
        return shopid;
    }

    public void setShopid(long shopid) {
        this.shopid.set(shopid);
    }

    public LongProperty categoryidProperty() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid.set(categoryid);
    }

    public StringProperty detailProperty() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail.set(detail);
    }

    public LongProperty getId() {return id;}

    public void setId(LongProperty id) {this.id = id;}

    public StringProperty getProduct() {
        return product;
    }

    public void setProduct(StringProperty product) {
        this.product = product;
    }

    public LongProperty getHasprice() {
        return hasprice;
    }

    public void setHasprice(LongProperty hasprice) {
        this.hasprice = hasprice;
    }

    public LongProperty getCurrencyid() {
        return currencyid;
    }

    public void setCurrencyid(LongProperty currencyid) {
        this.currencyid = currencyid;
    }

    public LongProperty getShopid() {
        return shopid;
    }

    public void setShopid(LongProperty shopid) {
        this.shopid = shopid;
    }

    public LongProperty getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(LongProperty categoryid) {
        this.categoryid = categoryid;
    }

    public StringProperty getDetail() {
        return detail;
    }

    public void setDetail(StringProperty detail) {
        this.detail = detail;
    }

    public LongProperty getPrice() {return price;}

    public void setPrice(LongProperty price) {this.price = price;}

}
