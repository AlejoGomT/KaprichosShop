package shop.develop.kaprichosshop.model;

import java.util.List;

public class DetailSale {
    private int amount;
    private  double subTotal;
    private List<Product> item;

    public DetailSale(int amount, double subTotal, List<Product> item) {
        this.amount = amount;
        this.subTotal = subTotal;
        this.item = item;
    }
    /** (Transacción) La aplicación debe permitir registrar las ventas del almacén: de cada venta se necesita registrar la siguiente información:
     El código, fecha, los detalles de las ventas, el cliente que hace la compra, total, iva.
     Del detalle de la venta se debe registrar la cantidad de productos vendidos, el subtotal, el producto vendido(relación). */

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public List<Product> getItem() {
        return item;
    }

    public void setItem(List<Product> item) {
        this.item = item;
    }

    public void itemRegister()  {

    }
    public void itemUpdate(){

    }
    public void itemDelate(){

    }
    public String printDetail(){
        return null;
    }
}

