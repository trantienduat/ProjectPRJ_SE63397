/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import java.io.Serializable;

/**
 *
 * @author trant
 */
public class CustomerDTO implements Serializable {

    private String custID;
    private String lastName;
    private String middleName;
    private String firstName;
    private String address;
    private String phone;
    private int custLevel;


    public CustomerDTO() {
    }

    public CustomerDTO(String custID, String lastName, String middleName, String firstName, String address, String phone, int custLevel) {
        this.custID = custID;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
        this.address = address;
        this.phone = phone;
        this.custLevel = custLevel;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(int custLevel) {
        this.custLevel = custLevel;
    }
}
