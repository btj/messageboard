package messageboard;

import logicalcollections.LogicalList;

/**
 * @invar | getParent() != null
 * @invar | isDeleted() || getParent().getReplies().contains(this)
 */
public class Reply extends Message {
	
	/**
	 * @invar | parent != null
	 * @invar | true // Inserted to ensure the following invariant is a Phase 3 invariant. Its validity depends on the Phase 2 invariant 'replies != null' of class Message.
	 * @invar | isDeleted || parent.replies.contains(this)
	 * 
	 * @peerObject
	 */
	final Message parent;
	
	/**
	 * @peerObject
	 */
	public Message getParent() { return parent; }

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
		parent.replies.add(this);
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
		parent.replies.remove(this);
	}
	
	@Override
	public boolean isIsomorphicTo(Message other) {
		return super.isIsomorphicTo(other) && other instanceof Reply && parent.isIsomorphicTo(((Reply)other).parent);
	}

}
