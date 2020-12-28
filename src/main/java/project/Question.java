package project;

import java.util.ArrayList;
import java.util.Date;

/**
 * TDA Question (pregunta)
 * Relación: Relación de agregación con Answer (respuesta).
 */
public class Question {
   private int _id;
   private Label _labels;
   private String _title;
   private String _content;
   private Date _date;
   private String _author;
   private String _status;
   private int _reward;
   private ArrayList<Answer> _answers;

   public Question() {
      _answers = new ArrayList<Answer>();
   }

   public ArrayList<Answer> getAnswers() {
      return _answers;
   }

   public void addAnswer(Answer answer) {
      _answers.add(answer);
   }
}
