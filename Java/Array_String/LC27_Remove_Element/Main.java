/*
27. Remove Element
Easy
Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.
Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100
*/

import java.util.Arrays;

class Solution {
    public int removeElement(int[] nums, int val) {

        int current = 0;
        for(int i =0; i <= nums.length - 1; i++){
            if(nums[i] != val){
                nums[current] = nums[i];
                current++;
            }
        }
        return current;
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    // Mimics LeetCode's Custom Judge
    public static void runTest(int testNum, String description, int[] nums, int val, int[] expectedNums) {
        totalCount++;
        Solution sol = new Solution();
        int k = sol.removeElement(nums, val);

        boolean lengthMatch = (k == expectedNums.length);
        boolean contentMatch = false;

        if (lengthMatch) {
            int[] actualFirstK = Arrays.copyOfRange(nums, 0, k);
            Arrays.sort(actualFirstK);
            Arrays.sort(expectedNums);
            contentMatch = Arrays.equals(actualFirstK, expectedNums);
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
                new int[]{3, 2, 2, 3}, 3,
                new int[]{2, 2});

        runTest(2, "LeetCode Example 2",
                new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2,
                new int[]{0, 0, 1, 3, 4});

        // --- EDGE CASES BASED ON CONSTRAINTS ---
        runTest(3, "Constraint min length: nums.length == 0",
                new int[]{}, 0,
                new int[]{});

        runTest(4, "All elements equal to val",
                new int[]{4, 4, 4, 4}, 4,
                new int[]{});

        runTest(5, "No elements equal to val",
                new int[]{1, 2, 3, 4}, 5,
                new int[]{1, 2, 3, 4});

        runTest(6, "Constraint boundary: val > 50 (e.g., val = 100 while nums[i] <= 50)",
                new int[]{0, 25, 50}, 100,
                new int[]{0, 25, 50});

        runTest(7, "Constraint boundary: val == 0 and nums contains zeros",
                new int[]{0, 1, 0, 2, 0}, 0,
                new int[]{1, 2});

        runTest(8, "Single element equals val",
                new int[]{7}, 7,
                new int[]{});

        runTest(9, "Single element does not equal val",
                new int[]{7}, 3,
                new int[]{7});

        runTest(10, "val appears only at the start and end",
                new int[]{9, 1, 2, 3, 9}, 9,
                new int[]{1, 2, 3});

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}