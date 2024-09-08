package qwerty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseArgs_emptyString_emptyMap() {
        HashMap<String, String> emptyMap = new HashMap<>();
        emptyMap.put("main", null);
        assertEquals(emptyMap, Parser.parseArgs(""));
    }

    @Test
    public void parseArgs_singleSlash_success() {
        HashMap<String, String> emptyMap = new HashMap<>();
        emptyMap.put("main", "/");
        assertEquals(emptyMap, Parser.parseArgs("/"));
    }

    @Test
    public void parseArgs_multipleSlashesWithSpacing_success() {
        HashMap<String, String> emptyMap = new HashMap<>();
        emptyMap.put("main", "/");
        // should ignore the other slashes due to empty argument
        assertEquals(emptyMap, Parser.parseArgs("/ / /"));
    }

    @Test
    public void parseArgs_paramsWithMissingArguments_success() {
        HashMap<String, String> emptyMap = new HashMap<>();
        emptyMap.put("main", "args");
        emptyMap.put("a2", "arg2");
        assertEquals(emptyMap, Parser.parseArgs("args /a1 /a2 arg2 /a3"));
    }

    @Test
    public void parseArgs_singleOneWordArgument_success() {
        HashMap<String, String> emptyMap = new HashMap<>();
        emptyMap.put("main", "cs2103t");
        assertEquals(emptyMap, Parser.parseArgs("cs2103t"));
    }

    @Test
    public void parseArgs_singleMultiWordArgument_success() {
        HashMap<String, String> emptyMap = new HashMap<>();
        emptyMap.put("main", "cs2103t is a pain");
        assertEquals(emptyMap, Parser.parseArgs("cs2103t is a pain"));
    }

    @Test
    public void parseArgs_multipleArguments_success() {
        HashMap<String, String> emptyMap = new HashMap<>();
        emptyMap.put("main", "cs2103t");
        emptyMap.put("arg2", "cs2101 too");
        assertEquals(emptyMap, Parser.parseArgs("cs2103t /arg2 cs2101 too"));
    }

    @Test
    public void parseArgs_multipleArguments2_success() {
        HashMap<String, String> emptyMap = new HashMap<>();
        emptyMap.put("main", "cs2103t");
        emptyMap.put("arg2", "cs2101 too");
        emptyMap.put("arg3", "insert text");
        emptyMap.put("help", "me this is so hard");
        assertEquals(emptyMap,
                Parser.parseArgs("cs2103t /arg2 cs2101 too /arg3 insert text /help me this is so hard"));
    }

}
