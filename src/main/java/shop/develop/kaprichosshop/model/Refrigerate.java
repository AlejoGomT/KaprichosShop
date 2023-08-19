package shop.develop.kaprichosshop.model;

import java.util.Objects;

public class Refrigerate extends Product{
    private String aprovationCode;
    private float temperature;

    public Refrigerate(String idProduct, String title, String description, double value, int stock,String aprovationCode, float temperature) {
        super(idProduct, title, description, value, stock);
        this.aprovationCode = aprovationCode;
        this.temperature = temperature;
    }

    public String getAprovationCode() {
        return aprovationCode;
    }

    public void setAprovationCode(String aprovationCode) {
        this.aprovationCode = aprovationCode;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
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
