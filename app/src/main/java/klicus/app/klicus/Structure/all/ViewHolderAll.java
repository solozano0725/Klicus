package klicus.app.klicus.structure.all;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import klicus.app.klicus.R;

/**
 * Created by Sol Mayra on 04/03/2018.
 */

public class ViewHolderAll extends RecyclerView.ViewHolder{

     public ImageView imageView;
     public TextView title;
     public TextView subtitle;

    public ViewHolderAll(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageViewAll);
        title = (TextView) itemView.findViewById(R.id.titleAll);
        subtitle = (TextView) itemView.findViewById(R.id.subtitleAll);
    }
}