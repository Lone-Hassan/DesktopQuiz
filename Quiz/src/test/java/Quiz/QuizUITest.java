package Quiz;

//import javax.swing.JRadioButton;
//import org.assertj.swing.testng.AssertJSwingTestNGTestCase;
//import java.awt.Robot;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class QuizUITest {

	Quiz quiz;

	@BeforeMethod
	public void setUp() {
		quiz = new Quiz();
	}

	@Test
	public void testUpdateQuestion() {
		quiz.currentQuestion = 1;
		quiz.upDateQuestion();
		Assert.assertEquals(quiz.Qlabel.getText(), "Q2: Which keyword is used to define a class in Java?");
		Assert.assertEquals(quiz.options[0].getText(), "class");
		Assert.assertEquals(quiz.options[1].getText(), "Class");
	}

	/**
	 * Clicking Next without selecting an option
	 */

	@Test(groups = { "NextButton" })
	public void testNextButtonClick() {
		quiz.currentQuestion = 0;
		quiz.options[1].doClick();
		quiz.getNextButton().doClick(); // Simulate a click on the "Next" button
		Assert.assertEquals(quiz.currentQuestion, 1);
		Assert.assertEquals(quiz.Qlabel.getText(), "Q2: Which keyword is used to define a class in Java?");
	}

	@Test
	public void testPreviousButtonClick() throws InterruptedException {
		quiz.currentQuestion = 1;
		quiz.upDateQuestion();

		quiz.getPrevious().doClick();

		Assert.assertEquals(quiz.currentQuestion, 0);
		Assert.assertEquals(quiz.Qlabel.getText(), "Q1: Which one of following is not a datatype?");
	}

	@Test
	public void testInitialQuestion() {
		Assert.assertEquals(quiz.Qlabel.getText(), "Q1: Which one of following is not a datatype?");
		Assert.assertTrue(quiz.options[0].isVisible());
		Assert.assertEquals(quiz.options[0].getText(), "int");
	}

//	@Test(dataProvider = "AnswerData")
//	public void TestScore(int ans1, int ans2, int ans3, int score) {
//
//		for (int i=0; i<quiz.questions.length; i++) {
//			quiz.options[ans1].doClick();
//			quiz.getNextButton().doClick();
//		}
//		
//
//	}

	@DataProvider(name = "AnswerData")
	public Object[][] options() {
		return new Object[][] { { 0, 0, 1, 2 }, { 1, 0, 1, 3 }, { 0, 1, 0, 0 } };

	}
}
