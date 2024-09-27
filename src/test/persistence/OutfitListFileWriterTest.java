package persistence;

import model.Clothing;
import model.ClothingList;
import model.Outfit;
import model.OutfitList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class OutfitListFileWriterTest {

    @Test
    public void testFileNoWork() {
        OutfitListFileWriter writer;
        try {
            writer = new OutfitListFileWriter("as./dataa\0mong_us.jason");
            writer.open();
            fail("There should be an exception");
        } catch (IOException e) {
        }
    }

    @Test
    public void testEmptyList() {
        try {
            OutfitList outfitList = new OutfitList();
            OutfitListFileWriter writer = new OutfitListFileWriter("./data/writing_empty_outfits_test.json");
            writer.open();
            writer.write(outfitList);
            writer.close();

            OutfitListFileReader reader = new OutfitListFileReader("./data/writing_empty_outfits_test.json");
            outfitList = reader.outfitListFileReader();
            assertEquals(0, outfitList.getSize());
        } catch (IOException e) {
            fail("There should be no exception");
        }
    }

    @Test
    public void testNonEmptyList() {
        try {
            OutfitList outfitList = new OutfitList();
            outfitList.addOutfit(new Outfit(null, null, null, null));
            outfitList.addOutfit(new Outfit(new Clothing("1", "red", "hat", true),
                    new Clothing("2", "red", "hat", true),
                    new Clothing("3", "red", "pants", true),
                    new Clothing("4", "red", "shoes", true)));
            OutfitListFileWriter writer = new OutfitListFileWriter("./data/writing_nonempty_outfits_test.json");
            writer.open();
            writer.write(outfitList);
            writer.close();

            OutfitListFileReader reader = new OutfitListFileReader("./data/writing_nonempty_outfits_test.json");
            outfitList = reader.outfitListFileReader();
            assertEquals(2, outfitList.getSize());
        } catch (IOException e) {
            fail("There should be no exception");
        }
    }
}
