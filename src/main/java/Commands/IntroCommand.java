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
            return "    Hi!! I am EmoteX!! How can I help ya tdy?";
        }

    }