package com.sdut.soft.ireciteword.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sdut.soft.ireciteword.BaseSettingActivity;
import com.sdut.soft.ireciteword.AboutActivity;
import com.sdut.soft.ireciteword.MenuActivity;
import com.sdut.soft.ireciteword.PwdActivity;
import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.activity.toolbar.ZhiHuActivity;
import com.sdut.soft.ireciteword.adapter.SettingOptionAdapter;
import com.sdut.soft.ireciteword.bean.SettingOption;
import com.sdut.soft.ireciteword.bean.User;
import com.sdut.soft.ireciteword.user.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 基本设置 ：设置每天背诵的单词数量，设置背诵的单词词汇表
 * 修改密码 ：旧密码，新密码，新密码重复
 * 软件信息 ：小组成员，使用的框架技术，软件更新信息，GitHub项目地址
 */
public class SettingsFragment extends Fragment {

    @BindView(R.id.iv_exit)
    ImageView ivExit;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_rcindex)
    TextView tvRcindex;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    //todo  add toolbar,tvTitle
    @BindView(R.id.toolbar_menu)
    Toolbar toolbar;
    @BindView(R.id.tv_tb_title)
    TextView tvTitle;

    SettingOptionAdapter adapter;
    UserService userService;
    List<String> options = Arrays.asList("基本设置", "修改密码", "软件信息", "zhihu");


    List<Class<? extends AppCompatActivity>> tgtClz = Arrays.asList(BaseSettingActivity.class, PwdActivity.class, AboutActivity.class, ZhiHuActivity.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // todo set toolbar

    }

    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        setToolBar();
        tvTitle.setText("Tools");

        userService = new UserService(getContext());
        User user = userService.currentUser();
        tvName.setText(user.getName());
        tvRcindex.setText(String.format("您的词汇量为%d个", user.getRcindex()));
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("用户退出")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("是否退出登陆")
                        .setPositiveButton(R.string.exit_commit, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                userService.removeUser();
                                dialog.dismiss();
                                getActivity().finish();
                                return;
                            }
                        })
                        .setNegativeButton(R.string.exit_quit, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
        List<SettingOption> list = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            list.add(new SettingOption(options.get(i), tgtClz.get(i)));
        }
        adapter = new SettingOptionAdapter(R.layout.setting_option_item, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), tgtClz.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    /**
     * TODO 添加 toolbar 重新布局 测试一
     * 设置toolbar
     */
    private void setToolBar() {

        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setTitle("");
        tvTitle.setText("Search");
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
     * 页面跳转， 前往 About页面
     */
    public void gotoAbout() {
        Intent intent;
        intent = new Intent(getActivity(), AboutActivity.class);
        startActivity(intent);
    }

    public void gotoBaseSettings() {
        Intent intent;
        intent = new Intent(getActivity(), BaseSettingActivity.class);
        startActivity(intent);
    }


}
