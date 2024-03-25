package org.example.model;

import org.springframework.stereotype.Service;


public class FilePath {

    private String path;
    private String typeOfFile;
    private int lineValid = 0;
    private int lineInvalid = 0;

    public FilePath() {
    }

    public int getLineValid() {
        return lineValid;
    }

    public void setLineValid() {
        this.lineValid += 1;
    }

    public int getLineInvalid() {
        return lineInvalid;
    }

    public void setLineInvalid() {
        this.lineInvalid += 1;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }
}
