package project;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Answer {
  private static final AtomicInteger count = new AtomicInteger(0);
  private final int id;
  private final Date publicationDate;
  private String acceptationStatus;
  private final String content;
  private int votes;
  private final User author;

  public Answer(User user, String content) {
    this.id = count.incrementAndGet();
    this.author = user;
    this.content = content;
    this.votes = 0;
    this.publicationDate = new Date();
    this.acceptationStatus = "No";
  }

  public void setAcceptationStatus(String newStatus) {
    acceptationStatus = newStatus;
  }

  public User getAuthor() {
    return author;
  }

  public void addOrSubstractVotes(int vote) {
    votes += vote;
  }

  @Override
  public String toString() {
    return "Answer{" +
            "id=" + id +
            ", publicationDate=" + publicationDate +
            ", acceptationStatus='" + acceptationStatus + '\'' +
            ", content='" + content + '\'' +
            ", votes=" + votes +
            ", author=" + author +
            '}';
  }
}
