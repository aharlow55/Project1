import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Prompter {
    private Jar jar;
    private BufferedReader bufferedReader;

    public Prompter() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String ask(String question) {
        System.out.println(question);
        String answer = "";
        try {
            answer = bufferedReader.readLine();
        } catch (IOException ioe) {
            System.out.println("An error has occured, please try again.%s");
            ioe.printStackTrace();
        }
        return answer;
    }

    public int askInt(String question) {
        int answer = 0;
        while (answer < 1) {
            try {
                answer = Integer.parseInt(ask(question));
            } catch (NumberFormatException nfe) {
                System.out.println("Please guess a number.");
                answer = 0;
            }
            if (answer < 1) {
                System.out.println("There must be at least one item in the jar");
            }
        }
        return answer;
    }

    public void administrator() {
        System.out.println("-------------- ADMINISTRATOR --------------");
        String itemName = ask("What type of item would you like to put in the jar?");
        int maxAmount = askInt("What is the maximum amount of " + itemName + " that can fit in the jar?");
        jar = new Jar(itemName, maxAmount);
    }

    public void askForGuess() {
        int guess = askInt("How many " + jar.getItemName()
                + " are in the jar? Pick a number between 1 and " + jar.getMaximunNumber() + ".");
        jar.setGuess(guess);
    }

    public void outputOfResult(String result) {
        switch (result) {
            case "win":
                System.out.println("You got it in " + jar.getGuessAttempts() + " attempts");
                jar.setWinGame(true);
            break;
            case "exceedsMax":
                System.out.println("Your guess must be less than " + jar.getMaximunNumber());
                break;
            case "high":
                System.out.println("Your guess is too high");
                break;
            case "low":
                System.out.println("Your guess is too low");
                break;
            case "error":
                System.out.println("THERE IS AN ERROR IN JARLOGIC.");
                break;
            default:
                System.out.println("THERE IS AN ERROR IN JARPROMPTER.");
                break;
        }
    }

    public void promptLoop() {
        while (!jar.isWinGame()) {
            askForGuess();
            String result = jar.applyGuess();
            outputOfResult(result);
        }
    }

    public void player() {
        System.out.println("-------------- PLAYER --------------");
        promptLoop();
    }

    public void play() {
        administrator();
        player();
    }
}    