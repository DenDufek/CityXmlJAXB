import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;

@XmlRootElement
@XmlType(propOrder = {"street", "house"})
class City {
    private String name;
    private String size;
    private String street;
    private String house;

    @XmlAttribute
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @XmlElement
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @XmlElement
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}

public class CityXmlJAXB {

    public static void main(String[] args) {
        generateXml();
        parseXml();
    }

    private static void generateXml() {
        try {
            City city = new City();
            city.setSize("big");
            city.setStreet("Mir Street");
            city.setHouse("15");

            JAXBContext context = JAXBContext.newInstance(City.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(city, new File("city_jaxb.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void parseXml() {
        try {
            JAXBContext context = JAXBContext.newInstance(City.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            City city = (City) unmarshaller.unmarshal(new File("city_jaxb.xml"));

            System.out.println("City: " + city.getSize());
            System.out.println("Street: " + city.getStreet());
            System.out.println("House: " + city.getHouse());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
