package scholorSoftware;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private List<Assessment> assessments;

    
    public Course() {
        assessments = new ArrayList<>();
    }

    // Allow tests to check the stored assessments.
    public List<Assessment> getAssessments() {
        return assessments;
    }

    public boolean createNewAssessment(
            String name, 
            String description, 
            int points,
            String submissionType, 
            boolean group, 
            String groupingName,
            String publishDateTime, 
            String dueDateTime,
            String hideDateTime) {

        boolean valid = true;

        // Condition 1:
        // The assessment name must be unique to the course - the assessment list cannot have assessments with the same name.
        for (Assessment assessment : assessments) {
            if (assessment.getName().equals(name)) {
                valid = false;
            }
        }

        // Condition 2:
        // The assessment name cannot be blank.
        if (name == null || name.trim().isEmpty()) {
            valid = false;
        }

        // Condition 3:
        // The assessment description must have at least 3 words.
        if (description == null ||
                description.trim().isEmpty()) {
            valid = false;
        } else {
            String[] words = description.trim().split("\\s+");

            if (words.length < 3) {
                valid = false;
            }
        }

        // Only create and store a valid assessment.
        if (valid) {

            Assessment newAssessment = new Assessment(
                    name, description, points,
                    submissionType, group, groupingName,
                    publishDateTime, dueDateTime,
                    hideDateTime);

            assessments.add(newAssessment);

            return true;
        }

        // Invalid input must not change the list.
        return false;
    }
}