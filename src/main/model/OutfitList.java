package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// A class representing the list of outfits the user has
public class OutfitList {
    private List<Outfit> outfitList;

    // MODIFIES: this
    // EFFECTS: initializes the list of outfits using an empty ArrayList
    public OutfitList() {
        this.outfitList = new ArrayList<Outfit>();
    }

    // Getter and setter functions for OutfitList
    public int getSize() {
        return outfitList.size();
    }

    public Outfit getAtIndex(int index) {
        return this.outfitList.get(index);
    }

    // MODIFIES: this
    // EFFECTS: adds an outfit onto the list of outfits
    public void addOutfit(Outfit outfit) {
        outfitList.add(outfit);
    }

    // MODIFIES: this
    // EFFECTS: removes an outfit from the list of outfits using the index given
    public void removeOutfit(int index) {
        outfitList.remove(index);
    }

    // EFFECTS: returns the outfit list as a JSONObject
    public JSONObject outfitListToJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Outfit outfit : outfitList) {
            jsonArray.put(outfit.outfitToJson());
        }

        json.put("outfitList", jsonArray);
        return json;
    }
}
