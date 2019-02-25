package com.example.teqani.base.presentation.screens.HomeScreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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

import com.example.teqani.base.R;
import com.example.teqani.base.helper.LocaleHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView name;
    TextView userPhone;

    MainViewModel mainViewModel;

    @Inject
    MainViewModelFactory mainViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidInjection.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.user_name);
        userPhone = headerView.findViewById(R.id.user_phone);
        navigationView.setNavigationItemSelectedListener(this);

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel.class);

        mainViewModel.getUser();

        initLiveObservers();

        LocaleHelper l = LocaleHelper.getInstance(getBaseContext());
        String country = getString(R.string.default_Country);

        l.setLocale(getBaseContext(), country.toUpperCase(), l.getLanguage());

        if (l.getLanguage().equals("ar")) {
            LocaleHelper.ChangeDesignToRTL(this);
        } else {
            LocaleHelper.ChangeDesignToLTR(this);
        }
    }

    private void initLiveObservers() {
        mainViewModel.userReadySLD.observe(this, this::onUserReady);
    }

    private void onUserReady(Boolean aBoolean) {
        name.setText(mainViewModel.getLoginResponse().getPlainUser().getName());
        userPhone.setText(mainViewModel.getLoginResponse().getPlainUser().getUsername());
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        //Set Application local

        LocaleHelper l = LocaleHelper.getInstance(newBase);

        String country = newBase.getString(R.string.default_Country);

        newBase = l.setLocale(newBase, country.toUpperCase(), l
                .getLanguage());

        super.attachBaseContext(newBase);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocaleHelper l = LocaleHelper.getInstance(getBaseContext());
        String country = getString(R.string.default_Country);
        l.setLocale(getBaseContext(), country.toUpperCase(), l.getLanguage());
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
        return false;
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

        if (id == R.id.nav_about) {
            // Handle the camera action
        } else if (id == R.id.nav_main) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment_container, MainFragment.newInstance()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
