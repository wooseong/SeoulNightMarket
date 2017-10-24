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

        listView = view.findViewById(R.id.introductionListView);

        textView0 = view.findViewById(R.id.introductionText0);
        textView1 = view.findViewById(R.id.introductionText1);
        textView2 = view.findViewById(R.id.introductionText2);
        textView3 = view.findViewById(R.id.introductionText3);
        textView4 = view.findViewById(R.id.introductionText4);
        imageView = view.findViewById(R.id.introductionHeaderImage);

        imageView1 = view.findViewById(R.id.introductionFooterImage);

        // onCreate 후에 화면을 구성할때 호출
        region = Singleton.getInstance().getRegion();

        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/intro")
                .buildUpon()
                .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                .build().toString();

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("소개");
        httpAsyncTask.execute(uri);

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

            setListViewHeightBasedOnChildren(listView);
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
        listView.setLayoutParams(params);

        listView.requestLayout();
    }
}