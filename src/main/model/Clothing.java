package model;

import org.json.JSONObject;

// A class representing a single piece of clothing
// A piece of clothing will have a name, color, type, buy and sell prices,
//     and a flag indicating whether it has been bought
public class Clothing {

    private final String name;
    private final String color;
    private final String clothingType;
    private boolean bought;

    // REQUIRES: color is one of: "red", "green", "blue"
    //           buyPrice and sellPrice are nonnegative
    // MODIFIES: this
    // EFFECTS: sets the fields of this as the parameters given
    public Clothing(String name, String color, String clothingType, boolean bought) {
        this.name = name;
        this.color = color;
        this.clothingType = clothingType;
        this.bought = bought;
    }

    // A series of getter functions for the fields defined in this class
    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public String getClothingType() {
        return this.clothingType;
    }

    public boolean isBought() {
        return this.bought;
    }

    // REQUIRES: this item is not already bought
    // MODIFIES: this
    // EFFECTS: sets the bought field to true
    public void buy() {
        this.bought = true;
    }

    // REQUIRES: this item is currently bought
    // MODIFIES: this
    // EFFECTS: sets the bought field to false
    public void sell() {
        this.bought = false;
    }

    // EFFECTS: Returns JSONObject containing the information about the piece of clothing
    public JSONObject clothingToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("color", color);
        json.put("type", clothingType);
        json.put("bought", boolToString(bought));
        return json;
    }

    // EFFECTS: Helper function for clothingToJson()
    //              returns "true" if true, "false" otherwise
    public String boolToString(boolean bool) {
        if (bool) {
            return "true";
        }
        return "false";
    }
}
