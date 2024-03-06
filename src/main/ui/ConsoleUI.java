package ui;

import model.Clothing;
import model.ClothingGame;
import model.ClothingList;

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
            System.out.println("You are currently in your room, you have four options:");
            System.out.println("[1]: Go see your closet");
            System.out.println("[2]: Go look at your mirror");
            System.out.println("[3]: Go clothes shopping");
            System.out.println("[4]: Exit the simulator");

            String userInput = scanner.nextLine();

            // Check for validity of the input
            while (!userInput.equals("1") && !userInput.equals("2")
                    && !userInput.equals("3") && !userInput.equals("4")) {
                System.out.println("It seems you have not provided a valid input, please try again!");
                userInput = scanner.nextLine();
            }

            if (userInput.equals("1")) {
                closet(); // Enter the closer
            } else if (userInput.equals("2")) {
//            mirror(); // Go to the mirror
            } else if (userInput.equals("3")) {
                shopping(); // Enter the shop
            } else {
                break; // Exit the loop, ending the game
            }
        }
    }

    // EFFECTS: acts as the closet screen; keeps running while the user is taking actions within the closet screen
    public void closet() {
        // As many actions as the user would like can be committed, until they choose to exit the closet
        while (true) {
            displayOwnedClothes();

            System.out.println("What would you like to do?");
            System.out.println("[1]: Exit your closet");
            System.out.println("[2]: Inspect a piece of clothing");
            System.out.println("[3]: Filter items");
            System.out.println("[4]: Remove filters");

            String userInput = scanner.nextLine();

            // Check for validity of the input
            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")
                    && !userInput.equals("4")) {
                System.out.println("It seems you have not provided a valid input, please try again!");
                userInput = scanner.nextLine();
            }

            if (userInput.equals("1")) {
                break; // Breaks out of the closet, returning to home() which originally called closet()
            } else if (userInput.equals("2")) {
                inspectOwnedClothes();
            } else if (userInput.equals("3")) {
                filterOwnedClothes();
            } else {
                removeFilters();
            }
        }
    }

    // EFFECTS: allows the user to inspect the clothes they own
    public void inspectOwnedClothes() {
        String userInput;
        displayOwnedClothes();

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
        System.out.println("Resell price:" + currentClothing.getSellPrice());
    }

    // REQUIRES: the shop contains at least one piece of clothing
    // EFFECTS: prints out information relevant to a customer of a clothing store
    public void inspectShopClothing(int index) {
        Clothing currentClothing = clothingGame.returnShopClothesList().getAtIndex(index);
        System.out.println("Name:" + currentClothing.getName());
        System.out.println("Color:" + currentClothing.getColor());
        System.out.println("Type:" + currentClothing.getClothingType());
        System.out.println("Cost:" + currentClothing.getBuyPrice());
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

    // REQUIRES: index is within 0 and the amount of clothes in the shop minus one
    // MODIFIES: this
    // EFFECTS: purchases a piece of clothing from the shop using the given index
    public void buyClothing(int index) {
        clothingGame.buyClothingAtIndex(index);
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
        displayOwnedClothes();
    }

    // EFFECTS: displays the clothes the user owns
    //          if there is a filter currently applied, print the clothes that match the
    //              specifications from the filter
    public void displayOwnedClothes() {
        System.out.println("These are the clothes you own:");

        ClothingList ownedClothes = clothingGame.returnClosetClothesList();

        String filter = clothingGame.returnClosetFilter();

        if (filter.equals("none")) {
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

    // EFFECTS: displays the clothes available in the shop
    public void displayShopClothes() {
        System.out.println("These are the clothes in the shop:");

        ClothingList shopClothes = clothingGame.returnShopClothesList();
        for (int i = 0; i < shopClothes.getSize(); i++) {
            System.out.println("[" + (i + 1) + "]: " + shopClothes.getAtIndex(i).getName());
        }
    }
}
