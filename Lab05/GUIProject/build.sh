#!/usr/bin/env bash
set -euo pipefail

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
JAVAFX_HOME="${JAVAFX_HOME:-$HOME/SDKs/javafx-sdk-25.0.3}"
PATH_TO_FX="${PATH_TO_FX:-$JAVAFX_HOME/lib}"

if [[ ! -d "$PATH_TO_FX" ]]; then
    echo "JavaFX lib directory not found: $PATH_TO_FX" >&2
    echo "Install JavaFX 25.0.3 to $HOME/SDKs/javafx-sdk-25.0.3, or set JAVAFX_HOME." >&2
    exit 1
fi

rm -rf "$PROJECT_DIR/bin"
mkdir -p "$PROJECT_DIR/bin"

find "$PROJECT_DIR/src" -name '*.java' -print0 | xargs -0 javac \
    --module-path "$PATH_TO_FX" \
    --add-modules javafx.controls,javafx.fxml \
    -d "$PROJECT_DIR/bin"

find "$PROJECT_DIR/src" -name '*.fxml' -print0 | while IFS= read -r -d '' file; do
    relative_path="${file#"$PROJECT_DIR/src/"}"
    mkdir -p "$PROJECT_DIR/bin/$(dirname "$relative_path")"
    cp "$file" "$PROJECT_DIR/bin/$relative_path"
done

echo "Built GUIProject into $PROJECT_DIR/bin"
