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

}
