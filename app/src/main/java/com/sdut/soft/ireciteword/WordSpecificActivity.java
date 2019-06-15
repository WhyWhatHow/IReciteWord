package com.sdut.soft.ireciteword;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sdut.soft.ireciteword.bean.Word;
import com.sdut.soft.ireciteword.dao.WordDao;
import com.sdut.soft.ireciteword.fragment.DetailFgt;
import com.sdut.soft.ireciteword.utils.Const;
import com.sdut.soft.ireciteword.utils.SettingsUtils;
import com.sdut.soft.ireciteword.utils.YouDaoAudioUriUtils;

import java.io.IOException;

public class WordSpecificActivity extends AppCompatActivity implements DetailFgt.onSpeechListener{

    DetailFgt detailFgt;
    WordDao wordDao;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_specific);
        wordDao = new WordDao(this);
        addDetailFgt();
        initPlayer();
    }

    private void addDetailFgt() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);
        Word w = wordDao.getWordById(Const.DEFAULT_META,id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        detailFgt = DetailFgt.newInstance(w);
        transaction.add(R.id.detail_container, detailFgt);
        transaction.commit();
    }

    @Override
    public void speech(Word word) {
        String content = word.getKey();
        try {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(this, YouDaoAudioUriUtils.getUri(content));
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void initPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                DetailFgt item = detailFgt;
                item.setSpeakImg(R.mipmap.icon_speaker_on);
                mMediaPlayer.start();
            }
        });
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mMediaPlayer.reset();
                return true;
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                DetailFgt item = detailFgt;
                item.setSpeakImg(R.mipmap.icon_speaker_off);
            }
        });
    }
}
