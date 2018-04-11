package PracticeWithBinaryTrees;

import java.util.*;
import TreePackage.DecisionTreeInterface;
import TreePackage.DecisionTree;

public class GuessingGame
{
    private DecisionTreeInterface<String> tree;
    Scanner keyboard;
    public GuessingGame(String question, String noAnswer, String yesAnswer)
    {
    	keyboard = new Scanner(System.in);
        DecisionTree<String> no = new DecisionTree<>(noAnswer);
        DecisionTree<String> yes = new DecisionTree<>(yesAnswer);
        tree = new DecisionTree<>(question, no, yes);
        play();
  
    }
    public void play()
    {
    	String answer;
    	System.out.println(tree.getCurrentData());
    		do{
    			answer = keyboard.nextLine();
        
    				if(answer.equals("no")) {
    					AdvanceToRoot("no");
    					break;
    				}else if(answer.equals("yes")){
    					AdvanceToRoot("yes");
    					break;
    				}
    			System.out.println("Please Enter: yes or no");
    		}while(!answer.equals("no")||!answer.equals("yes"));
    		if(PlayAgain().equals("Y")) {
    		tree.resetCurrentNode();
    		play();
    		}else {
    			System.exit(0);
    		}
    }
    
    public void learn()
    {
    	String QuestionOfAnimal, Animal;
        System.out.println("Please Enter the Question about the animal");
        QuestionOfAnimal = keyboard.nextLine();
        
        System.out.println("What animal were you thinking of?");
        Animal = keyboard.nextLine();
        
        tree.setResponses(tree.getCurrentData(), Animal);
        tree.setCurrentData(QuestionOfAnimal);
    }
    private String PlayAgain() {
    	System.out.println("Type Y to play again or N to quit");
    	return keyboard.nextLine();
    }
    private String GetInput() {
    	String answer; 
    	System.out.println("I guess it is a " + tree.getCurrentData());
    	
    		do {
    			answer = keyboard.nextLine();
    					if(answer.equals("no")|| answer.equals("yes")) {
    						break;
    					}
    			System.out.println("Please Enter: yes or no");
    		}while(!answer.equals("no")|| !answer.equals("yes"));
    	
    	return answer;
    }
    private void AdvanceToRoot(String Answer) {
    	String reply;
    	
    	if(Answer.equals("no")) {
    		tree.advanceToNo();
    	}else {
    		tree.advanceToYes();
    	}
    	
    	if(tree.isAnswer()) {
    		reply = GetInput();
    		if(reply.equals("no")) {
    			System.out.println("I Lose");
    			learn();
    		}else {
    			System.out.println("I Win");
    		}
    	}else {
    		play();
    	}
    }
}

