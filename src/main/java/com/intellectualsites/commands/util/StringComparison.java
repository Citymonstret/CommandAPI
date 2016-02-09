package com.intellectualsites.commands.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StringComparison<T> {

    private T bestMatch;
    private double match = Integer.MAX_VALUE;
    private T bestMatchObject;

    /**
     * Constructor
     *
     * @param input   Input Base Value
     * @param objects Objects to compare
     */
    public StringComparison(final String input, final T[] objects) {
        init(input, objects);
    }

    public StringComparison(final String input, final Collection<T> objects) {
        init(input, (T[]) objects.toArray());
    }

    /**
     * You should call init(...) when you are ready to get a String comparison value
     */
    public StringComparison() {
    }

    public void init(String input, final T[] objects) {
        int c;
        this.bestMatch = objects[0];
        this.bestMatchObject = objects[0];
        input = input.toLowerCase();
        for (final T o : objects) {
            if ((c = compare(input, getString(o).toLowerCase())) < this.match) {
                this.match = c;
                this.bestMatch = o;
                this.bestMatchObject = o;
            }
        }
    }

    public String getString(final T o) {
        return o.toString();
    }



    /**
     * Compare two strings
     *
     * @param s1 String Base
     * @param s2 Object
     * @return match
     */
    public static int compare(final String s1, final String s2) {
        int distance = StringUtil.getLevenshteinDistance(s1, s2);
        if (s2.contains(s1)) {
            distance -= (Math.min(s1.length(), s2.length()));
        }
        if (s2.startsWith(s1)) {
            distance -= 4;
        }
        return distance;
    }

    /**
     * Create an ArrayList containing pairs of letters
     *
     * @param s string to split
     * @return ArrayList
     */
    public static ArrayList<String> wLetterPair(final String s) {
        final ArrayList<String> aPairs = new ArrayList<String>();
        final String[] wo = s.split("\\s");
        for (final String aWo : wo) {
            final String[] po = sLetterPair(aWo);
            Collections.addAll(aPairs, po);
        }
        return aPairs;
    }

    /**
     * Get an array containing letter pairs
     *
     * @param s string to split
     * @return Array
     */
    public static String[] sLetterPair(final String s) {
        final int numPair = s.length() - 1;
        final String[] p = new String[numPair];
        for (int i = 0; i < numPair; i++) {
            p[i] = s.substring(i, i + 2);
        }
        return p;
    }

    /**
     * Get the object
     *
     * @return match object
     */
    public T getMatchObject() {
        return this.bestMatchObject;
    }

    /**
     * Get the best match value
     *
     * @return match value
     */
    public String getBestMatch() {
        return getString(this.bestMatch);
    }

    /**
     * Will return both the match number, and the actual match string
     *
     * @return object[] containing: double, String
     */
    public ComparisonResult getBestMatchAdvanced() {
        return new ComparisonResult(this.match, this.bestMatch);
    }

    /**
     * The comparison result
     */
    public class ComparisonResult {

        public final T best;
        public final double match;

        /**
         * The constructor
         *
         * @param match Match value
         * @param best  Best Match
         */
        public ComparisonResult(final double match, final T best) {
            this.match = match;
            this.best = best;
        }
    }
}