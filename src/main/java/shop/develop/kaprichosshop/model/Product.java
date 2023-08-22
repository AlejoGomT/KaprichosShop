package shop.develop.kaprichosshop.model;

public class Product {
    protected String idProduct;
    private String title;
    private String description;
    private double value;
    private int stock;

    public Product(String idProduct, String title, String description,int stock, double value) {
        this.idProduct = idProduct;
        this.title = title;
        this.description = description;
        this.value = value;
        this.stock = stock;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void listProducts(){

    }
}
