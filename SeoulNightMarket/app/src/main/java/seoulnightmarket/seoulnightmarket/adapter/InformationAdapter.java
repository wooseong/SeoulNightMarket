package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.data.InformationListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class InformationAdapter extends BaseAdapter {
    private ArrayList<InformationListViewItem> listViewItems = new ArrayList<>();

    public InformationAdapter() {

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
            convertView = inflater.inflate(R.layout.informationlistview_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textTurn); // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView textView1 = convertView.findViewById(R.id.textDate);
        TextView textView2 = convertView.findViewById(R.id.textRegion);

        InformationListViewItem listViewItem = listViewItems.get(position); // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득

        textView.setText(listViewItem.getTurn()); // 아이템 내 각 위젯에 데이터 반영
        textView1.setText(listViewItem.getDate());
        textView2.setText(listViewItem.getRegion());

        return convertView;
    }

    public void addItem(String turn, String date, String region) { // 아이템 데이터 추가를 위한 함수
        InformationListViewItem item = new InformationListViewItem();

        item.setTurn(turn);
        item.setDate(date);
        item.setRegion(region);

        listViewItems.add(item);
    }
}