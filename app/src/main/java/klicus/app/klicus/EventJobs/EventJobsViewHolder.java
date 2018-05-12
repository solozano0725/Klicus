package klicus.app.klicus.EventJobs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import klicus.app.klicus.R;

public class EventJobsViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title;
    TextView subtitle;

    public EventJobsViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageViewEJ);
        title = (TextView) itemView.findViewById(R.id.titleEJ);
        subtitle = (TextView) itemView.findViewById(R.id.subtitleEJ);
    }
}
