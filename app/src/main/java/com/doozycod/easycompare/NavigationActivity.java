package com.doozycod.easycompare;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.doozycod.easycompare.helper.ExpandableListAdapter;
import com.doozycod.easycompare.helper.MenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SearchView searchView;
    LinearLayout scan_btn, upload_btn;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        searchView = findViewById(R.id.search_bar);
        scan_btn = findViewById(R.id.scan_button);
        upload_btn = findViewById(R.id.upload_button);
        Toolbar toolbar = findViewById(R.id.toolbar);
        expandableListView = findViewById(R.id.expandableListView);
        toolbar.setTitle(R.string.product);
        setSupportActionBar(toolbar);

        prepareMenuData();
        populateExpandableList();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Scan.class));
//      Requesting Permission for the Camera
                if (ContextCompat.checkSelfPermission(NavigationActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                            NavigationActivity.this, Manifest.permission.CAMERA)) {


                    } else {
                        ActivityCompat.requestPermissions( NavigationActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                200);
                    }

                }
            }
        });
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UploadActivity.class));
            }
        });


    }

    private void prepareMenuData() {

        MenuModel menuModel = new MenuModel("All Categories", true, true, "");
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Home Decor", false, false, "https://www.homedepot.com/b/Home-Decor/N-5yc1vZas6p");
        childModelsList.add(childModel);

        childModel = new MenuModel("Furniture", false, false, "https://www.homedepot.com/b/Furniture/N-5yc1vZc7pc");
        childModelsList.add(childModel);

        childModel = new MenuModel("Kitchenware", false, false, "https://www.homedepot.com/b/Kitchen-Kitchenware/N-5yc1vZaqzo");
        childModelsList.add(childModel);


//        if (menuModel.hasChildren) {
//            Log.d("API123", "here");
//            childList.put(menuModel, childModelsList);
//        }
//
//        childModelsList = new ArrayList<>();
//        menuModel = new MenuModel("Python Tutorials", true, true, "");
//        headerList.add(menuModel);

        childModel = new MenuModel("Bedding & Bath", false, false, "https://www.homedepot.com/b/Home-Decor-Bedding-Bath/N-5yc1vZci04");
        childModelsList.add(childModel);

        childModel = new MenuModel("Lighting", false, false, "https://www.homedepot.com/b/Lighting/N-5yc1vZbvn5");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(headerList.get(groupPosition).url);
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    if (model.url.length() > 0) {
                        WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(model.url);
                        onBackPressed();
                    }
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);

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

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
//            startActivity(new Intent(getApplicationContext(), Scan.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
