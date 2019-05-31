package main;

import factory.Annotation;
import factory.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import templates.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class EditWindowController extends WindowController{
    private ArrayList<CreatableObject> composedObjectsList;
    private int selectedItemIndex = -1;

    public void CreateEditWindow(CreatableObject creatableObject, Factory factory, Data data, ObservableList<String> compositionClassesAnnotationsList, ObservableList<String> composedClassesAnnotationsList){
        Pane pane = new Pane();
        int width = 500;
        Stage stage = createStage("Редактировать объект",width,pane);
        composedObjectsList = new ArrayList<>();
        ArrayList<TextField> textfields = CreateTextFields(pane,creatableObject);
        HashMap<String, ArrayList<CreatableObject>> composedArrayLists = new HashMap<>();
        if (compositionClassesAnnotationsList.contains(creatableObject.getClass().getAnnotation(Annotation.class).name())) {
            ListView<String> compositionListView = CreateCompositionListView(creatableObject, width - 170, 20 + 40 * textfields.size(),composedArrayLists);
            compositionListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            compositionListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    selectedItemIndex = (compositionListView.getSelectionModel().getSelectedIndex());
                }
            });
            EventHandler<ActionEvent> addObjectButtonEventHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    AddCompositionWindowController addCompositionWindowController = new AddCompositionWindowController();
                    addCompositionWindowController.CreateAddCompositionWindow(data,composedArrayLists);
                    updateComposedObjectsList(composedArrayLists,composedClassesAnnotationsList);
                    DisplayCreatableObjects(composedObjectsList,compositionListView);
                }
            };
            EventHandler<ActionEvent> deleteObjectButtonEventHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(selectedItemIndex>=0){
                        CreatableObject selectedObject = composedObjectsList.get(selectedItemIndex);
                        for(String annotationName: composedClassesAnnotationsList){
                            ArrayList<CreatableObject> composedArrayList = new ArrayList<>();
                            if(composedArrayLists.containsKey(annotationName)) {
                                composedArrayList = composedArrayLists.get(annotationName);
                            }
                            if(composedArrayList.contains(selectedObject)){
                                composedArrayList.remove(selectedObject);
                            }
                        }
                        selectedItemIndex = -1;
                        updateComposedObjectsList(composedArrayLists,composedClassesAnnotationsList);
                        DisplayCreatableObjects(composedObjectsList,compositionListView);
                    } else{
                        CreateAlert("","Невозможно удалить объект","Объект не выбран");
                    }
                }
            };
            updateComposedObjectsList(composedArrayLists,composedClassesAnnotationsList);
            DisplayCreatableObjects(composedObjectsList,compositionListView);
            pane.getChildren().add(compositionListView);
            stage.setHeight(20+40*textfields.size()+200);
            Button buttonAddObject = CreateButton(width-140,(int)stage.getHeight()-240,"Добавить объект", addObjectButtonEventHandler);
            Button buttonDeleteObject = CreateButton(width-140,(int)stage.getHeight()-180,"Удалить объект", deleteObjectButtonEventHandler);
            pane.getChildren().add(buttonAddObject);
            pane.getChildren().add(buttonDeleteObject);
        }
        EventHandler<ActionEvent> saveButtonEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ArrayList<String> fieldValues = new ArrayList<>();
                    for (TextField textfield : textfields) {
                        fieldValues.add(textfield.getText());
                    }
                    if (creatableObject.getClass().isAnnotationPresent(Annotation.class)) {
                        factory.editingObject(creatableObject.getClass().getAnnotation(Annotation.class).name(), fieldValues, composedArrayLists, creatableObject);
                    }
                    stage.close();
                } catch(NumberFormatException ex){
                CreateAlert("","Некорректный ввод","Пожалуйста, введите данные заново");
            }
            }
        };
        stage.setHeight(20+40*textfields.size()+200);
        Button buttonSave = CreateButton(width-140,(int)stage.getHeight()-80,"Сохранить", saveButtonEventHandler);
        pane.getChildren().add(buttonSave);
        stage.showAndWait();
    }

    ListView CreateCompositionListView(CreatableObject creatableObject, int width, int layoutY, HashMap<String,ArrayList<CreatableObject>> composedArrayLists){
        ListView<String> compositionListView = new ListView<>();
        compositionListView.setLayoutY(layoutY);
        Class<?> current = creatableObject.getClass();
        ArrayList<CreatableObject> compositionList = new ArrayList<>();
        try {
            while (current.getSuperclass() != null) {
                Field decFields[] = current.getDeclaredFields();
                for (Field f : decFields) {
                    if(f.getType().equals(compositionList.getClass())) {
                        f.setAccessible(true);
                        compositionList = (ArrayList<CreatableObject>) f.get(creatableObject);
                        if(compositionList.size()>0) {
                            ObservableList<String> compositionObjects = FXCollections.observableArrayList();
                            for (CreatableObject compositionObject : compositionList) {
                                compositionObjects.add(compositionObject.getName());
                            }
                            compositionListView.setItems(compositionObjects);
                        }
                        if(f.isAnnotationPresent(Annotation.class)){
                            String annotationName = f.getAnnotation(Annotation.class).name();
                            composedArrayLists.put(annotationName,compositionList);
                        }
                    }
                }
                current = current.getSuperclass();
            }
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        compositionListView.setMinWidth(width);
        return compositionListView;
    }

    void updateComposedObjectsList(HashMap<String,ArrayList<CreatableObject>> composedArrayLists, ObservableList<String> composedClassesAnnotationsList){
        composedObjectsList = new ArrayList<>();
        for(String annotationName: composedClassesAnnotationsList){
            if(composedArrayLists.containsKey(annotationName)) {
                composedObjectsList.addAll(composedArrayLists.get(annotationName));
            }
        }
    }
}
