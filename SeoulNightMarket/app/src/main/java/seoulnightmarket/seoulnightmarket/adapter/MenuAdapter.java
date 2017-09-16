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
import seoulnightmarket.seoulnightmarket.data.MenuListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class MenuAdapter extends BaseAdapter {
    private ArrayList<MenuListViewItem> listViewItemList = new ArrayList<MenuListViewItem>(); // Adapter에 추가된 데이터를 저장하기 위한 ArrayList

    public MenuAdapter() { // 생성자

    }

    @Override
    public int getCount() { // Adapter에 사용되는 데이터의 갯수
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
        final Context context = parent.getContext();

        if (convertView == null) { // listview_item Layout을 inflate하여 convertView 참조 획득
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menulistview_item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.menuImage); // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView textView = (TextView) convertView.findViewById(R.id.menuName);
        TextView textView1 = (TextView) convertView.findViewById(R.id.menuPrice);

        MenuListViewItem listViewItem = listViewItemList.get(position); // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득

        imageView.setImageDrawable(listViewItem.getMenuImage()); // 아이템 내 각 위젯에 데이터 반영
        textView.setText(listViewItem.getMenuName());
        textView1.setText(String.valueOf(listViewItem.getMenuPrice()));

        return convertView;
    }

    @Override
    public long getItemId(int position) { // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
        return position ;
    }

    @Override
    public Object getItem(int position) { // 지정한 위치(position)에 있는 데이터 리턴
        return listViewItemList.get(position) ;
    }

    public void addItem(Drawable image, String name, int price) { // 아이템 데이터 추가를 위한 함수
        MenuListViewItem item = new MenuListViewItem();

        item.setMenuImage(image);
        item.setMenuName(name);
        item.setMenuPrice(price);

        listViewItemList.add(item);
    }
}