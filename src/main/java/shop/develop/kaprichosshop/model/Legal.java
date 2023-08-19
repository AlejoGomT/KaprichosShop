package shop.develop.kaprichosshop.model;

import java.util.Objects;

public class Legal extends Client {
    public Legal(String name, String lastName, String address,String phone,String id) {
        super(name,lastName,address,phone,id);
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
