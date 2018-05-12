package klicus.app.klicus.MainR;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import klicus.app.klicus.Ads.Ads;
import klicus.app.klicus.R;

/**
 * Created by Sol Mayra on 19/03/2018.
 */

public class RecyclerViewMain extends RecyclerView.Adapter<MainViewHolder> implements View.OnClickListener, Filterable {

    ArrayList<Ads> adsArray;
    Context context;
    LayoutInflater inflater;
    View.OnClickListener listener;

    public RecyclerViewMain(Context c, ArrayList<Ads> ads) {
        this.context = c;
        this.adsArray = ads;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_main, parent, false);
        view.setOnClickListener(this);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.title.setText(adsArray.get(position).getNAME());
        Picasso.with(context).load(adsArray.get(position).getIMAGE_URL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return adsArray.size();
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

    @Override
    public Filter getFilter() {
        return null;
    }
}