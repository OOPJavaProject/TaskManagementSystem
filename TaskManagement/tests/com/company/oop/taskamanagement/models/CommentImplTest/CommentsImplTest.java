package com.company.oop.taskamanagement.models.CommentImplTest;

import com.company.oop.taskamanagement.models.member.MemberImplTest;
import com.company.oop.taskmanagement.models.CommentImpl;
import com.company.oop.taskmanagement.models.contracts.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentsImplTest {

    public static final String COMMENT_CONTENT = "Valid Content";
    @Test
    public void should_CreateComment_When_ValuesAreValid(){
        Comment comment = initializeComment();

        assertAll(
                ()-> assertEquals(COMMENT_CONTENT, comment.getContent()),
                ()-> assertEquals(MemberImplTest.initializeMember(), comment.getAuthor())
        );
    }
    public static Comment initializeComment(){
        return new CommentImpl(
                COMMENT_CONTENT,
                MemberImplTest.initializeMember()
        );
    }
}
