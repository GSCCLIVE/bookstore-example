@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -X DELETE localhost:8080/book/100-1
pause