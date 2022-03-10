package com.company;

public class Item {

  private String catagory;
  private String description;
  private boolean available;

  public Item(String catagory, String description, boolean available) {
    this.catagory = catagory;
    this.description = description;
    this.available = available;
  }

  public Item(String catagory, String description) {
    this.catagory = catagory;
    this.description = description;
    available = true;
  }

  public void setCatagory(String catagory) {
    this.catagory = catagory;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public String getCatagory() {
    return catagory;
  }

  public String getDescription() {
    return description;
  }

  public boolean getAvailable() {
    return available;
  }

  public String toString() {
    String availableString;
    if (available == true) {
      availableString = "Denne ting er åben for udlån";
    } else {
      availableString = "Denne ting er udlånt";
    }
    return "Catagory: " + catagory + '\n' +
        "Description: " + description + '\n' +
        availableString + '\n' + '\n';
  }
}
