package com.company.oop.taskmanagement.models;

import com.company.oop.taskmanagement.models.contracts.Comment;

import com.company.oop.taskmanagement.models.contracts.Member;

public class CommentImpl implements Comment {

    private Member author;

    private String content;

    public CommentImpl(String content, Member author) {
        setContent(content);
        setAuthor(author);

    }

    private void setContent(String content) {
        this.content = content;
    }

    private void setAuthor(Member author) {
        this.author = author;
    }

    @Override
    public Member getAuthor() {
        return this.author;
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentImpl comment)) return false;

        if (!author.equals(comment.author)) return false;
        return content.equals(comment.content);
    }
}
