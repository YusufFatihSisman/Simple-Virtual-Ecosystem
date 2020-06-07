import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class CreatureGenerator {
	
	public static final int NUMBEROFGAZELLES = 100;
	public static final int NUMBEROFCHEETAHS = 100;
	
	// gender, age(years), gestationTime(months), weight(kg), speedLimit(kmph)
	public static void generateCreature(String fileName, int total, int minWeight, int maxWeight, int maxAgeFemale, int maxAgeMale, int gestation, int minSpeed, int maxSpeed){
		
		String gender, weight, age, speedLimit, gestationTime;
		
		try{
			FileWriter fWriter = new FileWriter(fileName);
			LinkedList<LinkedList<String>> MainList = new LinkedList<LinkedList<String>>();
			
			fWriter.append("Gender, Age, Gestation Time, Weight, Speed Limit, \n");
			
			for(int i=0; i<total; i++){
				Random r = new Random();
				LinkedList<String> subList = new LinkedList<String>();
				
				if(r.nextInt(2) == 1){
					gender = "female";
					age = Integer.toString(r.nextInt((maxAgeFemale -1)  + 1) + 1);
					gestationTime = Integer.toString(gestation);
				}else{
					gender = "male";
					age = Integer.toString(r.nextInt((maxAgeMale - 1)  + 1) + 1);
					gestationTime = "0";
				}	
				weight = Integer.toString(r.nextInt((maxWeight - minWeight) + 1) + minWeight);
				speedLimit = Integer.toString(r.nextInt((maxSpeed - minSpeed) + 1) + minSpeed);
				
				subList.add(gender);
				subList.add(age);
				subList.add(gestationTime);
				subList.add(weight);
				subList.add(speedLimit);
				MainList.add(subList);
			}
			
			for(int i=0; i<MainList.size(); i++){
				for(int j=0; j<MainList.get(i).size(); j++){
					fWriter.append(MainList.get(i).get(j));	
					fWriter.append(",");
				}
				fWriter.append("\n");
			}
			fWriter.close();
		}catch(IOException e){
			System.out.println(e);
		}
		
	}
	
	// Reading from file to ArrayList of ArrayList (Order: gender, age(years), gestationTime(months), weight(kg), speedLimit(kmph)) 
	public static void read(ArrayList<ArrayList<String>> list, String fileName){
			
		String csvFile = fileName;
	    BufferedReader br = null;
	    String line = "";
	    String temp = "";
	    int j=0;
	    
	    try {
	        br = new BufferedReader(new FileReader(csvFile));
	        line = br.readLine();
	        while ((line = br.readLine()) != null) {
	        	list.add(new ArrayList<String>());
	        	for(int i=0; i<line.length(); i++){
	        		if(line.charAt(i) != ','){
	        			temp += line.charAt(i);
	        		}else{
	        			list.get(j).add(temp);
	        			temp = "";
	        		}
	        	}
	        	j++;
	        }
	    } catch (IOException e) {
	    	System.out.println(e);
	    }
	          
	}  
	
	public static void main(String args[]){
		
		final int cheetahMinWeight = 20; //kg
		final int cheetahMaxWeight = 72; //kg 
		final int cheetahMaxAgeMale = 10; //year
		final int cheetahMaxAgeFemale = 14; //year
 		final int cheetahGestationTime = 3; //month
		final int cheetahMinSpeedLimit = 95; //kmph
		final int cheetahMaxSpeedLimit = 120; //kmph
		
		final int GazelleMinWeight = 45; //kg
		final int GazelleMaxWeight = 65; //kg
		final int GazelleMaxAgeMale = 12; //year
		final int GazelleMaxAgeFemale = 12; //year
		final int GazelleGestationTime = 7; //month
		final int GazelleMinSpeedLimit = 80; //kmph
		final int GazelleMaxSpeedLimit = 97; //kmph
		
		generateCreature("cheetah.csv", NUMBEROFCHEETAHS, cheetahMinWeight, cheetahMaxWeight, cheetahMaxAgeFemale, cheetahMaxAgeMale, cheetahGestationTime, cheetahMinSpeedLimit, cheetahMaxSpeedLimit);
		generateCreature("gazelle.csv", NUMBEROFGAZELLES, GazelleMinWeight, GazelleMaxWeight, GazelleMaxAgeFemale, GazelleMaxAgeMale, GazelleGestationTime, GazelleMinSpeedLimit, GazelleMaxSpeedLimit);
		
		ArrayList<ArrayList<String>> GazelleList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> CheetahList = new ArrayList<ArrayList<String>>();
		
		read(GazelleList, "gazelle.csv");
		read(CheetahList, "cheetah.csv");
	}
	
}
