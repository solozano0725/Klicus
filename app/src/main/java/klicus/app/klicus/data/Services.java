package klicus.app.klicus.data;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import klicus.app.klicus.entity.Ads;
import klicus.app.klicus.entity.Clasif;
import klicus.app.klicus.entity.Event;
import klicus.app.klicus.entity.Job;
import klicus.app.klicus.entity.Promo;
import klicus.app.klicus.structure.all.ads.DetailsAds;
import klicus.app.klicus.structure.all.ads.RecyclerViewAds;
import klicus.app.klicus.structure.all.clasif.DetailsClasif;
import klicus.app.klicus.structure.all.clasif.RecyclerViewClasif;
import klicus.app.klicus.structure.all.event.DetailsEvent;
import klicus.app.klicus.structure.all.event.RecyclerViewEvent;
import klicus.app.klicus.structure.all.job.DetailsJob;
import klicus.app.klicus.structure.all.job.RecyclerViewJobs;
import klicus.app.klicus.structure.home.DetailsMain;
import klicus.app.klicus.structure.home.RecyclerViewMain;

import klicus.app.klicus.entity.News;
import klicus.app.klicus.structure.all.promo.RecyclerViewPromo;

import static klicus.app.klicus.commons.CommonConstants.arrayAds;
import static klicus.app.klicus.commons.CommonConstants.arrayClasif;
import static klicus.app.klicus.commons.CommonConstants.arrayEvent;
import static klicus.app.klicus.commons.CommonConstants.arrayJob;
import static klicus.app.klicus.commons.CommonConstants.arrayNews;
import static klicus.app.klicus.commons.CommonConstants.arrayPromo;

/**
 * Created by Sol Mayra on 10/03/2018.
 */

public class Services {

    public HashMap<Integer, String> arrayLocation = new HashMap<>();

    public int ID_LOCATION = 0;

    public Services() {
        arrayLocation.put(1,"OCAÑA");
        arrayLocation.put(2,"ABREGO");
        arrayLocation.put(3,"CONVENCION ");
        arrayLocation.put(4,"LA PLAYA");
        arrayLocation.put(3,"RIO DE ORO");
    }

