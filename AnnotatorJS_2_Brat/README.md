# AnnotatorJS to Brat converter

## Introduction

This script extracts the annotations made using the AnnotatorJS library and transforms them into Brat format. AnnotatorJS' annotations are stored in Json format, and the converter builds ANN files from them.

AnnotatorJS (http://annotatorjs.org/) is an open-source javascript library that adds the annotation function to any website. Brat (http://brat.nlplab.org/) is brat is a web-based tool for text annotation to existing text documents.

## Prerequisites

This software has been compiled with Java SE 1.8 and it should work with recent versions. You can download Java from the following website: https://www.java.com/en/download

The script has dependency of the Jsoup library, an open source HTML parser. We used version 1.10.3 for this script. You can download Jsoup from the following website: https://jsoup.org/download

The script needs the Json-simple library to extract the information from Json annotations. The script works with version 1.1.1. You can download Json-simple from the following repository: https://github.com/fangyidong/json-simple

## Directory structure

<pre>
src/
  The script's source code.
  - AnnotatorJS_2_Brat.java: the main class and all functions to extract annotated data.
  - Mention.java: information we want to extract from each annotated mention.
  By default, each object stores the following data: mention string (quote), category,
  start and end offsets, note, URI where the mention can be found, and the paragraph
  of the text where this mention was annotated. AnnotatorJS stores extra information,
  for example, the time and day the mention was annotated.
  In case you want to extract more data, add the new parameters into this file.

exec/
  The executable to extract the annotations.

exec/AnnotatorJS_2_Brat_lib
  Moduels Jsoup and Json-simple, necessary to make the script work. 
</pre>

## Usage

The executable file "AnnotatorJS_2_Brat.jar" is the program you need to convert your annotations made with the AnnotatorJS into Brat format. The executable needs the following arguments:
- the URIs file: a file in tabular format, with the html file's name in the first column, and the URI in the second column.
- the folder with all HTML files.
- the URL of your website where annotations are stored.
- the folder where the Brat files (*.ann) will be stored.

To execute the script, type the following command in your terminal:

<pre>
$ java -jar AnnotatorJS_2_Brat.jar URIs_FILE
       HTML_FILES_DIRECTORY WEBSITE_URL ANN_OUTPUT_DIRECTORY
</pre>

## Example

<pre>
$ java -jar AnnotatorJS_2_Brat.jar ~/Annotations/URIs.txt ~/Annotations/html_files
       http://www.my-website.com/annotations-here/annotator-store ~/Annotations/Brat
</pre>


## Contact

Ander Intxaurrondo (ander.intxaurrondo@bsc.es)

## License

(This is so-called MIT/X License)

Copyright (c) 2017-2018 Secretaría de Estado para el Avance Digital (SEAD)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
