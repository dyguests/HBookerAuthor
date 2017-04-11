package com.fanhl.hbookerauthor.util;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;

/**
 * Created by fanhl on 2017/4/11.
 */
public class BottomNavigationAttachViewPagerHelper {
    public static void setup(BottomNavigationView navigation, ViewPager viewPager) {
        int naviSize = navigation.getMenu().size();
        int pagerSize = viewPager.getAdapter().getCount();

        for (int i = 0; i < naviSize; i++) {
            int finalI = i;
            navigation.getMenu().getItem(i).setOnMenuItemClickListener(item -> {
                if (finalI < pagerSize) {
                    viewPager.setCurrentItem(finalI);
                }
                return false;
            });
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position < naviSize) {
                    navigation.getMenu().getItem(position).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
