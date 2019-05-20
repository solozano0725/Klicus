package klicus.app.klicus.structure.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import klicus.app.klicus.R;
import klicus.app.klicus.entity.News;
import klicus.app.klicus.structure.all.ViewHolderAll;

/**
 * Created by Sol Mayra on 19/03/2018.
 */

public class RecyclerViewMain extends RecyclerView.Adapter<ViewHolderAll> implements View.OnClickListener{

    ArrayList<News> arrayList;
    Context context;
    LayoutInflater inflater;
    View.OnClickListener listener;

    public RecyclerViewMain(Context c, ArrayList<News> array) {
        this.context = c;
        this.arrayList = array;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public ViewHolderAll onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_main, parent, false);
        view.setOnClickListener(this);
        return new ViewHolderAll(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAll holder, int position) {
        holder.title.setText(arrayList.get(position).getTITLE());
        holder.subtitle.setText(arrayList.get(position).getSUBTITLE());
        Picasso.with(context).load(arrayList.get(position).getIMAGE()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
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