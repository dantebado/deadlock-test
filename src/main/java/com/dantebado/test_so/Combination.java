package com.dantebado.test_so;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
	
	public static void main(String[] args){
		String[] arr = {"A","B","C","D","E","F"};
		List<String[]> combinations = allPosibleCombinations(arr);
		
		for(String[] result : combinations) {
			System.out.println(Arrays.toString(result));
		}
	}
	
	static List<String[]> allPosibleCombinations(String[] arr) {
		List<String[]> finalResult = new ArrayList<String[]>();
		for(int i=2 ; i<=arr.length ; i++) {
			finalResult.addAll(allCombinationsOfSize(arr, i));
		}
		return finalResult;
	}
	
	static List<String[]> allCombinationsOfSize(String[] arr, int size) {
		List<String[]> allResultsOfSize = new ArrayList<String[]>();
		combinations2(arr, size, 0, new String[size], allResultsOfSize);
		return allResultsOfSize;
	}
	
	static void combinations2(String[] arr, int len, int startPosition, String[] currentResult, List<String[]> allResults){
		if (len == 0){			
			allResults.add(currentResult.clone());
			return;
		}
		for (int i = startPosition; i <= arr.length-len; i++){
			currentResult[currentResult.length - len] = arr[i];
			combinations2(arr, len-1, i+1, currentResult, allResults);
		}
	}
	
}