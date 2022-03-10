package com.company;

public class Item {

  private String catagory;
  private String description;
  private boolean available;
  private User owner;
  private User borrowUser;
  private boolean ownerWantItemBack;

  public Item(String catagory, String description, User owner, User borrowUser) {
    this.catagory = catagory;
    this.description = description;
    available = true;
    this.owner = owner;
    this.borrowUser = borrowUser;
    ownerWantItemBack = false;
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

  public void setOwnerWantItemBack(boolean ownerWantItemBack) {
    this.ownerWantItemBack = ownerWantItemBack;
  }

  public boolean getOwnerWantItemBack() {
    return ownerWantItemBack;
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

  public User getOwner() {
    return owner;
  }

  public String toString() {
    String availableString;
    if (available) {
      availableString = "Denne ting er åben for udlån";
    } else if (borrowUser != owner) {
      availableString = "Denne ting er udlånt til " + borrowUser.getFullName();
    } else {
      availableString = "Denne ting er i øjeblikket utilgængelig";
    }
    return "Katagori: " + catagory + '\n' +
        "Beskrivelse: " + description + '\n' +
        "Ejer: " + owner.getFullName() + '\n' +
        availableString + '\n' + '\n';
  }
}
