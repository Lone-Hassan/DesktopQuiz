//import javax.swing.JButton;
package Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * Display The question and four options. To answer select one of those Next>>:
 * press to display next Question if its not the last Question <<Previous: Press
 * to go to previous Question if its not the First question
 */
public class Quiz extends JFrame {

	private static final long serialVersionUID = 1L;
	public JLabel Qlabel;
	ButtonGroup bg;
	private JButton Next;
	private JButton Previous;

	public JRadioButton options[] = new JRadioButton[4];
	public String questions[][] = { { "Q1: Which one of following is not a datatype?", "int", "Float", "boolean", "char" },
			{ "Q2: Which keyword is used to define a class in Java?", "class", "Class", "define", "struct" },
			{ "Q3: What is the size of int in Java?", "2 bytes", "4 bytes", "8 bytes", "16 bytes" } };
	private int correctAnswers[] = { 1, 0, 1 };
	public int currentQuestion = 0;
	private int score = 0;
	private HashMap<Integer, Integer> scoreMap = new HashMap<Integer,Integer>();

	public Quiz() {
		
		
		Qlabel = new JLabel(questions[currentQuestion][0]);
		Qlabel.setName("Qlabel");
		add(Qlabel);
		bg = new ButtonGroup();
		for (int i = 0; i < 4; i++) {
			options[i] = new JRadioButton();
			options[i].setName("opt"+i);
			add(options[i]);
			bg.add(options[i]);
		}
		Next = new JButton("Next >>");
		Next.setName("Next >>");
		/**
		 * 
		 * */
		getNextButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// System.out.println("Inside next question event");
				if (optionSelected()) {

					checkAnswer();
					if (currentQuestion < questions.length - 1) {
						currentQuestion++;
						upDateQuestion();

					} else {
						DisplayScore();
					}
				} else {

					JOptionPane.showMessageDialog(null, "Please! Choose one answer: ");
				}
			}
		});

		Previous = new JButton("<< Previous");
		Previous.setName("<< Previous");
		Previous.setEnabled(false);
		getPrevious().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Current Question: "+currentQuestion);
				if (currentQuestion > 0) {
					
					currentQuestion--;
					scoreMap.put(currentQuestion, 0);
					upDateQuestion();
					System.out.println("Current Question: "+currentQuestion);
				}
			}
		});
		add(getNextButton());
		add(getPrevious());
		Qlabel.setBounds(30, 40, 450, 20);

		options[0].setText(questions[currentQuestion][1]);
		options[1].setText(questions[currentQuestion][2]);
		options[2].setText(questions[currentQuestion][3]);
		options[3].setText(questions[currentQuestion][4]);
		options[0].setBounds(50, 80, 100, 20);
		options[1].setBounds(50, 110, 100, 20);
		options[2].setBounds(50, 140, 100, 20);
		options[3].setBounds(50, 170, 100, 20);
		getPrevious().setBounds(100, 240, 120, 30);
		getNextButton().setBounds(250, 240, 100, 30);

		setTitle("Quiz Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 350);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void checkAnswer() {
		int selectedOption = -1;
		for (int i = 0; i < 4; i++) {
			if (options[i].isSelected()) {
				selectedOption = i;
				break;
			}
		}
				score = selectedOption == correctAnswers[currentQuestion] ? 1 : 0;
		
		//System.out.println("Current Question: "+currentQuestion+" Score:"+ score+" Current Score is: "+totalscore);
		
		scoreMap.put(currentQuestion, score);
		int totalscore = 0;
		for (int value : scoreMap.values()) {
			totalscore+=value;
		}

		System.out.println("Current Question: "+currentQuestion+" Score:"+ score+" Current Score is: "+totalscore);
		
	}
	public int getcurrentScore() {
		int totalscore = 0;
		for (int value : scoreMap.values()) {
			totalscore+=value;
		}
		return totalscore;
		
	}
	private boolean optionSelected() {
		// TODO Auto-generated method stub

		for (JRadioButton option : options) {
			// System.out.println("Inside Jradio button loop");
			if (option.isSelected()) {
				// System.out.println(option.getText()+" selected!!!");
				return true;
			}
		}
		return false;
	}

	private void DisplayScore() {
		// TODO Auto-generated method stub
		// Display the final score using a JOptionPane
		int totalscore = 0;
		for (int value : scoreMap.values()) {
			totalscore+=value;
		}
		JOptionPane.showMessageDialog(null, "Quiz over! Your final score is: " + totalscore + " out of " + questions.length);
		System.exit(0); // Close the application

	}

	public void upDateQuestion() {
		// TODO Auto-generated method stub
		if (currentQuestion == questions.length - 1) {
			Next.setText("Submit");

		} else {
			Next.setText("Next >>");
		}

		if (currentQuestion == 0)
			Previous.setEnabled(false);
		else
			Previous.setEnabled(true);

		Qlabel.setText(questions[currentQuestion][0]);
		options[0].setText(questions[currentQuestion][1]);
		options[1].setText(questions[currentQuestion][2]);
		options[2].setText(questions[currentQuestion][3]);
		options[3].setText(questions[currentQuestion][4]);
		bg.clearSelection();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Quiz();

	}

	public JButton getNextButton() {
		return Next;
	}

	public JButton getPrevious() {
		return Previous;
	}

}
