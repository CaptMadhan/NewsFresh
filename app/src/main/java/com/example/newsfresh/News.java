package com.example.newsfresh;

public class News{
    String title,  content, source,  url,  imageUrl;
    News(String title, String content, String source,
         String url, String imageUrl){
        this.title = title;
        this.content = content;
        this.source = source;
        this.url = url;
        this.imageUrl = imageUrl;
    }
}
