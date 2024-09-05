import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> userInputs = new ArrayList<>();

    private static void addItem(String inp) {
        userInputs.add(new Task(inp));
    }

    private static void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<userInputs.size(); i++) {
            System.out.println((i+1) + "." + userInputs.get(i));
        }
    }

    private static void taskAddOrDeleteDisplay(Task task, String addOrDelete) {
        System.out.println("Got it. I've" + addOrDelete + "ed this task:");
        System.out.println(task);
        System.out.println("Now you have " + userInputs.size() + " tasks in the list.");
    }

    public static void processInput(String inp) throws EmptyTaskException, InvalidInstructionException, EmptyCommandException {
        String instruction = inp.split(" ",2)[0];
        if (instruction.equals("list")) {
            display();
            return;
        }
        if (inp.split(" ", 2).length==0) {
            throw new EmptyCommandException();
        }
        boolean flag1 = !(Arrays.asList("list", "mark", "unmark", "todo", "event", "deadline", "delete").contains(inp.split(" ",2)[0]));
        if (inp.split(" ", 2).length==1 && flag1) {
            throw new InvalidInstructionException();
        }
        boolean flag2 = Arrays.asList("todo", "event", "deadline", "delete").contains(inp.split(" ",2)[0]);
        if (inp.split(" ",2).length==1 && flag2) {
            throw new EmptyTaskException();
        }
        String remainingInput = inp.split(" ",2)[1];
        if (instruction.equals("mark")) {
            int idx = Integer.parseInt(remainingInput)-1;
            userInputs.get(idx).setDone(true);
            System.out.println("Nice! I've marked this task as done:\n" +
                    userInputs.get(idx));
        } else if (instruction.equals("unmark")) {
            int idx = Integer.parseInt(remainingInput)-1;
            userInputs.get(idx).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n" +
                    userInputs.get(idx));
        } else if (instruction.equals("todo")){
            Todo task = new Todo(remainingInput);
            userInputs.add(task);
            taskAddOrDeleteDisplay(task, "add");
        } else if (instruction.equals("deadline")) {
            String name = remainingInput.split(" /by ", 2)[0];
            String endDate = remainingInput.split(" /by ", 2)[1];
            Deadline task = new Deadline(name, endDate);
            userInputs.add(task);
            taskAddOrDeleteDisplay(task, "add");
        } else if (instruction.equals("event")) {
            String name = remainingInput.split(" /from ", 2)[0];
            remainingInput = remainingInput.split(" /from ", 2)[1];
            String start = remainingInput.split(" /to ", 2)[0];
            String end = remainingInput.split(" /to ", 2)[1];
            Event task = new Event(name, start, end);
            userInputs.add(task);
            taskAddOrDeleteDisplay(task, "add");
        } else if (instruction.equals("delete")) {
            int idx = Integer.parseInt(remainingInput)-1;
            Task taskToBeDeleted = userInputs.get(idx);
            userInputs.remove(idx);
            taskAddOrDeleteDisplay(taskToBeDeleted, "delet");
        } else {
            throw new InvalidInstructionException();
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String logo = " ,--.--------.    ,----.    ,-,--.             ,---.      \n" +
                      "/==/,  -   , -\\,-.--` , \\ ,-.'-  _\\  _.-.    .--.'  \\     \n" +
                      "\\==\\.-.  - ,-./==|-  _.-`/==/_ ,_.'.-,.'|    \\==\\-/\\ \\    \n" +
                      " `--`\\==\\- \\  |==|   `.-.\\==\\  \\  |==|, |    /==/-|_\\ |   \n" +
                      "      \\==\\_ \\/==/_ ,    / \\==\\ -\\ |==|- |    \\==\\,   - \\  \n" +
                      "      |==|- ||==|    .-'  _\\==\\ ,\\|==|, |    /==/ -   ,|  \n" +
                      "      |==|, ||==|_  ,`-._/==/\\/ _ |==|- `-._/==/-  /\\ - \\ \n" +
                      "      /==/ -//==/ ,     /\\==\\ - , /==/ - , ,|==\\ _.\\=\\.-' \n" +
                      "      `--`--``--`-----``  `--`---'`--`-----' `--`         \n" +
                      "\n";
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm\n" + logo +
                "How may I be of service to you in this moment?\n" +
                "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();
        while (!inp.equals("bye")) {
            try {
                processInput(inp);
            } catch (EmptyTaskException e) {
                System.out.println("The description of the task must contain some substance; it cannot be void.");
            } catch (EmptyCommandException e) {
                System.out.println("An empty command has been received.");
            } catch (InvalidInstructionException e) {
                System.out.println("The instruction provided is deemed invalid.");
            }
            inp = scanner.nextLine();
        }
        System.out.println("Farewell! Until we meet again.\n");
    }
}
