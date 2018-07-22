package com.example.karaens.hackproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity implements android.support.v7.widget.SearchView.OnQueryTextListener{

    android.support.v7.widget.Toolbar toolbar;
    ViewPager mViewPager;
    TabFragmentAdapter fragmentAdapter;
    MenuItem number;
    List<String> history=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        mViewPager=findViewById(R.id.ViewPagerTab);
        fragmentAdapter=new TabFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.setCount(1);
        mViewPager.setAdapter(fragmentAdapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem search=menu.findItem(R.id.action_search);
        MenuItem options=menu.findItem(R.id.options);
        MenuItem plus=menu.findItem(R.id.plus);
        number=menu.findItem(R.id.number);
        MenuItem incognito=menu.findItem(R.id.incognito);
        MenuItem close=menu.findItem(R.id.close);
        close.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });
        options.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent=new Intent(Main.this,OptionsActivity.class);
                startActivity(intent);
                return true;
            }
        });
        plus.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int n=fragmentAdapter.getCount()+1;
                fragmentAdapter.setCount(n);
                number.setTitle(String.valueOf(n));
                mViewPager.removeAllViews();
                mViewPager.setAdapter(fragmentAdapter);
               return true;
            }
        });
        incognito.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent=new Intent(Main.this,Incognito.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Toast.makeText(Main.this,"You are now under cover !",Toast.LENGTH_LONG).show();
                startActivity(intent);
                return true;
            }
        });
        SearchView searchView=(android.support.v7.widget.SearchView) search.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
       /* TabFragment tabFragment=getTab();
       if(tabFragment!=null){
           tabFragment=getTab();
           tabFragment.webView.loadUrl("https://"+query+".com");
           history.add("https://"+query+".com");
       }*/
        return true;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    TabFragment getTab(){
        TabFragment tabFragment=fragmentAdapter.getTab(mViewPager.getCurrentItem());
        return tabFragment;
    }

}
