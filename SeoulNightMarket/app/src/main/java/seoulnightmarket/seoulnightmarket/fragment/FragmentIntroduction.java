package seoulnightmarket.seoulnightmarket.fragment;

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

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.IntroductionAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentIntroduction extends Fragment {
    String region;
    String uri;
    View view;
    IntroductionAdapter adapter;
    ListView listView;
    TextView textView0;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    ImageView imageView;
    ImageView imageView1;
    ArrayList<String> adapterHead = new ArrayList<String>();
    ArrayList<String> adapterString = new ArrayList<String>();

    public FragmentIntroduction() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Fragment가 생성될때 호출
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        view = inflater.inflate(R.layout.activity_fragment_introduction, null);
        region = Singleton.getInstance().getRegion();

        listView = view.findViewById(R.id.introductionListView);
        textView0 = view.findViewById(R.id.introductionText0);
        textView1 = view.findViewById(R.id.introductionText1);
        textView2 = view.findViewById(R.id.introductionText2);
        textView3 = view.findViewById(R.id.introductionText3);
        textView4 = view.findViewById(R.id.introductionText4);
        imageView = view.findViewById(R.id.introductionHeaderImage);
        imageView1 = view.findViewById(R.id.introductionFooterImage);

        adapter = new IntroductionAdapter();
        adapterHead.clear();
        adapterString.clear();

        if (region.equals("여의도")) {
            textView0.setText(R.string.textView0y);
            textView1.setText(R.string.textView1y);
            textView2.setText(R.string.textView2y);
            textView3.setText(R.string.textView3y);
            textView4.setText(R.string.textView4y);
            imageView.setImageResource(R.drawable.yydintro);
            imageView1.setImageResource(R.drawable.yydintro1);

            adapterHead.add(getString(R.string.listView0y));
            adapterHead.add(getString(R.string.listView2y));
            adapterHead.add(getString(R.string.listView4y));
            adapterHead.add(getString(R.string.listView6y));
            adapterHead.add(getString(R.string.listView8y));

            adapterString.add(getString(R.string.listView1y));
            adapterString.add(getString(R.string.listView3y));
            adapterString.add(getString(R.string.listView5y));
            adapterString.add(getString(R.string.listView7y));
            adapterString.add(getString(R.string.listView9y));
        } else if (region.equals("DDP")) {
            textView0.setText(R.string.textView0d);
            textView1.setText(R.string.textView1d);
            textView2.setText(R.string.textView2d);
            textView3.setText(R.string.textView3d);
            textView4.setText(R.string.textView4d);
            imageView.setImageResource(R.drawable.ddpintro);
            imageView1.setImageResource(R.drawable.ddpintro1);

            adapterHead.add(getString(R.string.listView0d));
            adapterHead.add(getString(R.string.listView2d));
            adapterHead.add(getString(R.string.listView4d));
            adapterHead.add(getString(R.string.listView6d));
            adapterHead.add(getString(R.string.listView8d));
            adapterHead.add(getString(R.string.listView10d));

            adapterString.add(getString(R.string.listView1d));
            adapterString.add(getString(R.string.listView3d));
            adapterString.add(getString(R.string.listView5d));
            adapterString.add(getString(R.string.listView7d));
            adapterString.add(getString(R.string.listView9d));
            adapterString.add(getString(R.string.listView11d));
        } else if (region.equals("반포")) {
            textView0.setText(R.string.textView0b);
            textView1.setText(R.string.textView1b);
            textView2.setText(R.string.textView2b);
            textView3.setText(R.string.textView3b);
            textView4.setText(R.string.textView4b);
            imageView.setImageResource(R.drawable.bpintro);
            imageView1.setImageResource(R.drawable.bpintro1);

            adapterHead.add(getString(R.string.listView0b));
            adapterHead.add(getString(R.string.listView2b));
            adapterHead.add(getString(R.string.listView4b));
            adapterHead.add(getString(R.string.listView6b));

            adapterString.add(getString(R.string.listView1b));
            adapterString.add(getString(R.string.listView3b));
            adapterString.add(getString(R.string.listView5b));
            adapterString.add(getString(R.string.listView7b));
        } else if (region.equals("청계천")) {
            textView0.setText(R.string.textView0c);
            textView1.setText(R.string.textView1c);
            textView2.setText(R.string.textView2c);
            textView3.setText(R.string.textView3c);
            textView4.setText(R.string.textView4c);
            imageView.setImageResource(R.drawable.cgcintro);
            imageView1.setImageResource(R.drawable.cgcintro1);

            adapterHead.add(getString(R.string.listView0c));
            adapterHead.add(getString(R.string.listView2c));
            adapterHead.add(getString(R.string.listView4c));
            adapterHead.add(getString(R.string.listView6c));
            adapterHead.add(getString(R.string.listView8c));

            adapterString.add(getString(R.string.listView1c));
            adapterString.add(getString(R.string.listView3c));
            adapterString.add(getString(R.string.listView5c));
            adapterString.add(getString(R.string.listView7c));
            adapterString.add(getString(R.string.listView9c));
        } else if (region.equals("청계광장")) {
            textView0.setText(R.string.textView0j);
            textView1.setText(R.string.textView1j);
            textView2.setText(R.string.textView2j);
            textView3.setText(R.string.textView3j);
            textView4.setText(R.string.textView4j);
            imageView.setImageResource(R.drawable.cggjintro);
            imageView1.setImageResource(R.drawable.cggjintro1);

            adapterHead.add(getString(R.string.listView0j));
            adapterHead.add(getString(R.string.listView2j));
            adapterHead.add(getString(R.string.listView4j));
            adapterHead.add(getString(R.string.listView6j));
            adapterHead.add(getString(R.string.listView8j));

            adapterString.add(getString(R.string.listView1j));
            adapterString.add(getString(R.string.listView3j));
            adapterString.add(getString(R.string.listView5j));
            adapterString.add(getString(R.string.listView7j));
            adapterString.add(getString(R.string.listView9j));
        }

        for (int i = 0; i < adapterHead.size(); i++) {
            adapter.addItem(adapterHead.get(i), adapterString.get(i));
        }

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.invalidateViews();

        setListViewHeightBasedOnItems(listView);

        // onCreate 후에 화면을 구성할때 호출
//        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/intro")
//                .buildUpon()
//                .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
//                .build().toString();
//
//        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("소개");
//        httpAsyncTask.execute(uri);

        return view;
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        int count;
        int beforeIndex;
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

            Singleton.getInstance().getStoreImageView().setImageBitmap(HttpTask.getInstance().translateBitmap(Singleton.getInstance().getOutlineSource()));

            textView0.setText(Singleton.getInstance().getOutlineTitle());
            textView1.setText(Singleton.getInstance().getOutlineSubtitle().replace("/", "\n"));
            textView2.setText(Singleton.getInstance().getOutlineDescribe().replace("/", "\n"));
            textView3.setText(Singleton.getInstance().getFormTitle());
            textView4.setText(Singleton.getInstance().getFormSubtitle().replace("/", "\n"));
            imageView.setImageBitmap(HttpTask.getInstance().translateBitmap(Singleton.getInstance().getOutlineSource()));
            imageView1.setImageBitmap(HttpTask.getInstance().translateBitmap(Singleton.getInstance().getFormSource()));

            adapter = new IntroductionAdapter();

            adapterHead.clear();
            adapterString.clear();
            count = 1;
            beforeIndex = 0;

            for (int i = 0; i < Singleton.getInstance().getFormDescribe().length(); i++) {
                if (Singleton.getInstance().getFormDescribe().substring(i, i + 1).equals("/")) {
                    if (count % 2 == 1) {
                        adapterHead.add(Singleton.getInstance().getFormDescribe().substring(beforeIndex, i));
                    } else {
                        adapterString.add(Singleton.getInstance().getFormDescribe().substring(beforeIndex, i));
                    }

                    beforeIndex = i + 1;
                    count++;
                }
            }

            for (int i = 0; i < adapterHead.size(); i++) {
                adapter.addItem(adapterHead.get(i), adapterString.get(i).replace("=", "\n"));
            }

            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.invalidateViews();

            setListViewHeightBasedOnItems(listView);
        }
    }

//    public static void setListViewHeightBasedOnChildren(ListView listView) { // 리스트뷰 자동 높이 설정
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            // pre-condition
//            return;
//        }
//
//        int totalHeight = 0;
//
//        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            //listItem.measure(0, 0);
//            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//
//        params.height = totalHeight;
//        listView.setLayoutParams(params);
//
//        listView.requestLayout();
//    }

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