package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    Catalogue catalogue = new Catalogue(1000);

    UserList list = new UserList();

    Scanner scanner = new Scanner(System.in);

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
        System.out.println("Indtast fuldt navn:");
        String fullName = scanner.nextLine();
        fullName = scanner.nextLine();
        System.out.println("Indtast ønskede brugernavn:");
        String userName = scanner.nextLine();
        System.out.println("Indtast adganskode:");
        String passWord = scanner.nextLine();
        User user = new User(fullName, userName, passWord);
        list.addUser(user);
        System.out.println("Velkommen " + fullName);
        hovedMenu();
    }

    public void hovedMenu() {
        System.out.println("Hvad kunne du tænke dig?");
        System.out.println("1. Se hele kataloget");
        System.out.println("2. Se hvad der er ledigt");
        System.out.println("3. Opret ting til udlån");
        System.out.println("4. Lån ting");
        System.out.println("5. Aflever ting");
        System.out.println("6. Log ud");
        valgHovedMenu();
    }

    public void valgHovedMenu() {
        int choice = scanner.nextInt();
        if (choice == 1) {
            Item[] allItems = catalogue.getFullList();
            for (int i = 0; i < allItems.length; i++) {
                if (allItems[i] != null) {
                    System.out.println(allItems[i]);
                }
            }
        } else if (choice == 2) {
            Item[] availableItems = catalogue.getAvailableItems();
            for (int i = 0; i < availableItems.length; i++) {
                if (availableItems[i] != null) {
                    System.out.println(availableItems[i]);
                }
            }
        } else if (choice == 3) {
            System.out.println("Hviken katagori tilhører objektet du ønsker at udlåne?");
            String catagory = scanner.nextLine();
            catagory = scanner.nextLine();
            System.out.println("Tilføj beskrivelse til objektet: ");
            String description = scanner.nextLine();
            catalogue.addItem(new Item(catagory, description));
            System.out.println("Tak fordi du vil udlåne din " + description + ".");
            System.out.println("Den er nu gjort tilgænglig for udlån.");
            System.out.println(" ");
        } else if (choice == 4) {
            System.out.println("Hvad ønsker du at låne?");
            String seek = scanner.nextLine();
            seek = scanner.nextLine();
            Item found = catalogue.findItem(seek);
            catalogue.borrowItem(found);
        } else if (choice == 5) {
            System.out.println("Hvad vil du returnere?");
            String returning = scanner.nextLine();
            returning = scanner.nextLine();
            Item found = catalogue.findItem(returning);
            catalogue.returnItem(found);
        } else if (choice == 6) {
            startMenu();
        } else {
            System.out.println("Du foretog et ugyldigt valg.");
        }
        hovedMenu();
    }

    public static void main(String[] args) {
	// write your code here
        Main obj = new Main();
        Item item1 = new Item("Sport", "Skateboard");
        obj.catalogue.addItem(item1);
        Item item2 = new Item("Sport", "Mountainbike");
        obj.catalogue.addItem(item2);
        Item item3 = new Item("Tøj", "Smoking");
        obj.catalogue.addItem(item3);
        Item item4 = new Item("Tøj", "Pailletkjole");
        obj.catalogue.addItem(item4);
        Item item5 = new Item("Møbel", "Stol");
        obj.catalogue.addItem(item5);
        Item item6 = new Item("Møbel", "Bord");
        obj.catalogue.addItem(item6);
        Item item7 = new Item("Møbel", "Sofa");
        obj.catalogue.addItem(item7);
        Item item8 = new Item("Køkken", "Kniv");
        obj.catalogue.addItem(item8);
        Item item9 = new Item("Køkken", "Elpisker");
        obj.catalogue.addItem(item9);
        Item item10 = new Item("Køkken", "Ismaskine");
        obj.catalogue.addItem(item10);

        obj.startMenu();

    }
}
