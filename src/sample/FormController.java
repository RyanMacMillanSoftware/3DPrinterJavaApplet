package sample;


import com.jfoenix.controls.JFXTimePicker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private ArrayList<Name> names;
    private ArrayList<Project> projects;
    private final File PROJECTS_FILE = new File(System.getProperty("user.home")+"/.user/projects.ser");
    private final File NAMES_FILE = new File(System.getProperty("user.home")+"/.user/names.ser");


    @FXML
    public void initialize(){
        names = new ArrayList<Name>();
        projects = new ArrayList<Project>();
        //reset name
        fillNameOptions();
        //reset project
        fillProjectOptions();
        //reset from_time
        from_time.setValue(LocalTime.now());
        //reset to_time
        to_time.setValue(LocalTime.now());
        //establishHerokuConnection();
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
            String user_name = result.get();
            Name new_name = new Name(user_name);
            addNameToDatabase(new_name);
            fillNameOptions();
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
            Name chosen_name = null;
            for (Name n : names){
                if (n.getName().equals(name.getValue())){
                    chosen_name = n;
                }
            }
            if (chosen_name != null) {
                removeNameFromDatabase(chosen_name);
                fillNameOptions();
            }
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
            String project_name = result.get();
            Project new_project = new Project(project_name);
            addProjectToDatabase(new_project);
            fillProjectOptions();
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
            Project chosen_project = null;
            for (Project p : projects){
                if (p.getName().equals(project.getValue())){
                    chosen_project = p;
                }
            }
            if (chosen_project != null) {
                removeProjectFromDatabase(chosen_project);
                fillProjectOptions();
            }
            return;
        } else {
            return;
        }
    }

    private void clearFormElements(){
        //reset name
        fillNameOptions();
        //reset project
        fillProjectOptions();
        //reset from_time
        from_time.setValue(LocalTime.now());
        //reset to_time
        to_time.setValue(LocalTime.now());
        //reset volume
        //TODO
        //reset notes
        notes.clear();
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
    private ArrayList<Name> getNames(){
        ArrayList<Name> names_read = new ArrayList<Name>();
        //establish read connection
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(NAMES_FILE);


            ObjectInputStream ois = new ObjectInputStream(fis);
            names_read = (ArrayList<Name>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return names_read;
    }

    private ArrayList<Project> getProjects(){
        ArrayList<Project> projects_read = new ArrayList<Project>();
        //establish read connection
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(PROJECTS_FILE);


            ObjectInputStream ois = new ObjectInputStream(fis);
            projects_read = (ArrayList<Project>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return projects_read;
    }

    private void fillNameOptions(){
        names = getNames();
        name.getItems().clear();
        for (Name n : names){
            name.getItems().add(n.getName());
        }
    }

    private void fillProjectOptions(){
        projects = getProjects();
        project.getItems().clear();
        for (Project p : projects){
            project.getItems().add(p.getName());
        }
    }

    private void addNameToDatabase(Name n){

        //establish write connection
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(NAMES_FILE, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        //add name to list
        names.add(n);
        //sort alphabetically
        Collections.sort(names);
        //write to file
        oos.writeObject(names);
        oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeNameFromDatabase(Name n){
        //establish write connection
        FileOutputStream fos = null;
        try {

            //false overwrites
            fos = new FileOutputStream(NAMES_FILE, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //remove name from list
            names.remove(n);
            //sort alphabetically
            Collections.sort(names);
            //write to file
            oos.writeObject(names);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addProjectToDatabase(Project p){
        //establish write connection
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(PROJECTS_FILE, false);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //add name to list
            projects.add(p);
            //sort alphabetically
            Collections.sort(projects);
            //write to file
            oos.writeObject(projects);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeProjectFromDatabase(Project p){
        FileOutputStream fos = null;
        try {

            //false overwrites
            fos = new FileOutputStream(PROJECTS_FILE, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //remove project from list
            projects.remove(p);
            //sort alphabetically
            Collections.sort(projects);
            //write to file
            oos.writeObject(projects);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDataToTable(){
        //need name and to_time
        //add to table
        //need behaviour to eventually remove old entries
    }
}
