package cc.biglong.bigandroid.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.adapter.PagerAdapter;
import cc.biglong.bigandroid.adapter.WidgetsAdapter;
import cc.biglong.bigandroid.base.BaseActivity;
import cc.biglong.bigandroid.base.BaseFragment;
import cc.biglong.bigandroid.fragment.MeFragment;
import cc.biglong.bigandroid.fragment.WidgetsFragment;

/**
 * Created by biglong on 2017/3/29.
 */

public class EntryActivity extends BaseActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.bottomMenu)
    BottomNavigationView mBottomMenu;

    private MeFragment mMeFragment;
    private WidgetsFragment mWidgetsFragment;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_entry);

        ButterKnife.bind(this);
        setupViewPager();
        mBottomMenu.setOnNavigationItemSelectedListener(this);
        mViewPager.addOnPageChangeListener(this);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_entry:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.item_me:
                if (mViewPager.getCurrentItem()==1)
                    mMeFragment.refresh();
                mViewPager.setCurrentItem(1);
                break;
        }
        return false;
    }

    private MenuItem menuItem;

    @Override
    public void onPageSelected(int position) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            mBottomMenu.getMenu().getItem(0).setChecked(false);
        }
        menuItem = mBottomMenu.getMenu().getItem(position);
        menuItem.setChecked(true);
    }


    private void setupViewPager() {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mMeFragment = MeFragment.newInstance("2");
        mWidgetsFragment = WidgetsFragment.newInstance("1");

        adapter.addFragment(mWidgetsFragment);
        adapter.addFragment(mMeFragment);
        mViewPager.setAdapter(adapter);
    }





    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
