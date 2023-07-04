@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -i --user user:password -X GET -F "author=Clive" http://localhost:8080/book
pause