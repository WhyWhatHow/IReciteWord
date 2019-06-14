package com.sdut.soft.ireciteword.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.WordSpecificActivity;
import com.sdut.soft.ireciteword.adapter.SearchWordAdapter;
import com.sdut.soft.ireciteword.bean.Word;
import com.sdut.soft.ireciteword.searchWord.SearchWordService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends Fragment {

    private static final String TAG = "查词";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.et_word)
    EditText etWord;
    SearchWordService service;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
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
}
