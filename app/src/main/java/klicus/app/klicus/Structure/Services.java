package klicus.app.klicus.Structure;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import klicus.app.klicus.Ads.Ads;
import klicus.app.klicus.Ads.RecyclerViewAds;
import klicus.app.klicus.Cate_Loca_Type;
import klicus.app.klicus.EventJobs.ContentE;
import klicus.app.klicus.EventJobs.ContentJ;
import klicus.app.klicus.EventJobs.Event;
import klicus.app.klicus.EventJobs.Job;
import klicus.app.klicus.EventJobs.RecyclerViewEvent;
import klicus.app.klicus.EventJobs.RecyclerViewJobs;
import klicus.app.klicus.MainR.RecyclerViewMain;
import klicus.app.klicus.Promo.Promo;
import klicus.app.klicus.Promo.RecyclerViewPromo;
import klicus.app.klicus.R;

/**
 * Created by Sol Mayra on 10/03/2018.
 */

public class Services {

    public Services(){}

    public ArrayList<Cate_Loca_Type> loadSpinner(DatabaseReference db, final ArrayList<Cate_Loca_Type> array, final MaterialSpinner spinner, final Activity activity){
        array.add(new Cate_Loca_Type(0, "Seleccione una opcion. "));
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    int id = Integer.parseInt(child.child("ID").getValue().toString());
                    String name = child.child("NAME").getValue().toString();
                    array.add(new Cate_Loca_Type(id, name));
                }
                ArrayAdapter<Cate_Loca_Type> adapter = new ArrayAdapter<Cate_Loca_Type>(activity.getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, array);
                spinner.setAdapter(adapter);
                spinner.setSelectedIndex(0);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity.getApplicationContext(), error.toException().toString(), Toast.LENGTH_LONG);
            }
        });
        return array;
    }

    public void loadPromo(DatabaseReference db, final int p, final String name, final ArrayList<Promo> array, final RecyclerView rv, final Activity activity, final FragmentManager fm){
        array.clear();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (Integer.parseInt(child.child(name).getValue().toString()) == p) {
                        String title = child.child("TITLE").getValue().toString();
                        String subtitle = child.child("SUBTITLE").getValue().toString();
                        String description = child.child("DESCRIPTION").getValue().toString();
                        String image = child.child("IMAGE_URL").getValue().toString();
                        array.add(new Promo(description, subtitle, title, image));

                        Collections.sort(array, new Comparator<Promo>() {
                            @Override
                            public int compare(Promo P1, Promo P2) {
                                return P1.getTITLE().compareTo(P2.getTITLE());
                            }
                        });

                        RecyclerViewPromo rvp= new RecyclerViewPromo(activity.getApplicationContext(), array);
                        rv.setAdapter(rvp);
                        rv.getAdapter().notifyDataSetChanged();
                    } else{

                    }
                }
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity.getApplicationContext(), error.toException().toString(), Toast.LENGTH_LONG);
            }
        });
    }

    public void loadAds(DatabaseReference db, final int p, final String name, final ArrayList<Ads> array, final RecyclerView rv, final Activity activity, final FragmentManager fm){
        array.clear();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    if(Integer.parseInt(child.child(name).getValue().toString())== p){

                        String name = child.child("NAME").getValue().toString();
                        String propiet = child.child("PRO").getValue().toString();
                        String description = child.child("DESCRIPTION").getValue().toString();
                        String dire = child.child("DIRE").getValue().toString();
                        String domicilio = child.child("DOMICILIO").getValue().toString();
                        String email = child.child("EMAIL").getValue().toString();
                        String web = child.child("WEB").getValue().toString();
                        String fb = child.child("FB").getValue().toString();
                        String ig = child.child("IG").getValue().toString();
                        String time = child.child("TIME").getValue().toString();
                        String tel = child.child("TEL").getValue().toString();
                        String cel = child.child("CEL").getValue().toString();
                        String img = child.child("IMAGE_URL").getValue().toString();

                        Log.i("cantidad", img);
                        Log.i("cantidad",  String.valueOf(child.child("GALLERY").getChildrenCount()));

                        ArrayList<String> listGallery = new ArrayList<>();

                        for (DataSnapshot im :  child.child("GALLERY").getChildren()) {
                            listGallery.add(im.getValue().toString());
                        }

                        array.add(new Ads(name,propiet, description,dire, domicilio, email, web, fb, ig, time, tel, cel, img, listGallery));

                        Collections.sort(array, new Comparator<Ads>() {
                            public int compare(Ads a1, Ads a2) {
                                return a1.getNAME().compareTo(a2.getNAME());
                            }
                        });

                        RecyclerViewAds rva = new RecyclerViewAds(activity.getApplicationContext(), array);
                        rva.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(activity.getApplicationContext(), ContentFB.class);
                                intent.putExtra("ads", array.get(rv.getChildAdapterPosition(v)));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.getApplicationContext().startActivity(intent);
                            }
                        });
                        rv.setAdapter(rva);
                        rv.getAdapter().notifyDataSetChanged();
                    } else{

                    }
                }
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity.getApplicationContext(), error.toException().toString(), Toast.LENGTH_LONG);
            }
        });

    }

    public void loadEvents(final DatabaseReference db, final int p, final String name, final ArrayList<Event> array, final RecyclerView rv, final Activity activity, final FragmentManager fm){
        array.clear();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (Integer.parseInt(child.child(name).getValue().toString()) == p) {
                        String title = child.child("TITLE").getValue().toString();
                        String subtitle = child.child("SUBTITLE").getValue().toString();
                        String date = child.child("DATE").getValue().toString();
                        String time = child.child("TIME").getValue().toString();
                        String place = child.child("PLACE").getValue().toString();
                        String contact = child.child("CONTACT").getValue().toString();
                        String image = child.child("IMAGE").getValue().toString();


                        ArrayList<String> listGallery = new ArrayList<>();
                        Log.i("cantidad",  String.valueOf(child.child("GALLERY").getChildrenCount()));
                        for (DataSnapshot im :  child.child("GALLERY").getChildren()) {
                            listGallery.add(im.getValue().toString());
                        }

                        array.add(new Event(title,subtitle,date,time,place,contact,image, listGallery));

                        Collections.sort(array, new Comparator<Event>() {
                            @Override
                            public int compare(Event E1, Event E2) {
                                return E1.getTITLE().compareTo(E2.getTITLE());
                            }
                        });

                        RecyclerViewEvent rvp= new RecyclerViewEvent(activity.getApplicationContext(), array);
                        rvp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(activity.getApplicationContext(), ContentE.class);
                                intent.putExtra("event", array.get(rv.getChildAdapterPosition(v)));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.getApplicationContext().startActivity(intent);
                            }
                        });
                        rv.setAdapter(rvp);
                        rv.getAdapter().notifyDataSetChanged();
                    } else{

                    }
                }
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity.getApplicationContext(), error.toException().toString(), Toast.LENGTH_LONG);
            }
        });
    }


    public void loadJobs(DatabaseReference db, final int p, final String name, final ArrayList<Job> array, final RecyclerView rv, final Activity activity, final FragmentManager fm){
        array.clear();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (Integer.parseInt(child.child(name).getValue().toString()) == p) {
                        String title = child.child("TITLE").getValue().toString();
                        String subtitle = child.child("SUBTITLE").getValue().toString();
                        String description = child.child("DESCRIPTION").getValue().toString();
                        String place = child.child("PLACE").getValue().toString();
                        String tel = child.child("TEL").getValue().toString();
                        String email = child.child("EMAIL").getValue().toString();
                        String image = child.child("IMAGE").getValue().toString();
                        array.add(new Job(title,subtitle,description,place,tel,email,image));

                        Collections.sort(array, new Comparator<Job>() {
                            @Override
                            public int compare(Job J1, Job J2) {
                                return J1.getTITLE().compareTo(J2.getTITLE());
                            }
                        });

                        RecyclerViewJobs rvp= new RecyclerViewJobs(activity.getApplicationContext(), array);
                        rvp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(activity.getApplicationContext(), ContentJ.class);
                                intent.putExtra("job", array.get(rv.getChildAdapterPosition(v)));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.getApplicationContext().startActivity(intent);
                            }
                        });
                        rv.setAdapter(rvp);
                        rv.getAdapter().notifyDataSetChanged();
                    } else{

                    }
                }
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity.getApplicationContext(), error.toException().toString(), Toast.LENGTH_LONG);
            }
        });
    }


    public RecyclerViewMain loadMain(final DatabaseReference db, final ArrayList<Ads> array, final RecyclerView rv, final Activity activity, final android.support.v7.widget.SearchView sv){
        final RecyclerViewMain[] rvm = new RecyclerViewMain[1];
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    String name = child.child("NAME").getValue().toString();
                    String propiet = child.child("PRO").getValue().toString();
                    String description = child.child("DESCRIPTION").getValue().toString();
                    String dire = child.child("DIRE").getValue().toString();
                    String domicilio = child.child("DOMICILIO").getValue().toString();
                    String email = child.child("EMAIL").getValue().toString();
                    String web = child.child("WEB").getValue().toString();
                    String fb = child.child("FB").getValue().toString();
                    String ig = child.child("IG").getValue().toString();
                    String time = child.child("TIME").getValue().toString();
                    String tel = child.child("TEL").getValue().toString();
                    String cel = child.child("CEL").getValue().toString();
                    String img = child.child("IMAGE_URL").getValue().toString();

                    ArrayList<String> listGallery = new ArrayList<>();
                    Log.i("cantidad",  String.valueOf(child.child("GALLERY").getChildrenCount()));
                    for (DataSnapshot im :  child.child("GALLERY").getChildren()) {
                        listGallery.add(im.getValue().toString());
                    }

                    array.add(new Ads(name,propiet, description,dire, domicilio, email, web, fb, ig, time, tel, cel, img, listGallery));

                    Collections.sort(array, new Comparator<Ads>() {
                        public int compare(Ads a1, Ads a2) {
                            return a1.getNAME().compareTo(a2.getNAME());
                        }
                    });

                    rvm[0] = new RecyclerViewMain(activity.getApplicationContext(), array);
                    rv.setAdapter(rvm[0]);
                    rvm[0].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity.getApplicationContext(), ContentFB.class);
                            intent.putExtra("ads", array.get(rv.getChildAdapterPosition(v)));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.getApplicationContext().startActivity(intent);
                        }
                    });
                    rv.getAdapter().notifyDataSetChanged();


                }
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new GridLayoutManager(activity.getApplicationContext(), 2));


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity.getApplicationContext(), error.toException().toString(), Toast.LENGTH_LONG);
            }
        });
        return rvm[0];
    }


}
