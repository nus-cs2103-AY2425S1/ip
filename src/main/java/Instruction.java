public enum Instruction {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    private static boolean contains(String command) {
        for (Instruction c : Instruction.values()) {
            if (c.name().equals(command.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkCommandExist(String command) {
        return Instruction.contains(command);
    }
}
