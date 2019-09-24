package com.eaj.ufrn.mobilemilk.Modelo;

import java.util.Arrays;

public class Arquivo {

    private String id;
    private String fileName;
    private String fileType;
    private byte[] data;
    private String fileDownloadUri;
    private long size;

    public Arquivo() {
    }

    public Arquivo(String id, String fileName, String fileType, byte[] data, String fileDownloadUri, long size) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.fileDownloadUri = fileDownloadUri;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Arquivo{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", size=" + size +
                '}';
    }
}
