package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) throws RuntimeException{

        int xIterator = 0;
        int yIterator = 0;
            if (x==null || y==null) {
                throw new IllegalArgumentException();}
                try {
                    for (int i = 0; i < y.size() - 1; i++) {
                        if (y.get(i).equals(x.get(xIterator))) {
                            yIterator++;
                            xIterator++;
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("first sequence is empty");                }
        if((yIterator == x.size()) || x.size() == 0)return true;
        return false;
    }
}
