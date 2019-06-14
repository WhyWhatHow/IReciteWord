package com.sdut.soft.ireciteword.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.bean.Word;

import java.util.List;

public class SearchWordAdapter extends BaseQuickAdapter<Word,BaseViewHolder> {


    public SearchWordAdapter(int layoutResId, @Nullable List<Word> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Word item) {
        helper.setText(R.id.key, item.getKey())
                .setText(R.id.trans, item.getTrans());
    }
}
