package project;

class User {
  private final String name;
  private final String password;
  private int reputation;
  private int tempReputation;

  User(String name, String password) {
    this.name = name;
    this.password = password;
    this.reputation = 30; // Se entrega por defecto esta cantidad para operar sobre ella
    this.tempReputation = 0;
  }

  public String getName() {
    return this.name;
  }

  public String getPassword() {
    return this.password;
  }

  public boolean validateReputation(int rewardQuantity) {
    return (reputation + tempReputation >= rewardQuantity);
  }

  public void addOrSubstractReputation(int newReputation) {
    reputation += newReputation;
  }

  public void addDebtReputation(int rewardQuantity) {
     tempReputation -= rewardQuantity;
  }

  public void discountReputation(int rewardQuantity) {
    reputation -= rewardQuantity;
    tempReputation += rewardQuantity;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", reputation=" + reputation +
            ", tempReputation=" + tempReputation +
            '}';
  }
}
