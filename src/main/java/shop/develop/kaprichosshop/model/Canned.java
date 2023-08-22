package shop.develop.kaprichosshop.model;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Canned  extends Product{
    private LocalDate batchDate;
    private double weigth;
    private Country country;

    public Canned(String idProduct, String title, String description, int stock, double value, LocalDate batchDate, double weigth, Country country) {
        super(idProduct, title, description,  stock, value);
        this.batchDate = batchDate;
        this.weigth = weigth;
        this.country = country;
    }

    public LocalDate getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(LocalDate batchDate) {
        this.batchDate = batchDate;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Canned)) return false;
        Canned canned = (Canned) o;
        return idProduct.equals(canned.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }
}