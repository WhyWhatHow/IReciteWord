package com.sdut.soft.ireciteword.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.bean.MetaOption;

import java.util.List;

public class MetaOptionAdapter extends BaseQuickAdapter<MetaOption,BaseViewHolder> {
    public MetaOptionAdapter(int layoutResId, @Nullable List<MetaOption> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MetaOption item) {
        helper.setBackgroundRes(R.id.btn_option,item.getIcon());
    }
}
