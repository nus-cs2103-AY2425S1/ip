package makima.command;

/**
 * State to handle program exit
 */
public class DeactivatedState extends State {

    @Override
    public String getResponse(String input, Makima makima) {
        makima.exit();
        return null;
    }
}
