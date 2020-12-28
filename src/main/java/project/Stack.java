package project;

import java.util.ArrayList;

public class Stack {
  private ArrayList<User> _users;
  private ArrayList<Question> _questions;
  private ArrayList<Label> _labels;
  private ArrayList<Answer> _answers;

  public Stack(ArrayList<User> users,
               ArrayList<Question> questions,
               ArrayList<Label> labels,
               ArrayList<Answer> answers) {
    _users = users;
    _questions = questions;
    _labels = labels;
    _answers = answers;
  }
}
