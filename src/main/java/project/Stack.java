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

  public void addUser(User user) {
    this.users = new ArrayList<>(this.users);
    this.users.add(user);
  }

  public void addLoggedUser(User user) {
    this.loggedUser = user;
  }

  /**
   * @return
   */
  public List<User> getUsers() {
    return this.users;
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

  /**
   * @param name
   * @param password
   */
  public boolean register(String name, String password) {
    User newUser = new User(name, password);
    if (validateUser(name)) return false;
    addUser(newUser);
    return true;
  }

  public boolean login(String name, String password) {
    User newUser = new User(name, password);
    if (!validateUser(name, password)) return false;
    addLoggedUser(newUser);
    return true;
  }
}
