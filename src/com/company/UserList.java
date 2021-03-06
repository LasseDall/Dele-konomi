package com.company;

public class UserList {

  //Attribut
  private User[] list;

  //Konstruktørmetode
  public UserList() {
    list = new User[1000];
  }

  //Tilføj bruger til liste
  public void addUser(User user) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == null) {
        list[i] = user;
        break;
      }
    }
  }

  //Find bruger ud fra brugernavn
  public User findUser(String userName) {
    User user = null;
    for (int i = 0; i < list.length; i++) {
      if (list[i] != null) {
        if (userName.equals(list[i].getUserName())) {
          user = list[i];
        }
      }
    }
    return user;
  }
}