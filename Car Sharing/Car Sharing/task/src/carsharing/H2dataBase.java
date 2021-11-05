package carsharing;

import java.io.File;
import java.sql.*;

public class H2dataBase {

    private final String USER = "";
    private final String PASS = "";
    private static Connection conn  = null;
    private static Statement stmt = null;


    public void makeDbConnection(String db_name){
        String path ="/Users/starlinlq/Desktop/Untitled/Car Sharing/Car Sharing/task/src/carsharing/db/"+ db_name;
        String JBDC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:"+path;


       try{
           // 1. Register JDBC driver
           Class.forName(JBDC_DRIVER);

            // 2. connect to to database
           System.out.println("Connecting to database");
           conn = DriverManager.getConnection(DB_URL, USER, PASS);

           // all changes are automatically saved in the database
           conn.setAutoCommit(true);

           //3. create sql statement
           stmt = conn.createStatement();

       } catch(SQLException se){
           System.out.println("Sql error: "+ se.getMessage());
           se.printStackTrace();

       } catch(Exception e ){
           System.out.println("Class.forName error:"+ e.getMessage());
           e.printStackTrace();
       }



    }

    public void handleSqlUpdate(String sql){
        try {
            stmt.executeUpdate(sql);

        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public ResultSet handleSqlQuery(String query){
        try{
            return stmt.executeQuery(query);
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }


    public void closeConnections(){
        try {
            if(stmt != null)  stmt.close();
        } catch (SQLException ex){

        }
        try {
            if(conn != null) conn.close();
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public boolean isConnection(){
        return conn == null;
    }


}
