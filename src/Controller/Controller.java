package Controller;

import java.awt.image.BufferedImage;
import java.util.List;

import Model.Product;
import Model.Recipe;
import Model.User;

/**
 * 
 * @author Daniel MOOSBRUGGER
 * 
 */
public class Controller {

	/**
	 * Adds and returns a new Recipe.<br>
	 * Comments and additionalPictures are initiated with <b>null</b> (as well as mainPicture, if none is uploaded) and a new Rating object is
	 * created. A random and unique ID (UUID) will also be created.
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
	 * @return The newly created recipe.
	 */
	public Recipe addRecipe(User author, String name, String shortDescription, String howToCook, int timeToCook, int portions,
			List<Product> ingredients, List<Integer> tags, BufferedImage picture) {
		return new Recipe(author, name, shortDescription, howToCook, timeToCook, portions, ingredients, tags, picture);
	}

	public String addRecipePicture(Recipe recipe, BufferedImage newPicture, User uploader) {
		boolean uploadOK = recipe.addPicture(newPicture, uploader);
		if (uploadOK) {
			return "Bild wurde zum Rezpt hinzugefuegt.";
		} else {
			return "Bild konnte nicht hinzugefuegt werden!";
		}
	}

	/**
	 * Method for editing a recipe. The editor must either be the original author or an admin.<br>
	 * Only parameters that are not <b>null</b> will be used for editing the recipe.
	 * 
	 * @param recipe
	 *            The recipe to be edited.
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
	 * @return A string telling the user if the edit was successful or not.
	 */
	public String editRecipe(Recipe recipe, User editor, String newName, String newShortDescription, String newHowToCook,
			int newTimeToCook, int newPortions, List<Product> newIngredients, List<Integer> newTags, BufferedImage newPicture) {
		boolean editOK = recipe.editRecipe(editor, newName, newShortDescription, newHowToCook, newTimeToCook, newPortions, newIngredients,
				newTags, newPicture);
		if (editOK) {
			return "Rezept " + recipe.getName() + " wurde erfolgreich editiert.";
		} else {
			return "Rezept " + recipe.getName() + " konnte nicht editiert werden!";
		}
	}

	/**
	 * Lets any logged in user upload a new picture for a recipe.
	 * 
	 * @param recipe
	 *            The recipe to which a new picture is to be added.
	 * @param newPicture
	 *            The picture to be added to the recipe.
	 * @param uploader
	 *            The user that wants to upload a new picture.
	 * @return A string telling the user if the deleting was successful or not.
	 */
	public String addAdditionalPicture(Recipe recipe, BufferedImage newPicture, User uploader) {
		boolean uploadOK = recipe.addPicture(newPicture, uploader);
		if (uploadOK) {
			return "Bild wurde erfolgreich hinzugefuegt.";
		} else {
			return "Bild konnte nicht hinzugefuegt werden!";
		}
	}

	/**
	 * Lets an admin delete a previously uploaded picture by passing the picture object to delete.
	 * 
	 * @param recipe
	 *            The Recipe in which a picture is to be deleted.
	 * @param deletingUser
	 *            The user that wants to delete a picture (has to be an adim).
	 * @param imageToRemove
	 *            The picture object that is to be deleted.
	 * @return A string telling the user if the deletion was successful or not.
	 */
	public String removeAdditionalPicture(Recipe recipe, User deletingUser, BufferedImage imageToRemoveObject) {
		boolean deleteOK = recipe.removeAdditionalPicture(deletingUser, imageToRemoveObject);
		if (deleteOK) {
			return "Bild wurde erfolgreich geloescht.";
		} else {
			return "Bild konnte nicht geloescht werden!";
		}
	}

