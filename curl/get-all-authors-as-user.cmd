@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl -i --user user:password -X GET localhost:8080/author
pause