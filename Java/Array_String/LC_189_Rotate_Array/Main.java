/*
189. Rotate Array

Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

 

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 

Constraints:

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105
 

Follow up:

Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
Could you do it in-place with O(1) extra space?
*/

import java.util.Arrays;

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if(k == 0) return;
        reverse_array(nums, 0, n-1);
        reverse_array(nums, 0, k-1);
        reverse_array(nums, k, n-1);
        
    }

    private void reverse_array(int[] nums, int pt_left, int pt_right){
        while(pt_left < pt_right){
            int tmp = nums[pt_left];
            nums[pt_left] = nums[pt_right];
            nums[pt_right] = tmp;
            pt_left++;
            pt_right--;
        }
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    public static void runTest(int testNum, String description, int[] nums, int k, int[] expected) {
        totalCount++;
        int[] numsCopy = Arrays.copyOf(nums, nums.length);
        Solution sol = new Solution();
        sol.rotate(numsCopy, k);

        boolean isPass = Arrays.equals(numsCopy, expected);
        if (isPass) passedCount++;

        System.out.println("Test " + testNum + " (" + description + "): " + (isPass ? "PASS" : "FAIL"));
        if (!isPass) {
            System.out.println("   Input   : " + Arrays.toString(nums) + ", k = " + k);
            System.out.println("   Output  : " + Arrays.toString(numsCopy));
            System.out.println("   Expected: " + Arrays.toString(expected));
        }
    }

    public static void main(String[] args) {
        // --- 2 LEETCODE OFFICIAL EXAMPLES ---
        runTest(1, "LeetCode Example 1",
                new int[]{1, 2, 3, 4, 5, 6, 7}, 3,
                new int[]{5, 6, 7, 1, 2, 3, 4});

        runTest(2, "LeetCode Example 2",
                new int[]{-1, -100, 3, 99}, 2,
                new int[]{3, 99, -1, -100});

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(3, "Constraint min length: nums.length == 1",
                new int[]{1}, 5,
                new int[]{1});

        runTest(4, "No rotation needed: k == 0",
                new int[]{1, 2, 3, 4}, 0,
                new int[]{1, 2, 3, 4});

        runTest(5, "k is an exact multiple of nums.length",
                new int[]{1, 2, 3}, 6,
                new int[]{1, 2, 3});

        runTest(6, "k is greater than nums.length (k > n)",
                new int[]{1, 2, 3, 4, 5}, 7,
                new int[]{4, 5, 1, 2, 3});

        runTest(7, "Two elements with odd k",
                new int[]{1, 2}, 1,
                new int[]{2, 1});

        runTest(8, "Constraint boundaries for values (-2^31 to 2^31 - 1)",
                new int[]{-2147483648, 0, 2147483647}, 1,
                new int[]{2147483647, -2147483648, 0});

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}