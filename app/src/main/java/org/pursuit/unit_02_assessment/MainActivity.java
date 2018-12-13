package org.pursuit.unit_02_assessment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText inputEditText;
    private Button submitButton;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        // TODO: Set an OnClickListener for the FloatingActionButton "fab" object, and in the onClick method, add an implicit intent to "send to" a mail app an email message to "mail.pursuit.org", with the subject "Email from Pursuit", and the body text of "This is my text!".

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMail = new Intent(Intent.ACTION_SENDTO);
                intentMail.setType("text/html");
                intentMail.putExtra(Intent.EXTRA_EMAIL,"mailto:mail@pursuit.org");
                intentMail.putExtra(Intent.EXTRA_SUBJECT, "Email from Pursuit");
                intentMail.putExtra(Intent.EXTRA_TEXT, "This is my text!");
                startActivity(Intent.createChooser(intentMail, "Send mail..."));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // TODO: Create an instance of the RandomGame class, and a field of type "int" which will store a random number using a method from the RandomGame class. Set the OnClickListener for the Button "submitButton" object.

        final RandomGame randomGame = new RandomGame();

        final int randomGameField = 2;
                //randomGame.getRandomNumber();

        // TODO: In the onClick method of the listener, use methods from the RandomGame class to check if the number in the EditText matches the number in the field above. If they match, send the winning phrase to the next activity called "SecondActivity", with an explicit intent, and an intent extra with the key "result", and the winning phrase as the value. If not, change the TextView "info_textview" text to display the losing phrase.

        submitButton = findViewById(R.id.submit_button);
        inputEditText = findViewById(R.id.number_edittext);
        textView = findViewById(R.id.info_textview);

        if (savedInstanceState != null) {
            SecondActivity.trueString = savedInstanceState.getString("inputText");
            inputEditText.setText(savedInstanceState.getString("inputEditText"));
                textView.setText(savedInstanceState.getString("textView"));

        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEditText.getText().toString().equals(Integer.toString(randomGameField))) {
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("result", randomGame.getStringResult(true));
                    startActivity(intent);
                } else {
                    textView.setText(randomGame.getStringResult(false).toString());
                }
            }
        });

        // TODO: Create another activity called "SecondActivity", and add it to the Android Manifest, adding also the "MainActivity" as its parent activity. Add a TextView to "SecondActivity", with an id of "second_textView", set its height and width to "match_parent". Set its color to black, and set its font to "Cursive".
        // TODO: In the activity "SecondActivity", get the intent extra using the key "result", and use the String value it returns to set the value of the TextView "second_textView".
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toast) {
            // TODO: Write code to handle the "Toast" Option click, and display a Toast to the screen with the text "Hello, Pursuit!".
            Toast.makeText(getApplicationContext(), "Hello, Pursuit!", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        // TODO: If the user selects the "nav_phone" view, add an implicit intent to "dial" the number "2125551212" in the phone app.
        // TODO: If the user selects the "nav_sms" view, add an implicit intent to "send to" the number "2125551212" in the sms text messaging app the message "Welcome to Pursuit!".
        // TODO: If the user selects the "nav_map_location" view, add an implicit intent to "view" the coordinates "0,0?q=40.7429595,-73.94192149999998(Pursuit Android HQ)" in the Google Maps app.

        switch (id) {
            case R.id.nav_phone:
                Uri uriPhone = Uri.parse("tel:2125551212");
                Intent intentPhone = new Intent(Intent.ACTION_DIAL, uriPhone);
                startActivity(intentPhone);

            case R.id.nav_sms:
                Uri uriText = Uri.parse("sms:2125551212");
                Intent intentText = new Intent(Intent.ACTION_SEND, uriText);
                startActivity(intentText);

            case R.id.nav_map_location:
                Uri uriMap = Uri.parse("geo:0,0?q=40.7429595,-73.94192149999998(Pursuit Android HQ)");
                Intent intentMap = new Intent(Intent.ACTION_VIEW, uriMap);
                startActivity(intentMap);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // TODO: Use the "onSavedInstanceState" method to save the values of each of the TextViews/EditTexts of both activities prior to orientation change, and set their values in the onCreate method.

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("inputText", SecondActivity.trueString);
        outState.putString("inputEditText", inputEditText.getText().toString());
        Log.e("error","this is an error");
        outState.putString("textView", textView.getText().toString());
        Log.e("error","this is an error");
    }


}
