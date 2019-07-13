package sample;
// import from the java library
import javafx.fxml.FXML;
import javafx.scene.control.TextField; //import the gui from scene builder to the IntelliJ

import java.time.LocalDate;//improt local date from the computer
import java.time.format.DateTimeFormatter;//import the format from date

public class ExpenseController  extends DBConnect {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");//set the date format
    LocalDate date = LocalDate.now();//using the current date and time

    @FXML
    TextField amountBox;
    @FXML
    TextField descriptionBox;
    @FXML
    TextField descriptionBox1;


    String descriptionAsText = "";//initialize as an empty string
    String categoryAsText = "";//initialize as an empty string
    String amountAsText = "";//initialize as an empty string

    DBConnect connect = new DBConnect();

    public void saveExpense(){ //save the curently inserted expense to db
        descriptionAsText = descriptionBox.getText();
        categoryAsText = descriptionBox1.getText();
        amountAsText = amountBox.getText();
        connect.insertData(dtf.format(date) , categoryAsText , descriptionAsText , amountAsText );
        int expense = Integer.parseInt(getExpense());
        int balance = Integer.parseInt(getBalance());
        Person.expenseAmount = expense += Integer.parseInt(amountAsText);//adding the expense amount and set it
        Person.balanceAmount = balance -= Integer.parseInt(amountAsText);//substract the balance with expense

        String amountExpense = Integer.toString(Person.expenseAmount);
        String amountBalance = Integer.toString(Person.balanceAmount);

        connect.updateExpense(amountExpense);//update the current expense
        connect.updateBalance(amountBalance);//update the current balance after substraction
        Controller.expenseStage.close();

        System.out.println(Person.expenseAmount);
        System.out.println(Person.balanceAmount);

    }
}
