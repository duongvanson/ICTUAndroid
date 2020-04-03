package dev.duongson.hocintent;

import java.io.Serializable;

public class Post implements Serializable {
    String title, content;
    int view;

    public Post(String title, String content, int view) {
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
