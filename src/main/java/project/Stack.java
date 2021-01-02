package project;

import java.util.ArrayList;
import java.util.List;

public class Stack {
  private User loggedUser;
  private List<User> users;
  private List<Question> questions;
  private List<Label> labels;

  public Stack() {
    this.loggedUser = null;
    this.users = new ArrayList<User>();
    this.questions = new ArrayList<Question>();
    this.labels = new ArrayList<Label>();
  }

  public Stack(List<User> users,
               List<Question> questions,
               List<Label> labels) {
    this.loggedUser = null;
    this.users = users;
    this.questions = questions;
    this.labels = labels;
  }

  public void addUser(String name, String password) {
    User user = new User (name, password);
    this.users = new ArrayList<>(this.users);
    this.users.add(user);
  }

  public void setLoggedUser(String name, String password) {
    User user = new User (name, password);
    this.loggedUser = user;
  }

  public void setLoggedUser() {
    this.loggedUser = null;
  }

  /**
   * @return
   */
  public List<User> getUsers() {
    return this.users;
  }

  public User getLoggedUser() {
    return this.loggedUser;
  }

  public boolean validateUser(String name) {
    for(int i = 0; i < this.users.size() -1; i++) {
      boolean equalName = this.users.get(i).getName().equals(name);
      if (equalName) {
        return true;
      }
    }
    return false;
  }

  public boolean validateUser(String name, String password) {
    for(int i = 0; i < this.users.size() -1; i++) {
      boolean equalName = this.users.get(i).getName().equals(name);
      boolean equalPassword = this.users.get(i).getPassword().equals(password);
      if (equalName && equalPassword) {
        return true;
      }
    }
    return false;
  }

  public boolean validateUser(String name, String password, boolean logged) {
    if (this.loggedUser == null) return false;
    boolean equalName = this.loggedUser.getName().equals(name);
    boolean equalPassword = this.loggedUser.getPassword().equals(password);
    return (equalName && equalPassword);
  }

  /**
   * @param name
   * @param password
   */
  public boolean register(String name, String password) {
    if (validateUser(name)) return false;
    addUser(name, password);
    return true;
  }

  public boolean login(String name, String password) {
    if (!validateUser(name, password)) return false;
    setLoggedUser(name, password);
    return true;
  }

  public boolean logout(String name, String password) {
    if (!validateUser(name, password, true)) return false;
    setLoggedUser();
    return true;
  }
}
