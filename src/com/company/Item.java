package com.company;

public class Item {

  private String catagory;
  private String description;
  private boolean available;
  private User owner;
  private User borrowUser;

  public Item(String catagory, String description, User owner, boolean available) {
    this.catagory = catagory;
    this.description = description;
    this.available = available;
    this.owner = owner;
  }

  public Item(String catagory, String description, User owner, User borrowUser) {
    this.catagory = catagory;
    this.description = description;
    available = true;
    this.owner = owner;
    this.borrowUser = borrowUser;
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

  public void setBorrowUser(User borrowuser) {
    this.borrowUser = borrowuser;
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
    if (available) {
      availableString = "Denne ting er åben for udlån";
    } else {
      availableString = "Denne ting er udlånt til " + borrowUser.getFullName();
    }
    return "Katagori: " + catagory + '\n' +
        "Beskrivelse: " + description + '\n' +
        "Ejer: " + owner.getFullName() + '\n' +
        availableString + '\n' + '\n';
  }
}
