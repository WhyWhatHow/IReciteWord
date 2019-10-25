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
        this.target = target;// 正确单词
        wordDao = new WordDao(context);
        list = wordDao.getOptionsFromUnit(meta,target);// 干扰项
        rigthIndex = new Random().nextInt(list.size());// 正确项地址
        list.add(rigthIndex,target);// 添加
    }
    public boolean isRight(Integer index) {
        return index == rigthIndex;
    }
    public String wordMark() { // word hint
        return Math.random() > 0.5 ? target.getTrans():String.format("[%s]",target.getPhono());
    }
    public List<Word> getOptions() {
        return list;
    }

}
