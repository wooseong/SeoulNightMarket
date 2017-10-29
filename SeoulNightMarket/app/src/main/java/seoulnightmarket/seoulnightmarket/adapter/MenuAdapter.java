package seoulnightmarket.seoulnightmarket.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.data.MenuListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private ArrayList<MenuListViewItem> listViewItemList = new ArrayList<>(); // Adapter에 추가된 데이터를 저장하기 위한 ArrayList

    public MenuAdapter() { // 생성자

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView0;
        public TextView textView1;
        public MenuListViewItem listViewItem;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.menuImage);
            textView0 = (TextView) view.findViewById(R.id.menuName);
            textView1 = (TextView) view.findViewById(R.id.menuPrice);
        }
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menulistview_item, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.listViewItem = listViewItemList.get(position);
        holder.imageView.setImageBitmap(holder.listViewItem.getMenuImage()); // 아이템 내 각 위젯에 데이터 반영
        holder.textView0.setText(holder.listViewItem.getMenuName());
        holder.textView1.setText(String.valueOf(holder.listViewItem.getMenuPrice()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listViewItemList.size();
    }

    @Override
    public long getItemId(int position) { // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
        return position;
    }

    public void addItem(Bitmap image, String name, String price) { // 아이템 데이터 추가를 위한 함수
        MenuListViewItem item = new MenuListViewItem();

        item.setMenuImage(image);
        item.setMenuName(name);
        item.setMenuPrice(price);

        listViewItemList.add(item);
    }
}