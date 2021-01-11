package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que representa una pregunta del sistema Stackoverflow
 * Cada pregunta queda determinada por un autor, título y contenido.
 * Adicionalmente, se setean atributos por defecto como: Status, reward,
 * listado de respuestas y listado de etiquetas (listas vacías).
 * Relaciones: Relación de agregación: Answer (respuestas) y Label (etiquetas).
 */
public class Question {
   private static final AtomicInteger count = new AtomicInteger(0);
   private int id;
   private String title;
   private String content;
   private Date publicationDate;
   private String author;
   private String status;
   private List<Answer> answers;
   private List<Label> labels;
   private List<Reward> rewards;

   /**
    * Crea una pregunta a partir de un autor, título y contenido
    * @param author Autor de una pregunta
    * @param title Título de una pregunta
    * @param content Contenido de una pregunta
    */
   public Question(String author, String title, String content) {
      this.id = count.incrementAndGet();
      this.author = author;
      this.title = title;
      this.content = content;
      this.publicationDate = new Date();
      this.status = "Abierta";
      this.rewards = new ArrayList<Reward>();
      this.answers = new ArrayList<Answer>();
      this.labels = new ArrayList<Label>();
   }

   /** Crea una pregunta a partir de un autor, título y contenido (Sobrecarga)
    * @param author Autor de una pregunta
    * @param title Título de una pregunta
    * @param content Contenido de una pregunta
    * @param labels Lista de etiquetas
    */

   public Question(String author, String title, String content, List<Label> labels) {
      this.id = count.incrementAndGet();
      this.author = author;
      this.title = title;
      this.content = content;
      this.publicationDate = new Date();
      this.status = "Abierta";
      this.rewards = new ArrayList<Reward>();
      this.answers = new ArrayList<Answer>();
      this.labels = labels;
   }

   /**
    * Selector de respuestas asociadas a una pregunta
    * @return Lista de respuestas asociadas a una pregunta
    */
   public List<Answer> getAnswers() {
      return answers;
   }

   public List<Label> getLabels() {
      return labels;
   }

   public String getTitle() {
      return title;
   }

   public String getContent() {
      return content;
   }

   public String getAuthor() {
      return author;
   }

   /**
    * Modificador de respuestas asociadas a una pregunta
    * @param answer Nueva respuesta que se agregará a lista de respuestas
    *               asociadas a una pregunta
    */
   public void addAnswer(Answer answer) {
      answers.add(answer);
   }

   public void addReward(Reward reward) {
      rewards.add(reward);
   }

   @Override
   public String toString() {
      return "Question{" +
              "id=" + id +
              ", título='" + title + '\'' +
              ", contenido='" + content + '\'' +
              ", fecha publicación=" + publicationDate +
              ", autor='" + author + '\'' +
              ", estado='" + status + '\'' +
              ", respuestas=" + answers +
              ", etiquetas=" + labels +
              ", recompensas=" + rewards +
              '}';
   }
}
