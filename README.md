# asynp

A Clojure core.async wrapper around the excellent [NuProcess library by Brett Wooldridge](https://github.com/brettwooldridge/NuProcess/) for efficiently interacting with a potentially large number of subprocesses concurrently.

## Installation

Add the following dependency to your `project.clj` file

    [asynp "0.0.1"]

## Usage

The following is a roughly complete demonstration of features and usage:

```
(require '[asynp.core :refer [run-process log-strings decode-chars write-str-to-process close-stdin-for-process wait-for-process]])
(let [process-dict (run-process ["/bin/cat"])]

  ;; set up readers for stderr and stdout
  (log-strings (decode-chars (:out process-dict)) \u0000)
  (log-strings (decode-chars (:err process-dict)) \newline)

  ;; write content
  (write-str-to-process process-dict "Hello world\u0000Partial write")
  (Thread/sleep 1000)
  (write-str-to-process process-dict "...completed\u0000")

  ;; close stdin
  (close-stdin-for-process process-dict)

  ;; wait for the process to exit (optional)
  (wait-for-process process-dict))
```

## License

Copyright © 2014 ThreatGRID, Inc.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.