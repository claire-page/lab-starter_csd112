package part1.logging;

import java.sql.*;
import java.time.Instant;

public class JdbcLogger extends ConsoleLogger {

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

//TODO:IF I HAVE TIME -> make it check for a table, able to customize which one?? think that would fuck with the signature tho...kabsdkh
    /**i mean it's likely it's only applicable to this one database. how else would
     * we know where to insert what values?? there's probably a way to populate left-to-right... I"d be
     * really surprised if no one had done that.
     *connection metadata....
     */

    @Override
    public void log(String message, LogLevel logLevel) {

        //things to check:
        //does database have a table?
        //checking to see if table has enough columns to store the log.
        //ideally will get column names and table names from the database info.
        //just testing to see if it works in the first place.



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



