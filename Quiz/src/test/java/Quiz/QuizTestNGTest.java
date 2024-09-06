package Quiz;

import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;

public class QuizTestNGTest extends AssertJSwingTestngTestCase {

    private FrameFixture window;
    private Quiz quiz;
    @Override
    protected void onSetUp() {
        JFrame frame = GuiActionRunner.execute(() -> {
            // Initialize your Quiz GUI here
        	quiz = new Quiz();
            return quiz; // Replace with your actual frame class
        });
        window = new FrameFixture(robot(), frame);
    }

    @Test
    public void shouldDisplayQuestionAndOptions() {
        window.label("Qlabel").requireText("Q1: Which one of following is not a datatype?");
        window.radioButton("opt0").requireText("int");
        window.radioButton("opt1").requireText("Float");
        window.radioButton("opt2").requireText("boolean");
        window.radioButton("opt3").requireText("char");
        //"Float", "boolean", "char" 
    }

    @Test
    public void shouldSelectOptionAndMoveToNextQuestion() {
        window.radioButton("opt0").click();
        window.button("Next >>").click();
        window.label("Qlabel").requireText("Q2: Which keyword is used to define a class in Java?");
        // Add assertions for the next question
    }

    @Test
    public void shouldShowErrorIfNoOptionSelected() {
        window.button("Next >>").click();
        window.optionPane().requireMessage("Please! Choose one answer: ");
        window.optionPane().okButton().click();
    }
    @Test(groups = { "NextButton" })
	public void testNextButtonClick() throws InterruptedException {

    	quiz.currentQuestion = 1;
    	GuiActionRunner.execute(() -> { quiz.upDateQuestion(); });
    	window.radioButton("opt0").click();
    	window.button("Next >>").click();
		Assert.assertEquals(quiz.currentQuestion, 2);
		Assert.assertEquals(quiz.Qlabel.getText(), "Q3: What is the size of int in Java?");
	}
    
    @Test
    public void previousButtonDisabled() {
    	
    	window.button("<< Previous").requireDisabled();
    	window.radioButton("opt1").click();
    	window.button("Next >>").click();
    	Assert.assertEquals(quiz.getcurrentScore(),1);
    	window.button("<< Previous").click();
    	Assert.assertEquals(quiz.getcurrentScore(),0);
    	window.button("<< Previous").requireDisabled();
    	
    }
}

