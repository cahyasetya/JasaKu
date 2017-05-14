package com.example.jasaku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.fragment.HalamanUtamaFragment;
import com.example.jasaku.fragment.KelolaTokoFragment;
import com.example.jasaku.model.Toko;
import com.example.jasaku.penjual.EditTokoActivity;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu=navigationView.getMenu();
        MenuItem kelolaToko=menu.findItem(R.id.kelola_toko);
        SharedPreferences preferences=getSharedPreferences("jasaku",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.commit();
        boolean isLoggedIn=preferences.getBoolean("isLoggedIn",false);
        if(!isLoggedIn){
            if(kelolaToko.isVisible()){
                kelolaToko.setVisible(false);
            }
        }else{
            if(!kelolaToko.isVisible()){
                kelolaToko.setVisible(true);
            }
        }

        View headerView=navigationView.getHeaderView(0);
        TextView nama=(TextView)headerView.findViewById(R.id.nama_pengguna);
        String namaPengguna=preferences.getString("nama",null);
        nama.setText(namaPengguna);

        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main,new HalamanUtamaFragment()).commit();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.buat_toko) {
            startActivity(new Intent(this, RegisterTokoActivity.class));
        } else if (id == R.id.kelola_toko) {
            getToko();
        } else if (id == R.id.profil) {
            startActivity(new Intent(this,ProfileActivity.class));
        } else if (id == R.id.keluar) {

        }else if(id==R.id.masuk){
            startActivity(new Intent(this, LoginActivity.class));
        }else if(id==R.id.beranda){
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main,new HalamanUtamaFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getToko(){
        SharedPreferences preferences=getSharedPreferences("jasaku",MODE_PRIVATE);
        String userId=preferences.getString("id_user",null);

        ServiceInterface serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
        serviceInterface.getTokoByIdPengguna(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getTokoSuccess,this::getTokoFailed);
    }

    private void getTokoSuccess(List<Toko> tokoList){
        Toko toko=tokoList.get(0);

        Gson gson=new Gson();
        String tokoString=gson.toJson(toko);

        Intent intent=new Intent(this, EditTokoActivity.class);
        intent.putExtra("id_toko",toko.getId());
        intent.putExtra("nama_toko",toko.getNama());
        intent.putExtra("toko",tokoString);

        startActivity(intent);
    }

    private void getTokoFailed(Throwable t){
        Toast.makeText(this,"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}
