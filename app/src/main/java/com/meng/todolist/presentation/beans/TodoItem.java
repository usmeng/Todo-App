package com.meng.todolist.presentation.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mengzhou on 8/10/17.
 */

public class TodoItem implements Serializable{
    public final String id;
    public String title;
    public String note;
    public long   startTime;
    public long   createTime;
    public int    priority;
    public boolean isFinished;

    public TodoItem(String id, String title, String note, long startTime, long createTime, int priority, boolean isFinished) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.startTime = startTime;
        this.createTime = createTime;
        this.priority = priority;
        this.isFinished = isFinished;
    }

    public TodoItem(String id, String title, String note, long startTime, int priority, boolean isFinished) {
        this(id, title, note, startTime, new Date().getTime(), priority, false);
    }

    public TodoItem(String id, String title, String note, long startTime, int priority) {
        this(id, title, note, startTime, priority, false);
    }

    public TodoItem(String id, String title, String note, long startTime) {
        this(id, title, note, startTime, 0);
    }

    public TodoItem(String id, String title, String note) {
        this(id, title, note, 0, 0);
    }

    public TodoItem(String id, String title) {
        this(id, title, "");
    }

    public TodoItem(String id) {
        this(id, "");
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", startTime=" + startTime +
                ", createTime=" + createTime +
                ", priority=" + priority +
                ", isFinished=" + isFinished +
                '}';
    }
}
