package shop.develop.kaprichosshop.model;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Natural extends Client {

    private String email;
    private LocalDate birth;

    public Natural(String id, String name, String lastName, String phone, String address, String email, LocalDate birth) {
        super(id,name,lastName,phone, address);
        this.email = email;
        this.birth = birth;
    }


    public String getEmail() {
        return email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Natural)) return false;
        Natural natural = (Natural) o;
        return id.equals(natural.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
