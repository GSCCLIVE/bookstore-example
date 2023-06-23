@echo off
@setlocal enableextensions
@cd /d "%~dp0"

curl localhost:8080/book
pause