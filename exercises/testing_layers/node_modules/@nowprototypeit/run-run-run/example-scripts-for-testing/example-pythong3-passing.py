#!/usr/bin/env python3

import time

remaining_iterations = 30

while remaining_iterations > 0:
    print(f"Remaining iterations (python): {remaining_iterations}")
    remaining_iterations -= 1
    time.sleep(0.1)

print("Finished all iterations (python)")
