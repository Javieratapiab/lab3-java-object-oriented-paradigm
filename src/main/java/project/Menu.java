package project;

import java.util.*;

public class Menu {
  private static boolean exitProgram = false;
  private static final Scanner scanner = new Scanner(System.in);
  private static Stack currentStack;

  public static void printHeader() {
    System.out.print("### SISTEMA DE PREGUNTAS Y RESPUESTAS ###\n");
  }

  public static void printStepsOne() {
    printHeader();
    System.out.print("1. Empezar con un Stack existente\n");
    System.out.print("2. Crear un Stack desde cero\n");
    System.out.print("3. Salir del programa\n");
    System.out.print("ESCOJA UNA OPCIÓN:\n");
  }

  public static void printStepsTwo() {
    printHeader();
    System.out.print("1. Registrar un usuario\n");
    System.out.print("2. Loguear un usuario\n");
    System.out.print("3. Salir del programa\n");
    System.out.print("ESCOJA UNA OPCIÓN:\n");
  }

  public static void initRegisterOrLogin() {
    String username;
    String password;
    // Imprime instrucciones
    printStepsTwo();
    int selectedOption = Integer.parseInt(scanner.nextLine());

    switch (selectedOption) {
      case 1 -> {
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
      }
      case 2 -> {
        // Loguear usuario
        System.out.println("Ingresa nombre de usuario:");
        username = scanner.nextLine();
        System.out.println("Ingresa password:");
        password = scanner.nextLine();
        boolean loginSuccess = currentStack.login(username, password);
        if (loginSuccess) {
          System.out.printf("El usuario %s ha sido logueado correctamente\n", username);
        } else {
          System.out.printf("El usuario %s no se encuentra registrado. Intenta con otro nombre\n", username);
        }
      }
      default -> System.out.println("La opción ingresada no es válida: " + selectedOption);
    }
  }

  public static void printLogout() {
    System.out.print("¡Adios!\n");
  }

  public static void printInvalid() {
    System.out.print("Por favor, selecciona una opción válida\n");
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
    int selectedOption = Integer.parseInt(scanner.nextLine());

    while (!exitProgram) {
      switch (selectedOption) {
        case 1 -> {
          // Usar stack existente
          currentStack = stack1;
          initRegisterOrLogin();
        }
        case 2 -> {
          // Usar nuevo stack
          currentStack = new Stack();
          printStepsTwo();
          initRegisterOrLogin();
        }
        case 3 -> {
          printLogout();
          exitProgram = true;
        }
        default -> printInvalid();
      }
    }
  }
}
