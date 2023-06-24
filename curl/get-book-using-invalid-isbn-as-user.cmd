@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -i --user user:password -X GET http://localhost:8080/book/100
pause