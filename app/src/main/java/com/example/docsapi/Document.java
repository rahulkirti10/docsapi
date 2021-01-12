package com.example.docsapi;

public class Document {
    private String doc_id;

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public Document() {
    }

    public Document(String doc_id) {
        this.doc_id = doc_id;
    }
}
