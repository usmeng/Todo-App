package com.meng.todolist.presentation.views;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.meng.todolist.R;
import com.meng.todolist.presentation.beans.TodoItem;
import com.meng.todolist.utils.ContextUtil;
import com.meng.todolist.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class AddOrEditTodoFragment extends Fragment {

    private static final String CURRENT_TODO_ITEM = "current_todo_item";
    public static final String TAG = AddOrEditTodoFragment.class.getSimpleName();

    private TodoItem mTodoItem;
    private OnOperateTodoItemListener mListener;
    private boolean isSaveMode;

    public AddOrEditTodoFragment() {
    }

    public static AddOrEditTodoFragment newInstance(TodoItem item) {
        AddOrEditTodoFragment fragment = new AddOrEditTodoFragment();
        if(item == null) return fragment;

        Bundle args = new Bundle();
        args.putSerializable(CURRENT_TODO_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTodoItem = (TodoItem) getArguments().getSerializable(CURRENT_TODO_ITEM);
            isSaveMode = mTodoItem != null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_or_edit_todo, container, false);
        final EditText title = view.findViewById(R.id.add_todo_title);
        final EditText note = view.findViewById(R.id.add_todo_note);
        final TextView date = view.findViewById(R.id.add_todo_date);
        final TextView time = view.findViewById(R.id.add_todo_time);
        final SeekBar  priority = view.findViewById(R.id.priority_seekBar);

        if(isSaveMode) {
            title.setText(mTodoItem.title);
            note.setText(mTodoItem.note);
            priority.setProgress(mTodoItem.priority);
        }
        final long dateTime = isSaveMode ? mTodoItem.startTime : new Date().getTime();
        String[] dateString = DateUtil.convert(dateTime).split(" ");
        date.setText(dateString[0]);
        time.setText(dateString[1]);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(dateTime);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(year + "/" + month + "/" + day);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(dateTime);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog builder = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        time.setText(hour + ":" + minute);
                    }
                }, hour, minute, true);
                builder.show();
            }
        });

        view.findViewById(R.id.save_floating_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleValue = title.getText().toString();
                if(titleValue.length() == 0) {
                    Snackbar.make(view, "title cannot be empty", Snackbar.LENGTH_LONG).show();
                    return;
                }

                int priorityValue = priority.getProgress();
                if(mTodoItem == null) mTodoItem = new TodoItem(titleValue);
                mTodoItem.note = note.getText().toString();
                mTodoItem.priority = priorityValue;
                mTodoItem.startTime = DateUtil.convert(date.getText().toString()
                        + " " + time.getText().toString());

                if(isSaveMode) {
                    mListener.onItemModified(mTodoItem, true);
                } else {
                    mListener.onNewItemAdded(mTodoItem, true);
                }

                backPressed();
            }
        });
        return view;
    }

    private void backPressed() {
        mListener = null;
        getActivity().getSupportFragmentManager().popBackStack();
        ContextUtil.hideKeyboard(getActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOperateTodoItemListener) {
            mListener = (OnOperateTodoItemListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnOperateTodoItemListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnOperateTodoItemListener {
        void onNewItemAdded(TodoItem item, boolean added);
        void onItemModified(TodoItem item, boolean modified);
    }
}