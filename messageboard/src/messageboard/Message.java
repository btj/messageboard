package messageboard;

import java.util.ArrayList;
import java.util.List;

import logicalcollections.LogicalList;

/**
 * @invar | getAuthor() != null
 * @invar | getReplies() != null
 * @invar | getReplies().stream().allMatch(reply -> reply != null && !reply.isDeleted() && reply.getParent() == this)
 */
public abstract class Message {
	
	/**
	 * @invar | author != null
	 * @invar | replies != null
	 * @invar | replies.stream().allMatch(reply -> reply != null)
	 * @invar | LogicalList.distinct(replies)
	 */
	private final String author;
	private final List<Reply> replies = new ArrayList<>();
	private boolean isDeleted;
	
	/**
	 * @invar | getRepliesInternal().stream().allMatch(reply -> !reply.isDeletedInternal() && reply.getParentInternal() == this)
	 * 
	 * @immutable
	 * @post | result != null
	 */
	String getAuthorInternal() { return author; }
	/**
	 * @creates | result
	 * @post | result != null
	 * @post | result.stream().allMatch(reply -> reply != null)
	 * @post | LogicalList.distinct(result)
	 * @peerObjects
	 */
	List<Reply> getRepliesInternal() { return List.copyOf(replies); }
	boolean isDeletedInternal() { return isDeleted; }
	
	public String getAuthor() { return getAuthorInternal(); }
	/**
	 * @creates | result
	 * @peerObjects (package-level)
	 */
	public List<Reply> getReplies() { return getRepliesInternal(); }
	public boolean isDeleted() { return isDeletedInternal(); }
	
	/**
	 * @throws IllegalArgumentException | author == null
	 * @post | getAuthorInternal() == author
	 * @post | getRepliesInternal().isEmpty()
	 * @post | !isDeletedInternal()
	 */
	Message(String author) {
		if (author == null)
			throw new IllegalArgumentException("`author` is null");
		
		this.author = author;
	}
	
	/**
	 * @pre | reply != null
	 * @pre | !getRepliesInternal().contains(reply)
	 * @mutates_properties | getRepliesInternal()
	 * @post | getRepliesInternal().equals(LogicalList.plus(old(getRepliesInternal()), reply))
	 */
	void addReply(Reply reply) {
		replies.add(reply);
	}

	/**
	 * @pre | reply != null
	 * @mutates_properties | getRepliesInternal()
	 * @post | getRepliesInternal().equals(LogicalList.minus(old(getRepliesInternal()), reply))
	 */
	void removeReply(Reply reply) {
		replies.remove(reply);
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
