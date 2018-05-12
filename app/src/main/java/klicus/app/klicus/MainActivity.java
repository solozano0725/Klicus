package klicus.app.klicus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import klicus.app.klicus.Ads.Ads;
import klicus.app.klicus.EventJobs.BasicFragment2;
import klicus.app.klicus.MainR.RecyclerViewMain;
import klicus.app.klicus.Structure.BasicFragmentBV;
import klicus.app.klicus.Structure.Services;
import klicus.app.klicus.UsAndDev.FragmentDev;
import klicus.app.klicus.UsAndDev.FragmentUs;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase db;
    RecyclerView recyclerView;
    ArrayList<Ads> arrayAds;
    private int count = 0;
    MenuItem searchItem;
    SearchView searchView;
    RecyclerViewMain rvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView txt = navigationView.getHeaderView(0).findViewById(R.id.txtWebOf);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.link_website)));
                startActivity(i);
            }
        });
        recyclerView = findViewById(R.id.rvMain);
        arrayAds = new ArrayList<>();
        db = FirebaseDatabase.getInstance("https://klicus-4b8a7.firebaseio.com");

        Services services = new Services();
        rvm = services.loadMain(db.getReference("ADS"), arrayAds, recyclerView, this, searchView);

    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            super.onBackPressed();
        } else{
            getSupportFragmentManager().popBackStack();
           setTitleSubtitle();
        }

    }

    public void setTitleSubtitle(){
        try{
            if(!getSupportActionBar().getSubtitle().toString().equalsIgnoreCase(getString(R.string.nav_main))){
                getSupportActionBar().setTitle(R.string.app_name);
                getSupportActionBar().setSubtitle(R.string.nav_main);
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_ads) {
            count++;
            fragment = BasicFragmentBV.getInstace(0);
        } else if (id == R.id.nav_promo) {
            count++;
            fragment = BasicFragmentBV.getInstace(1);
        }else if (id == R.id.nav_gifts) {
            //fragment = new NoContent();
            count++;
            fragment = BasicFragment2.getInstace(0);
        } else if (id == R.id.nav_jobs) {
            //fragment = new NoContent();
            count++;
            fragment = BasicFragment2.getInstace(1);
        } else if (id == R.id.nav_us) {
            count++;
            fragment = new FragmentUs();
        } else if (id == R.id.nav_dev) {
            count++;
            fragment = new FragmentDev();
        }

        if(count>0){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).addToBackStack(null).commit();
        } else{
            getSupportFragmentManager().beginTransaction().add(R.id.content_main, fragment).addToBackStack(null).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint(getText(R.string.search));
        changeAdapter(rvm);
        return super.onCreateOptionsMenu(menu);
    }

    public void changeAdapter(RecyclerViewMain rvm){
        searchView.setQueryHint(getText(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(MainActivity.this, "hola", Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {


                Toast.makeText(MainActivity.this, "holaaa", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


}
