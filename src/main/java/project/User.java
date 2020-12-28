package project;

/**
 * Descripción de la clase, atributos, métodos públicos y relaciones
 */
public class User {
  private String _name;
  private String _password;
  private int _reputation;

  /**
   * @param name
   * @param password
   */
  public User(String name, String password) {
    _name = name;
    _password = password;
    _reputation = 0;
  }
}
