package seoulnightmarket.seoulnightmarket.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import seoulnightmarket.seoulnightmarket.Activity.LoginActivity;
import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.ReviewAdapter;
import seoulnightmarket.seoulnightmarket.adapter.ReviewSpinnerAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentReview extends Fragment {
    private String today;
    private int[] users = {R.drawable.boy, R.drawable.girl, R.drawable.man};
    private int[] flags = {R.drawable.onestar, R.drawable.twostar, R.drawable.threestar, R.drawable.fourstar, R.drawable.fivestar};
    private int imageCount = 0;
    private int starScore = 3;
    private String uri;
    private String url;
    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public FragmentReview() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA); // 오늘 날짜
        today = simpleDateFormat.format(new Date());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_review, container, false);

        final FragmentActivity fragment = getActivity();

        recyclerView = view.findViewById(R.id.review_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(fragment);
        recyclerView.setLayoutManager(layoutManager);

        final EditText editText = view.findViewById(R.id.editText);
        final Spinner spinnerStar = view.findViewById(R.id.spinnerStar);

        ReviewSpinnerAdapter reviewSpinnerAdapter = new ReviewSpinnerAdapter(getActivity(), flags);
        spinnerStar.setAdapter(reviewSpinnerAdapter);

        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/review")
                .buildUpon()
                .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getType()))
                .build().toString();

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("리뷰");
        httpAsyncTask.execute(uri);

        spinnerStar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spinnerStar.setBackground(null);
                starScore = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnSubmit = view.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() { // 리뷰 남기기 버튼
            @Override
            public void onClick(View view) {
                if (Singleton.getInstance().getNowLogin())
                {
                    if(editText.getText().toString().length()>0) {
                        url = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/review/make")
                                .buildUpon()
                                .appendQueryParameter("phone", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowLoginID()))
                                .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getType()))
                                .appendQueryParameter("score", HttpTask.getInstance().getURLEncode(starScore + ""))
                                .appendQueryParameter("describe", HttpTask.getInstance().getURLEncode(editText.getText().toString()))
                                .appendQueryParameter("nickname", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowSeller()))
                                .appendQueryParameter("date", HttpTask.getInstance().getURLEncode(today))
                                .build().toString();

                        ReviewAsyncTask reviewAsyncTask = new ReviewAsyncTask("리뷰 등록"); // 닉네임, 날짜, 평점, 리뷰 서버에 전송
                        reviewAsyncTask.execute(url);

                        Toast.makeText(getActivity(), "리뷰가 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "리뷰 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "로그인이 필요한 서비스 입니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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

            adapter = new ReviewAdapter();

            for (int i = 0; i < Singleton.getInstance().getNicknameList().size(); i++) {
                if (imageCount < 3) {
                    adapter.addItem(ContextCompat.getDrawable(getActivity(), users[imageCount]), Singleton.getInstance().getNicknameList().get(i),
                            ContextCompat.getDrawable(getActivity(), flags[Singleton.getInstance().getStarScoreList().get(i) - 1]), Singleton.getInstance().getDateList().get(i), Singleton.getInstance().getDescribeList().get(i));
                    imageCount++;
                }
                if (imageCount == 3) {
                    imageCount = 0;
                }
            }

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            recyclerView.invalidate();
        }
    }

    public class ReviewAsyncTask extends AsyncTask<String, Void, String> {
        String type;

        ReviewAsyncTask(String type) {
            this.type = type;
        }

        @Override
        protected String doInBackground(String... urls) {
            //urls[0] 은 URL 주소
            return HttpTask.getInstance().POST(urls[0], type);
        }
        // onPostExecute displays the results of the AsyncTask.

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getActivity(), "리뷰가 정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
            HttpAsyncTask httpAsyncTask = new HttpAsyncTask("리뷰");
            httpAsyncTask.execute(uri);
        }
    }
}