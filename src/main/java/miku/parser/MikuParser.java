package miku.parser;

import miku.command.Command;

abstract class MikuParser {
    abstract Command parse(String str);
}
