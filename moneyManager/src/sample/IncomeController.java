package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IncomeController  extends DBConnect { //class to control the input from income and store it to data base


    @FXML
    TextField amountBox; // the box in the app to insert the amoun of income

    String amountAsText = ""; // title of the box above

    DBConnect connect = new DBConnect();

    public void saveIncome(){ // store the inserted amount to database

        amountAsText = amountBox.getText();//take the variable from amount box to the amount text

        int income = Integer.parseInt(getIncome()); //set the data type of income to be integer
        int balance = Integer.parseInt(getBalance());//set the data type of balance to be integer
        Person.incomeAmount = income += Integer.parseInt(amountAsText);// the math
        Person.balanceAmount = balance += Integer.parseInt(amountAsText);// the math


        String incomeAmount = Integer.toString(Person.incomeAmount);
        String balanceAmount = Integer.toString(Person.balanceAmount);
        connect.updateIncome(incomeAmount);//update the recently added amount of income
        connect.updateBalance(balanceAmount);// update the new inserted data to be balance

        Controller.incomeStage.close();

        System.out.println(Person.balanceAmount);
        System.out.println(Person.incomeAmount);

    }

}
