package messageboard;

import java.util.ArrayList;
import java.util.List;

import logicalcollections.LogicalList;

/**
 * @invar | getAuthor() != null
 * @invar | getReplies() != null
 * @invar | getReplies().stream().allMatch(reply -> reply != null && !reply.isDeleted() && reply.getParent() == this)
 * @invar | LogicalList.distinct(getReplies())
 */
public abstract class Message {
	
	/**
	 * @invar | author != null
	 * @invar | replies != null
	 * @invar | replies.stream().allMatch(reply -> reply != null && !reply.isDeleted && reply.parent == this)
	 * @invar | LogicalList.distinct(replies)
	 */
	final String author;
	/**
	 * @representationObject
	 * @peerObjects
	 */
	final List<Reply> replies = new ArrayList<>();
	boolean isDeleted;
	
	/**
	 * @immutable
	 */
	public String getAuthor() { return author; }
	/**
	 * @creates | result
	 * @peerObjects
	 */
	public List<Reply> getReplies() { return List.copyOf(replies); }
	public boolean isDeleted() { return isDeleted; }
	
	Message(String author) {
		if (author == null)
			throw new IllegalArgumentException("`author` is null");
		
		this.author = author;
	}
	
	/**
	 * @pre | !isDeleted()
	 * @mutates | this
	 * @post | isDeleted()
	 */
	public void delete() {
		isDeleted = true;
	}
	
	public boolean isIsomorphicTo(Message other) {
		return author.equals(other.author) && isDeleted == other.isDeleted;
	}

}
