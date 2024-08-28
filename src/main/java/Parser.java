public class Parser {

    public Commands parseCommand(String userInput) {
        if (userInput.equals("bye")) {
            return Commands.BYE;
        } else if (userInput.equals("list")) {
            return Commands.LIST;
        } else if (userInput.startsWith("mark")) {
            return Commands.MARK;
        } else if (userInput.startsWith("unmark")) {
            return Commands.UNMARK;
        } else if (userInput.startsWith("todo")) {
            return Commands.TODO;
        } else if (userInput.startsWith("deadline")) {
            return Commands.DEADLINE;
        } else if (userInput.startsWith("event")) {
            return Commands.EVENT;
        } else if (userInput.startsWith("delete")) {
            return Commands.DELETE;
        } else {
            return Commands.OTHERS;
        }
    }

    public void markCommand(TaskList tasks, String userInput, Storage storage) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            tasks.get(index).mark();
            storage.saveTasks(tasks.getTasks());
            System.out.println("________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index));
            System.out.println("________________________________");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("________________________________");
            System.out.println("Invalid task number! Please enter a valid task number, between 1 - " + tasks.size());
            System.out.println("________________________________");
        }
    }

    public void unmarkCommand(TaskList tasks, String userInput, Storage storage) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            tasks.get(index).unmark();
            storage.saveTasks(tasks.getTasks());
            System.out.println("________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index));
            System.out.println("________________________________");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("________________________________");
            System.out.println("Invalid task number! Please enter a valid task number, between 1 - " + tasks.size());
            System.out.println("________________________________");
        }
    }

    public void todoCommand(TaskList tasks, String userInput, Storage storage) {
        try {
            String todoDescription = userInput.substring(5).trim();
            if (todoDescription.isEmpty()) {
                throw new YapperBotException("The description for a Todo task cannot be empty.");
            }
            tasks.addTask(new Todo(todoDescription));
            storage.saveTasks(tasks.getTasks());
            System.out.println("________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
            System.out.println("________________________________");
        } catch (YapperBotException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage());
            System.out.println("________________________________");
        }
    }

    public void deadlineCommand(TaskList tasks, String userInput, Storage storage) {
        try {
            String[] deadlineInput = userInput.substring(9).split(" /by ");
            if (deadlineInput.length < 2 || deadlineInput[0].trim().isEmpty()) {
                throw new YapperBotException("You need to provide a description with a deadline "
                        + "in the following formats:\n"
                        + "Eg. deadline play bball /by 2/12/2024 1800 or deadline return book /by 2024-12-02");
            }
            String deadlineDescription = deadlineInput[0];
            String by = deadlineInput[1];
            tasks.addTask(new Deadline(deadlineDescription, by));
            storage.saveTasks(tasks.getTasks());
            System.out.println("________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
            System.out.println("________________________________");
        } catch (YapperBotException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage());
            System.out.println("________________________________");
        }
    }

    public void eventCommand(TaskList tasks, String userInput, Storage storage) {
        try {
            String[] eventInput = userInput.substring(6).split(" /from | /to ");
            if (eventInput.length < 3 || eventInput[0].trim().isEmpty()) {
                throw new YapperBotException("You need to provide a description with a time frame "
                        + "in the following format:\n"
                        + "Eg. event play bball /from 2/12/2024 1400 /to 2/12/2024 1600");
            }
            String eventDescription = eventInput[0];
            String from = eventInput[1];
            String to = eventInput[2];
            tasks.addTask(new Event(eventDescription, from, to));
            storage.saveTasks(tasks.getTasks());
            System.out.println("________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("________________________________");
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        } catch (YapperBotException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage());
            System.out.println("________________________________");
        }
    }

    public void deleteCommand(TaskList tasks, String userInput, Storage storage) {
        try {
            int deleteTaskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task deleteTargetTask = tasks.get(deleteTaskNumber);
            tasks.deleteTask(deleteTargetTask);
            storage.saveTasks(tasks.getTasks());
            System.out.println("________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + deleteTargetTask);
            System.out.println("________________________________");
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("________________________________");
            System.out.println("Invalid task number! Please enter a valid task number, between 1 - " + tasks.size());
            System.out.println("________________________________");
        }
    }
}

