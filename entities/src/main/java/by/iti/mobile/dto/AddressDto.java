package by.iti.mobile.dto;

import by.iti.mobile.pojo.City;
import by.iti.mobile.pojo.Country;
import by.iti.mobile.pojo.Street;

/**
 * Created by j on 18.5.17.
 */
public class AddressDto {

    private City city;
    private Country country;
    private Street street;

    public AddressDto() {
    }

    public AddressDto(City city, Country country, Street street) {
        this.city = city;
        this.country = country;
        this.street = street;
    }
    public AddressDto(AddressDto addressDto){
        this(addressDto.getCity(),addressDto.getCountry(),addressDto.getStreet());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDto)) return false;

        AddressDto that = (AddressDto) o;

        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        if (getCountry() != null ? !getCountry().equals(that.getCountry()) : that.getCountry() != null) return false;
        return getStreet() != null ? getStreet().equals(that.getStreet()) : that.getStreet() == null;

    }

    @Override
    public int hashCode() {
        int result = getCity() != null ? getCity().hashCode() : 0;
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "city=" + city +
                ", country=" + country +
                ", street=" + street +
                '}';
    }

}
