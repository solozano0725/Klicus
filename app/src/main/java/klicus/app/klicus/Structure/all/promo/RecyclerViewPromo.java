package klicus.app.klicus.structure.all.promo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import klicus.app.klicus.R;
import klicus.app.klicus.entity.Promo;
import klicus.app.klicus.structure.all.promo.PromoViewHolder;

/**
 * Created by Sol Mayra on 05/03/2018.
 */

public class RecyclerViewPromo extends RecyclerView.Adapter<PromoViewHolder> {

    ArrayList<Promo> promoArray;
    Context context;
    LayoutInflater inflater;

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            PromoViewHolder vh = (PromoViewHolder) v.getTag();
            int position = vh.getAdapterPosition();

            Toast.makeText(context.getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

        }
    };

    public RecyclerViewPromo(Context context, ArrayList<Promo> promo) {
        this.context = context;
        this.promoArray = promo;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_promo, parent, false);
        return new PromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromoViewHolder holder, int position) {
        holder.description.setText(promoArray.get(position).getTITLE());
        holder.subtitle.setText(promoArray.get(position).getSUBTITLE());
        holder.title.setText(promoArray.get(position).getDESCRIPTION());
        Picasso.with(context).load(promoArray.get(position).getIMAGE_URL()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return promoArray.size();
    }

}
