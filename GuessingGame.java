
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
        //LoadGame();
        play();
  
    }
    
    private void LoadGame()
    {
    	try{
    		
    		
    		File AnimalCopy = new File(FILENAME);
    		Scanner AnimalScanner = new Scanner(AnimalCopy);
   		
    		if(AnimalScanner.hasNext()) 
    		{	
    			
    			tree.CreateNode();
    			Iterator<String> iterator = tree.getLevelOrderIterator();
    		   		
    				while(AnimalScanner.hasNext())
    				{
    					System.out.println("HELLO");
    					tree.CreateNode();
    					iterator.next();
    					String LineInFile = AnimalScanner.nextLine();
    					
    					if(!LineInFile.equals("null")) {    						
    						tree.getCurrentNode().setData(AnimalScanner.nextLine());
    					}else {
    						tree.setCurrentNode(null);
    					}
    			
    				}
    				
    				AnimalScanner.close();
    		}
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    		System.out.println("File not Found\nCreate new file " + FILENAME + " ?");
    		System.out.println("Type in Y or N: ");
    		String answer = keyboard.nextLine();
    			
    		if(answer.equals("Y"))
    			{
    				
    					try {
    							PrintWriter file = new PrintWriter(FILENAME);
    							file.close();
    							return;
    					
    					}catch(Exception Ex) {
    							System.out.println("ERROR Creating File");
    							System.exit(0);
    					}
    				
    				
    			}else
    			{
    				
    				System.out.println("Program will now close");
    				
    				System.exit(0);
    			}
    	}
    }
    
    
    public void play()
    {
    	String answer;
    	System.out.println(tree.getCurrentData());
    		do{
    			answer = keyboard.nextLine();
        
    				if(answer.equals("no")) {
    					AdvanceToChild("no");
    					break;
    				}else if(answer.equals("yes")){
    					AdvanceToChild("yes");
    					break;
    				}
    			System.out.println("Please Enter: yes or no");
    		}while(!answer.equals("N")||!answer.equals("Y"));
    		EndofGAME();
    }
    
    
    public void EndofGAME() {
    	System.out.println("Type Y to play again or N to quit");
    	
		if(keyboard.nextLine().equals("Y")) {
			tree.resetCurrentNode();
			play();
		}else {
			Write_TO_console();
			if(AsktoWritetoFile()) {
				WritetoFile();
			}
			keyboard.close();
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
    
    
    private String IGUESSIT_IS() {
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
   

    private void AdvanceToChild(String Answer) {
    	String reply;
    	
    	if(Answer.equals("no")) {
    		tree.advanceToNo();
    	}else {
    		tree.advanceToYes();
    	}
    	
    	if(tree.isAnswer()) {
    		reply = IGUESSIT_IS();
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
   
    // This is what will be posted to the console 
    
    private void Write_TO_console() {
    	Iterator<String> iterator = null ;
    	
    	System.out.println("How would you like to see the tree?:\nType in the number for the following:");
    	System.out.println("1: LevelOrder\n2: Preorder\n3: Skip");
    	int Menu = keyboard.nextInt();
    	
    	
    	switch(Menu) {
    		case 1:
    		{
    			iterator = tree.getLevelOrderIterator();
    			break;
    		}
    		case 2:
    		{
    			iterator = tree.getPreorderIterator();
    			break;
    		}
    		default:
    		{    		
    			return;
    			
    		}
    	}
    	
    	while(iterator.hasNext()) {
    		System.out.println(iterator.next());
    	}
    	System.out.println();
    	
    }

    private void WritetoFile() 
    {
			
    			Iterator<String> iterator = tree.getLevelOrderIterator();
    			
    			while(iterator.hasNext())
    	        {
    	            iterator.next();
    	        }
    			tree.CloseFile();
    }
  
    private boolean AsktoWritetoFile() {
    	boolean writetoFile;
	
    	keyboard = new Scanner(System.in);
    	
    	System.out.print("Enter Y to save to File or N otherwise: ");
    	
    	String write = keyboard.nextLine();
    	
    	if(write.equals("Y")) {
    		writetoFile = true;
    	}else {
    		writetoFile = false;
    	}  
    	
    	return writetoFile; 	
    }
}

