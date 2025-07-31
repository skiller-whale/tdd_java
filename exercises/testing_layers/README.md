# Testing Layers Exercises

This is an incomplete Java implementation of the game Skiller Wordle, which uses Spring Boot to demonstrate TDD applied to a web service.

## Project Structure

```
src/
├── main/java/com/skillerwhale/wordle/
│   ├── core/                    # Core game logic
│   │   ├── Dictionary.java      # Word dictionary
│   │   ├── ValidateGuess.java   # Input validation (TODO)
│   │   ├── GameStatus.java      # Game state logic
│   │   └── EvaluateGuess.java   # Color evaluation (TODO)
│   ├── html/                    # HTML rendering components
│   │   ├── PageRenderer.java    # Main page layout
│   │   ├── GuessForm.java       # Input form
│   │   ├── GuessesFeedback.java # Display guesses
│   │   ├── StatusMessage.java   # Game status messages
│   │   └── ErrorMessage.java    # Error display
│   ├── GameState.java           # URL state management
│   ├── WordleController.java    # Spring Boot controller
│   └── WordleApplication.java   # Main application
└── test/java/com/skillerwhale/wordle/
    ├── core/                    # Unit tests for core logic
    │   ├── ValidateGuessTest.java
    │   ├── GameStatusTest.java
    │   └── EvaluateGuessTest.java
    ├── WordleControllerTest.java # Controller/server tests
    └── integration/
        └── GameTest.java        # End-to-end integration tests
```

## Running the Application

### Using Docker

```bash
# Run development server
docker-compose up dev-server

# Run tests with file watching
docker-compose run tests
```

### Running Tests

```bash
# Run all tests in watch mode (using run-run-run)
npm run test:all:watch

# Run tests continuously with Gradle
npm run test

# Run specific test layers
npm run test:unit:core    # Core unit tests
npm run test:server       # Controller/server tests
npm run test:system       # End-to-end integration tests
```

## Exercise Goals

This exercise demonstrates testing at different layers using `run-run-run` to coordinate test execution:

1. **Unit Tests (Core Layer)** - Test individual functions in isolation
2. **Integration Tests (Server Layer)** - Test HTTP endpoints and routing
3. **End-to-End Tests (System Layer)** - Test complete user workflows with Playwright

The `npm run test:all:watch` command uses `run-run-run` to execute all three test layers simultaneously with file watching, providing immediate feedback when code changes.

## TODO Items

The exercise includes several TODO items to implement:

1. Complete the `ValidateGuess.validateGuess()` method
2. Complete the `EvaluateGuess.evaluateGuess()` method for Wordle color logic
3. Uncomment and fix the corresponding unit tests
4. Wire up validation in the controller

## Technologies Used

- **Spring Boot 3.2** - Web framework
- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework
- **Playwright** - Modern browser automation for integration tests
- **Gradle** - Build tool
- **run-run-run** - Coordinated test execution across layers
- **Docker** - Containerization
