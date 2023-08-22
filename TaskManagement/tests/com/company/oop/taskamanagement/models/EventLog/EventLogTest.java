package com.company.oop.taskamanagement.models.EventLog;

import com.company.oop.taskmanagement.models.EventLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventLogTest {

    public static final String LOG_DESCRIPTION = "description";

    @Test
    public void should_CreateEventLog_WhenValidValuesArePassed(){
        EventLog log = new EventLog(LOG_DESCRIPTION);
        assertAll(
                ()-> assertEquals(LOG_DESCRIPTION, log.getDescription())
        );
    }


}
