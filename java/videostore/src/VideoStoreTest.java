import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest
{
	private static final String NEW_RELEASE_MOVIE_TITLE_A = "The Cell";
	private static final String CUSTOMER_NAME = "Fred";
	private static final String CHILDRENS_MOVIE_TITLE = "The Tigger Movie";
	private static final String REGULAR_MOVIE_TITLE_A = "Plan 9 from Outer Space";
	private static final String REGULAR_MOVIE_TITLE_B = "Eraserhead";
	private static final String REGULAR_MOVIE_TITLE_C = "8 1/2";
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
		statement = new Statement(CUSTOMER_NAME);
		newReleaseMovieA = new Rental(new NewReleaseMovie(NEW_RELEASE_MOVIE_TITLE_A), 3);
		newReleaseMovieB = new Rental(new NewReleaseMovie(CHILDRENS_MOVIE_TITLE), 3);
		childrensMovie = new Rental(new ChildrensMovie(CHILDRENS_MOVIE_TITLE), 3);
		regularMovieA = new Rental(new RegularMovie(REGULAR_MOVIE_TITLE_A), 1);
		regularMovieB = new Rental(new RegularMovie(REGULAR_MOVIE_TITLE_C), 2);
		regularMovieC = new Rental(new RegularMovie(REGULAR_MOVIE_TITLE_B), 3);
	}

	@Test
	public void testSingleNewReleaseStatement () {
		statement.addRental (newReleaseMovieA);
		assertEquals (String.format("Rental Record for %s\n\t%s\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", CUSTOMER_NAME, NEW_RELEASE_MOVIE_TITLE_A), statement.generateStatement());
	}

	@Test
	public void testDualNewReleaseStatement () {
		statement.addRental (newReleaseMovieA);
		statement.addRental (newReleaseMovieB);
		assertEquals (String.format("Rental Record for %s\n\t%s\t9.0\n\t%s\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", CUSTOMER_NAME, NEW_RELEASE_MOVIE_TITLE_A, CHILDRENS_MOVIE_TITLE), statement.generateStatement());
	}

	@Test
	public void testSingleChildrensStatement () {
		statement.addRental (childrensMovie);
		assertEquals (String.format("Rental Record for %s\n\t%s\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", CUSTOMER_NAME, CHILDRENS_MOVIE_TITLE), statement.generateStatement());
	}

	@Test
	public void testMultipleRegularStatement () {
		statement.addRental (regularMovieA);
		statement.addRental (regularMovieB);
		statement.addRental (regularMovieC);

		assertEquals (String.format("Rental Record for %s\n\t%s\t2.0\n\t%s\t2.0\n\t%s\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", CUSTOMER_NAME, REGULAR_MOVIE_TITLE_A, REGULAR_MOVIE_TITLE_C, REGULAR_MOVIE_TITLE_B), statement.generateStatement());
	}

}
