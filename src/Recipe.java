import java.awt.image.BufferedImage;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Daniel MOOSBRUGGER
 * 
 */
public class Recipe {

	/**
	 * The author of the recipe.
	 */
	private User author;
	/**
	 * The name of the recipe.
	 */
	private String name;
	/**
	 * The sort description of the recipe as seen in the recipe overview.
	 */
	private String shortDescription;
	/**
	 * The full description for preparing and cooking the recipe.
	 */
	private String howToCook;
	/**
	 * The time needed for cooking the recipe.
	 */
	private int timeToCook;
	/**
	 * The intended number of portions for the recipe.
	 */
	private int portions;
	/**
	 * The list of all ingredients that are used for the recipe.
	 */
	private List<Product> ingredients;
	/**
	 * The rating of the recipe.
	 */
	private Rating rating;
	/**
	 * A list of all comments for the recipe
	 */
	private List<Comment> comments;
	/**
	 * The tags for the recipe.
	 */
	private List<Integer> tags;
	/**
	 * The picture used for the recipe.
	 */
	private BufferedImage mainPicture;
	/**
	 * A list of additional images uploaded by the author or any other user.
	 */
	private List<BufferedImage> additionalPictures;
	// TODO: GregorianCalendar is useless when looking for a specific date - I used an int instead.
	/**
	 * The date when the recipe was first created as a int with the format YYYYMMDD.<br>
	 * For example: 20001231 for the 31st of December 2000.
	 */
	private int date;

	/**
	 * Constructor for a new Recipe.<br>
	 * Comments and additionalPictures are initiated with <b>null</b> (as well as mainPicture, if none is uploaded) and a new Rating object is
	 * created.
	 * 
	 * @param author
	 *            The author of the recipe.
	 * @param name
	 *            The name of the recipe.
	 * @param shortDescription
	 *            The short description of the recipe as seen in the recipe overview.
	 * @param howToCook
	 *            The full description for preparing and cooking the recipe.
	 * @param timeToCook
	 *            The time needed for cooking the recipe.
	 * @param portions
	 *            The intended number of portions for the recipe.
	 * @param ingredients
	 *            The list of all ingredients that are used for the recipe.
	 * @param tags
	 *            The tags for the recipe.
	 * @param picture
	 *            The main picture used for the recipe. <b>null</b> if no picture is uploaded.
	 */
	public Recipe(User author, String name, String shortDescription, String howToCook, int timeToCook, int portions,
			List<Product> ingredients, List<Integer> tags, BufferedImage picture) {
		this.author = author;
		this.name = name;
		this.shortDescription = shortDescription;
		this.howToCook = howToCook;
		this.timeToCook = timeToCook;
		this.portions = portions;
		this.ingredients = ingredients;
		this.tags = tags;
		this.rating = new Rating();
		this.comments = null;
		this.mainPicture = picture;
		this.additionalPictures = null;
		this.date = GregorianCalendar.YEAR * 10000 + GregorianCalendar.MONTH * 100 + GregorianCalendar.DAY_OF_MONTH;
	}

	/**
	 * Lets any logged in user upload a new picture for a recipe.
	 * 
	 * @param newPicture
	 *            The picture to be added to the recipe.
	 * @param uploader
	 *            The user that wants to upload a new picture.
	 * @return <b>true</b> if the user is logged in, and <b>false</b> otherwise.
	 */
	public boolean addPicture(BufferedImage newPicture, User uploader) {
		// TODO: getter for isLoggedIn in the User class needed
		if (uploader.getIsLoggedIn() && newPicture != null) {
			// TODO: an admin needs to validate the picture before it can be uploaded!
			this.additionalPictures.add(newPicture);
			return true;
		} else
			return false;
	}

	/**
	 * Method for editing a recipe. The editor must either be the original author or an admin.<br>
	 * Only parameters that are not <b>null</b> will be used for editing the recipe.
	 * 
	 * @param editor
	 *            The User that wants to edit the recipe.
	 * @param newName
	 *            The new name for the recipe.
	 * @param newShortDescription
	 *            The new short description for the recipe.
	 * @param newHowToCook
	 *            The new full description for preparing and cooking the recipe.
	 * @param newTimeToCook
	 *            The new time to prepare and cook the recipe.
	 * @param newPortions
	 *            The new number of intended portions.
	 * @param newIngredients
	 *            The new list of ingredients for the recipe.
	 * @param newTags
	 *            The new tags for the recipe.
	 * @param newPicture
	 *            The new main picture used for the recipe.
	 * @return <b>true</b> if the editor is the original author or an admin, and <b>false</b> otherwise.
	 */
	public boolean editRecipe(User editor, String newName, String newShortDescription, String newHowToCook, int newTimeToCook,
			int newPortions, List<Product> newIngredients, List<Integer> newTags, BufferedImage newPicture) {
		// TODO: an equals method for the User class (to check if the editor is the author) needed
		// TODO: getter for isAdminIn in the User class needed
		if (editor.equals(author) || editor.getIsAdmin()) {
			if (newName != null)
				this.name = newName;
			if (newShortDescription != null)
				this.shortDescription = newShortDescription;
			if (newHowToCook != null)
				this.howToCook = newHowToCook;
			if (newTimeToCook != 0)
				this.timeToCook = newTimeToCook;
			if (newPortions != 0)
				this.portions = newPortions;
			if (newIngredients != null)
				this.ingredients = newIngredients;
			if (newTags != null)
				this.tags = newTags;
			if (newPicture != null)
				this.mainPicture = newPicture;
			return true;
		}
		return false;
	}

	/**
	 * Lets an admin delete a previously uploaded picture by passing the picture object to delete.
	 * 
	 * @param deletingUser
	 *            The user that wants to delete a picture (has to be an adim).
	 * @param imageToRemove
	 *            The picture object that is to be deleted.
	 * @return <b>true</b> if the deleting User is an admin, and <b>false</b> otherwise.
	 */
	public boolean removeAdditionalPicture(User deletingUser, BufferedImage imageToRemove) {
		// TODO: getter for isAdminIn in the User class needed
		if (deletingUser.getIsAdmin()) {
			this.additionalPictures.remove(imageToRemove);
			return true;
		}
		return false;
	}

	/**
	 * Lets an admin delete a previously uploaded picture by passing the index of the picture to delete.
	 * 
	 * @param deletingUser
	 *            The user that wants to delete a picture (has to be an adim).
	 * @param imageToRemove
	 *            The index of the picture that is to be deleted.
	 * @return <b>true</b> if the deleting User is an admin, and <b>false</b> otherwise.
	 */
	public boolean removeAdditionalPicture(User deletingUser, int imageToRemove) {
		// TODO: getter for isAdminIn in the User class needed
		if (deletingUser.getIsAdmin()) {
			this.additionalPictures.remove(imageToRemove);
			return true;
		}
		return false;
	}

	public User getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getHowToCook() {
		return howToCook;
	}

	public int getTimeToCook() {
		return timeToCook;
	}

	public int getPortions() {
		return portions;
	}

	public List<Product> getIngredients() {
		return ingredients;
	}

	public Rating getRating() {
		return rating;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<Integer> getTags() {
		return tags;
	}

	public BufferedImage getMainPicture() {
		return mainPicture;
	}

	public List<BufferedImage> getAdditionalPictures() {
		return additionalPictures;
	}

	/**
	 * @return The date when the recipe was first created as an int in the format YYYYMMDD.<br>
	 *         For example: 20001231 for the 31st of December 2000.
	 */
	public int getDate() {
		return date;
	}

}
