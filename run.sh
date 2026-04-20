#!/usr/bin/env bash
set -e

BUILD_ONLY=false
if [[ "${1:-}" == "--build-only" ]]; then
  BUILD_ONLY=true
fi

run_fallback() {
  echo "Using javac/java fallback..."
  mkdir -p out
  javac -d out $(find src/main/java -name '*.java')
  if [[ "$BUILD_ONLY" == "false" ]]; then
    java -cp out com.taher.zooverse.Main
  fi
}

# Try to pin Maven to a modern JDK on macOS (avoids 'invalid target release: 17')
if [[ "$(uname)" == "Darwin" ]] && command -v /usr/libexec/java_home >/dev/null 2>&1; then
  for v in 25 24 21 17; do
    CANDIDATE=$(/usr/libexec/java_home -v "$v" 2>/dev/null || true)
    if [[ -n "$CANDIDATE" ]]; then
      export JAVA_HOME="$CANDIDATE"
      export PATH="$JAVA_HOME/bin:$PATH"
      break
    fi
  done
fi

if command -v mvn >/dev/null 2>&1; then
  echo "Using Maven with JAVA_HOME=${JAVA_HOME:-<not-set>}"
  if mvn -q clean package; then
    if [[ "$BUILD_ONLY" == "false" ]]; then
      mvn -q exec:java
    fi
  else
    echo "Maven build failed; switching to fallback compiler..."
    run_fallback
  fi
else
  echo "Maven not found."
  run_fallback
fi
