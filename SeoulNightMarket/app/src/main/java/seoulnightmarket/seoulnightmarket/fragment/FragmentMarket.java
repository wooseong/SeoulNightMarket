package seoulnightmarket.seoulnightmarket.fragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MarketAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentMarket extends Fragment
{
//    public int foodTruck[] = new ArrayList<String>();
//    public int handMade[] = {R.drawable.andro, R.drawable.babo, R.drawable.bom, R.drawable.soso};
//    public String foodTruckName[] = {"hero truck", "쉬림프컵히야", "치킨핏", "아임 스테이크", "제이프레시", "팬더그릴"};
//    public String handMadeName[] = {"안드로행성712공방", "바보공방", "봄이네", "소소공방"};

    static String type = "foodTruck";
    private String region = "";
    private String uri;
    public MarketAdapter adapter;
    public GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_fragment_market, container, false);
        region = Singleton.getInstance().getRegion();
        region = HttpTask.getInstance().regionTranslate(region);

        Singleton.getInstance().setServerRequest(false);
        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/foodstore")
                .buildUpon()
                .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                .build().toString();
        Log.e("URL", uri);

        Singleton.getInstance().setType(type);
//        adapter = new MarketAdapter(view.getContext(), Singleton.getInstance().getStoreImageList(), Singleton.getInstance().getStoreNameList());

        gridView = view.findViewById(R.id.gridView);

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("야시장");
        httpAsyncTask.execute(uri);

        final Button btnFoodTruck = view.findViewById(R.id.foodTruck);
        final Button btnHandMade = view.findViewById(R.id.handMade);

        switch (region)
        {
            case "Yeouido":
                btnFoodTruck.setTextColor(Color.parseColor("#ffffff"));
                btnFoodTruck.setBackgroundResource(R.color.md_deep_orange_400);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "DDP":
                btnFoodTruck.setTextColor(Color.parseColor("#FFEB3B"));
                btnFoodTruck.setBackgroundResource(R.color.md_deep_purple_400);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "Banpo":
                btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                btnFoodTruck.setBackgroundResource(R.color.md_yellow_500);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "Cheonggyecheon":
                btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                btnFoodTruck.setBackgroundResource(R.color.md_light_green_500);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "CheonggyePlaza":
                btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                btnFoodTruck.setBackgroundResource(R.color.md_blue_400);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            default:
                break;
        }

        btnFoodTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "foodTruck";
                Singleton.getInstance().setType(type);

                if (type == "foodTruck")
                {
                    switch (region) {
                        case "Yeouido":
                            btnFoodTruck.setTextColor(Color.parseColor("#ffffff"));
                            btnFoodTruck.setBackgroundResource(R.color.md_deep_orange_400);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "DDP":
                            btnFoodTruck.setTextColor(Color.parseColor("#FFEB3B"));
                            btnFoodTruck.setBackgroundResource(R.color.md_deep_purple_400);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "Banpo":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_yellow_500);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "Cheonggyecheon":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_light_green_500);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "CheonggyePlaza":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_blue_400);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        default:
                            break;
                    }

                    Singleton.getInstance().setServerRequest(false);
                    uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/foodstore")
                            .buildUpon()
                            .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                            .build().toString();
                    Log.e("URL", uri);
                    HttpAsyncTask httpAsyncTask = new HttpAsyncTask("야시장");
                    httpAsyncTask.execute(uri);
                }
            }
        });

        btnHandMade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "handMade";
                Singleton.getInstance().setType(type);

                if (type == "handMade") {
                    switch (region) {
                        case "Yeouido":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#ffffff"));
                            btnHandMade.setBackgroundResource(R.color.md_deep_orange_400);
                            break;
                        case "DDP":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#FFEB3B"));
                            btnHandMade.setBackgroundResource(R.color.md_deep_purple_400);
                            break;
                        case "Banpo":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_yellow_500);
                            break;
                        case "Cheonggyecheon":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_light_green_500);
                            break;
                        case "CheonggyePlaza":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_blue_400);
                            break;
                        default:
                            break;
                    }

                    Singleton.getInstance().setServerRequest(false);
                    uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/handmadestore")
                            .buildUpon()
                            .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                            .build().toString();
                    Log.e("URL", uri);
                    HttpAsyncTask httpAsyncTask = new HttpAsyncTask("야시장");
                    httpAsyncTask.execute(uri);
                }
            }
        });

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

            adapter = new MarketAdapter(getActivity().getApplicationContext(),Singleton.getInstance().getStoreImageList(), Singleton.getInstance().getStoreNameList());
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            gridView.invalidateViews();
        }
    }
}