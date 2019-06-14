package com.sdut.soft.ireciteword.reviewWord;

import android.content.Context;

import com.sdut.soft.ireciteword.bean.Word;
import com.sdut.soft.ireciteword.dao.WordDao;

import java.util.List;
import java.util.Random;

/**
 * 单词选项类
 */
public class WordOptions {
    WordDao wordDao;
    List<Word> list;
    // 从零开始的序号，正确选项的序号
    Integer rigthIndex;
    Word target;
    public WordOptions(Context context,String meta,Word target) {
        this.target = target;
        wordDao = new WordDao(context);
        list = wordDao.getOptionsFromUnit(meta,target);
        rigthIndex = new Random().nextInt(list.size());
        list.add(rigthIndex,target);
    }
    public boolean isRight(Integer index) {
        return index == rigthIndex;
    }

    public String wordMark() {
        return Math.random() > 0.5 ? target.getPhono():target.getTrans();
    }

    public List<Word> getOptions() {
        return list;
    }

}
