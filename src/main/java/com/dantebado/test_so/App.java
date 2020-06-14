package com.dantebado.test_so;

import java.util.ArrayList;
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
		    		detectDeadlocks(holders);
		    		
		    		D.addResource(R3);
		    		detectDeadlocks(holders);
		    		
		    		F.addResource(R2);
		    		detectDeadlocks(holders);
		    		
		    		break;
		    	case TWO:
		    		
		    		A.getTargets().add(R1);	    		
		    		C.getTargets().add(R1);
		    		C.getTargets().add(R2);
		    		
		    		B.addResource(R1);
		    		detectDeadlocks(holders);
		    		
		    		B.addResource(R1);
		    		detectDeadlocks(holders);

		    		F.addResource(R2);
		    		detectDeadlocks(holders);
		    		
		    		break;
		    	case THREE:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R2);
		    		C.getTargets().add(R3);
		    		
		    		B.addResource(R1);
		    		detectDeadlocks(holders);
		    		
		    		D.addResource(R2);
		    		detectDeadlocks(holders);
		    		
		    		E.addResource(R3);
		    		detectDeadlocks(holders);
		    		
		    		break;
		    	case FOUR:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R2);
		    		E.getTargets().add(R3);
		    		F.getTargets().add(R4);
		    		
		    		B.addResource(R1);
		    		detectDeadlocks(holders);
		    		
		    		A.addResource(R2);
		    		detectDeadlocks(holders);
		    		
		    		F.addResource(R3);
		    		detectDeadlocks(holders);
		    		
		    		E.addResource(R4);
		    		detectDeadlocks(holders);
		    		
		    		break;
		    	case FIVE:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R4);
		    		D.getTargets().add(R3);
		    		C.getTargets().add(R2);
		    		
		    		B.addResource(R1);
		    		detectDeadlocks(holders);
		    		
		    		A.addResource(R3);
		    		detectDeadlocks(holders);
		    		
		    		D.addResource(R4);
		    		detectDeadlocks(holders);
		    		
		    		D.addResource(R2);
		    		detectDeadlocks(holders);
		    		
		    		break;
		    	case SIX:
		    		
		    		A.getTargets().add(R1);
		    		B.getTargets().add(R2);
		    		D.getTargets().add(R4);
		    		C.getTargets().add(R3);
		    		
		    		B.addResource(R1);
		    		detectDeadlocks(holders);
		    		
		    		D.addResource(R2);
		    		detectDeadlocks(holders);
		    		
		    		C.addResource(R4);
		    		detectDeadlocks(holders);
		    		
		    		A.addResource(R3);
		    		detectDeadlocks(holders);
		    		
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
		    		detectDeadlocks(holders);
		    		
		    		D.addResource(R2);
		    		detectDeadlocks(holders);
		    		
		    		A.addResource(R3);
		    		detectDeadlocks(holders);
		    		
		    		C.addResource(R5);
		    		detectDeadlocks(holders);
		    		
		    		F.addResource(R7);
		    		detectDeadlocks(holders);
		    		
		    		E.addResource(R6);
		    		detectDeadlocks(holders);
		    		
		    		C.addResource(R4);
		    		detectDeadlocks(holders);
		    		
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
    
    public static void detectDeadlocks(List<Holder> holders) {
    	Boolean detectedDeadlock = false;
    	for(Holder h : holders) {
    		if(h.isInDeadlock()) {
    			System.out.println("\tHolder " + h.getName() + " is in deadlock for resource allocation " +
						h.getDeadlockCause().getResource().getName() + " to " +
						h.getDeadlockCause().getHolder().getName());
    		} else if(detectDeadlockFrom(h, holders)) {
    			System.out.println("\tHolder " + h.getName() + " is in deadlock for resource allocation " +
    						lastAllocation.getResource().getName() + " to " +
    						lastAllocation.getHolder().getName());
    			detectedDeadlock = true;
    		}
    	}
    	if(detectedDeadlock) {
    		solveDeadlockFor(lastAllocation, lastAllocation.getHolder(), holders);
    	}
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
    
}
