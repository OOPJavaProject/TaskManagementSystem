package models.contracts;

import java.lang.reflect.Member;

public interface Comment {

    Member getAuthor();

    String getContent();

}
