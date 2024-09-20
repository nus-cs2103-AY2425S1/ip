package Gumball;

public class FixedDurationTask extends Task {

    /**
     *
     * @param desc
     * @throws TaskException
     */
    public FixedDurationTask(String desc) throws TaskException {
        super("", desc);
        assert desc.startsWith("fixed");
        try {
            super.description = fixedInputFormatter(desc);
            taskType = "[F]";
        } catch (ArrayIndexOutOfBoundsException | InputErrorException | NumberFormatException e) {
            throw new TaskException("Sorry, the description you " +
                    "gave does not follow the format for FixedDurationTasks.\n" +
                    "\nIt should be ('description' /for 'integer' /hrs " +
                    "'integer between 0 and 60'mins)");
        }
    }

    private String fixedInputFormatter(String desc) throws InputErrorException {
        if (desc.equals("fixed")) {
            throw new TaskException("Sorry the description for a " +
                    "FixedDurationTask cannot be empty"
                    + "\nIt should be ('description' /for 'integer' /hrs " +
                    "'integer between 0 and 60'mins)");
        }
        String[] section = desc.substring(6).split("/for | /hrs");
        if(!isThereHrs(section[1]) && minsFormatCheck(section[2]) == 0) {
            throw new TaskException("Sorry the duration of a FixedDurationTask cannot be 0, maybe use a TODO instead");
        } else if(!isThereHrs(section[1])) {
            String output = section[0] + "for: " + section[2];
            return output;
        } else {
            String output = section[0] + "for: " + section[1] + "hrs" + section[2];
            return output;
        }
    }

    private static int getNumFromString(String input) {
        return Integer.parseInt(input.replaceAll("[^0-9]", ""));
    }

    private static boolean isThereHrs(String input) throws InputErrorException {
        int temp = getNumFromString(input);
        if(temp < 0) {
            throw new InputErrorException("Incorrect");
        } else if(temp > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static int minsFormatCheck(String section) throws InputErrorException {
        int num = getNumFromString(section);
        if(!section.endsWith("mins") || num > 60 || num < 0) {
            throw new InputErrorException("Incorrect");
        } else {
            return num;
        }
    }


}
