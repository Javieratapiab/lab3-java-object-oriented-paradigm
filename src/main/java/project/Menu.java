package project;

import java.util.*;

public class Menu {
  // Variables para menú
  private static boolean exitProgram = false;
  private static boolean showStepsTwo = true;
  private static boolean showStepsThree = true;
  private static Scanner scanner = new Scanner(System.in);
  private static Stack currentStack;
  private static Stack stack1;
  private static User user1;
  private static User user2;
  private static User user3;
  private static User user4;
  private static Label label1;
  private static Label label2;
  private static Label label3;
  private static Question question1;
  private static Question question2;
  private static Question question3;
  private static Question question4;
  private static Question question5;
  private static Answer answer1;
  private static Answer answer2;
  private static Answer answer3;
  private static Answer answer4;
  private static Answer answer5;
  private static Answer answer6;
  private static Answer answer7;
  private static Answer answer8;
  private static Answer answer9;
  private static Answer answer10;

  /**
   * @param args Argumentos de entrada del programa
   */
  public static void main(String[] args) {
    // Carga data inicial
    loadData();
    // Inicia menú
    printStepsOne();

    scanner = new Scanner(System.in);
    String selectedOptionOne = scanner.nextLine();

    while(!exitProgram) {
      switch (selectedOptionOne) {
        case "1":
          // Usar stack existente
          currentStack = stack1;
          showRegisterOrLoginOptions();
          break;
        case "2":
          // Usar nuevo stack
          currentStack = new Stack();
          showRegisterOrLoginOptions();
          break;
        case "3":
          // Salir del programa
          exitProgram();
          break;
        default:
          // Opción inválida
          printInvalid(selectedOptionOne);
          exitProgram();
          break;
      }
    }
  }

  /**
   * Método de clase estático que carga data de prueba
   */
  private static void loadData() {
    // Usuarios
    user1 = new User("Pepsi", "pa$$");
    user2 = new User("Coca Cola", "12345");
    user3 = new User("Canada Dry","myPass");
    user4 = new User("Inca Cola", "mySuperPa$$");

    // Labels
    label1 = new Label("Java", "Lenguaje de programación");
    label2 = new Label("Computer science", "Ciencia que estudia a los computadores");
    label3 = new Label("C#", "Lenguaje de programación");

    // Preguntas
    question1 = new Question("Canada Dry", "¿Qué es un arreglo?",
            "Me gustaría saber qué es un arreglo en Java", new ArrayList<>(Arrays.asList(label1, label2)));
    question2 = new Question("Inca Cola", "¿Cómo hacer un for loop en Java?",
            "No sé cómo hacer un for loop en Java", new ArrayList<>(Arrays.asList(label1, label2)));
    question3 = new Question("Pepsi", "¿Cuál es la diferencia entre un método y una función?",
            "No sé cómo hacer un for loop en Java", new ArrayList<>(Arrays.asList(label1, label2)));
    question4 = new Question("Canada Dry", "¿De qué se trata el criterio de cohesión?",
            "Me gustaría entender cómo funciona este concepto en OOP", Collections.singletonList(label2));
    question5 = new Question("Coca Cola", "¿Cómo empezar en Unity?",
            "Quiero programar videojuegos.", new ArrayList<>(Arrays.asList(label2, label3)));

    // Respuestas
    // Answer answer1 = new Answer(1, "Inca Cola", "Una arreglo es una estructura de dato que se encarga de reservar espacio en memoria para una colección de elementos");

    // Stack
    stack1 = new Stack(
            new ArrayList<>(Arrays.asList(user1, user2, user3, user4)),
            new ArrayList<>(Arrays.asList(question1, question2, question3, question4, question5)),
            new ArrayList<>(Arrays.asList(label1, label2, label3)));
  }

  /**
   * Método de clase estático que imprime cabecera del menú y stack elegido
   */
  private static void printHeader() {
    System.out.print("### SISTEMA DE PREGUNTAS Y RESPUESTAS ###\n");
    if (currentStack != null) {
      System.out.print(currentStack.toString());
    }
  }

  /**
   * Método de clase estático que imprime primera sección del menú
   */
  private static void printStepsOne() {
    printHeader();
    System.out.print("1. Empezar con un Stack existente\n");
    System.out.print("2. Crear un Stack desde cero\n");
    System.out.print("3. Salir del programa\n");
    System.out.print("ESCOJA UNA OPCIÓN:\n");
  }

  /**
   * Método de clase estático que imprime segunda sección del menú
   */
  private static void printStepsTwo() {
    printHeader();
    if (currentStack.getLoggedUser() != null) {
      System.out.printf("## Logueado como: %s ##\n", currentStack.getLoggedUser().getName());
    }
    System.out.print("1. Registrar un usuario\n");
    System.out.print("2. Loguear un usuario\n");
    System.out.print("3. Cerrar sesión\n");
    System.out.print("4. Salir del programa\n");
    System.out.print("ESCOJA UNA OPCIÓN:\n");
  }

  /**
   * Método de clase estático que imprime tercera sección del menú
   */
  private static void printStepsThree() {
    printHeader();
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
  }

  /**
   * Método de clase estático que muestra en menú que una opción seleccionada no es válida
   * @param selection Opción seleccionada en el menú no válida
   */
  private static void printInvalid(String selection) {
    System.out.printf("La opción %s ingresada no es válida\n", selection);
  }

