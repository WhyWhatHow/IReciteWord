package com.sdut.soft.ireciteword.fragment;

import android.app.assist.AssistStructure;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.translate.YouDaoTranslateService;
import com.sdut.soft.ireciteword.utils.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TranslateFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.et_src)
    EditText eTSrc;
    @BindView(R.id.tv_tgt)
    TextView tvTgt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {

    }
    @OnClick(R.id.btn_search)
    public void search() {
        YouDaoTranslateService.translateString(eTSrc.getText().toString(),handler);
    }
    private Handler handler = new Handler (){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Const
                        .TRA_SUCCESS:
                    tvTgt.setText((String)msg.obj);
                    tvTgt.setTextColor(Color.BLACK);
                    break;
                case Const.TRA_FAIL:
                    tvTgt.setText((String)msg.obj);
                    tvTgt.setTextColor(Color.RED);
                    break;
            }
        }
    };
}
