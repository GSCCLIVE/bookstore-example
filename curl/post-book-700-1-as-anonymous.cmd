@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -i -H "Content-Type:application/json" -d @new-book-700-1.json -X POST localhost:8080/book
pause