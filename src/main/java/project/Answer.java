package project;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Answer {
  private static final AtomicInteger count = new AtomicInteger(0);
  private int id;
  private String author;
  private Date publicationDate;
  private String content;

  public Answer(String author, String content) {
    this.id = count.incrementAndGet();
    this.author = author;
    this.content = content;
    this.publicationDate = new Date();
  }

  public String getAuthor() {
    return author;
  }

  @Override
  public String toString() {
    return "Answer{" +
            "id=" + id +
            ", autor='" + author + '\'' +
            ", fecha de publicación=" + publicationDate +
            ", contenido='" + content + '\'' +
            '}';
  }
}
