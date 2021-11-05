package carsharing;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static H2dataBase dataBase;
    static CompanyDao companies = new CompanyDaoImpl();
    static Scanner scanner = new Scanner(System.in);
    static ICarCollection carCollection = new CarCollection();

    public static void main(String[] args) {
        // write your code here
        createDir();
        connectDataBase(args);


        String sqlCommand = "CREATE TABLE COMPANY" +
                "(ID INT NOT NULL AUTO_INCREMENT, "
                +" NAME VARCHAR(255) NOT NULL UNIQUE, PRIMARY KEY (ID))";

        String sqlCommand2 = "CREATE TABLE CAR "+
                "(ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "NAME VARCHAR(20) NOT NULL UNIQUE , "+
                "COMPANY_ID INT NOT NULL, "+
                "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID) "+
                "REFERENCES COMPANY(ID))";


        dataBase.handleSqlUpdate(sqlCommand);
        dataBase.handleSqlUpdate(sqlCommand2);
        dataBase.handleSqlUpdate("ALTER TABLE company ALTER COLUMN ID RESTART WITH 1");
        dataBase.handleSqlQuery("ALTER TABLE car ALTER COLUMN ID RESTART WITH 1");


        getAllCars();
        getCompaniesFromDb();
        displayMenu();
        dataBase.closeConnections();
    }

    public static void displayMenu(){
        System.out.println("1. Log in as a manager \n"
        +"0. Exit");

        int opt = Integer.parseInt(scanner.nextLine());
        if(opt == 1) {
            company();
        }
    }

    public static void company(){
        System.out.println(
                        "1. Company list \n"
                        +"2. Create a company \n"
                        +"0. Back");

        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt){
            case 1: companyList(); break;
            case 2: createCompany(); break;
            default: displayMenu();
        }
    }

    public static void getCompaniesFromDb(){
        String sql = "SELECT * FROM COMPANY";
        try{
            ResultSet result = dataBase.handleSqlQuery(sql);
            companies.clearList();
            while(result.next()){
                int id = result.getInt("ID");
                String name = result.getString("NAME");
                companies.addCompany(new Company(name, id));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

    }
    public static void getAllCars(){
        String sql = "select * from car";
        ResultSet cars = dataBase.handleSqlQuery(sql);
        try{
            while(cars.next()){
                String name = cars.getString("NAME");
                int id = cars.getInt("ID");
                int company_id = cars.getInt("COMPANY_ID");
                carCollection.addCar(new Car(name, id , company_id));
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void companyList(){
        List<Company> list = companies.getAllCompanies();
        int i = 1;

        if(list.size() == 0){
            System.out.println("The company list is empty!");
            System.out.println("");
            company();
            return;
        }

        System.out.println("Choose a company: ");

        for(Company comp: list){
            System.out.println(i+". "+comp.getName());
            i++;
        }
        System.out.println("0. Back");

        int id = Integer.parseInt(scanner.nextLine());
        if(id == 0) company();

        String name = companies.getAllCompanies().get(id - 1).getName();
        System.out.println(name + " company:");
        singleCompany(id);
    }

    public static void singleCompany(int id){
        System.out.println("1. Car list \n"  +
                           "2. Create a car \n" +
                           "0. Back");
        int opt = Integer.parseInt(scanner.nextLine());
        switch (opt){
            case 1:{
                int i = 1;
                if(carCollection.filterCarById(id).size() == 0){
                    System.out.println("The car list is empty! \n");
                } else {
                    for (Car car: carCollection.filterCarById(id)){
                        System.out.println(i + ". " + car.getName());
                        i++;
                    }
                    System.out.println("");
                }
                singleCompany(id);
                break;
            }
            case 2: {
                System.out.println("Enter the car name: ");
                String name = scanner.nextLine();
                carCollection.addCar(new Car(name, 0, id));
                String sql = "insert into car (NAME, COMPANY_ID) values ('"+name+"',"+(id)+")";
                dataBase.handleSqlUpdate(sql);
                singleCompany(id);
                break;
            }
            default : company();
        }
    }

    public static void createCompany(){
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        String sql = "INSERT INTO COMPANY (NAME) " + "VALUES ('"+name+"')";
        handleSqlUpdate(sql);
        getCompaniesFromDb();
        System.out.println("The company was created!");
        System.out.println("");
        company();
    }

    public static void connectDataBase(String[] args){
        dataBase = new H2dataBase();

        try{
            String name = args[1];
            dataBase.makeDbConnection(name);

        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            if(dataBase.isConnection()){
                dataBase.makeDbConnection("test");
            }
        }

    }

    public static void createDir(){
        File file = new File("/Users/starlinlq/Desktop/Untitled/Car Sharing/Car Sharing/task/src/carsharing/db");
        if(file.mkdir()){
            System.out.println("Directory created");
        }
    }

    public static void handleSqlUpdate(String update){
        dataBase.handleSqlUpdate(update);
    }


}