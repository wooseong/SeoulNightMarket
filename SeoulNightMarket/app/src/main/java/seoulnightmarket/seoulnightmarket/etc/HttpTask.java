package seoulnightmarket.seoulnightmarket.etc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by cjw94 on 2017-10-22.
 */

public class HttpTask {
    private static HttpTask instance = null;
    private Bitmap bitmap;

    public static synchronized HttpTask getInstance() {
        if (instance == null) {
            instance = new HttpTask();
        }
        return instance;
    }

    public static String GET(String url, String type) {
        InputStream is = null;
        String result = "";
        int minimum;

        try {
            URL urlCon = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();

            if (httpCon != null) {
                httpCon.setRequestMethod("GET");
                httpCon.setRequestProperty("Accept-Charset", "UTF-8");
                httpCon.setRequestProperty("Accept", "application/json");
                httpCon.setRequestProperty("Content-type", "application/json");
                httpCon.setConnectTimeout(10000);
            }

            is = httpCon.getInputStream();

            StringBuilder builder = new StringBuilder(); //문자열을 담기 위한 객체
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "UTF-8")); //문자열 셋 세팅
            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            result = builder.toString();

            reader.close();
            httpCon.disconnect();

            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("results");

            switch (type) {
                case "소개":
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().setOutlineTitle(posts.optJSONObject(i).getString("Outline_Title"));
                        Singleton.getInstance().setOutlineSubtitle(posts.optJSONObject(i).getString("Outline_Subtitle"));
                        Singleton.getInstance().setOutlineDescribe(posts.optJSONObject(i).getString("Outline_Describe"));
                        Singleton.getInstance().setOutlineSource(posts.optJSONObject(i).getString("Outline_Source"));
                        Singleton.getInstance().setFormTitle(posts.optJSONObject(i).getString("Form_Title"));
                        Singleton.getInstance().setFormSubtitle(posts.optJSONObject(i).getString("Form_Subtitle"));
                        Singleton.getInstance().setFormDescribe(posts.optJSONObject(i).getString("Form_Describe"));
                        Singleton.getInstance().setFormSource(posts.optJSONObject(i).getString("Form_Source"));
                    }
                    break;
                case "푸드트럭":
                    Singleton.getInstance().initFoodStoreList();
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().addFoodStoreList(posts.optJSONObject(i).getString("Store_Name"), posts.optJSONObject(i).getString("Image_Source"));
                    }
                    break;
                case "핸드메이드상점":
                    Singleton.getInstance().initHandmadeStoreList();
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().addHandmadeStoreList(posts.optJSONObject(i).getString("Store_Name"), posts.optJSONObject(i).getString("Image_Source"), posts.optJSONObject(i).getString("Category"), posts.optJSONObject(i).getString("Product_Source"));
                    }
                    break;
                case "공연":
                    Singleton.getInstance().initPerformanceList();
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().addPerformanceList(posts.optJSONObject(i).getString("Team_Name"), posts.optJSONObject(i).getString("Image_Source"));
                    }
                    break;
                case "오시는길":
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().setMarketPlace(posts.optJSONObject(i).getString("Market_Place"));
                        Singleton.getInstance().setMarketAddress(posts.optJSONObject(i).getString("Market_Address"));
                        Singleton.getInstance().setBusWay(posts.optJSONObject(i).getString("Bus_Way"));
                        Singleton.getInstance().setSubwayWay(posts.optJSONObject(i).getString("Subway_Way"));
                        Singleton.getInstance().setCarWay(posts.optJSONObject(i).getString("Car_Way"));
                        Singleton.getInstance().setLoadmapSource(posts.optJSONObject(i).getString("Loadmap_Source"));
                    }
                    break;
                case "음식":
                    Singleton.getInstance().initFoodList();
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().addFoodList(posts.optJSONObject(i).getString("Food_Name"), posts.optJSONObject(i).getString("Image_source"), posts.optJSONObject(i).getString("Price"));
                    }
                    break;
                case "핸드메이드":
                    Singleton.getInstance().initProductList();
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().addProductList(posts.optJSONObject(i).getString("Handmade_Name"), posts.optJSONObject(i).getString("Price"));
                    }
                    break;
                case "순환일정":
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().setCourse(posts.optJSONObject(i).getString("Place_Course"));
                    }
                    break;
                case "예약현황":
                    Singleton.getInstance().initWaitList();
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().addWaitList(posts.optJSONObject(i).getInt("Number"), posts.optJSONObject(i).getString("Store_Name"), posts.optJSONObject(i).getString("Store_Image"));
                    }
                    break;
                case "리뷰":
                    Singleton.getInstance().initReviewList();
                    for (int i = 0; i < posts.length(); i++) {
                        Singleton.getInstance().addReviewList(posts.optJSONObject(i).getInt("Star_Score"), posts.optJSONObject(i).getString("Nickname"), posts.optJSONObject(i).getString("Day"), posts.optJSONObject(i).getString("Client_Comment"));
                    }
                    break;
                case "로그인":
                    Singleton.getInstance().setNowLogin(false);
                    if (posts.length() > 0) {
                        Singleton.getInstance().setNowLogin(true);
                        Singleton.getInstance().setNowSeller(posts.optJSONObject(0).getString("Nickname"));
                    } else {
                        Singleton.getInstance().setNowLogin(false);
                    }
                    break;
                case "번호표 보기":
                    int min = 99999;
                    int max = 0;

                    Singleton.getInstance().setDuplicated(false);
                    Singleton.getInstance().setWaitCount(posts.length());

                    Log.e("LENGTH", posts.length() + "");

                    for (int i = 0; i < posts.length(); i++)
                    {
                        if (posts.optJSONObject(i).getInt("Number") > max)
                        {
                            max = posts.optJSONObject(i).getInt("Number");
                        }

                        if (posts.optJSONObject(i).getInt("Number") < min) {
                            min = posts.optJSONObject(i).getInt("Number");
                            Singleton.getInstance().setNowCallNickName(posts.optJSONObject(i).getString("Customer").substring(7, 11));
                        }

                        if (posts.optJSONObject(i).getString("Customer").equals(Singleton.getInstance().getNowLoginID())) {
                            Singleton.getInstance().setDuplicated(true);
                        }
                    }

                    if (posts.length() > 4) {
                        Singleton.getInstance().setSMSReceiver(posts.optJSONObject(4).getString("Customer"));
                    }

                    if (min == 99999) {
                        min = 0;
                    }
                    Singleton.getInstance().setNowClient(min);
                    Singleton.getInstance().setLastClient(max);
                    break;
                case "번호표 호출":
                    break;
                case "현재 순서":
                    minimum = 99999;

                    for (int i = 0; i < posts.length(); i++) {
                        if (posts.optJSONObject(i).getInt("Number") < minimum) {
                            minimum = posts.optJSONObject(i).getInt("Number");
                        }
                    }

                    Singleton.getInstance().addNowWaitList(minimum);
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    public static String POST(String url, String type) {
        InputStream is = null;
        String result = "";

        try {
            URL urlCon = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();

            if (httpCon != null) {
                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Accept-Charset", "UTF-8");
                httpCon.setRequestProperty("Accept", "application/json");
                httpCon.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
                httpCon.setDoInput(true);
                httpCon.setDoOutput(true);
            }

            is = httpCon.getInputStream();

            StringBuilder builder = new StringBuilder(); //문자열을 담기 위한 객체
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "UTF-8")); //문자열 셋 세팅
            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            result = builder.toString();

            reader.close();
            httpCon.disconnect();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }


    public static String getURLEncode(String content) {
        try {
            return URLEncoder.encode(content, "utf-8");   // UTF-8
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Bitmap translateBitmap(String url) {
        final String de = url;

        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(de);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mThread.start();

        try {
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}