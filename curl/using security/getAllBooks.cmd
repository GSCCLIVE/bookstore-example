@echo off
@setlocal enableextensions
@cd /d "%~dp0"

@REM curl -d j_username=user -d j_password=password -L localhost:8080/login

curl -i -H "Content-Type: application/x-www-form-urlencoded" -c cookies.txt -H "Origin: http://localhost:8080/login" -d "j_username=user&j_password=password" -X POST http://localhost:8080/login
curl -i -H "Content-Type: application/json" -H "Origin: http://localhost:8080/login" -b cookies.txt http://localhost:8080/book
pause