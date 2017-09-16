package seoulnightmarket.seoulnightmarket.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class MenuListViewItem { // 리스트뷰에 들어갈 데이터 클래스
    private Drawable menuImage;
    private String menuName;
    private int menuPrice;

    public Drawable getMenuImage() {
        return this.menuImage;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public int getMenuPrice() {
        return this.menuPrice;
    }

    public void setMenuImage(Drawable image) {
        menuImage = image;
    }

    public void setMenuName(String name) {
        menuName = name;
    }

    public void setMenuPrice(int price) {
        menuPrice = price;
    }
}