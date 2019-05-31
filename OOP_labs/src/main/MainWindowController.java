package main;

import factory.Annotation;
import factory.Factory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import templates.*;

import javax.security.auth.login.AccountNotFoundException;

public class MainWindowController extends WindowController{

    @FXML
    private ComboBox<String> addComboBox;
    @FXML
    private ListView<String> mainListView;

    private Data data = new Data();
    private Factory factory = new Factory();
    private ObservableList<String> creatableObjects = FXCollections.observableArrayList("Автопарк", "Водитель", "Механик", "Легковой", "Автобус", "Грузовик");
    private ObservableList<String> compositionClassesAnnotationsList = FXCollections.observableArrayList("Автопарк", "Водитель", "Механик");
    private ObservableList<String> composedClassesAnnotationsList = FXCollections.observableArrayList("Автомобиль", "Сотрудник");
    private int selectedItemIndex = 0;

    @FXML
    public void initialize(){
        addComboBox.setItems(creatableObjects);
        addComboBox.setValue(creatableObjects.get(0));
        mainListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        mainListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                selectedItemIndex = (mainListView.getSelectionModel().getSelectedIndex());
            }
        });
    }

    public void addBtnOnClick(){
        String comboBoxValue = addComboBox.getValue();
        try {
            CreatableObject creatableObject = factory.firstObjectCreation(comboBoxValue);
            AddWindowController addWindowController = new AddWindowController();
            addWindowController.createAddWindow(creatableObject,data,factory,addComboBox.getValue());
            DisplayCreatableObjects(data.getCreatableObjects(),mainListView);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void removeBtnOnClick(){
        if(selectedItemIndex>=0){
            for(CreatableObject removeFrom: data.getCreatableObjects()){
                if(compositionClassesAnnotationsList.contains(removeFrom.getClass().getAnnotation(Annotation.class).name())){
                    factory.removeComposedObject(removeFrom,data.getCreatableObjects().get(selectedItemIndex));
                }
            }
            data.getCreatableObjects().remove(selectedItemIndex);
            DisplayCreatableObjects(data.getCreatableObjects(),mainListView);
        } else{
            CreateAlert("","Невозможно удалить объект","Объект не выбран");
        }
    }

    public void editBtnOnClick(){
        EditWindowController editWindowController = new EditWindowController();
        CreatableObject objectToEdit = data.getCreatableObjects().get(selectedItemIndex);
        editWindowController.CreateEditWindow(objectToEdit,factory,data,compositionClassesAnnotationsList,composedClassesAnnotationsList);
        DisplayCreatableObjects(data.getCreatableObjects(),mainListView);
    }
}

