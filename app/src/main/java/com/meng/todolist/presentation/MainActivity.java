package com.meng.todolist.presentation;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;

import com.meng.todolist.R;
import com.meng.todolist.presentation.base.BaseActivity;
import com.meng.todolist.presentation.beans.TodoItem;
import com.meng.todolist.presentation.presenters.MainPresenter;
import com.meng.todolist.presentation.views.AddOrEditTodoFragment;
import com.meng.todolist.presentation.views.IMainView;
import com.meng.todolist.presentation.views.TodoListFragment;

import java.util.List;

public class MainActivity extends BaseActivity implements IMainView,
        TodoListFragment.OnTodoItemClickListener,
        AddOrEditTodoFragment.OnOperateTodoItemListener {

    private FragmentManager mFragmentManager;
    private MainPresenter mMainPresenter;
    private TodoListFragment mTodoListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        mMainPresenter = new MainPresenter(this);
        mMainPresenter.loadData();
    }

    public TodoListFragment getTodoListFragment() {
        if(mTodoListFragment == null) {
            mTodoListFragment = (TodoListFragment) mFragmentManager.findFragmentByTag(TodoListFragment.TAG);
        }
        if(mTodoListFragment == null) {
            mTodoListFragment = new TodoListFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.todo_list_fragment, mTodoListFragment, TodoListFragment.TAG)
                    .commit();
        }
        return mTodoListFragment;
    }

    @Override
    public void removeItem(int position) {
        mMainPresenter.removeTodoItem(position);
    }

    @Override
    public void modifyItem(TodoItem item) {
        AddOrEditTodoFragment editTodoFragment = AddOrEditTodoFragment.newInstance(item);
        mFragmentManager.beginTransaction()
                .add(R.id.todo_list_fragment, editTodoFragment, AddOrEditTodoFragment.TAG)
                .addToBackStack("").hide(mTodoListFragment).commit();
    }

    @Override
    public void onEmptyTodo() {
        getTodoListFragment().setEmptyBackgroundVisibility();
    }

    public void onAddTodoItem(View view) {
        AddOrEditTodoFragment editTodoFragment = AddOrEditTodoFragment.newInstance(null);
        mFragmentManager.beginTransaction()
                .add(R.id.todo_list_fragment, editTodoFragment, AddOrEditTodoFragment.TAG)
                .addToBackStack("").hide(mTodoListFragment).commit();
    }

    @Override
    public void showTodoList(List<TodoItem> data) {
        getTodoListFragment().loadData(data);
    }

    @Override
    public void onNewItemAdded(TodoItem item, boolean added) {
        if(added) mMainPresenter.addNewItem(item);
        getTodoListFragment().notifyDatachanged();
        getTodoListFragment().setEmptyBackgroundVisibility();
    }

    @Override
    public void onItemModified(TodoItem item, boolean modified) {
        if(modified) mMainPresenter.saveModifiedItem(item);
        getTodoListFragment().notifyDatachanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.saveData();
    }
}