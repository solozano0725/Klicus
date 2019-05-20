package klicus.app.klicus.structure.all.job;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import klicus.app.klicus.R;
import klicus.app.klicus.entity.Job;
import klicus.app.klicus.structure.all.ViewHolderAll;

public class RecyclerViewJobs extends RecyclerView.Adapter<ViewHolderAll> implements View.OnClickListener{

    ArrayList<Job> jobArray;
    Context context;
    LayoutInflater inflater;

    View.OnClickListener listener;

    public RecyclerViewJobs(Context context, ArrayList<Job> job) {
        this.context = context;
        this.jobArray = job;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolderAll onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_all, parent, false);
        view.setOnClickListener(this);
        return new ViewHolderAll(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAll holder, int position) {
        holder.title.setText(jobArray.get(position).getTITLE());
        holder.subtitle.setText(jobArray.get(position).getSUBTITLE());
        Picasso.with(context).load(jobArray.get(position).getIMAGE()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return jobArray.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

}