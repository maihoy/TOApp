package by.iti.mobile.dto;

import by.iti.mobile.pojo.City;
import by.iti.mobile.pojo.Country;
import by.iti.mobile.pojo.Street;
import by.iti.mobile.pojo.UserTariff;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by j on 17.4.17.
 */
public class UserDto {
    private Long userId;
    private Long userDataId;
    @NotBlank
    @Size(min = 5, max = 45)
    private String username;
    @NotBlank
    @Size(min = 5, max = 45)
    private String password;

    private String firstName;
    private String lastName;
    private City city;
    private Country country;
    private Street street;
    private List<UserTariff> userTariffs= new ArrayList<>();

    public UserDto(Long userId, Long userDataId, String username, String password,
                   String firstName, String lastName, City city, Country country,
                   Street street, List<UserTariff> userTariffs) {
        this.userId = userId;
        this.userDataId = userDataId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.street = street;
        this.userTariffs = userTariffs;
    }

    public UserDto(UserDto userDto) {
        this(userDto.userId,userDto.getUserDataId(), userDto.username, userDto.password,
                userDto.firstName, userDto.lastName,
                userDto.city, userDto.country, userDto.street, userDto.userTariffs);
    }

    public UserDto() {
    }

    public Long getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(Long userDataId) {
        this.userDataId = userDataId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public List<UserTariff> getUserTariffs() {
        return userTariffs;
    }

    public void addUserTariff(UserTariff userTariff){
        this.userTariffs.add(userTariff);
    }

    public void setUserTariffs(List<UserTariff> userTariffs) {
        this.userTariffs = userTariffs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        if (getUserId() != null ? !getUserId().equals(userDto.getUserId()) : userDto.getUserId() != null) return false;
        if (getUserDataId() != null ? !getUserDataId().equals(userDto.getUserDataId()) : userDto.getUserDataId() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(userDto.getUsername()) : userDto.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(userDto.getPassword()) : userDto.getPassword() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(userDto.getFirstName()) : userDto.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(userDto.getLastName()) : userDto.getLastName() != null)
            return false;
        if (getCity() != null ? !getCity().equals(userDto.getCity()) : userDto.getCity() != null) return false;
        if (getCountry() != null ? !getCountry().equals(userDto.getCountry()) : userDto.getCountry() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(userDto.getStreet()) : userDto.getStreet() != null) return false;
        return getUserTariffs() != null ? getUserTariffs().equals(userDto.getUserTariffs()) : userDto.getUserTariffs() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getUserDataId() != null ? getUserDataId().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getUserTariffs() != null ? getUserTariffs().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", userDataId=" + userDataId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city=" + city +
                ", country=" + country +
                ", street=" + street +
                ", userTariffs=" + userTariffs +
                '}';
    }
}
