package com.company;

public class Catalogue {
  private Item[] list;

  public Catalogue(int size) {
    list = new Item[size];
  }

  int addItemCount = 0;

  public void addItem(Item item) {
    list[addItemCount] = item;
    addItemCount++;
  }

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

  public void returnItem(Item description) {
    Item returnItem = null;
    for (int i = 0; i < list.length; i++) {
      if (description.equals(list[i])) {
        returnItem = list[i];
        break;
      }
    }
    if (returnItem != null) {
      returnItem.setAvailable(true);
      System.out.println("Tak for returneringen af: " + description.getDescription());
      System.out.println(" ");
    } else {
      System.out.println("Beskrivelsen matchede ikke en genstand, der har været udlånt.");
      System.out.println(" ");
    }
  }

  public Item[] getFullList() {
    return list;
  }

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