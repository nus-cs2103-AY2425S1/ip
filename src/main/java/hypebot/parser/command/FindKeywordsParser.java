package hypebot.parser.command;

import static hypebot.common.Messages.ERROR_SEARCH_QUERY_EMPTY;

import java.util.regex.Pattern;

import hypebot.exception.missing.MissingArgumentException;
import hypebot.exception.missing.MissingSearchKeywordsException;
import hypebot.parser.Parser;

public class FindKeywordsParser extends Parser {
    private void checkKeywordsExist(String[] inputBeforeDates) throws MissingSearchKeywordsException {
        if (inputBeforeDates.length == 1) {
            throw new MissingSearchKeywordsException(ERROR_SEARCH_QUERY_EMPTY);
        }
    }

    private String[] extractKeywords(String line) throws MissingSearchKeywordsException {
        String[] inputBeforeDates = line.split(" /")[0].split(" ");
        checkKeywordsExist(inputBeforeDates);

        String[] keywords = new String[inputBeforeDates.length - 1];
        System.arraycopy(inputBeforeDates, 1, keywords, 0, inputBeforeDates.length - 1);

        return keywords;
    }

    private String assembleKeywordsRegex(String line) throws MissingSearchKeywordsException {
        String[] keywords = extractKeywords(line);
        StringBuilder keywordsSb = new StringBuilder();
        for (int i = 0; i < keywords.length - 1; i++) {
            keywordsSb.append(keywords[i].toLowerCase()).append("|");
        }
        keywordsSb.append(keywords[keywords.length - 1]);
        return keywordsSb.toString().trim();
    }

    @Override
    public Pattern parse(String line) throws MissingArgumentException {
        String keywordsRegex = assembleKeywordsRegex(line);
        return Pattern.compile(keywordsRegex, Pattern.CASE_INSENSITIVE);
    }
}
