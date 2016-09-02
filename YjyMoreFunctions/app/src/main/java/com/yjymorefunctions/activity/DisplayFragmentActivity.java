package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yjymorefunctions.R;
import com.yjymorefunctions.adapter.SubFragmentPagerAdapter;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Auth：yujunyao
 * Since: 2016/8/9 18:12
 * Email：yujunyao@yonglibao.com
 */
public class DisplayFragmentActivity extends BaseActivity {
//    @Bind(R.id.fragment_life)
//    FrameLayout fragmentLife;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private String[] array = new String[]{"Tab1Tab1", "Tab2", "Tab3Tab1Tab1", "Tab4", "Tab5", "Tab6Tab1Tab1Tab1", "Tab7", "Tab8", "Tab9"};
    private List<Fragment> fragments = new ArrayList<>();
    private SubFragmentPagerAdapter adapter;
    private List<TabLayout.Tab> tabList = new ArrayList<>();

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_display_fragment);
//        ButterKnife.bind(this);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        adapter = new SubFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new OnPageChangeListenerImp());
        for (int i=0;i<array.length;i++) {
            fragments.add(new NewsFragment(array[i]));

            TabLayout.Tab tab = tabLayout.newTab().setText(array[i]);
            tabLayout.addTab(tab);
            tabList.add(tab);
        }
        adapter.setFragments(fragments);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        switchFragment(new BaseFragment());
    }

    private class OnPageChangeListenerImp implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabList.get(position).select();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_display_fragment;
    }

//    protected void switchFragment(Fragment pFrgmnt) {
//        // 得到一个fragment 事务（类似sqlite的操作）
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_life, pFrgmnt);// 将得到的fragment
//        // 替换当前的viewGroup内容，add则不替换会依次累加
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);// 设置动画效果
//        ft.commitAllowingStateLoss();// 提交
//        // ft.commit();
//    }

}
