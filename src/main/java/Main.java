import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PoChat poChat = new PoChat();
        poChat.sayHello();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String textInput = scanner.nextLine();
            if (textInput.equals("bye")) {
                poChat.sayGoodbye();
                break;
            } else if (textInput.equals(("list"))) {
                poChat.replyWithListOfTextsEntered();
            } else if (textInput.startsWith("mark")) {
                int index = Integer.parseInt(textInput.substring(5)) - 1;
                poChat.markTaskDone(index);
            } else if (textInput.startsWith("unmark")) {
                int index = Integer.parseInt(textInput.substring(7)) - 1;
                poChat.unmarkTaskDone(index);
            } else {
                poChat.addToListAndReply(textInput);
            }
        }
    }
}