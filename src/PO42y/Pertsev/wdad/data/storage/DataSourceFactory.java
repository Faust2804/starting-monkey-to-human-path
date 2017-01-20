package PO42y.Pertsev.wdad.data.storage;

import org.sqlite.SQLiteDataSource;
/**
 * Created by Faust on 19.01.2017.
 */

public class DataSourceFactory {
    public static javax.sql.DataSource createDataSource() throws ClassNotFoundException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:C:\\Users\\Faust\\Desktop\\Java\\starting-monkey-to-human-path\\src\\PO42y\\Pertsev\\wdad\\learn\\jdbc\\db.db");
        return dataSource;
    }

    public static javax.sql.DataSource createDataSource(String dburl) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(dburl);
        return dataSource;
    }
}
