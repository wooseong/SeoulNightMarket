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
import android.view.ViewGroup;
import android.widget.Button;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MarketAdapter;
import seoulnightmarket.seoulnightmarket.etc.ExpandableHeightGridView;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;


public class FragmentMarket extends Fragment {
    static String type = "foodTruck";
    private String region = "";
    private String uri;
    public MarketAdapter adapter;
    public ExpandableHeightGridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_market, container, false);
        gridView = view.findViewById(R.id.gridView);
        gridView.setExpanded(true);

        region = Singleton.getInstance().getRegion();
        region = HttpTask.getInstance().regionTranslate(region);

        Singleton.getInstance().setType(type);
        Singleton.getInstance().setServerRequest(false);

        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/foodstore")
                .buildUpon()
                .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                .build().toString();

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("야시장");
        httpAsyncTask.execute(uri);

        final Button btnFoodTruck = view.findViewById(R.id.foodTruck);
        final Button btnHandMade = view.findViewById(R.id.handMade);

        switch (region) {
            case "여의도":
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
            case "반포":
                btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                btnFoodTruck.setBackgroundResource(R.color.md_yellow_500);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "청계천":
                btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                btnFoodTruck.setBackgroundResource(R.color.md_light_green_500);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "청계광장":
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

                if (type == "foodTruck") {
                    switch (region) {
                        case "여의도":
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
                        case "반포":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_yellow_500);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "청계천":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_light_green_500);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "청계광장":
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
                        case "여의도":
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
                        case "반포":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_yellow_500);
                            break;
                        case "청계천":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_light_green_500);
                            break;
                        case "청계광장":
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

            adapter = new MarketAdapter(getActivity().getApplicationContext(), Singleton.getInstance().getStoreImageList(), Singleton.getInstance().getStoreNameList());
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            gridView.invalidateViews();
        }
    }
}