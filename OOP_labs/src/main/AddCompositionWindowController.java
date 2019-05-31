package main;

import factory.Annotation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import templates.*;

import java.util.ArrayList;
import java.util.HashMap;

public class AddCompositionWindowController extends WindowController {
    private int selectedItemIndex = -1;
    public void CreateAddCompositionWindow(Data data, HashMap<String,ArrayList<CreatableObject>> composedArrayLists){
        Pane pane = new Pane();
        int width = 500;
        Stage stage = createStage("Добавление объектов композиции",width,pane);
        ArrayList<CreatableObject> compositionObjects = new ArrayList<>();
        for(CreatableObject creatableObject: data.getCreatableObjects()){
            if(creatableObject.getClass().getSuperclass() != null) {
                if(creatableObject.getClass().getSuperclass().isAnnotationPresent(Annotation.class)) {
                    if (composedArrayLists.containsKey(creatableObject.getClass().getSuperclass().getAnnotation(Annotation.class).name())) {
                        compositionObjects.add(creatableObject);
                    }
                }
            }
        }
        ListView<String> compositionObjectsListView = new ListView<>();
        compositionObjectsListView.setMinHeight(300);
        compositionObjectsListView.setMinWidth(width-150);
        compositionObjectsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        compositionObjectsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                selectedItemIndex = (compositionObjectsListView.getSelectionModel().getSelectedIndex());
            }
        });
        EventHandler<ActionEvent> addObjectButtonEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(selectedItemIndex>=0){
                    CreatableObject someObject = compositionObjects.get(selectedItemIndex);
                    String annotationName = someObject.getClass().getSuperclass().getAnnotation(Annotation.class).name();
                    if(composedArrayLists.containsKey(annotationName)){
                        ArrayList<CreatableObject> composedArrayList = composedArrayLists.get(annotationName);
                        if(!composedArrayList.contains(someObject)){
                            composedArrayList.add(someObject);
                        } else{
                            CreateAlert("","Невозможно добавить объект","Этот объект уже добавлен");
                        }
                    }
                } else{
                    CreateAlert("","Невозможно добавить объект","Объект не выбран");
                }
            }
        };
        DisplayCreatableObjects(compositionObjects,compositionObjectsListView);
        pane.getChildren().add(compositionObjectsListView);
        stage.setHeight(300);
        Button buttonAddObject = CreateButton(width-140,(int)stage.getHeight()-200,"Добавить объект", addObjectButtonEventHandler);
        pane.getChildren().add(buttonAddObject);
        EventHandler<ActionEvent> editButtonEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        };
        Button buttonSave = CreateButton(width-140,(int)stage.getHeight()-80,"Закрыть", editButtonEventHandler);
        pane.getChildren().add(buttonSave);
        stage.showAndWait();
    }
}
