package com.complexcity.test;

import java.util.Arrays;

public class TwoEqualSubset {
//Partition Equal Subset Sum
	//given an integer array nums, return true if you can partition the array into two subset such that the sum of th elements in both subsets is equal or false otherwise.
	//Input= nums = [1,5,11,5]\Output= true
	//Explaination=The array cannot be partitioned as [1,5,5] and [11].
	
		public boolean canPartition(int[] nums) {
			int sum=0;
			
			for(int i=0;i<nums.length;i++)
				sum+= nums[i];
			
			if((sum & 1) == 1)
				return false;
						
						int target = sum/2;
			//boolean ans = canPartitionRec(nums, 0, target);
			
			//Boolean[][] dp =new Boolean[nums.length+1][target+1];
			//boolean ans = canPartitionMemo(nums, 0 , target, dp);
			
			//boolean ans = canPartitionTab(nums, sum);
			
			boolean ans = canPartitionSpace(nums, sum);
			return ans;
		}
		
		//Time= O(2^N)
		//Space=O(N)
		public boolean canPartitionRec(int[] nums, int index, int target) {
			if(index >= nums.length)
				return false;
			if(target <0)
				return false;
			
			if(target ==0)
				return true;
			
			//include
			boolean take = canPartitionRec(nums, index+1, target - nums[index]);
			
			//exclude
			boolean noTake = canPartitionRec(nums, index+1, target);
			
			return take || noTake;
		}
		
		//Time= O(NT)
		//Space=O(NT)
		public boolean canPartitionMemo(int[] nums, int index, int target, Boolean[][] dp) {
			if(index >= nums.length)
				return false;
			if(target <0)
				return false;
			
			if(target ==0)
				return true;
			
			if(dp[index][target] != null)
				return dp[index][target];
			
			//include
			boolean take = canPartitionMemo(nums, index+1, target - nums[index],dp);
			
			//exclude
			boolean noTake = canPartitionMemo(nums, index+1, target,dp);
			
			return dp[index][target] = take || noTake;
		}
		
		//Time= O(NT)
		//Space=O(NT)
		public boolean canPartitionTab(int[] nums, int total) {
			Boolean[][] dp = new Boolean[nums.length+1][total];
			
			for(Boolean[] row : dp)
				Arrays.fill(row, false);
			for(int i=0; i<dp.length;i++)
				dp[i][0] = true;
			
			for(int index = nums.length; index>=0; index--) {
				for(int target =0; target <= total/2;target++) {
					//include
					boolean take =false;
					if(target - nums[index] >= 0)
						take=dp[index+1][target -nums[index]];
					
					//exclude
					boolean noTake = dp[index+1][target];
					
					dp[index][target] = take || noTake;
					
				}
			}
			
			return dp[0][total/2];
		}
		
		//Time= O(NT)
		//Space=O(T)
		public boolean canPartitionSpace(int[] nums, int total) {
			Boolean[] curr = new Boolean[total+1];
			Boolean[] next = new Boolean[total+1];
			
			Arrays.fill(curr, false);
			Arrays.fill(next, false);
			
			curr[0] = true;
			next[0] = true;
			
			for(int index = nums.length; index>=0; index--) {
				for(int target =0; target <= total/2;target++) {
					//include
					boolean take =false;
					if(target - nums[index] >= 0)
						take=next[target -nums[index]];
					
					//exclude
					boolean noTake = next[target];
					
					curr[target] = take || noTake;
					
				}
				
				next = Arrays.copyOf(curr, curr.length);
			}
			
			return next[total/2];
		}
}
