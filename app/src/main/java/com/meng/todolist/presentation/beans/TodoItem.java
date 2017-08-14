package com.meng.todolist.presentation.beans;

import java.io.Serializable;

/**
 * Created by mengzhou on 8/10/17.
 */

public class TodoItem implements Serializable{
    public String id;
    public String title;
    public String note;
    public long   startTime;
    public long   createTime;
    public int    priority;
    public boolean isFinished;

    public TodoItem(String title) {
        this.title = title;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
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
