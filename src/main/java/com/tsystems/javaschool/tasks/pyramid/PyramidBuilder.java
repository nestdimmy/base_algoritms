package com.tsystems.javaschool.tasks.pyramid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException {
       try {
           if (checkForNull(inputNumbers)) {
               inputNumbers.sort(Comparator.naturalOrder());
           } else throw new CannotBuildPyramidException();
       }catch (OutOfMemoryError e){
           throw new CannotBuildPyramidException();
       }
        //calumns and rows calculator
        int count = 0;
        int rows = 1;
        int columns = 1;
        boolean isTriangle = false;

        while (count < inputNumbers.size()) {
            count = count + rows;
            rows++;
            columns = columns + 2;
        }
        columns = columns - 2;
        rows = rows - 1;

        if (count == inputNumbers.size()) {
            isTriangle = true;
        }
        if (isTriangle) {
            int[][] massive = new int[rows][columns];

            for (int[] element : massive) {
                Arrays.fill(element, 0);
            }
            int center = (columns / 2);
            count = 1;
            int arrIdx = 0;

            for (int i = 0, offset = 0; i < rows; i++, offset++, count++) {
                int start = center - offset;
                for (int j = 0; j < count * 2; j += 2, arrIdx++) {
                    massive[i][start + j] = inputNumbers.get(arrIdx);
                }
            }

            return massive;
        } else throw new CannotBuildPyramidException();
    }

    public static boolean checkForNull(List<Integer> inputList) {
        boolean result = true;
        for (Integer element : inputList) {
            if (element == null) result = false;
        }
        return result;
    }
}



