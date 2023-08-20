package shop.develop.kaprichosshop.model;

import java.util.Objects;

public class Legal extends Client {
    public Legal(String id, String name, String lastName,String phone, String address) {
        super(id, name,lastName,phone, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Legal)) return false;
        Legal legal = (Legal) o;
        return id.equals(legal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
