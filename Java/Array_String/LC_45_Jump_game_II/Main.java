/*
45. Jump Game II

You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n

Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
*/

import java.util.Arrays;

class Solution {
    public int jump(int[] nums) {
        int maxReach = 0;
        int currentReach = 0;
        int jumps = 0;
        for(int i = 0; i < nums.length - 1; i++){
                maxReach = Math.max(maxReach, nums[i] + i);
                if(i == currentReach){
                        jumps++;
                        currentReach = maxReach;
                }
        }
        
        return jumps;
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    public static void runTest(int testNum, String description, int[] nums, int expected) {
        totalCount++;
        int[] numsCopy = Arrays.copyOf(nums, nums.length);
        Solution sol = new Solution();
        int actual = sol.jump(numsCopy);

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
        runTest(1, "LeetCode Example 1 (Standard jump)",
                new int[]{2, 3, 1, 1, 4},
                2);

        runTest(2, "LeetCode Example 2 (Jump over zero)",
                new int[]{2, 3, 0, 1, 4},
                2);

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(3, "Constraint min length: nums.length == 1 (already at end)",
                new int[]{0},
                0);

        runTest(4, "Constraint min length: nums.length == 1 (positive value)",
                new int[]{5},
                0);

        runTest(5, "Two elements (single jump needed)",
                new int[]{1, 0},
                1);

        runTest(6, "Single large jump from index 0 covers entire array",
                new int[]{10, 1, 1, 1, 1},
                1);

        runTest(7, "All ones (maximum possible jumps = n - 1)",
                new int[]{1, 1, 1, 1, 1},
                4);

        runTest(8, "Multiple zeros in middle requiring careful reach",
                new int[]{3, 0, 0, 2, 0, 1},
                3);

        runTest(9, "Optimal jump lands earlier to gain larger reach later",
                new int[]{2, 4, 1, 1, 1, 1},
                2);

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}