package persistence;

import model.ClothingList;
import model.Clothing;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// A class that is used to read a given clothing list file
public class ClothingListFileReader {
    ClothingList clothingList;

    String fileName;

    // MODIFIES: this
    // EFFECTS: sets the parameters as the ones given
    public ClothingListFileReader(String filename) {
        this.fileName = filename;
        this.clothingList = new ClothingList();
    }

    // MODIFIES: this
    // EFFECTS: reads the file stored in this object and returns the clothing list described in the file
    public ClothingList clothingListFileReader() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        String jsonData = contentBuilder.toString();

        JSONObject jsonObject = new JSONObject(jsonData);

        siftData(jsonObject);

        return clothingList;
    }

    // EFFECTS: Closet lists files contain information on the current applied filter,
    //              so this function just takes that filter information
    public String extractFilter() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        String jsonData = contentBuilder.toString();

        JSONObject jsonObject = new JSONObject(jsonData);

        return jsonObject.getString("currentClosetFilter");
    }

    // MODIFIES: this
    // EFFECTS: looks through every piece of clothing described in the file and adds them into clothingList
    //              by calling addObject() on every piece of clothing
    public void siftData(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("clothingList");
        for (Object json : jsonArray) {
            JSONObject nextClothing = (JSONObject) json;
            addClothing(nextClothing);
        }
    }

    // MODIFIES: this
    // EFFECTS: Turns the details of a single piece of clothing into an actual piece of clothing,
    //              and adds it to clothingList
    public void addClothing(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String colour = jsonObject.getString("color");
        String clothingType = jsonObject.getString("type");
        String bought = jsonObject.getString("bought");
        Clothing clothing = new Clothing(name, colour, clothingType, toBoolean(bought));
        clothingList.addClothing(clothing);
    }

    // EFFECTS: returns true if the string is equal to "true", false otherwise
    //              used as a helper function for addClothing()
    public boolean toBoolean(String str) {
        if (str.equals("true")) {
            return true;
        }
        return false;
    }
}