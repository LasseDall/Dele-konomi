package com.company;

public class User {

  private String userName;
  private String passWord;
  private String fullName;

  public User(String fullName, String userName, String passWord) {
    this.fullName = fullName;
    this.userName = userName;
    this.passWord = passWord;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String getFullName() {
    return fullName;
  }

  public String getPassWord() {
    return passWord;
  }

  public String getUserName() {
    return userName;
  }

  public String toString() {
    return "Brugernavn: " + userName + '\n' +
        "Adgangskode: " + passWord + '\n' +
        "Navn: " + fullName;
  }
}
