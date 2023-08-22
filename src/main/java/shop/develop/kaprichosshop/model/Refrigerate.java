package shop.develop.kaprichosshop.model;

import java.util.Objects;

public class Refrigerate extends Product{
    private String aprovationCode;
    private double temperature;

    public Refrigerate(String idProduct, String title, String description, int stock, double value,String aprovationCode, double temperature) {
        super(idProduct, title, description, stock, value);
        this.aprovationCode = aprovationCode;
        this.temperature = temperature;
    }

    public String getAprovationCode() {
        return aprovationCode;
    }

    public void setAprovationCode(String aprovationCode) {
        this.aprovationCode = aprovationCode;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Refrigerate)) return false;
        Refrigerate refrigerate = (Refrigerate) o;
        return idProduct.equals(refrigerate.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }
}
