package persistence;

import model.Clothing;
import model.ClothingGame;
import model.ClothingList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class ClothingListFileWriterTest {
    @Test
    public void testFileNoWork() {
        ClothingListFileWriter writer;
        try {
            writer = new ClothingListFileWriter("as./dataa\0mong_us.jason");
            writer.open();
            fail("There should be an exception");
        } catch (IOException e) {
        }
    }

    @Test
    public void testWriterWithFilter() {
        try {
            ClothingList clothingList = new ClothingList();
            ClothingListFileWriter writer = new ClothingListFileWriter("./data/writing_test.json");
            writer.open();
            writer.write(clothingList, true, "hat");
            writer.close();

            ClothingListFileReader reader = new ClothingListFileReader("./data/writing_test.json");
            clothingList = reader.clothingListFileReader();
            assertEquals(0, clothingList.getSize());
            assertEquals(reader.extractFilter(), "hat");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterNoFilter() {
        try {
            ClothingList clothingList = new ClothingList();
            ClothingListFileWriter writer = new ClothingListFileWriter("./data/writing_test1.json");
            writer.open();
            writer.write(clothingList, false, "none");
            writer.close();

            ClothingListFileReader reader = new ClothingListFileReader("./data/writing_test1.json");
            clothingList = reader.clothingListFileReader();
            assertEquals(0, clothingList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testNonEmpty() {
        try {
            ClothingList clothingList = new ClothingList();
            clothingList.addClothing(new Clothing("1", "red", "hat", true));
            ClothingListFileWriter writer = new ClothingListFileWriter("./data/writing_test2.json");
            writer.open();
            writer.write(clothingList, false, "none");
            writer.close();

            ClothingListFileReader reader = new ClothingListFileReader("./data/writing_test2.json");
            clothingList = reader.clothingListFileReader();
            assertEquals("hat", clothingList.getAtIndex(0).getClothingType());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
