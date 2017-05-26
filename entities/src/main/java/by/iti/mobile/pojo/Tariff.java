package by.iti.mobile.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by j on 11.5.17.
 */
@Entity
@Table(name = "tariff")
public class Tariff extends AbstractEntity<Long> {
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


    public Tariff() {
    }

    public Tariff(Long id, String name, Double connCost, Double subFee,
                  Double callCost, Double smsCost, Double internetCost,
                  String description) {
        super(id);
        this.name = name;
        this.connCost = connCost;
        this.subFee = subFee;
        this.callCost = callCost;
        this.smsCost = smsCost;
        this.internetCost = internetCost;
        this.description = description;
    }

    public Tariff(String name, Double connCost, Double subFee,
                  Double callCost, Double smsCost, Double internetCost,
                  String description) {
        this.name = name;
        this.connCost = connCost;
        this.subFee = subFee;
        this.callCost = callCost;
        this.smsCost = smsCost;
        this.internetCost = internetCost;
        this.description = description;
    }

    public Tariff(Tariff tariff) {
        this(tariff.getName(), tariff.getConnCost(), tariff.getSubFee(), tariff.getCallCost(),
                tariff.getSmsCost(), tariff.getInternetCost(), tariff.getDescription());
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
        if (!(o instanceof Tariff)) return false;
        if (!super.equals(o)) return false;

        Tariff tariff = (Tariff) o;

        if (getName() != null ? !getName().equals(tariff.getName()) : tariff.getName() != null) return false;
        if (getConnCost() != null ? !getConnCost().equals(tariff.getConnCost()) : tariff.getConnCost() != null)
            return false;
        if (getSubFee() != null ? !getSubFee().equals(tariff.getSubFee()) : tariff.getSubFee() != null) return false;
        if (getCallCost() != null ? !getCallCost().equals(tariff.getCallCost()) : tariff.getCallCost() != null)
            return false;
        if (getSmsCost() != null ? !getSmsCost().equals(tariff.getSmsCost()) : tariff.getSmsCost() != null)
            return false;
        if (getInternetCost() != null ? !getInternetCost().equals(tariff.getInternetCost()) : tariff.getInternetCost() != null)
            return false;
        return getDescription() != null ? getDescription().equals(tariff.getDescription()) : tariff.getDescription() == null;

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
        return "Tariff{" +
                "name='" + name + '\'' +
                ", connCost=" + connCost +
                ", subFee=" + subFee +
                ", callCost=" + callCost +
                ", smsCost=" + smsCost +
                ", internetCost=" + internetCost +
                ", description='" + description + '\'' +
                '}';
    }
}
