package klicus.app.klicus.Structure;

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
import com.valdesekamdem.library.mdtoast.MDToast;

import klicus.app.klicus.Ads.Ads;
import klicus.app.klicus.R;

public class ContentFB extends AppCompatActivity {

    private CarouselView carouselView;
    private Ads a;
    private TextView name, propiet, description, dire, domicilio, email, horario, tel, cel;
    private ImageView webb, face, inst;

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_content_basic);

        a = (Ads) getIntent().getExtras().getSerializable("ads");

        name = findViewById(R.id.txttitle);
        description = findViewById(R.id.txtdescription);
        dire = findViewById(R.id.txtadress);
        propiet = findViewById(R.id.txtdueño);
        cel = findViewById(R.id.txtcel);
        tel = findViewById(R.id.txttel);
        email = findViewById(R.id.txtemail);
        horario = findViewById(R.id.txthour);
        domicilio = findViewById(R.id.txtdomi);
        face = findViewById(R.id.img_fb);
        inst = findViewById(R.id.img_ig);
        webb = findViewById(R.id.img_web);

        loaderText(a);
        loaderSocial(a);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(a.getGALLERY().size());
        carouselView.setImageListener(imageListener);


        cel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + cel.getText().toString()));
                if (ActivityCompat.checkSelfPermission(ContentFB.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentFB.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    startActivity(intent);
                    return;
                } else{
                    startActivity(intent);
                }
            }
        });

        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + tel.getText().toString()));
                if (ActivityCompat.checkSelfPermission(ContentFB.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentFB.this,
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
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.link_subject_all)+name.getText().toString());
                try{
                    startActivity(i);
                } catch(android.content.ActivityNotFoundException ex){
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.error_mail), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }
            }
        });

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext()).load(a.getGALLERY().get(position)).fit().into(imageView);
        }
    };

    public void loaderText(Ads a){
        ((AppCompatActivity)this).getSupportActionBar().setTitle(R.string.app_name);
        ((AppCompatActivity)this).getSupportActionBar().setSubtitle(a.getNAME());

        String n = a.getNAME().toString(), de=a.getDESCRIPTION().toString(), di= a.getDIRE().toString(), p=a.getPROPIET().toString(), cl=a.getCEL().toString(), tl=a.getTEL().toString(), em=a.getEMAIL().toString(), hour=a.getTIME().toString(), domi=a.getDOMICILIO().toString();

        if(!n.equalsIgnoreCase("0")){
            name.setText(n);
        } else{
            name.setText(R.string.no_have);
        }

        if(!de.equalsIgnoreCase("0")){
            description.setText(de);
        } else{
            description.setText(R.string.no_have);
        }

        if(!di.equalsIgnoreCase("0")){
            dire.setText(di);
        } else{
            dire.setText(R.string.no_have);
        }

        if(!p.equalsIgnoreCase("0")){
            propiet.setText(p);
        } else{
            propiet.setText(R.string.no_have);
        }

        if(!cl.equalsIgnoreCase("0")){
            cel.setText(cl);
        } else {
            cel.setText(R.string.no_have);
        }

        if(!tl.equalsIgnoreCase("0")){
            tel.setText(tl);
        } else {
            tel.setText(R.string.no_have);
        }

        if(!em.equalsIgnoreCase("0")){
            email.setText(em);
        } else {
            email.setText(R.string.no_have);
        }

        if(!hour.equalsIgnoreCase("0")){
            horario.setText(hour);
        } else {
            horario.setText(R.string.no_have);
        }

        if(!domi.equalsIgnoreCase("0")){
            domicilio.setText(domi);
        } else {
            domicilio.setText(R.string.no_have);
        }

    }

    public void loaderSocial(Ads a){
        final String fb = a.getFB(), ig = a.getIG(), web = a.getWEB();

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fb.equalsIgnoreCase("0")){
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(fb));
                    startActivity(i);
                } else{
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.no_have), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }
            }
        });

        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ig.equalsIgnoreCase("0")){
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(ig));
                    startActivity(i);
                } else{
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.no_have), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }
            }
        });

        webb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!web.equalsIgnoreCase("0")){
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
                    startActivity(i);
                } else{
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), getString(R.string.no_have), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }
            }
        });





    }

}
