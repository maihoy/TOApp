package by.iti.mobile.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by j on 11.5.17.
 */
@Entity
@Table(name = "user_tariff")
public class UserTariff extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tariff_id", nullable = false)
    private Tariff tariff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "account_balance")
    private Double accBalance;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_tariff_has_service", catalog = "mobile", joinColumns = {
            @JoinColumn(name = "user_tariff_id", nullable = false, updatable = false)
    }, inverseJoinColumns = {@JoinColumn(name = "service_id", nullable = false, updatable = false)})
    private Set<Service> service = new HashSet<>();

    public UserTariff() {
    }

    public UserTariff(Tariff tariff, User user, String phoneNum,
                      Double accBalance, Set<Service> service) {
        this.tariff = tariff;
        this.user = user;
        this.phoneNum = phoneNum;
        this.accBalance = accBalance;
        this.service = service;
    }

    public UserTariff(Long id, Tariff tariff, User user, String phoneNum,
                      Double accBalance, Set<Service> service) {
        super(id);
        this.tariff = tariff;
        this.user = user;
        this.phoneNum = phoneNum;
        this.accBalance = accBalance;
        this.service = service;
    }

    public UserTariff(UserTariff userTariff) {
        this(userTariff.getTariff(), userTariff.getUser(),
                userTariff.getPhoneNum(), userTariff.getAccBalance(),
                userTariff.getService());
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(Double accBalance) {
        this.accBalance = accBalance;
    }

    public Set<Service> getService() {
        return service;
    }

    public void addService(Service service) {
        Set<Service> services = getService();
        services.add(service);
    }

    public void setService(Set<Service> service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTariff)) return false;
        if (!super.equals(o)) return false;

        UserTariff that = (UserTariff) o;

        if (getTariff() != null ? !getTariff().equals(that.getTariff()) : that.getTariff() != null) return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) return false;
        if (getPhoneNum() != null ? !getPhoneNum().equals(that.getPhoneNum()) : that.getPhoneNum() != null)
            return false;
        if (getAccBalance() != null ? !getAccBalance().equals(that.getAccBalance()) : that.getAccBalance() != null)
            return false;
        return getService() != null ? getService().equals(that.getService()) : that.getService() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getTariff() != null ? getTariff().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getPhoneNum() != null ? getPhoneNum().hashCode() : 0);
        result = 31 * result + (getAccBalance() != null ? getAccBalance().hashCode() : 0);
        result = 31 * result + (getService() != null ? getService().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserTariff{" +
                "tariff=" + tariff +
                ", user=" + user +
                ", phoneNum='" + phoneNum + '\'' +
                ", accBalance=" + accBalance +
                ", service=" + service +
                '}';
    }
}
