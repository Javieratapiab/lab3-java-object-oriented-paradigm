package project;

public class Reward {
  private final int quantity;
  private final User user;

  public Reward(int quantity, User user) {
    this.quantity = quantity;
    this.user = user;
  }

  public int getQuantity() {
    return quantity;
  }

  public User getUser() {
    return user;
  }

  @Override
  public String toString() {
    return "Reward{" +
            "quantity=" + quantity +
            ", user=" + user +
            '}';
  }
}
