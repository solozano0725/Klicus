package klicus.app.klicus.Structure;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import klicus.app.klicus.MainActivity;
import klicus.app.klicus.R;

public class BasicFragmentBV extends Fragment {

    private int option = 0;

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    MenuItem prevMenuItem;

    public BasicFragmentBV() {
        // Required empty public constructor
    }

    public static BasicFragmentBV getInstace(int opcion) {
        BasicFragmentBV fragment = new BasicFragmentBV();
        fragment.option = opcion;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.viewPager);
        bottomNavigationView = view.findViewById(R.id.bottonNavigation);

        viewPager.clearOnPageChangeListeners();

        switch(option){
            case 0:
                createAdapter(viewPager, option, getActivity());
                updateBottonNavigation();
                updateViewPage();
                break;
            case 1:
                createAdapter(viewPager, option, getActivity());
                updateBottonNavigation();
                updateViewPage();
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic, container, false);
    }

    public void onBackPressed() {
        Activity activity = getActivity();

        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        switch(option){
            case 0:
                activity.getSupportActionBar().setTitle(R.string.app_name);
                activity.getSupportActionBar().setSubtitle(R.string.nav_ads);
                break;
            case 1:
                activity.getSupportActionBar().setTitle(R.string.app_name);
                activity.getSupportActionBar().setSubtitle(R.string.nav_promo);
                break;
        }
    }

    public void updateBottonNavigation(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_category:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_muni:
                        viewPager.setCurrentItem(1);
                        break;
                }
                return false;
            }
        });
    }

    public void updateViewPage(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(true);
                }
                else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(false);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void createAdapter (ViewPager vp, int option, Activity activity){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        switch (option){
            case 0:
                adapter.addFragment(FragmentBasic.getInstace(0));
                adapter.addFragment(FragmentBasic.getInstace(1));
                vp.setAdapter(adapter);
                break;
            case 1:
                adapter.addFragment(FragmentBasic.getInstace(2));
                adapter.addFragment(FragmentBasic.getInstace(3));
                vp.setAdapter(adapter);
                break;
        }

    }



}
