package com.complexcity.test;

public class Fiboo {
	//0 1 1 2 3 5 8 13
	public int fib(int n) {
		//O(2^N)
		//O(N)
		//int ans = fibRec(n);
		
		//O(N)
		//O(N) + O(N)
		//int dp[] = new int[n+1];
		//Arrays.fill(dp, -1);
		//int ans = fibMemo(n, dp);
		
		//O(N)
		//O(N)
		//int ans = fibTab(n);
		
		//O(N)
		//O(1)
		int ans = fibTabSapce(n);
		return ans;
	}
	
	private int fibRec(int n) {
		if(n == 0 || n == 1)
			return n;
		return fibRec(n-1) + fibRec(n-2);
	}
	
	//step for Memoization
	//create a store or DP Array
	//store the result before returning
	//after the base case we have to check value is present in store or not
	
	private int fibMemo(int n, int[] dp) {
		if(n == 0 || n == 1)
			return n;
		if(dp[n] != -1)
			return dp[n];
		return dp[n] = fibMemo(n-1, dp) + fibMemo(n-2, dp);
	}
	
	//Steps for Tabulation
	//create a store or DP array
	//Initialize the base case
	//Analyzing the recursive call from where to where it is going
	//just reverse 2 to n
	
	private int fibTab(int n) {
		if(n == 0 || n == 1)
			return n;
		int [] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		for(int i=2;i<=n;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n];
	}
	
	//For space optimization
	private int fibTabSapce(int n) {
		if(n == 0 || n == 1)
			return n;
		int [] dp = new int[n+1];
		int prev2 = 0;
		int prev1 = 1;
		for(int i=2;i<=n;i++) {
			int curr = prev1 + prev2;
			prev2 = prev1;
			prev1 = curr;
		}
		return prev1;
	}
	
}
