package project;

/**
 * Descripción de la clase, atributos, métodos públicos y relaciones
 */
public class User {
  private String name;
  private String password;
  private int reputation;

  /**
   * @param name
   * @param password
   */
  public User(String name, String password) {
    this.name = name;
    this.password = password;
    this.reputation = 0;
  }

  /**
   * @return
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return
   */
  public String getPassword() {
    return this.password;
  }
}
