package shop.develop.kaprichosshop;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import shop.develop.kaprichosshop.model.Client;
import shop.develop.kaprichosshop.model.Legal;
import shop.develop.kaprichosshop.model.Natural;
import javafx.beans.property.SimpleStringProperty;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopBackend {
    @FXML public HBox formEmail, formBirth;
    @FXML public ChoiceBox<String> typeClientSelect;
    @FXML private Label idLabel;
    @FXML public TextField idInput, nameInput, lastNameInput, addressInput, phoneInput, emailInput;
    @FXML public DatePicker birthInput;
    @FXML private TableView<Client> tableClients;
    @FXML private TableColumn<Client, String> columnId, columnName, columnLastName, columnPhone, columnAddress;

    //Products, clients and sales List
    ArrayList<Client> listClient = new ArrayList<>();

    //Set values at the component BoxChoise with fx:id = 'typeClientSelect'
    public void initialize() {
        typeClientSelect.getItems().addAll("Natural", "Juridica");
        typeClientSelect.setValue("Natural");
        typeClientSelect.setOnAction( event -> dynamicForm());
    }

    //Form alerts and validation
    public void dynamicForm(){
        if ("Natural".equals(typeClientSelect.getValue())) {
            idLabel.setText("Cedula:");
            idInput.setPromptText("Numero de documento del client");
            formEmail.setVisible(true);
            formBirth.setVisible(true);
        } else {
            idLabel.setText("NIT:");
            idInput.setPromptText("Nit del client");
            formEmail.setVisible(false);
            formBirth.setVisible(false);
        }
    }
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void alertFromClient(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Form Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addClientList() {
        if ("Natural".equals(typeClientSelect.getValue())) {
            Date birth = Date.from(birthInput.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Natural newClient = new Natural(idInput.getText(),nameInput.getText(), lastNameInput.getText(),
                    phoneInput.getText(), addressInput.getText(), emailInput.getText(), birth);
            listClient.add(newClient);
        } else {
            Legal newClient = new Legal(idInput.getText(), nameInput.getText(), lastNameInput.getText(),
                    phoneInput.getText(), addressInput.getText());
            listClient.add(newClient);
        }
        getClientList();
    }

    public void getClientList(){
        columnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        columnLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        columnPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        columnAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));

        tableClients.getItems().setAll(listClient);
    }

}
