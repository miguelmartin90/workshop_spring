package org.example.model;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Service;

import javax.swing.text.View;

@Service
public class FileMetadata {

    private String path;
    private String typeOfFile;

    private int validLines = 0;
    private int invalidLines = 0;

    public FileMetadata() {
    }

    public int getValidLines() {
        return validLines;
    }

    public void setLineValid() {
        this.validLines += 1;
    }

    public int getInvalidLines() {
        return invalidLines;
    }

    public void setLineInvalid() {
        this.invalidLines += 1;
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

    public void validatedLinesCounter(boolean validatedLine){
        if (validatedLine){
            this.setLineValid();
        } else {
            this.setLineInvalid();
        }
    }


}
