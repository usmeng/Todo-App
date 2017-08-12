package com.meng.todolist.presentation.views;

import com.meng.todolist.presentation.beans.TodoItem;

import java.util.List;

/**
 * Created by mengzhou on 8/10/17.
 */

public interface IMainView {

    public void showTodoList(List<TodoItem> data);

//    public void showTodoItem(TodoItem todoItem);
//
//    public void deleteTodoItem(int position);
//
//    public void addTodoItem();
}
