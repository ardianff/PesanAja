package com.ardian.tiketsayanew;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeAct extends AppCompatActivity {
    LinearLayout btn_ticket_pisa,btn_ticket_torri,btn_ticket_pagoda,btn_ticket_candi
            ,btn_ticket_sphinx,btn_ticket_monas;
    CircleView btn_to_profile;
    boolean doubleBackToExitPressedOnce = false;
    ImageView photo_home_user,Pisa,Paris;
    TextView user_balance,nama_lengkap,bio;
    ViewPager viewPager;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getUsernameLocal();
        viewPager = findViewById(R.id.viewPager);
        btn_to_profile = findViewById(R.id.btn_to_profile);
        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_ticket_torri = findViewById(R.id.btn_ticket_torri);
        btn_ticket_pagoda = findViewById(R.id.btn_ticket_pagoda);
        btn_ticket_candi = findViewById(R.id.btn_ticket_candi);
        btn_ticket_sphinx = findViewById(R.id.btn_ticket_sphinx);
        btn_ticket_monas = findViewById(R.id.btn_ticket_monas);
        photo_home_user = findViewById(R.id.photo_home_user);
        user_balance = findViewById(R.id.user_balance);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);
//        Pisa = findViewById(R.id.pisa);
//        Paris = findViewById(R.id.paris);

        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(Objects.requireNonNull(dataSnapshot.child("nama_lengkap").getValue()).toString());
                bio.setText(Objects.requireNonNull(dataSnapshot.child("bio").getValue()).toString());
                user_balance.setText("US$ "+ Objects.requireNonNull(dataSnapshot.child("user_balance").getValue()).toString());

                Picasso.with(HomeAct.this).load(Objects.requireNonNull(dataSnapshot.child("url_photo_profile").getValue()).toString()).centerCrop()
                        .fit().into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoprofile = new Intent(HomeAct.this, MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });
        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket","Pisa");
                startActivity(gotopisaticket);
            }
        });
        btn_ticket_torri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket","Torri");
                startActivity(gotopisaticket);
            }
        });
        btn_ticket_candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket","Candi");
                startActivity(gotopisaticket);
            }
        });
        btn_ticket_sphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket","Sphinx");
                startActivity(gotopisaticket);
            }
        });
        btn_ticket_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket","Monas");
                startActivity(gotopisaticket);
            }
        });
        btn_ticket_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket","Pagoda");
                startActivity(gotopisaticket);
            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000,4000);
//        Pisa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent webIntent = new Intent(Intent.ACTION_VIEW);
//                webIntent.setData(Uri.parse("https://travel.detik.com/travel-news/d-4610012/10-fakta-dan-sejarah-menara-eiffel-yang-perlu-diketahui"));
//                startActivity(webIntent);
//
//            }
//        });
//        Paris.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent webIntent = new Intent(Intent.ACTION_VIEW);
//                webIntent.setData(Uri.parse("https://www.idntimes.com/science/discovery/lia-89/fakta-menara-pisa-c1c2"));
//                startActivity(webIntent);
//
//            }
//        });

    }
    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            HomeAct.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if (viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });

        }
    }
    @Override
        public void onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
    }
    public void  getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,  MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
