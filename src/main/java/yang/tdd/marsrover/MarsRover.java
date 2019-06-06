package yang.tdd.marsrover;

import java.util.List;

public class MarsRover {
	MarsMap mapCoordinate;
	MarsPosition marsPosition;
	
	public MarsRover() {
		marsPosition = new MarsPosition();
	}

	public MarsMap getMapCoordinate() {
		return mapCoordinate;
	}

	public void accept(List<MarsCommand> commands) {
		
		for(MarsCommand command: commands){
			try {
				processCommand(command);
			} catch (BarrierException e) {
				e.printStackTrace();
				break;
			}
		}

	}

	private void processCommand(MarsCommand command) throws BarrierException {
		if (command.getCommandType() == CommandType.MAP_SETTING) {
			settingMap(command.getMasMap());
		}
		if (command.getCommandType() == CommandType.INITIAL_SESSTING) {
			inialPosition(command.getCoordinate(), command.getDirecttion());
		}
		if (command.getCommandType() == CommandType.MOVE) {
			move(command.moveDirection);
		}
		if (command.getCommandType() == CommandType.TRURN) {
			turn(command.getTurnDirection());
		}
	}

	private void turn(TurnDirection turnDirection) {
		if (turnDirection == TurnDirection.L) {
			turnLeft();
		}
		if (turnDirection == TurnDirection.R) {
			turnRight();
		}
	}

	private void turnRight() {
		switch (marsPosition.getDirection()) {
		case N:
			marsPosition.setDirection(Direction.E);
			break;
		case W:
			marsPosition.setDirection(Direction.N);
			break;
		case S:
			marsPosition.setDirection(Direction.W);
			break;
		case E:
			marsPosition.setDirection(Direction.S);
			break;
		}
	}

	private void turnLeft() {
		switch (marsPosition.getDirection()) {
		case N:
			marsPosition.setDirection(Direction.W);
			break;
		case W:
			marsPosition.setDirection(Direction.S);
			break;
		case S:
			marsPosition.setDirection(Direction.E);
			break;
		case E:
			marsPosition.setDirection(Direction.N);
			break;
		}
	}

	private void move(MoveDirection moveDirection) throws BarrierException {
		Coordinate lastPosition= new Coordinate(marsPosition.getCoordinate().getX(), marsPosition.getCoordinate().getY());
		if (moveDirection == MoveDirection.F) {
			moveForward();
		}
		if (moveDirection == MoveDirection.B) {
			moveBack();
		}
		
		if(touchBarrier()){
			marsPosition.setCoordinate(lastPosition);
			throw new BarrierException("A barrier is in front!");
		}
		ajustBoundary();
	}

	private boolean touchBarrier() {
		return mapCoordinate.getBarriers().contains(marsPosition.getCoordinate());
	}

	private void moveForward() {
		switch (marsPosition.getDirection()) {
		case N:
			marsPosition.getCoordinate().updateY(marsPosition.getCoordinate().getY() + 1);
			break;
		case S:
			marsPosition.getCoordinate().updateY(marsPosition.getCoordinate().getY() - 1);
			break;
		case E:
			marsPosition.getCoordinate().updateX(marsPosition.getCoordinate().getX() + 1);
			break;
		case W:
			marsPosition.getCoordinate().updateX(marsPosition.getCoordinate().getX() - 1);
			break;
		}
	}

	private void moveBack() {
		switch (marsPosition.getDirection()) {
		case N:
			marsPosition.getCoordinate().updateY(marsPosition.getCoordinate().getY() - 1);
			break;
		case S:
			marsPosition.getCoordinate().updateY(marsPosition.getCoordinate().getY() + 1);
			break;
		case E:
			marsPosition.getCoordinate().updateX(marsPosition.getCoordinate().getX() - 1);
			break;
		case W:
			marsPosition.getCoordinate().updateX(marsPosition.getCoordinate().getX() + 1);
			break;
		}
	}

	private void ajustBoundary() {
		stopMoveOnBoundary();
		turnBackToMap();
	}

	private void turnBackToMap() {
		turnBackToMapInX();
		turnBackToMapInY();
	}

	private void turnBackToMapInY() {
		if(marsPosition.getCoordinate().getY()<0){
			marsPosition.getCoordinate().updateY(mapCoordinate.getMapScope().getY()-1);
		}
	}

	private void turnBackToMapInX() {
		if(marsPosition.getCoordinate().getX()<0){
			marsPosition.getCoordinate().updateX(mapCoordinate.getMapScope().getX()-1);
		}
	}

	private void stopMoveOnBoundary() {
		stopMoveOnXBoundary();		
		stopMoveOnYBoundary();
	}

	private void stopMoveOnYBoundary() {
		if(marsPosition.getCoordinate().getY()>mapCoordinate.getMapScope().getY()){
			marsPosition.getCoordinate().updateY(mapCoordinate.getMapScope().getY());
		}
	}

	private void stopMoveOnXBoundary() {
		if(marsPosition.getCoordinate().getX()>mapCoordinate.getMapScope().getX()){
			marsPosition.getCoordinate().updateX(mapCoordinate.getMapScope().getX());
		}
	}

	private void inialPosition(Coordinate coordinate, Direction direction) {
		this.marsPosition.setCoordinate(coordinate);
		this.marsPosition.setDirection(direction);
	}

	private void settingMap(MarsMap coordinate) {
		mapCoordinate = coordinate;
	}

	public MarsPosition currentMarsPosition() {
		return marsPosition;
	}

}