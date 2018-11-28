# utils

This repository contains different scripts that provide utilities for various miscellaneous tasks that are used internally, but they 
could be useful for researchers and developers.

All the scripts included in this repository are independent from each other. We recommend interested users to clone the entire repository and to remove those directories they are not interested in.

## Directory structure

Each directory contains an specific script for some specific actions. Here's a brief explanation for each tool:

<pre>
Abbreviation_Extractor/
This script is used to extract abbreviations and definitions from biomedical texts written in Spanish, by detecting 
abbreviations and their potential definitions explicitly mentioned in the same sentence.

AnnotatorJS_2_Brat/
This script extracts the annotations made using the AnnotatorJS library and converts them into Brat format. 
AnnotatorJS' annotations are stored in Json format and the converter builds ANN files from them.

FixEncodingErrors/
This script fixes some of the most common encoding problems in corpora.

FreeLing_2_brat/
This script converts FreeLing tabular output format into BRAT standoff format.
</pre>
