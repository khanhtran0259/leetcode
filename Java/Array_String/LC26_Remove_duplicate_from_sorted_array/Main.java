/*
26. Remove Duplicates from Sorted Array

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

Consider the number of unique elements in nums to be k​​​​​​​​​​​​​​. After removing duplicates, return the number of unique elements k.

The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index k - 1 can be ignored.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
*/


import java.util.Arrays;

class Solution {
    public int removeDuplicates(int[] nums) {
        int current = 0;
        for(int i = 0; i <= nums.length -1; i++){
            if(nums[current] != nums[i]){
                current++;
                nums[current] = nums[i];
            }
        }
        return current + 1;
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    // Mimics LeetCode's Custom Judge (preserves relative order without sorting)
    public static void runTest(int testNum, String description, int[] nums, int[] expectedNums) {
        totalCount++;
        Solution sol = new Solution();
        int k = sol.removeDuplicates(nums);

        boolean lengthMatch = (k == expectedNums.length);
        boolean contentMatch = true;

        if (lengthMatch) {
            for (int i = 0; i < k; i++) {
                if (nums[i] != expectedNums[i]) {
                    contentMatch = false;
                    break;
                }
            }
        } else {
            contentMatch = false;
        }

        boolean isPass = lengthMatch && contentMatch;
        if (isPass) passedCount++;

        System.out.println("Test " + testNum + " (" + description + "): " + (isPass ? "PASS" : "FAIL"));
        if (!isPass) {
            int safeK = Math.max(0, Math.min(k, nums.length));
            System.out.println("   Output k  : " + k + ", nums[0..k]: " + Arrays.toString(Arrays.copyOfRange(nums, 0, safeK)));
            System.out.println("   Expected k: " + expectedNums.length + ", expected  : " + Arrays.toString(expectedNums));
        }
    }

    public static void main(String[] args) {
        // --- 2 LEETCODE OFFICIAL EXAMPLES ---
        runTest(1, "LeetCode Example 1",
                new int[]{1, 1, 2},
                new int[]{1, 2});

        runTest(2, "LeetCode Example 2",
                new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                new int[]{0, 1, 2, 3, 4});

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(3, "Constraint min length: nums.length == 1",
                new int[]{5},
                new int[]{5});

        runTest(4, "All elements are identical",
                new int[]{2, 2, 2, 2, 2},
                new int[]{2});

        runTest(5, "No duplicate elements at all",
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5});

        runTest(6, "Negative numbers, zeros, and constraint boundaries (-100 to 100)",
                new int[]{-100, -100, -50, 0, 0, 0, 50, 100, 100},
                new int[]{-100, -50, 0, 50, 100});

        runTest(7, "Duplicates only at the very end",
                new int[]{1, 2, 3, 4, 4, 4},
                new int[]{1, 2, 3, 4});

        runTest(8, "Two identical elements",
                new int[]{-1, -1},
                new int[]{-1});

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}