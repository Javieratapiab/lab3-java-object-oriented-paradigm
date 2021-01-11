package project;

public class Label {
  private String name;
  private String description;

  public Label(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  @Override
  public String toString() {
    return "Label{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
