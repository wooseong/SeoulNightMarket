package seoulnightmarket.seoulnightmarket.fragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.ProductAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentProduct extends Fragment {
    ProductAdapter adapter;
    ListView listView;
    String uri;

    public FragmentProduct() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_product, null);
        listView = view.findViewById(R.id.listViewProduct);

        ImageView imageView = Singleton.getInstance().getStoreImageView();
        ImageView imageViewProduct = view.findViewById(R.id.imageViewProduct);
        imageView.setImageBitmap(HttpTask.getInstance().translateBitmap(Singleton.getInstance().getNowStoreImage()));
        imageViewProduct.setImageBitmap(HttpTask.getInstance().translateBitmap(Singleton.getInstance().getNowStoreDetailImage()));

        TextView storeTextView = view.findViewById(R.id.handmade_store_textview);
        TextView categoryTextView = view.findViewById(R.id.handmade_category_textview);

        storeTextView.setText(Singleton.getInstance().getNowStore());
        categoryTextView.setText(Singleton.getInstance().getNowCategory());

        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/handmade")
                .buildUpon()
                .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getRegion()))
                .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowStore()))
                .build().toString();

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("핸드메이드");
        httpAsyncTask.execute(uri);

        return view;
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        String type;

        HttpAsyncTask(String type) {
            this.type = type;
        }

        @Override
        protected String doInBackground(String... urls) {
            //urls[0] 은 URL 주소
            return HttpTask.getInstance().GET(urls[0], type);
        }
        // onPostExecute displays the results of the AsyncTask.

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            adapter = new ProductAdapter();

            for (int i = 0; i < Singleton.getInstance().getProductNameList().size(); i++) {
                adapter.addItem(Singleton.getInstance().getProductNameList().get(i), Singleton.getInstance().getProductPriceList().get(i)); // 서버에서 받은 만큼 데이터를 어댑터에 연결한다
            }

            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.invalidateViews();

            setListViewHeightBasedOnItems(listView);
        }
    }

    public void setListViewHeightBasedOnItems(ListView listView) { // 리스트뷰 높이 계산
        // Get list adpter of listview;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;

        int numberOfItems = listAdapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }

        // Get total height of all item dividers.
        int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}