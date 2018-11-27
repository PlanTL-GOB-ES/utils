# utils

## Introduction

This repository contains different scripts that provide utilities for various miscellaneous tasks that are used internally, but they 
could be useful for researchers and developers.

All the scripts included in this repository are independent from each other. We recommend interested users to clone the entire repository and to remove those directories they are not interested in.

## Directory structure

Each directory contains an specific script for some specific actions. Here's a brief explanation for each tool:

#### Abbreviation_Extractor

This script is used to extract abbreviations and definitions from biomedical texts written in Spanish, by detecting 
abbreviations and their potential definitions explicitly mentioned in the same sentence.

This script is used internally at the AbreMES-X project (https://github.com/PlanTL/AbreMES-X) to generate the Spanish Medical Abbreviation DataBase (https://github.com/PlanTL/AbreMES-DB).


#### AnnotatorJS_2_Brat

This script extracts the annotations made using the AnnotatorJS library and converts them into Brat format. AnnotatorJS' 
annotations are stored in Json format and the converter builds ANN files from them.

#### FixEncodingErrors

This script fixes some of the most common encoding problems in corpora.

#### FreeLing_2_Brat

This script converts FreeLing tabular output format into BRAT standoff format.

## Contact

ander.intxaurrondo@bsc.es

## License

(This is so-called MIT/X License)

Copyright (c) 2017-2018 Secretaría de Estado para el Avance Digital (SEAD)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
