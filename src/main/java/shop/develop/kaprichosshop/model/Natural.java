package shop.develop.kaprichosshop.model;
import java.util.Date;
import java.util.Objects;

public class Natural extends Client {

    private String email;
    private Date birth;

    public Natural(String name, String lastName, String address,String phone,String id,String email, Date birth) {
        super(name,lastName,address,phone,id);
        this.email = email;
        this.birth = birth;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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
