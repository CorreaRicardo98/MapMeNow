package e.ricardo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Map;

public class Inicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Perfil.OnFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener,
        Acerca.OnFragmentInteractionListener,
        mapa.OnFragmentInteractionListener,
        Settings.OnFragmentInteractionListener,
        SavedPlaces.OnFragmentInteractionListener{
    Acerca acerca;
        SavedPlaces sPlaces;
        Settings settings;
        mapa Mapa;
        MapsActivity mapaa;
        Perfil perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        perfil = new Perfil();
        acerca = new Acerca();
        Mapa = new mapa();
        sPlaces = new SavedPlaces();
        settings = new Settings();
        mapaa= new MapsActivity();


        TinyDB tinyDB = new TinyDB(getApplicationContext());




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View ver = navigationView.getHeaderView(0);
        TextView usuario = (TextView) ver.findViewById(R.id.draw_usu);
        usuario.setText(tinyDB.getString("UserName"));

        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        TinyDB tinyDB = new TinyDB(getApplicationContext());

        Fragment miFragment=null;
        boolean FragmentSelected=false;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_perfil) {


            Bundle BUsuario = new Bundle();
            BUsuario.putString("sendUsuario",tinyDB.getString("UserName"));
            perfil.setArguments(BUsuario);

            miFragment = perfil;
            FragmentSelected=true;
        } else if (id == R.id.nav_map) {
            Intent i = new Intent(Inicio.this,MapsActivity.class);
            startActivity(i);

            miFragment = Mapa;
            FragmentSelected=true;

        } else if (id == R.id.nav_settings) {
            miFragment = settings;
            FragmentSelected=true;

        } else if (id == R.id.nav_about) {
            miFragment = acerca;
            FragmentSelected=true;
        } else if (id == R.id.nav_sPlaces) {
            miFragment = sPlaces;
            FragmentSelected=true;
        }
        else if (id == R.id.nav_out) {
            tinyDB.clear();


        }
        if (FragmentSelected){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_inicio,miFragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }
}
