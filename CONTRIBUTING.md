# Contributing
When contributing to this repository, please make sure that the following rules are respected. If the rules are respected,
you are free to change anything you want in the code ! Make a pull request and I will merge it - make sure to explain your changes ! :)

## Coding style
- The variables must be written this way : thisIsMyVariable,
- If an attribute or a variable is final, it should be written THIS_WAY,
- Name the methods this way : "itIsSomething()" and not this way "it_is_something()" ,
- The getters must have "get" prefix, and then the exact name of the variable : "getHealth()" for "_health",
- If the variable is a boolean, then the getter should be written this way : "isAlive()" for "_alive",
- The setters must have "set" prefix, and then the exact name of the variable : "setPosition" for "_position".

## Commits syntax

If you have no idea how to name your commits, please see this small guide.
You are not obliged to follow this guide.

```
<type>(<scope>): <subject>

<description>

<footer>
```

- Type : defines the type of the commit :
    - arch: Code architecture,
    - docs: Documentation,
    - feat: New feature,
    - fix: Bug correction,
    - perf: Performance improvements,
    - refactor: Code modification that does not change the operation of the code,
    - style: Change the style of the code (without changing the logic),
    - test: Test modifications,
    - misc: Something else.
- Scope : defines which part of the library / application is affected by the commit (this information is optional)
    - Event handling,
    - Input / output,
    - ...
- Subject : contains a brief description of the changes
    - Using the present imperative ("change", not "changed" or "changes"),
    - Without any capital letter at the start,
    - Without "." at the end.
- Description : allows for a deeper insight into the motives behind the change. The rules are the same as for the subject part.
- Footer : contains important changes (Breaking Changes) and references to GitHub issues.
