package ui;

import model.Clothing;
import model.ClothingGame;
import model.ClothingList;
import model.Outfit;
import model.OutfitList;

import java.util.List;
import java.util.Scanner;

// The console application of the clothing game
// Contains methods and fields that are relevant to the console application of this game
public class ConsoleUI {
    ClothingGame clothingGame;

    Scanner scanner = new Scanner(System.in);

    // MODIFIES: this
    // EFFECTS: constructor for ConsoleUI
    public ConsoleUI() {
        clothingGame = new ClothingGame();
    }

    // MODIFIES: this
    // EFFECTS: Prints out a message to signal the start of the game, then calls home() to start the game
    public void beginGame() {
        System.out.println("Hello! Welcome to my clothes shopping simulator!");
        System.out.println("Press enter key to continue!");
        String userInput = scanner.nextLine();
        home();
    }

    // MODIFIES: this
    // EFFECTS: keeps running as long as the game hasn't ended. Acts as the main screen of the game
    public void home() {
        while (true) {
            printHomeMessage();

            String userInput = scanner.nextLine();

            // Check for validity of the input
            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")
                    && !userInput.equals("4") && !userInput.equals("5") && !userInput.equals("6")) {
                System.out.println("It seems you have not provided a valid input, please try again!");
                userInput = scanner.nextLine();
            }

            if (userInput.equals("1")) {
                closet(); // Enter the closer
            } else if (userInput.equals("2")) {
                mirror(); // Go to the mirror
            } else if (userInput.equals("3")) {
                shopping(); // Enter the shop
            } else if (userInput.equals("4")) {
                clothingGame.saveEverything();
            } else if (userInput.equals("5")) {
                clothingGame.loadEverything();
            } else {
                break; // Exit the loop, ending the game
            }
        }
    }

    // EFFECTS: Print out the ui for when at the home page
    public void printHomeMessage() {
        System.out.println("You are currently in your room, you have four options:");
        System.out.println("[1]: Go see your closet");
        System.out.println("[2]: Go look at your mirror");
        System.out.println("[3]: Go clothes shopping");
        System.out.println("[4]: Save the current game state");
        System.out.println("[5]: Load a saved game state");
        System.out.println("[6]: Exit the simulator");
    }

    // EFFECTS: acts as the closet screen; keeps running while the user is taking actions within the closet screen
    public void closet() {
        // As many actions as the user would like can be committed, until they choose to exit the closet
        while (true) {
            displayOwnedClothes(false);

            displayClosetText();

            String userInput = scanner.nextLine();
            // Check for validity of the input
            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")
                    && !userInput.equals("4") && !userInput.equals("5")) {
                System.out.println("It seems you have not provided a valid input, please try again!");
                userInput = scanner.nextLine();
            }

            if (userInput.equals("1")) {
                break; // Breaks out of the closet, returning to home() which originally called closet()
            } else if (userInput.equals("2")) {
                inspectOwnedClothes();
            } else if (userInput.equals("3")) {
                sellClothesMenu();
            } else if (userInput.equals("4")) {
                filterOwnedClothes();
            } else {
                removeFilters();
            }
        }
    }

    // EFFECTS: Display the text that the user sees at the closet
    public void displayClosetText() {
        System.out.println("What would you like to do?");
        System.out.println("[1]: Exit your closet");
        System.out.println("[2]: Inspect a piece of clothing");
        System.out.println("[3]: Sell a piece of clothing");
        System.out.println("[4]: Filter items");
        System.out.println("[5]: Remove filters");
    }

    // EFFECTS: allows the user to inspect the clothes they own
    public void inspectOwnedClothes() {
        String userInput;
        displayOwnedClothes(false);

        System.out.println("Which clothing item would you like to inspect?");
        System.out.println("Or, type 'exit' to go back to the closet");

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("exit")) {
                break; // Exit the loop and return to the loop in closet()
            }

            // Given that the user didn't input "exit", we can assume that the input is a string of numbers
            int inputToInt = (Integer.parseInt(userInput) - 1);

            // Check for validity of the input, given that it is a string of numbers
            if (inputToInt >= clothingGame.returnClosetClothesList().getSize() || inputToInt < 0) {
                System.out.println("This input is not valid, please try again");
            } else { // If the input is valid, we can then inspect the piece of clothing corresponding to the input
                inspectClosetClothing(inputToInt);
            }
        }
    }

    // EFFECTS: allows the user to inspect the clothes in the shop
    public void inspectShopClothes() {
        String userInput;
        displayShopClothes();

        System.out.println("Which clothing item would you like to inspect?");
        System.out.println("Or, type 'exit' to go back to the shop");

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("exit")) {
                break; // Break out of the loop and return to the loop in shopping()
            }

            // Given that the user didn't input "exit", we can assume that the input is a string of numbers
            int inputToInt = (Integer.parseInt(userInput) - 1);

            System.out.println(clothingGame.returnShopClothesList().getSize());
            // Check for validity of the input, given that it is a string of numbers
            if (inputToInt >= clothingGame.returnShopClothesList().getSize() || inputToInt < 0) {
                System.out.println("This input is not valid, please try again");
            } else { // If the input is valid, we can then inspect the piece of clothing corresponding to the input
                inspectShopClothing(inputToInt);
            }
        }
    }

    // REQUIRES: the user owns at least one piece of clothing
    // EFFECTS: prints out information relevant to a piece of clothing that the user owns
    public void inspectClosetClothing(int index) {
        Clothing currentClothing = clothingGame.returnClosetClothesList().getAtIndex(index);
        System.out.println("Name:" + currentClothing.getName());
        System.out.println("Color:" + currentClothing.getColor());
        System.out.println("Type:" + currentClothing.getClothingType());
    }

    // REQUIRES: the shop contains at least one piece of clothing
    // EFFECTS: prints out information relevant to a customer of a clothing store
    public void inspectShopClothing(int index) {
        Clothing currentClothing = clothingGame.returnShopClothesList().getAtIndex(index);
        System.out.println("Name:" + currentClothing.getName());
        System.out.println("Color:" + currentClothing.getColor());
        System.out.println("Type:" + currentClothing.getClothingType());
    }

    // MODIFIES: this
    // EFFECTS: acts as the shopping screen of the game
    //          keeps running as long as the user is taking actions regarding the shop
    public void shopping() {
        // As many actions as the user would like can be committed, until they choose to exit the shop
        while (true) {
            System.out.println("Welcome to the shop! Here you can buy any clothes you like!");
            displayShopClothes();

            System.out.println("What would you like to do?");
            System.out.println("[1]: Exit the shop");
            System.out.println("[2]: Inspect a piece of clothing");
            System.out.println("[3]: Buy a piece of clothing");

            String userInput = scanner.nextLine();

            // Check for validity of the input
            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")) {
                System.out.println("It seems you have not provided a valid input, please try again!");
                userInput = scanner.nextLine();
            }

            if (userInput.equals("1")) {
                break; // Breaks out of the shop, returning to home() which originally called shopping()
            } else if (userInput.equals("2")) {
                inspectShopClothes();
            } else {
                buyClothesMenu();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: acts as the mirror screen of the game
    //          keeps running as long as the user is taking actions regarding the mirror
    public void mirror() {
        // As many actions as the user would like can be committed, until they choose to exit the mirror
        while (true) {
            displayOwnedOutfits();

            displayMirrorText();

            String userInput = scanner.nextLine();
            // Check for validity of the input
            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")) {
                System.out.println("It seems you have not provided a valid input, please try again!");
                userInput = scanner.nextLine();
            }

            if (userInput.equals("1")) {
                break; // Breaks out of the closet, returning to home() which originally called closet()
            } else if (userInput.equals("2")) {
                addOutfit();
            } else {
                removeOutfit();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: add an outfit using the currently owned clothes
    public void addOutfit() {
        Clothing hat = addHat();
        Clothing top = addTop();
        Clothing pants = addPants();
        Clothing shoes = addShoes();

        clothingGame.returnOutfitList().addOutfit(new Outfit(hat, top, pants, shoes));
    }

    // MODIFIES: this
    // EFFECTS: allows the user to remove certain outfits
    public void removeOutfit() {
        displayOwnedOutfits();

        System.out.println("Which outfit would you like to remove?");
        System.out.println("Or, type 'exit' to go back to the mirror");

        String userInput = scanner.nextLine();

        int inputToInt;

        // While loop runs until the input is valid
        while (!(userInput.equals("exit"))
                && !(checkIfNumeric(userInput)
                && (Integer.parseInt(userInput) - 1) <=  clothingGame.returnOutfitList().getSize())) {
            System.out.println("This input is not valid, please try again");
            userInput = scanner.nextLine();
        }
        if (!userInput.equals("exit")) {
            inputToInt = (Integer.parseInt(userInput) - 1);
            clothingGame.returnOutfitList().removeOutfit(inputToInt);
        }
    }

    // EFFECTS: return the hat that the user specifies
    public Clothing addHat() {
        displayOwnedClothes(true);
        System.out.println("Please select the hat you would like to add:");
        System.out.println("If you don't want any hat, type 'none' ");

        String userInput = scanner.nextLine();

        ClothingList closetClothes = clothingGame.returnClosetClothesList();

        // Check for validity of the input
        while (!(checkIfNumeric(userInput) // Checks if string is in an integer form or equals "none"
                && closetClothes.getSize() >= (Integer.parseInt(userInput) - 1)
                && closetClothes.getAtIndex((Integer.parseInt(userInput) - 1)).getClothingType().equals("hat"))
                && !userInput.equals("none")) {
            System.out.println("It seems you have not provided a valid input, please try again!");
            userInput = scanner.nextLine();
        }
        if (userInput.equals("none")) {
            return null;
        } else {
            return clothingGame.returnClosetClothesList().getAtIndex((Integer.parseInt(userInput) - 1));
        }
    }

    // EFFECTS: return the top that the user specifies
    public Clothing addTop() {
        displayOwnedClothes(true);
        System.out.println("Please select the top you would like to add:");
        System.out.println("If you don't want any top, type 'none' ");

        String userInput = scanner.nextLine();

        ClothingList closetClothes = clothingGame.returnClosetClothesList();

        // Check for validity of the input
        while (!(checkIfNumeric(userInput) // Checks if string is in an integer form or equals "none"
                && closetClothes.getSize() >= (Integer.parseInt(userInput) - 1)
                && closetClothes.getAtIndex((Integer.parseInt(userInput) - 1)).getClothingType().equals("top"))
                && !userInput.equals("none")) {
            System.out.println("It seems you have not provided a valid input, please try again!");
            userInput = scanner.nextLine();
        }
        if (userInput.equals("none")) {
            return null;
        } else {
            return clothingGame.returnClosetClothesList().getAtIndex((Integer.parseInt(userInput) - 1));
        }
    }

    // EFFECTS: return the pants that the user specifies
    public Clothing addPants() {
        displayOwnedClothes(true);
        System.out.println("Please select the pants you would like to add:");
        System.out.println("If you don't want any pants, type 'none' ");

        String userInput = scanner.nextLine();

        ClothingList closetClothes = clothingGame.returnClosetClothesList();

        // Check for validity of the input
        while (!(checkIfNumeric(userInput) // Checks if string is in an integer form or equals "none"
                && closetClothes.getSize() >= (Integer.parseInt(userInput) - 1)
                && closetClothes.getAtIndex((Integer.parseInt(userInput) - 1)).getClothingType().equals("pants"))
                && !userInput.equals("none")) {
            System.out.println("It seems you have not provided a valid input, please try again!");
            userInput = scanner.nextLine();
        }
        if (userInput.equals("none")) {
            return null;
        } else {
            return clothingGame.returnClosetClothesList().getAtIndex((Integer.parseInt(userInput) - 1));
        }
    }

    // EFFECTS: return the shoes that the user specifies
    public Clothing addShoes() {
        displayOwnedClothes(true);
        System.out.println("Please select the shoe you would like to add:");
        System.out.println("If you don't want any shoes, type 'none' ");

        String userInput = scanner.nextLine();

        ClothingList closetClothes = clothingGame.returnClosetClothesList();

        // Check for validity of the input
        while (!(checkIfNumeric(userInput) // Checks if string is in an integer form or equals "none"
                && closetClothes.getSize() >= (Integer.parseInt(userInput) - 1)
                && closetClothes.getAtIndex((Integer.parseInt(userInput) - 1)).getClothingType().equals("shoes"))
                && !userInput.equals("none")) {
            System.out.println("It seems you have not provided a valid input, please try again!");
            userInput = scanner.nextLine();
        }
        if (userInput.equals("none")) {
            return null;
        } else {
            return clothingGame.returnClosetClothesList().getAtIndex((Integer.parseInt(userInput) - 1));
        }
    }

    // EFFECTS: Display the text that the user sees at the mirror
    public void displayMirrorText() {
        System.out.println("What would you like to do?");
        System.out.println("[1]: Leave the mirror");
        System.out.println("[2]: Add a new outfit");
        System.out.println("[3]: Remove an outfit");
    }

    // MODIFIES: this
    // EFFECTS: acts as an interface for the user to purchase clothes from the shop
    //          returns to the shop when the user is ready to leave the buy menu
    public void buyClothesMenu() {
        String userInput;
        displayShopClothes();

        System.out.println("Which clothing item would you like to buy?");
        System.out.println("Or, type 'exit' to go back to the shop");

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("exit")) {
                break; // Exit the buy menu and return to shopping()
            }

            // Given that the user didn't input "exit", we can assume that the input is a string of numbers
            int inputToInt = (Integer.parseInt(userInput) - 1);

            // Check for validity of the input, given that it is a string of numbers
            if (inputToInt >= clothingGame.returnShopClothesList().getSize() || inputToInt < 0) {
                System.out.println("This input is not valid, please try again");
            } else { // If the input is valid, we can buy from the shop the piece of clothing corresponding to the input
                buyClothing(inputToInt);
                displayShopClothes();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Allows the user to sell their clothes
    public void sellClothesMenu() {
        String userInput;
        displayOwnedClothes(true);

        System.out.println("Which clothing item would you like to sell?");
        System.out.println("Or, type 'exit' to go back to the closet");

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("exit")) {
                break; // Exit the buy menu and return to shopping()
            }

            // Given that the user didn't input "exit", we can assume that the input is a string of numbers
            int inputToInt = (Integer.parseInt(userInput) - 1);

            // Check for validity of the input, given that it is a string of numbers
            if (inputToInt >= clothingGame.returnClosetClothesList().getSize() || inputToInt < 0) {
                System.out.println("This input is not valid, please try again");
            } else { // If the input is valid, we can buy from the shop the piece of clothing corresponding to the input
                sellClothing(inputToInt);
                displayOwnedClothes(true);
            }
        }
    }

    // REQUIRES: index is within 0 and the amount of clothes in the shop minus one
    // MODIFIES: this
    // EFFECTS: purchases a piece of clothing from the shop using the given index
    public void buyClothing(int index) {
        clothingGame.buyClothingAtIndex(index);
    }

    // REQUIRES: index is within 0 and the amount of clothes in the closet minus one
    // MODIFIES: this
    // EFFECTS: sells a piece of clothing from the closet using the given index
    public void sellClothing(int index) {
        clothingGame.sellClothingAtIndex(index);
    }

    // MODIFIES: this
    // EFFECTS: allows the user to place a filter on the clothes they own,
    //          then displays their clothes with the filter applied
    public void filterOwnedClothes() {
        System.out.println("What type of clothes would you like to filter?");
        String userInput = scanner.nextLine();

        // Check for validity of the input
        while (!clothingGame.validClothingType(userInput)) {
            System.out.println("This is not a valid clothing type, please try again");
            userInput = scanner.nextLine();
        }
        clothingGame.setClosetFilter(userInput);
    }

    // MODIFIES: this
    // EFFECTS: clears and filters applied onto the user's owned clothes
    public void removeFilters() {
        clothingGame.setClosetFilter("none");
        displayOwnedClothes(false);
    }

    // EFFECTS: displays the clothes the user owns
    //          if there is a filter currently applied, print the clothes that match the
    //              specifications from the filter, but only if ignoreFilter is false
    public void displayOwnedClothes(boolean ignoreFilter) {
        System.out.println("These are the clothes you own:");

        ClothingList ownedClothes = clothingGame.returnClosetClothesList();

        String filter = clothingGame.returnClosetFilter();

        if (filter.equals("none") || ignoreFilter) {
            for (int i = 0; i < ownedClothes.getSize(); i++) {
                System.out.println("[" + (i + 1) + "]: " + ownedClothes.getAtIndex(i).getName());
            }
        } else { // If there are filters applied, print out only clothes corresponding to such filter
            for (int i = 0; i < ownedClothes.getSize(); i++) {
                if (ownedClothes.getAtIndex(i).getClothingType().equals(filter)) {
                    System.out.println("[" + (i + 1) + "]: " + ownedClothes.getAtIndex(i).getName());
                }
            }
        }
    }

    // EFFECTS: displays the outfits the user owns
    public void displayOwnedOutfits() {
        System.out.println("These are the outfits you own:");

        OutfitList ownedOutfits = clothingGame.returnOutfitList();

        for (int i = 0; i < ownedOutfits.getSize(); i++) {
            displayOutfitAtIndex(i);
        }
    }

    // EFFECTS: displays a single outfit the user owns at the index given
    public void displayOutfitAtIndex(int index) {
        System.out.println("Outfit[" + (index + 1) + "]: ");

        Outfit outfitToDisplay = clothingGame.returnOutfitList().getAtIndex(index);
        if (outfitToDisplay.getHat() != null && outfitToDisplay.getHat().isBought()) {
            System.out.println("Hat: " + outfitToDisplay.getHat().getName());
        } else {
            System.out.println("Hat: none");
        }
        if (outfitToDisplay.getTop() != null && outfitToDisplay.getTop().isBought()) {
            System.out.println("Top: " + outfitToDisplay.getTop().getName());
        } else {
            System.out.println("Top: none");
        }
        if (outfitToDisplay.getPants() != null && outfitToDisplay.getPants().isBought()) {
            System.out.println("Pants: " + outfitToDisplay.getPants().getName());
        } else {
            System.out.println("Pants: none");
        }
        if (outfitToDisplay.getShoes() != null && outfitToDisplay.getShoes().isBought()) {
            System.out.println("Shoes: " + outfitToDisplay.getShoes().getName());
        } else {
            System.out.println("Shoes: none");
        }
    }

    // EFFECTS: displays the clothes available in the shop
    public void displayShopClothes() {
        System.out.println("These are the clothes in the shop:");

        ClothingList shopClothes = clothingGame.returnShopClothesList();
        for (int i = 0; i < shopClothes.getSize(); i++) {
            System.out.println("[" + (i + 1) + "]: " + shopClothes.getAtIndex(i).getName());
        }
    }

    // EFFECTS: returns true if the string can be converted to a positive integer value
    public boolean checkIfNumeric(String str) {
        boolean curChar = false;
        for (int i = 0; i < 9; i++) {
            if (i == str.charAt(0) - '0') { // get the first character of the string and cast it as an int by - '0'
                curChar = true;
            }
        }
        if (str.length() > 1) {
            return curChar && checkIfNumeric(str.substring(1));
        } else {
            return curChar;
        }
    }
}
