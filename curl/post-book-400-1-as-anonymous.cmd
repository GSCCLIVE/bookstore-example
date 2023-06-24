@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -H "Content-Type:application/json" -d @new-book-400-1.json -X POST http://localhost:8080/book/100-1
pause