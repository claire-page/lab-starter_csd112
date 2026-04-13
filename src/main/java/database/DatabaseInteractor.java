package database;

import core.RunData;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInteractor {

    private static String dbUrl;
    Connection connection;

    public DatabaseInteractor(String url){
        this.dbUrl = url;
        try {
            this.connection = DriverManager.getConnection(this.dbUrl, "root", "Qs3al97-2ab");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendData(RunData runData){


        try {

            PreparedStatement s = this.connection.prepareStatement(

                    "INSERT INTO loggedruns (time, date, name, BACKTRACKS, KeyStrokes, charcount, wordcount)  VALUES " +
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
            throw new RuntimeException(e);
        }
    }
//gets the last 5 entries from the db.

    /**
     * retrieves data from last N entries in the form of an array of arrays containing strings.
     */
    public List<RunData> retrieveLastNEntries(int num){

        System.out.println("retrieving data");
            try {
                PreparedStatement s = this.connection.prepareStatement("SELECT * from loggedruns  ORDER BY runID DESC LIMIT "+ num +" ;");
                ResultSet results = s.executeQuery();

                ArrayList<RunData> arraylist = new ArrayList<>();

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
                throw new RuntimeException(e);
            }


    }



}
