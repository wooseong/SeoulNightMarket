package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.data.ProductListViewItem;

/**
 * Created by Yookmoonsu on 2017-09-17.
 */

public class ProductAdapter extends BaseAdapter {
    private ArrayList<ProductListViewItem> listViewItems = new ArrayList<>();

    public ProductAdapter() {

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
            convertView = inflater.inflate(R.layout.productlistview_item, parent, false);
        }

        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);

        ProductListViewItem listViewItem = listViewItems.get(position);

        productName.setText(listViewItem.getProductName());
        productPrice.setText(listViewItem.getProductPrice());

        return convertView;
    }

    public void addItem(String name, String price) {
        ProductListViewItem item = new ProductListViewItem();

        item.setProductName(name);
        item.setProductPrice(price);

        listViewItems.add(item);
    }
}