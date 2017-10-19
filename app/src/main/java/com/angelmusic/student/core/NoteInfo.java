package com.angelmusic.student.core;

/**
 * Created by wangshuai on 2017/10/19.
 */

public class NoteInfo {

    //21-108
    private int note;
    //ui上的位置 从1开始计数
    private int noteIndex;
    private int keyIndex;
    //显示颜色
    private boolean idNoteRed;

    public NoteInfo() {
    }

    public NoteInfo(int note, int noteIndex, int keyIndex, boolean idNoteRed) {
        this.note = note;
        this.noteIndex = noteIndex;
        this.keyIndex = keyIndex;
        this.idNoteRed = idNoteRed;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getNoteIndex() {
        return noteIndex;
    }

    public void setNoteIndex(int noteIndex) {
        this.noteIndex = noteIndex;
    }

    public int getKeyIndex() {
        return keyIndex;
    }

    public void setKeyIndex(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public boolean isIdNoteRed() {
        return idNoteRed;
    }

    public void setIdNoteRed(boolean idNoteRed) {
        this.idNoteRed = idNoteRed;
    }


}
