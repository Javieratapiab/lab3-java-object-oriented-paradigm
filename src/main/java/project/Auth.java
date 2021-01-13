package project;

public interface Auth {
  boolean login(String name, String password);
  boolean register(String name, String password);
  boolean logout();
}