  /**
   * Método estático que muestra mensaje de despedida y cambia flag (exitProgram) a true para salir del while loop
   * de menú interactivo.
   */
  private static void exitProgram() {
    exitProgram = true;
    System.out.print("¡Adios!\n");
  }

  /**
   * Método de clase que muestra al usuario las etiquetas disponibles en el stack que pueden ser elegidas
   * para la creación de una pregunta
   * @return Lista de etiquetas del stack que serán usadas para la creación de una pregunta
   */
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
          printInvalid(acceptedLabel);
          break;
      }
    }
    return selectedLabels;
  }

  /**
   * Método de clase que muestra al usuario las etiquetas disponibles en el stack que pueden ser elegidas
   * para la creación de una pregunta.
   * @return Lista de etiquetas del stack que serán usadas para la creación de una pregunta
   */
  private static Question selectQuestion() {
    Question selectedQuestion = null;

    System.out.print("Elija una de las siguientes pregunta: \n");

    for(Question question : currentStack.getQuestions()) {
      if (selectedQuestion == null) {
        System.out.printf("Pregunta: %s", question.toString());
        System.out.printf("1. Seleccionar\n");
        System.out.printf("2. Continuar\n");

        String acceptedQuestion = scanner.nextLine();

        switch (acceptedQuestion) {
          case "1":
            selectedQuestion = question;
            System.out.printf("Has seleccionado la siguiente pregunta: %s\n", question.toString());
            break;
          case "2":
            continue;
          default:
            printInvalid(acceptedQuestion);
            break;
        }
      }
    }
    return selectedQuestion;
  }

  /**
   * Método de clase estático que muestra opciones disponibles luego de loguear un usuario registrado,
   * permite la creación de una pregunta, respuesta, aceptar respuesta, crear etiquetas, etc.
   */
  private static void showOptionsForLoggedUser() {
    while(!exitProgram && currentStack.getLoggedUser() != null) {
      // Imprime instrucciones
      if (showStepsThree) {
        printStepsThree();
      }
      scanner = new Scanner(System.in);
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
            System.out.printf("La pregunta ha sido creada exitosamente\n");
          } else {
            System.out.printf("La creación de pregunta ha fallado. Inténtalo nuevamente\n");
          }
          showStepsThree = true;
          break;
        case "2":
          // Responder pregunta (Answer)
          Question selectedQuestion = selectQuestion();
          System.out.printf("Contenido de la respuesta: \n");
          String answerContent = scanner.nextLine();
          boolean answerSuccess = currentStack.answer(selectedQuestion, answerContent);
          if (answerSuccess) {
            System.out.printf("La respuesta ha sido creada exitosamente\n");
          } else {
            System.out.printf("La creación de respuesta ha fallado. Inténtalo nuevamente\n");
          }
          showStepsThree = true;
          break;
        case "3":
          // Dar recompensa (Reward)
          break;
        case "4":
          // Aceptar respuesta (Accept)
          break;
        case "5":
          // Agregar una etiqueta
        case "6":
          // Cerrar sesión
          showStepsTwo = true;
          setLogout();
          break;
        case "7":
          // Salir del programa
          exitProgram();
          break;
        default:
          showStepsTwo = true;
          printInvalid(selectedOptionThree);
          break;
      }
    }
  }

  /**
   * Método estático que pide credenciales para cerrar sesión y elimina sesión activa (stack logout)
   * si las credenciales son correctas.
   */
  private static void setLogout() {
    System.out.println("Ingresa nombre de usuario:");
    String username = scanner.nextLine();
    System.out.println("Ingresa password:");
    String password = scanner.nextLine();
    boolean logoutSuccess = currentStack.logout(username, password);
    if (logoutSuccess) {
      System.out.printf("El usuario %s ha sido deslogueado correctamente\n", username);
    } else {
      System.out.printf("El usuario %s no se encuentra logueado. Intenta con otro nombre\n", username);
    }
  }

  /**
   * Método de clase estático que muestra las opciones disponibles posterior a la elección de un stack,
   * si un usuario se loguea correctamente se mostrarán en consola las opciones disponibles para una sesión activa
   * (preguntar, responder, aceptar, ofrecer recompensa, crear etiquetas, etc).
   */
  private static void showRegisterOrLoginOptions() {
    String username;
    String password;

    while(showStepsTwo && !exitProgram) {
      printStepsTwo();
      scanner = new Scanner(System.in);
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
            System.out.printf("El usuario %s ha sido registrado correctamente\n", username);
          } else {
            System.out.printf("El usuario %s ya existe. Intenta con otro nombre\n", username);
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
            showStepsTwo = false;
            System.out.printf("El usuario %s ha sido logueado correctamente\n", username);
            showOptionsForLoggedUser();
          } else {
            System.out.printf("Credenciales incorrectas. Intenta nuevamente\n");
          }
          break;
        case "3":
          // Cerrar sesión
          setLogout();
          break;
        case "4":
          // Salir del programa
          exitProgram();
          break;
        default:
          // Opción no válida
          System.out.printf("La opción %s ingresada no es válida\n", selectedOptionTwo);
          printInvalid(selectedOptionTwo);
          break;
      }
    }
  }
}
