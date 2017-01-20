package PO42y.Pertsev.wdad.data.managers;

import PO42y.Pertsev.wdad.learn.rmi.Building;
import PO42y.Pertsev.wdad.learn.rmi.Flat;
import PO42y.Pertsev.wdad.learn.rmi.Registration;

import java.io.Serializable;
import java.rmi.Remote;
import java.sql.SQLException;

/**
 * Created by Faust on 19.01.2017.
 */
public interface DataManager extends Remote, Serializable {
    double getBill(Building building, int flatNumber);
    Flat getFlat(Building building, int flatNumber);
    void setTariff(String tariffName, double newValue) throws ClassNotFoundException, SQLException;
    void addRegistration (Building building, int flatNumber, Registration registration);
}