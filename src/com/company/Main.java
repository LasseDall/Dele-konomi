package com.company;

import java.util.Scanner;

public class Main {

    Catalogue catalogue = new Catalogue(1000);

    UserList list = new UserList();

    Scanner scanner = new Scanner(System.in);

    User loggedInUser = null;

    User user1 = new User("Lasse Dall Mikkelsen", "Lasse", "1234");
    User user2 = new User("Jens Jensen", "Jens", "Jensen123");
    User user3 = new User("Mathilde Pedersen", "Mathilde", "entotre");
    User user4 = new User("Gertrud Gertrudsen", "Gertrud", "gertrud");
    User user5 = new User("Jens Larsen", "Jens2", "Lars3");

    public void startMenu() {
        System.out.println("1. Log ind med eksisterende bruger");
        System.out.println("2. Opret bruger");
        System.out.println("3. Exit");
        valgStartMenu();
    }

    public void valgStartMenu() {
        int choice = scanner.nextInt();
        if (choice == 1) {
            logOn();
        } else if (choice == 2) {
            newUser();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            System.out.println("Du foretog et ugyldigt valg.");
            startMenu();
        }
    }

    public void logOn() {
        System.out.println("Indtast brugernavn:");
        scanner.nextLine();
        String userName = scanner.nextLine();
        User user = list.findUser(userName);
        if (user != null) {
            System.out.println("Indtast adgangskode:");
            String passWord = scanner.nextLine();
            if (passWord.equals(user.getPassWord())) {
                System.out.println("Hej " + user.getFullName());
                loggedInUser = user;
                hovedMenu();
            } else {
                System.out.println("Forkert adgangskode");
                logOn();
            }
        } else {
            System.out.println("Ugyldigt brugernavn");
            logOn();
        }
    }

    public void newUser() {
        boolean userNameRegistered = false;
        System.out.println("Indtast fuldt navn:");
        String fullName = scanner.nextLine();
        fullName = scanner.nextLine();
        User user = null;
        do {
            System.out.println("Indtast ønskede brugernavn:");
            String userName = scanner.nextLine();
            if (list.findUser(userName) == null) {
                System.out.println("Indtast adganskode:");
                String passWord = scanner.nextLine();
                user = new User(fullName, userName, passWord);
                list.addUser(user);
                System.out.println("Velkommen " + fullName);
                userNameRegistered = true;
            } else {
                System.out.println("Det ønskede brugernavn er allerede i brug.");
            }
        } while (!userNameRegistered);
        loggedInUser = user;
        hovedMenu();
    }

    public void hovedMenu() {
        System.out.println("Hvad kunne du tænke dig?");
        System.out.println("1. Se hele kataloget");
        System.out.println("2. Se hvad der er ledigt");
        System.out.println("3. Opret ting til udlån");
        System.out.println("4. Lån ting");
        System.out.println("5. Aflever ting");
        System.out.println("6. Se dine ting");
        System.out.println("7. Fjern udlån");
        System.out.println("8. Log ud");
        int choice = scanner.nextInt();
        if (choice == 1) {
            displayFullList();
        } else if (choice == 2) {
            displayAvailableList();
        } else if (choice == 3) {
            addItemToList();
        } else if (choice == 4) {
            borrowItem();
        } else if (choice == 5) {
            returnItem();
        } else if (choice == 6) {
            displayYourItems();
        } else if (choice == 7) {
            removeItem();
        } else if (choice == 8) {
            logOut();
        } else {
        System.out.println("Du foretog et ugyldigt valg.");
        hovedMenu();
        }
    }

    public void displayFullList(){
        Item[] allItems = catalogue.getFullList();
        for (int i = 0; i < allItems.length; i++) {
            if (allItems[i] != null) {
                System.out.println(allItems[i]);
            }
        }
        hovedMenu();
    }

    public void displayAvailableList() {
        Item[] availableItems = catalogue.getAvailableItems();
        for (int i = 0; i < availableItems.length; i++) {
            if (availableItems[i] != null) {
                System.out.println(availableItems[i]);
            }
        }
        hovedMenu();
    }

    public void addItemToList() {
        System.out.println("Hviken katagori tilhører objektet du ønsker at udlåne?");
        String catagory = scanner.nextLine();
        catagory = scanner.nextLine();
        System.out.println("Tilføj beskrivelse til objektet: ");
        String description = scanner.nextLine();
        catalogue.addItem(new Item(catagory, description, loggedInUser, null));
        System.out.println("Tak fordi du vil udlåne din " + description + ".");
        System.out.println("Den er nu gjort tilgænglig for udlån.");
        System.out.println(" ");
        hovedMenu();
    }

