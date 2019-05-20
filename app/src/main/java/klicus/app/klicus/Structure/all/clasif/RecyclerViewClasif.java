package klicus.app.klicus.structure.all.clasif;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import klicus.app.klicus.R;
import klicus.app.klicus.entity.Clasif;
import klicus.app.klicus.structure.all.ViewHolderAll;

public class RecyclerViewClasif extends RecyclerView.Adapter<ViewHolderAll> implements View.OnClickListener {

    ArrayList<Clasif> clasArray;
    Context context;
    LayoutInflater inflater;

    View.OnClickListener listener;

    public RecyclerViewClasif(Context context, ArrayList<Clasif> clas) {
        this.context = context;
        this.clasArray = clas;
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
        holder.title.setText(clasArray.get(position).getTITLE());
        holder.subtitle.setText(clasArray.get(position).getSUBTITLE());
        Picasso.with(context).load(clasArray.get(position).getIMAGE()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return clasArray.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }
}
