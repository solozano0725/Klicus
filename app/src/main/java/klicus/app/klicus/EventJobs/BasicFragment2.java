package klicus.app.klicus.EventJobs;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

import klicus.app.klicus.Cate_Loca_Type;
import klicus.app.klicus.MainActivity;
import klicus.app.klicus.R;
import klicus.app.klicus.Structure.FragmentBasic;
import klicus.app.klicus.Structure.Services;

public class BasicFragment2 extends Fragment {

    private FragmentBasic.OnFragmentInteractionListener mListener;
    private int option = 0;
    FirebaseDatabase db;
    DatabaseReference location, events, jobs;
    RecyclerView recyclerView;
    ArrayList<Job> arrayJobs;
    ArrayList<Cate_Loca_Type> arrayOptions;
    ArrayList<Event> arrayEvents;
    MaterialSpinner spinner;

    public BasicFragment2() {

    }

    public static BasicFragment2 getInstace(int op) {
        BasicFragment2 fragment = new BasicFragment2();
        fragment.option = op;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setHasOptionsMenu(true); }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
        final MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_v, container, false);

        db =  FirebaseDatabase.getInstance("https://klicus-4b8a7.firebaseio.com");

        events = db.getReference("EVENT");
        jobs = db.getReference("JOB");
        location = db.getReference("LOCATION_ADS");

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        spinner = (MaterialSpinner) view.findViewById(R.id.spinner);

        arrayEvents = new ArrayList<>();
        arrayOptions = new ArrayList<>();
        arrayJobs = new ArrayList<>();

        final Services services = new Services();

        switch(option) {
            case 0:
                arrayOptions = services.loadSpinner(location, arrayOptions, spinner, getActivity());
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        arrayEvents.clear();
                        for (int i = 0; i < arrayOptions.size(); i++) {
                            if (arrayOptions.get(i).getNAME().equalsIgnoreCase(item.toString())) {
                                services.loadEvents(events, arrayOptions.get(i).getID(), "ID_LOCATION", arrayEvents, recyclerView, getActivity(), getActivity().getSupportFragmentManager());
                            }

                        }
                    }
                });

                break;
            case 1:
                arrayOptions = services.loadSpinner(location, arrayOptions, spinner, getActivity());
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        arrayJobs.clear();
                        for (int i = 0; i < arrayOptions.size(); i++) {
                            if (arrayOptions.get(i).getNAME().equalsIgnoreCase(item.toString())) {
                                services.loadJobs(jobs, arrayOptions.get(i).getID(), "ID_LOCATION", arrayJobs, recyclerView, getActivity(), getActivity().getSupportFragmentManager());
                            }
                        }
                    }
                });

                break;
        }

        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        switch(option){
            case 0:
                activity.getSupportActionBar().setTitle(R.string.app_name);
                activity.getSupportActionBar().setSubtitle(R.string.nav_gift);
                break;
            case 1:
                activity.getSupportActionBar().setTitle(R.string.app_name);
                activity.getSupportActionBar().setSubtitle(R.string.nav_jobs);
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
