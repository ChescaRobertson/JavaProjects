package org.example;

import java.util.Random;
import java.util.Scanner;

public class DoubleOrNothing {

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private boolean keepPlaying = true;

    User user = new User();

    public void playGame(){
        gameWelcome();
        askContinue();

        while(this.keepPlaying) {

            if(shouldDouble()){
                doublePoints();
                printCongrats();
                askContinue();
            } else {
                printLost();
                return;
            }
        }

        printLeaveGame();
    }

    private void gameWelcome(){
        System.out.println("Welcome to Double or Nothing. You start with " + user.getPoints() + "points\n");
    }

    private void printCongrats(){
        System.out.println("You won! Your current points total is: " + user.getPoints());
    }

    private void printLost(){
        System.out.println("You lost! No points for you");
    }

    private void printLeaveGame(){
        System.out.println("Congrats! You finish with " + user.getPoints() + " points");
    }

    protected void askContinue(){
        System.out.println("Would you like to play? Enter YES to try double your points: ");
        this.keepPlaying = this.scanner.nextLine().equalsIgnoreCase("YES");
    }

    protected boolean shouldDouble() {
        return this.random.nextBoolean();
    }

    protected void doublePoints(){
        user.setPoints(user.getPoints() * 2);
    }
}
