/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errors;

import java.io.Serializable;

/**
 *
 * @author trant
 */
public class CustomerRegisterInfoErrors implements Serializable {

    private String usernameLengthError;
    private String lastnameLengthError;
    private String middlenameLengthError;
    private String firstnameLengthError;
    private String passwordLengthError;
    private String addressLengthError;
    private String phoneFormatError;
    private String duplicateUsernameError;

    public CustomerRegisterInfoErrors() {
    }

    public CustomerRegisterInfoErrors(String usernameLengthError, String lastnameLengthError, String middlenameLengthError, String firstnameLengthError, String passwordLengthError, String addressLengthError, String phoneFormatError, String duplicateUsernameError) {
        this.usernameLengthError = usernameLengthError;
        this.lastnameLengthError = lastnameLengthError;
        this.middlenameLengthError = middlenameLengthError;
        this.firstnameLengthError = firstnameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.addressLengthError = addressLengthError;
        this.phoneFormatError = phoneFormatError;
        this.duplicateUsernameError = duplicateUsernameError;
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getLastnameLengthError() {
        return lastnameLengthError;
    }

    public void setLastnameLengthError(String lastnameLengthError) {
        this.lastnameLengthError = lastnameLengthError;
    }

    public String getMiddlenameLengthError() {
        return middlenameLengthError;
    }

    public void setMiddlenameLengthError(String middlenameLengthError) {
        this.middlenameLengthError = middlenameLengthError;
    }

    public String getFirstnameLengthError() {
        return firstnameLengthError;
    }

    public void setFirstnameLengthError(String firstnameLengthError) {
        this.firstnameLengthError = firstnameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    public String getPhoneFormatError() {
        return phoneFormatError;
    }

    public void setPhoneFormatError(String phoneFormatError) {
        this.phoneFormatError = phoneFormatError;
    }

    public String getDuplicateUsernameError() {
        return duplicateUsernameError;
    }

    public void setDuplicateUsernameError(String duplicateUsernameError) {
        this.duplicateUsernameError = duplicateUsernameError;
    }
    
    

}
