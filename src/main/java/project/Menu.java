package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
  private static boolean exitProgram = false;
  private static final Scanner scanner = new Scanner(System.in);
  private static Stack currentStack;


  public static void main(String[] args) {
    // Inicia menú
    menuPrincipal();
  }

  private static void printMenuHeader() {
    System.out.print("\n### SISTEMA DE PREGUNTAS Y RESPUESTAS ###\n");
    if (currentStack != null) {
      System.out.printf(currentStack.toString());
    }
  }

  public static void menuPrincipal() {
    while(!exitProgram) {
      printMenuHeader();
      System.out.print("1. Empezar con un Stack existente\n");
      System.out.print("2. Crear un Stack desde cero\n");
      System.out.print("3. Salir del programa\n");
      System.out.print("ESCOJA UNA OPCIÓN:\n");

      String selectedOptionOne = scanner.nextLine();

      switch (selectedOptionOne) {
        case "1":
          // Usar stack existente
          currentStack = buildStack();
          registerAndLoginMenu();
          break;
        case "2":
          // Usar nuevo stack
          currentStack = new Stack();
          registerAndLoginMenu();
          break;
        case "3":
          // Salir del programa
          exitProgram = true;
          scanner.close();
          break;
        default:
          // Opción inválida
          System.out.printf("Tu opción no es válida. Selecciona un número entre 1 a 3. \n");
      }
    }
  }

  private static Stack buildStack() {
    // Usuarios
    User user1 = new User("Pepsi", "pa$$");
    User user2 = new User("Coca Cola", "12345");
    User user3 = new User("Canada Dry","myPass");
    User user4 = new User("Inca Cola", "mySuperPa$$");

    // Labels
    Label label1 = new Label("Java", "Lenguaje de programación");
    Label label2 = new Label("Computer science", "Ciencia que estudia a los computadores");
    Label label3 = new Label("C#", "Lenguaje de programación");

    // Preguntas
    Question question1 = new Question("Canada Dry", "¿Qué es un arreglo?",
            "Me gustaría saber qué es un arreglo en Java", new ArrayList<Label>(Arrays.asList(label1, label2)));
    Question question2 = new Question("Inca Cola", "¿Cómo hacer un for loop en Java?",
            "No sé cómo hacer un for loop en Java", new ArrayList<Label>(Arrays.asList(label1, label2)));
    Question question3 = new Question("Pepsi", "¿Cuál es la diferencia entre un método y una función?",
            "No sé cómo hacer un for loop en Java", new ArrayList<Label>(Arrays.asList(label1, label2)));
    Question question4 = new Question("Canada Dry", "¿De qué se trata el criterio de cohesión?",
            "Me gustaría entender cómo funciona este concepto en OOP", new ArrayList<>(Arrays.asList(label2)));
    Question question5 = new Question("Coca Cola", "¿Cómo empezar en Unity?",
            "Quiero programar videojuegos.", new ArrayList<Label>(Arrays.asList(label2, label3)));

    // Respuestas
    // Answer answer1 = new Answer(1, "Inca Cola", "Una arreglo es una estructura de dato que se encarga de reservar espacio en memoria para una colección de elementos");

    // Stack
    Stack stack1 = new Stack(
            new ArrayList<>(Arrays.asList(user1, user2, user3, user4)),
            new ArrayList<>(Arrays.asList(question1, question2, question3, question4, question5)),
            new ArrayList<>(Arrays.asList(label1, label2, label3)));

    return stack1;
  }

  private static void registerAndLoginMenu() {
    while(!exitProgram) {
      String username;
      String password;

      printMenuHeader();
      System.out.print("1. Registrar un usuario\n");
      System.out.print("2. Loguear un usuario\n");
      System.out.print("3. Cerrar sesión\n");
      System.out.print("4. Salir del programa\n");
      System.out.print("ESCOJA UNA OPCIÓN:\n");

      String selectedOptionTwo = scanner.nextLine();

      switch (selectedOptionTwo) {
        case "1":
          // Registrar usuario
          System.out.println("Ingresa nombre de usuario:");
          username = scanner.nextLine();
          System.out.println("Ingresa password:");
          password = scanner.nextLine();
          boolean registerSuccess = currentStack.register(username, password);
          if (registerSuccess) {
            System.out.printf("\nRESULTADO: El usuario %s ha sido registrado correctamente\n", username);
          } else {
            System.out.printf("\nRESULTADO: El usuario %s ya existe. Intenta con otro nombre\n", username);
          }
          break;
        case "2":
          // Loguear usuario
          System.out.println("Ingresa nombre de usuario:");
          username = scanner.nextLine();
          System.out.println("Ingresa password:");
          password = scanner.nextLine();
          boolean loginSuccess = currentStack.login(username, password);
          if (loginSuccess) {
            System.out.printf("\nRESULTADO: El usuario %s ha sido logueado correctamente\n", username);
            loggedUserMenu();
          } else {
            System.out.printf("\nRESULTADO: Credenciales incorrectas. Intenta nuevamente\n");
          }
          break;
        case "3":
          // Cerrar sesión
          setLogout();
          menuPrincipal();
          break;
        case "4":
          // Salir del programa
          exitProgram = true;
          scanner.close();
          break;
        default:
          // Opción no válida
          System.out.printf("Tu opción no es válida. Selecciona un número entre 1 y 4\n");
          break;
      }
    }
  }

  private static List<Label> selectLabels() {
    List<Label> selectedLabels = new ArrayList<Label>();

    System.out.print("Elija las etiquetas relacionadas a su pregunta: \n");

    for(Label label : currentStack.getLabels()) {
      System.out.printf("Etiqueta: %s, descripción: %s \n", label.getName(), label.getDescription());
      System.out.printf("1. Agregar\n");
      System.out.printf("2. No agregar\n");

      String acceptedLabel = scanner.nextLine();

      switch (acceptedLabel) {
        case "1":
          selectedLabels.add(label);
          System.out.printf("Etiqueta: %s ha sido agregada a la pregunta\n", label.getName());
          continue;
        case "2":
          System.out.printf("Etiqueta: %s no se agrega a la pregunta\n", label.getName());
          continue;
        default:
          System.out.printf("Tu opción no es válida. Selecciona un número entre 1 y 2\n");
          break;
      }
    }
    return selectedLabels;
  }

  private static Question selectQuestion(List <Question> questions) {
    Question selectedQuestion = null;

    System.out.print("Elija una de las siguientes preguntas: \n");

    for(Question question : questions) {
      if (selectedQuestion == null) {
        System.out.printf("Pregunta: %s\n", question.toString());
        System.out.print("1. Seleccionar\n");
        System.out.print("2. Continuar\n");

        String acceptedQuestion = scanner.nextLine();

        switch (acceptedQuestion) {
          case "1":
            selectedQuestion = question;
            System.out.printf("Has seleccionado la siguiente pregunta: %s\n", question.toString());
            break;
          case "2":
            continue;
          default:
            System.out.printf("Tu opción no es válida. Selecciona un número entre 1 y 2\n");
            break;
        }
      }
    }
    return selectedQuestion;
  }

  private static Answer selectAnswer(List<Answer> answers) {
    Answer selectedAnswer = null;

    System.out.print("\nElija una de las siguientes respuestas: \n");

    for(Answer answer : answers) {
      if (selectedAnswer == null) {
        System.out.printf("Respuesta: %s\n", answer.toString());
        System.out.print("1. Seleccionar\n");
        System.out.print("2. Continuar\n");

        String acceptedAnswer = scanner.nextLine();

        switch (acceptedAnswer) {
          case "1":
            selectedAnswer = answer;
            System.out.printf("\nHas seleccionado la siguiente respuesta: %s\n", answer.toString());
            break;
          case "2":
            continue;
          default:
            System.out.printf("\nTu opción no es válida. Selecciona un número entre 1 y 2\n");
            break;
        }
      }
    }
    return selectedAnswer;
  }

  private static void loggedUserMenu() {
    while(!exitProgram) {
      Question selectedQuestion;
      Answer selectedAnswer;

      printMenuHeader();
      if (currentStack.getLoggedUser() != null) {
        System.out.printf("## Logueado como: %s ##\n", currentStack.getLoggedUser().getName());
      }
      System.out.print("1. Agregar nueva pregunta\n");
      System.out.print("2. Responder pregunta\n");
      System.out.print("3. Dar recompensa\n");
      System.out.print("4. Aceptar respuesta\n");
      System.out.print("5. Crear etiqueta\n");
      System.out.print("6. Cerrar sesión\n");
      System.out.print("7. Salir del programa\n");
      System.out.print("ESCOJA UNA OPCIÓN:\n");

      String selectedOptionThree = scanner.nextLine();

      switch (selectedOptionThree) {
        case "1":
          // Agregar nueva pregunta (Ask)
          System.out.print("Título de la pregunta: \n");
          String title = scanner.nextLine();
          System.out.print("Contenido de la pregunta: \n");
          String content = scanner.nextLine();
          List<Label> selectedLabels = selectLabels();
          boolean askSuccess = currentStack.ask(title, content, selectedLabels);
          if (askSuccess) {
            System.out.printf("\nRESULTADO: La pregunta ha sido creada exitosamente\n");
          } else {
            System.out.printf("\nRESULTADO: La creación de pregunta ha fallado. Inténtalo nuevamente\n");
          }
          break;
        case "2":
          // Responder pregunta (Answer)
          selectedQuestion = selectQuestion(currentStack.getQuestions());
          if (selectedQuestion == null) {
            System.out.print("No has seleccionado ninguna pregunta. Inténtalo nuevamente\n");
          } else {
            System.out.printf("Contenido de la respuesta: \n");
            String answerContent = scanner.nextLine();
            boolean answerSuccess = currentStack.answer(selectedQuestion, answerContent);
            if (answerSuccess) {
              System.out.print("\nRESULTADO: La respuesta ha sido creada exitosamente\n");
            } else {
              System.out.printf("\nRESULTADO: La creación de respuesta ha fallado. Inténtalo nuevamente\n");
            }
          }
          break;
        case "3":
          // Dar recompensa (Reward)
          selectedQuestion = selectQuestion(currentStack.getQuestions());
          System.out.printf("\nIngrese una recompensa (puntos): \n");
          int rewardQuantity = scanner.nextInt();
          scanner.nextLine();
          boolean rewardSuccess = currentStack.reward(selectedQuestion, rewardQuantity);
          if (rewardSuccess) {
            System.out.print("\nRESULTADO: La recompensa ha sido creada exitosamente\n");
          } else {
            System.out.print("\nRESULTADO: La entrega de recompensa ha fallado. Inténtalo nuevamente\n");
          }
          break;
        case "4":
          // Aceptar respuesta (Accept)
          List<Question> filteredQuestions = currentStack.filterQuestionsByLoggedUser();
          selectedQuestion = selectQuestion(filteredQuestions);
          if (selectedQuestion == null) {
            System.out.print("\nNo has seleccionado ninguna pregunta. Inténtalo nuevamente\n");
          } else {
            selectedAnswer = selectAnswer(selectedQuestion.getAnswers());
            if (selectedAnswer == null) {
              System.out.print("\nNo has seleccionado ninguna respuesta. Inténtalo nuevamente\n");
            } else {
              boolean acceptSuccess = currentStack.accept(selectedQuestion, selectedAnswer);
              if (acceptSuccess) {
                System.out.print("\nRESULTADO: La respuesta ha sido aceptada exitosamente\n");
              } else {
                System.out.print("\nRESULTADO: No se ha podido aceptar la respuesta. Inténtalo nuevamente\n");
              }
            }
          }
          break;
        case "5":
          // Agregar una etiqueta
        case "6":
          // Cerrar sesión
          setLogout();
          registerAndLoginMenu();
          break;
        case "7":
          // Salir del programa
          exitProgram = true;
          scanner.close();
          break;
        default:
          System.out.printf("\nTu opción no es válida. Selecciona un número entre 1 y 7\n");
          break;
      }
    }
  }

  private static void setLogout() {
    boolean logoutSuccess = currentStack.logout();
    if (logoutSuccess) {
      System.out.printf("\nRESULTADO: Has sido deslogueado exitosamente\n");
    } else {
      System.out.printf("\nRESULTADO: Cierre de sesión inválido, no hay usuarios logueados.\n");
    }
  }
}
