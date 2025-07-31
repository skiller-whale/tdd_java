#!/bin/bash

remainingIterations=30

while [ $remainingIterations -gt 0 ]; do
    echo "Remaining iterations (bash): $remainingIterations"
    remainingIterations=$((remainingIterations - 1))
    sleep 0.1
done

echo "Finished all iterations (bash)"
