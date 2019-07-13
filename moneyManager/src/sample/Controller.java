package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.jfr.Category;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller extends DBConnect implements Initializable {

    public static Stage expenseStage;
    public static Stage incomeStage;



    @FXML
    private TableView<History> historyTable;
    @FXML
    private TableColumn<History, LocalDate> colDate;
    @FXML
    private TableColumn<History, String> colCategory;
    @FXML
    private TableColumn<History, String> colDesc;
    @FXML
    private TableColumn<History, String> colAmount;

    @FXML
    Text totalIncome;
    @FXML
    Text totalExpense;
    @FXML
    Text totalBalance;



    ObservableList<History> history = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAllBalance();//load balance from database
        loadData();//load the data

    }
    public void loadAllBalance(){

        totalIncome.setText(getIncome());
        totalExpense.setText(getExpense());
        totalBalance.setText(getBalance());
    }
    public void loadData(){
        loadAllBalance();
        historyTable.getItems().clear();

        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from history";
            ResultSet rs =  con.createStatement().executeQuery(sql);
            while (rs.next()) {
                history.add(new History(rs.getString("category"), rs.getString("description"), rs.getString("amount") , rs.getString("date")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        colCategory.setCellValueFactory(new PropertyValueFactory<History,String >("category"));
        colDesc.setCellValueFactory(new PropertyValueFactory<History, String>("description"));
        colAmount.setCellValueFactory(new PropertyValueFactory<History,String>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<History,LocalDate>("date"));

        historyTable.setItems(history);
    }

    public void openIncoome() throws IOException {
        incomeStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("income.fxml"));
        incomeStage.setTitle("INCOME");
        incomeStage.setScene(new Scene(root, 300, 185));
        incomeStage.setResizable(false);
        incomeStage.show();
    }

    public void openExpense() throws IOException {

        expenseStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("expense.fxml"));
        expenseStage.setTitle("EXPENSE");
        expenseStage.setScene(new Scene(root, 300, 338));
        expenseStage.setResizable(false);
        expenseStage.show();
    }


}
