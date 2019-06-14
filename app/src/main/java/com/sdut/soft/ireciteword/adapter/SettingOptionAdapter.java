package com.sdut.soft.ireciteword.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.bean.SettingOption;

import java.util.List;

public class SettingOptionAdapter extends BaseQuickAdapter<SettingOption,BaseViewHolder> {
    public SettingOptionAdapter(int layoutResId, @Nullable List<SettingOption> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingOption item) {
        helper.setText(R.id.tv_option,item.getName());
    }


}
