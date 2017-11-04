package com.therichclass.marquant.qwikdial;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        }

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exi) {
            finish();
            moveTaskToBack(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.rate) {
            // Handle the rate fragment
        }
        if (id == R.id.home) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                    .replace(R.id.content_frame, homeFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.mtn) {
            NetworkMtnFragment mtnFragment = new NetworkMtnFragment();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                    .replace(R.id.content_frame, mtnFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.glo) {
            NetworkGloFragment gloFragment = new NetworkGloFragment();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                    .replace(R.id.content_frame, gloFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.etisalat) {
            NetworkEtisalatFragment etisalatFragment = new NetworkEtisalatFragment();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                    .replace(R.id.content_frame, etisalatFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.airtel) {
            NetworkAirtelFragment airtelFragment = new NetworkAirtelFragment();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                    .replace(R.id.content_frame, airtelFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.contact) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:slywestmarquant@therichclass.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT,"");
            intent.putExtra(Intent.EXTRA_TEXT,"");
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivity(intent);
            }
        }
        else if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://www.slywestmarquant@therichclass.com,and don't forget to drop your  review on playStore");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else if (id == R.id.about) {
            final Dialog d = new Dialog(this);
            d.setContentView(R.layout.custom_dialog);
            Button connect = (Button) d.findViewById(R.id.dialogButtonOK);

            connect.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    d.dismiss();
                }
            });
            d.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mtncard1(View view) {
        try {
            String shwdatabal = "*131*4" + "#";
            Intent showbal = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwdatabal, null));
            startActivity(showbal);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void mtncard3(View view) {
        try {
            String mtnborow = "*606" + "#";
            Intent showborow = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mtnborow, null));
            startActivity(showborow);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void mtncard5(View view) {
        try {
            String shwbalance = "*556" + "#";
            Intent bal = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwbalance, null));
            startActivity(bal);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void mtncard6(View view) {
        try {
            String shwno = "*663" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void mtncard2(View view) {
        NetworkMtnSub mtnSub = new NetworkMtnSub();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                .replace(R.id.content_frame, mtnSub)
                .addToBackStack(null)
                .commit();
    }

    public void mtncard4(View v) {
        RechargeFragmentMtn fragmentMtn = new RechargeFragmentMtn();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                .replace(R.id.content_frame, fragmentMtn)
                .addToBackStack(null)
                .commit();
    }

    public void submit(View view) {
        EditText cardpin = (EditText) findViewById(R.id.pintext);
        String number = cardpin.getText().toString();
        String nuumber = "*555*" + number + "#";
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", nuumber, null));
        startActivity(i);

    }

    public void mtncard7(View view) {
        try {
            String shwno = "*123*3*1*1*1" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void mtncard8(View view) {
        try {
            String shwno = "*123*3*1*2*1" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void mtncard9(View view) {
        try {
            String shwno = "*123*3*1*3*1" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void glocard1(View v) {
        try {
            String shwdatabal = "*777*4*4" + "#";
            Intent showbal = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwdatabal, null));
            startActivity(showbal);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void glocard2(View v) {
        NetworkGloSub gloSub = new NetworkGloSub();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                .replace(R.id.content_frame, gloSub)
                .addToBackStack(null)
                .commit();
    }

    public void glocard3(View view) {
        try {
            String globorow = "*321*1" + "#";
            Intent showborow = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", globorow, null));
            startActivity(showborow);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void glocard4(View view) {
        RechargeFragmentGlo fragmentGlo = new RechargeFragmentGlo();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                .replace(R.id.content_frame, fragmentGlo)
                .addToBackStack(null)
                .commit();
    }

    public void submitglo(View view) {
        EditText cardpin = (EditText) findViewById(R.id.pintext);
        String number = cardpin.getText().toString();
        String nuumber = "*123*" + number + "#";
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", nuumber, null));
        startActivity(i);
    }

    public void glocard5(View view) {
        try {
            String shwbalance = "#124*1" + "#";
            Intent bal = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwbalance, null));
            startActivity(bal);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void glocard6(View view) {
        try {
            String shwno = "1244";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void glocard7(View view) {
        try {
            String shwno = "*777*1*1*1*1" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }

    }

    public void glocard8(View view) {
        try {
            String shwno = "*777*1*1*1*2" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }

    }

    public void glocard9(View view) {
        try {
            String shwno = "*777*1*1*2*1" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void airtelcard1(View view) {
        try {
            String shwno = "*223" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void airtelcard2(View v) {
        NetworkAirtelSub airtelSub = new NetworkAirtelSub();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                .replace(R.id.content_frame, airtelSub)
                .addToBackStack(null)
                .commit();
    }

    public void airtelcard3(View view) {
        try {
            String globorow = "*500" + "#";
            Intent showborow = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", globorow, null));
            startActivity(showborow);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }

    public void airtelcard4(View view) {
        RechargeFragmentAirtel fragmentAirtel = new RechargeFragmentAirtel();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                .replace(R.id.content_frame, fragmentAirtel)
                .addToBackStack(null)
                .commit();
    }

    public void submitairtel(View view) {
        EditText cardpin = (EditText) findViewById(R.id.pintext);
        String number = cardpin.getText().toString();
        String nuumber = "*126*" + number + "#";
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", nuumber, null));
        startActivity(i);
    }

    public void airtelcard5(View view) {
        try {
            String shwbalance = "*123" + "#";
            Intent bal = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwbalance, null));
            startActivity(bal);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void airtelcard6(View view) {
        try {
            String shwno = "*121*2*4" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void airtelcard7(View view) {
        try {
            String shwno = "*410" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void airtelcard8(View view) {
        try {
            String shwno = "*417" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void airtelcard9(View view) {
        try {
            String shwno = "*496" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void etisalatcard1(View view) {
        try {
            String shwdatabal = "*229*9" + "#";
            Intent showbal = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwdatabal, null));
            startActivity(showbal);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void etisalatcard2(View v) {
        NetworkEtisalatSub etisalatSub = new NetworkEtisalatSub();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                .replace(R.id.content_frame, etisalatSub)
                .addToBackStack(null)
                .commit();
    }
    public void etisalatcard3(View view) {
        try {
            String etisalatborow = "*665" + "#";
            Intent showborow = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", etisalatborow, null));
            startActivity(showborow);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void etisalatcard4(View view) {
        RechargeFragmentEtisalat fragmentEtisalat = new RechargeFragmentEtisalat();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_in, R.anim.pop_out)
                .replace(R.id.content_frame, fragmentEtisalat)
                .addToBackStack(null)
                .commit();
    }
    public void submitetisalat(View view) {
        EditText cardpin = (EditText) findViewById(R.id.pintext);
        String number = cardpin.getText().toString();
        String nuumber = "*222*" + number + "#";
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", nuumber, null));
        startActivity(i);
    }
    public void etisalatcard5(View view) {
        try {
            String shwbalance = "*232" + "#";
            Intent bal = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwbalance, null));
            startActivity(bal);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void etisalatcard6(View view) {
        try {
            String shwno = "*200*1*5" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void etisalatcard7(View view) {
        try {
            String shwno = "200*3*1*1*2*1" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void etisalatcard8(View view) {
        try {
            String shwno = "*200*3*1*2*1" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
    public void etisalatcard9(View view) {
        try {
            String shwno = "*200*3*1*4*2" + "#";
            Intent showno = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", shwno, null));
            startActivity(showno);
        } catch (android.content.ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "App failed", Toast.LENGTH_LONG).show();
        }
    }
}




