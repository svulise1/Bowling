package bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
	private String[] playerName;
	private int playerCount;
	private HashMap<String, ArrayList<String>> playerDetails;
	private int frameCount;
	private int score;
	private String Winner;
	private ArrayList<Integer> finalScore;
	public Game(){
		playerDetails=new HashMap<>();
	}
	public void startGame()
	{
		finalScore=new ArrayList<>();
		System.out.println("enter number of players");
		Scanner sc=new Scanner(System.in);
		playerCount=sc.nextInt();
		playerName=new String[playerCount];
		for(int i=0;i<playerCount;i++)
		{
			System.out.println("Enter player1Name:");
			playerName[i]=sc.next();
			playerDetails.put(playerName[i], new ArrayList<>());
		}
		startFrames();
		for(int j=0;j<finalScore.size();j++)
			System.out.println(finalScore.get(j));
		int max = -1;
	    for(int i=0; i<finalScore.size(); i++){
	        if(finalScore.get(i) > max){
	            max = finalScore.get(i);
	            Winner=playerName[i];
	        }
	    }
	    System.out.println("winner : "+Winner);
	}
	public void startFrames(){
		for(int i=0;i<10;i++){
			frameCount++;
			for(int j=0;j<playerCount;j++){
		System.out.println("Frame:"+frameCount);
		startRolls(frameCount,playerName[j]);
			}
		}
	}
	public void startRolls(int framecount,String name)
	{
		ArrayList<String> rolls;
		rolls=playerDetails.get(name);
		int count=2;
		Scanner sc=new Scanner(System.in);
		int temprolls[]=new int[3];
		int tempScore=0;
		for(int i=0;i<count;i++){
			System.out.println("Enter "+name +" Roll "+(i+1));
			temprolls[i]=sc.nextInt();
			tempScore+=temprolls[i];
			if(tempScore==10 && framecount!=10){
				if(i==0)
					rolls.add("X");
				else
				{
					rolls.add("/");
				}
				break;
			}
			else if(tempScore==10 & framecount==10){
				count=3;
				if(i==0)
					rolls.add("X");
				else
				{
					rolls.add("/");
				}
			}
			else
			{
				rolls.add(String.valueOf(temprolls[i]));
			}
		}
		playerDetails.put(name, rolls);		
		String[] rollsArray = rolls.toArray(new String[rolls.size()]);
		scoreCard(rollsArray);
		System.out.println(score);
		if(frameCount==10)
			finalScore.add(score);
		score=0;
	}
	public int scoreCard(String[] rollsArray){
		for(int i=0;i<rollsArray.length;i++)
		{
			if(rollsArray[i].equals("X")){
				if(i+2<rollsArray.length){
				if(rollsArray[i+2].equals("/")){
					score+=20;
					//System.out.println("in Strike Spare:"+score);
				}
				else
				{
					//System.out.println("in Strike case:"+score+" "+i+" "+rollsArray.length);
					score+=10+getValue(rollsArray[i+1])+getValue(rollsArray[i+2]);
					if(frameCount==10 && (i==rollsArray.length-3) )
						return score;
				}
				}
			}
			else if(rollsArray[i].equals("/"))
			{
				if(i+1<rollsArray.length){
						//System.out.println("Score in Spare Case:"+score);
						score+=10+getValue(rollsArray[i+1])-getValue(rollsArray[i-1]);
						//System.out.println("Score after adding:"+score+" "+rollsArray.length);
						if(frameCount==10 && (i==rollsArray.length-2))
							return score;
				}
			}
			else
			{ 
				//System.out.println("Adding score normally:"+score+" "+i);
					score+=getValue(rollsArray[i]);
					//System.out.println("score after adding normally:"+score+" "+frameCount);
			}
		}
		return score;
	}
	public int getValue(String str){
		if(str.equals("X"))
			return 10;
		return Integer.parseInt(str);
	}
}