    public void loadNews(DatabaseReference db, final RecyclerView rv, final Activity activity) {
        final ArrayList<News> generalArray = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String date = child.child(arrayNews[0]).getValue().toString();
                    String description = child.child(arrayNews[1]).getValue().toString();
                    ArrayList<String> listGallery = new ArrayList<>();
                    for (DataSnapshot im : child.child(arrayNews[2]).getChildren()) {
                        listGallery.add(im.getValue().toString());
                    }
                    //String gallery = child.child(arrayNews[2]).getValue().toString();
                    String id_location = child.child(arrayNews[3]).getValue().toString();
                    String image = child.child(arrayNews[4]).getValue().toString();
                    String subtitle = child.child(arrayNews[5]).getValue().toString();
                    String title = child.child(arrayNews[6]).getValue().toString();
                    generalArray.add(new News(date, description, listGallery, Integer.parseInt(id_location), image, subtitle, title));

                    Collections.sort(generalArray, new Comparator<News>() {
                        @Override
                        public int compare(News o1, News o2) {
                            return o1.getTITLE().compareTo(o2.getTITLE());
                        }
                    });

                    RecyclerViewMain rvm = new RecyclerViewMain(activity.getApplicationContext(), generalArray);
                    rvm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity.getApplicationContext(), DetailsMain.class);
                            intent.putExtra("news", generalArray.get(rv.getChildAdapterPosition(v)));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.getApplicationContext().startActivity(intent);
                        }
                    });
                    rv.setAdapter(rvm);
                    rv.getAdapter().notifyDataSetChanged();
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

    public void loadPromo(DatabaseReference db, final RecyclerView rv, final Activity activity, final String search) {
        final ArrayList<Promo> generalArray = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    String title = child.child(arrayPromo[0]).getValue().toString();
                    String subtitle = child.child(arrayPromo[1]).getValue().toString();
                    String description = child.child(arrayPromo[2]).getValue().toString();
                    String image = child.child(arrayPromo[3]).getValue().toString();
                    generalArray.add(new Promo(description, subtitle, title, image));

                    Collections.sort(generalArray, new Comparator<Promo>() {
                        @Override
                        public int compare(Promo P1, Promo P2) {
                            return P1.getTITLE().compareTo(P2.getTITLE());
                        }
                    });

                    if(!search.equalsIgnoreCase("null")){
                        for(int i =0; i<generalArray.size(); i++){
                            if(!generalArray.get(i).getSUBTITLE().toLowerCase().contains(search) &&
                            !generalArray.get(i).getDESCRIPTION().toLowerCase().contains(search)){
                                generalArray.remove(i);
                        }
                    }

                    RecyclerViewPromo rvp = new RecyclerViewPromo(activity.getApplicationContext(), generalArray);
                    rv.setAdapter(rvp);
                    rv.getAdapter().notifyDataSetChanged();
                }
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
            }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity.getApplicationContext(), error.toException().toString(), Toast.LENGTH_LONG);
            }
        });
    }

    public void loadAds(DatabaseReference db, final RecyclerView rv, final Activity activity, final String search) {
        final ArrayList<Ads> generalArray = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String name = child.child(arrayAds[0]).getValue().toString();
                    String propiet = child.child(arrayAds[1]).getValue().toString();
                    String description = child.child(arrayAds[2]).getValue().toString();
                    String dire = child.child(arrayAds[3]).getValue().toString();
                    String domicilio = child.child(arrayAds[4]).getValue().toString();
                    String email = child.child(arrayAds[5]).getValue().toString();
                    String web = child.child(arrayAds[6]).getValue().toString();
                    String fb = child.child(arrayAds[7]).getValue().toString();
                    String ig = child.child(arrayAds[8]).getValue().toString();
                    String time = child.child(arrayAds[9]).getValue().toString();
                    String tel = child.child(arrayAds[10]).getValue().toString();
                    String cel = child.child(arrayAds[11]).getValue().toString();
                    String img = child.child(arrayAds[12]).getValue().toString();
                    int location = Integer.parseInt(child.child("ID_LOCATION").getValue().toString());

                    ArrayList<String> listGallery = new ArrayList<>();

                    for (DataSnapshot im : child.child(arrayAds[13]).getChildren()) {
                        listGallery.add(im.getValue().toString());
                    }

                    generalArray.add(new Ads(name, propiet, description, dire, domicilio, email, web, fb, ig, time, tel, cel, img, listGallery, location));

                    Collections.sort(generalArray, new Comparator<Ads>() {
                        public int compare(Ads a1, Ads a2) {
                            return a1.getNAME().compareTo(a2.getNAME());
                        }
                    });


                    if(!search.equalsIgnoreCase("null")){
                        for(int i =0; i<generalArray.size(); i++){
                            if(!generalArray.get(i).getNAME().toLowerCase().contains(search) &&
                                    !generalArray.get(i).getDESCRIPTION().toLowerCase().contains(search)){
                                generalArray.remove(i);
                            }
                        }
                    }

                    RecyclerViewAds rva = new RecyclerViewAds(activity.getApplicationContext(), generalArray);
                    rva.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity.getApplicationContext(), DetailsAds.class);
                            intent.putExtra("ads", generalArray.get(rv.getChildAdapterPosition(v)));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.getApplicationContext().startActivity(intent);
                        }
                    });
                    rv.setAdapter(rva);
                    rv.getAdapter().notifyDataSetChanged();
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

    public void loadEvents(DatabaseReference db, final RecyclerView rv, final Activity activity, final String search) {
        final ArrayList<Event> generalArray = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String title = child.child(arrayEvent[0]).getValue().toString();
                    String subtitle = child.child(arrayEvent[1]).getValue().toString();
                    String date = child.child(arrayEvent[2]).getValue().toString();
                    String time = child.child(arrayEvent[3]).getValue().toString();
                    String place = child.child(arrayEvent[4]).getValue().toString();
                    String contact = child.child(arrayEvent[5]).getValue().toString();
                    String image = child.child(arrayEvent[6]).getValue().toString();

                    ArrayList<String> listGallery = new ArrayList<>();
                    Log.i("cantidad", String.valueOf(child.child(arrayEvent[7]).getChildrenCount()));
                    for (DataSnapshot im : child.child(arrayEvent[7]).getChildren()) {
                        listGallery.add(im.getValue().toString());
                    }

                    generalArray.add(new Event(title, subtitle, date, time, place, contact, image, listGallery));

                    Collections.sort(generalArray, new Comparator<Event>() {
                        @Override
                        public int compare(Event E1, Event E2) {
                            return E1.getTITLE().compareTo(E2.getTITLE());
                        }
                    });

                    if(!search.equalsIgnoreCase("null")){
                        for(int i =0; i<generalArray.size(); i++){
                            if(!generalArray.get(i).getTITLE().toLowerCase().contains(search) &&
                                    !generalArray.get(i).getSUBTITLE().toLowerCase().contains(search)){
                                generalArray.remove(i);
                            }
                        }
                    }

                    RecyclerViewEvent rvp = new RecyclerViewEvent(activity.getApplicationContext(), generalArray);
                    rvp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity.getApplicationContext(), DetailsEvent.class);
                            intent.putExtra("event", generalArray.get(rv.getChildAdapterPosition(v)));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.getApplicationContext().startActivity(intent);
                        }
                    });
                    rv.setAdapter(rvp);
                    rv.getAdapter().notifyDataSetChanged();

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

    public void loadClasif(DatabaseReference db, final RecyclerView rv, final Activity activity, final String search) {
        final ArrayList<Clasif> generalArray = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String title = child.child(arrayClasif[0]).getValue().toString();
                    String subtitle = child.child(arrayClasif[1]).getValue().toString();
                    String place = child.child(arrayClasif[2]).getValue().toString();
                    String description = child.child(arrayClasif[3]).getValue().toString();
                    String date = child.child(arrayClasif[4]).getValue().toString();
                    String image = child.child(arrayClasif[5]).getValue().toString();

                    ArrayList<String> listGallery = new ArrayList<>();
                    Log.i("cantidad", String.valueOf(child.child(arrayClasif[6]).getChildrenCount()));
                    for (DataSnapshot im : child.child(arrayClasif[6]).getChildren()) {
                        listGallery.add(im.getValue().toString());
                    }
                    String contact = child.child(arrayClasif[7]).getValue().toString();

                    generalArray.add(new Clasif(title, subtitle, place, description, date, image, listGallery, contact));

                    Collections.sort(generalArray, new Comparator<Clasif>() {
                        @Override
                        public int compare(Clasif C1, Clasif C2) {
                            return C1.getTITLE().compareTo(C2.getTITLE());
                        }
                    });

                    if(!search.equalsIgnoreCase("null")){
                        for(int i =0; i<generalArray.size(); i++){
                            if(!generalArray.get(i).getTITLE().toLowerCase().contains(search) &&
                                    !generalArray.get(i).getDESCRIPTION().toLowerCase().contains(search)){
                                generalArray.remove(i);
                            }
                        }
                    }

                    RecyclerViewClasif rvc = new RecyclerViewClasif(activity.getApplicationContext(), generalArray);
                    rvc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity.getApplicationContext(), DetailsClasif.class);
                            intent.putExtra("clasi", generalArray.get(rv.getChildAdapterPosition(v)));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.getApplicationContext().startActivity(intent);
                        }
                    });
                    rv.setAdapter(rvc);
                    rv.getAdapter().notifyDataSetChanged();
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

    public void loadJobs(DatabaseReference db, final RecyclerView rv, final Activity activity, final String search) {
        final ArrayList<Job> generalArray = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    String title = child.child(arrayJob[0]).getValue().toString();
                    String subtitle = child.child(arrayJob[1]).getValue().toString();
                    String description = child.child(arrayJob[2]).getValue().toString();
                    String place = child.child(arrayJob[3]).getValue().toString();
                    String tel = child.child(arrayJob[4]).getValue().toString();
                    String email = child.child(arrayJob[5]).getValue().toString();
                    String image = child.child(arrayJob[6]).getValue().toString();
                    generalArray.add(new Job(title, subtitle, description, place, tel, email, image));

                    Collections.sort(generalArray, new Comparator<Job>() {
                        @Override
                        public int compare(Job J1, Job J2) {
                            return J1.getTITLE().compareTo(J2.getTITLE());
                        }
                    });

                    if(!search.equalsIgnoreCase("null")){
                        for(int i =0; i<generalArray.size(); i++){
                            if(!generalArray.get(i).getTITLE().toLowerCase().contains(search) &&
                            !generalArray.get(i).getDESCRIPTION().toLowerCase().contains(search)){
                                generalArray.remove(i);
                            }
                        }
                    }

                    RecyclerViewJobs rvp = new RecyclerViewJobs(activity.getApplicationContext(), generalArray);
                    rvp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity.getApplicationContext(), DetailsJob.class);
                            intent.putExtra("job", generalArray.get(rv.getChildAdapterPosition(v)));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.getApplicationContext().startActivity(intent);
                        }
                    });
                    rv.setAdapter(rvp);
                    rv.getAdapter().notifyDataSetChanged();
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

}
