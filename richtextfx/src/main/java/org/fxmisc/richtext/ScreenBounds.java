package org.fxmisc.richtext;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class ScreenBounds {
    private final Bounds areaScreen;

    public ScreenBounds(Bounds areaScreen) {
        this.areaScreen = areaScreen;
    }

    /**
     * Evaluate the paragraph node bounds in the area.
     * @param nodeScreen the paragraph bound position on screen
     * @return the bounds inside the area of the provided paragraph bounds.
     */
    public Bounds getParagraphBoundsFrom(Bounds nodeScreen) {
        // use area's minX if scrolled right and paragraph's left is not visible
        double minX = nodeScreen.getMinX() < areaScreen.getMinX()
                ? areaScreen.getMinX()
                : nodeScreen.getMinX();
        // use area's minY if scrolled down vertically and paragraph's top is not visible
        double minY = nodeScreen.getMinY() < areaScreen.getMinY()
                ? areaScreen.getMinY()
                : nodeScreen.getMinY();
        // use area's width whether paragraph spans outside of it or not
        // so that short or long paragraph takes up the entire space
        double width = areaScreen.getWidth();
        // use area's maxY if scrolled up vertically and paragraph's bottom is not visible
        double maxY = nodeScreen.getMaxY() < areaScreen.getMaxY()
                ? nodeScreen.getMaxY()
                : areaScreen.getMaxY();
        return new BoundingBox(minX, minY, width, maxY - minY);
    }
}
