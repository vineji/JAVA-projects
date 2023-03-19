
import java.util.Scanner;
import java.io.*;

class Question
{
    String the_question;
    String [] the_answers;
    int [] the_scores;
    String correct_answer;
}

public class Mini {
    public static void main(String [] args) throws IOException
    {
        quiz();
        System.exit(0);
    }

    public static Question createRecords(Question Q, String question, String[] answers, int[] scores, String right_answer) {
        Q.the_question = question;
        Q.the_answers = answers;
        Q.the_scores = scores;
        Q.correct_answer = right_answer;
        return Q;
    }

    public static void printRules()// prints the rules of the quiz game
    {

        final String rule1 = "Do not ask for help to answer the question.";
        final String rule2 = "Do not look at your neighbor's monitor to copy the answer.";
        final String rule3 = "Do not search up the answer.";
        final String rule4 = "You should answer the questions by yourself." + "\n" + "Only 2 players can play";
        final String rules;
        rules = rule4 + "\n" + rule1 + "\n" + rule2 + "\n" + rule3;
        System.out.println(rules);
        return;
    }

    public static String print_question(Question Q) // asks the question and prints out the right answer
    {
        String answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println(Q.the_question);
        for (int i = 0; i <= 3; i++) {
            System.out.println(Q.the_answers[i]);
        }
        answer = scanner.nextLine();
        int n = Integer.parseInt(answer);

        System.out.println("You chose option " + Q.the_answers[n - 1] + ".");
        System.out.println("The right answer was " + Q.correct_answer + ".");
        return answer;
    }

    public static int current_score(String answer, Question Q, int current_points)// takes in the user's answer and returns how many points the user has scored
    {
        int n;
        n = Integer.parseInt(answer);
        int user_points = current_points;
        String allocation = Q.the_answers[n - 1];

        if (allocation.equals(Q.the_answers[0]))
            user_points = user_points + Q.the_scores[0];
        else if (allocation.equals(Q.the_answers[1]))
            user_points = user_points + Q.the_scores[1];
        else if (allocation.equals(Q.the_answers[2]))
            user_points = user_points + Q.the_scores[2];
        else if (allocation.equals(Q.the_answers[3]))
            user_points = user_points + Q.the_scores[3];

        System.out.println("You have a total of " + user_points + " points.");
        return user_points;
    }

    public static void player_points(int Q1_points, int Q2_points, int Q3_points, int current_points, int i) throws IOException
    {
        String filename = "player" + i + ".txt";
        PrintWriter outputStream = new PrintWriter (new FileWriter(filename));// writes the user's score for each question in a file

        outputStream.println("---------------- PLAYER " + i + " ----------------");
        outputStream.println("QUESTION 1: " + Q1_points + " points");
        outputStream.println("QUESTION 2: " + Q2_points + " points");
        outputStream.println("QUESTION 3: " + Q3_points + " points");
        outputStream.println("TOTAL: " + current_points + " points");

        outputStream.close();
    }

    public static void checkSCORE() throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        String player;
        String filename;

        System.out.println("Which player score do you want to check?" + "\n" + "ENTER THE NUMBER OF THE PLAYER (1 / 2)");
        player = scanner.nextLine();
        filename = "player" + player + ".txt";

        BufferedReader inputStream = new BufferedReader (new FileReader(filename));
        for (int i=0; i<=4; i++)
        {
            String s = inputStream.readLine(); // reads the user scores from the player file and outputs it.
            System.out.println(s);
        }// print what is read to the screen
        System.out.println("------------------------------------------");
        inputStream.close();
    }



    public static void quiz() {// calls the methods in the right order
        Scanner scanner = new Scanner(System.in);
        Question[] quiz = new Question[3];

        Question Q1 = new Question();
        String question1 = "1.Which club has the most Premier League titles since 1992?" + "\n" + "Choose between 1-4";
        String[] options_1 = {"1) Manchester United", "2) Arsenal", "3) Manchester City", "4) Chelsea"};
        int[] points_1 = {10, 0, 5, 2};
        String right_answer1 = "1) Manchester United";
        quiz[0] = createRecords(Q1, question1, options_1, points_1, right_answer1);
        int Q1_points;

        Question Q2 = new Question();
        String question2 = "2.Who  has the most Ballon d'ors?" + "\n" + "Choose between 1-4";
        String[] options_2 = {"1) Kaká", "2) Cristiano Ronaldo", "3) Lionel Messi", "4) Harry Maguire"};
        int[] points_2 = {2, 5, 10, 0};
        String right_answer2 = "3) Lionel Messi";
        quiz[1] = createRecords(Q2, question2, options_2, points_2, right_answer2);
        int Q2_points;

        Question Q3 = new Question();
        String question3 = "3.Which club has the most Champions league trophies?" + "\n" + "Choose between 1-4";
        String[] options_3 = {"1) AFC Ajax", "2) Real Madrid CF", "3) FC Barcelona", "4) FC Bayern Munich"};
        int[] points_3 = {0, 10, 2, 5};
        String right_answer3 = "2) Real Madrid CF";
        quiz[2] = createRecords(Q3, question3, options_3, points_3, right_answer3);
        int Q3_points;

        String Playing = "yes";
        int current_points = 0;
        String check_score;

        while (Playing.equals("yes"))
        {
            printRules();

            for (int i = 1; i <= 2; i++)// the question repeats to ask another user the same question
            {
                current_points = 0;
                System.out.println("");
                System.out.println("||||||||||||||||||||||||||||||| PLAYER " + i + " |||||||||||||||||||||||||||||||||||");
                System.out.println("_______________________________________________________________________");
                System.out.println("");
                current_points = current_score(print_question(quiz[0]), quiz[0], current_points);
                Q1_points = current_points;
                System.out.println("_______________________________________________________________________");
                System.out.println("");
                current_points = current_score(print_question(quiz[1]), quiz[1], current_points);
                Q2_points = current_points - Q1_points;
                System.out.println("_______________________________________________________________________");
                System.out.println("");
                current_points = current_score(print_question(quiz[2]), quiz[2], current_points);
                Q3_points = current_points - Q1_points - Q2_points;
                System.out.println("_______________________________________________________________________");
                try
                {
                    player_points(Q1_points, Q2_points, Q3_points, current_points, i);
                }
                catch(IOException e)
                {
                }
            }
            System.out.println("Do you want to check your score for each question? (yes/no)");
            check_score = scanner.nextLine();

            while (check_score.equals("yes"))
            {
                try
                {
                    checkSCORE();
                }
                catch(IOException e)
                {
                }
                System.out.println("");
                System.out.println("Do you want to check your score for each question? (yes/no)");
                check_score = scanner.nextLine();
            }

            System.out.println("");
            System.out.println("");
            System.out.println("Do you want to play again? (yes/no)");
            Playing = scanner.nextLine();
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("");

        }
        return;
    }
}
