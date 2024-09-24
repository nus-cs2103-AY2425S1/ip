#!/usr/bin/env bash

# shellcheck disable=SC2069
if command -v rg
then
  GREP_PROG='rg --pcre2'
else
  GREP_PROG='grep -r --perl-regexp --color --include *.java -M'
fi

PRINT_OUTPUT=$([ "$1" == "-p" ] && echo true || echo false)

BOOLEAN_VIOLATION='(?:^[^@]*)boolean [^is|^are|^has|^should]'
BOOLEAN_VIOLATION_COUNT=$($GREP_PROG "$BOOLEAN_VIOLATION" src | wc -l)
[ "$PRINT_OUTPUT" == true ] && $GREP_PROG "$BOOLEAN_VIOLATION" src
echo "$BOOLEAN_VIOLATION_COUNT" boolean naming violations found.

NO_PERIOD_VIOLATION='\* @.*[^,.:]$'
NO_PERIOD_VIOLATION_COUNT=$($GREP_PROG "$NO_PERIOD_VIOLATION" src | wc -l)
[ "$PRINT_OUTPUT" == true ] && $GREP_PROG "$NO_PERIOD_VIOLATION" src
echo "$NO_PERIOD_VIOLATION_COUNT" Javadoc punctuation violations found.

TOTAL_COUNT=$((BOOLEAN_VIOLATION_COUNT + NO_PERIOD_VIOLATION_COUNT))
echo $TOTAL_COUNT total violations found.

[ "$TOTAL_COUNT" -eq 0 ]
