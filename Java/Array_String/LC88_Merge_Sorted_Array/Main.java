/*
88. Merge Sorted Array
Easy

You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, 
but instead be stored inside the array nums1. To accommodate this, nums1 has a 
length of m + n, where the first m elements denote the elements that should be merged, 
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

 

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 

Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109
 
*/

import java.util.Arrays;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pt1 = m -1; //init pointer 1 = array 1 lenght
        int pt2 = n-1; // init pointer 2 = array 2 lenght
        int pt = m + n -1; //init a pointer = lenght.array1 + lenght.array2
        while (pt2 >= 0){
            if( pt1 >=0 && nums1[pt1] > nums2[pt2]){// 
                nums1[pt] = nums1[pt1];
                pt1--;
            } else{
                nums1[pt] = nums2[pt2];
                pt2--; 
            }
            pt--;
        }
        
    }
}

public class Main {
    private static int passedCount = 0;
    private static int totalCount = 0;

    public static void runT est(int testNum, String description, int[] nums1, int m, int[] nums2, int n, int[] expected) {
        totalCount++;
        Solution sol = new Solution();
        sol.merge(nums1, m, nums2, n);

        boolean isPass = Arrays.equals(nums1, expected);
        if (isPass) passedCount++;

        System.out.println("Test " + testNum + " (" + description + "): " + (isPass ? "PASS" : "FAIL"));
        if (!isPass) {
            System.out.println("   Output  : " + Arrays.toString(nums1));
            System.out.println("   Expected: " + Arrays.toString(expected));
        }
    }

    public static void main(String[] args) {
        // --- 3 LEETCODE OFFICIAL EXAMPLES ---
        runTest(1, "LeetCode Example 1",
                new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3,
                new int[]{1, 2, 2, 3, 5, 6});

        runTest(2, "LeetCode Example 2: n = 0 (empty nums2)",
                new int[]{1}, 1, new int[]{}, 0,
                new int[]{1});

        runTest(3, "LeetCode Example 3: m = 0 (empty nums1)",
                new int[]{0}, 0, new int[]{1}, 1,
                new int[]{1});

        // --- EDGE CASES ---
        runTest(4, "m = 0 with multiple elements in nums2",
                new int[]{0, 0, 0}, 0, new int[]{1, 2, 3}, 3,
                new int[]{1, 2, 3});

        runTest(5, "n = 0 with multiple elements in nums1",
                new int[]{1, 2, 3}, 3, new int[]{}, 0,
                new int[]{1, 2, 3});

        runTest(6, "All elements in nums2 are smaller than nums1",
                new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3,
                new int[]{1, 2, 3, 4, 5, 6});

        runTest(7, "All elements in nums2 are larger than nums1",
                new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{4, 5, 6}, 3,
                new int[]{1, 2, 3, 4, 5, 6});

        runTest(8, "Negative numbers and real zeros mixed",
                new int[]{-3, -1, 0, 0, 0}, 3, new int[]{-2, 1}, 2,
                new int[]{-3, -2, -1, 0, 1});

        runTest(9, "All duplicate elements",
                new int[]{2, 2, 0, 0}, 2, new int[]{2, 2}, 2,
                new int[]{2, 2, 2, 2});

        runTest(10, "Small 2-element reversed arrays",
                new int[]{2, 0}, 1, new int[]{1}, 1,
                new int[]{1, 2});

        runTest(11, "Single large element in nums1, multiple small elements in nums2",
                new int[]{10, 0, 0, 0, 0}, 1, new int[]{1, 2, 3, 4}, 4,
                new int[]{1, 2, 3, 4, 10});

        runTest(12, "Min and Max constraint boundaries (-10^9 and 10^9)",
                new int[]{-1000000000, 0, 1000000000, 0, 0}, 3, new int[]{-1000000000, 1000000000}, 2,
                new int[]{-1000000000, -1000000000, 0, 1000000000, 1000000000});

        System.out.println("\nSummary: " + passedCount + "/" + totalCount + " test cases passed.");
    }
}