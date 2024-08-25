import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class for making sense of user command and calling the relevant function
 */
public class Parser {


    public void parseCommand(String input, Scanner scanner, ArrayList<Task> arrayList, Ui ui, Storage storage) {

        if (input.equalsIgnoreCase("bye")) {
            ui.displayBye();
        } else if (input.equalsIgnoreCase("list")) {
            ui.displayTasks(arrayList);
        } else if (input.toLowerCase().startsWith("mark")) {
            handleMark(input, arrayList, ui);
        } else if (input.toLowerCase().startsWith("unmark")) {
            handleUnmark(input, arrayList, ui);
        } else if (input.toLowerCase().startsWith("delete")) {
            handleDelete(input, arrayList, ui);
        } else if (input.toLowerCase().startsWith("add")) {
            handleAdd(input, scanner, arrayList, ui);
        } else {
            ui.displayError("Hoshi doesn't understand, try a different input?");
        }
    }

    private void handleMark(String input, ArrayList<Task> arrayList, Ui ui) {

        if (input.trim().length() < 5) {

            ui.displayTaskToMark();

        } else {

            String trimmedInput = input.trim();

            char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

            // get only the number from the 2nd half of the splitInput
            int markIndex = Character.getNumericValue(taskNo) - 1;

            try {

                // if specified index is not out of bounds
                if (markIndex <= arrayList.size() - 1) {

                    arrayList.get(markIndex).setIsDone(true);
                    ui.displayTaskMarked(arrayList.get(markIndex));

                } else {
                    throw new HoshiException("Hoshi doesn't have such a task!");
                }


            } catch (HoshiException e) {
                ui.displayError(e.getMessage());
            }

        }
    }


    private void handleUnmark(String input, ArrayList<Task> arrayList, Ui ui) {

        if (input.trim().length() < 7) {
            ui.displayTaskToMark();

        } else {

            String trimmedInput = input.trim();

            char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

            // get only the number from the 2nd half of the splitInput
            int markIndex = Character.getNumericValue(taskNo) - 1;

            try {

                // if specified index is not out of bounds
                if (markIndex <= arrayList.size() - 1) {

                    // set isDone to false
                    arrayList.get(markIndex).setIsDone(false);

                    ui.displayTaskMarked(arrayList.get(markIndex));

                } else {
                    throw new HoshiException("Hoshi doesn't have such a task! \n");
                }

            } catch (HoshiException e) {
                ui.displayError(e.getMessage());
            }
        }
    }

    private void handleDelete(String input, ArrayList<Task> arrayList, Ui ui) {

        if (input.length() < 7) {
            ui.displayTaskToDelete();
        } else {

            String trimmedInput = input.trim();

            char taskNo = trimmedInput.charAt(trimmedInput.length() - 1);

            // get only the number from the 2nd half of the splitInput
            int markIndex = Character.getNumericValue(taskNo) - 1;

            arrayList.remove(markIndex);
            ui.displayTaskDeleted(arrayList.get(markIndex));

        }
    }

    /**
     * Adds either to do/deadline/event tasks that are described by the user to ArrayList which is to be written to a
     * txt file later
     *
     * @param input  String that represents general user input before add task details are required.
     * @param scanner Scanner that allows user input to be read.
     * @param arrayList ArrayList of 3 types of tasks that will be added to in this method.
     */
    private static void handleAdd(String input, Scanner scanner, ArrayList<Task> arrayList, Ui ui) {

        if (input.trim().length() < 4) {

            ui.displayTaskAdd();

        } else {

            String[] splitInput = input.split(" ");

            String taskInput = splitInput[1];

            switch (taskInput) {
            case "todo" -> {

                ui.displayTodoTask();
                String desc = scanner.nextLine();

                try {

                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    Todo newToDo = new Todo(desc);
                    arrayList.add(newToDo);
                    ui.displayTaskAdded(input);

                } catch (HoshiException e) {
                    ui.displayError(e.getMessage());
                }

            }
            case "deadline" -> {

                ui.displayDeadlineTask();
                String desc = scanner.nextLine();

                try {

                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    ui.displayDeadlineDue();

                    // take in input
                    String endTime = scanner.nextLine();

                    LocalDate dateTime = LocalDate.parse(endTime);

                    Deadline newDeadline = new Deadline(desc, dateTime);
                    arrayList.add(newDeadline);
                    ui.displayTaskAdded(input);

                } catch (HoshiException e) {
                    ui.displayError(e.getMessage());
                } catch (DateTimeParseException e) {
                    ui.displayError("Hoshi doesn't understand! Try YYYY-MY-DD format");
                }


            }
            case "event" -> {

                ui.displayEventTask();
                String desc = scanner.nextLine();

                try {
                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    ui.displayEventStart();

                    // take in input
                    String startTime = scanner.nextLine();

                    LocalDate dateTimeStart = LocalDate.parse(startTime);

                    ui.displayEventEnd();

                    // take in input
                    String endTime = scanner.nextLine();

                    LocalDate dateTimeEnd = LocalDate.parse(endTime);

                    Event newEvent = new Event(desc, dateTimeStart, dateTimeEnd);
                    arrayList.add(newEvent);
                    ui.displayTaskAdded(input);


                } catch (HoshiException e) {
                    ui.displayError(e.getMessage());
                } catch (DateTimeParseException e) {
                    ui.displayError("Hoshi doesn't understand! Try YYYY-MY-DD format");
                }


            }
            default ->

                // in event of invalid input
                    ui.displayError("Hoshi doesn't understand! Please try again with the above keywords");

            }

        }

    }



}
