package unal.datastructures;

import java.io.*;
import java.util.*;

/**
 * TODO: Implement miscellaneous methods (As described on Assignment requirements)
 * @author JhonAlx
 */
@SuppressWarnings("serial")
public class Notas implements Serializable
{
	//static ArrayLinearListImproved<Student> a;
	private static String fileName;
	
	/**
	 * Check the length of args[] to validate correct parameter input
	 * @param args
	 * @return true if args.length == 1, else returns false
	 */
	public static boolean checkArgs(String[] args)
	{
		if(args.length != 1)
		{
			System.out.println("Invalid parameters!");
			return false;
		}
		return true;
	}

	/**
	 * Checks the existence of a file, verifies if the specified file is a directory
	 * @param fn
	 * @return true if fn isn't an existing file, false if fn is a directory
	 */
	public static boolean checkFile(String fn)
	{
		File f = new File(fn);

		if(f.isDirectory())
		{
			System.out.println("File is a directory");
			return false;
		}

		return true;
	}

	public static boolean checkIfExists(int id, ArrayLinearListImproved<Student> a)
	{
		for(int i = 0; i < a.size(); i++)
		{
			if(a.get(i).getId() == id)
				return true;
		}
		return false;
	}
	
	public static void menu()
	{
		System.out.println("-------------------------------------------");
		System.out.println("apply_rounding (a) - Round grades == 2.9 to 3.0");
		System.out.println("add ID grade (ad) - Add a grade to a student");
		System.out.println("create ID name grades (c) - Create a new register for a student ");
		System.out.println("create ID * (c) - Create a new register and prompt for each field information");
		System.out.println("edit ID (e) - Allows to modify all accounts fields");
		System.out.println("menu (m) - Print main menu");
		System.out.println("remove ID (r) - Remove a student register");
		System.out.println("query ID (q) - Print student information");
		System.out.println("quit (qu) - Quit the system");
	}

	public static void commandParser(String[] command, ArrayLinearListImproved<Student> a)
	{
		switch(command[0])
		{
			case "apply_rounding":
				applyRounding(a);
				break;
				
			case "a":
				applyRounding(a);
				break;
				
			case "add":
				addNotes(a, command[1]);
				break;
				
			case "ad":
				addNotes(a, command[1]);
				break;
				
			case "create":
				if(command.length == 4)
					createWithParameters(command, a);
				else
					if(command.length == 3 && command[2].equalsIgnoreCase("*"))
						createWithPrompt(command[1], a);
					else
					{
						System.out.println("Command line unknown or incomplete!");
					}
				break;
				
			case "c":
				if(command.length == 4)
					createWithParameters(command, a);
				else
					if(command.length == 3 && command[2].equalsIgnoreCase("*"))
						createWithPrompt(command[1], a);
					else
					{
						System.out.println("Command line unknown or incomplete!");
						menu();
					}
				break;
				
			case "edit":
				changeName(command[1], a);
				break;
			
			case "e":
				changeName(command[1], a);
				break;
				
			case "menu":
				menu();
				break;
				
			case "m":
				menu();
				break;
				
			case "print":
				print(a);
				break;
				
			case "p":
				print(a);
				break;
				
			case "query":
				query(command[1], a);
				break;
			
			case "q":
				query(command[1], a);
				break;
				
			case "quit":
				System.out.println(fileName);
				System.out.println("Saving data file to " + fileName + "...");
				a.save(fileName);
				System.out.println("Thank you, I enjoyed serving you!");
				break;
				
			case "qu":
				System.out.println(fileName);
				System.out.println("Saving data file to " + fileName + "...");
				a.save(fileName);
				System.out.println("Thank you, I enjoyed serving you!");
				break;
				
			case "remove":
				remove(command[1], a);
				break;
				
			case "r":
				remove(command[1], a);
				break;
				
			default:
				System.out.println("Invalid command!");
				break;
		}
	}

