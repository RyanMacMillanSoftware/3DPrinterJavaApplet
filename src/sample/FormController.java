package sample;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/*
Class made by Ryan MacMillan on 13 July 2017
Form Controller is the GUI controller for this application. It fills the dropdown headers, manages the local storage
of available projects and names, displays a list of people currently in the lab, and it submits form information to
the Heroku database.
 */

public class FormController{


    @FXML private ComboBox name;
    @FXML private ComboBox project
    @FXML private Spinner from_time;
    @FXML private Spinner to_time;
    @FXML private Spinner volume;
    @FXML private TextArea notes;
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
    public void submitForm(MouseEvent me) {
        establishHerokuConnection();
        //format data
        sendFormDataToHeroku();
        clearFormElements(); //if no error
    }

    @FXML
    public void clearForm(MouseEvent me) {
        //are you sure dialogue (debatable feature)
        clearFormElements();
    }

    @FXML
    public void addName(MouseEvent me){
        addNameToDatabase();
        names = getNames();
        fillNameOptions();
    }

    @FXML
    public void removeName(MouseEvent me){
        //are you sure dialogue
        removeNameFromDatabase();
        names = getNames();
        fillNameOptions();
    }

    @FXML
    public void addProject(MouseEvent me){
        addProjectToDatabase();
        projects = getProjects();
        fillProjectOptions();
    }

    @FXML
    public void removeProject(MouseEvent me){
        //are you sure dialogue
        removeProjectFromDatabase();
        projects = getProjects();
        fillProjectOptions();
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
        //read objects
        //return list
    }

    private ArrayList<String> getProjects(){
        //establish connection
        //read objects
        //return list
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
        //find name
        //remove name
    }

    private void removeProjectFromDatabase(String project){
        //establish write connection
        //find name
        //remove name
    }
}
