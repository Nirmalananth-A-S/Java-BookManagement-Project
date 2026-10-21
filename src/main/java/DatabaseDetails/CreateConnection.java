
package DatabaseDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {

    public static Connection getCon() throws SQLException {
        try {
            Class.forName(Dbinfo.drivers);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        return DriverManager.getConnection(
                Dbinfo.url,
                Dbinfo.userName,
                Dbinfo.password
        );
    }
}
