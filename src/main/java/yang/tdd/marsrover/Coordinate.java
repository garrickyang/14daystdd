package yang.tdd.marsrover;

public class Coordinate {

	int x;
	int y;
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void updateX(int x) {
		this.x=x;
	}
	public void updateY(int y) {
			this.y=y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj instanceof Coordinate){
			 return ((Coordinate) obj).getX()==x && ((Coordinate) obj).getY()==y;
			 }
		return false;
	}
}
