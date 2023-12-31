package shop.develop.kaprichosshop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import shop.develop.kaprichosshop.model.Client;
import shop.develop.kaprichosshop.model.Product;

import java.time.LocalDate;

public class ShopController extends ShopBackend{

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
        if (clientsRegisterPage.isVisible()){
            clientsRegisterPage.setVisible(false);
            backButtonData.setDisable(false);
            backButtonRegister.setDisable(false);
        }else {
            modalPaneOfSale.setVisible(false);
        }
    }

    public void closeAdd(ActionEvent event){
        paneAdd.setVisible(false);
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
        resetFormSalesRegister();
        salesDataPage.setVisible(false);
        salesRegisterPage.setVisible(true);
    }
    public void clickNewClientSales(ActionEvent event){
        salesContainer.setVisible(false);
        salesDataPage.setVisible(true);
        salesRegisterPage.setVisible(false);
        clientsContainer.setVisible(true);
        clientsDataPage.setVisible(false);
        clientsRegisterPage.setVisible(true);
    }
    public void clickRegisterProduct(ActionEvent event){
        resetFormProductRegister();
        productsDataPage.setVisible(false);
        productsRegisterPage.setVisible(true);

    }
    public void clickRegisterClient(ActionEvent event){
        resetFormClientRegister();
        clientsDataPage.setVisible(false);
        clientsRegisterPage.setVisible(true);
    }

    //Clients Button CRUD
    public void clickAddClient(ActionEvent event){
        boolean isNaturalClient = "Natural".equals(typeClientSelect.getValue());

        if (idInput.getText().isEmpty()) {
            alertFromClient("El " + (isNaturalClient ? "documento" : "nit") + " del cliente es requerido.");
        } else if (nameInput.getText().isEmpty()) {
            alertFromClient("El campo de Nombre es requerido.");
        } else if (lastNameInput.getText().isEmpty()) {
            alertFromClient("El campo de Apellido es requerido.");
        } else if (addressInput.getText().isEmpty()) {
            alertFromClient("La direccion es requerida.");
        } else if (phoneInput.getText().isEmpty()) {
            alertFromClient("El numero de contacto es requerido.");
        } else if (isNaturalClient && (emailInput.getText().isEmpty() && formEmail.isVisible())) {
            alertFromClient("El email es requerido.");
        } else if (isNaturalClient && (birthInput.getValue() == null && formBirth.isVisible())) {
            alertFromClient("Debe seleccionar la fecha de nacimiento.");
        } else if (isNaturalClient && (!isValidEmail(emailInput.getText()) && formEmail.isVisible())) {
            alertFromClient("Debe ingresar una direccion de correo valida.");
        } else if (existIntoArray(idInput.getText(), listClient, Client::getId)) {
            alertNomatch("El id: " + idInput.getText() + " Ya existe en el sistema");
        } else {
            alertFromClient("Se ha registrado exitosamente!");
            addClientList();
            resetFormClientRegister();
        }
    }

    public void clickSearchClient(ActionEvent event){
        boolean idExists = false;

        for (Client client : listClient) {
            if (client.getId().equals(searchTextField.getText())) {
                idExists = true;
                break;
            }
        }

        if (!idExists) {
            alertNomatch("El cliente con el Id/Nit " + searchTextField.getText() + " no existe en el sistema.");
        }
        searchTextField.setText("");
        tableClients.setItems(listClient);
    }
    public void clickUpdateClient(ActionEvent event){
        boolean isNaturalClient = "Natural".equals(typeClientSelect.getValue());

        if (addressInput.getText().isEmpty()) {
            alertFromClient("La direccion es requerida.");
        } else if (phoneInput.getText().isEmpty()) {
            alertFromClient("El numero de contacto es requerido.");
        } else if (isNaturalClient && (emailInput.getText().isEmpty() && formEmail.isVisible())) {
            alertFromClient("El email es requerido.");
        } else if (isNaturalClient && (birthInput.getValue() == null && formBirth.isVisible())) {
            alertFromClient("Debe seleccionar la fecha de nacimiento.");
        } else if (isNaturalClient && (!isValidEmail(emailInput.getText()) && formEmail.isVisible())) {
            alertFromClient("Debe ingresar una direccion de correo valida.");
        } else {
            updateClient();
            alertUpdateDelete("Se a actualizado exitosamente");
        }
    }

    public void clickDeleteClient(ActionEvent event){
        deleteClient(idInput.getText());
    }

    //Controls Sales
    public void clickSearchClientSale(ActionEvent event){
        int lastElement = 1;
        Client saleClient = filterClient(listClient, searchClientSale.getText());
        System.out.println("saleClient = " + saleClient);
        LocalDate createAt = LocalDate.now();

        if (!listSale.isEmpty()) {
            lastElement = listSale.size() + 1;
        }

        if(saleClient != null) {
            labelSaleId.setText(saleClient.getId());
            labelSaleName.setText(saleClient.getName() + " " + saleClient.getLastName());
            labelSalePhone.setText(saleClient.getPhone());
            labelSaleAddress.setText(saleClient.getAddress());
            labelSaleDate.setText(createAt.toString());
            labelSerie.setText(String.format("%4d", lastElement));
        }else {
            alertNomatch("El cliente " + searchClientSale.getText() + " no se encuentra registrado en el sistema.");
            searchClientSale.setText("");
        }
    }

    public void clickAddProductSale(ActionEvent event){
        paneAdd.setVisible(false);
        modalPaneOfSale.setVisible(false);
        for (Product product : listProduct) {
            if (product.getIdProduct().equals(idAdd.getText())) {
                addProductSale(product, Integer.parseInt(amountAdd.getText()));
            }
        }
    }

    public void clickAddProduct(ActionEvent event){
        addProductList();
        resetFormProductRegister();
    }

    public void clickUpdateProduct(ActionEvent event){
            updateProduct();
            alertUpdateDelete("Se ha actualizado exitosamente");
    }

    public void clickDeleteProduct(ActionEvent event){
        deleteProduct(idProduct.getText());
    }

    public void clickSearchProduct(ActionEvent event){
        boolean idExists = false;

        for (Product product : listProduct) {
            if (product.getIdProduct().equals(searchFieldProducts.getText())) {
                idExists = true;
                break;
            }
        }

        if (!idExists) {
            alertNomatch("El producto con el Id " + searchFieldProducts.getText() + " no existe en el sistema.");
        }
        tableViewProducts.setItems(listProduct);
        searchFieldProducts.setText("");
    }

}