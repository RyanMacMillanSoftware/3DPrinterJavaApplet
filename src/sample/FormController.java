package sample;


import com.jfoenix.controls.JFXTimePicker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Optional;

/*
Class made by Ryan MacMillan on 13 July 2017
Form Controller is the main form controller for this application. It fills the dropdown headers, manages the local storage
of available projects and names, displays a list of people currently in the lab, and it submits form information to
the Heroku database.
 */

public class FormController{


    @FXML private ComboBox name;
    @FXML private ComboBox project;
    @FXML private JFXTimePicker from_time;
    @FXML private JFXTimePicker to_time;
    @FXML private Spinner volume;
    @FXML private TextArea notes;
    @FXML private TableView job_table;
    private ArrayList<String> names;
    private ArrayList<String> projects;

    @FXML
    public void initialise(){
        names = getNames();
        projects = getProjects();
        fillNameOptions(names);
        fillProjectOptions(projects);
        establishHerokuConnection();
    }

    @FXML
    public void submitForm(Event me) {
        establishHerokuConnection();
        //format data
        sendFormDataToHeroku();
        addDataToTable();
        clearFormElements(); //if no error

    }

    @FXML
    public void clearForm(Event me) {
        //are you sure dialogue (debatable feature)
        clearFormElements();
    }

    @FXML
    public void addName(Event e){
        //Get Project name from user
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Name");
        dialog.setHeaderText("Add a Name");
        dialog.setContentText("Please enter the name:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            //String new_name = result.get();
            //addProjectToDatabase();
            names = getNames();
            //fillProjectOptions();
        }
    }

    @FXML
    public void removeName(Event me){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to remove a name from the list");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //removeNameFromDatabase();
            names = getNames();
            //fillNameOptions();
            return;
        } else {
            return;
        }
    }

    @FXML
    public void addProject(Event me){
        //Get Project name from user
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Project");
        dialog.setHeaderText("Add a Project");
        dialog.setContentText("Please enter the project name:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            //String new_project = result.get();
            //addProjectToDatabase();
            projects = getProjects();
            //fillProjectOptions();
        }
    }

    @FXML
    public void removeProject(Event me){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to remove a project from the list");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //removeProjectFromDatabase();
            projects = getProjects();
            //fillProjectOptions();
            return;
        } else {
            return;
        }
    }

    private void clearFormElements(){
        //reset name
        //reset project
        //reset from_time
        //reset to_time
        //reset volume
        //reset notes
    }

    //establish Heroku connection to see if it's working
    private void establishHerokuConnection(){
        //try establish connection
        //probably login
        //if it works do nothing
        //if not, warn the user of the error (connectivity, contact admin,...)
    }

    //send form information as a relation to the Heroku database
    private void sendFormDataToHeroku() {
        //establish connection (handle errors)
        //log in
        //format data
        //send data
        //display success or failure notification
    }

    //retrieve all names from the local database
    private ArrayList<String> getNames(){
        //establish connection
        ArrayList<String> names_read = new ArrayList<String>();
        //read objects
        return names_read;
    }

    private ArrayList<String> getProjects(){
        //establish connection
        ArrayList<String> projects_read = new ArrayList<String>();
        //read objects
        return projects_read;
    }

    private void fillNameOptions(ArrayList<String> names){
        //fill "name" with names
    }

    private void fillProjectOptions(ArrayList<String> projects){
        //fill "project" with projects
    }

    private void addNameToDatabase(String name){
        //establish write connection
        //add alphabetically
    }

    private void removeNameFromDatabase(String name){
        //establish write connection
        //find name
        //remove name
    }

    private void addProjectToDatabase(String project){
        //establish write connection
        //add alphabetically
    }

    private void removeProjectFromDatabase(String project){
        //establish write connection
        //find name
        //remove name
    }

    private void addDataToTable(){
        //need name and to_time
        //add to table
        //need behaviour to eventually remove old entries
    }
}
