package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("form.fxml"));
        primaryStage.setTitle("3D Printer Record");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ensureNameAndProjectFiles();
        launch(args);
    }

    public static void ensureNameAndProjectFiles(){
        File file = new File(System.getProperty("user.home")+"/.user/names.ser");
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        file = new File(System.getProperty("user.home")+"/.user/projects.ser");
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
    }
}

