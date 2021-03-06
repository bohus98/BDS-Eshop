package org.but.feec.eshop.api.product;

public class ProductEditView {
    private Long id;
    private String product;
    private Long hasprice;
    private Long price;
    private Long currencyid;
    private Long shopid;
    private Long categoryid;
    private String detail;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getHasprice() {
        return hasprice;
    }

    public void setHasprice(Long hasprice) {
        this.hasprice = hasprice;
    }

    public Long getCurrencyid() {
        return currencyid;
    }

    public void setCurrencyid(Long currencyid) {
        this.currencyid = currencyid;
    }

    public Long getShopid() {
        return shopid;
    }

    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getPrice() {return price;}

    public void setPrice(Long price) {this.price = price;}

    @Override
    public String toString() {
        return "TitleCreateView{" +
                "product='" + product + '\'' +
                ", hasprice='" + hasprice + '\'' +
                ", price='" + price + '\'' +
                ", currencyid='" + currencyid + '\'' +
                ", shopid='" + shopid + '\'' +
                ", lenght='" + categoryid + '\'' +
                ", detail='" + detail +
                '}';
    }
}
