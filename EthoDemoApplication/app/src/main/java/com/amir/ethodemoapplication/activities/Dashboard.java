package com.amir.ethodemoapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.amir.ethodemoapplication.R;
import com.amir.ethodemoapplication.core.BaseActivity;
import com.amir.ethodemoapplication.databinding.ActivityDashboardBinding;
import com.amir.ethodemoapplication.fragments.Profile;
import com.amir.ethodemoapplication.fragments.Ride;
import com.amir.ethodemoapplication.util.Common;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends BaseActivity {

    private ActivityDashboardBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        setUpToggle();
        implementListener();
        switchToFragment(new Profile());
        Common.setToolbarWithBackAndTitle(mContext, "My Profile", false, 0);
    }

    /**
     *  listener of menu item selected on navigation drawer
     */
    private void implementListener() {
        binding.nvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                setNavigationView(menuItem);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToggle() {
        toggle = new ActionBarDrawerToggle(this,
                binding.drawerLayout, binding.toolbar.mToolbar, R.string.drawer_open, R.string.drawer_close);

        binding.drawerLayout.addDrawerListener(toggle);

        binding.toolbar.mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        setSupportActionBar(binding.toolbar.mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }

    private void setNavigationView(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                logout();
                break;

            case R.id.nav_my_ride:
                Common.setToolbarWithBackAndTitle(mContext, "My Ride", false, 0);
                switchToFragment(new Ride());
                break;

            case R.id.nav_my_profile:
                Common.setToolbarWithBackAndTitle(mContext, "My Profile", false, 0);
                switchToFragment(new Profile());
                break;
        }

        binding.drawerLayout.closeDrawers();
    }

    private void logout() {
        sp.clearData();
        startActivity(new Intent(this, Login.class));
        finish();
    }

    /**
     * replace the given fragment with existing fragment
     * @param fragment
     */
    private void switchToFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.frame.getId(), fragment);
        fragmentTransaction.commit();
    }
}
