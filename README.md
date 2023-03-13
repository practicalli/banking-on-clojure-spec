# banking-on-clojure
Developing a Clojure application using a test driven development approach (TDD).  Includes the use of `clojure.spec` libraries for defining data and function contracts to be used for generative testing.

A [guide to the development of this project](http://practicalli.github.io/clojure/clojure-spec/projects/bank-account/) is on the Practicalli Clojure website.

[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://circleci.com/gh/practicalli/banking-on-clojure)

## Installation

Download from https://github.com/practicalli/banking-on-clojure.

## Development
Open the code in a Clojure aware editor and start a REPL session.

```shell
clojure -M:repl/reloaded
```

Run all the tests in the project using the Cognitect Labs test runner, setting the classpath to include `test` directory.  The aliases are included in the project `deps.edn` file.

```shell
clojure -A:test:runner
```

Or continually run test with the kaocha test runner from the Practicalli Clojure CLI Config
```
clojure -X:test/watch
```


## Running the code
Use the `-m` option to set the main namespace to inform Clojure where it can find the `-main` function to start the code running.

```shell
clojure -m practicalli.banking-on-clojure
```

## Packaging / Deployment
Clojure is deployed as a Java archive (jar) file, an archive created using zip compression.  To package the code to run in a JVM environment, an uberjar is created which included the project code and the Clojure standard library.  This is called an uberjar.

Use the alias for depstar tool to build an uberjar for this project.  The alias is defined in the `deps.edn` file for this project.

```shell
clojure -A:uberjar
```

The code can be run from the uberjar on the command line

```shell
java -jar banking-on-clojure.jar
```


## License

Copyright © 2020 Practicalli

Distributed under the Creative Commons Attribution Share-Alike 4.0 International
