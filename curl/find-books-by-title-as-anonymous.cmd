@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -X GET -F "title=Sport World" http://localhost:8080/book
pause