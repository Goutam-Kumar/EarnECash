package com.earnecash.android.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.earnecash.android.R;
import com.earnecash.android.apphelper.AppHelper;
import com.earnecash.android.login.LoginActivity;
import com.earnecash.android.login.model.UserData;
import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rcvItem)
    RecyclerView rcvItem;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.ic_menu)
    ImageView icMenu;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeActivity.this, 3);
        rcvItem.setLayoutManager(gridLayoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(HomeActivity.this);
        rcvItem.setAdapter(homeAdapter);
        View header=navigationView.getHeaderView(0);
        user = (TextView) header.findViewById(R.id.user);
        String strUser = AppHelper.getString(AppHelper.USER_PREF,AppHelper.USER_DATA);
        UserData userData = new Gson().fromJson(strUser,UserData.class);
        if (userData != null){
            user.setText("Welcome "+ userData.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.logout) {
            AppHelper.saveBoolean(AppHelper.USER_PREF, AppHelper.ISLOGIN, false);
            startActivity(new Intent(HomeActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        return true;
    }

    @OnClick(R.id.ic_menu)
    public void onClick() {
        if (drawer.isDrawerOpen(Gravity.START)){
            drawer.closeDrawer(Gravity.START);
        }else {
            drawer.openDrawer(Gravity.START);
        }
    }
}
