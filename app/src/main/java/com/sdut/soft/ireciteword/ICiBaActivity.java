package com.sdut.soft.ireciteword;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ICiBaActivity extends AppCompatActivity {


    String TAG = "ICibaActivity: ";

    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.tv_phono_am)
    TextView tvPhonoAm;
    @BindView(R.id.tv_phono)
    TextView tvPhonoEn;
    @BindView(R.id.tv_trans)
    TextView tvTrans; // word meaning
    @BindView(R.id.tv_exam)
    TextView tvExam;
    @BindView(R.id.icon_speech_am)
    ImageView iconSpeechAm;
    @BindView(R.id.icon_speech)
    ImageView iconSpeechen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        setContentView(R.layout.activity_ici_ba);
        ButterKnife.bind(this);
        dealWithEnglishToChinese(name);
    }

    public void dealWithEnglishToChinese(String name) {

        try {
            SharedPreferences pref = getSharedPreferences(name, MODE_PRIVATE);
            String queryText = pref.getString("queryText", "空");
            String voiceAmText = pref.getString("voiceAmText", "空");
            final String voiceAmUrlText = pref.getString("voiceAmUrlText", "空");
            String voiceEnText = pref.getString("voiceEnText", "空");
            final String voiceEnUrlText = pref.getString("voiceEnUrlText", "空");
            String meanText = pref.getString("meanText", "空");
            String exampleText = pref.getString("exampleText", "Null");
            Log.i(TAG, "dealWithEnglishToChinese: " + queryText);
            tvKey.setText(queryText);
            tvPhonoAm.setText(voiceAmText);
            tvPhonoEn.setText(voiceEnText);
            tvTrans.setText(meanText);
            tvExam.setText(exampleText);
            iconSpeechAm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(ICiBaActivity.this, Uri.parse(voiceAmUrlText));
                        mediaPlayer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            iconSpeechen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(ICiBaActivity.this, Uri.parse(voiceEnUrlText));
                        mediaPlayer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "dealWithEnglishToChinese:  error in get sharedpreferenced file " + name);
        }
    }

    /***
     * todo  from chinese to english
     *  deal sharedPerferenced data from chinese to English
     *
     * @param pref , orginal data(after xml parse)
     */
//    public void dealChineseToEnglish(SharedPreferences pref) {
//
//        TextView query = (TextView) findViewById(R.id.query);
//
//        TextView enVoiceLab = (TextView) findViewById(R.id.en_voice);
//        ImageView enVoiceImg = (ImageView) findViewById(R.id.iv_en_voice);
//        TextView enVoiceText = (TextView) findViewById(R.id.en_voice_text);
//
//        TextView amVoiceLab = (TextView) findViewById(R.id.am_voice);
//        ImageView amVoiceImg = (ImageView) findViewById(R.id.iv_am_voice);
//        TextView amVoiceText = (TextView) findViewById(R.id.am_voice_text);
//
//        TextView baseMean = (TextView) findViewById(R.id.base_mean);
//
//        enVoiceLab.setText("拼音：");
//        amVoiceLab.setVisibility(View.GONE);
//        amVoiceImg.setVisibility(View.GONE);
//        amVoiceText.setVisibility(View.GONE);
//
//        query.setText(queryText);
//        enVoiceText.setText(voiceText);
//        baseMean.setText(meanText);
//
//        enVoiceImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    MediaPlayer mediaPlayer = MediaPlayer.create(ZhiHuActivity.this, Uri.parse(voiceUrlText));
//                    mediaPlayer.start();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//

}
