/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author trant
 */
public class CartObj implements Serializable {

    private String custID;
    private LinkedHashMap<String, HashMap<Integer, Integer>> items;
    //<ShoesID,<size, quantity>>

    public CartObj() {
    }

    public CartObj(String custID, LinkedHashMap<String, HashMap<Integer, Integer>> items) {
        this.custID = custID;
        this.items = items;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public LinkedHashMap<String, HashMap<Integer, Integer>> getItems() {
        return items;
    }

    public void setItems(LinkedHashMap<String, HashMap<Integer, Integer>> items) {
        this.items = items;
    }

    public void addItemToCart(String shoesID, int size) {
        if (items == null) {
            items = new LinkedHashMap<>();
        }
        int quantity = 1;
        HashMap<Integer, Integer> shoesDetails = items.get(shoesID);
        if (shoesDetails == null) {
            shoesDetails = new HashMap<>();
        }
        if (items.containsKey(shoesID)) {
            if (shoesDetails.containsKey(size)) {
                quantity = shoesDetails.get(size) + 1;
            }
        }
        shoesDetails.put(size, quantity);
        items.put(shoesID, shoesDetails);
    }

    public void removeItemFromCart(String shoesID, int size) {
        if (items == null) {
            return;
        }//end if items null
        HashMap<Integer, Integer> shoesDetails = items.get(shoesID);
        if (shoesDetails == null) {
            return;
        }//end if shoesDetails null
        if (items.containsKey(shoesID)) {
            if (shoesDetails.containsKey(size)) {
                shoesDetails.remove(size);
                if (shoesDetails.isEmpty()) {
                    shoesDetails = null;
                }//end if shoesDetails isEmpty
            }//end if shoesDetails contains Key
            if (items.isEmpty()) {
                items = null;
            }//ens if items isEmpty
            if (items.get(shoesID).entrySet().isEmpty()) {
                items.remove(shoesID);
            }
        }//end if items containsKey
    }
}
