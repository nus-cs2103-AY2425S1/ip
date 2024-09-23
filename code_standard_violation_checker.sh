#!/usr/bin/env bash
BOOLEAN_VIOLATION=$(\grep -r --perl-regexp --color '(?<!@) boolean [^is|^are|^has|^should]' src |
wc -l)

[ $BOOLEAN_VIOLATION -eq 0 ]
