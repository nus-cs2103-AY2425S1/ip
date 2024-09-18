package Commands;

public class IntroCommand extends Command {

    private final String userInput;

    public IntroCommand(String userInput) {
            this.userInput = userInput;
        }

        @Override
        public boolean isExit() {
            return false;
        }


        /**
         *
         * @return a string of intro message
         */
        @Override
        public String execute() {
            return "Hi!! I am EmoteX!! How can I help ya tdy?\n" +
                    "\nThese are the list of commands you can use:)\n" +
                    "\n- todo [desc] (to add a todo task)\n" +
                    "- deadline [desc] /by [yyyy-MM-dd or dd/MM/yyy 16:00] (to add a deadline task)\n" +
                    "- event [desc] /from [16:00] /to [18:00] /on [yyyy-MM-dd or dd/MM/yyyy] (to add an event)\n" +
                    "- mark [index num] (to mark a task as completed)\n" +
                    "- unmark [index num] (to unmark a task thats completed)\n" +
                    "- delete [index num] (to delete a task)\n" +
                    "- list (to view all tasks)\n" +
                    "- find [keywords] (to find tasks with keyword)\n" +
                    "- bye\n" +
                    "- hi\n" +
                    "- due [yyyy-MM-dd or dd/MM/yyyy] (find tasks due on date)\n" +
                    "- update <num> <desc, date, startTime, endTime, time> /to <newValue> (to update task info));";
        }

    }