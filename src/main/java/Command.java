public class Command {
    /**
     * The command name.
     */
    private String name;

    /**
     * The parameter string.
     */
    private String paramString;

    /**
     * The constructor. Splits commandString into command and paramString.
     * @param commandString A full line of command together with all parameters.
     */
    public Command(String commandString) {
        String[] tmp = commandString.split(" ", 2);
        name = tmp[0];
        if (tmp.length == 1) {
            paramString = ""; // no params
        } else {
            paramString = tmp[1];
        }
    }

    /**
     * Gets the command name.
     * @return The command name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the parameter string.
     * @return The parameter string.
     */
    public String getParamString() {
        return paramString;
    }

    /**
     * Gets an integer parameter at specific index.
     * @param index The index of the parameter required.
     * @return The required parameter.
     */
    public int getIntegerParam(int index) {
        String[] params = paramString.split(" ");
        return Integer.parseInt(params[index]);
    }


}
