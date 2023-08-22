package shop.develop.kaprichosshop.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Perishable extends Product {
    private LocalDate dueDate;

    public Perishable(String idProduct, String title, String description, double value, int stock, LocalDate dueDate) {
        super(idProduct, title, description, value, stock);
        this.dueDate = dueDate;

    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Perishable)) return false;
        Perishable peri = (Perishable) o;
        return idProduct.equals(peri.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }
}
