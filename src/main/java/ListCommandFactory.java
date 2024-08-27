public class ListCommandFactory extends CommandFactory {
    @Override
    public Command parse(String text) throws GrayException {
        if (!text.equals("list")) return null;
        return new ListCommand();
    }
}
