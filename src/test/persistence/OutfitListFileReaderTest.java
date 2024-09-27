package persistence;

import model.OutfitList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class OutfitListFileReaderTest {

    @Test
    public void testEmptyList() {
        OutfitListFileReader reader = new OutfitListFileReader("./data/empty_outfits_test.json");
        try {
            OutfitList outfitList = reader.outfitListFileReader();
            assertEquals(outfitList.getSize(), 0);
        } catch (IOException e) {
            fail("There should be no exception");
        }
    }

    @Test
    public void testFileNoWork() {
        OutfitListFileReader reader = new OutfitListFileReader("./data/among_us2.json");
        OutfitList outfitList = null;
        try {
            outfitList = reader.outfitListFileReader();
            fail("There should be an exception");
        } catch (IOException e) {
            assertEquals(outfitList, null);
        }
    }

    @Test
    public void testNonEmptyList() {
        OutfitListFileReader reader = new OutfitListFileReader("./data/nonempty_outfit_list.json");
        try {
            OutfitList outfitList = reader.outfitListFileReader();
            assertEquals(outfitList.getSize(), 3);
        } catch (IOException e) {
            fail("There should be no exception");
        }
    }
}
