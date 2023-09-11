package com.complexcity.test;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
//Example1
	//Input: nums = [10,9,2,5,3,7,101,18]
	//output: 4
	//Explanation: The longest increasing subsequence is [2,3,7,101] therefore the length is 4.
	
//Example2 
	//Input: nums= [0,1,0,3,2,3]
	//output: 4
	
	public int lengthOfList(int[] nums) {
		//TLE(Time limit Exceeded)
		//toTake, toNottake
		//Time= (2 ^ N)
		//Space= O(N)
		
		//int ans = lengthOfListRec(nums, 0, -1);
		
		//curr => 0 to n -1
		//pre => -1 to curr -1
		//Time = O(N^2)
		//Space= O(N^2)
		//int[][] dp = new int[nums.length+ 1][nums.length +1];
		//for(int i=0; i<dp[i].length; i++)
			//Arrays.fill(dp[i], -1);
		//int ans = lengthOfListMemo(nums, 0, -1, dp);
		
		//Time = O(N^2)
		//Space= O(N^2)
		//int ans = lengthOfListTab(nums);
		
		
		//Time = O(N^2)
		//Space= O(N)
		//int ans = lengthOfListSpace(nums);
		
		
		//Time: O(NlogN)
		//Space= O(N)
		int ans = lengthOfBinarySearch(nums);
		return ans;
	}
	
	public int lengthOfListRec(int[] nums, int curr, int prev) {
		if(curr == nums.length)
			return 0;
		
		//include
		int take =0;
		if(prev == -1 || nums[curr] > nums[prev])
			take = 1+ lengthOfListRec(nums, curr + 1, curr);
		
		//exclude
		int noTake = lengthOfListRec(nums, curr + 1, prev);
		
		return Math.max(take, noTake);
	}
	
	public int lengthOfListMemo(int[] nums, int curr, int prev, int[][] dp) {
		if(curr == nums.length)
			return 0;
		
		if(dp[curr][prev +1] != -1)
			return dp[curr][prev +1];
		
		//include
		int take =0;
		if(prev == -1 || nums[curr] > nums[prev])
			take = 1+ lengthOfListMemo(nums, curr + 1, curr, dp);
		
		//exclude
		int noTake = lengthOfListMemo(nums, curr + 1, prev, dp);
		
		return Math.max(take, noTake);
	}
	
	public int lengthOfListTab(int[] nums) {
		int[][] dp = new int[nums.length+1][nums.length+1];
		
		//curr => 0 to n -1       n-1 to 0
		//pre => -1 to curr -1    curr -1 to -1
		for(int curr = nums.length-1; curr>= 0; curr--) {
			for(int prev = curr-1; prev>= -1; prev--) {
				//include
				int take =0;
				if(prev == -1 || nums[curr] > nums[prev])
					take = 1 + dp[curr + 1][curr + 1];
				
				//exclude
				int noTake = dp[curr+1][prev+1];
				dp[curr][prev+1] = Math.max(take, noTake);
			}
		}
		return dp[0][0];
		
	}
	
	public int lengthOfListSpace(int[] nums) {
		int[] nextRow = new int[nums.length+1];
		int[] currRow = new int[nums.length+1];
		
		//curr => 0 to n -1       n-1 to 0
		//pre => -1 to curr -1    curr -1 to -1
		for(int curr = nums.length-1; curr>= 0; curr--) {
			for(int prev = curr-1; prev>= -1; prev--) {
				//include
				int take =0;
				if(prev == -1 || nums[curr] > nums[prev])
					take = 1 + nextRow[curr + 1];
				
				//exclude
				int noTake = nextRow[prev+1];
				currRow[prev+1] = Math.max(take, noTake);
			}
			nextRow = Arrays.copyOf(currRow, currRow.length);
		}
		return nextRow[0];
		
	}
	
	//[]
	//[10]
	//[9]
	//[2]
	//[2,5]
	//[2,3]
	//[2,3,7]
	//[2,3,7,101]
	//[2,3,7,18] = 4
	
	public int lengthOfBinarySearch(int[] nums) {
		int[] sortestList = new int[nums.length];
		int size =0;
		for(int i=0; i<nums.length; i++) {
			int index = findIndex(nums[i], sortestList, size);
			sortestList[index] = nums[i];
			if(index == size)
				size++;
		}
		return size;
	}
	
	private int findIndex(int num, int[] sortedList, int high) {
		int lo=0, hi= high-1;
		while(lo <= hi) {
			int mid = lo + (hi-lo)/2;
			
			if(sortedList[mid] >= num) {
				hi = mid - 1;
			}else {
				lo = mid+1;
			}
		}
		
		return lo;
	}
	
	
	
}
