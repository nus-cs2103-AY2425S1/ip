import java.util.*;

public class Bot {
    public class Task {
        private final String name;
        private Boolean isDone;

        public Task(String taskName) {
            this.name = taskName;
            this.isDone = false;
        }

        public void changeMark() {
            this.isDone = !isDone;
        }
    }

    private ArrayList<Task> taskList = new ArrayList<>();

    public void acceptCommand() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            List<String> inputList = Arrays.asList(input.split(" "));
            String command = inputList.get(0);
//            System.out.println("inputList size: " + inputList.size());
            String args = String.join(" ", inputList.subList(1, inputList.size()));
//            System.out.println("command: " + command);
//            System.out.println("args: " + args);
//            Consider implementing the 1010X approach to calling functions
//            Watch the data directed programming lecture
            if (Objects.equals(command, "bye")) {
                System.out.println("""
                        ____________________________________________________________
                        yeah bye bye to you too human being
                        ____________________________________________________________
                        """);
                System.exit(0);
            } else if (Objects.equals(command, "list")) {
                showList();
            } else if (Objects.equals(command, "mark")
                    || Objects.equals(command, "unmark")) {
                // need to handle the case where a non-integer is passed to mark
                int taskToMark = Integer.parseInt(args);
                markTask(taskToMark, command);
            } else {
                addToList(input);
            }
        }
    }
    public void addToList(String input) {
        taskList.add(new Task(input));
        System.out.println("____________________________________________________________\n"
                + "added: " + input + "\n"
                + "____________________________________________________________\n");
        acceptCommand();
    }

    public void showList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskStatus = task.isDone
                    ? "X"
                    : " ";
            System.out.println((i+1) + ". [" + taskStatus + "] " + task.name);
        }
        System.out.println("____________________________________________________________");
        acceptCommand();
    }

    public void markTask(int taskToMark, String command) {
        Task task = taskList.get(taskToMark-1);
        task.changeMark();
        if (Objects.equals(command, "mark")) {
            System.out.println("____________________________________________________________\n"
                + "ok i've marked this task as done:\n"
                + "[X] " + task.name + "\n"
                + "____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________\n"
                    + "ok i've unmarked this task:\n"
                    + "[ ] " + task.name + "\n"
                    + "____________________________________________________________");
        }
    }
}
