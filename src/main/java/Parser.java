public class Parser {
    public int parseLength(String reply) {
        return reply.split(" /").length;
    }

    public int parseIndex(String reply) {
        return Integer.parseInt(reply.split(" ")[1]);
    }
}
