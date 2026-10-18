
package scholorSoftware;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JUnitTestCourseTest {
	// Creates an assessment with valid default details.
    // Each test changes only the parameter being tested.
    private boolean addAssessment(Course course, String name,
            String description, int points,
            String submissionType, boolean group,
            String groupingName, String publish,
            String due, String hide) {

        return course.createNewAssessment(
                name, description, points,
                submissionType, group, groupingName,
                publish, due, hide);
    }

    // Checks that all attributes were stored correctly.
    private void checkAssessment(Assessment a,
            String name, String description, int points,
            String submissionType, boolean group,
            String groupingName, String publish,
            String due, String hide) {

        assertEquals(name, a.getName());
        assertEquals(description, a.getDescription());
        assertEquals(points, a.getPoints());
        assertEquals(submissionType, a.getSubmissionType());
        assertEquals(group, a.isGroup());
        assertEquals(groupingName, a.getGroupingName());
        assertEquals(publish, a.getPublishDateTime());
        assertEquals(due, a.getDueDateTime());
        assertEquals(hide, a.getHideDateTime());
    }
    
    //Bhupendra Bhat- Conditions 1, 2, 3
    // Condition 1: Duplicate name must be rejected.
    @Test
    public void testCondition1() {
        Course course = new Course();

        assertTrue(addAssessment(course,
                "assignment one", "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59"));

        // Precondition: one assessment already exists.
        assertEquals(1, course.getAssessments().size());
        Assessment original = course.getAssessments().get(0);

        boolean result = addAssessment(course,
                "assignment one", "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        assertFalse(result);
        assertEquals(1, course.getAssessments().size());
        assertSame(original, course.getAssessments().get(0));
        assertEquals("assignment one", original.getName());
    }

    // Condition 2: Blank name must be rejected.
    @Test
    public void testCondition2() {
        Course course = new Course();

        assertTrue(course.getAssessments().isEmpty());

        boolean result = addAssessment(course,
                "", "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        assertFalse(result);
        assertEquals(0, course.getAssessments().size());
    }

    // Condition 3: Description needs at least 3 words.
    @Test
    public void testCondition3() {
        Course course = new Course();

        assertTrue(course.getAssessments().isEmpty());

        boolean result = addAssessment(course,
                "assignment one", "final assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        assertFalse(result);
        assertEquals(0, course.getAssessments().size());
    }
    
    
    //Pooja Anil Bachche - Conditions 4, 5, 6
    // Condition 4: Points must be greater than zero.
    @Test
    public void testCondition4() {
        Course course = new Course();

        assertTrue(course.getAssessments().isEmpty());

        boolean result = addAssessment(course,
                "assignment one", "complete programming assignment",
                0, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        assertFalse(result);
        assertEquals(0, course.getAssessments().size());
    }

    // Condition 5: Maximum of 50 points per assessment.
    @Test
    public void testCondition5() {
        Course course = new Course();

        assertTrue(course.getAssessments().isEmpty());

        boolean result = addAssessment(course,
                "assignment one", "complete programming assignment",
                51, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        assertFalse(result);
        assertEquals(0, course.getAssessments().size());
    }

    // Condition 6: Total points cannot exceed 100.
    @Test
    public void testCondition6() {
        Course course = new Course();

        assertTrue(addAssessment(course,
                "assignment one", "complete programming assignment",
                50, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59"));

        assertTrue(addAssessment(course,
                "assignment two", "complete database assignment",
                50, "doc", false, "",
                "01/12/2026 09:00",
                "10/12/2026 23:59",
                "11/12/2026 23:59"));

        // Precondition: total already equals 100.
        assertEquals(2, course.getAssessments().size());
        assertEquals(100,
                course.getAssessments().get(0).getPoints()
                + course.getAssessments().get(1).getPoints());

        boolean result = addAssessment(course,
                "assignment three", "complete final assignment",
                1, "zip", false, "",
                "01/01/2027 09:00",
                "10/01/2027 23:59",
                "11/01/2027 23:59");

        assertFalse(result);
        assertEquals(2, course.getAssessments().size());
    }
    
    
    //Vishnuvardhan Kadem - Conditions 7, 8, 9
    // Condition 7: Submission type must be pdf, zip or doc.
    @Test
    public void testCondition7() {
        Course course = new Course();

        assertTrue(course.getAssessments().isEmpty());

        boolean result = addAssessment(course,
                "assignment one", "complete programming assignment",
                20, "txt", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        assertFalse(result);
        assertEquals(0, course.getAssessments().size());
    }

    // Condition 8: Group assessment needs a grouping name.
    @Test
    public void testCondition8() {
        Course course = new Course();

        assertTrue(course.getAssessments().isEmpty());

        boolean result = addAssessment(course,
                "group assignment", "complete group assignment",
                20, "pdf", true, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        assertFalse(result);
        assertEquals(0, course.getAssessments().size());
    }

    // Condition 9: Grouping names must be unique.
    @Test
    public void testCondition9() {
        Course course = new Course();

        assertTrue(addAssessment(course,
                "group assignment one", "complete group assignment",
                20, "pdf", true, "team a",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59"));

        assertEquals(1, course.getAssessments().size());
        Assessment original = course.getAssessments().get(0);

        boolean result = addAssessment(course,
                "group assignment two", "complete another assignment",
                20, "zip", true, "team a",
                "01/12/2026 09:00",
                "10/12/2026 23:59",
                "11/12/2026 23:59");

        assertFalse(result);
        assertEquals(1, course.getAssessments().size());
        assertSame(original, course.getAssessments().get(0));
        assertEquals("team a", original.getGroupingName());
    }
    
    //Kevinkumar Jaysukhbhai Bed - Conditions 10, 11, and All Valid
    // Condition 10: Strict date validation, two test data.
    @Test
    public void testCondition10() {
        Course course = new Course();

        assertTrue(course.getAssessments().isEmpty());

        // Test data 1: 2026 is not a leap year.
        boolean result1 = addAssessment(course,
                "assignment one", "complete programming assignment",
                20, "pdf", false, "",
                "29/02/2026 20:00",
                "10/03/2026 23:59",
                "11/03/2026 23:59");

        assertFalse(result1);
        assertEquals(0, course.getAssessments().size());

        // Test data 2: April does not have 31 days.
        boolean result2 = addAssessment(course,
                "assignment two", "complete programming assignment",
                20, "pdf", false, "",
                "01/04/2026 09:00",
                "31/04/2026 20:00",
                "02/05/2026 23:59");

        assertFalse(result2);
        assertEquals(0, course.getAssessments().size());
    }

    // Condition 11: publish < due < hide, two test data.
    @Test
    public void testCondition11() {
        Course course = new Course();

        assertTrue(course.getAssessments().isEmpty());

        // Test data 1: Due date equals publish date.
        boolean result1 = addAssessment(course,
                "assignment one", "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "01/11/2026 09:00",
                "11/11/2026 23:59");

        assertFalse(result1);
        assertEquals(0, course.getAssessments().size());

        // Test data 2: Hide date equals due date.
        boolean result2 = addAssessment(course,
                "assignment two", "complete database assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "10/11/2026 23:59");

        assertFalse(result2);
        assertEquals(0, course.getAssessments().size());
    }

    // Fully valid case: three different combinations.
    @Test
    public void testAllValid() {
        Course course = new Course();

        // Precondition: course starts empty.
        assertTrue(course.getAssessments().isEmpty());

        // Valid 1: Individual PDF assessment.
        boolean result1 = addAssessment(course,
                "assignment one", "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        assertTrue(result1);
        assertEquals(1, course.getAssessments().size());

        checkAssessment(course.getAssessments().get(0),
                "assignment one", "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        // Valid 2: Group ZIP assessment.
        boolean result2 = addAssessment(course,
                "group project", "complete group project",
                30, "zip", true, "project groups",
                "01/12/2026 09:00",
                "10/12/2026 23:59",
                "11/12/2026 23:59");

        assertTrue(result2);
        assertEquals(2, course.getAssessments().size());

        checkAssessment(course.getAssessments().get(1),
                "group project", "complete group project",
                30, "zip", true, "project groups",
                "01/12/2026 09:00",
                "10/12/2026 23:59",
                "11/12/2026 23:59");

        // Valid 3: Individual DOC assessment.
        // Total points = 20 + 30 + 50 = 100.
        boolean result3 = addAssessment(course,
                "final report", "submit final report",
                50, "doc", false, "",
                "01/01/2027 09:00",
                "10/01/2027 23:59",
                "11/01/2027 23:59");

        assertTrue(result3);
        assertEquals(3, course.getAssessments().size());

        checkAssessment(course.getAssessments().get(2),
                "final report", "submit final report",
                50, "doc", false, "",
                "01/01/2027 09:00",
                "10/01/2027 23:59",
                "11/01/2027 23:59");
    }
}
