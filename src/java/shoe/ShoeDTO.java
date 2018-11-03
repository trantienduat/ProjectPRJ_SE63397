/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoe;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author trant
 */
public class ShoeDTO implements Serializable{
    private String shoesID;
    private String description;
    private int totalQuantity;
    private HashMap<Integer, Float> sizeAndPrice;

    public ShoeDTO() {
    }

    public ShoeDTO(String shoesID, String description, int totalQuantity, HashMap<Integer, Float> sizeAndPrice) {
        this.shoesID = shoesID;
        this.description = description;
        this.totalQuantity = totalQuantity;
        this.sizeAndPrice = sizeAndPrice;
    }

    public String getShoesID() {
        return shoesID;
    }

    public void setShoesID(String shoesID) {
        this.shoesID = shoesID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getQuantity() {
        return totalQuantity;
    }

    public void setQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public HashMap<Integer, Float> getSizeAndPrice() {
        return sizeAndPrice;
    }

    public void setSizeAndPrice(HashMap<Integer, Float> sizeAndPrice) {
        this.sizeAndPrice = sizeAndPrice;
    }
    
    
}
