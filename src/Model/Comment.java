package Model;

import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * 
 * @author Daniel MOOSBRUGGER
 * 
 */
public class Comment {

	/**
	 * A randomly generated, unique ID for the comment.<br>
	 * 
	 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/util/UUID.html">http://docs.oracle.com/javase/7/docs/api/java/util/UUID.html</a>
	 */
	private UUID commentID;
	/**
	 * The author of the comment.
	 */
	private User author;
	/**
	 * The date the comment was created or, if there was an edit, the date of the last edit.
	 */
	private int date;
	/**
	 * The text of the comment.
	 */
	private String commentText;
	/**
	 * The recipe the comment was made on.
	 */
	private Recipe recipe;
	/**
	 * Indicates if the comment was edited or not.
	 */
	boolean edited;
	/**
	 * Indicates if the comment was reported by an user.
	 */
	boolean flagged;

	/**
	 * The constructor for a comment.<br>
	 * The <b>date</b> is automatically calculated, an UUID is created, and <b>edited</b> is set on <b>false</b>.
	 * 
	 * @param author
	 *            The author of the comment.
	 * @param commentText
	 *            The text of the comment.
	 * @param recipe
	 *            The recipe the comment was made on.
	 */
	protected Comment(User author, String commentText, Recipe recipe) {
		this.author = author;
		this.date = GregorianCalendar.YEAR * 10000 + GregorianCalendar.MONTH * 100 + GregorianCalendar.DAY_OF_MONTH;
		this.commentText = commentText;
		this.recipe = recipe;
		this.edited = false;
		this.flagged = false;
		this.commentID = UUID.randomUUID();
	}

	/**
	 * Allows the original author of the comment and admins to edit a comment.<br>
	 * After an edit, the comment's date will be updated to the date of the edit.
	 * 
	 * @param editor
	 *            The user that wants to edit the comment.
	 * @param newCommentText
	 *            The new text of the comment.
	 * @return <b>true</b> if the editing of the comment was successful, otherwise <b>false</b>.
	 */
	public boolean editComment(User editor, String newCommentText) {
		// TODO: an equals method for the User class (to check if the editor is the author) needed
		try {
			if (editor.equals(this.author) || editor.isAdmin()) {
				this.commentText = newCommentText;
				this.date = GregorianCalendar.YEAR * 10000 + GregorianCalendar.MONTH * 100 + GregorianCalendar.DAY_OF_MONTH;
				this.edited = true;
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	public User getAuthor() {
		return author;
	}

	public int getDate() {
		return date;
	}

	public String getCommentText() {
		return commentText;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public boolean isEdited() {
		return edited;
	}

	public boolean isFlagged() {
		return flagged;
	}

}
