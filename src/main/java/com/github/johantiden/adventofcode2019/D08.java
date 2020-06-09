package com.github.johantiden.adventofcode2019;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class D08 {




    static LayeredImage parse(String imageData, int width, int height) {
        List<String> layeredImageData = splitByLayer(imageData, width, height);

        List<LayeredImage.Layer> layers = layeredImageData.stream()
                .map(layerImageData -> parseLayer(layerImageData, width))
                .collect(Collectors.toList());


        return new LayeredImage(layers);
    }

    private static LayeredImage.Layer parseLayer(String layerImageData, int width) {
        List<String> rowsImageData = splitByRow(layerImageData, width);

        List<LayeredImage.Layer.Row> rows = rowsImageData.stream()
                .map(D08::parseRow)
                .collect(Collectors.toList());

        return new LayeredImage.Layer(rows);
    }

    static LayeredImage.Layer.Row parseRow(String rowData) {
        List<Integer> pixels = new ArrayList<>();
        for (int i = 0; i < rowData.length(); i++) {
            String c = ""+rowData.charAt(i);
            int asInt = Integer.parseInt(c);
            pixels.add(asInt);
        }
        return new LayeredImage.Layer.Row(pixels);
    }

    private static List<String> splitByRow(String layerImageData, int width) {
        int numRows = layerImageData.length() / width;


        List<String> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            String rowImageData = layerImageData.substring(i*width, (i+1)*width);
            rows.add(rowImageData);
        }
        return rows;
    }

    private static List<String> splitByLayer(String imageData, int width, int height) {
        int pixelsPerLayer = width * height;
        int numLayers = imageData.length() / pixelsPerLayer;


        List<String> layeredImageData = new ArrayList<>();
        for (int i = 0; i < numLayers; i++) {
            String layerImageData = imageData.substring(i*pixelsPerLayer, (i+1)*pixelsPerLayer);
            layeredImageData.add(layerImageData);
        }
        return layeredImageData;
    }


    static class LayeredImage {

        public static final int TRANSPARENT = 2;
        private final List<Layer> layers;

        LayeredImage(List<Layer> layers) {
            this.layers = layers;
        }

        long hash() {
            Layer layer = findLayerWithFewestZeroes();
            long countOnes = layer.rows.stream().flatMap(row -> row.pixels.stream())
                    .filter(pixel -> pixel == 1)
                    .count();
            long countTwos = layer.rows.stream().flatMap(row -> row.pixels.stream())
                    .filter(pixel -> pixel == 2)
                    .count();

            long answer = countOnes * countTwos;
            return answer;
        }

        private Layer findLayerWithFewestZeroes() {

            Layer best = layers.get(0);
            long bestZeroes = countZeroes(best);

            for (Layer layer : layers) {
                long l = countZeroes(layer);
                if (l < bestZeroes) {
                    best = layer;
                    bestZeroes = countZeroes(best);
                }
            }

            return best;

        }

        static long countZeroes(Layer layer) {
            long countZeroes = layer.rows.stream().flatMap(row -> row.pixels.stream())
                    .filter(pixel -> pixel == 0)
                    .count();
            return countZeroes;
        }

        private List<Layer> getLayers(Comparator<Layer> comparator) {
            return layers.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        public Layer blend() {
            int width = getWidth();
            int height = getHeight();

            Layer blended = createLayer(width, height);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    blended.set(x, y, get(x, y));
                }
            }
            return blended;
        }

        private int getHeight() {
            return layers.get(0).getHeight();
        }

        private int getWidth() {
            return layers.get(0).getWidth();
        }

        private int get(int x, int y) {
            for (int i = 0; i < layers.size(); i++) {
                int layerPixel = layers.get(i).get(x, y);
                if (layerPixel != TRANSPARENT) {
                    return layerPixel;
                }
            }

            return TRANSPARENT;
        }

        private static Layer createLayer(int width, int height) {
            IntFunction<Layer.Row> createNewRow = dummy -> new Layer.Row(
                    IntStream.generate(() -> 0).limit(width).boxed().collect(Collectors.toList())
            );

            List<Layer.Row> rows = IntStream.generate(() -> 0).limit(height).mapToObj(createNewRow).collect(Collectors.toList());
            return new Layer(
                    rows
            );
        }

        static class Layer {

            final List<Row> rows;

            Layer(List<Row> rows) {
                this.rows = rows;
            }

            public int get(int x, int y) {
                return rows.get(y).pixels.get(x);
            }

            public void set(int x, int y, int value) {
                rows.get(y).pixels.set(x, value);
            }

            public void print(Consumer<String> output) {
                for (int y = 0; y < getHeight(); y++) {
                    for (int x = 0; x < getWidth(); x++) {
                        String c = get(x, y) == 0 ? "■" : "⬜";
                        output.accept(c);
                    }
                    output.accept("\n");

                }
            }

            public int getHeight() {
                return rows.size();
            }

            public int getWidth() {
                return rows.get(0).pixels.size();
            }

            static class Row {
                final List<Integer> pixels;

                Row(List<Integer> pixels) {
                    this.pixels = pixels;
                }

                @Override
                public String toString() {
                    return String.join("", pixels.stream().map(i -> "" + i).collect(Collectors.toList()));
                }
            }

            @Override
            public String toString() {
                return String.join("\n", rows.stream().map(i -> "" + i).collect(Collectors.toList()));
            }
        }

        @Override
        public String toString() {
            return String.join("\n-------------\n", layers.stream().map(Object::toString).collect(Collectors.toList()));
        }
    }


}
