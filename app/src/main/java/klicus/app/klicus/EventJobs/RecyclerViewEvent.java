package klicus.app.klicus.EventJobs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import klicus.app.klicus.R;

public class RecyclerViewEvent extends RecyclerView.Adapter<EventJobsViewHolder> implements View.OnClickListener {

    ArrayList<Event> eventArray;
    Context context;
    LayoutInflater inflater;
    View.OnClickListener listener;

    public RecyclerViewEvent(Context context, ArrayList<Event> event) {
        this.context = context;
        this.eventArray = event;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public EventJobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_eventjob, parent, false);
        view.setOnClickListener(this);
        return new EventJobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventJobsViewHolder holder, int position) {
        holder.title.setText(eventArray.get(position).getTITLE());
        holder.subtitle.setText(eventArray.get(position).getSUBTITLE());
        Picasso.with(context).load(eventArray.get(position).getIMAGE()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return eventArray.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
}
