package com.gmail.aba.task2;

import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Properties;

public class ServiceClass {
    public static <T> T loadFromProperties(Class<T> cls, String propertiesPath) throws Exception{
        String format = "";
        String numberProperty = "";
        FileReader reader = new FileReader(propertiesPath);
        Properties p = new Properties();
        p.load(reader);

        Object obj = cls.getConstructor().newInstance();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Property.class);

            if(annotation instanceof Property) {
                Property myAnnotation = (Property) annotation;
                if (!myAnnotation.name().equals("name"))
                    numberProperty = myAnnotation.name();
                if (!myAnnotation.format().equals("format"))
                    format = myAnnotation.format();
            }

            if (field.getType().equals(String.class)) {
                cls.getMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), String.class).invoke(obj, p.getProperty(field.getName()));
            }

            if (field.getType().equals(int.class)) {
                if (!field.getName().equals(numberProperty) && !numberProperty.equals("")){
                    field.setAccessible(true);
                    field.setInt(obj, Integer.parseInt(p.getProperty(numberProperty)));
                    continue;
                }
                cls.getMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), int.class).invoke(obj, Integer.parseInt(p.getProperty(field.getName())));
            }

            if (field.getType().equals(Instant.class)) {
                cls.getMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), Instant.class).invoke(obj, convertDateTime(format, p.getProperty(field.getName())));
            }
        }
        return (T) obj;
    }

    private static Instant convertDateTime(String format, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        TemporalAccessor temporalAccessor = formatter.parse(date);
        Instant fieldName = LocalDateTime.from(temporalAccessor).toInstant(ZoneOffset.UTC);
        return fieldName;
    }
}
