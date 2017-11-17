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
    private String type;
    private String region;
    private String uri;
    public MarketAdapter adapter;
    public ExpandableHeightGridView gridView;

    public FragmentMarket() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_market, container, false);

        final Button btnFoodTruck = view.findViewById(R.id.foodTruck);
        final Button btnHandMade = view.findViewById(R.id.handMade);

        gridView = view.findViewById(R.id.gridView);
        gridView.setExpanded(true);
        region = Singleton.getInstance().getRegion();

        type = "foodTruck";
        Singleton.getInstance().setType(type);

        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/foodstore")
                .buildUpon()
                .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                .build().toString();

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("푸드트럭");
        httpAsyncTask.execute(uri);

        btnFoodTruck.bringToFront();

        btnFoodTruck.setTextColor(Color.parseColor("#ffffff"));
        btnHandMade.setTextColor(Color.parseColor("#000000"));
        switch (region) {
            case "여의도":
                btnFoodTruck.setBackgroundResource(R.drawable.btn_yyd_foodtruck);
                btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                break;
            case "DDP":
                btnFoodTruck.setBackgroundResource(R.drawable.btn_ddp_foodtruck);
                btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                break;
            case "반포":
                btnFoodTruck.setBackgroundResource(R.drawable.btn_bp_foodtruck);
                btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                break;
            case "청계천":
                btnFoodTruck.setBackgroundResource(R.drawable.btn_cgc_foodtruck);
                btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                break;
            case "청계광장":
                btnFoodTruck.setBackgroundResource(R.drawable.btn_cggj_foodtruck);
                btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
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
                    btnFoodTruck.bringToFront();
                    btnFoodTruck.setTextColor(Color.parseColor("#ffffff"));
                    btnHandMade.setTextColor(Color.parseColor("#000000"));

                    switch (region) {
                        case "여의도":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_yyd_foodtruck);
                            btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            break;
                        case "DDP":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_ddp_foodtruck);
                            btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            break;
                        case "반포":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_bp_foodtruck);
                            btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            break;
                        case "청계천":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_cgc_foodtruck);
                            btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            break;
                        case "청계광장":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_cggj_foodtruck);
                            btnHandMade.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            break;
                        default:
                            break;
                    }

                    uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/foodstore")
                            .buildUpon()
                            .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                            .build().toString();
                    Log.e("URL", uri);
                    HttpAsyncTask httpAsyncTask = new HttpAsyncTask("푸드트럭");
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
                    btnHandMade.bringToFront();
                    btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                    btnHandMade.setTextColor(Color.parseColor("#ffffff"));

                    switch (region) {
                        case "여의도":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            btnHandMade.setBackgroundResource(R.drawable.btn_yyd_foodtruck);
                            break;
                        case "DDP":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            btnHandMade.setBackgroundResource(R.drawable.btn_ddp_foodtruck);
                            break;
                        case "반포":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            btnHandMade.setBackgroundResource(R.drawable.btn_bp_foodtruck);
                            break;
                        case "청계천":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            btnHandMade.setBackgroundResource(R.drawable.btn_cgc_foodtruck);
                            break;
                        case "청계광장":
                            btnFoodTruck.setBackgroundResource(R.drawable.btn_yyd_handmade);
                            btnHandMade.setBackgroundResource(R.drawable.btn_cggj_foodtruck);
                            break;
                        default:
                            break;
                    }

                    uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/handmadestore")
                            .buildUpon()
                            .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(region))
                            .build().toString();
                    Log.e("URL", uri);
                    HttpAsyncTask httpAsyncTask = new HttpAsyncTask("핸드메이드상점");
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

            adapter = new MarketAdapter(getActivity(), Singleton.getInstance().getStoreImageList(), Singleton.getInstance().getStoreNameList());
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            gridView.invalidateViews();
        }
    }
}