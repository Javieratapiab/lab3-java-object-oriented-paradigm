package project;

public class Menu {
  public static void main(String[] args) {
    User userOne = new User("Javiera", "pa$$");
    Question question = new Question();
    Answer answer = new Answer("Javiera", "Esta es una respuesta");
    question.addAnswer(answer);
    System.out.print(question.getAnswers().get(0).getAuthor());
  }
}
