#!/usr/bin/env python3

import sys
import time

remaining_iterations = 30
runs_so_far = 0
fail_after = 20

while True:
    runs_so_far += 1
    print(f"Remaining iterations (python): {remaining_iterations}")
    remaining_iterations -= 1

    if runs_so_far >= fail_after:
        print(f"This run has intentionally failed after [{fail_after}] iterations", file=sys.stderr)
        sys.exit(1)

    time.sleep(0.1)
