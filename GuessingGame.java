
import java.util.*;
import TreePackage.DecisionTreeInterface;
import TreePackage.DecisionTree;
import java.io.*;

public class GuessingGame
{
	private final String FILENAME = "ANIMALS.txt";
	private DecisionTreeInterface<String> tree;
    Scanner keyboard;
    public GuessingGame(String question, String noAnswer, String yesAnswer)
    {
    	keyboard = new Scanner(System.in);
        DecisionTree<String> no = new DecisionTree<>(noAnswer);
        DecisionTree<String> yes = new DecisionTree<>(yesAnswer);
        tree = new DecisionTree<>(question, no, yes);
        LoadGame();
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
    		}while(!answer.equals("N")||!answer.equals("Y"));
    		
    		if(PlayAgain().equals("Y")) {
    			tree.resetCurrentNode();
    			play();
    		}else {
    			if(AsktoWritetoFile()) {
    				WritetoFile();
    			}
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
    private void LoadGame(){
    	try{
    		
    		
    	}catch(Exception ex){
    		System.out.println("File not Found\nCreate new file " + FILENAME + " ?");
    		System.out.println("Type in Y or N");
    		String answer = keyboard.nextLine();
    			if(answer.equals("Y")){
    				CreateFile();
    			}else{
    				System.out.println("Program will now close");
    				System.exit(0);
    			}
    	}
    }
    private void CreateFile(){
    	try{
    		File file = new File(FILENAME);
    		}catch(Exception ex){
    			System.out.println("File could not open Program is now closing");
    			System.exit(0);
    		}
    }
    private void WritetoFile() {
    	try{
    			PrintWriter file = new PrintWriter(FILENAME);
    			Iterator<String> iterator = tree.getLevelOrderIterator();
    			
    			while(iterator.hasNext())
    	        {
    	            file.println(iterator.next());
    	        }
    			file.close();
    			
    		}catch(Exception ex){
    			System.out.println("File not Found\n Would you like to Create a new File?" );
    			System.out.println("Please Enter Y or N:");
    			String answer = keyboard.nextLine();
    			
    			if(answer.equals("Y")){
    				CreateFile();
    			}else{
    				System.out.println("Program is now Closing");
    				System.exit(0);
    			}
    		}
    }
    private boolean AsktoWritetoFile() {
    	boolean writetoFile = false;
    	
    	System.out.println("Enter Y to save to File or N otherwise");
    	String Answer = keyboard.nextLine();
    	
    	writetoFile = Answer.equals("Y") ? true : false;
    	
    	return writetoFile;
    	
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

