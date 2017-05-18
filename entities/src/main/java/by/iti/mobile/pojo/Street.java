package by.iti.mobile.pojo;

import javax.persistence.*;

/**
 * Created by j on 11.5.17.
 */
@Entity
@Table(name = "street")
public class Street extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    City city;

    public Street() {
    }

    public Street(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Street(Long id, String name, City city) {
        super(id);
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Street)) return false;
        if (!super.equals(o)) return false;

        Street street = (Street) o;

        if (getName() != null ? !getName().equals(street.getName()) : street.getName() != null) return false;
        return getCity() != null ? getCity().equals(street.getCity()) : street.getCity() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}