import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is a chatbot class named Bob.
 */
public class Bob {
    private ArrayList<Task> records;
    private int counter;

    /**
     * Initialises an instance of Bob.
     */
    public Bob() {
        this.records = new ArrayList<>();
        this.counter = 0;
    }

    public static void main(String[] args)  {
        String welcome = "Hello! I'm Bob\n"
                + "\tWhat can I do for you?";
        Bob.printLines(welcome);
        Bob bob = new Bob();
        Bob.chat(bob);
    }

    /**
     * This is a chat function by Bob.
     */
    static void chat(Bob bob)  {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim(); //input with NO whitespace in front/back

        while (!input.equals("bye")) {
            String[] inputWords = input.split("\s+");
            String keyword = inputWords[0];

            switch (keyword) {
                case "list":
                    bob.listRecords();
                    break;
                case "mark":
                    bob.updateMark(input, inputWords, true);
                    break;
                case "unmark":
                    bob.updateMark(input, inputWords, false);
                    break;
                default:
                    bob.addTask(input, inputWords);
            }

            input = scanner.nextLine().trim();
        }
        printLines("Bye. Hope to see you again soon!");
    }

    /**
     * Updates whether the task in the record is completed or not completed.
     * @param inputWords String array of the words given as input.
     * @param isCompleted Whether the task is marked as completed or incompleted.
     */
    public void updateMark(String input, String[] inputWords, Boolean isCompleted) {
        if (inputWords.length == 1) {
            System.out.println("Please input which item number you want to mark.");
        } else if (this.records.size() < Integer.valueOf(inputWords[1]) || Integer.valueOf(inputWords[1]) <= 0) {
            System.out.println("Item index out of range.");
        } else {
            Task currTask = this.records.get(Integer.parseInt(inputWords[1]) - 1);
//            Task currTask = this.getTask(inputWords[0], input);
            if (isCompleted) {
                currTask.markTask(true);
            } else {
                currTask.markTask(false);
            }
        }
    }

    /**
     * Adds a task to records.
     * @param input Input given by a user.
     */
    public void addTask(String input, String[] inputWords )  {
        try {
            String keyword = inputWords[0];
            Task newTask = getTask(keyword, input); //initialise the exact Task class
            String immediateAdd = "Got it. I've added this task:\n\t"
                    + "  ["
                    + newTask.taskLetter()
                    + "][ ] "
                    + this.getInputDescription(input)
                    + "\n\t"
                    + "Now you have "
                    + (String.valueOf(counter + 1))
                    + " tasks in the list.";

            Bob.printLines(immediateAdd);
            this.records.add(this.counter, newTask);
            this.setCounter(this.counter + 1);
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns the actual task type instance.
     *
     * @param keyword
     * @param input
     * @return
     */
    public Task getTask(String keyword, String input) throws InvalidTaskException {
        Task newTask = new Task("");
        String[] inputWords = input.split("\s+");
        String taskDescription = this.getInputDescription(input); //gets the specific task description based on keyword.
        switch (keyword) {
            case "todo":
                if (taskDescription.equals("")) {
                    throw new InvalidTaskException("OOPS!!! The description of a todo cannot be empty.");
                }
                newTask = new Todo(taskDescription);
                break;
            case "deadline":
                if (taskDescription.equals("")) {
                    throw new InvalidTaskException("OOPS!!! The description of a deadline cannot be empty.");
                }

                String deadline = inputWords[Arrays.asList(inputWords).indexOf("/by") + 1];

                newTask = new Deadline(taskDescription, deadline);
                break;
            case "event":
                if (taskDescription.equals("")) {
                    throw new InvalidTaskException("OOPS!!! The description of a event cannot be empty.");
                }

                String startDateTime = inputWords[Arrays.asList(inputWords).indexOf("/from") + 1];
                String endDateTime = inputWords[Arrays.asList(inputWords).indexOf("/to") + 1];
                newTask = new Event(taskDescription, startDateTime, endDateTime);
                break;
            default:
//                newTask = new Task(taskDescription);
                throw new InvalidTaskException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return newTask;
    }

    /**
     * Returns String representation of the Task's description only.
     * This description includes the task specific details.
     *
     * @param input original input given by the user.
     */
    public String getInputDescription(String input) throws InvalidTaskException {
        String[] separateKeyword = input.split(" ", 2); //separate the keyword from the rest of string
        switch (separateKeyword[0]) {
            case "todo":
                if (separateKeyword.length == 1) {
                    throw new InvalidTaskException("OOPS!!! The description of todo cannot be empty.");
                }
                return separateKeyword[1];
            case "deadline":
                if (separateKeyword.length == 1) {
                    throw new InvalidTaskException("OOPS!!! The description of deadline cannot be empty.");
                }
                String[] subString1 = separateKeyword[1].split("/by");
                if (subString1.length <= 1) {
                    throw new InvalidTaskException("Invalid use of deadline. Should be '... /by ...'.");
                }
                return subString1[0].trim() + " (by:" + subString1[1] + ")";
            case "event":
                if (separateKeyword.length == 1) {
                    throw new InvalidTaskException("OOPS!!! The description of event cannot be empty.");
                }

                String[] subString2 = separateKeyword[1].split("/from");
                if (subString2.length <= 1) {
                    if (subString2.length == 0) {
                        throw new InvalidTaskException("OOPS!!! The event description cannot be empty.");
                    }
                    throw new InvalidTaskException("Invalid use of event format. Should be  '... /from ...'");
                }

                String[] subString3 = subString2[1].split("/to");
                if (subString3.length <= 1) {
                    throw new InvalidTaskException("Invalid use of event format. Should be '... /to ...'.");
                }
                if (subString3[0].trim().isEmpty()) {
                    throw new InvalidTaskException("OOPS!!! The start time for the event cannot be empty.");
                }
                if (subString3[1].trim().isEmpty()) {
                    throw new InvalidTaskException("OOPS!!! The end time for the event cannot be empty.");
                }

                return subString2[0].trim() + " (from:" + subString3[0] + " to:" + subString3[1] + ")";
            default:
                return input;
        }

    }

    /**
     * Prints the text input with lines above and below it.
     * @param text The user input into the chatbot
     */
    static void printLines(String text) {
        String textToPrint = "\t____________________________________________________________\n"
                + "\t"
                + text
                + "\n"
                + "\t____________________________________________________________\n";
        System.out.println(textToPrint);
    }

    /**
     * Returns the index of the next available slot in the arraylist of records.
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Sets the count of the total number of recorded items in records.
     */
    public void setCounter(int x) {
        this.counter = x;
    }

    /**
     * Prints a list of all recorded user inputs.
     */
    public void listRecords() {
        String allRecords = "Here are the tasks in your list:\n\t";
        for (int i = 0; i < records.size(); i++) {
            int num = i + 1;
            Task currTask = records.get(i);
            if (num == records.size()) {
                allRecords += num + "." + currTask.getTaskListItem();
            } else {
                allRecords += num + "." + currTask.getTaskListItem() + "\n\t";
            }
        }
        Bob.printLines(allRecords);
    }
}
