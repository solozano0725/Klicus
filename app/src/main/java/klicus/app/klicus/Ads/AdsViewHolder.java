package klicus.app.klicus.Ads;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import klicus.app.klicus.R;

/**
 * Created by Sol Mayra on 04/03/2018.
 */

public class AdsViewHolder extends RecyclerView.ViewHolder{

     ImageView imageView;
     TextView title;
     TextView tel;

    public AdsViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageViewA);
        title = (TextView) itemView.findViewById(R.id.titleA);
        tel = (TextView) itemView.findViewById(R.id.telA);
    }
}