package klicus.app.klicus.structure.all.event;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import klicus.app.klicus.R;
import klicus.app.klicus.entity.Event;

public class DetailsEvent extends AppCompatActivity {

    private CarouselView carouselView;
    private Event e;
    private TextView title, subtitle, date, place, time, contact ;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_event);

        e = (Event) getIntent().getExtras().getSerializable("event");

        title = findViewById(R.id.txttitle);
        subtitle = findViewById(R.id.txtsubtitle);
        date = findViewById(R.id.txtdate);
        place = findViewById(R.id.txtplace);
        time = findViewById(R.id.txtime);
        contact = findViewById(R.id.txtcontact);

        loaderText(e);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(e.getGALLERY().size());
        carouselView.setImageListener(imageListener);


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contact.getText().toString()));
                if (ActivityCompat.checkSelfPermission(DetailsEvent.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DetailsEvent.this,
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


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext()).load(e.getGALLERY().get(position)).fit().into(imageView);
        }
    };

    public void loaderText(Event e) {
        ((AppCompatActivity) this).getSupportActionBar().setTitle(R.string.app_name);
        ((AppCompatActivity) this).getSupportActionBar().setSubtitle(e.getTITLE());

        String t = e.getTITLE().toString();
        String s = e.getSUBTITLE().toString();
        String d = e.getDATE().toString();
        String p = e.getPLACE().toString();
        String ti = e.getTIME().toString();
        String c = e.getCONTACT().toString();

        if (!t.equalsIgnoreCase("0")) {
            title.setText(t);
        } else {
            title.setText(R.string.no_have);
        }

        if(!s.equalsIgnoreCase("0")){
            subtitle.setText(s);
        } else{
            subtitle.setText(R.string.no_have);
        }

        if(!d.equalsIgnoreCase("0")){
            date.setText(d);
        } else{
            date.setText(R.string.no_have);
        }

        if(!p.equalsIgnoreCase("0")){
            place.setText(p);
        } else{
            place.setText(R.string.no_have);
        }

        if(!ti.equalsIgnoreCase("0")){
            time.setText(ti);
        } else{
            time.setText(R.string.no_have);
        }

        if(!c.equalsIgnoreCase("0")){
            contact.setText(c);
        } else{
            contact.setText(R.string.no_have);
        }

    }

}
