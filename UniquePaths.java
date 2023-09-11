package com.complexcity.test;

import java.util.Arrays;

public class UniquePaths {
	public int uniquePaths(int m, int n){
		//int ans = uniquePathRec(m-1,n-1);
		
		//int[][] dp = new int[m][n];
		//for(int[] row : dp) {
			//Arrays.fill(row, -1);
		//}
		//int ans = uniquePathMemo(m-1,n-1,dp);
		
		//int ans = uniquePathTab(m,n);
		
		int ans = uniquePathSpace(m,n);
		
		return ans;
	}
	
	//f(m,n) = f(m-1, n) + f(m, n-1)
	//Time= O(M*N)
	//Space= O(M*N) 
	private int uniquePathRec(int m, int n) {
		if(m ==0 || n ==0)
			return 1;
		return uniquePathRec(m-1, n) + uniquePathRec(m, n-1);
	}
	
	//f(m,n) = f(m-1, n) + f(m, n-1)
	//Time= 2 ^ MAX(m,n)
	//Space= O(MAX(m,n)) 
	private int uniquePathMemo(int m, int n, int[][] dp) {
		if(m ==0 || n ==0)
			return 1;
		if(dp[m][n] != -1)
			return dp[m][n];
		return uniquePathMemo(m-1, n, dp) + uniquePathMemo(m, n-1, dp);
	}
	
	//M -> m-1 to 1  1 to m-1
	//N -> n-1 to 1  1 to n-1
	//Time= 2 ^ MAX(m,n)
	//Space= O(MAX(m,n)) 
	private int uniquePathTab(int m, int n) {
		int[][] dp = new int[m][n];
		
		for(int i=0;i<m;i++) {
			dp[i][0] =1;
		}
		for(int j=0;j<n;j++) {
			dp[j][0] =1;
		}
		for(int i =1; i<m; i++) {
			for(int j=1; j<n;j++) {
				dp[i][j] = dp[i-1] [j] + dp[i] [j-1];
			}
		}
		
		return dp[m-1][n-1];
	}
	
	//Time= 2 ^ MAX(m,n)
	//Space= O(N)) 
	private int uniquePathSpace(int m, int n) {
		int[] prev = new int[m];
		
		Arrays.fill(prev, 1);
		
		for(int i =1; i<m; i++) {
			for(int j=1; j<n;j++) {
				prev[j] += prev[j-1];
			}
		}
		
		return prev[n-1];
	}
	
}