	/**
	 * Lets an admin delete a previously uploaded picture by passing the index of the picture to delete.
	 * 
	 * @param recipe
	 *            The Recipe in which a picture is to be deleted.
	 * @param deletingUser
	 *            The user that wants to delete a picture (has to be an adim).
	 * @param imageToRemove
	 *            The index of the picture that is to be deleted.
	 * @return A string telling the user if the deletion was successful or not.
	 */
	public String removeAdditionalPicture(Recipe recipe, User deletingUser, int imageToRemoveIndex) {
		boolean deleteOK = recipe.removeAdditionalPicture(deletingUser, imageToRemoveIndex);
		if (deleteOK) {
			return "Bild wurde erfolgreich geloescht.";
		} else {
			return "Bild konnte nicht geloescht werden!";
		}
	}

	/**
	 * Adds and returns a new Product.<br>
	 * A random and unique ID (UUID) will also be created.
	 * 
	 * @param specificName
	 *            The specific name of the product.
	 * @param description
	 *            A short description of the product.
	 * @param diet
	 *            A list of all the diets that are supported by the product.
	 * @param allergen
	 *            A list of all allergens contained in the product.
	 * @param unit
	 *            The measuring unit of the product, e.g. "ml", "g", "Stueck".
	 * @param content
	 *            The amount of content of the product.
	 * @param tags
	 *            A number of tags (<i>generic names</i>) for the product.
	 */
	public Product addProduct(String specificName, String description, List<Integer> diet, List<Integer> allergen, String unit,
			int content, List<String> tags) {
		return new Product(specificName, description, diet, allergen, unit, content, tags);
	}

	/**
	 * Adds a comment to a recipe if the user is logged in.
	 * 
	 * @param recipe
	 *            The recipe the comment is to be added to.
	 * @param author
	 *            The author of the comment.
	 * @param commentText
	 *            The comment text.
	 * @return A string telling the user if the comment was successfully added or not.
	 */
	public String addComment(Recipe recipe, User author, String commentText) {
		boolean addingOK = recipe.addComment(author, commentText);
		if (addingOK) {
			return "Kommentar wurde hinzugefuegt.";
		} else {
			return "Kommentar konnte nicht hinzugefuegt werden!";
		}
	}

	/**
	 * Edits a comment if the editor either is the original author of the comment, or an admin.
	 * 
	 * @param comment
	 *            The comment that is to be edited.
	 * @param editor
	 *            The editor of the comment.
	 * @param newCommentText
	 *            The comment text.
	 * @return A string telling the user if the edit was successfully added or not.
	 */
	public String editComment(Model.Comment comment, User editor, String newCommentText) {
		boolean editOK = comment.editComment(editor, newCommentText);
		if (editOK) {
			return "Kommentar wurde editiert.";
		} else {
			return "Kommentar konnte nicht editiert werden!";
		}
	}

	/**
	 * Adds a new tag to a product.
	 * 
	 * @param user
	 *            The user who wants to add a new tag to a product. Only an admin can add a new tag.
	 * @param product
	 *            The product to which a new tag is to be added.
	 * @param newTag
	 *            The new tag for the product.
	 * @return A string telling the user if the adding was successfully added or not.
	 */
	public String addTagToProduct(User user, Product product, String newTag) {
		boolean addOK = product.addTag(user, newTag);
		if (addOK) {
			return "Tag erfolgreich hinzugefuegt.";
		} else {
			return "Tag konnte nicht hinzugefuegt werden!";
		}
	}

	/**
	 * Removes a tag from the product's tag list.
	 * 
	 * @param user
	 *            The user who wants to remove a tag from a product. Only an admin can add a new tag.
	 * @param product
	 *            The product to which a new tag is to be removed.
	 * @param tagToRemove
	 *            The tag that is to be removed from the product.
	 * @return A string telling the user if the removing was successfully added or not.
	 */
	public String removeTagFromProduct(User user, Product product, String tagToRemove) {
		boolean removeOK = product.removeTag(user, tagToRemove);
		if (removeOK) {
			return "Tag erfolgreich entfernt.";
		} else {
			return "Tag konnte nicht entfernt werden!";
		}
	}
}
