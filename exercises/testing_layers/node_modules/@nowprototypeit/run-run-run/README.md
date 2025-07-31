# run-run-run
A file watcher that runs the tests, if one test phase fails it doesn't continue

## Usage

Basic usage:

```bash
npx run-run-run 'npm run test:unit' 'npm run test:integration' 'npm run test:e2e'
```

This will run the unit tests, then the integration tests, and finally the e2e tests. If any of these phases fail, it won't run further tests.  Any file changes in the directory you run this command in will trigger the tests to run again.

### Testing across languages

This tool is used via `npx` (it is therefore an NPM package), but it isn't just for node projects. For example, you can run Python tests, Ruby tests, or any other command-line test suite.

As a couple of examples:

```bash
npx run-run-run 'python -m unittest discover -s tests/unit' 'python -m unittest discover -s tests/integration' 'python -m unittest discover -s tests/e2e'
```

```bash
npx run-run-run 'mvn test -Dtest=UnitTests' 'mvn test -Dtest=IntegrationTests' 'mvn test -Dtest=E2ETests'
```

### Optional arguments
- `--watch-dir <string>`: The directory to watch for file changes. Defaults to the current working directory.
- `--working-dir <string>`: The directory in which to run the tests. Defaults to the current working directory.
- `--watch-file-extensions <string>`: Comma-separated list of file extensions to watch for changes. By default, all files are watched.
- `--version` or `-v`: Displays the version of the `run-run-run` package.
- `--help` or `-h`: Displays help information about the command and its options.

### Example using all optional arguments

```bash
npx run-run-run --watch-dir './my-project-files' --working-dir './my-project-files/a-subdir-for-some-reason' --watch-file-extensions 'ts,json' 'npm run test:unit' 'npm run test:integration' 'npm run test:e2e'
```

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contributing
Contributions are welcome! We accept PRs, or you can raise an issue if you want to discuss a feature or bug.

## Recognising the irony
This project is a TDD tool and it's not covered by tests.  This was imported from another project and it wasn't suitable to port any tests.  Publishing this as an NPM package is essentially a refactor step.  As this project starts to have a life of its own, a sensible test approach will become clearer.

## Acknowledgements
Thanks to [Skiller Whale](https://www.skillerwhale.com/) for allowing me to open-source this project.  While working with them on material, I produced this test runner, and they generously allowed me to open-source it.
