package messageboard;

public class OM extends Message {
	
	/**
	 * @throws IllegalArgumentException | author == null
	 * 
	 * @post | getAuthor() == author
	 * @post | getReplies().isEmpty()
	 * @post | !isDeleted()
	 */
	public OM(String author) {
		super(author);
	}
	
	// Method 'delete' is overridden here only to refine the effect specification
	// from '@mutates | this' to '@mutates_properties | isDeleted()'
	/**
	 * @pre | !isDeleted()
	 * @mutates_properties | isDeleted()
	 * @post | isDeleted()
	 */
	@Override
	public void delete() {
		super.delete();
	}
	
	@Override
	public boolean isIsomorphicTo(Message other) {
		return super.isIsomorphicTo(other) && other instanceof OM;
	}

}
