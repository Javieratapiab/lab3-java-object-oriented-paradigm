package project;

import java.util.ArrayList;
import java.util.List;

public class Stack {
  private User loggedUser;
  private List<User> users;
  private List<Question> questions;
  private List<Label> labels;

  /**
   * TODO: DOCUMENTAR CONSTRUCTOR
   * Constructor de Stack
   */
  public Stack() {
    this.loggedUser = null;
    this.users = new ArrayList<>();
    this.questions = new ArrayList<>();
    this.labels = new ArrayList<>();
  }

  /**
   * TODO: DOCUMENTAR CONSTRUCTOR
   * Constructor de Stack (sobrecarga)
   * @param users
   * @param questions
   * @param labels
   */
  public Stack(List<User> users,
               List<Question> questions,
               List<Label> labels) {
    this.loggedUser = null;
    this.users = users;
    this.questions = questions;
    this.labels = labels;
  }

  /*
  * Selectores
  * */
  public User getLoggedUser() {
    return this.loggedUser;
  }

  public List<Label> getLabels() {
    return this.labels;
  }

  public List<Question> getQuestions() {
    return this.questions;
  }

  private void addQuestion(Question question) {
    this.questions = new ArrayList<>(this.questions);
    this.questions.add(question);
  }

  public void addUser(String name, String password) {
    this.users = new ArrayList<>(this.users);
    this.users.add(new User (name, password));
  }

  private void setLoggedUser(String name, String password) {
    this.loggedUser = new User(name, password);
  }

  private void setLoggedUser() {
    this.loggedUser = null;
  }

  /**
   * TODO: DOCUMENTAR MÉTODO
   * @param name
   * @return
   */
  private boolean validateUser(String name) {
    for(int i = 0; i < this.users.size(); i++) {
      boolean equalName = this.users.get(i).getName().equals(name);
      if (equalName) {
        return true;
      }
    }
    return false;
  }

  /**
   * TODO: DOCUMENTAR MÉTODO
   * @param name
   * @param password
   * @return
   */
  private boolean validateUser(String name, String password) {
    for(int i = 0; i < this.users.size(); i++) {
      boolean equalName = this.users.get(i).getName().equals(name);
      boolean equalPassword = this.users.get(i).getPassword().equals(password);
      if (equalName && equalPassword) {
        return true;
      }
    }
    return false;
  }

  /**
   * Método de instancia privado que valida usuario logueado en el stack
   * @param name Nombre de usuario a validar
   * @param password Password de usuario a validar
   * @return boolean, true si el usuario fue registrado previamente,
   * false si usuario no se encuentra asociado al stack.
   */
  private boolean validateLoggedUser(String name, String password) {
    if (this.loggedUser == null) return false;
    boolean equalName = this.loggedUser.getName().equals(name);
    boolean equalPassword = this.loggedUser.getPassword().equals(password);
    return (equalName && equalPassword);
  }

  /**
   * Método de instancia público que registra un usuario y lo agrega
   * a la lista de usuarios registrados en el stack
   * @param name Nombre del usuario a registrar
   * @param password Password del usuario a registrar
   * @return boolean (operación exitosa (true) o fracaso (false))
   */
  public boolean register(String name, String password) {
    if (validateUser(name)) return false;
    addUser(name, password);
    return true;
  }

  /**
   * Método de instancia público que loguea un usuario y lo setea en el stack
   * retornando operación de éxito o fracaso.
   * @param name Nombre de usuario que se intenta loguear
   * @param password Password de usuario que se intenta loguear
   * @return boolean (operación exitosa (true) o fracaso (false))
   */
  public boolean login(String name, String password) {
    if (!validateUser(name, password)) return false;
    setLoggedUser(name, password);
    return true;
  }

  /**
   * Método de instancia público que desloguea un usuario del stack y lo elimina del stack
   * retornando operación de éxito o fracaso.
   * @param name Nombre de usuario que se intenta desloguear
   * @param password Password de usuario que se intenta desloguear
   * @return boolean (operación exitosa (true) o fracaso (false))
   */
  public boolean logout(String name, String password) {
    if (!validateLoggedUser(name, password)) return false;
    setLoggedUser();
    return true;
  }

  /**
   * Método de instancia público que permite crear una pregunta y agregarla al stack
   * @param title Título de una pregunta
   * @param content Contenido de una pregunta
   * @param labels Etiquetas asociadas a una pregunta
   * @return boolean (operación exitosa (true) o fracaso (false))
   */
  public boolean ask(String title, String content, List<Label> labels) {
    if (this.loggedUser == null) return false;
    Question question = new Question(this.loggedUser.getName(), title, content, labels);
    addQuestion(question);
    return true;
  }


  public boolean answer(Question question, String content) {
    if (this.loggedUser == null) return false;
    Answer answer = new Answer(this.loggedUser.getName(), content);
    question.addAnswer(answer);
    return true;
  }

  @Override
  public String toString() {
    String loggedAsString = null;
    String usersAsString = "";
    String questionsAsString = "";
    String labelsAsString = "";

    // Nombres de usuarios registrados
    for(int i = 0; i < users.size(); i++) {
      usersAsString = usersAsString + users.get(i).getName();
      if (i != users.size() - 1) {
        usersAsString = usersAsString + ", ";
      }
    }

    // Títulos de preguntas
    for(int i = 0; i < questions.size(); i++) {
      questionsAsString = questionsAsString + questions.get(i).getTitle();
      if (i != questions.size() - 1) {
        questionsAsString = questionsAsString + ", ";
      }
    }

    // Nombres de etiquetas
    for(int i = 0; i < labels.size(); i++) {
      labelsAsString = labelsAsString + labels.get(i).getName();
      if (i != labels.size() - 1) {
        labelsAsString = labelsAsString + ", ";
      }
    }

    // Usuario logueado
    if (loggedUser != null) {
      loggedAsString = loggedUser.getName();
    }

    return "Stack{" +
            "usuario logueado=" + loggedAsString +
            ", usuarios=" + usersAsString +
            ", preguntas=" + questionsAsString +
            ", etiquetas=" + labelsAsString +
            "}\n";
  }
}
