package edu.worcester.cs.kwurst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {
	
	private Student student1;
	private Course course1;
	private Course course2;
	private Grade grade1;
	private Grade grade2;
	private Transcript.Semester fall;
	private Transcript transcript1;

	@Before
	public void setUp() throws Exception {
		student1 = new Student("Jane", "Smith");
		course1 = new Course("CS", 101, "cs101", 4);
		course2 = new Course("Math", 102, "MA102", 120);
		grade1 = new Grade("A");
		grade2 = new Grade("IP");
		transcript1 = new Transcript();
		transcript1.addCourse(course1,fall,1, grade1);
	}

	@Test
	@SuppressWarnings("unused")
	public void testGetStudentCount() {
		int current = Student.getStudentCount();
		Student student2 = new Student("Bob", "Clark");
		assertEquals(current+1, Student.getStudentCount());
	}

	@Test
	public void testSetFirstName() {
		student1.setFirstName("David");
		assertEquals("David", student1.getFirstName());
	}

	@Test
	public void testGetFirstName() {
		assertEquals("Jane", student1.getFirstName());
	}

	@Test
	public void testSetLastName() {
		student1.setLastName("Passmore");
		assertEquals("Passmore", student1.getLastName());
	}

	@Test
	public void testGetLastName() {
		assertEquals("Smith", student1.getLastName());
	}

	@Test
	public void testGetId() {
        String currentId = student1.getId();
        Student student2 = new Student("Bob", "Clark");
        int x = Integer.parseInt(currentId);
        x = x + 1;                
        currentId = String.format("%07d",x);
        assertEquals(currentId, student2.getId());
	}

	@Test
	public void testGetGpa() {
		student1.addCourse(course1, fall, 1, grade1);
		assertEquals(4.0, student1.getGpa(), 0);
	}

	@Test
	public void testGetCurrentEarnedCr() {
		student1.addCourse(course1, fall, 1, grade1);
		assertEquals(4, student1.getCurrentEarnedCr());
	}

	@Test
	public void testGetAnticipatedAdditionalCr() {
		student1.addCourse(course1, fall, 1, grade1);
		assertEquals(0, transcript1.getAnticipatedAdditionalCr());
	}

	@Test
	public void testSetLascComplete() {
		transcript1.setLascComplete(true);
		assertEquals(true, transcript1.getLascComplete());
	}

	@Test
	public void testSetMajorComplete() {
		transcript1.setMajorComplete(true);
		assertEquals(true, transcript1.getMajorComplete());
	}

	@Test
	public void testGetCurrentRemainingCr() {
		student1.addCourse(course1, fall, 1, grade1);
		assertEquals(116, student1.getCurrentRemainingCr());
	}

	@Test
	public void testReadyToGraduate1() {	// Remaining credits > 0
		student1.addCourse(course1, fall, 1, grade1);
		student1.setLascComplete(true);
		student1.setMajorComplete(true);
		assertEquals(false, student1.readyToGraduate());
	}
	
	@Test
	public void testReadyToGraduate2() { // All parameters correct
		student1.addCourse(course2, fall, 1, grade1);
		student1.setLascComplete(true);
		student1.setMajorComplete(true);
		assertEquals(true, student1.readyToGraduate());
	}
	
	@Test
	public void testReadyToGraduate3() {	// Lasc complete = false
		student1.addCourse(course2, fall, 1, grade1);
		student1.setLascComplete(false);
		student1.setMajorComplete(true);
		assertEquals(false, student1.readyToGraduate());
	}
	
	@Test
	public void testReadyToGraduate4() {	// Major complete = false
		student1.addCourse(course2, fall, 1, grade1);
		student1.setLascComplete(true);
		student1.setMajorComplete(false);
		assertEquals(false, student1.readyToGraduate());
	}
	
	@Test
	public void testReadyToGraduate5() {	// GPA = 0.0
		student1.addCourse(course2, fall, 1, grade2);
		student1.setLascComplete(true);
		student1.setMajorComplete(false);
		assertEquals(false, student1.readyToGraduate());
	}

	// testAddCourse is tested throughout the other tests

	@Test
	public void testDropCourse() {
		fail("Not yet implemented");
	}
	
	/*	Can't make this work
	 * @Test
	public void testChangeGrade() {
		student1.addCourse(course1, fall, 1, grade1);
		student1.changeGrade(course1, fall, 1, grade1, grade3);
		assertEquals(grade3, student1.getGpa());
	}
	*/
	
	@Test
	public void testGetTranscript() {
		System.out.println(transcript1.getTranscript());
 	}
}
