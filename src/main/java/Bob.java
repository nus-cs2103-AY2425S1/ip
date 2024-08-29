import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is a chatbot class named Bob.
 */
public class Bob {
    private ArrayList<Task> records;
    private int counter;

    private String savedFilePath;

    /**
     * Initialises an instance of Bob.
     */
    public Bob() {
        this.records = new ArrayList<>();
        this.counter = 0;
        this.savedFilePath = "src/main/java/savedFile.txt";
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
        bob.loadTasks();
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
                case "delete":
                    bob.delete(input);
                    break;
                default:
                    bob.addTask(input, inputWords);
            }
            bob.saveRecords();
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
//            for (String x : inputWords) {
//                System.out.println(x);
//            }
            if (!inputWords[Arrays.asList(inputWords).indexOf("/from") + 3].equals("/to")) {
                throw new InvalidTaskException("Invalid use of event format. Should be  '<description> /from <day> <start time>'");
            }
            String day = inputWords[Arrays.asList(inputWords).indexOf("/from") + 1];
            String startTime = inputWords[Arrays.asList(inputWords).indexOf("/from") + 2];
            String endTime = inputWords[Arrays.asList(inputWords).indexOf("/to") + 1];
            newTask = new Event(taskDescription, day, startTime, endTime);
            break;
        default:
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
                throw new InvalidTaskException("Invalid use of event format. Should be  '<description> /from <day> <start time>'");
            }

            String[] subString3 = subString2[1].split("/to");
            if (subString3.length <= 1) {
                throw new InvalidTaskException("Invalid use of event format. Should be '<day> <start time> /to <end time>'.");
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
     * Checks if the value given is a valid task number in the current records of tasks.
     *
     * @param value Input after the keyword.
     * @return Whether the input when converted to int is a valid task number.
     */
    public boolean isValidTaskNumber(String value) {
        if (value.trim().isEmpty() || value == null) {
            return false;
        }
        if (Integer.valueOf(value) <= 0 ||Integer.valueOf(value) > this.records.size()) {
            return false;
        }
        for (char c: value.trim().toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;

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
     * Remove the task from records, based on the task's index in records.
     * @param input Input given by the user.
     */

    public void delete(String input) {
        try {
            String[] separateKeyword = input.split(" ", 2); //separate the keyword from the rest of string
            if (separateKeyword.length == 1) {
                throw new InvalidTaskException("OOPS!!! The description of delete cannot be empty.");
            }
            if (!isValidTaskNumber(separateKeyword[1])) {
                throw new InvalidTaskException("Invalid input. Integer required between range of record items.");
            }

            Task taskToDelete = records.get(Integer.parseInt(separateKeyword[1]) - 1);
            String immediateAdd = "Noted. I've removed this task:\n\t"
                    + taskToDelete.getTaskListItem()
                    + "\n\t"
                    + "Now you have "
                    + (String.valueOf(counter - 1))
                    + " tasks in the list.";
            Bob.printLines(immediateAdd);
            this.records.remove(Integer.parseInt((separateKeyword[1]).trim()) - 1);
            this.setCounter(this.counter - 1);
        } catch (InvalidTaskException e) {
            System.err.println((e.getMessage()));
        }
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

    /**
     * Saves tasks updates by user to records.
     */
    public void saveRecords() {
        File file = new File (savedFilePath);
        try {
            if(!file.exists()) {
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    System.out.println("Failed to create new file when saving records.");
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task x: records) {
                String fileFormatLine = x.fileFormat();
                bufferedWriter.write(fileFormatLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            super.toString();
        }
    }

    /**
     * Updates Bob's records and prints out all existing records.
     */
    public void loadTasks() {
        try {
            File file = new File(savedFilePath);
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    System.out.println("Failed to create file when loading task.");
                }
                return;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            System.out.println("\t====== Current Records =====\n");
            while (line != null && !line.equals("")) {
                Task task = this.parseData(line);
                this.records.add(this.counter, task);
                this.setCounter(this.counter + 1);
                System.out.println("\t" + line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            super.toString();
        }
    }

    public Task parseData(String lineInput) throws IOException {
        String[] parts = lineInput.split(" //| ");
        boolean isDone = (parts[1].equals("1")) ? true : false;

        switch (parts[0]) {
        case "T":
            return new Todo(parts[2], isDone);
        case "D":
            return new Deadline(parts[2], parts[3], isDone);
        case "E":
            return new Event(parts[2], parts[3], parts[4], parts[5]);
        default:
            throw new IOException("unable to parse Data for loading.");
        }
    }

}
