package klicus.app.klicus.MainR;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import klicus.app.klicus.R;

/**
 * Created by Sol Mayra on 19/03/2018.
 */

public class MainViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView title;

    public MainViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img_main);
        title = (TextView) itemView.findViewById(R.id.title_main);
        }
    }

