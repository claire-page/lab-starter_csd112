package database;

import controllers.Control;
import core.RunData;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseInteractor {

   Connection connection;
   Runnable onError;
    public DatabaseInteractor(Runnable errorDisplay){
        this.onError = errorDisplay;

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("db.properties"));

             this.connection = DriverManager.getConnection( properties.getProperty("url"),  "root", properties.getProperty("password") );
        } catch (IOException | SQLException e) {
            errorDisplay.run();
        }
    }

    public void sendData(RunData runData){


        try {

            PreparedStatement s = this.connection.prepareStatement(

                    "INSERT INTO laggedruns (time, date, name, BACKTRACKS, KeyStrokes, charcount, wordcount)  VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?);"
            );
            s.setDouble(1, runData.time());
            s.setDate(2, new Date(System.currentTimeMillis()));
            s.setString(3, runData.name());
            s.setInt(4, runData.backTracked());
            s.setInt(5, runData.keyStrokes());
            s.setInt(6, runData.characterCount());
            s.setInt(7, runData.wordCount());

            s.execute();

        } catch (SQLException e) {
            onError.run();
        }
    }
//gets the last 5 entries from the db.

    /**
     * retrieves data from last N entries in the form of an array of arrays containing strings.
     */
    public List<RunData> retrieveLastNEntries(int num){

        System.out.println("retrieving data");
        ArrayList<RunData> arraylist = new ArrayList<>();

            try {
                PreparedStatement s = this.connection.prepareStatement("SELECT * from loggedruns  ORDER BY runID DESC LIMIT "+ num +" ;");
                ResultSet results = s.executeQuery();


                while (results.next()){
                    RunData entry = new RunData(results.getDouble("time"),
                                                results.getString("name"),
                                                results.getInt("KeyStrokes"),
                                                results.getInt("BACKTRACKS"),
                                                results.getInt("wordcount"),
                                                results.getInt("charcount"));
                    arraylist.add(entry);
                }
                return(arraylist.stream().toList());


            } catch (SQLException e) {

            }

         return(arraylist.stream().toList());
    }


}
