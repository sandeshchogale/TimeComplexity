package com.complexcity.test;

import java.util.Arrays;

public class MatrixMultiplication {
	int matrixMultiplication(int N, int arr[]) {
		
		//int ans = maxtriMultiplicationRec(arr, 1, N-1);
		
		//int[][] dp = new int[N][N];
		//for(int[] row : dp) {
			//Arrays.fill(row, -1);
		//}
		//int ans = maxtriMultiplicationMemo(arr, 1, N-1, dp);
		
		int ans = maxtriMultiplicationTab(arr, N);

		return ans;
	}
	
	//Time= O(2^N)
	//Space= O(N)
	private int maxtriMultiplicationRec(int[] arr, int left, int rigth) {
		if(left >= rigth)
			return 0;
		
		int min = Integer.MAX_VALUE;
		int temp = 0;
		for(int k=left; k<rigth; ++k) {
			temp = maxtriMultiplicationRec(arr, left, k) + maxtriMultiplicationRec(arr, k+1, rigth)
			+ arr[left-1] * arr[k] * arr[rigth];
			min= Math.min(min, temp);
		}
		return min;
	}
	
	//Time= O(3^N)
	//Space= O(N^2)
	private int maxtriMultiplicationMemo(int[] arr, int left, int rigth, int[][] dp) {
		if(left >= rigth)
			return 0;
		if(dp[left][rigth] != -1)
			return dp[left][rigth];
		
		int min = Integer.MAX_VALUE;
		int temp = 0;
		for(int k=left; k<rigth; ++k) {
			temp = maxtriMultiplicationRec(arr, left, k) + maxtriMultiplicationRec(arr, k+1, rigth)
			+ arr[left-1] * arr[k] * arr[rigth];
			min= Math.min(min, temp);
		}
		return dp[left][rigth] = min;
	}
	
	//Time= O(3^N)
	//Space= O(N^2)
	private int maxtriMultiplicationTab(int[] arr, int N) {
		int[][] dp = new int[N][N];
		for(int i=2; i<N;i++) {
			for(int left=1;left<=N-i;left++) {
				int rigth = i+left-1;
				dp[left][rigth] = Integer.MAX_VALUE;
				int temp;
				for(int k=left; k<rigth; k++) {
					temp = dp[left][k] + dp[k+1][rigth]
					+ arr[left-1] * arr[k] * arr[rigth];
					dp[left][rigth] = Math.min(dp[left][rigth], temp);
				}
			}
		}
		
		return dp[1][N-1];
	}
	
}
