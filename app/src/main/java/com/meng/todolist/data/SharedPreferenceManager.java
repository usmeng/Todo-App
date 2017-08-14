package com.meng.todolist.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.meng.todolist.TodoApplication;
import com.meng.todolist.presentation.beans.TodoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mengzhou on 8/11/17.
 */

public class SharedPreferenceManager {

    private static SharedPreferenceManager sharedPreferenceManager
            = new SharedPreferenceManager();
    private static final String TODO_LIST_PREFERENCE = "todo_list_preference";
    private static final String TODO_LIST_DATA = "todo_list_data";
    private final SharedPreferences mPreferences;
    private List<TodoItem> mData;

    private SharedPreferenceManager() {
        Context mApplication = TodoApplication.getApplicatioContext();
        mPreferences = mApplication.getSharedPreferences(TODO_LIST_PREFERENCE, Activity.MODE_PRIVATE);
    }

    static SharedPreferenceManager getInstance() {
        return sharedPreferenceManager;
    }

    void saveData() {
        Set<String> data = new HashSet<>();
        for (TodoItem item : mData) {
            data.add(toString(item));
        }
        mPreferences.edit().putStringSet(TODO_LIST_DATA, data).apply();
    }

    List<TodoItem> loadData() {
        mData = new ArrayList<>();
        Set<String> mTodoSet = mPreferences.getStringSet(TODO_LIST_DATA, new HashSet<String>());
        int id = 0;
        for(String itemStr : mTodoSet) {
            String[] values = itemStr.split(",");
            TodoItem item = new TodoItem(values[TotoItemColumn.TITILE]);
            item.setId(String.valueOf(id++));
            item.setNote(values[TotoItemColumn.NOTE]);
            item.setStartTime(Long.parseLong(values[TotoItemColumn.START_TIME]));
            item.setCreateTime(Long.parseLong(values[TotoItemColumn.CREATE_TIME]));
            item.setPriority(Integer.parseInt(values[TotoItemColumn.PRIORITY]));
            item.setFinished(Boolean.parseBoolean(values[TotoItemColumn.IS_FINISHED]));
            mData.add(item);
        }
        Collections.sort(mData, new Comparator<TodoItem>() {
            @Override
            public int compare(TodoItem item, TodoItem item2) {
                return item.createTime < item2.createTime ? -1 : 1;
            }
        });
        return mData;
    }

    private String toString(TodoItem item) {
        return item.id + "," + item.title + "," + item.note + "," + item.startTime + ","
                + item.createTime + "," + item.priority + "," + item.isFinished;
    }
}
