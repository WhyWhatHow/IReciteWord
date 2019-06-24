package com.sdut.soft.ireciteword.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sdut.soft.ireciteword.AboutActivity;
import com.sdut.soft.ireciteword.BaseSettingActivity;
import com.sdut.soft.ireciteword.MenuActivity;
import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.WordSpecificActivity;
import com.sdut.soft.ireciteword.adapter.SearchWordAdapter;
import com.sdut.soft.ireciteword.bean.Word;
import com.sdut.soft.ireciteword.searchWord.SearchWordService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * add toolbar
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "查词";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.et_word)
    EditText etWord;
    //todo  add toolbar,tvTitle
    @BindView(R.id.toolbar_menu)
    Toolbar toolbar;
    @BindView(R.id.tv_tb_title)
    TextView tvTitle;

    SearchWordService service;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MenuActivity activity = (MenuActivity)getActivity();
//        activity.mTvTitle.setText("Search");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        setToolBar();
        tvTitle.setText("Search");
        initView();
        return view;
    }

    private void initView() {
        service = new SearchWordService(getContext());
    }
    @OnClick(R.id.btn_search)
    public void searchWord() {
        final List<Word> words = service.findWords(etWord.getText().toString());
        Log.i(TAG, "searchWord: "+words);
        SearchWordAdapter adapter = new SearchWordAdapter(R.layout.search_word_item, words);
        // TODO add searchHistory

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WordSpecificActivity.class);
                intent.putExtra("id", words.get(position).getId());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    /**
     * TODO 添加 toolbar 重新布局 测试一
     *  设置toolbar
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
     *  页面跳转， 前往 About页面
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
