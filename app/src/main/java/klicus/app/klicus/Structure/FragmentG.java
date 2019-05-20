package klicus.app.klicus.structure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import klicus.app.klicus.MainActivity;
import klicus.app.klicus.R;
import klicus.app.klicus.commons.CommonUtil;
import klicus.app.klicus.data.Services;

import static klicus.app.klicus.commons.CommonConstants.arrayDBCategory;
import static klicus.app.klicus.commons.CommonConstants.db;

public class FragmentG extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private int page;
    private String title;
    private String search;
    private Toolbar bar;

    public FragmentG() {    }

    public static FragmentG getInstance(int page, String title) {
        FragmentG fragmentFirst = new FragmentG();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("page", 0);
            title = getArguments().getString("title");
            CommonUtil.setTitleSubtitle(((AppCompatActivity)getActivity()).getSupportActionBar(), getActivity().getApplicationContext(), title);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv, container, false);
        recyclerView = view.findViewById(R.id.rv);
        searchView = view.findViewById(R.id.searchView);
        bar = getActivity().findViewById(R.id.toolbar);

        bar.setSubtitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        bar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        bar.setPadding(10,10,10,10);

        final Services s = new Services();

        changeRV(s, "null");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //search = ;
                changeRV(s, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //search = newText;
                changeRV(s, newText);
                return true;
            }
        });

        CommonUtil.setTitleSubtitle(((AppCompatActivity)getActivity()).getSupportActionBar(), getActivity().getApplicationContext(), title);
        return view;
    }

    private void changeRV(Services s, String search){
        switch (page){
            case 0:
                s.loadAds(db.getReference(arrayDBCategory[0]), recyclerView, getActivity(), search);
                break;
            case 1:
                s.loadPromo(db.getReference(arrayDBCategory[5]), recyclerView, getActivity(), search);
                break;
            case 2:
                s.loadEvents(db.getReference(arrayDBCategory[2]), recyclerView, getActivity(), search);
                break;
            case 3:
                s.loadClasif(db.getReference(arrayDBCategory[1]), recyclerView, getActivity(), search);
                break;
            case 4:
                s.loadJobs(db.getReference(arrayDBCategory[3]), recyclerView, getActivity(), search);
                break;
        }
    }

}
