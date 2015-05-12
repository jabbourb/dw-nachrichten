# dw-nachrichten
This script fetches the daily news from Deutche Welle's Langsam
Gesprochene Nachrichten, and extracts the text to make it easier to view
on my ereader.

## Running the script

`sbt "run <destination>"` will run the script and handle the dependencies.

The `helper.sh` script takes care of mounting/unmounting the reader, and
uses today's date for the file name.
