import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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
            String textPart;
            StringBuilder sb = new StringBuilder();
            int numberPart = 0;

            // reading each line
            inputData = sc.nextLine();

            String[] partsOfInput = inputData.split(" ");

            /* this is to check if there is any number in the input.
             * I am splitting the input into parts, and then
             * I am doing a check. If the last element is an integer,
             * I will go ahead and save it as an integer. If not,
             * it will be made into a string using StringBuilder.
             * The .trim() is used to remove the trailing spaces.
             */
            if (partsOfInput.length > 0) {
                String lastPart = partsOfInput[partsOfInput.length - 1];

                try {
                    numberPart = Integer.parseInt(lastPart);

                    for (int i = 0; i < partsOfInput.length - 1; i++) {
                        sb.append(partsOfInput[i]).append(" ");
                    }
                    textPart = sb.toString().trim();
                } catch (NumberFormatException e) {
                    textPart = inputData.trim();
                }
            } else {
                textPart = inputData.trim();
            }

            // I am using a switch and case method to deal with the user input
            // this is because the user can key in list / bye / mark / unmark / any other input which
            // the scanner will read.
            switch (textPart) {
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

