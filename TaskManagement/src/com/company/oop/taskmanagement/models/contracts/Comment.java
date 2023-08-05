package com.company.oop.taskmanagement.models.contracts;

import java.lang.reflect.Member;

public interface Comment {

    Member getAuthor();

    String getContent();

}
