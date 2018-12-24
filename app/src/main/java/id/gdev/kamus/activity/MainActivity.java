package id.gdev.kamus.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.gdev.kamus.R;
import id.gdev.kamus.fragment.EngIndFragment;
import id.gdev.kamus.fragment.IndEngFragment;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind the view
        ButterKnife.bind(this);

        setToolbar();
        setupViewPager();

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    } */

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.ic_8_language));
        }
    }

    private void setupViewPager() {
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_title_ind_eng)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_title_eng_ind)));
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //class view pager adapter
    class ViewPagerAdapter extends FragmentPagerAdapter {

        String[] tab_title = {getResources().getString(R.string.tab_title_ind_eng), getResources().getString(R.string.tab_title_eng_ind)};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new IndEngFragment();

                case 1:
                    return new EngIndFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_title[position];
        }

        @Override
        public int getCount() {
            return tab_title.length;
        }
    }
}
