#!/bin/bash
set -e

echo "Checking environment..."
echo "Java version: $(java -version 2>&1 | head -1)"
echo "Maven version: $(mvn --version | head -1)"

echo "Installing npm dependencies (for test runner)..."
npm install

echo "Starting tests..."
# Set Node.js options to prevent EventEmitter warnings
export NODE_OPTIONS="--max-old-space-size=4096 --no-warnings"
# Enable colored Maven output
export MAVEN_OPTS="-Djansi.force=true -Dstyle.color=always"

echo "Running tests in watch mode..."
npm run test:all:watch
