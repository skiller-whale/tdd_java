#!/usr/bin/env node
import { Command } from 'commander'
import { exec } from 'node:child_process'
import fs from 'fs'
import fsp from 'fs/promises'
import path from 'node:path'
import chokidar from 'chokidar'

let packageJsonVersion = 'unknown'
try {
  packageJsonVersion = JSON.parse(
    fs.readFileSync(
      path.join(import.meta.dirname, '..', 'package.json'),
      'utf8'
    )
  )?.version
} catch (e) {
  console.error('Failed to read package.json version:', e)
}

const program = new Command()
const parsed = program
  .name('run-run-run')
  .description('Watch files and run tests')
  .option('--watch-dir <string>', 'Directory to watch for changes', process.cwd())
  .option('--watch-file-extensions <string>', 'File extensions to watch for changes, comma seperated', '*')
  .option('--working-dir <string>', 'Directory run the tests in', process.cwd())
  .arguments('[testsToRun...]')
  .version(packageJsonVersion, '-v, --version', 'Show version number')
  .parse(process.argv)

const { watchDir, watchFileExtensions, workingDir } = parsed.opts()

const testsToRun = parsed.args

console.log('tests to run ', testsToRun)

if (!testsToRun || testsToRun.length === 0) {
  console.error('No tests provided to run. Please provide at least one test command.')
  process.exit(1)
}

const lastModifiedTime = 0
let runInProgress = null

function debouncer (fn, delay) {
  let timeoutId = null
  return function () {
    if (timeoutId) clearTimeout(timeoutId)
    timeoutId = setTimeout(() => {
      fn()
      timeoutId = null
    }, delay)
  }
}

const debouncedTestRun = debouncer(() => {
  runConditionalTests()
}, 100)

console.log(`Watching directory: ${watchDir}`)
const preparedFileExtensions = watchFileExtensions.split(',').map(ext => ext.trim())
chokidar.watch(watchDir, {
  ignoreInitial: true,
  recursive: true,
  ignored: (filePath, stats) => {
    if (!stats || !stats.isFile()) return false
    if (watchFileExtensions === '*') return filePath.endsWith('~')
    const ext = path.extname(filePath).split('.').pop()
    return !preparedFileExtensions.includes(ext)
  }
}).on('all', async (event, filePath) => {
  const stats = await fsp.stat(filePath).catch(() => ({ mtimeMs: 0 }))
  const modifiedTime = stats.mtimeMs

  if (modifiedTime > lastModifiedTime) {
    debouncedTestRun()
  }
})

function runTests (
  command
) {
  return new Promise((resolve) => {
    const spawned = exec(command, {
      stdio: 'pipe',
      cwd: workingDir,
      env: {
        ...process.env,
        FORCE_COLOR: 'true',
        COLORTERM: 'truecolor',
        CLICOLOR_FORCE: '1'
      }
    })
    spawned.stdout.pipe(process.stdout)
    spawned.stderr.pipe(process.stderr)
    runInProgress = spawned

    spawned.on('error', (error) => {
      console.error(`Error running command: ${command}`, error)
    })

    spawned.on('close', (code) => {
      runInProgress = null
      if (code === 0) {
        resolve({
          message: `${command} completed successfully`,
          pass: true
        })
      } else {
        console.log(`Command [${command}] failed with code [${code}]`)
        resolve({
          pass: false
        })
      }
    })
  })
}

function highlightedLog (message, testsPassed) {
  console.log('')
  const messageWithBars = `│     ${message}     │`
  const emptyLine = '│'.padEnd(messageWithBars.length - 1, ' ') + '│'
  const top = '┌' + '─'.repeat(messageWithBars.length - 2) + '┐'
  const bottom = '└' + '─'.repeat(messageWithBars.length - 2) + '┘'
  const format = testsPassed ? '\x1b[32m' : '\x1b[31m'
  console.log(format, top, '\x1b[0m')
  console.log(format, emptyLine, '\x1b[0m')
  console.log(format, messageWithBars, '\x1b[0m')
  console.log(format, emptyLine, '\x1b[0m')
  console.log(format, bottom, '\x1b[0m')
}

async function runConditionalTests () {
  const timings = {}
  const fullStartTime = Date.now()
  if (runInProgress) {
    runInProgress.kill('SIGTERM')
  }
  console.log('')
  console.log('')
  console.log('')
  console.clear()

  for (const test of testsToRun) {
    const startTime = Date.now()
    const typeCheckResult = await runTests(test)
    timings[test] = Date.now() - startTime

    if (!typeCheckResult.pass) {
      highlightedLog(
        `Test run [${test}] failed in (${displayTime(timings[test])})`,
        false
      )
      return
    }
  }
  console.log('')
  console.log('')
  logTimingsTable(timings)
  console.log('')
  highlightedLog(
    `All tests passed in ${displayTime(Date.now() - fullStartTime)}`,
    true
  )
}

runConditionalTests()

const keepAliveInterval = setInterval(() => {

}, 999999)

const interruptHandler = (code) => () => {
  clearInterval(keepAliveInterval)
  if (runInProgress) {
    runInProgress.kill()
  }
  process.exit(code)
}

process.on('SIGINT', interruptHandler(0))
process.on('SIGTERM', interruptHandler(1))

process.stdin.on('data', (data) => {
  const str = data.toString().trim().toLowerCase()
  if (['exit', 'quit', 'q'].includes(str)) {
    return interruptHandler(0)()
  }
  if (['run', 'rerun', 'restart', 'r', 'rs'].includes(str)) {
    runConditionalTests()
  }
})

function displayTime (ms) {
  if (ms < 1000) {
    return `${ms}ms`
  }
  return `${Math.round(ms / 10) / 100}s`
}

function getSeparator (start, middle, end, maxKeyLength, maxValueLength) {
  return `${start}${'─'.repeat(maxKeyLength + 2)}${middle}${'─'.repeat(maxValueLength + 2)}${end}`
}

function logTimingsTable (timings) {
  const keys = Object.keys(timings).map(x => `${x}     `)
  const values = Object.values(timings).map(displayTime)
  const maxKeyLength = Math.max(...keys.map(k => k.length))
  const maxValueLength = Math.max(...values.map(v => v.length))
  const columnSeperator = '│'
  const header = ` ${columnSeperator} ${'Test'.padEnd(maxKeyLength)} ${columnSeperator} ${'Time'.padEnd(maxValueLength)} ${columnSeperator}`
  const topSeperator = getSeparator(' ┌', '┬', '┐', maxKeyLength, maxValueLength)
  const midSeperator = getSeparator(' ├', '┼', '┤', maxKeyLength, maxValueLength)
  const botSeperator = getSeparator(' └', '┴', '┘', maxKeyLength, maxValueLength)
  console.log(topSeperator)
  console.log(header)
  console.log(midSeperator)
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i]
    const value = values[i]
    console.log(` ${columnSeperator} ${key.padEnd(maxKeyLength)} ${columnSeperator} ${value.padEnd(maxValueLength)} ${columnSeperator}`)
  }
  console.log(botSeperator)
}
