package klicus.app.klicus.Ads;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import klicus.app.klicus.R;

/**
 * Created by Sol Mayra on 10/03/2018.
 */

public class RecyclerViewAds extends RecyclerView.Adapter<AdsViewHolder> implements View.OnClickListener{

    ArrayList<Ads> adsArray;
    Context context;
    LayoutInflater inflater;
    View.OnClickListener listener;

    public RecyclerViewAds(Context context, ArrayList<Ads> ads) {
        this.context = context;
        this.adsArray = ads;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public AdsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_ads, parent, false);
        view.setOnClickListener(this);
        return new AdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdsViewHolder holder, int position) {
        holder.title.setText(adsArray.get(position).getNAME());
        if(!adsArray.get(position).getTEL().equals("0")){
            holder.tel.setText(adsArray.get(position).getTEL().toString());
        } else if(!adsArray.get(position).getCEL().equals("0")){
            holder.tel.setText(adsArray.get(position).getCEL().toString());
        } else{
            holder.tel.setText(context.getString(R.string.no_have));
        }
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
}
