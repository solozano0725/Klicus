package klicus.app.klicus.structure.all.clasif;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import klicus.app.klicus.R;
import klicus.app.klicus.entity.Clasif;
import klicus.app.klicus.structure.all.job.DetailsJob;

public class DetailsClasif extends AppCompatActivity {

    private CarouselView carouselView;
    private Clasif c;
    private TextView title, subtitle, description, place, date, contact;
    private ImageView imgview;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_clasif);
        c = (Clasif) getIntent().getExtras().getSerializable("clasi");

        title = findViewById(R.id.txtTitle);
        subtitle = findViewById(R.id.txtSubtitle);
        description = findViewById(R.id.txtDescrip);
        place = findViewById(R.id.txtPlace);
        date = findViewById(R.id.txtDate);
        contact = findViewById(R.id.txtContact);

        loaderText(c);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contact.getText().toString()));
                if (ActivityCompat.checkSelfPermission(DetailsClasif.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DetailsClasif.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    startActivity(intent);
                    return;
                } else{
                    startActivity(intent);
                }

            }
        });
    }

    public void loaderText(Clasif c){
        this.getSupportActionBar().setTitle(R.string.app_name);
        this.getSupportActionBar().setSubtitle(c.getTITLE());

        String t= c.getTITLE(), st= c.getSUBTITLE(), d= c.getDATE(), des= c.getDESCRIPTION(), p= c.getPLACE(), con= c.getCONTACT();

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

        if(!p.equalsIgnoreCase("0")| !p.isEmpty()){
            place.setText(p);
        } else{
            description.setText(R.string.no_have);
        }

        if(!con.equalsIgnoreCase("0")| !con.isEmpty()){
            contact.setText(con);
        } else{
            contact.setText(R.string.no_have);
        }

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(c.getGALLERY().size());
        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext()).load(c.getGALLERY().get(position)).fit().into(imageView);
        }
    };
}
