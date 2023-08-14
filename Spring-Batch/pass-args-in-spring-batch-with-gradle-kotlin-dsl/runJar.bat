@echo off
call "setenv.bat"
call gradlew.bat clean bootJar
java -jar .\build\libs\%BATCH_PROJECT_NAME%-%BATCH_PROJECT_VERSION%.jar %*
