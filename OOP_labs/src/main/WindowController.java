package main;

import factory.Annotation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import templates.CreatableObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class WindowController {
    Stage createStage(String title, int width, Pane pane){
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setWidth(width);
        stage.setResizable(false);
        stage.setScene(new Scene(pane));
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    Button CreateButton(int layoutX, int layoutY, String text, EventHandler<ActionEvent> event){
        Button buttonAdd = new Button();
        buttonAdd.setText(text);
        buttonAdd.setMinWidth(100);
        buttonAdd.setLayoutX(layoutX);
        buttonAdd.setLayoutY(layoutY);
        buttonAdd.setOnAction(event);
        return buttonAdd;
    }

    ArrayList<TextField> CreateTextFields(Pane pane, CreatableObject creatableObject){
        ArrayList<TextField> textfields = new ArrayList<>();
        Class<?> current = creatableObject.getClass();
        int offsetX = 20;
        int offsetY = 20;
        try {
            while (current.getSuperclass() != null) {
                Field decFields[] = current.getDeclaredFields();
                for (Field f : decFields) {
                    if(f.getType().isPrimitive()||f.getType().equals(String.class)) {
                        f.setAccessible(true);
                        Label label = new Label(f.getName());
                        TextField textfield = new TextField();
                        textfield.setId(f.getName());
                        if(f.get(creatableObject)!=null) {
                            textfield.setText(f.get(creatableObject).toString());
                        }
                        textfields.add(textfield);
                        label.setLayoutX(offsetX);
                        label.setLayoutY(offsetY);
                        textfield.setLayoutX(offsetX + 150);
                        textfield.setLayoutY(offsetY);
                        pane.getChildren().add(label);
                        pane.getChildren().add(textfield);
                        offsetY += 40;
                    }
                }
                current = current.getSuperclass();
            }
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }catch(IllegalAccessException ex){
            ex.printStackTrace();
        }
        return textfields;
    }

    void DisplayCreatableObjects(ArrayList<? extends CreatableObject> creatableObjects, ListView<String> listview){
        ObservableList<String> mainListViewList = FXCollections.observableArrayList();
        for (CreatableObject creatableObject: creatableObjects){
            if (creatableObject.getClass().isAnnotationPresent(Annotation.class)) {
                mainListViewList.add(creatableObject.getClass().getAnnotation(Annotation.class).name()+" "+creatableObject.getName());
            }
        }
        listview.setItems(mainListViewList);
    }

    void CreateAlert(String title, String header, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
