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
public class CustomerConfirmInfoErrors implements Serializable{
    private String addressDeliveryLengthError;
    private String receiverLengthError;
    private String receiverPhoneNumberFormatError;

    public CustomerConfirmInfoErrors() {
    }

    public CustomerConfirmInfoErrors(String addressDeliveryLengthError, String receiverLengthError, String receiverPhoneNumberFormatError) {
        this.addressDeliveryLengthError = addressDeliveryLengthError;
        this.receiverLengthError = receiverLengthError;
        this.receiverPhoneNumberFormatError = receiverPhoneNumberFormatError;
    }

    public String getAddressDeliveryLengthError() {
        return addressDeliveryLengthError;
    }

    public void setAddressDeliveryLengthError(String addressDeliveryLengthError) {
        this.addressDeliveryLengthError = addressDeliveryLengthError;
    }

    public String getReceiverLengthError() {
        return receiverLengthError;
    }

    public void setReceiverLengthError(String receiverLengthError) {
        this.receiverLengthError = receiverLengthError;
    }

    public String getReceiverPhoneNumberFormatError() {
        return receiverPhoneNumberFormatError;
    }

    public void setReceiverPhoneNumberFormatError(String receiverPhoneNumberFormatError) {
        this.receiverPhoneNumberFormatError = receiverPhoneNumberFormatError;
    }
    
    
}
