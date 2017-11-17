package seoulnightmarket.seoulnightmarket.data;

import android.graphics.Bitmap;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class MenuListViewItem
{ // 리스트뷰에 들어갈 데이터 클래스
    private String menuImage;
    private String menuName;
    private String menuPrice;

    public MenuListViewItem() {

    }

    public String getMenuImage() {
        return this.menuImage;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public String getMenuPrice() {
        return this.menuPrice;
    }

    public void setMenuImage(String image) {
        menuImage = image;
    }

    public void setMenuName(String name) {
        menuName = name;
    }

    public void setMenuPrice(String price) {
        menuPrice = price;
    }
}