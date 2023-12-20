package com.company.laeventa.models;

public class Area {

    private String name;
    private String capacity;
    private boolean isSports;

    public Area(String name, String capacity) {
        this.name = name;
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public boolean isSports() {
        return isSports;
    }

    public void setSports(boolean sports) {
        isSports = sports;
    }
}
