import java.util.Scanner;

public class Vuewee {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TaskListUI ui = new TaskListUI(scanner);
    ui.run();
    scanner.close();
  }
}
