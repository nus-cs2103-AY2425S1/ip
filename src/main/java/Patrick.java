import javax.sound.midi.SysexMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Patrick {
    static String HORIZONTAL_LINE = "____________________________________________________________\n";
    static String GREETING_MSG = HORIZONTAL_LINE + "Hello! I'm Patrick, Spongebob bestie\nHow can I help you?\n" + HORIZONTAL_LINE;
    static String EXIT_MSG = HORIZONTAL_LINE + "Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE;
    static String TASK_MSG = "Got it. I've added this task:\n";
    static String NUM_TASK_MSG_1 = "Now you have ";
    static String NUM_TASK_MSG_2 = " tasks in the list.\n";
    static String input;
    static ArrayList<Task> list = new ArrayList<>();
    static Task task;
    public enum Type {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, ERROR
    }
    static Type inputType;
    static File file;
    static String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) {
        Scanner inputMsg = new Scanner(System.in);
        System.out.println(GREETING_MSG);
        loadFile();
        printFileContents();

        do {
            input = inputMsg.nextLine();
            checkType(input);
            switch (inputType) {
            case LIST:
                printFileContents();
                break;

            case BYE:
                System.out.println(EXIT_MSG);
                break;

            case MARK:
                try {
                    mark(input);
                    break;
                } catch (PatrickException e) {
                    System.out.print(HORIZONTAL_LINE + e.toString() + "\n" + HORIZONTAL_LINE);
                }

            case UNMARK:
                try {
                    unmark(input);
                    break;
                } catch (PatrickException e) {
                    System.out.println(HORIZONTAL_LINE + e.toString() + "\n" + HORIZONTAL_LINE);
                }

            case TODO:
                try {
                    toDoTask(input);
                    break;
                } catch (PatrickException e) {
                    System.out.print(HORIZONTAL_LINE + e.toString() + "\n" + HORIZONTAL_LINE);
                }

            case DEADLINE:
                try {
                    deadlineTask(input);
                    break;
                } catch (PatrickException e) {
                    System.out.print(HORIZONTAL_LINE + e.toString() + "\n" + HORIZONTAL_LINE);
                }

            case EVENT:
                try {
                    eventTask(input);
                    break;
                } catch (PatrickException e) {
                    System.out.print(HORIZONTAL_LINE + e.toString() + "\n" + HORIZONTAL_LINE);
                }

            case DELETE:
                try {
                    delete(input);
                    break;
                } catch (PatrickException e) {
                    System.out.print(HORIZONTAL_LINE + e.toString() + "\n" + HORIZONTAL_LINE);
                }

            default:
                System.out.println(HORIZONTAL_LINE + "What are you trying to say man. Re-enter your command \n" + HORIZONTAL_LINE);
                break;
            }
        } while (!inputType.equals(Type.BYE));
    }

    private static void readTasks() throws FileNotFoundException {
        file = new File(FILE_PATH);
        Scanner s = new Scanner(file);
        Task currTask;
        while (s.hasNext()) {
            String taskString = s.nextLine();
            if (taskString.startsWith("T")) {
                String taskDescription = taskString.substring(7);
                currTask = new ToDo(taskDescription);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            } else if (taskString.startsWith("D")) {
                String taskDescription = taskString.substring(7);
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
                String deadline = taskString.substring(7).replace(taskDescription, "").replace("| ", "");
                currTask = new Deadline(taskDescription, deadline);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            } else if (taskString.startsWith("E")) {
                String taskDescription = taskString.substring(7);
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
                String tempFrom = taskString.substring(7).replace(taskDescription, "").substring(2);
                String to = tempFrom.substring(tempFrom.indexOf("-") + 1);
                String from = tempFrom.replace("-" + to, "");
                currTask = new Event(taskDescription, from, to);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            }
        }
    }

    private static void toDoTask(String input) throws PatrickException {
        String taskDescription = input.replace("todo", "");
        if (taskDescription.isEmpty()) {
            throw new PatrickException("Description of a todo cannot be empty!!");
        } else {
            task = new ToDo(taskDescription);
            list.add(task);
            System.out.println(HORIZONTAL_LINE + TASK_MSG + task.toString() + "\n" + NUM_TASK_MSG_1
                    + list.size() + NUM_TASK_MSG_2 + HORIZONTAL_LINE);
            try {
                appendToFile("\n" + task.toString());
            } catch (IOException e) {
                System.out.println("There is an error: " + e.getMessage());
            }
        }
    }

    private static void deadlineTask(String input) throws PatrickException {
        String newInput = input.replace("deadline", "");
        if (newInput.isEmpty()) {
            throw new PatrickException("Deadline Task Details cannot be empty!!");
        }
        else if (!newInput.contains("/by")) {
            throw new PatrickException("You are missing a '/by' in ur details!!");
        }
        else {
            String taskDescription = newInput.substring(0, newInput.indexOf("/") - 1);
            if (taskDescription.isEmpty()) {
                throw new PatrickException("Deadline Task Description cannot be empty!!");
            }
            else {
                String deadline = newInput.substring(newInput.indexOf("/by")).replace("/by", "");
                if (deadline.isEmpty()) {
                    throw new PatrickException("Deadline Task deadline cannot be empty!!");
                }
                else {
                    task = new Deadline(taskDescription, deadline);
                    list.add(task);
                    System.out.println(HORIZONTAL_LINE + TASK_MSG + task.toString() + "\n" + NUM_TASK_MSG_1
                            + list.size() + NUM_TASK_MSG_2 + HORIZONTAL_LINE);
                    try {
                        appendToFile("\n" + task.toString());
                    } catch (IOException e) {
                        System.out.println("There is an error: " + e.getMessage());
                    }
                }
            }
        }
    }

    private static void eventTask(String input) throws PatrickException {
        String newInput = input.replace("event", "");
        if (newInput.isEmpty()) {
            throw new PatrickException("Event Task Details cannot be empty!!");
        } else if (!newInput.contains("/from")) {
            throw new PatrickException("You are missing a '/from' in your details!!");
        } else if (!newInput.contains("/to")) {
            throw new PatrickException("You are missing a 'to' in your details!!");
        } else {
            String taskDescription = newInput.substring(0, newInput.indexOf("/from") - 1);
            if (taskDescription.isEmpty()) {
                throw new PatrickException("Event Task Description cannot be empty!!");
            } else {
                String from = newInput.substring(newInput.indexOf("/from"), newInput.indexOf("/to") - 1).replace("/from", "");
                String to = newInput.substring(newInput.indexOf("/to")).replace("/to", "");
                if (from.isEmpty()) {
                    throw new PatrickException("You are missing 'from' information from your details!!");
                } else if (to.isEmpty()) {
                    throw new PatrickException("You are missing 'to' information from your details!!");
                } else {
                    task = new Event(taskDescription, from, to);
                    list.add(task);
                    System.out.println(HORIZONTAL_LINE + TASK_MSG + task.toString() + "\n" + NUM_TASK_MSG_1
                            + list.size() + NUM_TASK_MSG_2 + HORIZONTAL_LINE);
                    try {
                        appendToFile("\n" + task.toString());
                    } catch (IOException e) {
                        System.out.println("There is an error: " + e.getMessage());
                    }
                }
            }
        }
    }

    private static void mark(String input) throws PatrickException {
        String taskNo = input.replace("mark", "").trim();
        if (taskNo.isEmpty()) {
            throw new PatrickException("Task Number cannot be empty!!");
        } else {
            try {
                Integer.parseInt(taskNo);
            } catch (NumberFormatException e) {
                throw new PatrickException("Mark Task Details must be an integer");
            }
            int num = Integer.parseInt(taskNo);
            if (num > list.size()) {
                throw new PatrickException("Invalid Task Number!!");
            } else {
                Task curr = (Task) list.get(num - 1);
                if (curr.isDone) {
                    throw new PatrickException("You cannot mark a completed task!!");
                } else {
                    curr.markAsDone();
                    System.out.println(HORIZONTAL_LINE + "Nice! I've marked this task as done:\n  "
                            + curr.toString() + "\n" + HORIZONTAL_LINE);
                    try {
                        writeToFile();
                    } catch (IOException e) {
                        System.out.println("There is an error: " + e.getMessage());
                    }
                }
            }
        }
    }

    private static void unmark(String input) throws PatrickException {
        String taskNo = input.replace("unmark", "").trim();
        if (taskNo.isEmpty()) {
            throw new PatrickException("Task Number cannot be empty!!");
        } else {
            try {
                Integer.parseInt(taskNo);
            } catch (NumberFormatException e) {
                throw new PatrickException("Unmark Task Details must be an integer");
            }
            int num = Integer.parseInt(taskNo);

            if (num > list.size()) {
                throw new PatrickException("Invalid Task Number!!");
            } else {
                Task curr = (Task) list.get(num - 1);
                if (!curr.isDone) {
                    throw new PatrickException("You cannot unmark an incomplete task!!");
                } else {
                    curr.markAsUndone();
                    System.out.println(HORIZONTAL_LINE + "Nice! I've marked this task as as not done yet:\n  "
                            + curr.toString() + "\n" + HORIZONTAL_LINE);
                    try {
                        writeToFile();
                    } catch (IOException e) {
                        System.out.println("There is an error: " + e.getMessage());
                    }
                }
            }
        }
    }

    private static void delete(String input) throws PatrickException {
        String taskNo = input.replace("delete", "").trim();
        try {
            Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new PatrickException("Delete Task Details must be an integer");
        }
        int num = Integer.parseInt(taskNo);
        if (taskNo.isEmpty()) {
            throw new PatrickException("Delete Task Details cannot be empty!!");
        }
        else if (num > list.size()){
            throw new PatrickException("Input task index is invalid. Please try again!!");
        }
        else {
            System.out.println(HORIZONTAL_LINE + "Noted. I've removed this task:\n"
                + list.get(num - 1).toString() + "\n" + NUM_TASK_MSG_1 + (list.size() - 1) + NUM_TASK_MSG_2);
            list.remove(num - 1);
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("There is an error: " + e.getMessage());
            }
        }
    }

    private static void checkType(String input) {
        if (input.startsWith("list"))
            inputType = Type.LIST;
        else if (input.startsWith("bye"))
            inputType = Type.BYE;
        else if (input.startsWith("mark"))
            inputType = Type.MARK;
        else if (input.startsWith("unmark"))
            inputType = Type.UNMARK;
        else if (input.startsWith("todo"))
            inputType = Type.TODO;
        else if (input.startsWith("deadline"))
            inputType = Type.DEADLINE;
        else if (input.startsWith("event"))
            inputType = Type.EVENT;
        else if (input.startsWith("delete"))
            inputType = Type.DELETE;
        else
            inputType = Type.ERROR;
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : "O");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return  getStatusIcon() + " |" + this.description;
        }

    }

    public static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "D | " + super.toString() + " |" + this.by;
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "T | " + super.toString();
        }
    }

    public static class Event extends Task {
        protected String from, to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "E | " + super.toString() + " |" + this.from + "-" + this.to;
        }
    }

    public static class PatrickException extends Exception {
        String str;
        public PatrickException(String str) {
            super(str);
        }
    }

    private static void printFileContents() {
        System.out.println(HORIZONTAL_LINE + "Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            Task curr = (Task) list.get(i - 1);
            System.out.println(i + ". " + curr.toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void loadFile() {
        try {
            readTasks();
        } catch (FileNotFoundException e) {
            System.out.println(HORIZONTAL_LINE + "File not found!" + "\n" + HORIZONTAL_LINE);
        }
    }

    private static void appendToFile(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH, true);
        fileWriter.write(text);
        fileWriter.close();
    }

    private static void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i).toString();
            fileWriter.write(temp + "\n");
        }
        fileWriter.close();
    }
}
