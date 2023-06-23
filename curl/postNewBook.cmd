@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -H "Content-Type:application/json" -d @new-book.json localhost:8080/book
pause