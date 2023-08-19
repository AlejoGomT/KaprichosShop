package shop.develop.kaprichosshop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ShopController {
    //Controls elements from fxml
    @FXML
    public AnchorPane pageHome, salesContainer, salesDataPage, salesRegisterPage,
            productsContainer, productsDataPage, productsRegisterPage, clientsContainer,
            clientsDataPage, clientsRegisterPage;
    public Pane modalPaneOfSale;

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
}