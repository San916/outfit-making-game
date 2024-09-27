package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// A class representing the list of clothes that the user currently owns
public class ClothingList {
    private List<Clothing> clothingList;

    // MODIFIES: this
    // EFFECTS: initializes the list of clothing using an empty ArrayList
    public ClothingList() {
        this.clothingList = new ArrayList<Clothing>();
    }

    // Getter and setter functions for CLothingList
    public int getSize() {
        return clothingList.size();
    }

    public Clothing getAtIndex(int index) {
        return this.clothingList.get(index);
    }

    // MODIFIES: this
    // EFFECTS: adds the given clothing to the list of clothes
    public void addClothing(Clothing clothing) {
        clothingList.add(clothing);
    }

    // MODIFIES: this
    // EFFECTS: removes the clothing pointed to by the index given
    public void removeClothing(int index) {
        clothingList.remove(index);
    }

    // EFFECTS: returns the clothing list in a JSONObject format
    public JSONObject clothingListToJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Clothing clothing : clothingList) {
            jsonArray.put(clothing.clothingToJson());
        }

        json.put("clothingList", jsonArray);
        return json;
    }
}
