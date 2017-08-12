package com.meng.todolist.data;

import com.meng.todolist.presentation.beans.TodoItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoDataModel {
    public List<TodoItem> mData = new ArrayList<TodoItem>();

    private final SharedPreferenceManager preferenceManager;

    public TodoDataModel() {
        preferenceManager = SharedPreferenceManager.getInstance();
    }

    public void loadData() {
        mData = preferenceManager.loadData();
    }

    public void removeTodoItem(int position) {
        mData.remove(position);
    }

    public void addNewItem(TodoItem item) {
        mData.add(item);
    }

    public void saveData() {
        preferenceManager.saveData();
    }

}