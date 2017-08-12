package com.meng.todolist.presentation.presenters;

import com.meng.todolist.data.TodoDataModel;
import com.meng.todolist.presentation.beans.TodoItem;
import com.meng.todolist.presentation.views.IMainView;

/**
 * Created by mengzhou on 8/10/17.
 */

public class MainPresenter {

    private final IMainView iMainView;
    private final TodoDataModel mDataModel;

    public MainPresenter(IMainView mainView) {
        this.iMainView = mainView;

        mDataModel = new TodoDataModel();
    }

    public void loadData() {
        mDataModel.loadData();
        iMainView.showTodoList(mDataModel.mData);
    }

    public void saveData() {
        mDataModel.saveData();
    }

    public void removeTodoItem(int item) {
        mDataModel.removeTodoItem(item);
    }

    public void markItemFinished(TodoItem item, boolean selected) {
//        mDataModel.markItemFinished(item, selected);
    }

    public void addNewItem(TodoItem item) {
        mDataModel.addNewItem(item);
    }

    public void saveModifiedItem(TodoItem item) {
//        mDataModel.saveModifiedItem(item);
    }
}
