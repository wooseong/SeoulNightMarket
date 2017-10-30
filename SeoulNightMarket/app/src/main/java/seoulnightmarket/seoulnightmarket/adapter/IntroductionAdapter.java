package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.data.IntroductionListViewItem;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class IntroductionAdapter extends BaseAdapter {
    private ArrayList<IntroductionListViewItem> listViewItems = new ArrayList<>();

    public IntroductionAdapter() {

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
            convertView = inflater.inflate(R.layout.introductionlistview_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.introductionlist0);
        TextView textView1 = convertView.findViewById(R.id.introductionlist1);
        TextView textView2 = convertView.findViewById(R.id.introductionlist2);
        ImageView imageView = convertView.findViewById(R.id.indexLineColor3);

        IntroductionListViewItem listViewItem = listViewItems.get(position);

        textView.setText(listViewItem.getIntroductionHeader());
        textView1.setText(listViewItem.getIntroductionTitle());
        textView2.setText(listViewItem.getIntroductionContent());

        switch(Singleton.getInstance().getRegion())
        {
            case "여의도":
                imageView.setBackgroundColor(Color.parseColor("#FFA726"));
                break;
            case "DDP":
                imageView.setBackgroundColor(Color.parseColor("#7E57C2"));
                break;
            case "반포":
                imageView.setBackgroundColor(Color.parseColor("#FDD835"));
                break;
            case "청계천":
                imageView.setBackgroundColor(Color.parseColor("#CDDC39"));
                break;
            case "청계광장":
                imageView.setBackgroundColor(Color.parseColor("#00838F"));
                break;
        }

        return convertView;
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