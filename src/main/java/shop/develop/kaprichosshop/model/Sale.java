package shop.develop.kaprichosshop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Sale {
    private String idSale;
    private Date date;
    private Double total;
    private double tax;
    protected Client buyer;
    private ArrayList<DetailSale> detailSale;

    public Sale(String idSale, Date date, Double total, double tax, Client buyer, ArrayList<DetailSale> detailSale) {
        this.idSale = idSale;
        this.date = date;
        this.buyer = buyer;
        this.total = total;
        this.tax = tax;
        this.detailSale = detailSale;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public ArrayList<DetailSale> getDetailSale() {
        return detailSale;
    }

    public void setDetailSale(ArrayList<DetailSale> detailSale) {
        this.detailSale = detailSale;
    }

    public void RegisterSale() {

    }

    public String searchSale() {

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return idSale.equals(sale.idSale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSale);
    }

}
