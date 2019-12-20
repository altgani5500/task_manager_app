package com.example.ienovo.bnan;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String password1;
    public static String user_name;

   public static String url ="http://tagogx.000webhostapp.com/task_manager/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        user_name = getIntent().getStringExtra("user_name");
        password1=getIntent().getStringExtra("user_pass");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
//        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent alvvwefrerxx = new Intent(Home.this, Choice_Project.class);

                alvvwefrerxx.putExtra("password",password1);
                alvvwefrerxx.putExtra("massage_type","1");

                startActivity(alvvwefrerxx);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Intent alvvwefrerxx = new Intent(Home.this, Choice_Project.class);

                alvvwefrerxx.putExtra("password",password1);
                alvvwefrerxx.putExtra("massage_type","2");

                startActivity(alvvwefrerxx);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });
//        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu third item clicked
//
//            }
//        });

        Button etUserName23 = (Button) findViewById(R.id.button5);
        etUserName23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vvv) {

                Intent alvvwefrerxx = new Intent(Home.this, Profile.class);

                startActivity(alvvwefrerxx);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupTabIcons();
    }


    private void setupTabIcons() {

//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("الكل");
//     //  tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.menuside, 0, 0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("مهام واردة");
       tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_get_app_black_24dp, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("مهام صادرة");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_publish_black_24dp, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabThree);
        TextView tabfour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabfour.setText("مهام مستلمة");
     tabfour.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_check_black_24dp, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabfour);
//
////
//        TextView tabfive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabfive.setText("مفقودات");
//        //   tabfive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.menuside, 0, 0);
//        tabLayout.getTabAt(3).setCustomView(tabfive);
//
//
//        TextView tabsex = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabsex.setText("اخري");
//        //   tabsex.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.menuside, 0, 0);
//        tabLayout.getTabAt(5).setCustomView(tabsex);


    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Fragment_tow(), "الكل");
        adapter.addFrag(new Fragment_tow(), "سيارات");
        adapter.addFrag(new Fragment_one(), "اثاث منزلي");
//        adapter.addFrag(new Fragment_four(), "عقارات");
//        adapter.addFrag(new Fragment_five(), "هواتف واجهزة");
//        adapter.addFrag(new Fragment_sex(), "اخري");

        viewPager.setAdapter(adapter);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }


}
