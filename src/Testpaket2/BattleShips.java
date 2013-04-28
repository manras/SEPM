package Testpaket2;
import java.util.Scanner;

public class BattleShips {

	/** The size of the square game field */
	private static int FIELD_SIZE = 10;

	public static void main(final String[] args) {
		final char gamefield[][] = new char[BattleShips.FIELD_SIZE][BattleShips.FIELD_SIZE];
		final char gamefieldOpponent[][] = new char[BattleShips.FIELD_SIZE][BattleShips.FIELD_SIZE];
		int round = 0;

		BattleShips.initGameField(gamefield);
		BattleShips.initGameField(gamefieldOpponent); // gamefieldOpponent is only used as a reference,
		BattleShips.placeShips(gamefieldOpponent); // it won't be changed during the game at all

		/*
		 * This prints out the computer's field, and is used to see if all the ships were placed correctly and with the right length. It's also good
		 * for cheating.
		 */
		System.out.println("For cheaters: here're the bad guy's ships:");
		for (int i = 0; i < BattleShips.FIELD_SIZE; i++) {
			for (int j = 0; j < BattleShips.FIELD_SIZE; j++)
				System.out.printf(" %c", gamefieldOpponent[i][j]);
			System.out.println(" |");
		}
		System.out.println("---------------------/");

		while (!BattleShips.allShipsSunk(gamefield)) {
			round++;
			System.out.println("\n\nRound " + round + "\n");
			BattleShips.printGameField(gamefield);

			System.out.println("Enter horizontal coordinate"); // get coordinates to fire
			final int x = BattleShips.readIntFromCommandLine(0, BattleShips.FIELD_SIZE - 1);
			System.out.println("Enter vertical coordinate");
			final int y = BattleShips.readIntFromCommandLine(0, BattleShips.FIELD_SIZE - 1);

			if (BattleShips.fieldIsShip(gamefieldOpponent, x, y)) {
				gamefield[x][y] = '$'; // ship is hit - mark with $
			} else {
				gamefield[x][y] = 'o'; // water is hit - mark with o
			}
		}

		BattleShips.printGameField(gamefield); // out of the while-loop: game is won
		System.out.println("You got the damn bastard!");
		System.out.println("Game finished in " + round + " rounds!");
	}

	/**
	 * Initialize the game field with '~' (water symbols).
	 */
	private static void initGameField(final char gamefield[][]) {

		int i, j;
		for (i = 0; i < BattleShips.FIELD_SIZE; i++) {
			for (j = 0; j < BattleShips.FIELD_SIZE; j++)
				gamefield[i][j] = '~';
		}
	}

	/**
	 * Checks if the ship can be placed in a certain row or column.
	 * 
	 * @returns true if there is enough room for the ship
	 */
	private static boolean isThereRoom(int line, int direction, int ship, final char gamefield[][]) {

		int check = 0;
		for (int i = 0; i < (BattleShips.FIELD_SIZE - ship); i++) {
			for (int j = i; j < BattleShips.FIELD_SIZE; j++) {

				if (direction == 0) { // ship is being placed horizontally

					if (gamefield[line][j] == '~') {
						check++;
						if (gamefield[line][j] != '~')
							check = 0;
						if (check == ship) // if the number of '~' IN A ROW is at
							return true; // least the same as the length of the
					} // ship, isThereRoom returns true.
				}

				if (direction == 1) { // ship is being placed vertically
					if (gamefield[j][line] == '~') {
						check++;
						if (gamefield[j][line] != '~')
							check = 0;
						if (check == ship)
							return true;
					}
				}
			}
		}
		return false; // only happens, if there is no more room for the
	} // current ship in the current row or column

