package seoulnightmarket.seoulnightmarket.fragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MarketAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentDirections extends Fragment
{
    String region;
    String uri;
    View view;
    TextView textDirection0 ;
    TextView textDirection1 ;
    TextView textAddress;
    TextView textBus;
    TextView textSubway;
    TextView textCar;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_fragment_directions, null);

        textDirection0 = view.findViewById(R.id.textDirection0);
        textDirection1 = view.findViewById(R.id.textDirection1);
        textAddress = view.findViewById(R.id.textAddress);
        textBus = view.findViewById(R.id.textBus);
        textSubway = view.findViewById(R.id.textSubway);
        textCar = view.findViewById(R.id.textCar);
        imageView = view.findViewById(R.id.imageViewDirection);

        // onCreate 후에 화면을 구성할때 호출
        region = HttpTask.getInstance().regionTranslate(Singleton.getInstance().getRegion());

        Singleton.getInstance().setServerRequest(false);
        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/intro")
                .buildUpon()
                .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                .build().toString();

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("오시는길");
        httpAsyncTask.execute(uri);

        return view;
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String>
    {
        String type;

        HttpAsyncTask(String type) { this.type = type; }

        @Override
        protected String doInBackground(String ...urls)
        {
            //urls[0] 은 URL 주소
            return HttpTask.getInstance().GET(urls[0], type);
        }
        // onPostExecute displays the results of the AsyncTask.

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            textDirection0.setText("@"+region+" 오시는 길");
            textDirection1.setText(Singleton.getInstance().getMarketPlace());
            textAddress.setText(Singleton.getInstance().getMarketAddress());
            textBus.setText(Singleton.getInstance().getBusWay().replace("/", "\n"));
            textSubway.setText(Singleton.getInstance().getSubwayWay().replace("/", "\n"));
            textCar.setText(Singleton.getInstance().getCarWay().replace("/", "\n"));
            imageView.setImageBitmap(HttpTask.getInstance().translateBitmap(Singleton.getInstance().getLoadmapSource()));
        }
    }
}