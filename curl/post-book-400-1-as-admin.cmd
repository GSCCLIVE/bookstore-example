@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -i --user admin:password -H "Content-Type:application/json" -d @new-book-400-1.json -X POST  localhost:8080/book
pause