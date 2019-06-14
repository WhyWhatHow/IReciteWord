package com.sdut.soft.ireciteword.searchWord;

import android.content.Context;

import com.sdut.soft.ireciteword.bean.Word;
import com.sdut.soft.ireciteword.dao.WordDao;

import java.util.List;

public class SearchWordService {
    WordDao wordDao  ;
    public SearchWordService(Context context) {
        this .wordDao = new WordDao(context);
    }
    public  List<Word> findWords(String str) {
        return wordDao.searchWords(str);
    }
}
