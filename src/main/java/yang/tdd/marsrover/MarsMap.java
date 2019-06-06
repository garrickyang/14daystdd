package yang.tdd.marsrover;

import java.util.List;

public class MarsMap {
	private Coordinate mapScope;
	private List<Coordinate> barriers;
	public Coordinate getMapScope() {
		return mapScope;
	}
	public List<Coordinate> getBarriers() {
		return barriers;
	}
	public MarsMap(Coordinate mapScope, List<Coordinate> barriers) {
		this.mapScope=mapScope;
		this.barriers=barriers;
	}

}
