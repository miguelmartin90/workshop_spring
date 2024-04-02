package org.example.dto;

public class FileMetadataDTO {

    private int validLines;
    private int invalidLines;

    public FileMetadataDTO() {
    }

    public FileMetadataDTO(int validLines, int invalidLines) {
        this.validLines = validLines;
        this.invalidLines = invalidLines;
    }

    public int getValidLines() {
        return validLines;
    }

    public void setValidLines(int validLines) {
        this.validLines = validLines;
    }

    public int getInvalidLines() {
        return invalidLines;
    }

    public void setInvalidLines(int invalidLines) {
        this.invalidLines = invalidLines;
    }
}
