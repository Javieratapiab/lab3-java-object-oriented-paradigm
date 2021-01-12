package project;

public interface Authenticator {
  boolean login(String name, String password);
  boolean register(String name, String password);
  boolean logout();
}
