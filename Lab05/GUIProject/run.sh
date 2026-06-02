#!/usr/bin/env bash
set -euo pipefail

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
JAVAFX_HOME="${JAVAFX_HOME:-$HOME/SDKs/javafx-sdk-25.0.3}"
PATH_TO_FX="${PATH_TO_FX:-$JAVAFX_HOME/lib}"

"$PROJECT_DIR/build.sh"

java \
    --enable-native-access=javafx.graphics \
    --module-path "$PATH_TO_FX" \
    --add-modules javafx.controls,javafx.fxml \
    -cp "$PROJECT_DIR/bin" \
    hust.soict.dsai.javafx.Painter
