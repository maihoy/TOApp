package by.iti.mobile.pojo;

import javax.persistence.*;

/**
 * Created by j on 11.5.17.
 */
@Entity
@Table(name = "city")
public class City extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "country_id")
    Country country;

    public City() {
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City(Long id, String name, Country country) {
        super(id);
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof City)) return false;
        if (!super.equals(o)) return false;

        City city = (City) o;

        if (getName() != null ? !getName().equals(city.getName()) : city.getName() != null) return false;
        return getCountry() != null ? getCountry().equals(city.getCountry()) : city.getCountry() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
