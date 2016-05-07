package ComponentBase.address;

import ComponentBase.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by waiti on 5/1/2016.
 */
@Entity
public class Address {
    @Id
    private String id;
    private String number;
    private String village;
    private String street;
    private String subDistrict;
    private String district;
    private String privince;
    private String postal;
    @OneToMany
    private User owner;

    public Address() {

    }

    public Address(String number, String village, String street, String subDistrict, String district, String privince, String postal, User owner) {
        this.number = number;
        this.village = village;
        this.street = street;
        this.subDistrict = subDistrict;
        this.district = district;
        this.privince = privince;
        this.postal = postal;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
