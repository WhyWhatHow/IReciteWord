package com.sdut.soft.ireciteword.activity.toolbar;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdut.soft.ireciteword.AboutActivity;
import com.sdut.soft.ireciteword.ICiBaActivity;
import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.activity.base.BaseActivity;
import com.sdut.soft.ireciteword.utils.ICiBaParseUtil;
import com.sdut.soft.ireciteword.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 仿知乎主界面Toolbar的应用
 *
 */
public class ZhiHuActivity extends BaseActivity {
    String TAG = "------------------query test-----------";
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu);
        initToolBar();

    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.zhihu_toolbar_menu, menu);
        Log.i(TAG, "onCreateOptionsMenu: orzz");
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.w(TAG, query);
                dealWithWord(query);
                Toast.makeText(ZhiHuActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        setToolBar();
        return true;
    }


    private void setToolBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setTitle(R.string.home_page);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
//        toolbar.inflateMenu(R.menu.zhihu_toolbar_menu); // 关联 mmenu 菜单
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

                } else if (menuItemId == R.id.action_about) {
                    // TODO  goto About Activity
                    //  Toast.makeText(ToolBarActivity.this, R.string.item_02, Toast.LENGTH_SHORT).show();
                    gotoAbout();
                }
                return true;
            }
        });
    }




    public void gotoAbout() {
        Intent intent;
        intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void gotoIciba(String spName) {
        Intent intent;
        intent = new Intent(this, ICiBaActivity.class);
        intent.putExtra("name" ,spName);
        startActivity(intent);
    }

    public void dealWithWord(String word) {
        //金山查词网址，默认xml，使用中
        final String urlxml = "http://dict-co.iciba.com/api/dictionary.php?w=" + word + "&key=9AA9FA4923AC16CED1583C26CF284C3F";
        //金山查词网址，可选json，&type=json  ，因为缺少例句，未使用
        String url = "http://dict-co.iciba.com/api/dictionary.php?w=" + word + "&type=json&key=9AA9FA4923AC16CED1583C26CF284C3F";

        if (ICiBaParseUtil.isEnglish(word)) { // english to chinese
            OkHttpUtil.sendOkHttpRequest(urlxml, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.w(TAG, "onFailure:  didn't get word information " );
                    Toast.makeText(ZhiHuActivity.this, "获取翻译数据失败！", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                    Log.d(TAG, "onResponse:  get word information");
                    final String result = response.body().string();
                    Log.d(TAG, result);

                    runOnUiThread(new Runnable() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void run() {
                            ICiBaParseUtil.parseJinshanEnglishToChineseXMLWithPull(result); // parse the information and storage  in sharedPerferences
//                            SharedPreferences pref = getSharedPreferences("JinshanEnglishToChinese", MODE_PRIVATE);
                            gotoIciba("JinshanEnglishToChinese");
                            //                            dealEnglishToChinese(pref, "英式发音: ");
                        }
                    });
                }
            });
        } else {
            OkHttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Toast.makeText(ZhiHuActivity.this, "获取翻译数据失败！", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                    final String result = response.body().string();
                    Log.d(TAG, result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ICiBaParseUtil.parseJinshanChineseToEnglishJSONWithGson(result);
                            gotoIciba("JinshanChineseToEnglish");

                        }
                    });
                }
            });

        }
    }

    //todo delete
//            OkHttpUtil.sendOkHttpRequest(urlxml, new Callback() {
//                @Override
//                public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                    Toast.makeText(ZhiHuActivity.this, "获取翻译数据失败！", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                    final String result = response.body().string();
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            String example = ICiBaParseUtil.parseJinshanChineseToEnglishXMLWithPull(result);
////                            TextView examples = (TextView) findViewById(R.id.related_examples);
////                            examples.setText(example);
//                        }
//                    });
//
//                }
//            });
}
