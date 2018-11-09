# Abbreviation Extractor

## Introduction

This script is used to extract abbreviations and definitions from biomedical texts written in Spanish, by detecting abbreviations and their potential definitions explicitly mentioned in the same sentence.
This script is used internally at the AbreMES-X project (https://github.com/PlanTL/AbreMES-X) to generate the Spanish Medical Abbreviation DataBase (https://github.com/PlanTL/AbreMES-DB). This script is provided to everyone in order to make use of on your own projects.

We use a modified version of the algorithm developed by Schwartz & Hearst[1], adapted to the Spanish language and applying specific filters to improve the results. Users are permitted to change the code and adapt it to their needs without any restrictions, like adapting the abbreviation-definition extractor into other languages, domains, or other document types to analyze.

## Prerequisites

This software has been compiled with Java SE 1.8 and it should work with recent versions. You can download Java from the following website: https://www.java.com/en/download

Some of the detected potential abbreviations could be geophysical locations' names. In order to filter these abbreviations and discard them, the script makes use of the GeoNames ontology. GeoNames is a geographical database which covers over eleven million placements. GeoNames is distributed under Creative Commons BY-4.0 license. It is available for download free or charge: http://www.geonames.org

## Directory structure

_src_ contains the following files:

<pre>
- AbbreviationExtractor.java
  The script to extract abbreviations from biomedical texts.
  
- Example.java
  An example of how to make use of the script above.
</pre>

## Contact

Ander Intxaurrondo (ander.intxaurrondo@bsc.es)

## License

(This is so-called MIT/X License)

Copyright (c) 2017-2018 Secretaría de Estado para el Avance Digital (SEAD)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## References

[1] A.S. Schwartz, M.A. Hearst, "A Simple Algorithm for Identifying Abbreviation Definitions in Biomedical Text"
