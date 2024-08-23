public class Deadline extends Task {

    String deadlineDate;
    public Deadline(String name, String[] deadlineInfo) {

        super(validateString(name, deadlineInfo));
        this.deadlineDate = deadlineInfo[1];
    }

    private static String validateString(String name, String[] deadlineInfo) {
        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time tell me what your deadline is");
        }

        if (deadlineInfo == null) {
            throw new TarsException("Add a /by command and a deadline date");
        }

        switch(deadlineInfo.length) {
            case 1:
                if (deadlineInfo[0].equals("by")) {
                    throw new TarsException("Finish the command by adding a deadline date");
                } else {
                    throw new TarsException("Add the /by command");
                }

            case 2:
                if (deadlineInfo[0].equals("by")) {
                    if (deadlineInfo[1].isEmpty()) {
                        throw new TarsException("Finish the command by adding a deadline date");
                    }
                } else {
                    throw new TarsException("Add the /by command");
                }
        }

        return name;
    }


    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadlineDate);
    }
}
