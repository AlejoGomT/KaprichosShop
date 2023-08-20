package shop.develop.kaprichosshop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ShopController extends ShopBackend{
    //Controls elements from fxml
    @FXML private AnchorPane pageHome, salesContainer, salesDataPage, salesRegisterPage,
            productsContainer, productsDataPage, productsRegisterPage, clientsContainer,
            clientsDataPage, clientsRegisterPage;
    @FXML private Pane modalPaneOfSale;

    //Function for close the application
    public void clickCloseButton(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }

    //Button Back
    public void clickBackButton(ActionEvent event){
        if(salesContainer.isVisible()){
            if(salesDataPage.isVisible()){
                salesContainer.setVisible(false);
                pageHome.setVisible(true);
            }
            if(salesRegisterPage.isVisible()){
                salesRegisterPage.setVisible(false);
                salesDataPage.setVisible(true);
            }
        }
        if(productsContainer.isVisible()){
            if(productsDataPage.isVisible()){
                productsContainer.setVisible(false);
                pageHome.setVisible(true);
            }
            if(productsRegisterPage.isVisible()){
                productsRegisterPage.setVisible(false);
                productsDataPage.setVisible(true);
            }
        }
        if(clientsContainer.isVisible()){
            if(clientsDataPage.isVisible()){
                clientsContainer.setVisible(false);
                pageHome.setVisible(true);
            }
            if(clientsRegisterPage.isVisible()){
                clientsRegisterPage.setVisible(false);
                clientsDataPage.setVisible(true);
            }
        }
    }

    //Actions buttons from modal pane of sale
    public void addProductModal(ActionEvent event){
        modalPaneOfSale.setVisible(true);
    }
    public void closeProductModal(ActionEvent event){
        modalPaneOfSale.setVisible(false);
    }


    //Routes Buttons: Sales, Clients and Products
    public void clickSalesButton(ActionEvent event){
        pageHome.setVisible(false);
        salesContainer.setVisible(true);
    }
    public void clickProductsButton(ActionEvent event){
        pageHome.setVisible(false);
        productsContainer.setVisible(true);
    }
    public void clickClientsButton(ActionEvent event){
        pageHome.setVisible(false);
        clientsContainer.setVisible(true);
    }

    //Register Buttons: Sales, Clients and Products
    public void clickRegisterSale(ActionEvent event){
        salesDataPage.setVisible(false);
        salesRegisterPage.setVisible(true);
    }
    public void clickRegisterProduct(ActionEvent event){
        productsDataPage.setVisible(false);
        productsRegisterPage.setVisible(true);
    }
    public void clickRegisterClient(ActionEvent event){
        clientsDataPage.setVisible(false);
        clientsRegisterPage.setVisible(true);
    }

    //Add button client
    public void clickAddClient(ActionEvent event){
        if (idInput.getText().isEmpty()) {
            alertFromClient("El " + ("Natural".equals(typeClientSelect.getValue()) ? "documento" : "nit") + "del cliente es requerido.");
        } else if (nameInput.getText().isEmpty()) {
            alertFromClient("El campo de Nombre es requerido.");
        } else if (lastNameInput.getText().isEmpty()) {
            alertFromClient("El campo de Apellido es requerido.");
        } else if (addressInput.getText().isEmpty()) {
            alertFromClient("La direccion es requerida.");
        } else if (phoneInput.getText().isEmpty()) {
            alertFromClient("El numero de contacto es requerido.");
        } else if (emailInput.getText().isEmpty() && formEmail.isVisible()) {
            alertFromClient("El email es requerido.");
        } else if (birthInput.getValue() == null && formBirth.isVisible()) {
            alertFromClient("Debe seleccionar la fecha de nacimiento.");
        } else if (!isValidEmail(emailInput.getText()) && formEmail.isVisible()) {
            alertFromClient("Debe ingresar una direccion de correo valida.");
        } else {
            alertFromClient("Se a registrado exitosamente!");
            addClientList();
            idInput.setText("");
            nameInput.setText("");
            lastNameInput.setText("");
            addressInput.setText("");
            phoneInput.setText("");
            emailInput.setText("");
            birthInput.setValue(null);
        }
    }
}