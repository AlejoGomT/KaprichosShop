package shop.develop.kaprichosshop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Store {
    private ArrayList<Client> listClient;
    private ArrayList<Product> listProduct;
    private ArrayList<Sale> listSale;

    public Store(ArrayList<Client> listClient, ArrayList<Product> listProduct, ArrayList<Sale> listSale) {
        this.listClient = listClient;
        this.listProduct = listProduct;
        this.listSale = listSale;
    }

    public ArrayList<Client> getListClient() {
        return listClient;
    }

    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public ArrayList<Sale> getListSale() {
        return listSale;
    }

    ////////////////////CRUD////////////////////

    //CREATE AREA

    public boolean createClientNatural(String name, String lastName, String address, String phone, String id, String email, LocalDate birth) {
        Natural clientNatural = new Natural(name, lastName, address, phone, id, email, birth);
        if (listClient.contains(clientNatural)) {
            return false;
        }
        listClient.add(clientNatural);
        return true;
    }

    public boolean createClientLegal(String name, String lastName, String address, String phone, String id) {
        Legal clientLegal = new Legal(name, lastName, address, phone, id);
        if (listClient.contains(clientLegal)) {
            return false;
        }
        listClient.add(clientLegal);
        return true;
    }


    public boolean createProductRefrigerate(String idProduct, String title, String description, double value, int stock, String aprovationCode, float temperature) {
        Refrigerate productRefrigerate = new Refrigerate(idProduct, title, description, stock, value, aprovationCode, temperature);
        if (listProduct.contains(productRefrigerate)) {
            return false;
        }
        listProduct.add(productRefrigerate);
        return true;
    }

    public boolean createProductCanned(String idProduct, String title, String description,  int stock, double value, LocalDate batchDate, float weigth, Country country) {
        Canned productCanned = new Canned(idProduct, title, description,  stock, value, batchDate, weigth, country);
        if (listProduct.contains(productCanned)) {
            return false;
        }
        listProduct.add(productCanned);
        return true;
    }

    public boolean createProductPerishable(String idProduct, String title, String description,int stock, double value, LocalDate dueDate) {
        Perishable productPerishable = new Perishable(idProduct, title, description, stock,value, dueDate);
        if (listProduct.contains(productPerishable)) {
            return false;
        }
        listProduct.add(productPerishable);
        return true;
    }

    //Delete Area

    public void deleteProduct(String idProduct) {
        if (listProduct.contains(idProduct)) {
            int i = listProduct.indexOf(idProduct);
            listProduct.remove(i);
        } else {
            System.out.println("No exist");
        }
    }

    public void deleteClient(String id) {
        if (listClient.contains(id)) {
            int i = listClient.indexOf(id);
            listClient.remove(i);
        } else {
            System.out.println("No exist");
        }

    }

    //Update Area
    public boolean updateProductRefrigerate(String idProduct, String title, String description, double value, int stock, String aprovationCode, float temperature) {
        Refrigerate productRefrigerate = new Refrigerate(idProduct, title, description,stock, value, aprovationCode, temperature);
        if (listProduct.contains(productRefrigerate)) {
            int i = listProduct.indexOf(productRefrigerate);
            listProduct.set(i, productRefrigerate);
            return true;
        }
        return false;
    }

    public boolean updateProductCanned(String idProduct, String title, String description, int stock,  double value, LocalDate batchDate, float weigth, Country country) {
        Canned productCanned = new Canned(idProduct, title, description,  stock, value, batchDate, weigth, country);
        if (listProduct.contains(productCanned)) {
            int i = listProduct.indexOf(productCanned);
            listProduct.set(i, productCanned);
            return true;
        }
        return false;
    }

    public boolean updateProductPerishable(String idProduct, String title, String description, double value, int stock, LocalDate dueDate) {
        Perishable productPerishable = new Perishable(idProduct, title, description,  stock,value, dueDate);
        if (listProduct.contains(productPerishable)) {
            int i = listProduct.indexOf(productPerishable);
            listProduct.set(i, productPerishable);
            return true;
        }
        return false;
    }

    public boolean updateClientNatural(String name, String lastName, String address, String phone, String id, String email, LocalDate birth) {
        Natural clientNatural = new Natural(name, lastName, address, phone, id, email, birth);
        if (listClient.contains(clientNatural)) {
            int i = listClient.indexOf(clientNatural);
            listClient.set(i, clientNatural);
            return true;
        }
        return false;
    }

    public boolean updateClientLegal(String name, String lastName, String address, String phone, String id) {
        Legal clientLegal = new Legal(name, lastName, address, phone, id);
        if (listClient.contains(clientLegal)) {
            int i = listClient.indexOf(clientLegal);
            listClient.set(i, clientLegal);
            return true;
        }
        return false;
    }


    //Read-Search Area
    public Product searchProduct(String idProduct) {
        if (listProduct.contains(idProduct)) {
            int i = listProduct.indexOf(idProduct);
            Product obj = listProduct.get(i);
            return obj;
        } else {
            return null;
        }
    }

    public Client searchClient(String id) {
        if (listClient.contains(id)) {
            int i = listClient.indexOf(id);
            Client obj = listClient.get(i);
            return obj;
        } else {
            return null;
        }
    }

    /**
     * (Transacción) La aplicación debe permitir registrar las ventas del almacén: de cada venta se necesita registrar la siguiente información:
     * El código, fecha, los detalles de las ventas, el cliente que hace la compra, total, iva.
     * Del detalle de la venta se debe registrar la cantidad de productos vendidos, el subtotal, el producto vendido(relación).
     */

    public boolean createSale(String idSale, LocalDate date, double tax, Client buyer, ArrayList<DetailSale> detailSale) {
        double total = detailSale.stream().mapToDouble(DetailSale::getSubTotal).sum();
        Sale sale = new Sale(idSale, date, total, tax, buyer, detailSale);
        if (listSale.contains(sale)) {
            return false;
        }
        listSale.add(sale);
        return true;
    }
}