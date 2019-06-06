package yang.tdd.marsrover;

public class MarsCommand {

	CommandType commandType;
	MarsMap masMap;

	Coordinate coordinate;
	Direction directtion;
	MoveDirection moveDirection;
	TurnDirection turnDirection;

	public MarsCommand(CommandType commandType, MarsMap marsMap, Coordinate coordinate, Direction directtion,
			MoveDirection moveDirection, TurnDirection turnDirection) {
		this.commandType = commandType;
		this.masMap = marsMap;
		this.coordinate = coordinate;
		this.directtion = directtion;
		this.moveDirection = moveDirection;
		this.turnDirection = turnDirection;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Direction getDirecttion() {
		return directtion;
	}

	public MoveDirection getMoveDirection() {
		return moveDirection;
	}

	public TurnDirection getTurnDirection() {
		return turnDirection;
	}

	public MarsMap getMasMap() {
		return masMap;
	}

}
