public class UI {
    public void printFormattedResponse(String response) {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
        System.out.println(response);
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println("\n");
    }

    public void printByeMessage() {
        printFormattedResponse("Bye. Hope to see you again soon!");
    }

    public void printErrorMessage(String s) {
        printFormattedResponse("Oops! An error has occurred " + s);
    }
}
