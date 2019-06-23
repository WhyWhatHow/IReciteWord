
package com.sdut.soft.ireciteword;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.sdut.soft.ireciteword.adapter.FragmentAdapter;
import com.sdut.soft.ireciteword.fragment.ReciteFragment;
import com.sdut.soft.ireciteword.fragment.SearchFragment;
import com.sdut.soft.ireciteword.fragment.SettingsFragment;
import com.sdut.soft.ireciteword.fragment.TranslateFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout mTabLayout ;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tv_tb_title)
    public TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        setToolBar();
        initViewPager();
    }
    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("词典");
        titles.add("学习");
        titles.add("翻译");
        titles.add("工具");
        List<Fragment> fragments = new ArrayList<>();
        for (String title : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));

        }
        fragments.add(new SearchFragment());
        fragments.add(new ReciteFragment());
        fragments.add(new TranslateFragment());
        fragments.add(new SettingsFragment());

        FragmentAdapter mFragmentAdapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(mFragmentAdapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);
    }

    /**
     * TODO 添加 toolbar 重新布局 测试一
     *  设置toolbar
     */
    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_menu);

        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setTitle("");
        mTvTitle.setText("首页");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        toolbar.inflateMenu(R.menu.zhihu_toolbar_menu); // 关联 mmenu 菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    // TODO  跳转到 search 界面
                    // Toast.makeText(ToolBarActivity.this, R.string.menu_search, Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_settings) {
                    //  TODO  goto Settings
                    // Toast.makeText(ToolBarActivity.this, R.string.item_01, Toast.LENGTH_SHORT).show();
                    gotoBaseSettings();
                } else if (menuItemId == R.id.action_about) {
                    // TODO  goto About Activity
                    //  Toast.makeText(ToolBarActivity.this, R.string.item_02, Toast.LENGTH_SHORT).show();
                    gotoAbout();
                }
                return true;
            }
        });
    }

    /**
     *  页面跳转， 前往 About页面
     */
    public void gotoAbout() {
        Intent intent;
        intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
    public void gotoBaseSettings() {
        Intent intent;
        intent = new Intent(this, BaseSettingActivity.class);
        startActivity(intent);
    }


}
