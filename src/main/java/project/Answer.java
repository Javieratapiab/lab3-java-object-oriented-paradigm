package project;

import java.util.Date;

public class Answer {
  public int _id;
  public String _author;
  public Date _publicationDate;
  public String _content;

  public Answer(String author, String content) {
    _author = author;
    _content = content;
    _publicationDate = new Date();
  }

  public String getAuthor() {
    return _author;
  }
}
