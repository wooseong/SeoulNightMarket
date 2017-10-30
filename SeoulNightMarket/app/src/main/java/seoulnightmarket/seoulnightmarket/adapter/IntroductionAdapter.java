package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.data.IntroductionListViewItem;
import seoulnightmarket.seoulnightmarket.data.MenuListViewItem;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class IntroductionAdapter extends RecyclerView.Adapter<IntroductionAdapter.ViewHolder> {
    private ArrayList<IntroductionListViewItem> listViewItems = new ArrayList<>();

    public IntroductionAdapter() {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;
        public TextView textView1;
        public TextView textView2;
        public ImageView imageView;
        public IntroductionListViewItem listViewItem;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView)view.findViewById(R.id.introductionlist0);
            textView1 =(TextView)view.findViewById(R.id.introductionlist1);
            textView2 =(TextView)view.findViewById(R.id.introductionlist2);
            imageView = (ImageView)view.findViewById(R.id.indexLineColor3);
        }
    }

    @Override
    public IntroductionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.introductionlistview_item, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(IntroductionAdapter.ViewHolder holder, int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.listViewItem = listViewItems.get(position);

        holder.textView.setText(holder.listViewItem.getIntroductionHeader());
        holder.textView1.setText(holder.listViewItem.getIntroductionTitle());
        holder.textView2.setText(holder.listViewItem.getIntroductionContent());

        switch(Singleton.getInstance().getRegion())
        {
            case "여의도":
                holder.imageView.setBackgroundColor(Color.parseColor("#FFA726"));
                break;
            case "DDP":
                holder.imageView.setBackgroundColor(Color.parseColor("#7E57C2"));
                break;
            case "반포":
                holder.imageView.setBackgroundColor(Color.parseColor("#FDD835"));
                break;
            case "청계천":
                holder.imageView.setBackgroundColor(Color.parseColor("#CDDC39"));
                break;
            case "청계광장":
                holder.imageView.setBackgroundColor(Color.parseColor("#00838F"));
                break;
        }
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

    public void addItem(String header, String title, String content)
    { // 아이템 데이터 추가를 위한 함수
        IntroductionListViewItem item = new IntroductionListViewItem();

        item.setIntroductionHeader(header);
        item.setIntroductionTitle(title);
        item.setIntroductionContent(content);

        listViewItems.add(item);
    }
}