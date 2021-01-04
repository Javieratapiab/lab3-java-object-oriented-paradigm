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
 * @version 1, 13/01/21
 * @author Javiera Tapia
 */
public class Question {
   private static final AtomicInteger count = new AtomicInteger(0);
   private int id;
   private String title;
   private String content;
   private Date publicationDate;
   private String author;
   private String status;
   private int reward;
   private List<Answer> answers;
   private List<Label> labels;

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
      this.answers = new ArrayList<Answer>();
      this.labels = labels;
   }

   /**
    * Selector de respuestas asociadas a una pregunta
    * @return Lista de respuestas asociadas a una pregunta
    */
   public List<Answer> getAnswers() {
      return this.answers;
   }

   public List<Label> getLabels() {
      return this.labels;
   }

   public String getTitle() {
      return this.title;
   }

   public String getContent() {
      return this.content;
   }

   /**
    * Modificador de respuestas asociadas a una pregunta
    * @param answer Nueva respuesta que se agregará a lista de respuestas
    *               asociadas a una pregunta
    */
   public void addAnswer(Answer answer) {
      this.answers.add(answer);
   }

   @Override
   public String toString() {
      return "Question{" +
              "id=" + id +
              ", título='" + title + '\'' +
              ", contenido='" + content + '\'' +
              ", fecha de publicación=" + publicationDate +
              ", autor='" + author + '\'' +
              ", estado='" + status + '\'' +
              ", recompensa=" + reward +
              ", respuestas=" + answers +
              ", etiquetas=" + labels +
              "}\n";
   }
}
