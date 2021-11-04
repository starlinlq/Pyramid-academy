package carsharing;

import java.io.File;

public class Main {
    static H2dataBase dataBase;
    public static void main(String[] args) {
        // write your code here
        createDir();
        dataBase = new H2dataBase();
        try{
            String name = args[1];
            dataBase.makeDbConnection(name);

        } catch(Exception ex){

        } finally {
            if(dataBase.isConnection()){
                dataBase.makeDbConnection("test");
            }
        }

        handleSqlCommands();
       // dataBase.closeConnections();
    }


    public static void createDir(){
        File file = new File("/Users/starlinlq/Desktop/Untitled/Car Sharing/Car Sharing/task/src/carsharing/db");
        if(file.mkdir()){
            System.out.println("Directory created");
        }
    }

    public static void handleSqlCommands(){
        String sqlCommand = "CREATE TABLE COMPANY" +
                "(ID INT, "
                +" NAME VARCHAR(255));";

        dataBase.handleSql(sqlCommand);
    }
}