	public static void remove(String param, ArrayLinearListImproved<Student> a)
	{
		int id = -1;
		
		try
		{
			id = Integer.parseInt(param);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid parameters! Try again.");
			return;
		}
		
		if(checkIfExists(id, a))
			for(int i = 0; i < a.size(); i++)
			{
				if(a.get(i).getId() == id)
				{
					a.remove(i);
					return;
				}
			}
		else
			System.out.println("Error - ID doesn't exists!");
	}
	
	public static void query(String param, ArrayLinearListImproved<Student> a)
	{
		int id = -1;
		
		try
		{
			id = Integer.parseInt(param);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid parameters! Try again.");
			return;
		}
		if(checkIfExists(id, a))
			for(int i = 0; i < a.size(); i++)
			{
				if(a.get(i).getId() == id)
					System.out.println(a.get(i));
			}
		else
			System.out.println("Error - ID doesn't exists");
	}
	
	public static void createWithParameters(String[] params, ArrayLinearListImproved<Student> a)
	{
		int id = -1;
		ArrayLinearListImproved<Double> ar = new ArrayLinearListImproved<>();
		String name;
		
		try
		{
			id = Integer.parseInt(params[1]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid parameters! Try again");
			return;
		}
		
		if(checkIfExists(id, a))
			System.out.println("Error - Student already exists!");
		else
		{
			name = params[2];
			
			params[3] = params[3].replaceAll("\\[+", "");
			params[3] = params[3].replaceAll("\\]+", "");
			
			String[] numbers = params[3].split("\\,");
			
			for(int i = 0; i < numbers.length; i++)
			{
				ar.add(0, Double.parseDouble(numbers[i]));
			}
			
			Student s = new Student(id, name, ar, 0);
			
			a.add(a.size(), s);
		}
	}
	
	public static void createWithPrompt(String param, ArrayLinearListImproved<Student> a)
	{
		Scanner sn = new Scanner(System.in);
		int id = -1, n;
		ArrayLinearListImproved<Double> ar = new ArrayLinearListImproved<>();
		String name;
		
		try
		{
			id = Integer.parseInt(param);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid parameter! Try again");
			return;
		}
		
		if(checkIfExists(id, a))
			System.out.println("Error - Student already exists!");
		else
		{
			System.out.print("Name: ");
			name = sn.nextLine();
			System.out.print("How many notes do you want to register? :");
			n = Integer.parseInt(sn.nextLine());
			
			for(int i = 0; i < n; i++)
			{
				double d;
				System.out.print("Please enter grade #" + (i + 1) + ": ");
				d = Double.parseDouble(sn.nextLine());
				ar.add(i, d);
			}
			
			Student s = new Student(id, name, ar, 0);
			
			a.add(a.size(), s);	
		}
	}
	
	public static void applyRounding(ArrayLinearListImproved<Student> a)
	{
		for(int i = 0; i < a.size(); i++)
		{
			ArrayLinearListImproved<Double> d = a.get(i).getGrades();
			ArrayLinearListImproved<Double> copy = new ArrayLinearListImproved<>();
			
			for(int j = 0; j < d.size(); j++)
			{
				if(d.get(j) != 2.9)
				{
					copy.add(copy.size(), d.get(j));
				}
				else
					copy.add(copy.size(), 3.0);
			}
			
			a.get(i).setGrades(copy);
		}
	}
	
    public static void print( ArrayLinearListImproved<Student> aa )
    {
		System.out.print("|\tID\t|\tName\t|\tGrades\t|\tGPA\t|\n");
		for(Student s : aa){
			System.out.print( "|\t" + s.getId() + "\t|\t" );
			System.out.print(  s.getName( ) + "\t|\t");
			System.out.print( s.getGrades( ) + "\t|\t" );
			System.out.print( s.getGpa( ) + "\t|\t\n" );
		}	
	}
	
    public static ArrayLinearListImproved<Double> addGrades( int n ) 
    {
    	ArrayLinearListImproved<Double> temp = new ArrayLinearListImproved<>();
    	Scanner s = new Scanner(System.in);
    	for(int i = 0; i < n; i++){
    		System.out.println("Digite nota numero " + (i+1));
    		double note = s.nextDouble();
    		temp.add( i, note );
    	} 
    	return temp;
    }
    
    public static void addNotes( ArrayLinearListImproved<Student> a, String param)
    {
    	int id = -1;
		Scanner sn = new Scanner(System.in);
    	
		try
		{
			id = Integer.parseInt(param);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid parameters! Try again.");
			return;
		}
		if(checkIfExists(id, a))
    	for( Student s : a ){
    		if( s.getId() == id){
    			System.out.println("Digite numero de notas a añadir:");
    			int num = Integer.parseInt(sn.nextLine());
    			if (s.getGrades()== null) 
    				s.setGrades( addGrades( num ) );
    			else{
    				for(Double grade : addGrades( num ) ) 
    					s.getGrades().add( s.getGrades().size , grade);
    			}
    		}
    	}
    }

    public static void changeName (String param, ArrayLinearListImproved<Student> a)
    {
    	int id = -1;
		Scanner sn = new Scanner(System.in);
    	
		try
		{
			id = Integer.parseInt(param);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid parameters! Try again.");
			return;
		}
		if(checkIfExists(id, a))
		{
			Scanner scan = new Scanner( System.in );
	    	String oldName = null;
	    	System.out.println( "Enter a new name: " );
	    	String name = scan.nextLine( );
	    	for( Student s : a ){
	    		if( s.getId() == id){
	    			oldName = s.getName(); 
	    				s.setName( name );    			
	    		}
	    	}
		}
		else
			System.out.println("Error - ID doesn't exists");
    }
    
    public int getMax(ArrayLinearListImproved<Student> a)
    {
    	int size = 0;
    	
    	for(int i = 0; i < a.size(); i++)
    	{
    		if(a.get(i).getGrades().size() > size)
    			size = a.get(i).getGrades().size();
    	}
    	
    	return size;
    }
    
    public void setGPA(ArrayLinearListImproved<Student> a)
    {
    	int s = getMax(a);
    	
    	for(int i = 0; i < a.size(); i++)
    	{
    		double sum = 0.0;
    		
    		for(int j = 0; j < a.get(i).getGrades().size(); j++)
    		{
    			sum += a.get(i).getGrades().get(j);
    		}
    		
    		double avg = sum / s;
    		
    		a.get(i).setGpa(avg);
    	}
    }
    
    public static void main(String[] args)
	{
		ArrayLinearListImproved<Student> ar = new ArrayLinearListImproved<>();
		
		if(checkArgs(args))
			if(checkFile(args[0]))
			{
				Scanner sn = new Scanner(System.in);
				ar.load(args[0]);
				
				fileName = args[0];
				String command = "";

				System.out.println("Welcome to the automated Grades System");
				System.out.println("Please enter one of the following commands");

				menu();

				do
				{
					System.out.print("command> ");
					command = sn.nextLine();
					String[] commandArray = command.split("\\s");
					commandParser(commandArray, ar);
				}
				while(!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q"));
			}
	}
}

@SuppressWarnings("serial")
class Student implements Serializable, Comparable<Student>
{
	private int id;
	private String name;
	private ArrayLinearListImproved<Double> grades;
	private double gpa;

	public Student() 
	{
		this.id = -1;
		this.name = "Unknown";
		this.grades = null;
		this.gpa = -1;
	}

	public Student(int id, String name, ArrayLinearListImproved<Double> grades,	double gpa) 
	{
		this.id = id;
		this.name = name;
		this.grades = grades;
		this.gpa = 0;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public ArrayLinearListImproved<Double> getGrades() 
	{
		return grades;
	}

	public void setGrades(ArrayLinearListImproved<Double> grades) 
	{
		this.grades = grades;
	}

	public double getGpa() 
	{
		return gpa;
	}

	public void setGpa(double gpa) 
	{
		this.gpa = gpa;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		int i = 1;
		sb.append("    ID : " + id);
		sb.append("\n  Name : " + name);
		sb.append("\nGrades : ");

		if(grades != null)
			for(double q : grades)
				sb.append("\n Grade " + (i++) + " : " + q);
		else
			sb.append("\nNo grades registered");

		sb.append("\nGPA : " + gpa);

		return new String(sb);
	}

	@Override
	public int compareTo(Student arg0) 
	{
		return 0;
	}
}