package seoulnightmarket.seoulnightmarket.data;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class ProductListViewItem {
    private String productName;
    private String productPrice;

    public ProductListViewItem() {

    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductName(String name) {
        productName = name;
    }

    public void setProductPrice(String price) {
        productPrice = price;
    }
}