package project;

public class User {
  private String name;
  private String password;
  private int reputation;
  private int debtReputation;

  public User(String name, String password) {
    this.name = name;
    this.password = password;
    this.reputation = 30;
    this.debtReputation = 0;
  }

  public String getName() {
    return this.name;
  }

  public String getPassword() {
    return this.password;
  }

  public boolean validateReputation(int rewardQuantity) {
    return (reputation + debtReputation > rewardQuantity);
  }

  public void addDebtReputation(int rewardQuantity) {
     debtReputation -= rewardQuantity;
  }

  @Override
  public String toString() {
    return "User{" +
            "nombre='" + name + '\'' +
            ", contrasena='" + password + '\'' +
            ", reputación=" + reputation +
            ", reputación descontada (temp)=" + debtReputation +
            '}';
  }
}
