package knapsackGenaticsAlgorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class Chromosome {

	private ArrayList<Boolean> chromosome ;
	private int value ;
	
	
	public Chromosome  ( ){
		chromosome = new ArrayList<Boolean>();
		value = 0 ;
	}
	
	/*
	 *create chromosome with value to bits  
	 * */
	public Chromosome  (int chromosomeValue){
		if(chromosomeValue<0) chromosomeValue =0;
		value = chromosomeValue ;
		setChromosomeBits();
	}
	
	
	/*
	 *create chromosome with copy constructor 
	 *check if chromosome != null 
	 * */
	public Chromosome  ( ArrayList<Boolean> chromosome ){
		if(chromosome== null){
			this.chromosome = new ArrayList<Boolean>();
			value =0 ;
		}
		else {
			this.chromosome = new ArrayList<Boolean>(chromosome);
			setValue ();
		}
	}
	
	/*
	 *create chromosome with random bits within length>0
	 * */
	public void generatRandomChromosome  (int length){
		if(length<0) length =0;
		chromosome = new ArrayList<Boolean>();
		Random random = new Random();
		for (int i=0;i<length;++i){
			chromosome.add(i, random.nextBoolean());
		}
		setValue ();
	}
	
	/*
	 *get Length 
	 * */
	public int getLength(){
		return chromosome.size();
	}
	
	public ArrayList<Boolean> getChromosome (){
		 return chromosome ;
	}

	public int getValue (){
		
		return value;
	}
	
	private void setValue (){
		/*
		 * bitSet lenght = lenght of 2^(size-1)  
		 * if size = 5
		 * 		bitValue = 2^4 = 16 
		 * 		16 = 10000 length of bitset = 5
		 * */
		int bitValue = (int) (Math.pow(2, chromosome.size()-1) ) ;
		
		BitSet bitSet = new BitSet(bitValue) ;
		 
		for(int i=0;i<chromosome.size();++i){
			bitSet.set(i, chromosome.get(i));
		}
		value =  bitSetToInt(bitSet);
		   
	}
	
	//set binary bits for chromosome within value
	private void setChromosomeBits(){ 
		ArrayList<Boolean> bitSet = intToBooleanArray(value);
		chromosome = new ArrayList<Boolean>();
		chromosome = bitSet;
	}
	
	
	private int bitSetToInt(BitSet bitSet)
	{
	    int bitInteger = 0;
	    for(int i = 0 ; i < 32; i++)
	        if(bitSet.get(i))
	            bitInteger |= (1 << i);
	    return bitInteger;
	}

	private ArrayList<Boolean> intToBooleanArray ( int value){	
		String binaryString = Integer.toBinaryString(value);
		ArrayList<Boolean> bits = new ArrayList<Boolean>();
		for(int i =0;i<binaryString.length(); ++i){
			if(binaryString.charAt(i) == '1') 
				bits.add(true);
			else 
				bits.add(false);
		}
		return bits ;
	}

}
