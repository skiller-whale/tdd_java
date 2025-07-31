#!/bin/bash

remainingIterations=30
runsSoFar=0
failAfter=20

while true; do
    runsSoFar=$((runsSoFar + 1))
    echo "Remaining iterations (bash): $remainingIterations"
    remainingIterations=$((remainingIterations - 1))

    if [ $runsSoFar -ge $failAfter ]; then
        echo "This run has intentionally failed after [$failAfter] iterations" >&2
        exit 1
    fi

    sleep 0.1
done
