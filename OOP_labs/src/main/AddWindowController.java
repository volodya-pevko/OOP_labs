package main;

import factory.Factory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import templates.CreatableObject;
import java.util.ArrayList;

public class AddWindowController extends WindowController{

    @FXML
    public void createAddWindow(CreatableObject creatableObject, Data data, Factory factory, String annotationName){
        Pane pane = new Pane();
        Stage stage = createStage("Добавить объект",400,pane);
        ArrayList<TextField> textfields = CreateTextFields(pane,creatableObject);
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ArrayList<String> fieldValues = new ArrayList<>();
                    for (TextField textfield : textfields) {
                        fieldValues.add(textfield.getText());
                    }
                    String regex = "^[а-яёА-ЯЁ0-9]+$";
                    Boolean isEmptyField = false;
                    for(String fieldvalue: fieldValues){
                        if (!fieldvalue.matches(regex)){
                            isEmptyField = true;
                        }
                    }
                    if(!isEmptyField) {
                        data.addObject(factory.finalObjectCreation(annotationName, fieldValues));
                        stage.close();
                    }else{
                        CreateAlert("","Некорректный ввод","Пожалуйста, введите данные заново");
                    }
                }catch(NumberFormatException ex){
                    CreateAlert("","Некорректный ввод","Пожалуйста, введите данные заново");
                }
            }
        };
        stage.setHeight(20+40*textfields.size()+100);
        Button buttonAdd = CreateButton(100, 20+40*textfields.size(),"Сохранить", eventHandler);
        pane.getChildren().add(buttonAdd);
        stage.showAndWait();
    }
}
