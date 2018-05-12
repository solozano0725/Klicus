package klicus.app.klicus.EventJobs;

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
import com.valdesekamdem.library.mdtoast.MDToast;

import klicus.app.klicus.R;

public class ContentJ extends AppCompatActivity {

    private Job j;
    private TextView title, subtitle, description, place, tel, email ;
    private ImageView imgview;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_j);

        j = (Job) getIntent().getExtras().getSerializable("job");

        title = findViewById(R.id.txttitle);
        subtitle = findViewById(R.id.txtsubtitle);
        description = findViewById(R.id.txtdescription);
        place = findViewById(R.id.txtplace);
        tel = findViewById(R.id.txtel);
        email = findViewById(R.id.txtemail);
        imgview = findViewById(R.id.imageContentJ);

        loaderText(j);

        Picasso.with(getApplicationContext()).load(j.getIMAGE()).into(imgview);

        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + tel.getText().toString()));
                if (ActivityCompat.checkSelfPermission(ContentJ.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentJ.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    startActivity(intent);
                    return;
                } else{
                    startActivity(intent);
                }

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"+email.getText().toString()));
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.link_subject_all)+j.getSUBTITLE().toString()+" en "+j.getTITLE().toString());
                try{
                    startActivity(i);
                } catch(android.content.ActivityNotFoundException ex){
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.error_mail), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }
            }
        });
    }

    public void loaderText(Job j) {
        ((AppCompatActivity) this).getSupportActionBar().setTitle(R.string.app_name);
        ((AppCompatActivity) this).getSupportActionBar().setSubtitle(j.getTITLE());

        String t = j.getTITLE().toString();
        String s = j.getSUBTITLE().toString();
        String d = j.getDESCRIPTION().toString();
        String p = j.getPLACE().toString();
        String te = j.getTEL().toString();
        String e = j.getEMAIL().toString();

        if (!s.equalsIgnoreCase("0")) {
            title.setText(s);
        } else {
            title.setText(R.string.nofound_title);
        }

        if(!d.equalsIgnoreCase("0")){
            subtitle.setText(d);
        } else{
            subtitle.setText(R.string.nofound_title);
        }

        if(!t.equalsIgnoreCase("0")){
            description.setText(t);
        } else{
            description.setText(R.string.nofound_title);
        }

        if(!p.equalsIgnoreCase("0")){
            place.setText(p);
        } else{
            place.setText(R.string.nofound_title);
        }

        if(!te.equalsIgnoreCase("0")){
            tel.setText(te);
        } else{
            tel.setText(R.string.nofound_title);
        }

        if(!e.equalsIgnoreCase("0")){
            email.setText(e);
        } else{
            email.setText(R.string.nofound_title);
        }

    }

}
