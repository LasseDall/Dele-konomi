package com.company;

public class Catalogue {

  //Definer attribut
  private Item[] list;

  //Konstruktørmetode
  public Catalogue(int size) {
    list = new Item[size];
  }

  //Tilføj ting til liste
  public void addItem(Item item) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == null) {
        list[i] = item;
        break;
      }
    }
  }

  //Find ting ud fra beskrivelse
  public Item findItem(String description) {
    Item foundItem = null;
    for (int i = 0; i < list.length; i++) {
      if (list[i] != null) {
        if (description.equals(list[i].getDescription())) {
          foundItem = list[i];
          break;
        }
      }
    }
    return foundItem;
  }

  //Lån ting
  public void borrowItem(Item description) {
    Item borrowItem = null;
    for (int i = 0; i < list.length; i++) {
      if (list[i] != null) {
        if (list[i].equals(description)) {
          borrowItem = list[i];
          break;
        }
      }
    }
    if (borrowItem != null) {
      borrowItem.setAvailable(false);
      System.out.println("Du har lånt følgende: " + description.getDescription());
      System.out.println(" ");
    } else {
      System.out.println("Beskrivelsen matchede ikke nogen genstande til udlån.");
      System.out.println(" ");
    }
  }

  //Returner ting
  public void returnItem(Item description) {
    if (description != null) {
      description.setAvailable(true);
      System.out.println("Tak for returneringen af: " + description.getDescription());
      System.out.println(" ");
    } else {
      System.out.println("Beskrivelsen matchede ikke en genstand, der har været udlånt.");
      System.out.println(" ");
    }
  }

  //Getmetode
  public Item[] getFullList() {
    return list;
  }

  //Find tilgængelige ting
  public Item[] getAvailableItems() {
    int count = 0;
    Item[] availableItems = new Item[list.length];
    for (int i = 0; i < list.length; i++) {
      if (list[i] != null) {
        if (list[i].getAvailable() == true) {
          availableItems[count] = list[i];
          count++;
        }
      }
    }
    return availableItems;
  }
}