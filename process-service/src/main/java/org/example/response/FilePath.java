package org.example.response;

public class FilePath {

    private String path;
    private String typeOfFile;

    public FilePath(String path, String typeOfFile) {
        this.path = path;
        this.typeOfFile = typeOfFile;
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
