package com.example.bottomnavigation;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView Bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bottom=findViewById(R.id.bottom);

        Bottom.setOnNavigationItemSelectedListener(item -> {
            int id=item.getItemId();
            if (id==R.id.home)
            {
                loadfrag(new FragmentA(),false);

            }
            else if (id==R.id.search)
            {
                loadfrag(new FragmentB(),false);
            }
            else if (id==R.id.contact)
            {
                loadfrag(new FragmentC(),false);
            }
            else if (id==R.id.profile)
            {
                loadfrag(new FragmentD(),false);

            }
            else
            {
                loadfrag(new FragmentE() ,true);
            }


            return true;
        });
        Bottom.setSelectedItemId(R.id.profile);


    }
    public  void loadfrag(Fragment fragment,boolean flag)
    {

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        if(flag)
        ft.add(R.id.container,new FragmentA());
        else
            ft.replace(R.id.container,fragment);
        ft.commit();

    }
}