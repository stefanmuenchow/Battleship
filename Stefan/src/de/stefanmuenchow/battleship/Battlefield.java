package de.stefanmuenchow.battleship;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import de.stefanmuenchow.battleship.common.PatchStatus;
import de.stefanmuenchow.battleship.common.Position;

public class Battlefield {
	private static final int FIELD_SIZE = 10;
	private final List<Patch> patches;
	
	public Battlefield() {
		patches = new ArrayList<Patch>(FIELD_SIZE * FIELD_SIZE);
		
		for (int i = 0; i < FIELD_SIZE * FIELD_SIZE; i++) {
			Position newPos = new Position(i / FIELD_SIZE, i % FIELD_SIZE);
			patches.add(new Patch(newPos, PatchStatus.Unknown, 0.5d));
		}
	}
	
	public List<Patch> getPatches() {
		return patches;
	}
	
	public SortedMap<Double, List<Patch>> getProbabilitiesWithPatches() {
		SortedMap<Double, List<Patch>> result = new TreeMap<Double, List<Patch>>(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return o2.compareTo(o1);
			}
		});
		
		for (Patch p : getPatches()) {
			if (result.containsKey(p.getHitProbability())) {
				result.get(p.getHitProbability()).add(p);
			} else {
				List<Patch> tmpList = new ArrayList<Patch>();
				tmpList.add(p);
				result.put(p.getHitProbability(), tmpList);
			}
		}
		
		return result;
	}
	
	Patch getPatch(int x, int y) {
		return patches.get(x * FIELD_SIZE + y);
	}
	
	void setPatch(int x, int y, Patch patch) {
		patches.set(x * FIELD_SIZE + y, patch);
	}
	
	void setHitProbability(int x, int y, double probability) {
		getPatch(x, y).setHitProbability(probability);
	}
	
	void setStatus(int x, int y, PatchStatus status) {
		getPatch(x, y).setStatus(status);
	}
}
