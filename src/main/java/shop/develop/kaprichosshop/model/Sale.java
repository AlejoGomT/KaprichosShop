package shop.develop.kaprichosshop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Sale {
    private String idSale;
    private LocalDate date;
    private Double total;
    private double tax;
    protected Client buyer;
    private ArrayList<DetailSale> detailSale;

    public Sale(String idSale, LocalDate date, Double total, double tax, Client buyer, ArrayList<DetailSale> detailSale) {
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

    public LocalDate getDate() {
        return date;
    }

    public Double getTotal() {
        return total;
    }

    public double getTax() {
        return tax;
    }

    public ArrayList<DetailSale> getDetailSale() {
        return detailSale;
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
