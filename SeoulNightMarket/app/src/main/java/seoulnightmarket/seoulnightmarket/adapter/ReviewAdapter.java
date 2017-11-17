package seoulnightmarket.seoulnightmarket.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.data.ReviewListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private ArrayList<ReviewListViewItem> listViewItems = new ArrayList<>();

    public ReviewAdapter() {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ImageView imageView1;
        public TextView textView1;
        public TextView textView2;
        public ReviewListViewItem listViewItem;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageprofile);
            textView = view.findViewById(R.id.textnickname);
            imageView1 = view.findViewById(R.id.imagestar);
            textView1 = view.findViewById(R.id.textdate);
            textView2 = view.findViewById(R.id.textreview);
        }
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewlistview_item, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.listViewItem = listViewItems.get(position); // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        holder.imageView.setImageDrawable(holder.listViewItem.getUserImage());
        holder.textView.setText(holder.listViewItem.getUserName()); // 아이템 내 각 위젯에 데이터 반영
        holder.imageView1.setImageDrawable(holder.listViewItem.getStarImage());
        holder.textView1.setText(holder.listViewItem.getReviewDate());
        holder.textView2.setText(holder.listViewItem.getReview());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listViewItems.size();
    }

    @Override
    public long getItemId(int position) { // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
        return position;
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