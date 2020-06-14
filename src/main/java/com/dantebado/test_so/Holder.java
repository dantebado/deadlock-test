package com.dantebado.test_so;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Holder {
	
	String name;
	
	List<Resource> targets;
	List<Resource> holding;
	
	ResourceAllocation deadlockCause;
	
	public Holder(String name) {
		this(name, new ArrayList<Resource>(), new ArrayList<Resource>(), null);
	}
	
	public boolean isInDeadlock() {
		return deadlockCause != null;
	}
	
	public void removeFromTargets(Resource resource) {
		List<Resource> list = targets;
		for(int i=0 ; i<list.size() ; i++) {
			if(list.get(i).getName().equals(resource.getName())) {
				list.remove(i);
				return;
			}
		}
	}
	
	public void removeFromHoldings(Resource resource) {
		List<Resource> list = holding;
		for(int i=0 ; i<list.size() ; i++) {
			if(list.get(i).getName().equals(resource.getName())) {
				list.remove(i);
				return;
			}
		}
	}
	
	public void printStatus() {
		System.out.println("    Holder " + name);
		System.out.println("\tCurrent Targets");
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(Resource r : targets) {
			Integer tc = map.get(r.getName());
			if(tc == null) {
				map.put(r.getName(), 1);
			} else {
				map.put(r.getName(), tc+1);
			}
		}
		for(String key : map.keySet()) {
			System.out.println("\t\tResource " + key + " : " + map.get(key));
		}
		if(map.size() == 0)
			System.out.println("\t\t---");
		map.clear();
		System.out.println("\tCurrent Holdings");
		for(Resource r : holding) {
			Integer tc = map.get(r.getName());
			if(tc == null) {
				map.put(r.getName(), 1);
			} else {
				map.put(r.getName(), tc+1);
			}
		}
		for(String key : map.keySet()) {
			System.out.println("\t\tHolding " + key + " : " + map.get(key));
		}
		if(map.size() == 0)
			System.out.println("\t\t---");
		
		System.out.println("\tDeadlock : " + isInDeadlock());
	}

	public boolean isCompleted() {
		return targets.isEmpty();
	}

	public void addResource(Resource r) {
		System.out.println("    Adding resource " + r.getName() + " to holder " + this.getName());
		this.getHolding().add(r);
		App.lastAllocation = new ResourceAllocation(r, this);
	}
	
}
