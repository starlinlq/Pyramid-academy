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
    static ICustomerCollection customerCollection = new CustomerCollection();

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
                "IS_RENTED BOOLEAN DEFAULT FALSE, "+
                "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID) "+
                "REFERENCES COMPANY(ID))";

        String sqlCommand3 = "CREATE TABLE CUSTOMER " +
                             "(ID INT AUTO_INCREMENT PRIMARY KEY, " +
                             "NAME VARCHAR(20) NOT NULL UNIQUE, " +
                             "RENTED_CAR_ID INT, " +
                             "CONSTRAINT FK_CAR FOREIGN KEY (RENTED_CAR_ID) "+
                             "REFERENCES CAR(ID))";

        dataBase.handleSqlUpdate(sqlCommand);
        dataBase.handleSqlUpdate(sqlCommand2);
        dataBase.handleSqlUpdate(sqlCommand3);

        dataBase.handleSqlUpdate("ALTER TABLE company ALTER COLUMN ID RESTART WITH 1");
        dataBase.handleSqlUpdate("ALTER TABLE car ALTER COLUMN ID RESTART WITH 1");
        dataBase.handleSqlUpdate("ALTER TABLE customer ALTER COLUMN ID RESTART WITH 1");



        getAllCustomers();
        getAllCars();
        getCompaniesFromDb();
        displayMenu();
        dataBase.closeConnections();
    }

    public static void displayMenu(){

        System.out.println("1. Log in as a manager \n"
                          +"2. Log in as a customer \n" +
                           "3. Create a customer \n" +
                           "0. Exit");

        int opt = Integer.parseInt(scanner.nextLine());
        switch (opt){
            case 1: company(); break;
            case 2: customer(); break;
            case 3: createCustomer(); break;
            default:
        }
    }

    public static void customer(){

        if(customerCollection.getAllCustomers().size() == 0){
            System.out.println("The customer list is empty!");
            displayMenu();
            return;
        }

        System.out.println("Customer list:");
        int i  = 1;
        for(Customer cus: customerCollection.getAllCustomers()){
            System.out.println(i +". "+cus.getName());
            i++;
        }
        System.out.println("0. Back");

        int customerId = Integer.parseInt(scanner.nextLine());
        if(customerId == 0) {
            displayMenu();
            return;
        }
        displayRentMenu(customerId);

    }

    public static void displayRentMenu(int customerId){
        System.out.println("1. Rent a car \n"+
                "2. Return a rented car \n"+
                "3. My rented car \n" +
                "0. Back");

        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt){
            case 1: {
                if(alreadyRent(customerId)){
                    System.out.println("You've already rented a car!");
                } else
                    rentACar(customerId);
                break;
            }
            case 2:{
                returnACar(customerId);
                break;
            }
            case 3:{
                rentedCarDetails(customerId);
                break;
            }
            default: {
                customer();
                return;
            }
        }
        System.out.println("");
        displayRentMenu(customerId);
    }

    public static boolean alreadyRent(int customerId){
        String sql = "select * from customer where id = "+customerId+"";
        ResultSet customer = dataBase.handleSqlQuery(sql);
        try{
           while(customer.next()){
               int rentId = customer.getInt("RENTED_CAR_ID");
               if(rentId > 0) return true;
           }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static void rentedCarDetails(int customerId){
        String sql = "SELECT * FROM CUSTOMER WHERE ID = "+customerId+" limit 1";
        String carName = "";
        String compName = "";
        int carId = 0;
        int compId = 0;
        try {
            ResultSet customer = dataBase.handleSqlQuery(sql);
            while(customer.next()){
                carId = customer.getInt("RENTED_CAR_ID");

            }
            if(!(carId > 0)){
                System.out.println("You didn't rent a car!");
                displayRentMenu(customerId);
                return;
            }
            ResultSet car = dataBase.handleSqlQuery("SELECT * FROM CAR WHERE ID = "+carId+" limit 1");

            while(car.next()){
                carName = car.getString("NAME");
                 compId = car.getInt("COMPANY_ID");
            }

            ResultSet company = dataBase.handleSqlQuery("SELECT * FROM COMPANY WHERE ID = "+compId+" limit 1");
            while(company.next()){
                compName = company.getString("NAME");
            }

            System.out.println("Your rented car: \n" +
                    carName +"\n" +
                    "Company: \n"  +
                    compName);

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    };

    public static void rentACar(int customerId){
        System.out.println("Choose a company: ");

        int i = 1;
        for(Company comp: companies.getAllCompanies()){
            System.out.println(i +". "+comp.getName());
            i++;
        }

        i = 1;
        System.out.println("0. Back");

        int companyId = Integer.parseInt(scanner.nextLine());
        if (companyId < 1)customer();

        String name = companies.getAllCompanies().get(companyId - 1).getName();
        System.out.println("Choose a car:");

        if(carCollection.filterCarById(companyId).size() == 0){
            System.out.println("No available cars in the name "+ name);
            System.out.println("");
            rentACar(customerId);
           return;
        }

        for(Car car: carCollection.filterCarById(companyId)){
            if(!car.is_rented()){
                System.out.println(i +". "+car.getName());
                i++;
            }
        }
        System.out.println("0. Back");

        int carId = Integer.parseInt(scanner.nextLine());
        if(carId == 0){
            rentACar(customerId);
            return;
        }
        String sql = "update car set is_rented = true where id = "+ carId+"";
        dataBase.handleSqlUpdate(sql);
        String sql2 = "UPDATE CUSTOMER SET RENTED_CAR_ID = "+carId+" WHERE ID = "+customerId+"";
        dataBase.handleSqlUpdate(sql2);

        String carName = carCollection.getAllCars().get(carId - 1).getName();
        System.out.println("You rented '"+carName+"'");
        System.out.println("");
        displayRentMenu(customerId);
    }

    public static void returnACar(int customerId){

        String sql = "UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = "+customerId+" LIMIT 1";
        String sql2 = "SELECT * FROM CUSTOMER WHERE ID = "+customerId+"";
        ResultSet customer = dataBase.handleSqlQuery(sql2);

        try {
            while(customer.next()){
                int rented_car_id = customer.getInt("RENTED_CAR_ID");

                if(!(rented_car_id > 0)){
                    System.out.println("You didn't rent a car!");
                    return;
                }

                handleSqlUpdate("UPDATE CAR SET IS_RENTED = FALSE WHERE ID= "+rented_car_id+"");
                handleSqlUpdate(sql);
                System.out.println("You've returned a rented car!");
                System.out.println("");

            }
        } catch(SQLException ex){
           // System.out.println(ex.getMessage());
        }
    }

    public static void createCustomer(){
        int id = customerCollection.getAllCustomers().size();
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        String sql = "insert into customer (name) values ('"+name+"')";
        dataBase.handleSqlUpdate(sql);
        customerCollection.addCustomer(new Customer(name , id, 0));
        System.out.println("The customer was added!");
        System.out.println("");
        displayMenu();
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

    public static void getAllCustomers(){
        String sql = "select * from customer";
        ResultSet customers = dataBase.handleSqlQuery(sql);
        try{
            while(customers.next()){
                String name = customers.getString("NAME");
                int id = customers.getInt("ID");
                int rented_car_id = customers.getInt("RENTED_CAR_ID");
                customerCollection.addCustomer(new Customer(name, id , rented_car_id));
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void getAllCars(){
        String sql = "select * from car";
        ResultSet cars = dataBase.handleSqlQuery(sql);
        carCollection.clearCar();
        try{
            while(cars.next()){
                String name = cars.getString("NAME");
                int id = cars.getInt("ID");
                int company_id = cars.getInt("COMPANY_ID");
                boolean is_rented  = cars.getBoolean("IS_RENTED");
                carCollection.addCar(new Car(name, id , company_id, is_rented));
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

                String sql = "insert into car (NAME, COMPANY_ID) values ('"+name+"',"+(id)+")";
                dataBase.handleSqlUpdate(sql);
                getAllCars();
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