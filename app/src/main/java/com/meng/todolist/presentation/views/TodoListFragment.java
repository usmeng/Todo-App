package com.meng.todolist.presentation.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meng.todolist.R;
import com.meng.todolist.presentation.beans.TodoItem;

import java.util.List;

public class TodoListFragment extends Fragment {

    public static final String TAG = TodoListFragment.class.getSimpleName();
    private OnTodoItemClickListener mListener;
    private RecyclerView mRecyclerView;
    private List<TodoItem> mData;
    private TodoListAdapter mAdapter;
    private View mView;
    private View mEmptyBackground;

    public TodoListFragment() {
    }

    @SuppressWarnings("unused")
    public static TodoListFragment newInstance(int columnCount) {
        TodoListFragment fragment = new TodoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_todo_list, container, false);
        mEmptyBackground = mView.findViewById(R.id.empty_background);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new TodoListAdapter(mData, mListener);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchRemoveHelper(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        setEmptyBackgroundVisibility();

        return mView;
    }

    public void setEmptyBackgroundVisibility() {
        mEmptyBackground.setVisibility(mData.size() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTodoItemClickListener) {
            mListener = (OnTodoItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTodoItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void loadData(List<TodoItem> data) {
        mData = data;
    }

    public void notifyDatachanged() {
        mAdapter.notifyDataSetChanged();
    }

    public interface OnTodoItemClickListener {
        void removeItem(int position);
        void modifyItem(TodoItem item);
        void onEmptyTodo();
    }
}