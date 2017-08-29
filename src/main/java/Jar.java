import java.util.Random;

public class Jar {
    private String itemName;
    private int maximunNumber;
    private int itemQuantity;
    private int guess;
    private int guessAttempts;
    private boolean winGame = false;

    public Jar(String itemName, int maximunNumber) {
        this.itemName = itemName;
        this.maximunNumber = maximunNumber;
        setItemQuantity();
    }

    public int fill() {
        Random random = new Random();
        return random.nextInt(maximunNumber) + 1;
    }

    public String applyGuess() {
        String apply = "";
        if (guess == itemQuantity) {
            newAttempt();
            apply = "win";
        } else if (guess > maximunNumber) {
            apply = "exceedsMax";
        } else if (guess > itemQuantity) {
            newAttempt();
            apply = "high";
        } else if (guess < itemQuantity) {
            newAttempt();
            apply = "low";
        } else {
            newAttempt();
            apply = "error";
        }
        return apply;
    }

    public void newAttempt() {
        guessAttempts++;
    }

    public String getItemName() {
        return itemName;
    }

    public int getMaximunNumber() {
        return maximunNumber;
    }

    public void setItemQuantity() {
        this.itemQuantity = fill();
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getGuessAttempts() {
        return guessAttempts;
    }

    public boolean isWinGame() {
        return winGame;
    }

    public void setWinGame(boolean winGame) {
        this.winGame = winGame;
    }
}