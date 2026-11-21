/*
169. Majority Element

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. 
You may assume that the majority element always exists in the array.

 
Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
 

Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
The input is generated such that a majority element will exist in the array.
 

Follow-up: Could you solve the problem in linear time and in O(1) space?
*/

import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> counter = new HashMap<>();
        int appearance = nums.length / 2;
        for(int num : nums){
            int currentcount = counter.getOrDefault(num, 0) + 1;
            if(currentcount > appearance) return num;
            counter.put(num, currentcount);
        }
        return -1;
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    public static void runTest(int testNum, String description, int[] nums, int expected) {
        totalCount++;
        int[] numsCopy = Arrays.copyOf(nums, nums.length);
        Solution sol = new Solution();
        int actual = sol.majorityElement(numsCopy);

        boolean isPass = (actual == expected);
        if (isPass) passedCount++;

        System.out.println("Test " + testNum + " (" + description + "): " + (isPass ? "PASS" : "FAIL"));
        if (!isPass) {
            System.out.println("   Input   : " + Arrays.toString(nums));
            System.out.println("   Output  : " + actual);
            System.out.println("   Expected: " + expected);
        }
    }

    public static void main(String[] args) {
        // --- 2 LEETCODE OFFICIAL EXAMPLES ---
        runTest(1, "LeetCode Example 1",
                new int[]{3, 2, 3},
                3);

        runTest(2, "LeetCode Example 2",
                new int[]{2, 2, 1, 1, 1, 2, 2},
                2);

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(3, "Constraint min length: nums.length == 1",
                new int[]{1},
                1);

        runTest(4, "All elements are identical",
                new int[]{7, 7, 7, 7, 7},
                7);

        runTest(5, "Negative numbers and constraint boundaries (-10^9 to 10^9)",
                new int[]{-1000000000, 1000000000, -1000000000, 0, -1000000000},
                -1000000000);

        runTest(6, "Majority element appears exactly floor(n / 2) + 1 times at the end",
                new int[]{1, 2, 3, 4, 4, 4, 4},
                4);

        runTest(7, "Majority element appears exactly floor(n / 2) + 1 times at the start",
                new int[]{5, 5, 5, 5, 1, 2, 3},
                5);

        runTest(8, "Alternating elements with majority winning by 1",
                new int[]{1, 2, 1, 2, 1},
                1);

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}