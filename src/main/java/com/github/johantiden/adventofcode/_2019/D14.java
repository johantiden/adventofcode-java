package com.github.johantiden.adventofcode._2019;

import com.google.common.base.Preconditions;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class D14 {


    public static final long ORE_LIMIT = 1000000000000l;

    public static long solve(List<Recipe> recipesList) {
        final Map<String, Long> stock = new HashMap<>();

        solveOneFuel(recipesList, stock);

        return stock.get("ORE") * -1;
    }

    public static long solve2(List<Recipe> recipesList) {
        List<Recipe> metaRecipies = new ArrayList<>();
        final Map<String, Long> stock = new HashMap<>();

        stock.put("ORE", 0l);

        while(stock.get("ORE") > -ORE_LIMIT) {
            if (isMetaRecipeApplicable(stock, metaRecipies, ORE_LIMIT)) {
                Recipe bestMetaRecipe = findBestMetaRecipe(stock, metaRecipies, ORE_LIMIT);
                applyRecipe(bestMetaRecipe, stock, 1);
            }

            metaRecipies.add(createMetaRecipe(stock));

            if (stock.get("ORE") > -ORE_LIMIT) {
                solveOneFuel(recipesList, stock);
            }

            metaRecipies.add(createMetaRecipe(stock));
            cleanupObsoleteRecipes(ORE_LIMIT, stock, metaRecipies);
        }

        return stock.get("FUEL");
    }

    private static void cleanupObsoleteRecipes(long oreLimit, Map<String, Long> stock, List<Recipe> metaRecipies) {
        long oreLeft = oreLimit + stock.get("ORE"); // ORE is negative
        metaRecipies.removeIf(
                r -> getSingleValue(r.inputs) > oreLeft
        );

        metaRecipies.removeIf(
                r -> r.outputs.isEmpty()
        );
    }

    private static Recipe findBestMetaRecipe(Map<String, Long> stock, List<Recipe> metaRecipies, long oreLimit) {
        return metaRecipies.stream()
                .max(Comparator.comparing(r -> {
                    long oreCost = r.inputs.values().iterator().next();

                    long sumOutputs = r.outputs.values().stream().mapToLong(l -> l).sum();
                    long fuelOutput = r.outputs.get("FUEL");

                    long leftOvers = sumOutputs - fuelOutput;

                    double oreSpentPerLeftovers = oreCost / (double)leftOvers;
                    return oreSpentPerLeftovers;
                }))
                .orElseThrow(() -> new RuntimeException("Expected at least one metaRecipe"));
    }

    private static boolean isMetaRecipeApplicable(Map<String, Long> stock, List<Recipe> metaRecipies, long oreLimit) {
        return metaRecipies.size() > 0;
    }

    private static Recipe createMetaRecipe(Map<String, Long> stock) {
        HashMap<String, Long> copy = new HashMap<>(stock);
        copy.remove("ORE");

        HashMap<String, Long> inputOre = new HashMap<>();
        inputOre.put("ORE", stock.get("ORE") * -1);

        return new Recipe(inputOre, copy);
    }

    private static void solveOneFuel(List<Recipe> recipesList, Map<String, Long> stock) {
        Recipe fuelRecipe = recipesList.stream().filter(r -> r.getSingleOutput().equals("FUEL")).findAny().get();
        applyRecipe(fuelRecipe, stock, 1);

        while (hasNegative(stock)) {
            Recipe recipe = findRecipeForNegative(stock, recipesList);

            long negativeStock = stock.get(recipe.getSingleOutput());

            long numberOfTimesToApplyRecipe = (long) Math.ceil(-1 * negativeStock / (double) recipe.getSingleOutputValue());

            applyRecipe(recipe, stock, numberOfTimesToApplyRecipe);
        }
        boolean a = true;
    }

    private static void applyRecipe(Recipe recipe, Map<String, Long> stock, long numberOfTimesToApplyRecipe) {
        recipe.outputs.entrySet().forEach(e -> put(e, numberOfTimesToApplyRecipe, stock));
        recipe.inputs.entrySet().forEach(e -> put(e, -numberOfTimesToApplyRecipe, stock));
    }

    private static void put(Map.Entry<String, Long> entry, long multiplier, Map<String, Long> stock) {
        stock.putIfAbsent(entry.getKey(), 0l);
        stock.compute(entry.getKey(), (oldKey, oldValue) -> oldValue + entry.getValue()*multiplier);
    }

    private static Recipe findRecipeForNegative(Map<String, Long> stock, List<Recipe> recipesList) {
        String key = stock.entrySet().stream()
                .filter(e -> !e.getKey().equals("ORE") && e.getValue() < 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no negatives, boo!"))
                .getKey();

        return findRecipe(key, recipesList);
    }

    private static Recipe findRecipe(String key, List<Recipe> recipesList) {
        return recipesList.stream()
                .filter(r -> r.getSingleOutput().equals(key))
                .findAny()
                .orElseThrow(() -> new RuntimeException("expected to find " + key + " recipe, but it didn't exist."));
    }

    private static boolean hasNegative(Map<String, Long> stock) {
        return stock.entrySet().stream()
                .anyMatch(e -> !e.getKey().equals("ORE") && e.getValue() < 0);
    }

    private static <T> T getSingleValue(Map<?, T> map) {
        Preconditions.checkArgument(map.size() == 1);
        return map.values().iterator().next();
    }

    static class Parser {
        static List<Recipe> parse(String input) {

            String[] rows = input.split("\n");

            return Arrays.stream(rows)
                    .map(Parser::parseRow)
                    .collect(Collectors.toList());

        }

        static Recipe parseRow(String row) {
            String[] split = row.split(" => ");
            String left = split[0];
            String right = split[1];

            return new Recipe(
                    parseEntries(left),
                    parseEntryAsMap(right));
        }

        private static Map<String, Long> parseEntries(String part) {
            Map<String, Long> ingredients = new HashMap<>();

            String[] split = part.split(", ");
            Arrays.stream(split)
                    .forEach(
                            pair ->
                            {
                                Map.Entry<String, Long> entry = parseEntry(pair);
                                ingredients.put(entry.getKey(), entry.getValue());
                            });

            return ingredients;
        }

        private static Map<String, Long> parseEntryAsMap(String pair) {
            String[] s = pair.split(" ");
            long quantity = Long.parseLong(s[0]);
            String name = s[1];

            HashMap<String, Long> map = new HashMap<>();
            map.put(name, quantity);
            return map;
        }

        private static Map.Entry<String, Long> parseEntry(String pair) {
            String[] s = pair.split(" ");
            long quantity = Integer.parseInt(s[0]);
            String name = s[1];
            return new AbstractMap.SimpleImmutableEntry<>(name, quantity);
        }
    }

    static class Recipe {
        final Map<String, Long> inputs;
        final Map<String, Long> outputs;

        Recipe(Map<String, Long> inputs, Map<String, Long> outputs) {
            this.inputs = inputs;
            this.outputs = outputs;
        }

        String getSingleOutput() {
            Preconditions.checkArgument(outputs.size() == 1);
            return outputs.keySet().iterator().next();
        }

        Long getSingleOutputValue() {
            return getSingleValue(outputs);
        }

        @Override
        public String toString() {
            return "Recipe{" +
                    "inputs=" + inputs +
                    ", output=" + outputs +
                    '}';
        }
    }
}
