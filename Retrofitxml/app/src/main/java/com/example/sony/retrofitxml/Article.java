package com.example.sony.retrofitxml;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by sony on 2017/4/6.
 */

@Root(name = "item",strict = false)
public class Article {
    @Element(name="title")
    private String title;

    @Element(name="link")
    private String link;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }
}
