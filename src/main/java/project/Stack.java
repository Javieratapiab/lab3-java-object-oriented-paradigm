package project;

import java.util.ArrayList;
import java.util.List;

public class Stack {
  private User loggedUser;
  private List<User> users;
  private List<Question> questions;
  private List<Label> labels;

  public Stack() {
    this.loggedUser = null;
    this.users = new ArrayList<User>();
    this.questions = new ArrayList<Question>();
    this.labels = new ArrayList<Label>();
  }

  public Stack(List<User> users,
               List<Question> questions,
               List<Label> labels) {
    this.loggedUser = null;
    this.users = users;
    this.questions = questions;
    this.labels = labels;
  }

  public User getLoggedUser() {
    return loggedUser;
  }

  public List<Label> getLabels() {
    return labels;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  private void addQuestion(Question question) {
    questions = new ArrayList<>(questions);
    questions.add(question);
  }

  public void addUser(String name, String password) {
    users = new ArrayList<>(users);
    users.add(new User (name, password));
  }

  private void setLoggedUser(String name, String password) {
    loggedUser = new User(name, password);
  }

  private boolean validateUser(String name) {
    for(User user : users) {
      boolean equalName = user.getName().equals(name);
      if (equalName) { return true; }
    }
    return false;
  }

  private boolean validateUser(String name, String password) {
    for(User user : users) {
      boolean equalName = user.getName().equals(name);
      boolean equalPassword = user.getPassword().equals(password);
      if (equalName && equalPassword) {
        return true;
      }
    }
    return false;
  }

  public boolean register(String name, String password) {
    if (validateUser(name)) return false;
    addUser(name, password);
    return true;
  }

  public boolean login(String name, String password) {
    if (!validateUser(name, password)) return false;
    setLoggedUser(name, password);
    return true;
  }

  public boolean logout() {
    if (loggedUser == null) return false;
    loggedUser = null;
    return true;
  }

  public boolean ask(String title, String content, List<Label> labels) {
    if (loggedUser == null) return false;
    Question question = new Question(loggedUser.getName(), title, content, labels);
    addQuestion(question);
    return true;
  }

  public boolean answer(Question question, String content) {
    if (loggedUser == null) return false;
    Answer answer = new Answer(loggedUser.getName(), content);
    question.addAnswer(answer);
    return true;
  }

  public boolean reward(Question question, int rewardQuantity) {
    if (loggedUser == null) return false;
    boolean validatedReputation = loggedUser.validateReputation(rewardQuantity);
    if (!validatedReputation) return false;
    loggedUser.addDebtReputation(rewardQuantity);
    Reward reward = new Reward(rewardQuantity, loggedUser);
    question.addReward(reward);
    return true;
  }

  public boolean accept(Question question, Answer answer) {
    if (loggedUser == null) return false;
    return true;
  }

  public List<Question> filterQuestionsByLoggedUser() {
    List <Question> result = new ArrayList<Question>();
    if (loggedUser == null) return result;

    for(Question question : questions) {
      boolean matchAuthor = loggedUser.getName().equals(question.getAuthor());
      if (matchAuthor) {
        result.add(question);
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return "Stack{" +
            "loggedUser=" + loggedUser +
            ", users=" + users +
            ", questions=" + questions +
            ", labels=" + labels +
            '}' + "\n";
  }
}
