import java.util.Scanner;

public class Kat {

    private static final String LOGO = """
             _         _  \s
            | | __ ___| |_\s
            | |/ / _  | __|
            |   < (_| | |_\s
            |_|\\_\\____|\\__|
            """;

    private static final Scanner scanner = new Scanner(System.in);

    private static final TaskStorage storage = new TaskStorage();

    public static void main(String[] args) {
        // Todo: Refactor out to separate class / methods
        System.out.println("Hello from\n" + LOGO);

        respond("Hi! I'm Kat.\nHow can I help?");

        while (true) {
            System.out.println("> Me");

            String inputMsg = scanner.nextLine().trim();
            if (inputMsg.equalsIgnoreCase("bye")) {
                break;
            }

            if (inputMsg.equalsIgnoreCase("list")) {
                respond("Here are your tasks:\n" + storage);
                continue;
            }

            // Todo: Handle invalid inputs
            if (inputMsg.startsWith("mark")) {
                int taskIdx = Integer.parseInt(inputMsg.split(" ")[1]) - 1;
                storage.setTaskAsDone(taskIdx);
                respond("Nice! Done:\n" + storage.getTask(taskIdx).toString() + "\n");
                continue;
            }

            if (inputMsg.startsWith("unmark")) {
                int taskIdx = Integer.parseInt(inputMsg.split(" ")[1]) - 1;
                storage.setTaskAsNotDone(taskIdx);
                respond("Ah! Not done:\n" + storage.getTask(taskIdx).toString());
                continue;
            }

            if (inputMsg.startsWith("todo")) {
                String taskName = inputMsg.replaceFirst("todo ", "");
                Task task = new ToDoTask(taskName);
                storage.addTask(task);
                respond(String.format("Got it. Task saved:\n%s\n%d tasks in the list.", task, storage.getSize()));
                continue;
            }

            if (inputMsg.startsWith("deadline")) {
                String inputMsgWithoutPrefix = inputMsg.replaceFirst("deadline ", "");
                String[] splitInputMsg = inputMsgWithoutPrefix.split("/by");
                Task task = new DeadlineTask(splitInputMsg[0].trim(), splitInputMsg[1].trim());
                storage.addTask(task);
                respond(String.format("Got it. Task saved:\n%s\n%d tasks in the list.", task, storage.getSize()));
                continue;
            }

            if (inputMsg.startsWith("event")) {
                String inputMsgWithoutPrefix = inputMsg.replaceFirst("deadline ", "");
                String[] splitInputMsg = inputMsgWithoutPrefix.split("/from|/to");
                Task task = new EventTask(splitInputMsg[0].trim(), splitInputMsg[1].trim(), splitInputMsg[2].trim());
                storage.addTask(task);
                respond(String.format("Got it. Task saved:\n%s\n%d tasks in the list.", task, storage.getSize()));
                continue;
            }
        }

        respond("See you!");
    }

    private static void respond(String responseMsg) {
        System.out.println("~".repeat(50));
        System.out.println("> Kat");
        System.out.println(responseMsg);
        System.out.println("~".repeat(50));
    }

}
