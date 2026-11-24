/*
121. Best Time to Buy and Sell Stock

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 

Constraints:

1 <= prices.length <= 10^5
0 <= prices[i] <= 10^4
*/

import java.util.Arrays;

class Solution {
    public int maxProfit(int[] prices) {
        int day_buy = 0;
        int day_sell = 1;
        int maxProfit = 0;
        while(day_sell < prices.length){
            if(prices[day_buy] < prices[day_sell]){
                int currentProfit = prices[day_sell] - prices[day_buy];
                maxProfit = Math.max(currentProfit, maxProfit);
            }
            else {
                day_buy = day_sell;
                
            }
            day_sell++;
        }
        
        return maxProfit;
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
        // --- 2 LEETCODE OFFICIAL EXAMPLES ---
        runTest(1, "LeetCode Example 1",
                new int[]{7, 1, 5, 3, 6, 4},
                5);

        runTest(2, "LeetCode Example 2 (Strictly decreasing)",
                new int[]{7, 6, 4, 3, 1},
                0);

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(3, "Constraint min length: prices.length == 1",
                new int[]{5},
                0);

        runTest(4, "Min price appears at the very end (cannot sell after buying)",
                new int[]{2, 4, 1},
                2);

        runTest(5, "All prices are identical",
                new int[]{4, 4, 4, 4},
                0);

        runTest(6, "Strictly increasing prices (Buy first day, sell last day)",
                new int[]{1, 2, 3, 4, 5},
                4);

        runTest(7, "Two elements with profit",
                new int[]{1, 2},
                1);

        runTest(8, "Constraint boundaries for values (0 to 10^4)",
                new int[]{10000, 0, 10000},
                10000);

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}