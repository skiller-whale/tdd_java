#!/usr/bin/env node

let remainingIterations = 30
let runsSoFar = 0
const failAfter = 20

const interval = setInterval(async () => {
  runsSoFar++
  console.log(`Remaining iterations (node): ${remainingIterations}`)
  remainingIterations--
  if (runsSoFar >= failAfter) {
    clearInterval(interval)
    console.error(`This run has intentionally failed after [${failAfter}] iterations`)
    process.exit(1)
  }
}, 100)
