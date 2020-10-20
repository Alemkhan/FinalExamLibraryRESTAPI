package com.project.library.model;

public class Cell {

    private int id;
    private boolean isCellAvailable;

    public Cell(int id) {
        this.id = id;
        this.isCellAvailable = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCellAvailable() {
        return isCellAvailable;
    }

    public void setCellAvailable(boolean cellAvailable) {
        isCellAvailable = cellAvailable;
    }
}
