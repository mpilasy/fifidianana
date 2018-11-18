package org.njarasoa.fifidianana.valimpifidianana.model;

import org.njarasoa.fifidianana.util.RemoteHelper;

import java.text.MessageFormat;

public class FileDefinition {
    private String remotePath;
    private String filepath;
    private String filename;

    public FileDefinition(String _fileId, String _filepath, String _filename) {
        remotePath = RemoteHelper.VALINY_URL + _fileId;
        filepath = "output/" + _filepath;
        filename = _filename;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getFullFilePath() {
        return MessageFormat.format("{0}/{1}", getFilepath(), getFilename());
    }

    public String getRemotePath() {
        return remotePath;
    }

    @Override
    public String toString() {
        return "FileDefinition{" +
                "remotePath='" + remotePath + '\'' +
                ", filepath='" + filepath + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
