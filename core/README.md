# Core Bowling

# UTIL

Count lines with powershell
~~~shell
dir . -filter "*.java" -Recurse -name | foreach{(Get-Content $_).Count} | measure-object -sum
~~~

Count lines with shell (linux, bash, zsh etc)
~~~shell
find src/ '*.java' -type f | xargs wc -l
~~~