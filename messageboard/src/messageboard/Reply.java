package messageboard;

import logicalcollections.LogicalList;

/**
 * @invar | getParent() != null
 * @invar | isDeleted() || getParent().getReplies().contains(this)
 */
public class Reply extends Message {
	
	/**
	 * @invar | parent != null
	 */
	final Message parent;
	
	/**
	 * @invar | isDeletedInternal() || getParentInternal().getRepliesInternal().contains(this)
	 *
	 * @post | result != null
	 * @peerObject (package-level)
	 */
	Message getParentInternal() { return parent; }
	
	/**
	 * @peerObject
	 */
	public Message getParent() { return getParentInternal(); }

	/**
	 * @throws IllegalArgumentException | author == null
	 * @throws IllegalArgumentException | parent == null
	 * @mutates_properties | parent.getReplies()
	 * @post | getAuthor() == author
	 * @post | getReplies().isEmpty()
	 * @post | !isDeleted()
	 * @post | parent.getReplies().equals(LogicalList.plus(old(parent.getReplies()), this))
	 */
	public Reply(String author, Message parent) {
		super(author);
		this.parent = parent;
		parent.addReply(this);
	}
	
	/**
	 * @pre | !isDeleted()
	 * @mutates_properties | isDeleted(), getParent().getReplies()
	 * @post | isDeleted()
	 * @post | getParent().getReplies().equals(LogicalList.minus(old(getParent().getReplies()), this))
	 */
	@Override
	public void delete() {
		super.delete();
		parent.removeReply(this);
	}
	
	@Override
	public boolean isIsomorphicTo(Message other) {
		return super.isIsomorphicTo(other) && other instanceof Reply && parent.isIsomorphicTo(((Reply)other).parent);
	}

}
