package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MaterialToolbar materialToolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialToolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.main_frame_layout);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation);

        // Set the custom toolbar as the app's toolbar
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView.bringToFront();

        // Enable drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                materialToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        // Add the drawer listener
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Add the feed fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new FeedFragment()).commit();

    }

    @Override
    // Close the drawer when back is clicked if it opened
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Manage fragments change
        switch (item.getItemId()) {
            case (R.id.feed):
                fragmentManager.popBackStackImmediate();
                FeedFragment feedFragment = (FeedFragment) fragmentManager.findFragmentByTag("feed");
                if (!(feedFragment instanceof FeedFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, new FeedFragment())
                            .addToBackStack("feed")
                            .commit();
                }
                break;
            case (R.id.articles):
                ArticlesFragment articlesFragment = (ArticlesFragment) fragmentManager.findFragmentByTag("articles");
                if (!(articlesFragment instanceof ArticlesFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, new ArticlesFragment())
                            .addToBackStack("articles")
                            .commit();
                }
                break;
            case (R.id.new_post):
                NewPostFragment newPostFragment = (NewPostFragment) fragmentManager.findFragmentByTag("new_post");
                if (!(newPostFragment instanceof NewPostFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, new NewPostFragment())
                            .addToBackStack("new_post")
                            .commit();
                }
                break;
            case (R.id.signup_nav):
                SignupFragment signupFragment = (SignupFragment) fragmentManager.findFragmentByTag("signup");
                if (!(signupFragment instanceof SignupFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, new SignupFragment())
                            .addToBackStack("signup")
                            .commit();
                }
                break;
            case (R.id.signin_nav):
                SigninFragment signinFragment = (SigninFragment) fragmentManager.findFragmentByTag("signup");
                if (!(signinFragment instanceof SigninFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, new SigninFragment())
                            .addToBackStack("signin")
                            .commit();
                }
                break;
            case (R.id.my_account_nav):
                MyAccountFragment myAccountFragment = (MyAccountFragment) fragmentManager.findFragmentByTag("signup");
                if (!(myAccountFragment instanceof MyAccountFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, new MyAccountFragment())
                            .addToBackStack("my_account")
                            .commit();
                }
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

}