package model;

import org.json.JSONObject;
import exceptions.ClothingNotFoundException;

import java.util.Optional;

// A class representing a single outfit
// A piece of clothing will a hat, top, pants, and shoes
public class Outfit {
    private Clothing hat;
    private Clothing top;
    private Clothing pants;
    private Clothing shoes;

    // MODIFIES: this
    // EFFECTS: sets the fields of this as the parameters given, unless the values given are null
    public Outfit(Clothing hat, Clothing top, Clothing pants, Clothing shoes) {
        if (hat != null) {
            this.hat = hat;
        }
        if (top != null) {
            this.top = top;
        }
        if (pants != null) {
            this.pants = pants;
        }
        if (shoes != null) {
            this.shoes = shoes;
        }
    }

    // Getter and setter functions for Outfit
    public Clothing getHat() {
        return hat;
    }

    public Clothing getTop() {
        return top;
    }

    public Clothing getPants() {
        return pants;
    }

    public Clothing getShoes() {
        return shoes;
    }

    public void setHat(Clothing hat) {
        this.hat = hat;
    }

    public void setTop(Clothing top) {
        this.top = top;
    }

    public void setPants(Clothing pants) {
        this.pants = pants;
    }

    public void setShoes(Clothing shoes) {
        this.shoes = shoes;
    }

    // EFFECTS: Returns JSONObject containing the information about the outfit
    public JSONObject outfitToJson() {
        JSONObject json = new JSONObject();
        if (hat == null) {
            json.put("hat", "none");
        } else {
            json.put("hat", hat.clothingToJson());
        }
        if (top == null) {
            json.put("top", "none");
        } else {
            json.put("top", top.clothingToJson());
        }
        if (pants == null) {
            json.put("pants", "none");
        } else {
            json.put("pants", pants.clothingToJson());
        }
        if (shoes == null) {
            json.put("shoes", "none");
        } else {
            json.put("shoes", shoes.clothingToJson());
        }
        return json;
    }
}
