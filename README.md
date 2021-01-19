# Lempel-Ziv-Welch Compression
This application will compress text files using the Lempel-Ziv-Welch (LZW) compression algorithm. This is a java implementation of the algorithm, and was programmed as part of my Data Strucures and Algorithms module.

## Installation
To install, simply download source files. Required Java 11 or later.

## Usage 
To use:
1. Build the project with javac using ``javac LZW.java``.
2. Run using ``java LZW "[compress/decompress]" "[absolute file path]" "[optional:file name without extension]"``.

## Common Pitfalls
- Make sure that you spell compress/decompress correctly. 'c' or 'd' are also acceptable.
- Make sure that the file path you specify is the absolute path, not a path relative to the path of your LZW.class.

## Extending or Contributing
It is possible to extend this project for your own use. This project is covered by the license located in the ``LICENSE.md`` file. 