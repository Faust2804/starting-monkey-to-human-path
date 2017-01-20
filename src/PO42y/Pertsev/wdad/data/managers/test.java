package PO42y.Pertsev.wdad.data.managers;

import PO42y.Pertsev.wdad.learn.rmi.Building;
import PO42y.Pertsev.wdad.learn.rmi.Registration;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Faust on 20.01.2017.
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        JDBCDataManager jdbcDataManager = new JDBCDataManager();

        jdbcDataManager.setTariff("gas", 1.0);

        Building building = new Building("N.Panova", 45);
        Registration registration = new Registration();

        Calendar calendar = Calendar.getInstance();

        long time = 1484596800;

        calendar.setTimeInMillis(time*1000);

        System.out.println(calendar.getTime());

        registration.setDate(calendar);
        jdbcDataManager.addRegistration(building,7,registration);

        System.out.println(jdbcDataManager.getBill(building, 7));
    }
}
