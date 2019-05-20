package klicus.app.klicus;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.valdesekamdem.library.mdtoast.MDToast;

import klicus.app.klicus.commons.CommonUtil;


public class FragmentUs extends Fragment {

    FloatingActionButton web, ig, fb, gmail, whatsapp;
    public FragmentUs() {    }

    public static FragmentUs newInstance() {
        FragmentUs fragment = new FragmentUs();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtil.setTitleSubtitle(((AppCompatActivity)getActivity()).getSupportActionBar(), getActivity().getApplicationContext(), getString(R.string.nav_aboutus));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_us, container, false);

        web = view.findViewById(R.id.fab_web);
        ig = view.findViewById(R.id.fab_ig);
        fb = view.findViewById(R.id.fab_fb);
        gmail = view.findViewById(R.id.fab_gmail);
        whatsapp = view.findViewById(R.id.fab_wasp);


        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.link_website)));
                startActivity(i);
            }
        });

        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.link_ig)));
                startActivity(i);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.link_fb)));
                startActivity(i);
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse(getResources().getString(R.string.link_email)));
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.link_subject));
                try{
                    startActivity(i);
                } catch(android.content.ActivityNotFoundException ex){
                    MDToast mdToast = MDToast.makeText(getActivity().getApplicationContext(), getString(R.string.error_mail), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.link_wasp)));
                startActivity(i);
            }
        });

        CommonUtil.setTitleSubtitle(((AppCompatActivity)getActivity()).getSupportActionBar(), getActivity().getApplicationContext(), getString(R.string.nav_aboutus));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().setTitle(getString(R.string.app_name));
        activity.getSupportActionBar().setSubtitle(getString(R.string.nav_aboutus));
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void onBackPressed() {
        Activity activity = getActivity();

        if (activity != null) {
            activity.onBackPressed();
        }
    }

}
