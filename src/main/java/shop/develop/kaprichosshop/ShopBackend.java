package shop.develop.kaprichosshop;
import javafx.scene.layout.Pane;
import shop.develop.kaprichosshop.model.*;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.beans.property.SimpleStringProperty;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopBackend {
    @FXML public Pane paneAdd, modalPaneOfSale;
    @FXML public AnchorPane pageHome, salesContainer, productsContainer, clientsContainer, clientsDataPage, clientsRegisterPage, productsDataPage, productsRegisterPage,
            salesDataPage, salesRegisterPage;
    @FXML public HBox formEmail, formBirth, boxCodigo, boxTemperatura, boxDate, boxCountry;
    @FXML public ChoiceBox<String> typeClientSelect;
    @FXML public ChoiceBox<Country> boxCountrySelect;
    @FXML public RadioButton selectPerece, selectRefri, selectEnla;
    @FXML private Label idLabel,codLabel,tempLabel, dateLabelProduct;
    @FXML public TextField amountAdd, searchProductsSale, searchClientSale, valueProductField, tempField, stockField, idInput, nameInput, lastNameInput, addressInput, phoneInput, emailInput, searchTextField, idProduct, codigoTextField, productField, searchFieldProducts;
    @FXML public DatePicker birthInput, datePick;
    @FXML public TextArea detailField;
    @FXML public TableView<Client> tableClients;
    @FXML private TableColumn<Client, String> columnId, columnName, columnLastName, columnPhone, columnAddress;
    @FXML public TableView<Product> tableViewProducts;
    @FXML private TableColumn<Product, String> codigoCol, productCol, typeCol;
    @FXML private TableColumn<Product, Integer> stockCol;
    @FXML private TableColumn<Product, Double> valorCol;
    @FXML public TableView<DetailSale> tableSales;
    @FXML public TableColumn<DetailSale, Integer> columnAmount;
    @FXML public TableColumn<DetailSale, String> columnDescription;
    @FXML public TableColumn<DetailSale, Double> columnPrice, columnAccumulated;
    @FXML public TableView<Product> tableAddProductsSale;
    @FXML public TableColumn<Product, String> idProductsSale, nameProductsSale;
    @FXML public TableColumn<Product, Double> priceProductsSale;
    @FXML private TableColumn<Product, Integer> stockProductsSale;
    @FXML public Button btnProductSearch, closeModalSale, closeModal, backButtonData, backButtonRegister, updateButtonClient, deleteButonClient, addClient,
            updateButtonSales, updateButtonProduct, deleteButonProduct, addProduct, closeModalProducts;
    @FXML public Label labelSaleName, labelSaleId, labelSaleAddress, labelSalePhone, labelSaleDate, labelSerie, labelSubTotal,
            labelIva, labelTotal, titleAdd, idAdd, stockAdd;
    //Products, clients and sales List

    public ObservableList<Sale> listSale = FXCollections.observableArrayList();
    public ObservableList<DetailSale> listDetail = FXCollections.observableArrayList();
    public ObservableList<Client> listClient = FXCollections.observableArrayList();
    public ObservableList<Product> listProduct = FXCollections.observableArrayList();
    public ObservableList<Country> listCountries = FXCollections.observableArrayList();

    //Set values at the component BoxChoice with fx:id = 'typeClientSelect'
    public void initialize() {
        resetVisibleApp();//Seteo por defecto de la App

        getClientList();
        tableClients.setItems(listClient);

        getProductList();
        tableViewProducts.setItems(listProduct);

        getConfigurationProductsSale();
        tableAddProductsSale.setItems(listProduct);

        getConfigurationDetailSale();
        tableSales.setItems(listDetail);

        typeClientSelect.getItems().addAll("Natural", "Juridica");
        typeClientSelect.setValue("Natural");
        typeClientSelect.setOnAction( event -> dynamicForm());

        boxCountrySelect.getItems().addAll(Country.values());
        boxCountrySelect.setValue(Country.ARGENTINA);

        searchTextField.setOnKeyReleased(event -> {
            String searchQuery = searchTextField.getText();
            filterAndShowMatches(searchQuery);
        });

        searchFieldProducts.setOnKeyReleased(event -> {
            String searchQuery = searchFieldProducts.getText();
            filterAndShowMatchesProduct(searchQuery);
        });

        tableClients.setRowFactory(tv -> selectItemRowClient());
        tableViewProducts.setRowFactory(tv -> selectItemRowProduct());
        tableAddProductsSale.setRowFactory(tv -> selectItemRowProductSale());

        ToggleGroup toggleGroup = new ToggleGroup();
        selectPerece.setToggleGroup(toggleGroup);
        selectRefri.setToggleGroup(toggleGroup);
        selectEnla.setToggleGroup(toggleGroup);
        selectPerece.setOnAction(event -> dynamicFormProducts());
        selectRefri.setOnAction(event -> dynamicFormProducts());
        selectEnla.setOnAction(event -> dynamicFormProducts());
    }

    public void resetVisibleApp(){
        pageHome.setVisible(true);

        salesContainer.setVisible(false);
        salesDataPage.setVisible(true);
        salesRegisterPage.setVisible(false);

        productsContainer.setVisible(false);
        productsDataPage.setVisible(true);
        productsRegisterPage.setVisible(false);

        clientsContainer.setVisible(false);
        clientsDataPage.setVisible(true);
        clientsRegisterPage.setVisible(false);
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

///////////////CRUD VENTAS///////////////
    public void getConfigurationProductsSale(){
        idProductsSale.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProduct()));
        nameProductsSale.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        priceProductsSale.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValue()));
        stockProductsSale.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getStock()));

        // Configurar la columna de botones
        /*addProductsSale.setCellFactory(param -> new TableCell<Button, Button>() {
            final Button addButton = new Button("Agregar");

            {
                addButton.getStyleClass().add("btnAddProduct");
                System.out.println(addButton);
                addButton.setOnAction(event -> {
                    //Product product = getTableView().getItems().get(getIndex());
                    // Perform action when button is clicked
                    // For example, you could add the product to a cart
                    System.out.println("Agregar a la cesta: ");
                });
            }

            @Override
            protected void updateItem(Button button, boolean empty) {
                super.updateItem(button, empty);
                if (empty || button == null) {
                    setGraphic(null);
                } else {
                    setGraphic(addButton);
                }
            }
        });*/
    }

    public void getConfigurationDetailSale() {
        columnAmount.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAmount()));
        columnDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItem().getDescription()));
        columnPrice.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getItem().getValue()));
        columnAccumulated.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getSubTotal()));
    }
    public void resetFormSalesRegister(){
        labelSaleId.setText("");
        labelSaleName.setText("");
        labelSalePhone.setText("");
        labelSaleAddress.setText("");
        labelSaleDate.setText("");
        labelSerie.setText("");

        labelSubTotal.setText("");
        labelIva.setText("");
        labelTotal.setText("");
    }

    public static Client filterClient(ObservableList<Client> clientList, String id) {
            return clientList.stream()
                    .filter(client -> client.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    public Double calculateAmount(double priceItem, int stock){
        return priceItem * stock;
    }

    public Double calculateSubTotal(double total){
        return total/1.19;
    }

    public void addProductSale(Product product, int amount){
        DetailSale detailSale = new DetailSale(amount, calculateAmount(product.getValue(), amount), product);
        listDetail.add(detailSale);
    }

    public TableRow<Product> selectItemRowProductSale(){
        TableRow<Product> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Product clickedProduct = row.getItem();
                for (Product product:listProduct) {
                    if (clickedProduct.getIdProduct().equals(product.getIdProduct())){
                        paneAdd.setVisible(true);
                        titleAdd.setText(product.getTitle());
                        idAdd.setText(product.getIdProduct());
                        stockAdd.setText(String.valueOf(product.getStock()));
                        break;
                    }
                }
            }

        });
        return row;
    }

    public static <Type> Boolean existIntoArray(String idItem, ObservableList<Type> list, Function<Type, String> idList){
        return list.stream().anyMatch(idExist -> idList.apply(idExist).equals(idItem));
    }


    ///////////////CRUD PRODUCTOS///////////////

    public void dynamicFormProducts(){
        if (selectPerece.isSelected()) {
            boxCodigo.setVisible(false);
            boxTemperatura.setVisible(false);
            dateLabelProduct.setText("Vencimiento:");
            boxCountry.setVisible(false);
            boxDate.setVisible(true);
        }else if(selectRefri.isSelected()) {
            boxCodigo.setVisible(true);
            boxTemperatura.setVisible(true);
            boxDate.setVisible(false);
            boxCodigo.setVisible(true);
            codLabel.setText("Cod. Aprovacion:");
            codigoTextField.setPromptText("Cod. Aprovacion");
            boxCountry.setVisible(false);
        }else if(selectEnla.isSelected()){
            dateLabelProduct.setText("Envasado:");
            boxCodigo.setVisible(true);
            codLabel.setText("Peso:");
            boxDate.setVisible(true);
            codigoTextField.setPromptText("Peso del producto");
            boxCountry.setVisible(true);
            boxTemperatura.setVisible(false);
        }
    }

    public void resetFormProductRegister(){
        idProduct.setDisable(false);
        productField.setDisable(false);
        detailField.setDisable(false);
        addProduct.setVisible(true);
        selectPerece.setDisable(false);
        selectRefri.setDisable(false);
        selectEnla.setDisable(false);
        idProduct.setText("");
        productField.setText("");
        detailField.setText("");
        valueProductField.setText("");
        stockField.setText("");
        codigoTextField.setText("");
        tempField.setText("");
        datePick.setValue(null);
        selectPerece.setSelected(true);
        boxDate.setVisible(true);
        boxTemperatura.setVisible(false);
        boxCodigo.setVisible(false);
        boxCountry.setVisible(false);
        boxCountrySelect.setValue(Country.ARGENTINA);
        dateLabelProduct.setText("Vencimiento:");
        updateButtonProduct.setVisible(false);
        deleteButonProduct.setVisible(false);
    }

    public void resetFormProductUpdate(Product producto){
        backButtonData.setDisable(true);
        updateButtonProduct.setVisible(true);
        deleteButonProduct.setVisible(true);
        addProduct.setVisible(false);
        selectPerece.setDisable(true);
        selectRefri.setDisable(true);
        selectEnla.setDisable(true);

        idProduct.setText(producto.getIdProduct());
        idProduct.setDisable(true);
        productField.setText(producto.getTitle());
        productField.setDisable(true);
        detailField.setText(producto.getDescription());
        detailField.setDisable(true);
        stockField.setText(String.valueOf(producto.getStock()));
        valueProductField.setText(String.valueOf(producto.getValue()));
        idInput.setDisable(true);
        nameInput.setDisable(true);
        lastNameInput.setDisable(true);
        if(producto instanceof Perishable){
            selectPerece.setSelected(true);
            dynamicFormProducts();
            datePick.setValue(((Perishable) producto).getDueDate());
            datePick.setDisable(true);
        }else if(producto instanceof Refrigerate) {
            selectRefri.setSelected(true);
            dynamicFormProducts();
            tempField.setText(String.valueOf(((Refrigerate) producto).getTemperature()));
            codigoTextField.setText(((Refrigerate) producto).getAprovationCode());
        }else if(producto instanceof Canned) {
            selectEnla.setSelected(true);
            dynamicFormProducts();
            datePick.setValue(((Canned) producto).getBatchDate());
            codigoTextField.setText(String.valueOf(((Canned) producto).getWeigth()));
        }
    }

    public TableRow<Product> selectItemRowProduct(){
        TableRow<Product> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Product clickedProduct = row.getItem();
                for (Product product:listProduct) {
                    if (clickedProduct.getIdProduct().equals(product.getIdProduct())){
                        productsRegisterPage.setVisible(true);
                        resetFormProductUpdate(product);
                        break;
                    }
                }
            }

        });
        return row;
    }
    public void addProductList(){
        if (selectPerece.isSelected()) {
            Perishable newProduct = new Perishable(idProduct.getText(), productField.getText(), "Perecedero",
                    Integer.valueOf(stockField.getText()), Double.valueOf(valueProductField.getText()), datePick.getValue());
            listProduct.add(newProduct);
            System.out.println("agregado perecedero");
        } else if(selectRefri.isSelected()){
            Refrigerate newProduct = new Refrigerate(idProduct.getText(), productField.getText(), "Refrigerado", Integer.valueOf(stockField.getText()), Double.valueOf(valueProductField.getText()), codigoTextField.getText(), Double.valueOf(tempField.getText()));
            listProduct.add(newProduct);
            System.out.println("agregado refrigerado");
        } else if(selectEnla.isSelected()){
            Canned newProduct = new Canned(idProduct.getText(), productField.getText(), "Envasado", Integer.valueOf(stockField.getText()), Double.valueOf(valueProductField.getText()), datePick.getValue(), Double.valueOf(codigoTextField.getText()), boxCountrySelect.getValue());
            listProduct.add(newProduct);
            System.out.println("agregado enlatado");
        }
    }

    public void deleteProduct(String id){
        listProduct.removeIf(product -> id.equals(product.getIdProduct()));
        productsRegisterPage.setVisible(false);
        alertUpdateDelete("Se ha eliminado con exitosamente");
    }

    public void updateProduct(){
        listProduct.forEach(product -> {
            if (product instanceof Perishable && idProduct.getText().equals(product.getIdProduct())) {
                Perishable perishable = (Perishable) product;
                Perishable update = new Perishable(idProduct.getText(), productField.getText(), "Perecedero",
                        Integer.valueOf(stockField.getText()), Double.valueOf(valueProductField.getText()), datePick.getValue());
                listProduct.set(listProduct.indexOf(perishable), update);
            } else if (product instanceof Refrigerate && idProduct.getText().equals(product.getIdProduct())){
                Refrigerate refrigerate = (Refrigerate) product;
                Refrigerate update = new Refrigerate(idProduct.getText(), productField.getText(), "Refrigerado", Integer.valueOf(stockField.getText()), Double.valueOf(valueProductField.getText()), codigoTextField.getText(), Double.valueOf(tempField.getText()));
                listProduct.set(listProduct.indexOf(refrigerate), update);
            }else if (product instanceof Refrigerate && idProduct.getText().equals(product.getIdProduct())){
                Canned refrigerate = (Canned) product;
                Canned update = new Canned (idProduct.getText(), productField.getText(), "Envasado", Integer.valueOf(stockField.getText()), Double.valueOf(valueProductField.getText()), datePick.getValue(), Double.valueOf(codigoTextField.getText()), boxCountrySelect.getValue());
                listProduct.set(listProduct.indexOf(refrigerate), update);
            }
        });
        productsRegisterPage.setVisible(false);
    }

    public void getProductList(){
        codigoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProduct()));
        productCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(getTypeProduct(cellData.getValue())));
        stockCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getStock()));
        valorCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValue()));
    }

    public String getTypeProduct(Product producto){
        String name = "";
        if(producto instanceof Perishable){
            return "Perecedero";
        }else if(producto instanceof Refrigerate){
            return "Refrigerado";
        }else if(producto instanceof Canned){
            return "Envasado";
        }
        return name;
    }

    public void filterAndShowMatchesProduct(String searchQuery) {
        if (searchQuery.isEmpty()){
            tableViewProducts.setItems(listProduct);
        } else {
            ObservableList<Product> filteredProduct = FXCollections.observableArrayList();

            for (Product product : listProduct) {
                if (product.getIdProduct().startsWith(searchQuery)) {
                    filteredProduct.add(product);
                }
            }
            tableViewProducts.setItems(filteredProduct);
        }
    }

    public static Product filterProduct(ObservableList<Product> listProduct, String id) {
        return listProduct.stream()
                .filter(product -> product.getIdProduct().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static <Type> Boolean existIntoArrayProduct(String idItem, ObservableList<Type> list, Function<Type, String> idList){
        return list.stream().anyMatch(idExist -> idList.apply(idExist).equals(idItem));
    }
}
