import java.awt.image.BufferedImage;
import java.util.List;

public class Recipe {

	private User author;
	private String name;
	private String shortDescription;
	private String howToCook;
	private int timeToCook;
	private int portions;
	private List<Product> ingredients;
	private Rating rating;
	private List<Comment> comments;
	private List<Integer> tags;
	private BufferedImage picture;

	public Recipe() {

	}

}
