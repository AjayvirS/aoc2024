package aoc;

import java.util.Arrays;

public class RedNosedReports {


    static void solveA(TwoDimensionalList<Integer> data) {
        int safeReports = 0;

        for (int i = 0; i < data.rows().size(); i++) {
            boolean isReportSafe = true;
            for (int j = 1; j < data.rows().get(i).length - 1; j++) {
                int prev = data.rows().get(i)[j - 1];
                int curr = data.rows().get(i)[j];
                int next = data.rows().get(i)[j + 1];

                if (!isValidMonotonic(prev, curr) || ((prev <= curr && curr >= next) || (prev >= curr && curr <= next))) {
                    isReportSafe = false;
                    break;
                }
            }
            int[] currRow = data.rows().get(i);

            if (isValidMonotonic(currRow[currRow.length - 2], currRow[currRow.length - 1]) && isReportSafe) {
                safeReports += 1;
            }
        }

        System.out.println("Safe reports: " + safeReports);


    }

    static void solveB(TwoDimensionalList<Integer> data) {
        int safeReports = 0;

        for (int[] currRow : data.rows()) {
            if (currRow.length < 2) continue;

            int startAtIndex = firstNonZeroDifference(currRow);
            if (startAtIndex == -1) {
                // all equal -> cannot become strictly monotone with a single removal
                continue;
            }

            // Count leading zero-diff steps we just skipped past.
            int leadingZeroSteps = startAtIndex - 1;
            if (leadingZeroSteps >= 2) {
                // Needs >=2 removals to clear equalities -> impossible
                continue;
            }

            // If exactly 1 leading equality, we MUST spend our one skip now.
            boolean forceUsedSkip = (leadingZeroSteps == 1);

            // Determine initial trend from the first non-zero pair
            boolean isIncreasing = currRow[startAtIndex] - currRow[startAtIndex - 1] > 0;

            boolean ok =
                    checkOneSkip(currRow, startAtIndex, isIncreasing, forceUsedSkip) ||
                            checkOneSkip(currRow, startAtIndex, !isIncreasing, forceUsedSkip);

            if (ok) safeReports++;
        }

        System.out.println("Safe reports with one allowed skip: " + safeReports);
    }

    private static boolean checkOneSkip(int[] currRow, int startAtIndex, boolean isIncreasing, boolean usedSkipInit) {
        boolean usedSkip = usedSkipInit;
        boolean isReportSafe = true;

        int prev = startAtIndex - 1;
        int j = startAtIndex;

        while (j < currRow.length) {
            int d = currRow[j] - currRow[prev];

            if (Integer.signum(d) == (isIncreasing ? 1 : -1) && isValidMonotonic(currRow[prev], currRow[j])) {
                prev = j;
                j++;
                continue;
            }

            if (usedSkip) { isReportSafe = false; break; }

            if (j == currRow.length - 1) {
                usedSkip = true;
                break;
            }

            // Try skipping current j: compare prev -> j+1
            if (j + 1 < currRow.length) {
                int dSkipJ = currRow[j + 1] - currRow[prev];
                if (Integer.signum(dSkipJ) == (isIncreasing ? 1 : -1)
                        && isValidMonotonic(currRow[prev], currRow[j + 1])) {
                    usedSkip = true;
                    prev = j + 1;
                    j += 2;
                    continue;
                }
            }

            if (prev == 0) {
                usedSkip = true;
                prev = j;
                j++;
                continue;
            }

            int dSkipPrev = currRow[j] - currRow[prev - 1];
            if (Integer.signum(dSkipPrev) == (isIncreasing ? 1 : -1)
                    && isValidMonotonic(currRow[prev - 1], currRow[j])) {
                usedSkip = true;
                prev = j;
                j++;
                continue;
            }

            isReportSafe = false;
            break;
        }

        return isReportSafe;
    }





    private static int firstNonZeroDifference(int[] row) {
        for (int i = 1; i < row.length; i++) {
            if (Math.abs(row[i] - row[i - 1]) > 0) {
                return i;
            }
        }
        return -1;
    }

    static boolean isValidMonotonic(int prev, int curr) {
        int difference = Math.abs(curr - prev);
        return (difference == 1 || difference == 2 || difference == 3);
    }

}
