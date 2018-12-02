# FreeLing_2_Brat

Script to convert FreeLing tabular output format into BRAT standoff format.

## Directory structure
* `ann/`: folder for temporary use of the script `Anotacion_a_BRAT.sh`
* `Anotacion_a_BRAT.sh`: main script.
* `convertidorFreelingBrat_puntos.py`: auxiliary script to convert the sentence splitting. 
* `convertidorFreelingBrat.py`: auxiliary script to complete the conversion.
* `convertidorFreelingBrat_tokens.py`: auxiliary script to convert the tokenization.
* `convertidor.sh`: auxiliary script to call the suitable conversor.
* `ejemplo_anotados/`: annotated sample text folder.
* `ejemplo_originales/`: original sample text folder. 
* `ejemplo_resultados_ann_BRAT/`: sample text folder as they result from the execution of the script `Anotacion_a_BRAT.sh`.
* `entrada/`: folder for temporary use of the script` Anotacion_a_BRAT.sh`.
* `generadorFicherosBrat.py`: script with auxiliary functionality common to all converters.
* `originales/`: folder for temporary use of the script `Anotacion_a_BRAT.sh`.
* `README.md`: this file.

## Prerequisites

The script requires Python 3 installed on your system.

## To convert FreeLing into BRAT

To convert a set of files labeled by SPACCC_POS-TAGGER into BRAT standoff format, follow the following steps:
* Put all original files (with extension `.txt`) in a folder
* Put all of their corresponding annotated files (with extension `.txt_tagged`) in another folder
* Create an output folder
* Run the `Anotacion_a_BRAT.sh` script by choosing one of the three annotation level: `COMPLETO` for POS, `PUNTOS` for sentence splitting, or `TOKENS` for tokenization.

## Example:

Assuming that we have:
- the original files are in folder `ejemplo_originales/`
- the annotated files are in folder `ejemplo_anotados/`
- the destination folder is `ejemplo_resultados_ann_BRAT/` (it has to be created)

<pre>
Anotacion_a_BRAT.sh ejemplo_originales/ ejemplo_anotados/ ejemplo_resultados_ann_BRAT/ POINTS 
</pre>


## License

(This is so-called MIT/X License)

Copyright (c) 2017-2018 Secretaría de Estado para el Avance Digital (SEAD)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
