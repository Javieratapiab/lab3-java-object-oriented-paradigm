package project;

import java.util.ArrayList;
import java.util.List;

class Stack implements Auth {
  private User userLogged;
  private List<User> users;
  private List<Question> questions;
  private List<Label> labels;

  public Stack() {
    this.userLogged = null;
    this.users = new ArrayList<>();
    this.questions = new ArrayList<>();
    this.labels = new ArrayList<>();
  }

  public Stack(List<User> users,
               List<Question> questions,
               List<Label> labels) {
    this.userLogged = null;
    this.users = users;
    this.questions = questions;
    this.labels = labels;
  }

  public User getLoggedUser() {
    return userLogged;
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

  public boolean addLabel(Label newLabel) {
    boolean unique = true;
    for (Label label : labels) {
      if (label.getName().equals(newLabel.getName())) {
        unique = false;
        break;
      }
    }
    if (unique) { labels.add(newLabel); }
    return unique;
  }

  private void setLoggedUser(User user) {
    userLogged = user;
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
    for (User user : users) {
      if (user.getName().equals(name) && user.getPassword().equals(password)) {
        setLoggedUser(user);
        break;
      }
    }
    return true;
  }

  public boolean logout() {
    if (userLogged == null) return false;
    userLogged = null;
    return true;
  }

  public boolean ask(String title, String content, List<Label> labels) {
    if (userLogged == null) return false;
    Question question = new Question(userLogged, title, content);
    for(Label label: labels) {
      question.addLabel(label);
    }
    addQuestion(question);
    return true;
  }

  public boolean answer(Question question, String content) {
    if (userLogged == null) return false;
    Answer answer = new Answer(userLogged, content);
    question.addAnswer(answer);
    return true;
  }

  public boolean reward(Question question, int rewardQuantity) {
    if (userLogged == null) return false;
    if (question.getStatus().equals("Cerrada")) return false;
    boolean validatedReputation = userLogged.validateReputation(rewardQuantity);
    if (!validatedReputation) return false;
    userLogged.addDebtReputation(rewardQuantity);
    Reward reward = new Reward(rewardQuantity, userLogged);
    question.addReward(reward);
    return true;
  }

  public boolean accept(Question question, Answer answer) {
    if (userLogged == null) return false;
    question.setStatus("Cerrada");
    answer.setAcceptationStatus("Sí");
    userLogged.addOrSubstractReputation(2);
    answer.getAuthor().addOrSubstractReputation(15);
    for(Reward reward : question.getRewards()) {
      reward.getUser().discountReputation(reward.getQuantity());
    }
    question.setRewards(new ArrayList<>());
    return true;
  }

  public boolean vote(Question question, String voteType) {
    if (userLogged == null) return false;
    boolean result;

    switch (voteType) {
      case "UP" -> {
        question.addOrSubstractVotes(1);
        question.getAuthor().addOrSubstractReputation(10);
        result = true;
      }
      case "DOWN" -> {
        question.addOrSubstractVotes(-1);
        question.getAuthor().addOrSubstractReputation(-2);
        result = true;
      }
      default -> result = false;
    }

    return result;
  }

  public boolean vote(Answer answer, String voteType) {
    if (userLogged == null) return false;
    boolean result;

    switch (voteType) {
      case "UP" -> {
        answer.addOrSubstractVotes(1);
        answer.getAuthor().addOrSubstractReputation(10);
        result = true;
      }
      case "DOWN" -> {
        answer.addOrSubstractVotes(-1);
        answer.getAuthor().addOrSubstractReputation(-2);
        userLogged.addOrSubstractReputation(-1);
        result = true;
      }
      default -> result = false;
    }

    return result;
  }

  public List<Question> filterQuestionsByUser(User user) {
    List <Question> result = new ArrayList<>();

    for(Question question : questions) {
      boolean matchAuthor = user.getName().equals(question.getAuthor().getName());
      if (matchAuthor) {
        result.add(question);
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return "Stack{" +
            "userLogged=" + userLogged +
            ", users=" + users +
            ", questions=" + questions +
            ", labels=" + labels +
            '}' + "\n\n";
  }
}
