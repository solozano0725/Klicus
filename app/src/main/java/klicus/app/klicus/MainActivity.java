package klicus.app.klicus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import klicus.app.klicus.commons.CommonUtil;
import klicus.app.klicus.data.Services;
import klicus.app.klicus.entity.News;
import klicus.app.klicus.structure.FragmentG;

import static klicus.app.klicus.commons.CommonConstants.arrayDBCategory;
import static klicus.app.klicus.commons.CommonConstants.db;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<News> arrayList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setPadding(5,15,5,10);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setSubtitle();

        recyclerView = findViewById(R.id.rvMain);
        arrayList = new ArrayList<>();
        Services s = new Services();

        s.loadNews(db.getReference(arrayDBCategory[4]), recyclerView, this);

    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            super.onBackPressed();
         } else{
            getSupportFragmentManager().popBackStack();
            CommonUtil.setTitleSubtitle(getSupportActionBar(), this, getResources().getString(R.string.nav_home));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_home) {
           // if(getSupportFragmentManager().getBackStackEntryCount()>0){
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                startActivity(new Intent(this, MainActivity.class));
            //}
        } else if (id == R.id.nav_ads) {
            fragment = FragmentG.getInstance(0, getResources().getString(R.string.nav_ads));

        } else if (id == R.id.nav_promo) {
            fragment = FragmentG.getInstance(1, getResources().getString(R.string.nav_promo));

        } else if (id == R.id.nav_event) {
            fragment = FragmentG.getInstance(2, getResources().getString(R.string.nav_event));

        } else if (id == R.id.nav_news) {
            fragment = FragmentG.getInstance(3, getResources().getString(R.string.nav_news));

        } else if (id == R.id.nav_jobs) {
            fragment = FragmentG.getInstance(4, getResources().getString(R.string.nav_jobs));

        } else if (id == R.id.nav_signin) {
            fragment = new FragmentRegist();

        } else if (id == R.id.nav_aboutus) {
            fragment = new FragmentUs();
        }

        if(fragment!=null){
            if(getSupportFragmentManager().getBackStackEntryCount()>0){
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).addToBackStack(null).commit();
            } else{
                getSupportFragmentManager().beginTransaction().add(R.id.content_main, fragment).addToBackStack(null).commit();
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setSubtitle(){
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.nav_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}
