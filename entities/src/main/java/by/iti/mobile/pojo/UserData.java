package by.iti.mobile.pojo;

import javax.persistence.*;

/**
 * Created by j on 11.5.17.
 */
@Entity
@Table(name = "user_data")
public class UserData extends AbstractEntity<Long>{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "street_id", nullable = false)
    Street street;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    User user;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "city_id")
    Long cityId;

    @Column(name = "country_id")
    Long countryId;

    public UserData() {
    }
    public UserData(UserData userData) {
        this(userData.getStreet(),userData.getUser(),
                userData.getFirstName(),userData.getLastName(),
                userData.getStreet().getCity().getId(),
                userData.getStreet().getCity().getCountry().getId());
    }

    public UserData(Long id, Street street, User user,
                    String firstName, String lastName,
                    Long cityId, Long countryId) {
        super(id);
        this.street = street;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityId = cityId;
        this.countryId = countryId;
    }

    public UserData(Street street, User user, String firstName,
                    String lastName, Long cityId, Long countryId) {
        this.street = street;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityId = cityId;
        this.countryId = countryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;
        if (!super.equals(o)) return false;

        UserData userData = (UserData) o;

        if (getStreet() != null ? !getStreet().equals(userData.getStreet()) : userData.getStreet() != null)
            return false;
        if (getUser() != null ? !getUser().equals(userData.getUser()) : userData.getUser() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(userData.getFirstName()) : userData.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(userData.getLastName()) : userData.getLastName() != null)
            return false;
        if (getCityId() != null ? !getCityId().equals(userData.getCityId()) : userData.getCityId() != null)
            return false;
        return getCountryId() != null ? getCountryId().equals(userData.getCountryId()) : userData.getCountryId() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getCityId() != null ? getCityId().hashCode() : 0);
        result = 31 * result + (getCountryId() != null ? getCountryId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "street=" + street +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cityId=" + cityId +
                ", countryId=" + countryId +
                '}';
    }
}
