package com.example.jasaku.penjual;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Toko;
import com.example.jasaku.penjual.fragment.HalamanJasaFragment;
import com.example.jasaku.penjual.fragment.HalamanTokoFragment;
import com.example.jasaku.penjual.fragment.PesananDiterimaFragment;
import com.example.jasaku.penjual.fragment.PesananMasukFragment;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTokoActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_toko);

        ButterKnife.bind(this);

        Gson gson=new Gson();

        Intent intent=getIntent();
        String idToko=intent.getStringExtra("id_toko");
        String namaToko=intent.getStringExtra("nama_toko");
        Toko toko=gson.fromJson(intent.getStringExtra("toko"),Toko.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(namaToko);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),idToko,toko);

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(mSectionsPagerAdapter);

        tabs.setupWithViewPager(viewPager);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_toko, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String idToko;
        private Toko toko;

        public SectionsPagerAdapter(FragmentManager fm, String idToko, Toko toko) {
            super(fm);
            this.idToko=idToko;
            this.toko=toko;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Bundle bundle=new Bundle();
            bundle.putString("id_toko",this.idToko);
            switch (position){
                case 0:
                    bundle.putSerializable("toko",this.toko);
                    HalamanTokoFragment htf=new HalamanTokoFragment();
                    htf.setArguments(bundle);
                    return htf;
                case 1:
                    HalamanJasaFragment hjf=new HalamanJasaFragment();
                    hjf.setArguments(bundle);
                    return hjf;
                case 2:
                    PesananMasukFragment pmf=new PesananMasukFragment();
                    pmf.setArguments(bundle);
                    return pmf;
                default:
                    PesananDiterimaFragment pdf=new PesananDiterimaFragment();
                    pdf.setArguments(bundle);
                    return pdf;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Beranda";
                case 1:
                    return "Jasa";
                case 2:
                    return "Pesanan Masuk";
                case 3:
                    return "Pesanan Diterima";
            }
            return null;
        }
    }
}
