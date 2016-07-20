package com.wzy.zhihudaily.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LatestBean {

    private String date;

    @SerializedName("stories")
    private List<StoriesBean> todayStories;

    @SerializedName("top_stories")
    private List<TopStoriesBean> topStories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getTodayStories() {
        return todayStories;
    }

    public void setTodayStories(List<StoriesBean> todayStories) {
        this.todayStories = todayStories;
    }

    public List<TopStoriesBean> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStoriesBean> topStories) {
        this.topStories = topStories;
    }

    public static class StoriesBean {
        private int type;
        private int id;

        @SerializedName("gaPrefix")
        private String gaPrefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        @SerializedName("ga_prefix")
        private String gaPrefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
