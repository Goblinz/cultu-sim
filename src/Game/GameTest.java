package Game;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class GameTest {

	@Test
	public void testPatrol() {
		Game game = new Game();
		Point[] path = {new Point(5,5),new Point(0,5),new Point(5,0)};
		game.spawnUnitMoveGather(6, 6,path);
		//test actor is on 6,6
		if(!game.world.getTiles()[6][6].isActorOnTile())
			fail("actor should be there");
		game.tick();
		if(game.world.getTiles()[6][6].isActorOnTile())
			fail("actor should not be there");
		if(!game.world.getTiles()[5][5].isActorOnTile())
			fail("actor should be there");
		game.tick();
		game.tick();
		game.tick();
		game.tick();
		game.tick();
		if(!game.world.getTiles()[0][5].isActorOnTile())
			fail("actor should be there");
		game.tick();
		game.tick();
		game.tick();
		game.tick();
		game.tick();
		if(!game.world.getTiles()[5][0].isActorOnTile())
			fail("actor should be there");
		game.tick();
		game.tick();
		game.tick();
		game.tick();
		game.tick();
		if(!game.world.getTiles()[5][5].isActorOnTile())
			fail("actor should be there");
	}

}
