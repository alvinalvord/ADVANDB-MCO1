@echo off

@echo compiling src files
javac src/*.java -d bin
@echo compile done...

@echo initializing program
cd bin
java Driver
cd ..
@echo exiting program