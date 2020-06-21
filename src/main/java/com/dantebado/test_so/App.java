package com.dantebado.test_so;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App {
	
	enum ExecScenario {
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN
	}
	
	public static ResourceAllocation lastAllocation = null;
	
	public static List<List<Holder>> groups = new ArrayList<List<Holder>>();

    public static void main(String[] args) throws Exception {
    	
    	List<Resource> resources = new ArrayList<Resource>();
    	List<Holder> holders = new ArrayList<Holder>();
    	
    	Resource R1 = new Resource("R1");
    	Resource R2 = new Resource("R2");
    	Resource R3 = new Resource("R3");
    	Resource R4 = new Resource("R4");
    	Resource R5 = new Resource("R5");
    	Resource R6 = new Resource("R6");
    	Resource R7 = new Resource("R7");
    	
    	resources.add(R1);
    	resources.add(R2);
    	resources.add(R3);
    	resources.add(R4);
    	resources.add(R5);
    	resources.add(R6);
    	resources.add(R7);

    	Holder A = new Holder("A");
    	Holder B = new Holder("B");
    	Holder C = new Holder("C");
    	Holder D = new Holder("D");
    	Holder E = new Holder("E");
    	Holder F = new Holder("F");
    	
    	holders.add(A);
    	holders.add(B);
    	holders.add(C);
    	holders.add(D);
    	holders.add(E);
    	holders.add(F);
    	
    	Collections.shuffle(holders);
    	
    	if(true) {
    		buildLists(holders);
    		for(List<Holder> tg : groups) {    			
    			System.out.print("[");
    			
    			for(int i=0 ; i<tg.size() ; i++) {
    				System.out.print(tg.get(i).getName());
    				if(i != tg.size()-1) {
    					System.out.print(", ");
    				}
    			}
    			
    			System.out.println("]");
    		}
    	}
    	
    	for(ExecScenario scenario : ExecScenario.values()) {
    		System.out.println("Scenario " + scenario.name());
    	
	    	for(Holder h : holders) {
	    		h.setDeadlockCause(null);
	    		h.getTargets().clear();
	    		h.getHolding().clear();
	    	}
	    	
	    	switch(scenario) {
		    	case ONE:
		    		
		    		A.getTargets().add(R1);
		    		C.getTargets().add(R3);
		    		E.getTargets().add(R2);
		    		
		    		B.addResource(R1);
		    		detectCircularChains(holders);
		    		
		    		D.addResource(R3);
		    		detectCircularChains(holders);
		    		
		    		F.addResource(R2);
		    		detectCircularChains(holders);
		    		
		    		break;
		    	case TWO:
		    		
		    		A.getTargets().add(R1);	    		
		    		C.getTargets().add(R1);
		    		C.getTargets().add(R2);
		    		
		    		B.addResource(R1);
		    		detectCircularChains(holders);
		    		
		    		B.addResource(R1);
		    		detectCircularChains(holders);

		    		F.addResource(R2);
		    		detectCircularChains(holders);
		    		
		    		break;
		    	case THREE:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R2);
		    		C.getTargets().add(R3);
		    		
		    		B.addResource(R1);
		    		detectCircularChains(holders);
		    		
		    		D.addResource(R2);
		    		detectCircularChains(holders);
		    		
		    		E.addResource(R3);
		    		detectCircularChains(holders);
		    		
		    		break;
		    	case FOUR:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R2);
		    		E.getTargets().add(R3);
		    		F.getTargets().add(R4);
		    		
		    		B.addResource(R1);
		    		detectCircularChains(holders);
		    		
		    		A.addResource(R2);
		    		detectCircularChains(holders);
		    		
		    		F.addResource(R3);
		    		detectCircularChains(holders);
		    		
		    		E.addResource(R4);
		    		detectCircularChains(holders);
		    		
		    		break;
		    	case FIVE:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R4);
		    		D.getTargets().add(R3);
		    		C.getTargets().add(R2);
		    		
		    		B.addResource(R1);
		    		detectCircularChains(holders);
		    		
		    		A.addResource(R3);
		    		detectCircularChains(holders);
		    		
		    		D.addResource(R4);
		    		detectCircularChains(holders);
		    		
		    		D.addResource(R2);
		    		detectCircularChains(holders);
		    		
		    		break;
		    	case SIX:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R2);
		    		D.getTargets().add(R4);
		    		C.getTargets().add(R3);
		    		
		    		B.addResource(R1);
		    		detectCircularChains(holders);
		    		
		    		D.addResource(R2);
		    		detectCircularChains(holders);
		    		
		    		C.addResource(R4);
		    		detectCircularChains(holders);
		    		
		    		A.addResource(R3);
		    		detectCircularChains(holders);
		    		
		    		break;
		    	case SEVEN:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R2);
		    		D.getTargets().add(R4);
		    		C.getTargets().add(R3);
		    		E.getTargets().add(R5);
		    		F.getTargets().add(R6);
		    		D.getTargets().add(R7);
		    		
		    		B.addResource(R1);
		    		detectCircularChains(holders);
		    		
		    		D.addResource(R2);
		    		detectCircularChains(holders);
		    		
		    		A.addResource(R3);
		    		detectCircularChains(holders);
		    		
		    		C.addResource(R5);
		    		detectCircularChains(holders);
		    		
		    		F.addResource(R7);
		    		detectCircularChains(holders);
		    		
		    		E.addResource(R6);
		    		detectCircularChains(holders);
		    		
		    		C.addResource(R4);
		    		detectCircularChains(holders);
		    		
		    		break;
	    	}
	    
	    	System.out.println("\nFinal Situation");
	    	for(Holder h : holders) {
	    		h.printStatus();
	    	}
	    	
	    	System.out.println("\n------------------------\n");
	
	    	/*for(Holder h : holders) {
	    		whoHasSomethingINeed(h, holders);
	    	}
	    	
	    	for(Holder h1 : holders) {
	    		for(Holder h2 : holders) {
	    			if(areInDeadlock(h1, h2, holders)) {
	    				System.out.println("Holders " + h1.getName() + " and " + h2.getName() + " are in deadlock");
	    			}
	    		}
	    	}*/
    	}
    	
    }
    
    public static void solveDeadlockFor(ResourceAllocation allocation, Holder root, List<Holder> holders) {
    	System.out.println("\t    Solving...");
    	
    	for(int k=0 ; k<root.getHolding().size() ; k++) {
    		Resource r = root.getHolding().get(k);
        	for(int i=0 ; i<holders.size() ; i++) {
        		Holder h = holders.get(i);
        		if(h.getTargets().contains(r) &&
        				!h.getName().equals(root.getName()) &&
        				r.getName().equals(allocation.getResource().getName())) {
        			System.out.println("\t\tTrading resource " + r.getName() + " from " + root.getName() + " to " + h.getName());
        			
        			h.removeFromTargets(r);
        			root.removeFromHoldings(r);
        			h.setDeadlockCause(null);
        			root.setDeadlockCause(null);
        		}
        		
        		if(h.getDeadlockCause() == allocation) {
        			h.setDeadlockCause(null);
        		}
        	}
    	}
    }
    
    public static void detectCircularChains(List<Holder> allHolders) {
    	for(List<Holder> group : groups) {
    		if(detectDeadlocks(group)) {
    			
    			Boolean isDeadlock = true;
    			
    			for(int i=1 ; i<group.size() ; i++) {
    				if(!existsPathTo(group.get(i), group.get(i), group.get(0), group, new ArrayList<Holder>())) {
    					isDeadlock = false;
    				}
    				if(!existsPathTo(group.get(0), group.get(0), group.get(i), group, new ArrayList<Holder>())) {
    					isDeadlock = false;
    				}
    			}
    			
    			if(isDeadlock) {
        			System.out.print("Hay deadlock en: ");        			
        			System.out.print("[");    			
        			for(int i=0 ; i<group.size() ; i++) {
        				System.out.print(group.get(i).getName());
        				if(i != group.size()-1) {
        					System.out.print(", ");
        				}
        			}    			
        			System.out.println("]");
    			}
    			
    		}
    	}
    }
    
    public static Boolean detectDeadlocks(List<Holder> holders) {
    	if(detectDeadlockFrom(holders.get(0), holders)) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean existsPathTo(Holder originalNode, Holder from, Holder to, List<Holder> holders, List<Holder> steps) {
    	if( from.getName().equals(to.getName()) ) return true;
    	
    	//System.out.println("Path from " + from.getName() + " to " + to.getName() + " (" + originalNode.getName() + ")");
    	
    	if( whoHasSomethingINeed(from, holders).contains(to) ) return true;
    	if( whoHasSomethingINeed(from, holders).size() == 0 ) return false;
    	
    	List<Holder> baseList = whoHasSomethingINeed(from, holders);
    	for(Holder cHolder : baseList) {
    		if(!cHolder.getName().equals(originalNode.getName()) && !steps.contains(cHolder)) {
    			steps.add(cHolder);
        		if(existsPathTo(originalNode, cHolder, to, holders, steps)) {
        			return true;
        		}    			
    		}
    	}
    	
    	return false;
    }
    
    public static boolean detectDeadlockFrom(Holder root, List<Holder> holders) {
    	if(root.isCompleted()) return false;
    	
    	List<Holder> baseList = whoHasSomethingINeed(root, holders);    	
    	if(baseList.size() == 0) return false;
    	
    	for(Holder h : baseList) {
    		if(existsPathTo(root, h, root, holders, new ArrayList<Holder>())) {
    			root.setDeadlockCause(lastAllocation);
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public static boolean areInDeadlock(Holder holderOne, Holder holderTwo, List<Holder> holders) {
    	return	whoHasSomethingINeed(holderOne, holders).contains(holderTwo) && 
    			whoHasSomethingINeed(holderTwo, holders).contains(holderOne);
    }
    
    public static List<Holder> whoHasSomethingINeed(Holder holder, List<Holder> holders) {
    	List<Holder> listReturn = new ArrayList<Holder>();
    	
    	if(holder.isCompleted()) {
    		//System.out.println("For Holder " + holder.getName() + ":");
    		//System.out.println("\tI have no needs");
    		return listReturn;
    	}
    	
    	//System.out.println("For Holder " + holder.getName() + ":");
    	
    	for(Resource target : holder.getTargets()) {
    		Boolean foundSomeoneWhoHasIt = false;    		
    		for(Holder anotherHolder : holders) {
    			if(!holder.getName().equals(anotherHolder.getName())) {
    				for(Resource heHas : anotherHolder.getHolding()) {
    					if(heHas.getName().equals(target.getName()) && !foundSomeoneWhoHasIt) {
    						listReturn.add(anotherHolder);
    						foundSomeoneWhoHasIt = true;
    						//System.out.println("\tHolder " + anotherHolder.getName() + " has a " + target.getName());
    					}
    				}
    			}
    		}
    	}
    	
    	if(listReturn.size() == 0) {
    		//System.out.println("\tNo one has something I need");
    	}
    	
    	return listReturn;
    }
    
    public static void buildLists(List<Holder> allHolders) {    	
    	Holder[] allHoldersArray = new Holder[allHolders.size()];
    	allHolders.toArray(allHoldersArray);
    	
    	for(Holder[] result : allPosibleCombinations(allHoldersArray)) {
    		List<Holder> thisResultAsList = new ArrayList<Holder>();
    		for(Holder h : result) {
    			thisResultAsList.add(h);
    		}
    		groups.add(thisResultAsList);
    	}    	
    }
    
    static List<Holder[]> allPosibleCombinations(Holder[] arr) {
		List<Holder[]> finalResult = new ArrayList<Holder[]>();
		for(int i=2 ; i<=arr.length ; i++) {
			finalResult.addAll(allCombinationsOfSize(arr, i));
		}
		return finalResult;
	}
	
	static List<Holder[]> allCombinationsOfSize(Holder[] arr, int size) {
		List<Holder[]> allResultsOfSize = new ArrayList<Holder[]>();
		combinations2(arr, size, 0, new Holder[size], allResultsOfSize);
		return allResultsOfSize;
	}
	
	static void combinations2(Holder[] arr, int len, int startPosition, Holder[] currentResult, List<Holder[]> allResults){
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
