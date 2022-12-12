package com.gmail.aba.task2;


import java.time.Instant;

public class PropertiesModel {
    private String stringProperty;
    @Property(name = "numberProperty")
    private int numberProperty;
    @Property(format = "MM.dd.yyyy HH:mm")
    private Instant timeProperty;

    public PropertiesModel(String stringProperty, int numberProperty, Instant timeProperty) {
        this.stringProperty = stringProperty;
        this.numberProperty = numberProperty;
        this.timeProperty = timeProperty;
    }

    public PropertiesModel() {}

    public String getStringProperty() {
        return stringProperty;
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public int getNumberProperty() {
        return numberProperty;
    }

    public void setNumberProperty(int numberProperty) {
        this.numberProperty = numberProperty;
    }

    public Instant getTimeProperty() {
        return timeProperty;
    }

    public void setTimeProperty(Instant timeProperty) {
        this.timeProperty = timeProperty;
    }
}
