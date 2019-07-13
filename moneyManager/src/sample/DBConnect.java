package sample;

import java.sql.*; // import sql from java library

public class DBConnect { //class to translate java to quary using connector

    public Connection con;
    public Statement st;
    public ResultSet rs;


    public String incomeAmount; // create a function to show the amount of income

    public static Connection getConnection() throws ClassNotFoundException, SQLException { // get to connect the database

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaljava","root",""); // admin user and password (its only for me)

        return connection;

    }

    DBConnect(){
        //constructor
        try {

            Class.forName("com.mysql.jdbc.Driver");
            // Create Connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaljava","root","");
            st = con.createStatement();
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }
    }

    void insertData( String date, String category , String description , String amount){ // function to insert data from the application and store it into database
        String sql = String.format("insert into history(date,category,description,amount) VALUES('%s', '%s' ,'%s' , '%s')"  , date, category ,description,amount);// the format
        System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Data Inserted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //update the expense in the database
    void updateExpense(String amount){
        //constructor
        String sql = String.format("update balance set amount = '%s' where id = 2",amount );
        System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Expense Updated");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    //update the income in the database
    void updateIncome(String amount){
        //constructor
        String sql = String.format("update balance set amount = '%s' where id = 1",amount );
        System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Expense Updated");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    //update the balance in database
    void updateBalance(String amount){
        //constructor
        String sql = String.format("update balance set amount = '%s' where id = 3",amount );
        System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Expense Updated");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    //take the data from income column in mySQL
    public String getIncome(){
        String sql = "select amount from balance where id = 1";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                incomeAmount = rs.getString("amount");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return incomeAmount;

    }
    //take the expense's data column from mySQL
    public String getExpense(){
        String sql = "select amount from balance where id = 2";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                incomeAmount = rs.getString("amount");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return incomeAmount;
    }
    //take the data from balance's column
    public String getBalance(){
        String sql = "select amount from balance where id = 3";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                incomeAmount = rs.getString("amount");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return incomeAmount;
    }

}


