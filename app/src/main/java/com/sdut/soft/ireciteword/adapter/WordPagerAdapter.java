package com.sdut.soft.ireciteword.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.sdut.soft.ireciteword.bean.Word;
import com.sdut.soft.ireciteword.fragment.DetailFragment;

import java.util.List;


public class WordPagerAdapter extends FragmentStatePagerAdapter {

    private List<Word> mWordList;
    private SparseArray<Fragment> mFragments;

    public WordPagerAdapter(FragmentManager fm, List<Word> wordList) {
        super(fm);
        mWordList = wordList;
        mFragments=new SparseArray<>(mWordList.size());
    }

    @Override
    public Fragment getItem(int position) {
        DetailFragment detailFgt = DetailFragment.newInstance(mWordList.get(position));
        mFragments.put(position, detailFgt);
        return detailFgt;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        mFragments.remove(position);
    }

    @Override
    public int getCount() {
        return mWordList.size();
    }

    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }
}
