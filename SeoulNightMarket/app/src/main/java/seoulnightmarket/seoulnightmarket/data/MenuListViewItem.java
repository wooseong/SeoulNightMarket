package seoulnightmarket.seoulnightmarket.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Yookmoonsu on 2017-09-15.
 */

public class MenuListViewItem { // ListView 아이템에 출력될 데이터 클래스
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

    public void setMenuImagee(Drawable image) {
        menuImage = image;
    }

    public void setMenuName(String name) {
        menuName = name;
    }

    public void setMenuPrice(int price) {
        menuPrice = price;
    }
}