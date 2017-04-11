package com.fanhl.hbookerauthor.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.util.BottomNavigationAttachViewPagerHelper;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView navigation;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
    }

    private void assignViews() {
        viewPager = ((ViewPager) findViewById(R.id.viewPager));
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return BooksManagerFragment.newInstance();
                    case 1:
                        return BooksManagerFragment.newInstance();
                    case 2:
                        return BooksManagerFragment.newInstance();
                    case 3:
                        return BooksManagerFragment.newInstance();
                    case 4:
                        return BooksManagerFragment.newInstance();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        viewPager.setOffscreenPageLimit(4);

        BottomNavigationAttachViewPagerHelper.setup(navigation, viewPager);
    }

}
