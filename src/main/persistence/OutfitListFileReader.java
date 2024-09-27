package persistence;

import model.Clothing;
import model.Outfit;
import model.OutfitList;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// A class that is used to read a given outfit list .json file
public class OutfitListFileReader {
    OutfitList outfitList;

    String fileName;

    // MODIFIES: this
    // EFFECTS: sets the parameters as the ones given
    public OutfitListFileReader(String filename) {
        this.fileName = filename;
        this.outfitList = new OutfitList();
    }

    // MODIFIES: this
    // EFFECTS: reads the file stored in this object and returns the outfit list in the file
    public OutfitList outfitListFileReader() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        String jsonData = contentBuilder.toString();

        JSONObject jsonObject = new JSONObject(jsonData);

        siftData(jsonObject);

        return outfitList;
    }

    // MODIFIES: this
    // EFFECTS: Parses through every outfit in the files
    public void siftData(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("outfitList");
        for (Object json : jsonArray) {
            JSONObject nextOutfit = (JSONObject) json;
            addOutfit(nextOutfit);
        }
    }

    // MODIFIES: this
    // EFFECTS: Goes through the component of every outfit in the file and accordingly adds it to clothingList
    public void addOutfit(JSONObject jsonObject) {
        Clothing hat = null;
        Clothing top = null;
        Clothing pants = null;
        Clothing shoes = null;
        if (!jsonObject.optString("hat").equals("none")) {
            hat = getClothing(jsonObject.getJSONObject("hat"));
        }
        if (!jsonObject.optString("top").equals("none")) {
            top = getClothing(jsonObject.getJSONObject("top"));
        }
        if (!jsonObject.optString("pants").equals("none")) {
            pants = getClothing(jsonObject.getJSONObject("pants"));
        }
        if (!jsonObject.optString("shoes").equals("none")) {
            shoes = getClothing(jsonObject.getJSONObject("shoes"));
        }
        Outfit outfit = new Outfit(hat, top, pants, shoes);
        outfitList.addOutfit(outfit);
    }

    // EFFECTS: combines the details of a piece of clothing into an actual piece of clothing and returns it
    public Clothing getClothing(JSONObject jsonObject) {
//        System.out.println(jsonObject.toString());
        String name = jsonObject.getString("name");
        String colour = jsonObject.getString("color");
        String clothingType = jsonObject.getString("type");
        String bought = jsonObject.getString("bought");
        return new Clothing(name, colour, clothingType, toBoolean(bought));
    }

    // EFFECTS: returns true if the string = "true", false otherwise. Used as a helper function in getClothing()
    public boolean toBoolean(String str) {
        if (str.equals("true")) {
            return true;
        }
        return false;
    }
}