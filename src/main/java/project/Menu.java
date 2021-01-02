package project;

import java.util.*;

public class Menu {
  private static boolean exitProgram = false;
  private static boolean showStepsTwo = true;
  private static boolean showStepsThree = true;
  private static Scanner scanner = new Scanner(System.in);
  private static Stack currentStack;

  private static void printHeader() {
    System.out.print("### SISTEMA DE PREGUNTAS Y RESPUESTAS ###\n");
  }

  private static void printStepsOne() {
    printHeader();
    System.out.print("1. Empezar con un Stack existente\n");
    System.out.print("2. Crear un Stack desde cero\n");
    System.out.print("3. Salir del programa\n");
    System.out.print("ESCOJA UNA OPCIÓN:\n");
  }

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

  private static void printStepsThree() {
    printHeader();
    if (currentStack.getLoggedUser() != null) {
      System.out.printf("## Logueado como: %s ##\n", currentStack.getLoggedUser().getName());
    }
    System.out.print("1. Agregar nueva pregunta\n");
    System.out.print("2. Responder pregunta\n");
    System.out.print("3. Dar recompensa\n");
    System.out.print("4. Aceptar respuesta\n");
    System.out.print("5. Cerrar sesión\n");
    System.out.print("6. Salir del programa\n");
    System.out.print("ESCOJA UNA OPCIÓN:\n");
  }

  private static void showOptionsForLoggedUser() {
    // Imprime instrucciones
    if (showStepsThree) {
      printStepsThree();
    }

    scanner = new Scanner(System.in);
    String selectedOptionThree = scanner.nextLine();

    switch (selectedOptionThree) {
      case "1":
        // Agregar nueva pregunta (Ask)
        break;
      case "2":
        // Responder pregunta (Answer)
        break;
      case "3":
        // Dar recompensa (Reward)
        break;
      case "4":
        // Aceptar respuesta (Accept)
        break;
      case "5":
        // Cerrar sesión
        showStepsTwo = true;
        setLogout();
        break;
      case "6":
        // Salir del programa
        exitProgram();
        break;
      default:
        showStepsTwo = true;
        printInvalid(selectedOptionThree);
        break;
    }
  }

  private static void exitProgram() {
    printLogout();
    exitProgram = true;
  }

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

  private static void showRegisterOrLoginOptions() {
    String username;
    String password;

    // Imprime instrucciones
    if (showStepsTwo) {
      printStepsTwo();
    }

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
          System.out.printf("El usuario %s no se encuentra registrado. Intenta con otro nombre\n", username);
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
        printInvalid(selectedOptionTwo);
        break;
    }
  }

  private static void printLogout() {
    System.out.print("¡Adios!\n");
  }

  private static void printInvalid(String selection) {
    System.out.printf("La opción %s ingresada no es válida\n", selection);
  }

  public static void main(String[] args) {
    // Carga inicial

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
            "Me gustaría saber qué es un arreglo en Java", new ArrayList<>(Arrays.asList(label1, label2)));
    Question question2 = new Question("Inca Cola", "¿Cómo hacer un for loop en Java?",
            "No sé cómo hacer un for loop en Java", new ArrayList<>(Arrays.asList(label1, label2)));
    Question question3 = new Question("Pepsi", "¿Cuál es la diferencia entre un método y una función?",
            "No sé cómo hacer un for loop en Java", new ArrayList<>(Arrays.asList(label1, label2)));
    Question question4 = new Question("Canada Dry", "¿De qué se trata el criterio de cohesión?",
            "Me gustaría entender cómo funciona este concepto en OOP", Collections.singletonList(label2));
    Question question5 = new Question("Coca Cola", "¿Cómo empezar en Unity?",
            "Quiero programar videojuegos.", new ArrayList<>(Arrays.asList(label2, label3)));

    // Respuestas
    // Answer answer1 = new Answer(1, "Inca Cola", "Una arreglo es una estructura de dato que se encarga de reservar espacio en memoria para una colección de elementos");

    // Lista de usuarios
    List<User> users = Arrays.asList(user1, user2, user3, user4);
    List<Question> questions = Arrays.asList(question1, question2, question3, question4, question5);
    List<Label> labels  = Arrays.asList(label1, label2, label3);
    //List<Answer> answers = Collections.singletonList(answer1);

    // Stack
    Stack stack1 = new Stack(new ArrayList<>(users), new ArrayList<>(questions), new ArrayList<>(labels));

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
          exitProgram();
          break;
        default:
          printInvalid(selectedOptionOne);
          exitProgram();
          break;
      }
    }
  }
}
