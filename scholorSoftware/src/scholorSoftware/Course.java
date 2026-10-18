
package scholorSoftware;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Course {

    private List<Assessment> assessments;

    // Create an empty list for the assessments.
    public Course() {
        assessments = new ArrayList<>();
    }

    // Used by JUnit tests to check stored assessments.
    public List<Assessment> getAssessments() {
        return assessments;
    }

    public boolean createNewAssessment(
            String name, String description, int points,
            String submissionType, boolean group, String groupingName,
            String publishDateTime, String dueDateTime,
            String hideDateTime) {

        // Condition 1: Assessment name must be unique.
        for (Assessment assessment : assessments) {
            if (assessment.getName().equals(name)) {
                return false;
            }
        }

        // Condition 2: Assessment name cannot be blank.
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // Condition 3: Description must have at least 3 words.
        if (description == null ||
                description.trim().isEmpty()) {
            return false;
        }

        String[] words = description.trim().split("\\s+");
        if (words.length < 3) {
            return false;
        }

        // Condition 4: Points must be greater than zero.
        if (points <= 0) {
            return false;
        }

        // Condition 5: A single assessment cannot exceed 50 points.
        if (points > 50) {
            return false;
        }

        // Condition 6: Total course assessment points <= 100.
        int totalPoints = points;

        for (Assessment assessment : assessments) {
            totalPoints += assessment.getPoints();
        }

        if (totalPoints > 100) {
            return false;
        }

        // Condition 7: Only pdf, zip or doc are allowed.
        if (!"pdf".equals(submissionType)
                && !"zip".equals(submissionType)
                && !"doc".equals(submissionType)) {
            return false;
        }

        // Condition 8: Group assessments need a grouping name.
        if (group && (groupingName == null ||
                groupingName.trim().isEmpty())) {
            return false;
        }

        // Condition 9: Grouping names must be unique.
        // Only non-blank grouping names are checked.
        if (groupingName != null &&
                !groupingName.trim().isEmpty()) {

            for (Assessment assessment : assessments) {
                if (groupingName.equals(
                        assessment.getGroupingName())) {
                    return false;
                }
            }
        }

        // Condition 10: Strict DD/MM/YYYY HH:mm date validation.
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
                        .withResolverStyle(ResolverStyle.STRICT);

        LocalDateTime publishDate;
        LocalDateTime dueDate;
        LocalDateTime hideDate;

        try {
            publishDate = LocalDateTime.parse(
                    publishDateTime, formatter);

            dueDate = LocalDateTime.parse(
                    dueDateTime, formatter);

            hideDate = LocalDateTime.parse(
                    hideDateTime, formatter);

        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }

        // Condition 11: publish < due < hide.
        if (!dueDate.isAfter(publishDate)
                || !hideDate.isAfter(dueDate)) {
            return false;
        }

        // All 11 conditions have passed.
        // Create a new Assessment using its constructor.
        Assessment newAssessment = new Assessment(
                name, description, points,
                submissionType, group, groupingName,
                publishDateTime, dueDateTime, hideDateTime);

        // Store the new Assessment in the list.
        assessments.add(newAssessment);

        return true;
    }
}
