package messageboard.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import messageboard.OM;
import messageboard.Reply;

class MessageBoardTest {

	@Test
	void test() {
		OM om = new OM("Author1");
		assertEquals("Author1", om.getAuthor());
		assertTrue(om.getReplies().isEmpty());
		assertFalse(om.isDeleted());
		
		Reply reply1 = new Reply("Author2", om);
		assertEquals("Author2", reply1.getAuthor());
		assertTrue(reply1.getReplies().isEmpty());
		assertFalse(reply1.isDeleted());
		assertEquals(List.of(reply1), om.getReplies());
		
		Reply reply2 = new Reply("Author2", om);
		assertEquals(List.of(reply1, reply2), om.getReplies());
		
		OM om2 = new OM("Author1");
		assertTrue(om2.isIsomorphicTo(om));
		assertTrue(reply1.isIsomorphicTo(reply2));
		assertFalse(reply1.isIsomorphicTo(om));
		
		reply1.delete();
		assertTrue(reply1.isDeleted());
		assertEquals(List.of(reply2), om.getReplies());
		assertFalse(reply1.isIsomorphicTo(reply2));
		
		om.delete();
		assertTrue(om.isDeleted());
	}

}
