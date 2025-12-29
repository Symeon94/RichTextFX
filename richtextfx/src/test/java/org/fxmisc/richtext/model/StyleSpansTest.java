package org.fxmisc.richtext.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StyleSpansTest {
    private void checkStyle(StyleSpans<String> s, int length, String[] styles, int... ranges) {
        new StyleSpansChecker<>(s).check(length, styles, ranges);
    }

    private StyleSpans<String> create(String style, int len) {
        StyleSpansBuilder<String> builder = new StyleSpansBuilder<>();
        builder.add(style, len);
        return builder.create();
    }


    @Nested
    @DisplayName("Append")
    class AppendTest {
        private StyleSpans<String> base;

        @BeforeEach
        void setup() {
            base = create("text", 10);
            checkStyle(base, 10, new String[]{"text"}, 0, 10);
        }

        @Test
        @DisplayName("Append empty style at the end of an existing style does not do anything")
        void appendEmpty() {
            checkStyle(base.append("text", 0), 10,
                    new String[]{"text"}, 0, 10);

        }

        @Test
        @DisplayName("Append on an empty style will create a new style with the provided values")
        void appendOnEmpty() {
            checkStyle(create("test", 0).append("text", 2), 2,
                    new String[]{"text"}, 0, 2);
        }

        @Test
        @DisplayName("Append a different style to an existing style span should add it at the end")
        void appendDifferentStyle() {
            checkStyle(base.append("else", 2), 12,
                    new String[]{"text", "else"}, 0, 10, 10, 12);
        }

        @Test
        @DisplayName("Append an existing style at the end of a style should extend the existing one")
        void appendSameStyle() {
            checkStyle(base.append("text", 2), 12,
                    new String[]{"text"}, 0, 12);
        }
    }
}
