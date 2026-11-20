/*
80. Remove Duplicates from Sorted Array II

You are given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

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

Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
*/

import java.util.Arrays;

class Solution {
    public int removeDuplicates(int[] nums) {
        int current = 0;
        int counter = 1;
        for(int i = 1; i <= nums.length -1; i++){
            if(nums[i] == nums[current]){
                if(counter <2){
                    counter++;
                    current++;
                    nums[current] = nums[i];
                }
            }
            else{
                current++;
                counter = 1;
                nums[current] = nums[i];
            }
        }
        return current + 1;
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    // Mimics LeetCode's Custom Judge
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
                new int[]{1, 1, 1, 2, 2, 3},
                new int[]{1, 1, 2, 2, 3});

        runTest(2, "LeetCode Example 2",
                new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3},
                new int[]{0, 0, 1, 1, 2, 3, 3});

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(3, "Constraint min length: nums.length == 1",
                new int[]{7},
                new int[]{7});

        runTest(4, "Length == 2 with identical elements (both kept)",
                new int[]{4, 4},
                new int[]{4, 4});

        runTest(5, "All elements are identical (only 2 kept)",
                new int[]{2, 2, 2, 2, 2, 2},
                new int[]{2, 2});

        runTest(6, "No duplicate elements at all",
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5});

        runTest(7, "Negative numbers, zeros, and constraint boundaries (-10^4 to 10^4)",
                new int[]{-10000, -10000, -10000, 0, 0, 0, 10000, 10000},
                new int[]{-10000, -10000, 0, 0, 10000, 10000});

        runTest(8, "Duplicates appear only at the very end",
                new int[]{1, 2, 3, 3, 3, 3},
                new int[]{1, 2, 3, 3});

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}