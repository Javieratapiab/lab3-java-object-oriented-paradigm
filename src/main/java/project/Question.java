package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


public class Question {
   private static final AtomicInteger count = new AtomicInteger(0);
   private final int id;
   private final String title;
   private final String content;
   private final Date publicationDate;
   private final User author;
   private int votes;
   private String status;
   private List<Answer> answers;
   private List<Label> labels;
   private List<Reward> rewards;

   public Question(User author, String title, String content) {
      this.id = count.incrementAndGet();
      this.author = author;
      this.title = title;
      this.content = content;
      this.publicationDate = new Date();
      this.votes = 0;
      this.status = "Abierta";
      this.answers = new ArrayList<>();
      this.rewards = new ArrayList<>();
      this.labels = new ArrayList<>();
   }

   public List<Answer> getAnswers() {
      return answers;
   }

   public User getAuthor() {
      return author;
   }

   public List<Reward> getRewards() {
      return rewards;
   }

   public String getStatus() {
      return status;
   }

   public void addLabel(Label label) {
      labels.add(label);
   }

   public void addAnswer(Answer answer) {
      answers.add(answer);
   }

   public void addReward(Reward reward) {
      rewards.add(reward);
   }

   public void addOrSubstractVotes(int vote) {
      votes += vote;
   }

   public void setStatus(String newStatus) {
      status = newStatus;
   }

   public void setRewards(List<Reward> newRewards) {
     rewards = newRewards;
   }

   @Override
   public String toString() {
      return "Question{" +
              "id=" + id +
              ", title='" + title + '\'' +
              ", content='" + content + '\'' +
              ", publicationDate=" + publicationDate +
              ", author=" + author +
              ", votes=" + votes +
              ", status='" + status + '\'' +
              ", answers=" + answers +
              ", labels=" + labels +
              ", rewards=" + rewards +
              '}';
   }
}
