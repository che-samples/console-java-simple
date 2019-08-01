# console-java-simple

A simple java "Hello World!" application, buildable with both maven and gradle. Useful for testing Eclipse Che Java workspaces.


# Building manually

|        | Task  | Command                    |
| ------ | ----- | -------------------------- |
| Maven  | Build | `mvn clean install`        |
|        | Run   | `java -jar ./target/*.jar` |
| Gradle | Build | `gradle build`             |
|        | Run   | `gradle run`               |


# App output

App output is streamed into a console. Note that if your app expects user input, do not use command but execute jars in the terminal directly.
