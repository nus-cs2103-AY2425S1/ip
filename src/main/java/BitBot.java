import javax.crypto.spec.PSource;
import java.util.*;

// adapted from the partial solution in the question
class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String finalString() {
        return isDone
                ? "[X] " + taskDescription
                : "[ ] " + taskDescription;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

}

// adapted from the partial solution in the question
class Deadline extends Task {

    protected String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String finalString() {
        return "[D]" + super.finalString() + " (by: " + by + ")";
    }
}

class ToDos extends Task {

    public ToDos (String description) {
        super(description);
    }

    @Override
    public String finalString() {
        return "[T]" + super.finalString();
    }
}

class Events extends Task {

    protected String from, to;

    public Events (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String finalString() {
        return "[E]" + super.finalString() + " (from: " + from + " to: " + to + ")";
    }
}
public class BitBot {

    public static void main(String[] args) {

        ArrayList<Task> arrayList = new ArrayList<>();

        String inputData;
        String intro = "          ____________________________________ \n          Hello! I'm BitBot \n          What can I do for you? \n          ____________________________________";
        String conclusion = "          ____________________________________ \n          Bye. Hope to see you again soon! \n          ____________________________________ \n ";

        //printing out the intro
        System.out.println(intro);
        //creating a scanner
        Scanner sc = new Scanner(System.in);

        while (true) {
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            StringBuilder by = new StringBuilder();
            String keyWord;
            String textPart = null;
            StringBuilder sb = new StringBuilder();

            // to get the integer number for mark and unmark
            int numberPart = 0;
            // to store the index when /from is present so that
            // it aids me in getting the description
            int indexFrom = -1;
            // to store the index when /by is present so that
            // it aids be in getting the description
            int indexBy = -1;

            // reading each line
            inputData = sc.nextLine();

            String[] partsOfInput = inputData.split(" ");
            System.out.println(Arrays.toString(partsOfInput));

            /* this is to check if there is any number in the input.
             * I am splitting the input into parts, and then
             * I am doing a check. If the last element is an integer,
             * I will go ahead and save it as an integer. If not,
             * it will be made into a string using StringBuilder.
             * The .trim() is used to remove the trailing spaces.
             */
            if (partsOfInput.length > 0) {
                keyWord = partsOfInput[0];

                if (Objects.equals(keyWord, "mark") || Objects.equals(keyWord, "unmark")) {
                    String lastPart = partsOfInput[partsOfInput.length - 1];
                    numberPart = Integer.parseInt(lastPart);

                }
                // this is to check if the keyword is "event".
                // if so, get the different parts accurately.
                else if (Objects.equals(keyWord, "event")) {
                    for (int i = 0; i < partsOfInput.length; i++) {
                        /* if the value == "/from", then I am storing the value of the index
                         * in indexFrom. Then I am running another for loop to check
                         * the location where the /to is present. In the middle,
                         * I am adding the strings needed for from using the StringBuilder.
                         * The same goes for /to. Once I find the /to, I will iterate
                         * till the end of the list and then use the to StringBuilder to
                         * build the string required.
                         */
                        if (partsOfInput[i].equals("/from")) {
                            indexFrom = i;
                            for (int j = i + 1; j < partsOfInput.length; j++) {
                                if (partsOfInput[j].equals("/to")) {
                                    break;
                                }
                                if (!from.isEmpty()) {
                                    from.append(" ");
                                }
                                from.append(partsOfInput[j]);
                            }
                        } else if (partsOfInput[i].equals("/to")) {
                            for (int j = i + 1; j < partsOfInput.length; j++) {
                                if (!to.isEmpty()) {
                                    to.append(" ");
                                }
                                to.append(partsOfInput[j]);
                            }
                        }
                    }
                    // finally I am getting the required part for the description using the indexFrom stored earlier.
                    for (int i = 1; i < indexFrom; i++) {
                        sb.append(partsOfInput[i]).append(" ");
                    }
                    textPart = sb.toString().trim();

                }
                // the same concept as above for the keyword "deadline"
                else if (Objects.equals(keyWord, "deadline")) {
                    for (int i = 0; i < partsOfInput.length; i++) {
                        if (partsOfInput[i].equals("/by")) {
                            indexBy = i;
                            for (int j = i + 1; j < partsOfInput.length; j++) {
                                if (!by.isEmpty()) {
                                    by.append(" ");
                                }
                                by.append(partsOfInput[j]);
                            }
                        }
                    }
                    for (int i = 1; i < indexBy; i++) {
                        sb.append(partsOfInput[i]).append(" ");
                    }
                    textPart = sb.toString().trim();

                }
                // if it does not fall in any of this keyword,
                // just add it normally.
                else {
                    for (int i = 1; i < partsOfInput.length; i++) {
                        sb.append(partsOfInput[i]).append(" ");
                    }
                    textPart = sb.toString().trim();
                }
            } else {
                textPart = inputData.trim();
                keyWord = textPart;
            }

            // this is to help check if the size is more than 1 or not
            // so that I can use the correct term (either "task" or "tasks")
            String task = "";
            if (arrayList.size() > 0) {
                task = "tasks";
            } else {
                task = "task";
            }

            // I am using a switch and case method to deal with the user input
            // this is because the user can key in list / bye / mark / unmark / any other input which
            // the scanner will read.
            switch (keyWord) {
                case "bye":
                    break;
                case "list":
                    System.out.println("          ____________________________________\n          Here are the tasks in your list: ");
                    for (int i = 1; i < arrayList.size() + 1; i++) {
                        System.out.println("          " + i + ". " + arrayList.get(i - 1).finalString());
                    }
                    System.out.println("          ____________________________________ \n");
                    break;

                case "mark":
                    arrayList.get(numberPart - 1).markAsDone();
                    System.out.println("          ____________________________________\n          Nice! I've marked this task as done: ");
                    System.out.println("             " + arrayList.get(numberPart - 1).finalString());
                    System.out.println("          ____________________________________\n");
                    break;

                case "unmark":
                    arrayList.get(numberPart - 1).markAsUndone();
                    System.out.println("          ____________________________________\n          OK, I've marked this task as not done yet: ");
                    System.out.println("             " + arrayList.get(numberPart - 1).finalString());
                    System.out.println("          ____________________________________\n");
                    break;

                case "todo":
                    Task toDos = new ToDos(textPart);
                    arrayList.add(toDos);
                    System.out.println("          ____________________________________\n          Got it. I've added this task: \n"
                            + "             " + toDos.finalString() + "\n"
                            + "          Now you have " + arrayList.size() + " " + task + " in the list. \n"
                            + "          ____________________________________\n");

                    break;

                case "deadline":
                    Task deadline = new Deadline(textPart, by.toString().trim());
                    arrayList.add(deadline);
                    System.out.println("          ____________________________________\n          Got it. I've added this task: \n"
                            + "             " + deadline.finalString() + "\n"
                            + "          Now you have " + arrayList.size() + " " + task + " in the list. \n"
                            + "          ____________________________________");

                    break;

                case "event":

                    Task event = new Events(textPart, from.toString().trim(), to.toString().trim());
                    arrayList.add(event);
                    System.out.println("          ____________________________________\n          Got it. I've added this task: \n"
                            + "             " + event.finalString() + "\n"
                            + "          Now you have " + arrayList.size() + " " + task + " in the list. \n"
                            + "          ____________________________________");

                    break;

                default:
                    // if the user adds in anything else that is not either list or bye or mark or unmark,
                    // it will add it into the list.
                    Task t = new Task(inputData);
                    arrayList.add(t);
                    System.out.println("          ____________________________________ \n "
                            + "          added: " + textPart
                            + "\n          ____________________________________");
                    break;

            }
            // if the user keys in "bye" break out of the while loop and print out the conclusion.
            if (Objects.equals(inputData, "bye")) {
                break;
            }
        }

        System.out.println(conclusion);


    }
}

