package Hamyo.Misc;

import Hamyo.Tasks.Task;

/**
 * Ui - deals with interactions with the user.
 */
public class UI {

  public void greet() {
    printLine();
    printLogo();
    System.out.println("\nAnnyeonghaseyo! Hamyo here!\nHow may I assist you today?");
    printLine();
  }

  public void terminate() {
    System.out.println("Annyeong! Till we meet again. <3");
    printLine();
  }

  public static void printLine() {
    System.out.println("________________________________________________________________________________");
  }

  private static void printLogo() {
    System.out.println(
        "$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n" +
        "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n" +
        "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n" +
        "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n" +
        "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$");
  }

  public static void printAddTask(Task task, int size) {
    System.out.println("Got it. I've added this task:");
    System.out.println(task.toString());
    System.out.printf("There are %d tasks in the list now.\n", size);
    UI.printLine();
  }

  public static void printDeleteTask(Task task, int size) {
    System.out.println("Noted. I've removed this task:");
    System.out.println(task.toString());
    System.out.printf("There are %d tasks in the list now.\n", size);
    UI.printLine();
  }

  public static void printListTasks(String tasksList) {
    System.out.println("These are your tasks:");
    System.out.println((tasksList.isEmpty() ? "No tasks found!" : tasksList));
    UI.printLine();
  }

  public static void printListTasksByDate(String tasksList, String formattedDate) {
    System.out.println("These are your tasks on " + formattedDate + ".");
    System.out.println(tasksList);
    UI.printLine();
  }

  public static void markTask(Task task) {
    System.out.println("Yay! This task has been marked as completed.");
    System.out.println(task.toString());
    UI.printLine();
  }

  public static void unmarkTask(Task task) {
    System.out.println("Oki! This task has been marked as incomplete.");
    System.out.println(task.toString());
    UI.printLine();
  }

  public static void printException(Exception e) {
    System.out.println(e.toString());
    UI.printLine();
  }
}
