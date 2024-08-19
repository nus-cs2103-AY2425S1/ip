import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TaskDescriptionEmptyException {
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
            } else if (textInput.startsWith("delete")) {
                int taskIndex = Integer.parseInt(textInput.substring(7)) - 1;
                poChat.deleteTask(taskIndex);
            } else if (textInput.startsWith("todo")) {
                try {
                    String taskDescription = textInput.substring(5);
                    if (taskDescription.isEmpty()) {
                        throw new TaskDescriptionEmptyException();
                    }
                    poChat.addToListAndReply(taskDescription);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new TaskDescriptionEmptyException();
                }
            } else if (textInput.startsWith("deadline")) {
                try {
                    int byIndex = textInput.indexOf("/by");

                    String taskDescription = textInput.substring(9, byIndex - 1);
                    String deadline = textInput.substring(byIndex + 4);

                    if (taskDescription.isEmpty()) {
                        throw new TaskDescriptionEmptyException();
                    }

                    poChat.addToListAndReply(taskDescription, deadline);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new TaskDescriptionEmptyException();
                }
            } else if (textInput.startsWith("event")) {
                try {
                    int fromIndex = textInput.indexOf("/from");
                    int toIndex = textInput.indexOf("/to");

                    String taskDescription = textInput.substring(6, fromIndex - 1);
                    String startDate = textInput.substring(fromIndex + 6, toIndex - 1);
                    String endDate = textInput.substring(toIndex + 4);

                    if (taskDescription.isEmpty()) {
                        throw new TaskDescriptionEmptyException();
                    }

                    poChat.addToListAndReply(taskDescription, startDate, endDate);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new TaskDescriptionEmptyException();
                }
            } else {
                poChat.replyToInvalidInput();
            }
        }
    }
}