package com.katana.quikread.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @author DEEPANKAR
 * @since 12-09-2015.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    int numberOfItems;

    public ViewPagerAdapter(FragmentManager fm ,int items) {
        super(fm);
        this.numberOfItems = items;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position",position); // TODO: defining keys properly

        return BookItemFragment.newInstance(bundle);
    }

    @Override
    public int getCount() {
        return numberOfItems;
    }
}
