package yang.tdd.marsrover;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MarsRoverTest {
	List<Coordinate> barriers = new ArrayList<>();
	MarsMap marsMap;
	MarsCommand initMapCommand;
	MarsCommand initMarsRoverCommand;
	MarsCommand moveForeardCommand;
	MarsCommand moveBackCommand;
	MarsCommand turnLeftCommand;
	MarsCommand turnRightCommand;

	@Before
	public void setUp() throws Exception {
		marsMap = new MarsMap(new Coordinate(100, 100), barriers);
		barriers.add(new Coordinate(5, 5));
		barriers.add(new Coordinate(7, 7));
		initMapCommand= new MarsCommand(CommandType.MAP_SETTING, marsMap, null, null, null, null);
		initMarsRoverCommand = new MarsCommand(CommandType.INITIAL_SESSTING, null, new Coordinate(10, 9), Direction.N,
				null, null);
		moveForeardCommand = new MarsCommand(CommandType.MOVE, null, null, null, MoveDirection.F, null);
		moveBackCommand = new MarsCommand(CommandType.MOVE, null, null, null, MoveDirection.B, null);
		turnLeftCommand = new MarsCommand(CommandType.TRURN, null, null, null, null, TurnDirection.L);
		turnRightCommand = new MarsCommand(CommandType.TRURN, null, null, null, null, TurnDirection.R);
	
	}
	@Test
	public void testBarriers(){
		assertTrue(barriers.contains(new Coordinate(5, 5)));
	}

	@Test
	public void oneExploreMapInitializeTest() {
		List<MarsCommand> commands = new ArrayList<MarsCommand>();
		commands.add(initMapCommand);
		MarsRover marsRover = new MarsRover();
		marsRover.accept(commands);
		assertEquals(100, marsRover.getMapCoordinate().getMapScope().getX());
		assertEquals(100, marsRover.getMapCoordinate().getMapScope().getY());
	}

	@Test
	public void initiallizeMarsRoverCoordinate() {
		List<MarsCommand> commands = new ArrayList<MarsCommand>();
		commands.add(initMarsRoverCommand);
		MarsRover marsRover = new MarsRover();
		marsRover.accept(commands);
		MarsPosition marsPosition = marsRover.currentMarsPosition();
		assertEquals(10, marsPosition.getCoordinate().getX());
		assertEquals(9, marsPosition.getCoordinate().getY());
		assertEquals(Direction.N, marsPosition.getDirection());
	}

	@Test
	public void moveAndTeset() {
		List<MarsCommand> commands = new ArrayList<MarsCommand>();
		commands.add(initMapCommand);
		commands.add(initMarsRoverCommand);
		commands.add(moveForeardCommand);
		MarsRover marsRover = new MarsRover();
		marsRover.accept(commands);
		MarsPosition marsPosition = marsRover.currentMarsPosition();
		assertEquals(10, marsPosition.getCoordinate().getX());
		assertEquals(10, marsPosition.getCoordinate().getY());
		assertEquals(Direction.N, marsPosition.getDirection());
	}

	@Test
	public void moveAndTurnTeset() {
		List<MarsCommand> commands = new ArrayList<MarsCommand>();
		commands.add(initMapCommand);
		commands.add(initMarsRoverCommand);
		commands.add(moveForeardCommand);
		commands.add(turnLeftCommand);
		commands.add(moveForeardCommand);
		commands.add(moveBackCommand);
		commands.add(turnRightCommand);
		MarsRover marsRover = new MarsRover();
		marsRover.accept(commands);
		MarsPosition marsPosition = marsRover.currentMarsPosition();
		assertEquals(10, marsPosition.getCoordinate().getX());
		assertEquals(10, marsPosition.getCoordinate().getY());
		assertEquals(Direction.N, marsPosition.getDirection());
	}

	@Test
	public void moveOnBoundaryMapTest() {
		initMarsRoverCommand = new MarsCommand(CommandType.INITIAL_SESSTING, null,new Coordinate(100, 100), Direction.N, null,
				null);
		List<MarsCommand> commands = new ArrayList<MarsCommand>();
		commands.add(initMapCommand);
		commands.add(initMarsRoverCommand);
		commands.add(moveForeardCommand);
		commands.add(turnRightCommand);
		commands.add(moveForeardCommand);
		MarsRover marsRover = new MarsRover();
		marsRover.accept(commands);
		MarsPosition marsPosition = marsRover.currentMarsPosition();
		assertEquals(100, marsPosition.getCoordinate().getX());
		assertEquals(100, marsPosition.getCoordinate().getY());
		assertEquals(Direction.E, marsPosition.getDirection());
	}

	@Test
	public void moveOutOfMapAndReturnTest() {
		initMarsRoverCommand = new MarsCommand(CommandType.INITIAL_SESSTING,null, new Coordinate(0, 0), Direction.N, null,
				null);
		List<MarsCommand> commands = new ArrayList<MarsCommand>();
		commands.add(initMapCommand);
		commands.add(initMarsRoverCommand);
		commands.add(moveBackCommand);
		commands.add(turnRightCommand);
		commands.add(moveBackCommand);
		MarsRover marsRover = new MarsRover();
		marsRover.accept(commands);
		MarsPosition marsPosition = marsRover.currentMarsPosition();
		assertEquals(99, marsPosition.getCoordinate().getX());
		assertEquals(99, marsPosition.getCoordinate().getY());
		assertEquals(Direction.E, marsPosition.getDirection());
	}
	
	 @Test
	 public void moveOnBarrierTest(){
		 initMarsRoverCommand = new MarsCommand(CommandType.INITIAL_SESSTING, null, new Coordinate(4, 4), Direction.N,
					null, null);
	 List<MarsCommand> commands= new ArrayList<MarsCommand>();
	 commands.add(initMapCommand);
	 commands.add(initMarsRoverCommand);
	 commands.add(moveForeardCommand);
	 commands.add(turnRightCommand);
	 commands.add(moveForeardCommand);
	 commands.add(moveForeardCommand);
	 commands.add(moveForeardCommand);
	 commands.add(moveForeardCommand);
	 MarsRover marsRover=new MarsRover();
	 marsRover.accept(commands);
	 MarsPosition marsPosition=marsRover.currentMarsPosition();
	 assertEquals(4, marsPosition.getCoordinate().getX());
	 assertEquals(5, marsPosition.getCoordinate().getY());
	 assertEquals(Direction.E,marsPosition.getDirection());

	 }

}
