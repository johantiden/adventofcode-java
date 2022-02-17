package com.github.johantiden.adventofcode.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public abstract class Graphics<T> {

    public Matrix<T> render() {
        RectangleInt clip = areaOfInterest()
                .orElseThrow(() -> new IllegalStateException("Infinite Graphics cannot be rendered! Please restrict the viewport"));

        List<List<T>> rendered = new ArrayList<>(clip.height() + 1);

        for (int y = clip.top(); y <= clip.bottom(); y++) {
            ArrayList<T> row = new ArrayList<>(clip.width() + 1);
            for (int x = clip.left(); x <= clip.right(); x++) {
                row.add(get(x, y));
            }
            rendered.add(row);
        }

        JList<JList<T>> jListJList = JList.copyOf(rendered)
                .map(JList::copyOf);

        return Matrix.of(jListJList);
    }

    protected abstract Optional<RectangleInt> areaOfInterest();

    abstract T get(int x, int y);

    public static class Threshold<T> extends Graphics<T> {
        private final Predicate<T> predicate;
        private final Graphics<T> input;
        private final Graphics<T> ifTrue;
        private final Graphics<T> ifFalse;

        public Threshold(Predicate<T> predicate, Graphics<T> input, Graphics<T> ifTrue, Graphics<T> ifFalse) {
            this.predicate = predicate;
            this.input = input;
            this.ifTrue = ifTrue;
            this.ifFalse = ifFalse;
        }

        @Override
        protected Optional<RectangleInt> areaOfInterest() {
            return input.areaOfInterest();
        }

        @Override
        T get(int x, int y) {
            if (predicate.test(input.get(x, y))) {
                return ifTrue.get(x, y);
            } else {
                return ifFalse.get(x, y);
            }
        }
    }

    public static class Rectangle<T> extends Graphics<T> {
        private final RectangleInt bounds;
        private final T colorInside;
        private final T colorOutside;

        public Rectangle(RectangleInt bounds, T colorInside, T colorOutside) {
            this.bounds = bounds;
            this.colorInside = colorInside;
            this.colorOutside = colorOutside;
        }

        @Override
        T get(int x, int y) {
            return bounds.contains(x, y) ?
                    colorInside : colorOutside;
        }

        @Override
        protected Optional<RectangleInt> areaOfInterest() {
            return Optional.of(bounds);
        }
    }

    public static class Blend<T> extends Graphics<T> {
        private final JList<Graphics<T>> layers;
        private final BinaryOperator<T> blender;

        private Blend(JList<Graphics<T>> layers, BinaryOperator<T> blender) {
            this.layers = layers;
            this.blender = blender;
        }

        public static <T> Graphics<T> blend(JList<Graphics<T>> layers, BinaryOperator<T> blender) {

            JList<Graphics<T>> flattened = layers.mapMulti((g, c) -> {
                        if (g instanceof Graphics.Blend<T> && ((Blend<T>) g).blender.equals(blender)) {
                            JList<Graphics<T>> childLayers = ((Blend<T>) g).layers;
                            childLayers.forEach(c);
                        } else {
                            c.accept(g);
                        }
                    });

            return new Blend<>(flattened, blender);
        }

        @Override
        T get(int x, int y) {
            JList<T> pixels = layers.map(g -> g.get(x, y));
            return pixels.tail().reduce(pixels.get(0), blender);
        }

        @Override
        protected Optional<RectangleInt> areaOfInterest() {
            return layers.map(Graphics::areaOfInterest)
                    .reduce(Optional.empty(), (under, over) -> Optionals.reduceOptional(under, over, RectangleInt::union));
        }
    }

    public static class Pixel<T> extends Graphics<T> {
        private final PointInt pixel;
        private final T colorInside;
        private final T colorOutside;

        public Pixel(PointInt pixel, T colorInside, T colorOutside) {
            this.pixel = pixel;
            this.colorInside = colorInside;
            this.colorOutside = colorOutside;
        }

        @Override
        protected Optional<RectangleInt> areaOfInterest() {
            return Optional.of(new RectangleInt(pixel.x(), pixel.y(), 0, 0));
        }

        @Override
        T get(int x, int y) {
            if (pixel.x() == x && pixel.y() == y) {
                return colorInside;
            } else {
                return colorOutside;
            }
        }
    }

    public static class Solid<T> extends Graphics<T> {
        private final T color;

        public Solid(T color) {
            this.color = color;
        }

        @Override
        protected Optional<RectangleInt> areaOfInterest() {
            return Optional.empty();
        }

        @Override
        T get(int x, int y) {
            return color;
        }
    }
}
