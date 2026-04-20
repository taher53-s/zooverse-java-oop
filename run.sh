#!/usr/bin/env bash
set -e

if command -v mvn >/dev/null 2>&1; then
  mvn -q clean package
  mvn -q exec:java
else
  echo "Maven not found. Running with javac/java fallback..."
  mkdir -p out
  javac -d out $(find src/main/java -name '*.java')
  java -cp out com.taher.zooverse.Main
fi
