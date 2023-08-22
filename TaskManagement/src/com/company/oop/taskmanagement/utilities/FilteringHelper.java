package com.company.oop.taskmanagement.utilities;

import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.TaskStatus.BugStatus;
import com.company.oop.taskmanagement.models.enums.TaskStatus.FeedbackStatus;
import com.company.oop.taskmanagement.models.enums.TaskStatus.StoryStatus;
import com.company.oop.taskmanagement.models.tasks.contracts.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilteringHelper {

    public static final String NO_TASKS_SORT = "There are no tasks to sort";
    public static final String NO_TASKS_FILTER = "There are no tasks to filter.";

    public static String filterTasksByTitle(List<Task> tasks, String title) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException(FilteringHelper.NO_TASKS_FILTER);
        }
        return tasks.stream().filter((task) -> task.getTitle().contains(title)).toList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }

    public static String filterTasksByStatus(List<Task> tasks, Status status) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException(FilteringHelper.NO_TASKS_FILTER);
        }
        return tasks.stream().filter((task) -> task.getStatus().equals(status)).toList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }

    public static String filterTasksByAssignee(List<PrioritizableTask> tasks, String memberName) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException(FilteringHelper.NO_TASKS_FILTER);
        }
        return tasks.stream().filter((task) -> task.getAssignee().getName().equals(memberName)).toList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }

//    public static <T extends Task> String filterTasksByTitle(List<T> list, String argument)


    public static <T extends Task> String sortTasksByStatus(List<T> elements) {
        StringBuilder sb = new StringBuilder();
        List<Task> bugList = new ArrayList<>();
        List<Task> storyList = new ArrayList<>();
        List<Task> feedbackList = new ArrayList<>();

        for (T task : elements) {
            if (task.getStatus().getClass() == BugStatus.class) {
                orderedInsertInList(bugList, task, BugStatus.class);
            } else if (task.getStatus().getClass() == StoryStatus.class) {
                orderedInsertInList(storyList, task, StoryStatus.class);
            } else if (task.getStatus().getClass() == FeedbackStatus.class) {
                orderedInsertInList(feedbackList, task, FeedbackStatus.class);
            }
        }
        insertListInStringBuilder(bugList, sb);
        insertListInStringBuilder(storyList, sb);
        insertListInStringBuilder(feedbackList, sb);

        return sb.toString();
    }

    private static void insertListInStringBuilder(List<Task> list, StringBuilder output) {
        for (Task task : list) {
            output.append(task.toString());
        }
    }

    /**
     * Inputs a task in a list based on the enum of the status.
     * The order is based on the ordinal value of the enum class.
     *
     * @param list
     * @param element
     * @param type
     * @param <E>
     */
    private static <E extends Enum<E>> void orderedInsertInList(List<Task> list, Task element, Class<E> type) {
        if (list.isEmpty()) {
            list.add(element);
            return;
        }

        E status;
        E statusToOrder = ParsingHelpers.tryParseEnum(element.getStatus().toString(), type);
        for (Task task : list) {
            status = ParsingHelpers.tryParseEnum(task.getStatus().toString(), type);

            if (status.ordinal() < statusToOrder.ordinal()) {
                list.add(element);
                return;
            }
        }
    }


    public static <T extends Task> String sortByTitle(List<T> tasks) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException(NO_TASKS_SORT);
        }
        Comparator<Task> titleComparator = Comparator.comparing(Task::getTitle);
        return tasks.stream().sorted(titleComparator).toList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }

    public static <T extends PrioritizableTask> String sortByPriority(List<T> tasks) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException(NO_TASKS_SORT);
        }
        Comparator<PrioritizableTask> priorityComparator = Comparator.comparing(PrioritizableTask::getPriority);
        return tasks.stream().sorted(priorityComparator).toList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }

    public static <T extends Bug> String sortBySeverity(List<T> tasks) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException(NO_TASKS_SORT);
        }
        Comparator<Bug> severityComparator = Comparator.comparing(Bug::getSeverity);
        return tasks.stream().sorted(severityComparator).toList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }

    public static <T extends Story> String sortBySize(List<T> tasks) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException(NO_TASKS_SORT);
        }
        Comparator<Story> sizeComparator = Comparator.comparing(Story::getSize);
        return tasks.stream().sorted(sizeComparator).toList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }

    public static <T extends Feedback> String sortByRating(List<T> tasks) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException(NO_TASKS_SORT);
        }
        Comparator<Feedback> ratingComparator = Comparator.comparing(Feedback::getRating);
        return tasks.stream().sorted(ratingComparator).toList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }


//    public static <T extends Task> String tasksFilteredByTitle(List<T> elements) {
//        List<String> result = new ArrayList<>();
//        return elements.stream().filter(element -> element.getTitle());
//        for (T element : elements) {
//            result.add(element.toString());
//        }
//
//        return String.join(CommandsConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
//    }

}
