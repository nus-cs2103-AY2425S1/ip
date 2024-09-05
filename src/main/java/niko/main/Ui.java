    package niko.main;

    import java.util.ArrayList;
    import java.util.Scanner;

    import niko.task.Task;

    /**
     * Handles interactions with the user.
     */
    public class Ui {

        /** Scanner for reading user input from the console. */
        private Scanner scanner;

        /**
         * Constructs a Ui object to interact with the user.
         */
        public Ui() {
            this.scanner = new Scanner(System.in);
        }

        /**
         * Displays the welcome message to the user.
         *
         * @param name The name of the chatbot.
         * @return The welcome message as a string.
         */
        public String showWelcomeMessage(String name) {
            return "________________________________________\n"
                    + "Hello! I'm " + name + "\n"
                    + "What can I do for you?\n"
                    + "________________________________________";
        }

        /**
         * Reads the user input from the console.
         *
         * @return The user input as a string.
         */
        public String getUserInput() {
            return scanner.nextLine();
        }

        /**
         * Displays a message indicating that tasks were successfully loaded.
         *
         * @param taskCount The number of tasks loaded.
         * @return The load success message as a string.
         */
        public String showLoadSuccessMessage(int taskCount) {
            return "Successfully loaded " + taskCount + " tasks from file.";
        }

        /**
         * Displays a message indicating that a task was successfully added.
         *
         * @param task      The task that was added.
         * @param taskCount The total number of tasks in the list.
         * @return The task added message as a string.
         */
        public String showAddTaskMessage(Task task, int taskCount) {
            return "________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + "  " + task + "\n"
                    + "Now you have " + taskCount + " tasks in the list.\n"
                    + "________________________________________";
        }

        /**
         * Displays the list of tasks to the user.
         *
         * @param tasks The list of tasks to display.
         * @return The task list as a string.
         */
        public String showTaskList(ArrayList<Task> tasks) {
            StringBuilder sb = new StringBuilder();
            sb.append("________________________________________\n");
            if (!tasks.isEmpty()) {
                sb.append("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
                }
            } else {
                sb.append("Your task list is empty.\n");
            }
            sb.append("________________________________________");
            return sb.toString();
        }

        /**
         * Displays a message indicating that a task was marked as done.
         *
         * @param task The task that was marked as done.
         * @return The task marked as done message as a string.
         */
        public String showMarkTaskMessage(Task task) {
            return "________________________________________\n"
                    + "Nice! I've marked this task as done:\n"
                    + "  " + task + "\n"
                    + "________________________________________";
        }

        /**
         * Displays a message indicating that a task was unmarked as done.
         *
         * @param task The task that was unmarked as done.
         * @return The task unmarked message as a string.
         */
        public String showUnmarkTaskMessage(Task task) {
            return "________________________________________\n"
                    + "OK, I've marked this task as not done yet:\n"
                    + "  " + task + "\n"
                    + "________________________________________";
        }

        /**
         * Displays a message indicating that a task was deleted.
         *
         * @param task      The task that was deleted.
         * @param taskCount The total number of tasks remaining in the list.
         * @return The task deleted message as a string.
         */
        public String showDeleteTaskMessage(Task task, int taskCount) {
            return "________________________________________\n"
                    + "Noted. I've removed this task:\n"
                    + "  " + task + "\n"
                    + "Now you have " + taskCount + " tasks in the list.\n"
                    + "________________________________________";
        }

        /**
         * Displays an error message to the user.
         *
         * @param message The error message to display.
         * @return The error message as a string.
         */
        public String showErrorMessage(String message) {
            return "________________________________________\n"
                    + "OOPS!!! " + message + "\n"
                    + "________________________________________";
        }

        /**
         * Displays the goodbye message to the user.
         *
         * @return The goodbye message as a string.
         */
        public String showGoodbyeMessage() {
            return "Bye. Hope to see you again soon!\n"
                    + "________________________________________";
        }

        /**
         * Displays a message indicating that no matching tasks were found.
         *
         * @return The no matching tasks message as a string.
         */
        public String showNoMatchingTasksMessage() {
            return "________________________________________\n"
                    + "OOPS!!! No matching tasks found!\n"
                    + "________________________________________";
        }

        /**
         * Displays a line separator for better readability.
         *
         * @return The line separator as a string.
         */
        public String showLine() {
            return "________________________________________";
        }
    }
