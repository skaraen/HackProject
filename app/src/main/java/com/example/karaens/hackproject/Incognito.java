package com.example.karaens.hackproject;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Incognito extends AppCompatActivity implements android.support.v7.widget.SearchView.OnQueryTextListener{

    android.support.v7.widget.Toolbar toolbar;
    ViewPager mViewPager;
    TabFragmentAdapter fragmentAdapter;
    MenuItem number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.grey));
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
        options.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(Incognito.this,"Options",Toast.LENGTH_LONG).show();
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
                Intent intent=new Intent(Incognito.this,Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Toast.makeText(Incognito.this,"Back in the light !",Toast.LENGTH_LONG).show();
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

       /* if(fragmentAdapter.getItem(mViewPager.getCurrentItem())==null)
            Toast.makeText(Main.this,"Options",Toast.LENGTH_LONG).show();
        else {
            fragmentAdapter.getItem(mViewPager.getCurrentItem()).webView.setWebViewClient(new MyWebViewClient());
            fragmentAdapter.getItem(mViewPager.getCurrentItem()).webView.loadUrl("http://" + query);
        }*/
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}
