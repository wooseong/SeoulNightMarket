package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.data.TicketListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-19.
 */

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<TicketListViewItem> listViewItems = new ArrayList<>();

    public TicketAdapter(Context context) {
        this.context = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView0;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TicketListViewItem listViewItem;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.orderImageView);
            textView0 = (TextView) view.findViewById(R.id.orderMenu);
            textView1 = (TextView) view.findViewById(R.id.currentNumber);
            textView2 = (TextView) view.findViewById(R.id.myOrderNumber);
            textView3 = (TextView) view.findViewById(R.id.waitingNumber);
        }
    }

    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.listViewItem = listViewItems.get(position);

        Picasso.with(context).load(holder.listViewItem.getOrderImage()).into(holder.imageView);
        holder.textView0.setText(holder.listViewItem.getOrderMenu());
        holder.textView1.setText(Integer.toString(holder.listViewItem.getCurrentNumber()));
        holder.textView2.setText(Integer.toString(holder.listViewItem.getMyOrderNumber()));
        holder.textView3.setText(Integer.toString(holder.listViewItem.getWaitingNumber()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listViewItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String image, String menu, int currentNumber, int myOrderNumber, int waitingNumber) {
        TicketListViewItem item = new TicketListViewItem();

        item.setOrderImage(image);
        item.setOrderMenu(menu);
        item.setCurrentNumber(currentNumber);
        item.setMyOrderNumber(myOrderNumber);
        item.setWaitingNumber(waitingNumber);

        listViewItems.add(item);
    }
}