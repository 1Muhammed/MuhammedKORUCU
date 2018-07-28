package com.korucu.muhammedkorucu;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText txtyazismalar;
    EditText mesaj;
    Button btngonder;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference childReference;
    DatabaseReference childmesaj;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtyazismalar  = findViewById(R.id.txtyazismalar);
        mesaj = findViewById(R.id.txtmesaj);
        btngonder = findViewById(R.id.btngonder);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Mesajlar");
        childReference = databaseReference.child("21072018");
        btngonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gonder();
            }
        });

        childReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String gonderen = dataSnapshot.getKey().toString().split(":")[0];

                String gelenmetin = dataSnapshot.getValue(String.class);
                String duzenlenmismetin = gonderen+ " in Mesajı ...: "+ gelenmetin+" \n";
                txtyazismalar.setText(txtyazismalar.getText()+duzenlenmismetin);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public  void gonder(){
        i++;
        String zaman = Calendar.getInstance().getTime()+"";
        String gonderilecekmesaj = mesaj.getText().toString();
        childmesaj = childReference.child("Muhammed:"+zaman);
        childmesaj.setValue(gonderilecekmesaj);
mesaj.setText("");
    }
}
