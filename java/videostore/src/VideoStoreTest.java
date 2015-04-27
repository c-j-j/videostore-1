import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest
{
	private Rental newReleaseMovieA;
	private Rental newReleaseMovieB;

	private Statement statement;
	private Rental childrensMovie;
	private Rental regularMovieA;
	private Rental regularMovieB;
	private Rental regularMovieC;

	public VideoStoreTest() {
	}

	@Before
	public void setUp() throws Exception {
		statement = new Statement("Fred");
		newReleaseMovieA = new Rental(new NewReleaseMovie("The Cell"), 3);
		newReleaseMovieB = new Rental(new NewReleaseMovie("The Tigger Movie"), 3);
		childrensMovie = new Rental(new ChildrensMovie("The Tigger Movie"), 3);
		regularMovieA = new Rental(new RegularMovie("Plan 9 from Outer Space"), 1);
		regularMovieB = new Rental(new RegularMovie("8 1/2"), 2);
		regularMovieC = new Rental(new RegularMovie("Eraserhead"), 3);
	}

	@Test
	public void testSingleNewReleaseStatement () {
		statement.addRental (newReleaseMovieA);
		assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", statement.generateStatement());
	}

	@Test
	public void testDualNewReleaseStatement () {
		statement.addRental (newReleaseMovieA);
		statement.addRental (newReleaseMovieB);
		assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", statement.generateStatement());
	}

	@Test
	public void testSingleChildrensStatement () {
		statement.addRental (childrensMovie);
		assertEquals ("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", statement.generateStatement());
	}

	@Test
	public void testMultipleRegularStatement () {
		statement.addRental (regularMovieA);
		statement.addRental (regularMovieB);
		statement.addRental (regularMovieC);

		assertEquals ("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", statement.generateStatement());
	}

}
