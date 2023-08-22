package shop.develop.kaprichosshop;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import shop.develop.kaprichosshop.model.Client;
import shop.develop.kaprichosshop.model.Legal;
import shop.develop.kaprichosshop.model.Natural;
import javafx.beans.property.SimpleStringProperty;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopBackend {
    @FXML public AnchorPane clientsDataPage, clientsRegisterPage, productsDataPage, productsRegisterPage,
            salesDataPage, salesRegisterPage;
    @FXML public HBox formEmail, formBirth;
    @FXML public ChoiceBox<String> typeClientSelect;
    @FXML private Label idLabel;
    @FXML public TextField idInput, nameInput, lastNameInput, addressInput, phoneInput, emailInput, searchTextField;
    @FXML public DatePicker birthInput;
    @FXML public TableView<Client> tableClients;
    @FXML private TableColumn<Client, String> columnId, columnName, columnLastName, columnPhone, columnAddress;
    @FXML public Button closeModal, backButtonData, backButtonRegister, updateButtonClient, deleteButonClient, addClient;
    //Products, clients and sales List
    public ObservableList<Client> listClient = FXCollections.observableArrayList();

    //Set values at the component BoxChoise with fx:id = 'typeClientSelect'
    public void initialize() {
        getClientList();
        tableClients.setItems(listClient);

        typeClientSelect.getItems().addAll("Natural", "Juridica");
        typeClientSelect.setValue("Natural");
        typeClientSelect.setOnAction( event -> dynamicForm());

        searchTextField.setOnKeyReleased(event -> {
            String searchQuery = searchTextField.getText();
            filterAndShowMatches(searchQuery);
        });

        tableClients.setRowFactory(tv -> selectItemRowClient());
    }

    public void resetFormClientRegister(){
        closeModal.setVisible(false);
        updateButtonClient.setVisible(false);
        deleteButonClient.setVisible(false);
        addClient.setVisible(true);

        typeClientSelect.setDisable(false);
        idInput.setDisable(false);
        nameInput.setDisable(false);
        lastNameInput.setDisable(false);

        typeClientSelect.setValue("Natural");
        idInput.setText("");
        nameInput.setText("");
        lastNameInput.setText("");
        addressInput.setText("");
        phoneInput.setText("");
        emailInput.setText("");
        birthInput.setValue(null);
    }
    public void resetFormClientUpdate(Client client){
        closeModal.setVisible(true);
        updateButtonClient.setVisible(true);
        deleteButonClient.setVisible(true);
        addClient.setVisible(false);

        backButtonRegister.setDisable(true);
        backButtonData.setDisable(true);
        typeClientSelect.setDisable(true);
        idInput.setDisable(true);
        nameInput.setDisable(true);
        lastNameInput.setDisable(true);

        if(client instanceof Natural){
            typeClientSelect.setValue("Natural");
            emailInput.setText(((Natural) client).getEmail());
            birthInput.setValue(((Natural) client).getBirth());
        }else {
            typeClientSelect.setValue("Legal");
        }

        idInput.setText(client.getId());
        nameInput.setText(client.getName());
        lastNameInput.setText(client.getLastName());
        addressInput.setText(client.getAddress());
        phoneInput.setText(client.getPhone());
    }

    //Form alerts and validation
    public void dynamicForm(){
        if ("Natural".equals(typeClientSelect.getValue())) {
            idLabel.setText("Cedula:");
            idInput.setPromptText("Numero de documento del cliente");
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Formulario de validacion");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void alertNomatch(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alerta: Sin coincidencia");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void alertUpdateDelete(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta: Sin coincidencia");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Create, Read, Search, Delete and Update Client Data
    public void addClientList() {
        if ("Natural".equals(typeClientSelect.getValue())) {
            Natural newClient = new Natural(idInput.getText(), nameInput.getText(), lastNameInput.getText(),
                    phoneInput.getText(), addressInput.getText(), emailInput.getText(), birthInput.getValue());
            listClient.add(newClient);
        } else {
            Legal newClient = new Legal(idInput.getText(), nameInput.getText(), lastNameInput.getText(),
                    phoneInput.getText(), addressInput.getText());
            listClient.add(newClient);
        }
    }

    public void getClientList(){
        columnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        columnLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        columnPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        columnAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
    }

    private void filterAndShowMatches(String searchQuery) {
        if (searchQuery.isEmpty()){
            tableClients.setItems(listClient);
        } else {
            ObservableList<Client> filteredClients = FXCollections.observableArrayList();

            for (Client client : listClient) {
                if (client.getId().startsWith(searchQuery)) {
                    filteredClients.add(client);
                }
            }
            tableClients.setItems(filteredClients);
        }
    }

    public TableRow<Client> selectItemRowClient(){
        TableRow<Client> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Client clickedClient = row.getItem();

                for (Client client:listClient) {
                    if (clickedClient.getId().equals(client.getId())){
                        clientsRegisterPage.setVisible(true);
                        resetFormClientUpdate(client);
                        break;
                    }
                }
            }

        });
        return row;
    }

    public void updateClient(){
        listClient.forEach(client -> {
            if (client instanceof Natural && idInput.getText().equals(client.getId())) {
                Natural natural = (Natural) client;
                Natural update = new Natural(idInput.getText(),nameInput.getText(), lastNameInput.getText(),
                        phoneInput.getText(), addressInput.getText(), emailInput.getText(), birthInput.getValue());
                listClient.set(listClient.indexOf(natural), update);

            } else if (client instanceof Legal && idInput.getText().equals(client.getId())) {
                Legal legal = (Legal) client;
                Legal update = new Legal(idInput.getText(), nameInput.getText(), lastNameInput.getText(),
                        phoneInput.getText(), addressInput.getText());
                listClient.set(listClient.indexOf(legal), update);
            }
        });
    }

    public void deleteClient(String id){
        listClient.removeIf(client -> id.equals(client.getId()));
        alertUpdateDelete("Se a eliminado con exitosamente");
    }

    public static <Type> Boolean existIntoArray(String idItem, ObservableList<Type> list, Function<Type, String> idList){
        return list.stream().anyMatch(idExist -> idList.apply(idExist).equals(idItem));
    }
}
