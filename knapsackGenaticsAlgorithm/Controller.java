/**
 * 
 */
package knapsackGenaticsAlgorithm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Nov 7, 2015 2:19:10 PM
 * GenaticsAlgorithmAssigemnt1
 * @author Almgwary
 * 
 */
public class Controller {
	
	public void  run (){



		Scanner input = new Scanner(System.in);
		
		//System.out.print("numberOfTestcases? ");
		int numberOfTestcases = input.nextInt();
		for(int i=0;i<numberOfTestcases;++i){
			
			ArrayList<Item> items = new ArrayList<Item>();
			//System.out.print("itemNumber? ");
			int itemsNumber = input.nextInt();
			//System.out.print("sizeOfKnapSack? ");
			int sizeOfKnapSack = input.nextInt();
			
			for(int j =0; j<itemsNumber;++j){
				System.out.print("weight? benifi? ");
				items.add(new Item(input.nextInt(), input.nextInt()));
			} 
			Canonical canonical = new Canonical() ;
		    
			double bestBenifet = canonical.run(sizeOfKnapSack , items);
		
			//System.out.print("best=" + bestBenifet);
			
			
			
		}
		
 
	}

}
