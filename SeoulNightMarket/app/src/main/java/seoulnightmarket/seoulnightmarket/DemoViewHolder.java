package seoulnightmarket.seoulnightmarket;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by SONU on 25/09/15.
 */
public class DemoViewHolder  extends RecyclerView.ViewHolder {


public TextView title;


public DemoViewHolder(View view) {
        super(view);


        this.title = (TextView) view.findViewById(R.id.cardTitle);

}
        }