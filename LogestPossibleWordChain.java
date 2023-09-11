package com.complexcity.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LogestPossibleWordChain {
	//DP on hash
	//Input: word =["a","b","ba","bca","bda","bdca"]
	//output= 4
	//Explaination= One of the longest word chains is ["a","ba",bda","bdca"]
	public int longestStrChain(String[] words) {
		//Set<String> dict = new HashSet<>();
		//Collections.addAll(dict, words);
		
		//Map<String, Integer> dp = new HashMap<>();
		//int res = Integer.MIN_VALUE;
		//for(String word : words) {
			//res = Math.max(res, 1 + longestStrChainRec(word, dict));//Rec
			//res = Math.max(res, 1 + longestStrChainMemo(word, dict,dp));//Memo
		//}
		
		int res = longestStrChainTab(words);//Tab
		return res;
	}
	
	//TIME= O(N^2 M^2)
	public int longestStrChainRec(String word, Set<String> dict) {
		if(word.length() == 0)
			return 0;
		
		StringBuilder sb = new StringBuilder(word);
		int max=0;
		for(int i=0;i<word.length();i++) {
			sb.deleteCharAt(i);
			String predecessor = sb.toString();
			if(dict.contains(predecessor)) {
				max = Math.max(max, 1+longestStrChainRec(predecessor, dict));
			}
			sb.insert(i, word.charAt(i));
		}
		return max;
		
	}
	
	//TIME= O(N^2 M^2) => O(NM^2)
	public int longestStrChainMemo(String word, Set<String> dict, Map<String, Integer> dp) {
		if(word.length() == 0)
			return 0;
		
		if(dp.containsKey(word))
			return dp.get(word);
		
		StringBuilder sb = new StringBuilder(word);
		int max=0;
		for(int i=0;i<word.length();i++) {
			sb.deleteCharAt(i);
			String predecessor = sb.toString();
			if(dict.contains(predecessor)) {
				max = Math.max(max, 1+longestStrChainRec(predecessor, dict));
			}
			sb.insert(i, word.charAt(i));
		}
		dp.put(word, max);
		return max;
	}
	
	//TIME= O(N^2 M^2) => O(NM^2) => O(NM^2) ~ O(NM^2)
	//Space=O(N)
	public int longestStrChainTab(String[] words) {
		Arrays.sort(words, (a,b) -> a.length() - b.length());
		
		int maxPath =1;
		Map<String, Integer> dp = new HashMap<>();
		for(String word: words){
			int currLength =1;
			
			StringBuilder sb = new StringBuilder(word);
			for(int i=0;i<word.length();i++) {
				sb.deleteCharAt(i);
				String predecessor = sb.toString();
				currLength = Math.max(currLength, dp.getOrDefault(predecessor, 0) +1);
				sb.insert(i, word.charAt(i));
			}
			
			dp.put(word, currLength);
			maxPath = Math.max(maxPath, currLength);
		}
		return maxPath;
	}
	
}
