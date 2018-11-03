/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.io.Serializable;

/**
 *
 * @author trant
 */
public class AccountDTO implements Serializable {

    private String username;
    private String password;
    private String custID;

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, String custID) {
        this.username = username;
        this.password = password;
        this.custID = custID;
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

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

}
