package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.AreaInformationWithTabBar;
import seoulnightmarket.seoulnightmarket.etc.LoginActivity;
import seoulnightmarket.seoulnightmarket.etc.NumberTicketActivity;

/**
 * Created by Yookmoonsu on 2017-09-14.
 */

public class MainAdapter extends BaseAdapter {
    private Context context;
    private int[] image;
    private boolean login = false;

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
                if (position == 0) {
                    if (!login) {
                        context.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // 로그아웃 상태일때 로그인 화면으로 이동
                    }
                    else if (login) {
                        context.startActivity(new Intent(context, NumberTicketActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // 로그인 상태일때 번호표 화면으로 이동
                    }
                }
                else {
                    context.startActivity(new Intent(context, AreaInformationWithTabBar.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // 클릭한 메뉴 상세 화면으로 이동
                }
            }
        });

        return gridView;
    }
}