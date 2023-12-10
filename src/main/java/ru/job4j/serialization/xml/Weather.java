package ru.job4j.serialization.xml;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {
    @XmlAttribute
    String desc = "";
    @XmlAttribute
    int wind = 10;
    boolean rain = false;
    String[] seasons = {"Winter", "Spring", "Summer", "Autumn"};

    public String getDesc() {
        return desc;
    }

    public int getWind() {
        return wind;
    }

    public boolean isRain() {
        return rain;
    }

    public Weather() {
    }

    public Weather(String desc, int wind, boolean rain) {
        this.desc = desc;
        this.wind = wind;
        this.rain = rain;
    }

    @Override
    public String toString() {
        return "Weather{"
                + "desc='" + desc + '\''
                + ", wind=" + wind
                + ", rain=" + rain
                + ", seasons=" + Arrays.toString(seasons)
                + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {
        /*Weather weather = new Weather();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(weather);
        System.out.println(gson.toJson(weather));
        Weather weatherMod = gson.fromJson(json, Weather.class);
        System.out.println(weatherMod);*/

       /* Weather weather = new Weather("Sunny", 3, false);
        JAXBContext context = JAXBContext.newInstance(Weather.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(weather, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Weather result = (Weather) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }*/

        JSONObject jsonSeasons = new JSONObject("{\"seasons\":[\"Winter\", \"Spring\", \"Summer\", \"Autumn\"]}");

        List<String> list = new ArrayList<>();
        list.add("cloudy");
        list.add("clearly");
        JSONArray jsonArray = new JSONArray(list);

        Weather weather = new Weather("Sunny", 3, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("desc", weather.desc);
        jsonObject.put("rain", weather.rain);
        jsonObject.put("seasons", jsonSeasons);
        jsonObject.put("statuses", jsonArray);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(weather).toString());
    }
}
