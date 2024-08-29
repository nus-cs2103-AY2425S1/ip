package poChat.bot;

import poChat.exceptions.TaskDescriptionEmptyException;
import poChat.tasks.Deadline;
import poChat.tasks.Event;
import poChat.tasks.Task;
import poChat.tasks.ToDo;

class Parser {
    private final TaskList taskList;

    private Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public static Parser of(TaskList taskList) {
        return new Parser(taskList);
    }

    /**
     * This method takes in the input and parses it to determine the
     * right response from the chatbot. Also updates the TaskList with any
     * new tasks created.
     *
     * @param textInput the String input from the user
     * @return <code>true</code> if the input is bye and the chat is over, otherwise return
     * <code>false</code> which means the conversation continues
     */

    public boolean parse(String textInput) throws TaskDescriptionEmptyException {
        boolean isChatOver = false;

        if (textInput.equals("bye")) {
            sayGoodbye();
            isChatOver = true;
        } else if (textInput.equals(("list"))) {
            replyWithListOfTextsEntered();
        } else if (textInput.startsWith("mark")) {
            int taskIndex = Integer.parseInt(textInput.substring(5)) - 1;
            markTaskDone(taskIndex);
        } else if (textInput.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(textInput.substring(7)) - 1;
            unmarkTaskDone(taskIndex);
        } else if (textInput.startsWith("delete")) {
            int taskIndex = Integer.parseInt(textInput.substring(7)) - 1;
            deleteTask(taskIndex);
        } else if (textInput.startsWith("todo")) {
            try {
                String taskDescription = textInput.substring(5);
                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }
                addToListAndReply(taskDescription);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("deadline")) {
            try {
                int byIndex = textInput.indexOf("/by");

                String taskDescription = textInput.substring(9, byIndex - 1);
                String deadline = textInput.substring(byIndex + 4);

                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }

                addToListAndReply(taskDescription, deadline);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("event")) {
            try {
                int fromIndex = textInput.indexOf("/from");
                int toIndex = textInput.indexOf("/to");

                String taskDescription = textInput.substring(6, fromIndex - 1);
                String startDate = textInput.substring(fromIndex + 6, toIndex - 1);
                String endDate = textInput.substring(toIndex + 4);

                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }

                addToListAndReply(taskDescription, startDate, endDate);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("find")) {
            String keyword = textInput.substring(5);
            this.taskList.printMatchingTasks(keyword);
        } else {
            replyToInvalidInput();
        }

        return isChatOver;
    }

    /**
     * Prints the goodbye message to the user
     */
    private void sayGoodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    private void addTaskToList(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have "
                + this.getNumTasks() + " pochat.tasks in the list.");
    }

    private void addToListAndReply(String textInput) {
        Task task = new ToDo(textInput);
        this.addTaskToList(task);
    }

    private void addToListAndReply(String textInput, String deadline) {
        Task task = new Deadline(textInput, deadline);
        this.addTaskToList(task);
    }

    private void addToListAndReply(String textInput, String startDate, String endDate) {
        Task task = new Event(textInput, startDate, endDate);
        this.addTaskToList(task);
    }

    private void replyWithListOfTextsEntered() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    private void markTaskDone(int index) {
        Task task = this.taskList.get(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    private void unmarkTaskDone(int index) {
        Task task = this.taskList.get(index);
        task.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    private void replyToInvalidInput() {
        String errorMessage = "Please enter a valid input and try again! Some examples of valid inputs are:\n" +
                "todo [description]\ndeadline [description] /by [deadline]\n" +
                "event [description] /from [start time] /to [end time]";
        System.out.println(errorMessage);
    }

    private int getNumTasks() {
        return this.taskList.size();
    }

    private void deleteTask(int index) {
        Task task = this.taskList.get(index);
        this.taskList.remove(task);

        System.out.println("Noted. I've removed this task:\n" + task + "\nNow you have "
                + this.getNumTasks() + " tasks in the list.");
    }
}