@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -i --user user:password -H "Content-Type:application/json" -d @new-book-500-1-missing-title.json -X POST localhost:8080/book
pause