package persistence;

import model.ClothingList;
import model.Clothing;

import org.json.JSONObject;

import java.io.*;

// A class that is used to write a .json file containing clothing list info
public class ClothingListFileWriter {
    private PrintWriter writer;
    private String destinationFile;

    // EFFECTS: constructs writer to write to destination file
    public ClothingListFileWriter(String destination) {
        this.destinationFile = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destinationFile);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of clothingList to file
    public void write(ClothingList clothingList, boolean writeFilter, String filter) {
        JSONObject json = clothingList.clothingListToJson();
        if (writeFilter) {
            json.put("currentClosetFilter", filter);
        }
        saveToFile(json.toString());
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
//        System.out.println(json);
        writer.print(json);
    }
}
