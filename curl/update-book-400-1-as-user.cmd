@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -i --user user:password -H "Content-Type:application/json" -d @new-book-400-1-updated.json -X PUT localhost:8080/book/400-1
pause