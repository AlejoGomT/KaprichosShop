package shop.develop.kaprichosshop.model;
import java.util.Date;
import java.util.Objects;

public class Canned  extends Product{
    private Date batchDate;
    private float weigth;
    private Country country;

    public Canned(String idProduct, String title, String description, double value, int stock,Date batchDate, float weigth, Country country) {
        super(idProduct, title, description, value, stock);
        this.batchDate = batchDate;
        this.weigth = weigth;
        this.country = country;
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    public float getWeigth() {
        return weigth;
    }

    public void setWeigth(float weigth) {
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