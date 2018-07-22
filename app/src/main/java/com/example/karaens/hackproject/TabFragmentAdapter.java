package com.example.karaens.hackproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAdapter extends FragmentStatePagerAdapter {

    int num;
    SparseArray<TabFragment> registeredFragments = new SparseArray<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TabFragment tab = (TabFragment) super.instantiateItem(container, position);
        registeredFragments.put(position,new TabFragment());
        return tab;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public TabFragment getTab(int position) {
        return registeredFragments.get(position);
    }

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public TabFragment getItem(int position) {
        return new TabFragment();
    }

    @Override
    public int getCount() {
        return num;
    }

    void setCount(int n){
        num=n;
    }



}
