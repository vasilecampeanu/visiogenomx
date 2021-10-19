package com.visiogenomx.app.classes;

public class NGLParams
{
    /**
     * Life cycle 
     */
    public NGLParams(String structUrl)
    {
        this.url = "//cdn.jsdelivr.net/gh/nglviewer/ngl@v0.9.3/dist/ngl.js";
        this.structUrl = structUrl;
        this.setSize(500);
    }

    /**
     * Getters and Setters
     */
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) { this.url = url; }

    public String getStructUrl() {
        return this.structUrl;
    }
    public void setStructUrl(String structUrl) {
        this.structUrl = structUrl;
    }

    public int getSize() {
        return this.size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Private properties
     */
    private String url;
    private String structUrl;
    private int size;
}