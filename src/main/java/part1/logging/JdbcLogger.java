package part1.logging;

import java.sql.*;
import java.time.Instant;

public class JdbcLogger implements Logger {

   private static String dbUrlString;
   Connection connection;

   //constructor with url of destination db.
   public JdbcLogger(String url) {

     this.dbUrlString = url;

       try {
           this.connection = DriverManager.getConnection(this.dbUrlString);
       } catch (SQLException e) {
           throw new RuntimeException("Error occured trying to connect.");
       }
   }

    /**Logs details of an error message to provided database, log.db
     * via formatted SQL insert statement.
     * logs the message, level, and timestamp into the appropriate columns.
     * @param message string info- msg to be logged (details)
     * @param logLevel type of error (info, warning, error)
     */

    @Override
    public void log(String message, LogLevel logLevel) {

        try {
            PreparedStatement s = connection.prepareStatement(
                    "INSERT INTO main.log_entries (timestamp, level, message) VALUES (?, ?, ?);");
            s.setString(1, Instant.now().toString());
            s.setString(2, logLevel.toString());
            s.setString(3, message);

            s.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



