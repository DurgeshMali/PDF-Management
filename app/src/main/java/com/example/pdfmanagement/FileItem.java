package com.example.pdfmanagement;

public class FileItem {
    public final String name, meta, type; // type: pdf/doc/xls/ppt
    public FileItem(String name, String meta, String type) {
        this.name = name;
        this.meta = meta;
        this.type = type;
    }
}
