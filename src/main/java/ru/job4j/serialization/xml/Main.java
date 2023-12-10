package ru.job4j.serialization.xml;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
      /*  Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        *//* Получаем контекст для доступа к АПИ *//*
        JAXBContext context = JAXBContext.newInstance(Person.class);
        *//* Создаем сериализатор *//*
        Marshaller marshaller = context.createMarshaller();
        *//* Указываем, что нам нужно форматирование *//*
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            *//* Сериализуем *//*
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        *//* Для десериализации нам нужно создать десериализатор *//*
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            *//* десериализуем *//*
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }*/

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}