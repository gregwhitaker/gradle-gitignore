package com.github.gregwhitaker.gitignore;

import java.util.ArrayList;
import java.util.List;

public class GitIgnoreExtension {
    public static final String NAME = "gitignore";

    private boolean autoDetect = true;
    private String url;
    private List<String> facets = new ArrayList<>();

    public void noAutoDetect() {
        this.autoDetect = false;
    }

    public boolean isAutoDetect() {
        return autoDetect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getFacets() {
        return facets;
    }

    public void setFacets(List<String> facets) {
        this.facets = facets;
    }
}
