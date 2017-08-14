package com.meng.todolist.presentation.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.meng.todolist.R;
import com.meng.todolist.presentation.beans.TodoItem;
import com.meng.todolist.utils.DateUtil;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder>
        implements ItemTouchRemoveHelper.ItemTouchHelperAdapter {

    private final List<TodoItem> mValues;
    private final TodoListFragment.OnTodoItemClickListener mListener;

    public TodoListAdapter(List<TodoItem> items, TodoListFragment.OnTodoItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mView.setAlpha(holder.mItem.isFinished ? 0.5f : 1);
        holder.mDateView.setText(DateUtil.convert(holder.mItem.startTime));
        holder.mTittleView.setText(holder.mItem.title);
        holder.mStatusCheckBox.setChecked(holder.mItem.isFinished);
        // set image alpha by priority value, 0 being transparent and 255 being opaque
        holder.mPriorityImage.setImageAlpha(holder.mItem.priority * 255 / 100);
        holder.mStatusCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selected) {
                holder.mItem.isFinished = selected;
                holder.mView.setAlpha(selected ? 0.7f : 1);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.modifyItem(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemRemoved(int position) {
        mListener.removeItem(position);
        if(mValues.size() == 0) mListener.onEmptyTodo();
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mDateView;
        final TextView mTittleView;
        final CheckBox mStatusCheckBox;
        final ImageView mPriorityImage;
        TodoItem mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mDateView = view.findViewById(R.id.todo_time);
            mTittleView = view.findViewById(R.id.todo_title);
            mStatusCheckBox = view.findViewById(R.id.checked_text_view);
            mPriorityImage = view.findViewById(R.id.todo_priority_star);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTittleView.getText() + "'";
        }
    }
}