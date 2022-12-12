package com.gmail.aba.task1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class JsonToXMLClass implements Runnable {
     private Map<String, Long> map = new HashMap<>();

     private File directory = new File("/Users/mishaabaiev/Java/JavaProjects/HomeworkWeek5_6/src/main/java/com/gmail/aba/task1/jsonfiles/");
     private File[] files = directory.listFiles();

    public File[] getFiles() {
        return files;
    }

    public Map<String, Long> sortedMap() {
        Map<String, Long> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }

    public void writeToXML(Map<String, Long> map) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        xmlEncoder.writeObject(map);
        xmlEncoder.close();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("task2.xml"))) {
            bw.write(bos.toString());
        } catch (IOException ex) {
            System.out.println("Some exception");
        }
    }

    @Override
    public void run() {
        System.out.println("Start " + Thread.currentThread().getName());

        ObjectMapper objectMapper = new ObjectMapper();
        for (File file : files) {
            if (file.isFile()) {
                List<TrafficViolation> myObjects = null;
                try {
                    myObjects = objectMapper.readValue(new FileReader("/Users/mishaabaiev/Java/JavaProjects/HomeworkWeek5_6/src/main/java/com/gmail/aba/task1/jsonfiles/" + file.getName()), new TypeReference<List<TrafficViolation>>() {});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                synchronized (this) {
                    for (TrafficViolation t : myObjects) {

                        if (map.containsKey(t.getType()) && !map.isEmpty())
                            map.put(t.getType(), map.get(t.getType()) + t.getFine_amount());
                        else
                            map.put(t.getType(), t.getFine_amount());
                    }
                }
            }
        }
        System.out.println("Finish " + Thread.currentThread().getName());
    }
}

