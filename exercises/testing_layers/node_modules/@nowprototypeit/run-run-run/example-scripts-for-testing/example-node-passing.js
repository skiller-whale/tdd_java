#!/usr/bin/env node

let remainingIterations = 30

const interval = setInterval(async () => {
  console.log(`Remaining iterations (node): ${remainingIterations}`)
  remainingIterations--
  if (remainingIterations <= 0) {
    clearInterval(interval)
    console.log('Finished all iterations (node)')
  }
}, 100)
