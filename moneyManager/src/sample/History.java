package sample;

import javafx.beans.property.SimpleStringProperty;

public class History {    private SimpleStringProperty category , description , date , amount; //class for history table



    public History(String category , String description , String amount , String date){ //create the function show any transaction
        this.category = new SimpleStringProperty(category); // make the object of category using simplestringproperty function
        this.description = new SimpleStringProperty(description);// make the object of description using simplestringproperty function
        this.amount = new SimpleStringProperty(amount);// make the object of amount using simplestringproperty function
        this.date = new SimpleStringProperty(date);// make the object of date using simplestringproperty function
    }

    public String getCategory(){
        return category.get();
    } // get the category and with the data type as string

    public void setCategory(SimpleStringProperty category){
        this.category = category;
    } // set the category

    public void setDescription(SimpleStringProperty description){ // set the description
        this.description = description;
    }

    public void setAmount(SimpleStringProperty amount){
        this.amount = amount;
    }//set amount

    public String getDescription(){
        return description.get();
    }//get description
    public String getAmount(){
        return amount.get();
    }//get amount

    public String getDate(){
        return date.get();
    }//get date



}
