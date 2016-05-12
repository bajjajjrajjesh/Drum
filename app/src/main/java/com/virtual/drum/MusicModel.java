package com.virtual.drum;

/**
 * Created by GaziRimon on 9/24/2015.
 */
public class MusicModel {

    public MusicModel(String name, String path) {
        this.name = name;
        this.path = path;
    }

    String name;
    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
