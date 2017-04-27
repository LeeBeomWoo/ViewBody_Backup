package com.example.leebeomwoo.viewbody_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.leebeomwoo.viewbody_final.Fragment.Account_Fragment;
import com.example.leebeomwoo.viewbody_final.Fragment.Join_Fragment;

/**
 * Created by LeeBeomWoo on 2017-04-24.
 */

public class AccountActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    Fragment joinFragment = new Join_Fragment();
    Fragment accountFragment = new Account_Fragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.account_draw_layout);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        transaction.replace(R.id.account_fragment, accountFragment);
        transaction.addToBackStack(null);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);
        ab.setDisplayShowCustomEnabled(true);
        // Get access to the custom title view
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.accountmenu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        // Create new fragment and transaction

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed

// Commit the transaction
        transaction.commit();
        switch (item.getItemId()) {
            case R.id.body: //문서정보로 이동
                 intent = new Intent(this, MainActivity.class);
                intent.putExtra("message", 0);
                startActivity(intent);
                return true;
            case R.id.food: // 음식정보로 이동
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("message", 1);
                startActivity(intent);
                return true;
            case R.id.follow: // 동영상 따라하는 것으로 이동
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("message", 2);
                startActivity(intent);
                return true;
            case R.id.writer: // 작가텝으로 이동
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("message", 3);
                startActivity(intent);
                return true;
            case R.id.join: // 현재 액티비티에 회원가입 신청프레그먼트를 출력
                transaction.replace(R.id.account_fragment, accountFragment);
                transaction.addToBackStack(null);
                return true;
            case R.id.information: // 현재 액티비티에 사용자 정보 프레그먼트 출력
                transaction.replace(R.id.account_fragment, joinFragment);
                transaction.addToBackStack(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed ()
    {
        super.onBackPressed();
        startActivity(new Intent(AccountActivity.this, MainActivity.class));
        finish();
    }
}
