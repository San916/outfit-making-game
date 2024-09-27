package persistence;

import model.ClothingList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class ClothingListFileReaderTest {

    @Test
    public void testEmptyList() {
        ClothingListFileReader reader = new ClothingListFileReader("./data/empty_closet_test.json");
        try {
            ClothingList clothingList = reader.clothingListFileReader();
            assertEquals(clothingList.getSize(), 0);
        } catch (IOException e) {
            fail("There should be no exception");
        }
    }

    @Test
    public void testFileNoWork() {
        ClothingListFileReader reader = new ClothingListFileReader("./data/among_us.json");
        ClothingList clothingList = null;
        try {
            clothingList = reader.clothingListFileReader();
            fail("There should be an exception");
        } catch (IOException e) {
            assertEquals(clothingList, null);
        }
    }

    @Test
    public void extractFilterTest() {
        ClothingListFileReader reader = new ClothingListFileReader("./data/empty_closet_test.json");
        try {
            String filter = reader.extractFilter();
            assertTrue(filter.equals("none"));
        } catch (IOException e) {
            fail("There should be no exception");
        }
    }

    @Test
    public void testNonEmptyList() {
        ClothingListFileReader reader = new ClothingListFileReader("./data/nonempty_closet_clothing_list.json");
        try {
            ClothingList clothingList = reader.clothingListFileReader();
            assertEquals(clothingList.getSize(), 3);
        } catch (IOException e) {
            fail("There should be no exception");
        }
    }

    @Test
    public void extractFilterTest2() {
        ClothingListFileReader reader = new ClothingListFileReader("./data/nonempty_closet_clothing_list.json");
        try {
            String filter = reader.extractFilter();
            assertTrue(filter.equals("hat"));
        } catch (IOException e) {
            fail("There should be no exception");
        }
    }

}
