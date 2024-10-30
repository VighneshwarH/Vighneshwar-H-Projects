import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TimedQuiz {
    private static final int QUESTION_TIME_LIMIT = 10000;
    private static final String[] QUESTIONS = {
        "Which Operating System is open source?",
        "Which Database System is open source?",
        "Which protocol is used to send emails?"
    };
    private static final String[][] OPTIONS = {
        {"A) Windows", "B) macOS", "C) Linux", "D) ChromeOS"},
        {"A) MySQL", "B) Oracle", "C) MS SQL Server", "D) IBM DB2"},
        {"A) HTTP", "B) FTP", "C) SMTP", "D) DNS"}
    };
    private static final char[] ANSWERS = {'C', 'A', 'C'};
    private static int score = 0;
    private static int questionIndex = 0;
    private static Timer timer;
    private static boolean answered;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Timed Quiz! You have 10 seconds for each question.");
        System.out.println("\nWithout answering a question you cannot move on to next question.\n");
        System.out.println("If time is up then quiz is over!\n");
        while (questionIndex < QUESTIONS.length) {
            askQuestion(scanner);
        }

        System.out.println("Quiz finished! Your score: " + score + "/" + QUESTIONS.length);
        scanner.close();
    }

    private static void askQuestion(Scanner scanner) {
        System.out.println("Question " + (questionIndex + 1) + ": " + QUESTIONS[questionIndex]);
        for (String option : OPTIONS[questionIndex]) {
            System.out.println(option);
        }
        System.out.print("Your answer (A, B, C, or D): ");
        answered = false;
        startTimer();

        String answer = scanner.nextLine().toUpperCase();
        if (!answered) {
            timer.cancel();
            answered = true;
            checkAnswer(answer);
        }
    }

    private static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up!");
                    questionIndex++;
                    timer.cancel();
                    System.out.println("Quiz over!");
                }
            }
        }, QUESTION_TIME_LIMIT);
    }

    private static void checkAnswer(String answer) {
        if (answer.length() == 1 && answer.charAt(0) == ANSWERS[questionIndex]) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect! The correct answer is: " + ANSWERS[questionIndex]);
        }
        questionIndex++;
    }
}
