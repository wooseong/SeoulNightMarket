package seoulnightmarket.seoulnightmarket.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentDirections extends Fragment {
    String region;
    String uri;
    View view;
    TextView textDirection0;
    TextView textDirection1;
    TextView textAddress;
    TextView textBus;
    TextView textSubway;
    TextView textCar;
    ImageView imageView;
    ImageView imageLineView;
    ImageView imageLineView1;
    ImageView imageLineView2;
    ImageView imageLineView3;
    ImageView imageLineView4;

    public FragmentDirections() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_directions, null);
        region = Singleton.getInstance().getRegion();

        textDirection0 = view.findViewById(R.id.textDirection0);
        textDirection1 = view.findViewById(R.id.textDirection1);
        textAddress = view.findViewById(R.id.textAddress);
        textBus = view.findViewById(R.id.textBus);
        textSubway = view.findViewById(R.id.textSubway);
        textCar = view.findViewById(R.id.textCar);
        imageView = view.findViewById(R.id.imageViewDirection);
        imageLineView = view.findViewById(R.id.directionLineColor0);
        imageLineView1 = view.findViewById(R.id.directionLineColor1);
        imageLineView2 = view.findViewById(R.id.directionLineColor2);
        imageLineView3 = view.findViewById(R.id.directionLineColor3);
        imageLineView4 = view.findViewById(R.id.directionLineColor4);

        if (region.equals("여의도")) {
            textDirection0.setText(R.string.textDirection0y);
            textDirection1.setText(R.string.textDirection1y);
            textAddress.setText(R.string.textAddressy);
            textBus.setText(R.string.textBusy);
            textSubway.setText(R.string.textSubwayy);
            textCar.setText(R.string.textCary);
            imageView.setImageResource(R.drawable.yyddir);
            imageLineView.setBackgroundColor(Color.parseColor("#FFA726"));
            imageLineView1.setBackgroundColor(Color.parseColor("#FFA726"));
            imageLineView2.setBackgroundColor(Color.parseColor("#FFA726"));
            imageLineView3.setBackgroundColor(Color.parseColor("#FFA726"));
            imageLineView4.setBackgroundColor(Color.parseColor("#FFA726"));
        } else if (region.equals("DDP")) {
            textDirection0.setText(R.string.textDirection0d);
            textDirection1.setText(R.string.textDirection1d);
            textAddress.setText(R.string.textAddressd);
            textBus.setText(R.string.textBusd);
            textSubway.setText(R.string.textSubwayd);
            textCar.setText(R.string.textCard);
            imageLineView.setBackgroundColor(Color.parseColor("#7E57C2"));
            imageLineView1.setBackgroundColor(Color.parseColor("#7E57C2"));
            imageLineView2.setBackgroundColor(Color.parseColor("#7E57C2"));
            imageLineView3.setBackgroundColor(Color.parseColor("#7E57C2"));
            imageLineView4.setBackgroundColor(Color.parseColor("#7E57C2"));
            imageView.setImageResource(R.drawable.ddpdir);
        } else if (region.equals("반포")) {
            textDirection0.setText(R.string.textDirection0b);
            textDirection1.setText(R.string.textDirection1b);
            textAddress.setText(R.string.textAddressb);
            textBus.setText(R.string.textBusb);
            textSubway.setText(R.string.textSubwayb);
            textCar.setText(R.string.textCarb);
            imageLineView.setBackgroundColor(Color.parseColor("#FDD835"));
            imageLineView1.setBackgroundColor(Color.parseColor("#FDD835"));
            imageLineView2.setBackgroundColor(Color.parseColor("#FDD835"));
            imageLineView3.setBackgroundColor(Color.parseColor("#FDD835"));
            imageLineView4.setBackgroundColor(Color.parseColor("#FDD835"));
            imageView.setImageResource(R.drawable.bpdir);
        } else if (region.equals("청계천")) {
            textDirection0.setText(R.string.textDirection0c);
            textDirection1.setText(R.string.textDirection1c);
            textAddress.setText(R.string.textAddressc);
            textBus.setText(R.string.textBusc);
            textSubway.setText(R.string.textSubwayc);
            textCar.setText(R.string.textCarc);
            imageView.setImageResource(R.drawable.cgcdir);
            imageLineView.setBackgroundColor(Color.parseColor("#CDDC39"));
            imageLineView1.setBackgroundColor(Color.parseColor("#CDDC39"));
            imageLineView2.setBackgroundColor(Color.parseColor("#CDDC39"));
            imageLineView3.setBackgroundColor(Color.parseColor("#CDDC39"));
            imageLineView4.setBackgroundColor(Color.parseColor("#CDDC39"));
        } else if (region.equals("청계광장")) {
            textDirection0.setText(R.string.textDirection0j);
            textDirection1.setText(R.string.textDirection1j);
            textAddress.setText(R.string.textAddressj);
            textBus.setText(R.string.textBusj);
            textSubway.setText(R.string.textSubwayj);
            textCar.setText(R.string.textCarj);
            imageView.setImageResource(R.drawable.cggjdir);
            imageLineView.setBackgroundColor(Color.parseColor("#00838F"));
            imageLineView1.setBackgroundColor(Color.parseColor("#00838F"));
            imageLineView2.setBackgroundColor(Color.parseColor("#00838F"));
            imageLineView3.setBackgroundColor(Color.parseColor("#00838F"));
            imageLineView4.setBackgroundColor(Color.parseColor("#00838F"));
        }

        // onCreate 후에 화면을 구성할때 호출
//        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/intro")
//                .buildUpon()
//                .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
//                .build().toString();
//
//        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("오시는길");
//        httpAsyncTask.execute(uri);

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

            textDirection0.setText("@" + region + " 오시는 길");
            textDirection1.setText(Singleton.getInstance().getMarketPlace());
            textAddress.setText(Singleton.getInstance().getMarketAddress());
            textBus.setText(Singleton.getInstance().getBusWay().replace("/", "\n"));
            textSubway.setText(Singleton.getInstance().getSubwayWay().replace("/", "\n"));
            textCar.setText(Singleton.getInstance().getCarWay().replace("/", "\n"));
            imageView.setImageBitmap(HttpTask.getInstance().translateBitmap(Singleton.getInstance().getLoadmapSource()));
        }
    }
}