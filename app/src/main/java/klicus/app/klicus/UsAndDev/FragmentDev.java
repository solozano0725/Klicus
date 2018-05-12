package klicus.app.klicus.UsAndDev;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import klicus.app.klicus.MainActivity;
import klicus.app.klicus.R;

public class FragmentDev extends Fragment {

    ImageView link;
    public FragmentDev() {
        // Required empty public constructor
    }

    public static FragmentDev newInstance(String param1, String param2) {
        FragmentDev fragment = new FragmentDev();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dev, container, false);

        link = view.findViewById(R.id.btnlinkedin);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/solozano0725"));
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().setTitle(getString(R.string.app_name));
        activity.getSupportActionBar().setSubtitle(getString(R.string.nav_dev));
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public void onBackPressed() {
        Activity activity = getActivity();

        if (activity != null) {
            activity.onBackPressed();
        }
    }


}