    public void borrowItem() {
        System.out.println("Hvad ønsker du at låne?");
        String seek = scanner.nextLine();
        seek = scanner.nextLine();
        Item found = catalogue.findItem(seek);
        if (found != null) {
            if (found.getAvailable()) {
                catalogue.borrowItem(found);
                found.setBorrowUser(loggedInUser);
            } else {
                System.out.println("Denne genstand er ikke tilgænglig for udlån i øjeblikket.");
                System.out.println(" ");
            }
        }
        hovedMenu();
    }

    public void returnItem() {
        System.out.println("Hvad vil du returnere?");
        String returning = scanner.nextLine();
        returning = scanner.nextLine();
        Item found = catalogue.findItem(returning);
        catalogue.returnItem(found);
        if (found.getOwnerWantItemBack()) {
            found.setBorrowUser(found.getOwner());
            found.setAvailable(false);
        } else {
            found.setBorrowUser(null);
        }
        hovedMenu();
    }

    public void displayYourItems() {
        Item[] yourItems = findYourItems();
        for (int i = 0; i < yourItems.length; i++) {
            if (yourItems[i] != null) {
                System.out.println(yourItems[i]);
            }
        }
        hovedMenu();
    }

    public void removeItem() {
        System.out.println("Hvad ønsker du, at gøre utilgængligt?");
        String description = scanner.nextLine();
        description = scanner.nextLine();
        Item foundItem = catalogue.findItem(description);
        if (foundItem != null) {
            if (loggedInUser == foundItem.getOwner()) {
                if (foundItem.getAvailable()) {
                        foundItem.setAvailable(false);
                        foundItem.setBorrowUser(loggedInUser);
                        System.out.println(foundItem.getDescription() + " er nu gjort utilgængelig for udlån.");
                        System.out.println(" ");
                    } else {
                        System.out.println("Denne ting er i øjeblikket udlånt.");
                        System.out.println("Den vil blive gjort utilgænglig for yderligere udlån, når den indleveres.");
                        System.out.println(" ");
                        foundItem.setOwnerWantItemBack(true);
                    }
                } else {
                    System.out.println("Dette er ikke din ting");
                    System.out.println(" ");
                }
            } else {
            System.out.println("Der er ikke nogen ting, der matcher beskrivelsen.");
            System.out.println(" ");
            }
        hovedMenu();
        }

    public void logOut() {
        loggedInUser = null;
        startMenu();
        hovedMenu();
    }

    public Item[] findYourItems() {
        Item[] allItems = catalogue.getFullList();
        Item[] yourItems = new Item[catalogue.getFullList().length];
        int ownerCount = 0;
        for (int i = 0; i < catalogue.getFullList().length; i++) {
            if (allItems[i] != null) {
                if (allItems[i].getOwner() == loggedInUser) {
                    yourItems[ownerCount] = allItems[i];
                    ownerCount++;
                }
            }
        }
        return yourItems;
    }

    public static void main(String[] args) {
	// write your code here
        Main obj = new Main();
        Item item1 = new Item("Sport", "Skateboard", obj.user1, null);
        obj.catalogue.addItem(item1);
        Item item2 = new Item("Sport", "Mountainbike", obj.user2, null);
        obj.catalogue.addItem(item2);
        Item item3 = new Item("Tøj", "Smoking", obj.user1, null);
        obj.catalogue.addItem(item3);
        Item item4 = new Item("Tøj", "Pailletkjole", obj.user3, null);
        obj.catalogue.addItem(item4);
        Item item5 = new Item("Møbel", "Stol", obj.user4, null);
        obj.catalogue.addItem(item5);
        Item item6 = new Item("Møbel", "Bord", obj.user4, null);
        obj.catalogue.addItem(item6);
        Item item7 = new Item("Møbel", "Sofa", obj.user4, null);
        obj.catalogue.addItem(item7);
        Item item8 = new Item("Køkken", "Kniv", obj.user3, null);
        obj.catalogue.addItem(item8);
        Item item9 = new Item("Køkken", "Elpisker", obj.user2, null);
        obj.catalogue.addItem(item9);
        Item item10 = new Item("Køkken", "Ismaskine", obj.user5, null);
        obj.catalogue.addItem(item10);
        obj.list.addUser(obj.user1);
        obj.list.addUser(obj.user2);
        obj.list.addUser(obj.user3);
        obj.list.addUser(obj.user4);
        obj.list.addUser(obj.user5);

        obj.startMenu();

    }
}
