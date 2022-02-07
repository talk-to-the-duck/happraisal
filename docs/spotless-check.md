# Spotless check

Spotless can format <antlr | c | c# | c++ | css | flow | graphql | groovy | html | java | javascript | json | jsx | kotlin | less | license headers | markdown | objective-c | protobuf | python | scala | scss | sql | typeScript | vue | yaml | anything> using <gradle | maven | anything>.

This project is in Java, so we use Spotless in a very specific context.

## What should we expect when Spotless formats the Java code?

We configured Spotless in the `gradle/spotless.gradle` file.

To format some file types such as `*.gradle` we configured the format section.

To format Java code we chose to use the **Google Java Format**.
Google Java Format is a set of opinionated rules, and you can customize almost nothing.
You can learn more about these rules here: [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
The Google Java Format code contains some bugs, so sometimes things can become messy. For example some edge cases
with idempotency were detected.

With these settings, generally you should expect that: 
- imports are alphabetically sorted
- tab characters are replaced by spaces 
- some empty lines are removed
- annotations are inlined with property declarations
- some lexical tokens are separated
- accolades are placed coherently
- ...


## How to use Spotless with Gradle:

With Spotless you can format your code at every commit.


How do you know if your code follows the rules set in the configuration file?
Check the code compliance with formatting rules without modifying anything:

```bash
./gradlew spotlessCheck
```

This step is implicitly run every time you build your project.

With Spotless you can check and automatically format your code before you commit.
The action is triggered also by a normal build.

**ATTENTION**: currently, this action is up to the developer. If you do not perform the task, you
will commit unformatted code. Also, currently there are no git hooks to force code formatting.

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
