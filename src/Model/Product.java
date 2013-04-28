package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 
 * @author Daniel MOOSBRUGGER
 * 
 */
public class Product {

	/**
	 * A randomly generated, unique ID for the product.<br>
	 * 
	 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/util/UUID.html">http://docs.oracle.com/javase/7/docs/api/java/util/UUID.html</a>
	 */
	private UUID productID;
	/**
	 * The specific name of the product.
	 */
	private String specificName;
	/**
	 * A short description of the product.
	 */
	private String description;
	/**
	 * A list of all the diets that are supported by the product.
	 */
	private List<Integer> diet;
	/**
	 * A list of all allergens contained in the product.
	 */
	private List<Integer> allergen;
	/**
	 * The measuring unit of the product.
	 */
	private String unit;
	/**
	 * The amount of content of the product, e.g. "ml", "g", "Stueck".
	 */
	private int content;
	/**
	 * A number of tags (<i>generic names</i>) for the product.
	 */
	private List<String> tags;

	/**
	 * Constructor for a new Product.<br>
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
	public Product(String specificName, String description, List<Integer> diet, List<Integer> allergen, String unit, int content,
			List<String> tags) {
		this.specificName = specificName;
		this.description = description;
		this.diet = diet;
		this.allergen = allergen;
		this.unit = unit;
		this.content = content;
		this.tags = tags;
		this.productID = UUID.randomUUID();
	}

	/**
	 * Adds a new tag to the product's taglist.
	 * 
	 * @param user
	 *            The user that wants to add a tag. The user has to be an admin.
	 * @param newTag
	 *            The new tag.
	 * @return <b>true</b> if the tag was newly added to the list, <b>false</b> if it already was in the list or the user was not an admin.
	 */
	public boolean addTag(User user, String newTag) {
		try {
			if (this.tags == null) {
				this.tags = new ArrayList<String>();
			}
			if (this.tags.contains(newTag))
				return false;
			else if (user.isAdmin() == true) {
				this.tags.add(newTag);
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Removes a new tag from the product's taglist.
	 * 
	 * @param tagToDelete
	 *            The tag that is to be deleted.
	 * @return <b>true</b> if the tag was removed to the list, <b>false</b> if it was not in the list or the user was not an admin.
	 */
	public boolean removeTag(User user, String tagToDelete) {
		try {
			if (this.tags == null) {
				this.tags = new ArrayList<String>();
				return false;
			}
			if (this.tags.contains(tagToDelete) && user.isAdmin() == true) {
				this.tags.remove(tagToDelete);
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			return false;
		}
	}

	public void saveProduct() {
		// TODO
	}

	public UUID getProductID() {
		return productID;
	}

	public String getSpecificName() {
		return specificName;
	}

	public String getDescription() {
		return description;
	}

	public List<Integer> getDiet() {
		return diet;
	}

	public List<Integer> getAllergen() {
		return allergen;
	}

	public String getUnit() {
		return unit;
	}

	public int getContent() {
		return content;
	}

	public List<String> getTags() {
		return tags;
	}

}
