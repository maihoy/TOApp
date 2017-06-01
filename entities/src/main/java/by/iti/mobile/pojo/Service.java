package by.iti.mobile.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by j on 11.5.17.
 */
@Entity
@Table(name = "service")
public class Service extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;
    @Column(name = "connection_cost")
    private Double connCost;
    @Column(name = "subscription_fee")
    private Double subFee;
    @Column(name = "call_cost")
    private Double callCost;
    @Column(name = "sms_cost")
    private Double smsCost;
    @Column(name = "internet_cost")
    private Double internetCost;
    @Column(name = "description")
    private String description;

    public Service() {
    }

    public Service(String name, Double connCost, Double subFee, Double callCost,
                   Double smsCost, Double internetCost, String description) {
        this.name = name;
        this.connCost = connCost;
        this.subFee = subFee;
        this.callCost = callCost;
        this.smsCost = smsCost;
        this.internetCost = internetCost;
        this.description = description;
    }

    public Service(Long id, String name, Double connCost, Double subFee, Double callCost,
                   Double smsCost, Double internetCost, String description) {
        super(id);
        this.name = name;
        this.connCost = connCost;
        this.subFee = subFee;
        this.callCost = callCost;
        this.smsCost = smsCost;
        this.internetCost = internetCost;
        this.description = description;

    }

    public Service(Service service) {
        this(service.getName(), service.getConnCost(), service.getSubFee(), service.getCallCost(),
                service.getSmsCost(), service.getInternetCost(), service.getDescription());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getConnCost() {
        return connCost;
    }

    public void setConnCost(Double connCost) {
        this.connCost = connCost;
    }

    public Double getSubFee() {
        return subFee;
    }

    public void setSubFee(Double subFee) {
        this.subFee = subFee;
    }

    public Double getCallCost() {
        return callCost;
    }

    public void setCallCost(Double callCost) {
        this.callCost = callCost;
    }

    public Double getSmsCost() {
        return smsCost;
    }

    public void setSmsCost(Double smsCost) {
        this.smsCost = smsCost;
    }

    public Double getInternetCost() {
        return internetCost;
    }

    public void setInternetCost(Double internetCost) {
        this.internetCost = internetCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        if (!super.equals(o)) return false;

        Service service = (Service) o;

        if (getName() != null ? !getName().equals(service.getName()) : service.getName() != null) return false;
        if (getConnCost() != null ? !getConnCost().equals(service.getConnCost()) : service.getConnCost() != null)
            return false;
        if (getSubFee() != null ? !getSubFee().equals(service.getSubFee()) : service.getSubFee() != null) return false;
        if (getCallCost() != null ? !getCallCost().equals(service.getCallCost()) : service.getCallCost() != null)
            return false;
        if (getSmsCost() != null ? !getSmsCost().equals(service.getSmsCost()) : service.getSmsCost() != null)
            return false;
        if (getInternetCost() != null ? !getInternetCost().equals(service.getInternetCost()) : service.getInternetCost() != null)
            return false;
        return getDescription() != null ? getDescription().equals(service.getDescription()) : service.getDescription() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getConnCost() != null ? getConnCost().hashCode() : 0);
        result = 31 * result + (getSubFee() != null ? getSubFee().hashCode() : 0);
        result = 31 * result + (getCallCost() != null ? getCallCost().hashCode() : 0);
        result = 31 * result + (getSmsCost() != null ? getSmsCost().hashCode() : 0);
        result = 31 * result + (getInternetCost() != null ? getInternetCost().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", connCost=" + connCost +
                ", id=" + getId() +
                ", subFee=" + subFee +
                ", callCost=" + callCost +
                ", smsCost=" + smsCost +
                ", internetCost=" + internetCost +
                ", description='" + description + '\'' +
                '}';
    }

}
