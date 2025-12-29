package org.fxmisc.richtext.model;

import java.util.Objects;
import java.util.Optional;

/**
 * Essentially, a {@link org.reactfx.util.Tuple2} of a given style {@link S} that spans a given length.
 *
 * @param <S> the style type
 */
public class StyleSpan<S> {
    private final S style;
    private final int length;
    private final int start;

    /**
     * Creates a style span. Note: length cannot be negative.
     */
    public StyleSpan(S style, int length) {
        this(style, 0, length);
    }

    StyleSpan(S style, int start, int length) {
        if(length < 0) {
            throw new IllegalArgumentException("StyleSpan's length cannot be negative");
        }
        this.style = style;
        this.start = start;
        this.length = length;
    }

    /**
     * If the provided argument is of the same size append the content
     * @param next the item to be appended
     * @return an empty optional if styles differs, else a new {@link StyleSpan} with the two concatenated length
     */
    public Optional<StyleSpan<S>> append(StyleSpan<S> next) {
        if(Objects.equals(this.style, next.style)) {
            return Optional.of(new StyleSpan<>(this.style, this.start, this.length + next.length));
        }
        return Optional.empty();
    }

    public S getStyle() {
        return style;
    }

    public int getLength() {
        return length;
    }

    public StyleSpan<S> moveTo(int start) {
        return new StyleSpan<>(style, start, length);
    }

    int getStart() {
        return start;
    }

    /**
     * Two {@code StyleSpan}s are considered equal if they have equal length and
     * equal style.
     */
    @Override
    public boolean equals(Object other) {
        if(other instanceof StyleSpan) {
            StyleSpan<?> that = (StyleSpan<?>) other;
            return this.length == that.length
                    && Objects.equals(this.style, that.style);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(style, length);
    }

    @Override
    public String toString() {
        return String.format("StyleSpan[length=%s, style=%s]", length, style);
    }
}
