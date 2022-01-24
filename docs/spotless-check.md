# Spotless check

Spotless can format <antlr | c | c# | c++ | css | flow | graphql | groovy | html | java | javascript | json | jsx | kotlin | less | license headers | markdown | objective-c | protobuf | python | scala | scss | sql | typeScript | vue | yaml | anything> using <gradle | maven | anything>.

This project is in Java, so we use Spotless in a very specific context.

## What should we expect when Spotless formats the Java code?
 
- imports are alphabetically sorted
- tab characters are replaced by spaces 
- remove some empty lines
- inline annotations with property declarations
- separate some lexical tokens
- accolades are placed coherently
- ...


## How to use Spotless with Gradle:

With Spotless you can format your code at every commit.


How do you know if your code follows the rules set in the configuration file?
Check the code compliance with formatting rules without modifying anything:
```bash
./gradlew spotlessCheck
```

This step is run every time you build your project.
Spotless is integrated in the Gradle build lifecycle. So when you build the project, a spotlessCheck step is triggered.

If during the build, Spotless finds one or more files that are not well formatted, when the build is finished
an error message similar to the one below is shown: 

```
Violations also present in XX other files.
Run './gradlew :spotlessApply' to fix these violations.
```

Then you have to run the command below to apply the formatting rules to the code.
This will modify any file in the project that didn't pass the check.

```bash
./gradlew spotlessApply
```

After having applied this command, you will have to commit all the modified files.

With Spotless you can format your code at every commit.

The configuration 

For more details:
[Spotless: Keep your code spotless](https://github.com/diffplug/spotless).
[Spotless plugin for Gradle](https://github.com/diffplug/spotless/tree/main/plugin-gradle).
