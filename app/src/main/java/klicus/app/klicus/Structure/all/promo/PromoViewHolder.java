package klicus.app.klicus.structure.all.promo;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import klicus.app.klicus.R;

/**
 * Created by Sol Mayra on 04/03/2018.
 */

public class PromoViewHolder extends ViewHolder {

     ImageView imageView;
     TextView title;
     TextView subtitle;
     TextView description;

    public PromoViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageViewP);
        title = (TextView) itemView.findViewById(R.id.titleP);
        subtitle = (TextView) itemView.findViewById(R.id.subtitleP);
        description = (TextView) itemView.findViewById(R.id.descripP);
    }
}
