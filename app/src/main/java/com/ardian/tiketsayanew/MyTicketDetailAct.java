package com.ardian.tiketsayanew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MyTicketDetailAct extends AppCompatActivity {
    DatabaseReference reference;
    LinearLayout btn_back;
    TextView xnama_wisata,xlokasi,xdate_wisata,xtime_wisata,xketentuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_detail);

        xnama_wisata = findViewById(R.id.xnama_wisata);
        xlokasi = findViewById(R.id.xlokasi);
        xdate_wisata = findViewById(R.id.xdate_wisata);
        xtime_wisata = findViewById(R.id.xtime_wisata);
        xketentuan = findViewById(R.id.xketentuan);
        btn_back = findViewById(R.id.btn_back);

        Bundle bundle = getIntent().getExtras();
        final String nama_wisata_baru = Objects.requireNonNull(bundle).getString("nama_wisata");

        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(Objects.requireNonNull(nama_wisata_baru));
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                xnama_wisata.setText(Objects.requireNonNull(dataSnapshot.child("nama_wisata").getValue()).toString());
                xlokasi.setText(Objects.requireNonNull(dataSnapshot.child("lokasi").getValue()).toString());
                xdate_wisata.setText(Objects.requireNonNull(dataSnapshot.child("date_wisata").getValue()).toString());
                xtime_wisata.setText(Objects.requireNonNull(dataSnapshot.child("time_wisata").getValue()).toString());
                xketentuan.setText(Objects.requireNonNull(dataSnapshot.child("ketentuan").getValue()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
