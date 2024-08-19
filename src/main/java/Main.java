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
                int taskIndex = Integer.parseInt(textInput.substring(5)) - 1;
                poChat.markTaskDone(taskIndex);
            } else if (textInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(textInput.substring(7)) - 1;
                poChat.unmarkTaskDone(taskIndex);
            } else if (textInput.startsWith("todo")) {
                poChat.addToListAndReply(textInput.substring(5));
            } else if (textInput.startsWith("deadline")) {
                int byIndex = textInput.indexOf("/by");

                String taskDescription = textInput.substring(9, byIndex - 1);
                String deadline = textInput.substring(byIndex + 4);

                poChat.addToListAndReply(taskDescription, deadline);
            } else if (textInput.startsWith("event")) {
                int fromIndex = textInput.indexOf("/from");
                int toIndex = textInput.indexOf("/to");

                String taskDescription = textInput.substring(6, fromIndex - 1);
                String startDate = textInput.substring(fromIndex + 6, toIndex - 1);
                String endDate = textInput.substring(toIndex + 4);

                poChat.addToListAndReply(taskDescription, startDate, endDate);
            } else {
                poChat.replyToInvalidInput();
            }
        }
    }
}