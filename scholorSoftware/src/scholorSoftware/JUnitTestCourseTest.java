
package scholorSoftware;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JUnitTestCourseTest {

    // TC01: Verify duplicate assessment names are rejected.
    @Test
    public void testCondition1DuplicateName() {

        Course course = new Course();

        // Create the first valid assessment.
        boolean firstResult = course.createNewAssessment(
                "assignment one",
                "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        // Precondition: the original assessment must exist.
        assertTrue(firstResult);
        assertEquals(1, course.getAssessments().size());

        Assessment original = course.getAssessments().get(0);

        assertEquals("assignment one", original.getName());
        assertEquals("complete programming assignment",
                original.getDescription());
        assertEquals(20, original.getPoints());

        // Attempt to create an assessment with the same name.
        boolean result = course.createNewAssessment(
                "assignment one",
                "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        // Expected: duplicate name is rejected.
        assertFalse(result);

        // No new assessment should be added.
        assertEquals(1, course.getAssessments().size());

        // The original object and attributes stay unchanged.
        assertSame(original, course.getAssessments().get(0));
        assertEquals("assignment one", original.getName());
        assertEquals("complete programming assignment",
                original.getDescription());
        assertEquals(20, original.getPoints());
    }

    // TC02: Verify a blank assessment name is rejected.
    @Test
    public void testCondition2BlankName() {

        Course course = new Course();

        // Precondition: the assessment list is empty.
        assertTrue(course.getAssessments().isEmpty());

        // Test the empty-string boundary.
        boolean result = course.createNewAssessment(
                "",
                "complete programming assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        // Expected: blank name is rejected.
        assertFalse(result);

        // No Assessment object should be stored.
        assertEquals(0, course.getAssessments().size());
    }

    // TC03: Verify descriptions with fewer than 3 words fail.
    @Test
    public void testCondition3DescriptionTooShort() {

        Course course = new Course();

        // Precondition: the assessment list is empty.
        assertTrue(course.getAssessments().isEmpty());

        // Description contains only 2 words.
        boolean result = course.createNewAssessment(
                "assignment one",
                "final assignment",
                20, "pdf", false, "",
                "01/11/2026 09:00",
                "10/11/2026 23:59",
                "11/11/2026 23:59");

        // Expected: 2-word description is invalid.
        assertFalse(result);

        // No Assessment object should be stored.
        assertEquals(0, course.getAssessments().size());
    }
}
