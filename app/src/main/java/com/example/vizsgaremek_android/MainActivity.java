package com.example.vizsgaremek_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fooldal()).commit();
            navigationView.setCheckedItem(R.id.nav_fooldal);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_fooldal:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fooldal()).commit();
                break;
            case R.id.nav_bejelentkezes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Bejelentkezes()).commit();
                break;
            case R.id.nav_regisztracio:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Regisztracio()).commit();
                break;
            case R.id.nav_hirdetesekKeresese:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HirdetesekKeresese()).commit();
                break;
            case R.id.nav_hirdetesFeladasa:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HirdetesFeladasa()).commit();
                break;
            case R.id.nav_profil:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profil()).commit();
                break;
            case R.id.nav_ranglista:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Ranglista()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}