	/**
	 * Places ships on the opponent's field (not visible to player).
	 */
	private static void placeShips(final char gamefield[][]) {

		int x, i, j, k, l, m, counter;
		int direction, line, ship;

		for (x = 0; x < 10; x++) { // runs the for-loop ten times: once for every
									// ship to place in the game field.
			switch (x) { // the length of the current ship.
			case 0:
				ship = 5;
				break; // the longest ship comes first,
			case 1:
			case 2:
				ship = 4;
				break; // then come the smaller ones.
			case 3:
			case 4:
			case 5:
				ship = 3;
				break;
			default:
				ship = 2; // gives a total of 10 ships: one ship with
			} // a length of 5, two 4s, three 3s, four 2s.

			i = 0;
			while (i != 1) { // this while-loop ends as soon as the current ship is placed on the game field
				line = BattleShips.random((BattleShips.FIELD_SIZE - 1)); // random line in the game field
				direction = BattleShips.random(1); // horizontal or vertical?

				if (direction == 0) { // ship is placed horizontally
					if (BattleShips.isThereRoom(line, direction, ship, gamefield) == true) {

						j = 0;
						while (j != 1) {
							k = BattleShips.random((BattleShips.FIELD_SIZE - ship)); // a random field in the row
							counter = 0;
							for (l = k; l <= (BattleShips.FIELD_SIZE - ship); l++) { // is there place for the ship
								if (gamefield[line][l] == '~') // in the current row from a
									counter++; // random starting point?
								if (gamefield[line][l] != '~') { // if there's no room for the ship from this starting point,
									counter = 0; // another random starting point in that row is chosen.
								}
								if (counter == ship) { // a place for the ship is found!
									for (m = l; m >= k; m--)
										// formerly (m = l-(ship-1); m <= ship; m++)
										gamefield[line][m] = '*';
									j = 1;
									l = BattleShips.FIELD_SIZE; // the whole ship is on the game field,
								} // and the while-loop ends
							}
						}
						i = 1; // line + direction + ship combination was valid, ship is on the field, loop ends
					}
				}

				if (direction == 1) {
					if (BattleShips.isThereRoom(line, direction, ship, gamefield) == true) {

						j = 0;
						while (j != 1) {
							k = BattleShips.random((BattleShips.FIELD_SIZE - ship)); // a random field in the row
							counter = 0;
							for (l = k; l <= (BattleShips.FIELD_SIZE - ship); l++) { // is there place for the ship
								if (gamefield[l][line] == '~') // in the current row from a
									counter++; // random starting point?
								if (gamefield[l][line] != '~') { // if there's no room for the ship from this starting point,
									counter = 0; // another random starting point in that row is chosen.
								}
								if (counter == ship) { // a place for the ship is found!
									for (m = l; m >= k; m--)
										gamefield[m][line] = '*';
									j = 1;
									l = BattleShips.FIELD_SIZE; // the whole ship is on the field
								} // while-loop ends
							}
						}
						i = 1; // this loop also endeth
					}
				}
			}
		}
	}

	/**
	 * Generates a random number.
	 * 
	 * @returns a random number between 0 and the variable max
	 */
	private static int random(int max) {
		return (int) Math.round(Math.floor(Math.random() * (max + 1)));
	}

	/**
	 * Prints the game field.
	 */
	private static void printGameField(final char gamefield[][]) {

		int i, j;
		System.out.printf(" "); // prints the numbers
		for (i = 0; i < BattleShips.FIELD_SIZE; i++)
			// from 0 to FIELD_SIZE-1
			System.out.printf(" " + i);
		System.out.println(); // line's done, time for the actual game field

		for (i = 0; i < BattleShips.FIELD_SIZE; i++) { // prints out each row's
			System.out.printf("%d", i); // number and the game field
			for (j = 0; j < BattleShips.FIELD_SIZE; j++)
				System.out.printf(" %c", gamefield[i][j]);
			System.out.println();
		}
	}

	/**
	 * Checks if all ships have been sunk.
	 * 
	 * @returns true if all ships are sunk
	 */
	private static boolean allShipsSunk(final char gamefield[][]) {

		int hit = 0;
		for (int i = 0; i < BattleShips.FIELD_SIZE; i++) {
			for (int j = 0; j < BattleShips.FIELD_SIZE; j++) {
				if (gamefield[i][j] == '$')
					hit++;
				if (hit == 30)
					return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a field is part of a ship.
	 * 
	 * @returns true if a ship is hit
	 */
	private static boolean fieldIsShip(final char gamefield[][], final int x, final int y) {

		if (gamefield[x][y] == '*') // returns true, if some part of a ship was hit
			return true;
		else
			return false;
	}

	/**
	 * Reads an integer from command line and asks again for input if the user enters a non-integer value or a value exceeding the [min, max] range
	 * 
	 * @returns the integer from command line
	 */
	private static int readIntFromCommandLine(final int min, final int max) {
		boolean gotBadValue = true;
		int integerValue = 0;

		while (gotBadValue) {
			final Scanner in = new Scanner(System.in);
			try {
				integerValue = in.nextInt();
				if ((integerValue < min) || (integerValue > max)) {
					throw new Exception();
				}
				gotBadValue = false; // gotGoodValue - end method
			} catch (final Exception e) {
				System.out.printf("Only numbers between " + min + " and " + max + " are allowed");
			}
		}
		return integerValue;
	}
}