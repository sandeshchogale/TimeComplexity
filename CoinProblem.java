package com.complexcity.test;

import java.util.Arrays;

public class CoinProblem {
	//Example1
	//Input: coins = [1,2,5], amount = 11
	//output: 3
	//Explanation: 11 = 5 + 5 + 1

	//Example2
	//Input: coins = [2], amount = 3
	//output: -1
	
	//Example3
	//Input: coins = [1], amount = 0
	//output: 0
	
	//mincoins = Math.min(mincoins, 1+ f(amount - coins[i]))
	public int coinChange(int[] coins, int amount) {
		//TLE
		//11 = 10(1) 9(2) 6(5)
		//10(1) = 9(1) 8(2) 5(5)
		//Time = O(N^C)
		//Space = O(N)
		//int ans = coinChange(coins, amount);
		
		//Time = O(NC)
		//Space = O(N) + O(N)
		//int[] dp = new int[amount+1];
		//Arrays.fill(dp, -1);
		//int ans = coinChangeMemo(coins, amount, dp);
		
		//Time = O(NC)
		//Space = O(N)
		int ans = coinChangeTab(coins, amount);
		if(ans == Integer.MAX_VALUE)
			return -1;
		return ans;
	}
	
	public int coinRec(int[] coins, int amount) {
		if(amount ==0)
			return 0;
		if(amount <0)
			return Integer.MAX_VALUE;
		
		int mincoins = Integer.MAX_VALUE;
		for(int i=0;i< coins.length; i++) {
			int ans = coinChange(coins, amount - coins[i]);
			if(ans != Integer.MAX_VALUE) {
				mincoins = Math.min(amount, 1+ ans);
			}
		}
		return mincoins;
	}
	
	public int coinChangeMemo(int[] coins, int amount, int dp[]) {
		if(amount ==0)
			return 0;
		if(amount <0)
			return Integer.MAX_VALUE;
		
		if(dp[amount] != -1)
			return dp[amount];
		int mincoins = Integer.MAX_VALUE;
		for(int i=0;i< coins.length; i++) {
			int ans = coinChangeMemo(coins, amount - coins[i], dp);
			if(ans != Integer.MAX_VALUE) {
				mincoins = Math.min(amount, 1+ ans);
			}
		}
		return dp[amount] = mincoins;
	}
	
	public int coinChangeTab(int[] coins, int amount) {
		if(amount ==0)
			return 0;
		if(amount <0)
			return Integer.MAX_VALUE;
		
		int[] dp = new int[amount+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] =0;
		
		for(int i=1;i< amount; i++) {
			for(int j=0; j< coins.length; j++) {
				if(i - coins[j] >= 0 && dp[i-coins[j]] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], 1+ dp[i - coins[j]]);
				}
			}
			
			
		}
		
		
		return dp[amount];
	}
}
