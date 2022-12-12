package com.gmail.aba.task2;

import org.junit.jupiter.api.Test;


import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ServiceClassTest {

    @Test
    void loadFromProperties() throws Exception {
        String path = "/Users/mishaabaiev/Java/JavaProjects/Example/src/main/java/org/example/file-properties.txt";
        PropertiesModel propertiesModel = ServiceClass.loadFromProperties(PropertiesModel.class, path);

        assertEquals(10, propertiesModel.getNumberProperty());
        assertEquals("value1", propertiesModel.getStringProperty());
        assertEquals(Instant.parse("2022-01-11T10:30:00Z"), propertiesModel.getTimeProperty());
    }

}