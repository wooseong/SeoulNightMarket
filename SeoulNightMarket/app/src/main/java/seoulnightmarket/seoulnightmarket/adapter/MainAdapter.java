package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import seoulnightmarket.seoulnightmarket.Activity.AreaInformationWithTabBar;
import seoulnightmarket.seoulnightmarket.Activity.LoginActivity;
import seoulnightmarket.seoulnightmarket.Activity.NumberTicketActivity;
import seoulnightmarket.seoulnightmarket.Activity.SellerActivity;
import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

/**
 * Created by Yookmoonsu on 2017-09-14.
 */

public class MainAdapter extends BaseAdapter {
    private Context context;
    private int[] image;
    private boolean login;
    private String region;

    public MainAdapter(Context context, int[] image) {
        this.context = context;
        this.image = image;
    }

    @Override
    public int getCount() {
        return image.length;
    } // 그리드뷰에 출력할 목록 갯수

    @Override
    public Object getItem(int position) {
        return image[position];
    } // 아이템 호출

    @Override
    public long getItemId(int position) {
        return position;
    } // 아이템의 아이디

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.gridview_home, null);
            ImageView imageView = gridView.findViewById(R.id.imageViewHome);

            imageView.setImageResource(image[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            gridView = convertView;
        }

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = Singleton.getInstance().getNowLogin(); // 로그인 상태 받아옴

                if (position == 0) { // 로그인 버튼
                    if (!login) {
                        context.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // 로그아웃 상태일때 로그인 화면으로 이동
                    } else if (login) {
                        if(Singleton.getInstance().getNowLoginID().contains("Admin"))
                        {
                            context.startActivity(new Intent(context, SellerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                        else {
                            context.startActivity(new Intent(context, NumberTicketActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                    }
                } else if (position == 1) {
                    context.startActivity(new Intent(context, AreaInformationWithTabBar.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // 클릭한 시장 상세 화면으로 이동
                    region = "여의도";
                    Singleton.getInstance().setRegion(region);
                } else if (position == 2) {
                    context.startActivity(new Intent(context, AreaInformationWithTabBar.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    region = "DDP";
                    Singleton.getInstance().setRegion(region);
                } else if (position == 3) {
                    context.startActivity(new Intent(context, AreaInformationWithTabBar.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    region = "반포";
                    Singleton.getInstance().setRegion(region);
                } else if (position == 4) {
                    region = "청계천";
                    Singleton.getInstance().setRegion(region);
                    context.startActivity(new Intent(context, AreaInformationWithTabBar.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } else if (position == 5) {
                    region = "청계광장";
                    Singleton.getInstance().setRegion(region);
                    context.startActivity(new Intent(context, AreaInformationWithTabBar.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });

        return gridView;
    }
}