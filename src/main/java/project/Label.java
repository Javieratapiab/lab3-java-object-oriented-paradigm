package project;

class Label {
  private final String name;
  private final String description;

  Label(String name, String description) {
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
