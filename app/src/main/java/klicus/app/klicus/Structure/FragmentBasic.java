package klicus.app.klicus.Structure;

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

import klicus.app.klicus.Ads.Ads;
import klicus.app.klicus.Cate_Loca_Type;
import klicus.app.klicus.Promo.Promo;
import klicus.app.klicus.R;

public class FragmentBasic extends Fragment {

    private OnFragmentInteractionListener mListener;
    private int option = 0;
    FirebaseDatabase db;
    DatabaseReference promo, category, location, ads, events, jobs;
    RecyclerView recyclerView;
    ArrayList<Promo> arrayPromo;
    ArrayList<Cate_Loca_Type> arrayOptions;
    ArrayList<Ads> arrayAds;
    MaterialSpinner spinner;

    public FragmentBasic() {

    }

    public static FragmentBasic getInstace(int op) {
        FragmentBasic fragment = new FragmentBasic();
        fragment.option = op;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

   /* @Override
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

        promo = db.getReference("PROMO");
        ads = db.getReference("ADS");
        category = db.getReference("CATEGORY_ADS");
        location = db.getReference("LOCATION_ADS");

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        spinner = (MaterialSpinner) view.findViewById(R.id.spinner);

        arrayPromo = new ArrayList<>();
        arrayOptions = new ArrayList<>();
        arrayAds = new ArrayList<>();

        final Services services = new Services();

        switch(option){
            case 0:
                arrayOptions = services.loadSpinner(location, arrayOptions, spinner, getActivity());
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        arrayAds.clear();
                        for(int i = 0; i<arrayOptions.size(); i++){
                            if(arrayOptions.get(i).getNAME().equalsIgnoreCase(item.toString())){
                                services.loadAds(ads, arrayOptions.get(i).getID(), "ID_LOCATION", arrayAds, recyclerView, getActivity(), getActivity().getSupportFragmentManager());
                            }

                        }
                    }
                });

                break;
            case 1:
                arrayOptions = services.loadSpinner(category, arrayOptions, spinner, getActivity());
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        arrayAds.clear();
                        for(int i = 0; i<arrayOptions.size(); i++){
                            if(arrayOptions.get(i).getNAME().equalsIgnoreCase(item.toString())){
                               services.loadAds(ads, arrayOptions.get(i).getID(), "ID_CATEGORY", arrayAds, recyclerView, getActivity(), getActivity().getSupportFragmentManager());
                            }
                        }
                    }
                });

                break;
            case 2:
                arrayOptions = services.loadSpinner(location, arrayOptions, spinner, getActivity());
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        arrayPromo.clear();
                        for(int i = 0; i<arrayOptions.size(); i++){
                            if(arrayOptions.get(i).getNAME().equalsIgnoreCase(item.toString())){
                                services.loadPromo(promo, arrayOptions.get(i).getID(), "ID_LOCATION", arrayPromo, recyclerView, getActivity(), getActivity().getSupportFragmentManager());
                            }
                        }
                    }
                });

                break;
            case 3:
                arrayOptions = services.loadSpinner(category, arrayOptions, spinner, getActivity());
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        arrayPromo.clear();
                        for(int i = 0; i<arrayOptions.size(); i++){
                            if(arrayOptions.get(i).getNAME().equalsIgnoreCase(item.toString())){
                                services.loadPromo(promo, arrayOptions.get(i).getID(), "ID_CATEGORY", arrayPromo, recyclerView, getActivity(), getActivity().getSupportFragmentManager());
                            }
                        }

                    }
                });

                break;
        }

        return view;
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
