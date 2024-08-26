import java.util.Scanner;

public class Dave {

    public static void main(String[] args) {
        String logo = " ____    _    __     ______\n"
                + "|  _ \\  / \\   \\ \\   / / ___|\n"
                + "| | | |/ _ \\   \\ \\ / /|  _|\n"
                + "| |_| / ___ \\   \\ V / | |___\n"
                + "|____/_/   \\_\\   \\_/  |_____|\n";
        String horizontal = "__________________________________________________________";
        Task[] dataList = new Task[100];
        Boolean[] booleanList = new Boolean[100];
        int dataIndex = 0;
        String state;
        String statement;
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal);
        System.out.println("Hello! I'm Dave.");
        System.out.println("What can I do for you?");
        System.out.println(horizontal);
        Scanner scanner = new Scanner(System.in);
        String userInput;

        for (int i = 0; i < 100 ; i ++)
        {
            booleanList[i] = false;
        }

        while (true)
        {
            String[] reply = scanner.nextLine().trim().split(" ", 2);
            userInput = reply[0];

            if (userInput.isEmpty()) {
                continue;
            }

            switch (userInput) {
                case "bye":
                    System.out.println(horizontal);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(horizontal);
                    scanner.close();
                    return;

                case "list":
                    System.out.println(horizontal);
                    for (int i = 0; i < dataIndex; i++) {
                        statement = String.format("%d.%s", i+1, dataList[i]);
                        System.out.println(statement);
                    }
                    System.out.println(horizontal);
                    break;

                case "mark":
                    if (reply.length > 0)
                    {
                        int taskNumber = Integer.parseInt(reply[1]);
                        if (dataList[taskNumber - 1] == null)
                        {
                            System.out.println(horizontal);
                            System.out.println("Oh no! You have entered invalid number. Please enter again! ");
                            System.out.println(horizontal);
                            continue;
                        }
                        dataList[taskNumber - 1].markAsDone();
                        System.out.println(horizontal);
                        System.out.println("Nice! I've marked this task as done:");
                        statement = String.format("%s", dataList[taskNumber - 1]);
                        System.out.println(statement);
                    }
                    System.out.println(horizontal);
                    break;

                case "unmark":
                    if (reply.length > 0)
                    {
                        int taskNumber = Integer.parseInt(reply[1]);
                        if (dataList[taskNumber - 1] == null)
                        {
                            System.out.println(horizontal);
                            System.out.println("Oh no! You have entered invalid number. Please enter again! ");
                            System.out.println(horizontal);
                            continue;
                        }
                        dataList[taskNumber - 1].markAsNotDone();
                        System.out.println(horizontal);
                        System.out.println("Ok, I've marked this task as not done yet:");
                        statement = String.format("%s", dataList[taskNumber - 1]);
                        System.out.println(statement);
                    }
                    System.out.println(horizontal);
                    break;

                case "todo":
                    dataList[dataIndex] = new Todo(reply[1]);
                    dataIndex ++;
                    System.out.println(horizontal);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(dataList[dataIndex - 1]);
                    System.out.println("Now you have " + dataIndex + " tasks in the list.");
                    System.out.println(horizontal);
                    break;

                case "deadline":
                    String[] deadlineParts = reply[1].split(" /by ");
                    dataList[dataIndex] = new Deadline(deadlineParts[0], deadlineParts[1]);
                    dataIndex++;
                    System.out.println(horizontal);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(dataList[dataIndex - 1]);
                    System.out.println("Now you have " + dataIndex + " tasks in the list.");
                    System.out.println(horizontal);
                    break;

                case "event":
                    String[] eventParts = reply[1].split(" /from | /to ");
                    dataList[dataIndex] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    dataIndex++;
                    System.out.println(horizontal);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(dataList[dataIndex - 1]);
                    System.out.println("Now you have " + dataIndex + " tasks in the list.");
                    System.out.println(horizontal);
                    break;

                default:
                    dataList[dataIndex] = new Task(userInput);
                    dataIndex ++;
                    System.out.println(horizontal);
                    System.out.printf("added: %s%n", userInput);
                    System.out.println(horizontal);
                    break;
            }
        }
    }
}
