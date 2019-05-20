package klicus.app.klicus.structure.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import klicus.app.klicus.MainActivity;
import klicus.app.klicus.R;
import klicus.app.klicus.entity.News;

public class DetailsMain extends AppCompatActivity {

    private CarouselView carouselView;
    private News n;
    private TextView title, subtitle, date, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_main);
        n = (News) getIntent().getExtras().getSerializable("news");

        title = findViewById(R.id.txtTitle);
        subtitle = findViewById(R.id.txtSubtitle);
        date = findViewById(R.id.txtDate);
        description = findViewById(R.id.txtDescrip);

        loaderText(n);

    }

    public void loaderText(News n){
        this.getSupportActionBar().setTitle(R.string.app_name);
        this.getSupportActionBar().setSubtitle(n.getTITLE());

        String t= n.getTITLE(), st= n.getSUBTITLE(), d= n.getDATE(), des= n.getDESCRIPTION();

        if(!t.equalsIgnoreCase("0") || !t.isEmpty()){
            title.setText(t);
        } else{
            title.setText(R.string.no_have);
        }

        if(!st.equalsIgnoreCase("0")|| !st.isEmpty()){
            subtitle.setText(st);
        } else{
            subtitle.setText(R.string.no_have);
        }

        if(!d.equalsIgnoreCase("0")| !d.isEmpty()){
            date.setText(d);
        } else{
            date.setText(R.string.no_have);
        }

        if(!des.equalsIgnoreCase("0")| !des.isEmpty()){
            description.setText(des);
        } else{
            description.setText(R.string.no_have);
        }

        carouselView = findViewById(R.id.carouselView);
        Log.i("size",""+n.getGALLERY().size());
        carouselView.setPageCount(n.getGALLERY().size());
        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext()).load(n.getGALLERY().get(position)).fit().into(imageView);
        }
    };
}
