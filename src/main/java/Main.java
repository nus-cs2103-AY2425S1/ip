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
            } else {
                poChat.addToListAndReply(textInput);
            }
        }
    }
}