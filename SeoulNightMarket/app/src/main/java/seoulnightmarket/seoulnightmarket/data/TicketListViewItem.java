package seoulnightmarket.seoulnightmarket.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Yookmoonsu on 2017-09-19.
 */

public class TicketListViewItem {
    private Drawable orderImage;
    private String orderMenu;
    private int currentNumber;
    private int myOrderNumber;
    private int waitingNumber;

    public TicketListViewItem() {

    }

    public Drawable getOrderImage() {
        return orderImage;
    }

    public String getOrderMenu() { return orderMenu; }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public int getMyOrderNumber() {
        return myOrderNumber;
    }

    public int getWaitingNumber() {
        return waitingNumber;
    }

    public void setOrderImage(Drawable image) {
        orderImage = image;
    }

    public void setOrderMenu(String menu) { orderMenu = menu; }

    public void setCurrentNumber(int number) {
        currentNumber = number;
    }

    public void setMyOrderNumber(int number) {
        myOrderNumber = number;
    }

    public void setWaitingNumber(int number) {
        waitingNumber = number;
    }
}