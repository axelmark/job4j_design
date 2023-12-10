package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;


@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {
    @XmlAttribute
    String desc = "";
    @XmlAttribute
    int wind = 10;
    boolean rain = false;
    String[] seasons = {"Winter", "Spring", "Summer", "Autumn"};

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

        Weather weather = new Weather("Sunny", 3, false);
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
        }
    }

}
