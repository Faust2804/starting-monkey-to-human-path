package PO42y.Pertsev.wdad.learn.rmi;

import PO42y.Pertsev.wdad.learn.xml.XmlTask;
import java.io.IOException;
import java.util.Calendar;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
/**
 * Created by Faust on 19.01.2017.
 */
class XmlDataManagerImpl implements XmlDataManager {
    private XmlTask xmlTask;

    public XmlDataManagerImpl(){
        try {
            xmlTask = new XmlTask();
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public double getBill(Building building, int flatNumber) {
        return xmlTask.getBill(building.getStreet(), building.getNumber(), flatNumber);
    }

    @Override
    public Flat getFlat(Building building, int flatNumber) {
        return xmlTask.getFlat(building, flatNumber);
    }

    @Override
    public void setTariff(String tariffName, double newValue) {
        try {
            xmlTask.setTariff(tariffName, newValue);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addRegistration(Building building, int flatNumber, Registration registration) {
        try {
            xmlTask.addRegistration(building.getStreet(),building.getNumber(), flatNumber,
                    registration.getDate().get(Calendar.YEAR), registration.getDate().get(Calendar.MONTH),
                    registration.getColdwater(),registration.getHotwater(),
                    registration.getElectricity(),registration.getGas());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
