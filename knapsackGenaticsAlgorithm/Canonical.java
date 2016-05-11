package knapsackGenaticsAlgorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
/**
 * Outline of the Basic Genetic Algorithm 
 * [Start] Generate random population of n chromosomes (suitable solutions for the problem) 
 * [Fitness] Evaluate the fitness f(x) of each chromosome x in the population 
 * [New population] Create a new population by repeating following steps until the new population is complete 
 * [Selection] Select two parent chromosomes from a population according to their fitness (the better fitness, the bigger chance to be selected) 
 * [Crossover] With a crossover probability cross over the parents to form a new offspring (children). If no crossover was performed, offspring is an exact copy of parents. 
 * [Mutation] With a mutation probability mutate new offspring at each locus (position in chromosome). 
 * [Accepting] Place new offspring in a new population 
 * [Replace] Use new generated population for a further run of algorithm 
 * [Test] If the end condition is satisfied, stop, and return the best solution in current population 
 * [Loop] Go to step 2 
 * 
 * */

public class Canonical {
	
	private ArrayList<Boolean> chromosome1 ;
	private ArrayList<Boolean> chromosome2 ;
	private ArrayList<Boolean> newChromosome1 ;
	private ArrayList<Boolean> newChromosome2 ;
	
	private ArrayList<Item> items ;
	
	private int popluationSize;
	
	private ArrayList<Chromosome> population ;
	private ArrayList<Chromosome> Newchromosomes ;
	
	private ArrayList<Double> fittness ;
	private ArrayList<Double> fittnessProbality ;
	
	private ArrayList<Integer> selection ;
	
	private int itemsNumber ;
	
	private double bestBenift ;
	private Chromosome bestChromosome ;
	private int sizeOfKnapsack ;
 	
 	private void doInitialzation(){
		
		ArrayList<Chromosome> population = new ArrayList<Chromosome>();
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		 
		for(int j = 0;j<itemsNumber;++j){
			Chromosome chromosome = new Chromosome();
			chromosome.generatRandomChromosome(itemsNumber);
			population.add(chromosome);
		}
		
		this.popluationSize = itemsNumber;
	    this.population   = population ;
	}
	
	/**
	 * evaluate every chromosome and get value of each gene "item" and sum all values
	 * get maximum right  value to bestValue and to best Chromosome
	 * */
	private void doEvaluateFittnes( ){
		
		fittness = new ArrayList<Double>();
 		
		for(int i=0 ; i <popluationSize;++i){
			ArrayList<Boolean> chromosome = new ArrayList<Boolean>();
			chromosome = population.get(i).getChromosome();
			int chromosomeTotalWeight = 0 ;
			int chromosomeTotaBenift = 0 ;
			for(int j=0;j<chromosome.size();++j){
				if(chromosome.get(j) == true){
					chromosomeTotalWeight+= items.get(j).getWeight();
					chromosomeTotaBenift+= items.get(j).getBeinfit();
				}
			}
			
			if(chromosomeTotalWeight<= sizeOfKnapsack && chromosomeTotaBenift > bestBenift)
			{
				bestBenift = chromosomeTotaBenift ;
				bestChromosome = population.get(i);
			}
			
			fittness.add((double) chromosomeTotalWeight);
			
				
			 
		}
	}

	private void doSelection () {
		RouletteWheel rouletteWheel = new RouletteWheel();
		selection = rouletteWheel.run(fittness);		
	}

	/**
	 * make cross  over operation split to chromosome with randome index number
	 * */
	private void doCrosser(){
		
		for(int i= 0;i<population.size()/2;++i){
			chromosome1 = population.get(i*2).getChromosome();
			chromosome2 = population.get((i*2)+1).getChromosome();
			
			Random random = new Random();
			
			int splitIndex = random.nextInt(popluationSize-2)+1;
			for(int j=0 ; j<splitIndex ; ++j){
				newChromosome1.add(chromosome1.get(i));
				newChromosome2.add(chromosome2.get(i));
			}
			for(int j= splitIndex;j<popluationSize;++j){
				newChromosome1.add(chromosome2.get(i));
				newChromosome2.add(chromosome1.get(i));
			}
			
			Chromosome chromosomeOpject  = new Chromosome(newChromosome1);
			Newchromosomes.add(chromosomeOpject);
			chromosomeOpject  = new Chromosome(newChromosome2);
			Newchromosomes.add(chromosomeOpject);
		}
			
	}	
	
	 private void doMutaion (){
			
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			for(int i= 0;i<Newchromosomes.size()/2;++i){
				
				Random random = new Random();
				int mutationIndex = random.nextInt(popluationSize-1);
				
				ArrayList<Boolean> chromosome = new ArrayList<Boolean>();
				chromosome =  Newchromosomes.get(i).getChromosome() ;
				 
				chromosome.set(mutationIndex, !chromosome.get(mutationIndex));
				 
				Newchromosomes.set(i,new Chromosome(chromosome));	
				//System.out.println(" --->  " +i +" $ "+ chromosome.toString());
				
			}
			 
		}

     public Canonical() {
    	this.chromosome1 = new ArrayList<Boolean>();
    	this.chromosome2 = new ArrayList<Boolean>();
    	this.newChromosome1 = new ArrayList<Boolean>();
    	this.newChromosome2 =  new ArrayList<Boolean>();
    	this.popluationSize = 0;
    	this.population = new ArrayList<Chromosome>() ;
    	this.Newchromosomes = new ArrayList<Chromosome>();
    	this.fittness = new ArrayList<Double>();
    	this.fittnessProbality = new ArrayList<Double>() ;
        this.selection = new ArrayList<Integer>() ;	 
       	this.items = new ArrayList<Item>();
       	this.itemsNumber = 0;
    	this.bestBenift = 0;
    	this.bestChromosome =new Chromosome() ;
    	this.sizeOfKnapsack = 0 ;
     }
     

	/**
	 *  the two chromosome size must be equal 
	 * */
	public double run ( int sizeOfKnapsack , ArrayList<Item> items){
	 
	    this.items = items ;
	    this.itemsNumber = items.size();
	    this.sizeOfKnapsack = sizeOfKnapsack;
	    
	    for(int i =0 ; i < 70 ;++i){
	    	System.out.println("$>doInitialzation");
			doInitialzation();
			System.out.println("$>doEvaluateFittnes");
			doEvaluateFittnes();
			System.out.println("$>doSelection");
			doSelection();
			System.out.println("$>doCrosser");
			doCrosser();
			System.out.println("$>doMutaion");
			doMutaion();
			System.out.println("+-------------------------+\n| ["+i+"] "+bestBenift+"\n+-------------------------+\n");
			population = new ArrayList<Chromosome>() ;
			population = Newchromosomes ;
			Newchromosomes = new ArrayList<Chromosome>();
			
	    }
		
		return bestBenift ;
		
	}
}
