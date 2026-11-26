/*
55. Jump Game

You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5
*/

import java.util.Arrays;

class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int lastIndex = nums.length - 1;
        for(int i = 0; i < nums.length; i++){
            if(i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if(maxReach >= lastIndex) return true;
        }
        
        return true;
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    public static void runTest(int testNum, String description, int[] nums, boolean expected) {
        totalCount++;
        int[] numsCopy = Arrays.copyOf(nums, nums.length);
        Solution sol = new Solution();
        boolean actual = sol.canJump(numsCopy);

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
        runTest(1, "LeetCode Example 1 (Reachable)",
                new int[]{2, 3, 1, 1, 4},
                true);

        runTest(2, "LeetCode Example 2 (Trapped at zero)",
                new int[]{3, 2, 1, 0, 4},
                false);

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(3, "Constraint min length: nums.length == 1 (value 0)",
                new int[]{0},
                true);

        runTest(4, "Constraint min length: nums.length == 1 (value > 0)",
                new int[]{5},
                true);

        runTest(5, "Starts with 0 on multi-element array",
                new int[]{0, 2, 3},
                false);

        runTest(6, "Jump directly over zeros",
                new int[]{2, 0, 0},
                true);

        runTest(7, "Zero right before the last index (unreachable)",
                new int[]{1, 1, 0, 1},
                false);

        runTest(8, "Large jump from index 0 covering whole array",
                new int[]{100000, 0, 0, 0, 0},
                true);

        runTest(9, "All ones (minimum steady steps)",
                new int[]{1, 1, 1, 1, 1},
                true);

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}