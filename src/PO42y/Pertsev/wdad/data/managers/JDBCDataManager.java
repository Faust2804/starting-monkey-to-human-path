package PO42y.Pertsev.wdad.data.managers;

import PO42y.Pertsev.wdad.data.storage.DataSourceFactory;
import PO42y.Pertsev.wdad.learn.rmi.Building;
import PO42y.Pertsev.wdad.learn.rmi.Flat;
import PO42y.Pertsev.wdad.learn.rmi.Registration;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * Created by Faust on 19.01.2017.
 */
public class JDBCDataManager implements DataManager {
    private Connection connection;

    public JDBCDataManager() throws ClassNotFoundException, SQLException {
        super();
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        connection = dataSourceFactory.createDataSource().getConnection();
        connection.setAutoCommit(false);
    }     
    @Override
    public double getBill(Building building, int flatNumber){
        try
        {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("" +
                    "SELECT sum(r.amount*t.cost) as total_cost\n" +
                    "FROM registrationstariffs r\n" +
                    "INNER JOIN tariffs t ON t.name == r.tariffs_name\n" +
                    "WHERE r.registrations_id IN (\n" +
                    "  SELECT DISTINCT r.id\n" +
                    "  FROM registrations r\n" +
                    "  WHERE r.date >= 1483214400000\n" +
                    "  AND r.date <= 1485806400000\n" +
                    "  AND r.flats_id ==\n" +
                    "      (\n" +
                    "        SELECT DISTINCT f.id\n" +
                    "        FROM flats f\n" +
                    "        WHERE f.buildings_id ==\n" +
                    "              (\n" +
                    "                SELECT DISTINCT b.id\n" +
                    "                FROM buildings b\n" +
                    "                WHERE b.number == '"+building.getNumber()+"'\n" +
                    "              )\n" +
                    "        AND f.number == '"+flatNumber+"'\n" +
                    "  )\n" +
                    ")");

            return result.getDouble("total_cost");
        }
        catch (SQLException ex) {
            System.out.println("some wrong" + ex.getMessage());
            return 0.0;
        }
    };


    @Override
    public Flat getFlat(Building building, int flatNumber) {
        try {
            Statement statemen = connection.createStatement();
            statemen.setQueryTimeout(30);
            ResultSet result = statemen.executeQuery("SELECT DISTINCT f.number\n" +
                    "FROM flats f\n" +
                    "WHERE f.buildings_id IN\n" +
                    "(SELECT DISTINCT b.id\n" +
                    "FROM buildings b\n" +
                    "WHERE b.street_id IN (SELECT DISTINCT s.id\n" +
                    "FROM street s\n" +
                    "WHERE s.name == '" + building.getStreet() + "')\n" +
                    "AND b.number == '" + building.getNumber() + "')");
            Flat flat = new Flat();
            flat.setNumber(result.getInt("flat number"));
            return flat;
        } catch (SQLException ex) {
            System.out.println("some wrong" + ex.getMessage());
            return null;
        }
    };
    @Override
    public void setTariff(String tariffName, double newCost) throws ClassNotFoundException, SQLException
    {
            try
            {
                PreparedStatement statement = connection.prepareStatement("UPDATE tariffs SET cost  = ? WHERE name =='"+tariffName+"'");
                statement.setDouble(1, newCost);
                statement.executeUpdate();
                connection.commit();
            }
            catch(SQLException ex)
            {
                System.out.println("some wrong" + ex.getMessage());
            }
    };
    @Override
    public void addRegistration (Building building, int flatNumber, Registration registration)
    {
        try
        {
            Statement state = connection.createStatement();
            ResultSet result = state.executeQuery("SELECT flats.number  flats_id \n" +
                    "        FROM  flats, buildings" +
                    "        WHERE flats.buildings_id == buildings.id" +
                    "        AND buildings.number == '"+building.getNumber()+"'" +
                    "        AND flats.number == '"+flatNumber+"'");

            int flat_id = result.getInt("flats_id");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO registrations (date, flats_id) VALUES('"+registration.getDate().getTimeInMillis()+"', '"+flat_id+"')");
            statement.executeUpdate();
            connection.commit();
        }
        catch(SQLException ex)
        {
            System.out.println("some wrong" + ex.getMessage());
        }

    };
}
