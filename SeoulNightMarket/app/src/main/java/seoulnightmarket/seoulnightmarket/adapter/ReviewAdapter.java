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
import seoulnightmarket.seoulnightmarket.data.ReviewListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class ReviewAdapter extends BaseAdapter {
    private ArrayList<ReviewListViewItem> listViewItems = new ArrayList<>();

    public ReviewAdapter() {

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

        if (convertView == null) { // listview_item Layout을 inflate하여 convertView 참조 획득
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reviewlistview_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageprofile);
        TextView textView = convertView.findViewById(R.id.textnickname); // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView imageView1 = convertView.findViewById(R.id.imagestar);
        TextView textView1 = convertView.findViewById(R.id.textdate);
        TextView textView2 = convertView.findViewById(R.id.textreview);

        ReviewListViewItem listViewItem = listViewItems.get(position); // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득

        imageView.setImageDrawable(listViewItem.getUserImage());
        textView.setText(listViewItem.getUserName()); // 아이템 내 각 위젯에 데이터 반영
        imageView1.setImageDrawable(listViewItem.getStarImage());
        textView1.setText(listViewItem.getReviewDate());
        textView2.setText(listViewItem.getReview());

        return convertView;
    }

    public void addItem(Drawable user, String name, Drawable star, String date, String review) { // 아이템 데이터 추가를 위한 함수
        ReviewListViewItem item = new ReviewListViewItem();

        item.setUserImage(user);
        item.setUserName(name);
        item.setStarImage(star);
        item.setReviewDate(date);
        item.setReview(review);

        listViewItems.add(item);
    }
}