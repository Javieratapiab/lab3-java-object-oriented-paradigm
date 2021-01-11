package project;

public class Reward {
  private int quantity;
  private User user;

  public Reward(int quantity, User user) {
    this.quantity = quantity;
    this.user = user;
  }

  @Override
  public String toString() {
    return "Reward{" +
            "cantidad recompensa=" + quantity +
            ", usuario=" + user +
            '}';
  }
}
