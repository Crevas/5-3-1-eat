package com.crevas.and.go_5_3_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.crevas.and.go_5_3_1.FirebaseUtility.mChoicesId;


public class FinalOptions extends AppCompatActivity {

    public String userKey;

    public Boolean isFinalChoice1;
    public Boolean isFinalChoice2;
    public Boolean isFinalChoice3;
    public Boolean isFinalChoice4;
    public Boolean isFinalChoice5;

    @BindView(R.id.choice1) TextView tvChoice1;
    @BindView(R.id.choice2) TextView tvChoice2;
    @BindView(R.id.choice3) TextView tvChoice3;
    @BindView(R.id.choice4) TextView tvChoice4;
    @BindView(R.id.choice5) TextView tvChoice5;
    @BindView(R.id.maps_button) Button maps_button;


    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_venue_options_display);
        ButterKnife.bind(this);

        tvChoice2.setTextSize(40);
        tvChoice2.setVisibility(View.VISIBLE);

        tvChoice3.setVisibility(View.VISIBLE);
        tvChoice3.setTextSize(20);
        tvChoice3.setClickable(false);
        tvChoice3.setVisibility(View.VISIBLE);

        maps_button.setVisibility(View.VISIBLE);
        maps_button.setContentDescription(getApplicationContext().getString(R.string.maps_button));
        maps_button.setClickable(true);


        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        userKey = mChoicesId;

    }

    @Override
    public void onStart() {
        super.onStart();

        mFirebaseDatabase.child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ChoicesDetail choice1 = dataSnapshot.child("choice1").getValue(ChoicesDetail.class);
                ChoicesDetail choice2 = dataSnapshot.child("choice2").getValue(ChoicesDetail.class);
                ChoicesDetail choice3 = dataSnapshot.child("choice3").getValue(ChoicesDetail.class);
                ChoicesDetail choice4 = dataSnapshot.child("choice4").getValue(ChoicesDetail.class);
                ChoicesDetail choice5 = dataSnapshot.child("choice5").getValue(ChoicesDetail.class);

                String choice1Name = choice1.name;
                String choice2Name = choice2.name;
                String choice3Name = choice3.name;
                String choice4Name = choice4.name;
                String choice5Name = choice5.name;

                String choice1Adress = choice1.adress;
                String choice2Adress = choice1.adress;
                String choice3Adress = choice1.adress;
                String choice4Adress = choice1.adress;
                String choice5Adress = choice1.adress;


                isFinalChoice1 = ThreeOptions.isFinalChoice1;
                isFinalChoice2 = ThreeOptions.isFinalChoice2;
                isFinalChoice3 = ThreeOptions.isFinalChoice3;
                isFinalChoice4 = ThreeOptions.isFinalChoice4;
                isFinalChoice5 = ThreeOptions.isFinalChoice5;


                tvChoice1.setClickable(false);
                tvChoice2.setClickable(false);
                tvChoice3.setClickable(false);
                tvChoice4.setClickable(false);
                tvChoice5.setClickable(false);


                if (isFinalChoice1) {
                    tvChoice2.setText(choice1Name);
                    tvChoice2.setContentDescription(choice1Name);
                    tvChoice3.setContentDescription(choice1Adress);
                    tvChoice3.setText(choice1Adress);
                    FirebaseUtility.setFinalChoice(choice1Name, choice1Adress);
                }
                if (isFinalChoice2) {
                    tvChoice2.setText(choice2Name);
                    tvChoice2.setContentDescription(choice2Name);
                    tvChoice3.setContentDescription(choice2Adress);
                    tvChoice3.setText(choice2Adress);
                    FirebaseUtility.setFinalChoice(choice2Name, choice2Adress);
                }
                if (isFinalChoice3) {
                    tvChoice2.setText(choice3Name);
                    tvChoice2.setContentDescription(choice3Name);
                    tvChoice3.setContentDescription(choice3Adress);
                    tvChoice3.setText(choice3Adress);
                    FirebaseUtility.setFinalChoice(choice3Name, choice3Adress);
                }
                if (isFinalChoice4) {
                    tvChoice2.setText(choice4Name);
                    tvChoice3.setText(choice4Adress);
                    tvChoice3.setContentDescription(choice4Adress);
                    tvChoice2.setContentDescription(choice4Name);
                    FirebaseUtility.setFinalChoice(choice4Name, choice4Adress);
                }
                if (isFinalChoice5) {
                    tvChoice2.setText(choice5Name);
                    tvChoice3.setText(choice5Adress);
                    tvChoice3.setContentDescription(choice5Adress);
                    tvChoice2.setContentDescription(choice5Name);
                    FirebaseUtility.setFinalChoice(choice5Name, choice5Adress);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up UpdateVenueButton, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void venueOptions5(View view) {
        Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="
                + tvChoice3.getText().toString()));
        if (geoIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(geoIntent);
        } else {
            Toast.makeText(FinalOptions.this, R.string.final_venue_no_map, Toast.LENGTH_LONG).show();
        }


    }

}