package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.data.TicketListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-19.
 */

public class TicketAdapter extends BaseAdapter {
    private ArrayList<TicketListViewItem> listViewItems = new ArrayList<>();

    public TicketAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ticketlistview_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.orderImageView);
        TextView textView0 = convertView.findViewById(R.id.orderMenu);
        TextView textView1 = convertView.findViewById(R.id.currentNumber);
        TextView textView2 = convertView.findViewById(R.id.myOrderNumber);
        TextView textView3 = convertView.findViewById(R.id.waitingNumber);

        TicketListViewItem listViewItem = listViewItems.get(position);

        imageView.setImageDrawable(listViewItem.getOrderImage());
        textView0.setText(listViewItem.getOrderMenu());
        textView1.setText(Integer.toString(listViewItem.getCurrentNumber()));
        textView2.setText(Integer.toString(listViewItem.getMyOrderNumber()));
        textView3.setText(Integer.toString(listViewItem.getWaitingNumber()));

        return convertView;
    }

    public void addItem(Drawable image, String menu, int currentNumber, int myOrderNumber, int waitingNumber) {
        TicketListViewItem item = new TicketListViewItem();

        item.setOrderImage(image);
        item.setOrderMenu(menu);
        item.setCurrentNumber(currentNumber);
        item.setMyOrderNumber(myOrderNumber);
        item.setWaitingNumber(waitingNumber);

        listViewItems.add(item);
    }
}