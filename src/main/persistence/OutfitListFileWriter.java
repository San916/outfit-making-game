package persistence;

import model.ClothingList;
import model.Clothing;
import model.OutfitList;
import model.Outfit;

import org.json.JSONObject;

import java.io.*;

// A class that is used to write a .json file containing outfit list info
public class OutfitListFileWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destinationFile;

    // EFFECTS: constructs writer to write to destination file
    public OutfitListFileWriter(String destination) {
        this.destinationFile = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destinationFile));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of outfit list to file
    public void write(OutfitList outfitList) {
        JSONObject json = outfitList.outfitListToJson();
        saveToFile(json.toString(TAB));
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
