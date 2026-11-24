/*
122. Best Time to Buy and Sell Stock II

You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 

Constraints:

1 <= prices.length <= 3 * 10^4
0 <= prices[i] <= 10^4
*/

import java.util.Arrays;

class Solution {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;
        for(int i = 1; i <= prices.length -1; i++){
            if(prices[i] > prices[i-1]){
                totalProfit += prices[i] - prices[i-1];

            }
        }
        
        return totalProfit;
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    public static void runTest(int testNum, String description, int[] prices, int expected) {
        totalCount++;
        int[] pricesCopy = Arrays.copyOf(prices, prices.length);
        Solution sol = new Solution();
        int actual = sol.maxProfit(pricesCopy);

        boolean isPass = (actual == expected);
        if (isPass) passedCount++;

        System.out.println("Test " + testNum + " (" + description + "): " + (isPass ? "PASS" : "FAIL"));
        if (!isPass) {
            System.out.println("   Input   : " + Arrays.toString(prices));
            System.out.println("   Output  : " + actual);
            System.out.println("   Expected: " + expected);
        }
    }

    public static void main(String[] args) {
        // --- 3 LEETCODE OFFICIAL EXAMPLES ---
        runTest(1, "LeetCode Example 1 (Multiple transactions)",
                new int[]{7, 1, 5, 3, 6, 4},
                7);

        runTest(2, "LeetCode Example 2 (Strictly increasing)",
                new int[]{1, 2, 3, 4, 5},
                4);

        runTest(3, "LeetCode Example 3 (Strictly decreasing)",
                new int[]{7, 6, 4, 3, 1},
                0);

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(4, "Constraint min length: prices.length == 1",
                new int[]{5},
                0);

        runTest(5, "All prices are identical",
                new int[]{3, 3, 3, 3},
                0);

        runTest(6, "Two elements with profit",
                new int[]{2, 9},
                7);

        runTest(7, "Two elements with loss",
                new int[]{9, 2},
                0);

        runTest(8, "Alternating peaks and valleys (Zig-zag)",
                new int[]{1, 5, 2, 8, 3, 10},
                17);

        runTest(9, "Constraint boundaries for values (0 to 10^4)",
                new int[]{0, 10000, 0, 10000},
                20000);

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}