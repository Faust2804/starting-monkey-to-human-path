package PO42y.Pertsev.wdad.learn.rmi;

import java.io.Serializable;
import java.rmi.Remote;
/**
 * Created by Faust on 19.01.2017.
 */
public interface XmlDataManager extends Remote, Serializable {
    double getBill(Building building, int flatNumber);
    Flat getFlat(Building building, int flatNumber);
    void setTariff(String tariffName, double newValue);
    void addRegistration (Building building, int flatNumber, Registration registration);
}