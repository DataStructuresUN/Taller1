package unal.datastructures;

import java.io.*;
import java.util.*;

/**
 * TODO: Implement miscellaneous methods (As described on Assignment requirements)
 * @author JhonAlx
 */
public class Notas implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	/**
	 * Checks if an ID already exists in the ArrayLinearListImproved<Student> 
	 * @param id
	 * @param a
	 * @return true if ID exists in the ArrayLinearListImproved<Student>
	 */
	public static boolean checkIfExists(int id, ArrayLinearListImproved<Student> a)
	{
		for(int i = 0; i < a.size(); i++)
		{
			if(a.get(i).getId() == id)
				return true;
		}
		return false;
	}
	
	/**
	 * Prints the main menu
	 */
	public static void menu()
	{
		System.out.println("-------------------------------   Academic Information System    -----------------------------------");
		System.out.println();
		System.out.println("apply_rounding (a) - Round grades == 2.9 to 3.0");
		System.out.println("apply_gpa (g) - Calculate GPA for all registered students");
		System.out.println("add ID (d) - Add a grade to a student");
		System.out.println("create ID name [grades (Comma separated list eg: [1,2,3])] (c) - Create a new register for a student");
		System.out.println("create ID * (c) - Create a new register and prompt for each field information");
		System.out.println("edit ID (e) - Allows to modify all accounts fields");
		System.out.println("menu (m) - Print main menu");
		System.out.println("remove ID (r) - Remove a student register");
		System.out.println("query ID (q) - Print student information");
		System.out.println("total (t) - Prints the course average");
		System.out.println("quit (x) - Quit the system");
	}

	public static void totalAvg(ArrayLinearListImproved<Student> a)
	{
		double sum = 0;
		
		for(int i = 0; i < a.size(); i++)
		{
			sum += a.get(i).getGpa();
		}
		
		System.out.print("Total course avg: ");
		System.out.printf("%.2f",sum / a.size());
		System.out.println();
	}
	
	public static void commandParser(String[] command, ArrayLinearListImproved<Student> a)
	{
		switch(command[0])
		{
			case "apply_rounding":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
					applyRounding(a);
				break;
				
			case "a":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
					applyRounding(a);
				break;
				
			case "apply_gpa":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
					setGPA(a);
				break;
			
			case "g":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
					setGPA(a);
				break;
				
			case "add":
				if(command.length < 2 || command.length > 2)
					System.out.println("Command line unknown or incomplete!");
				else
					addNotes(a, command[1]);
				break;
				
			case "d":
				if(command.length < 2 || command.length > 2)
					System.out.println("Command line unknown or incomplete!");
				else
					addNotes(a, command[1]);
				break;
				
			case "create":
				if(command.length == 4)
					createWithParameters(command, a);
				else
					if(command.length == 3 && command[2].equalsIgnoreCase("*"))
						createWithPrompt(command[1], a);
					else
						System.out.println("Command line unknown or incomplete!");
				break;
				
			case "c":
				if(command.length == 4)
					createWithParameters(command, a);
				else
					if(command.length == 3 && command[2].equalsIgnoreCase("*"))
						createWithPrompt(command[1], a);
					else
						System.out.println("Command line unknown or incomplete!");
				break;
				
			case "edit":
				if(command.length < 2  || command.length > 2)
					System.out.println("Command line unknown or incomplete!");
				else
					changeName(command[1], a);
				break;
			
			case "e":
				if(command.length < 2  || command.length > 2)
					System.out.println("Command line unknown or incomplete!");
				else
					changeName(command[1], a);
				break;
				
			case "menu":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
					menu();
				break;
				
			case "m":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
					menu();
				break;
				
			case "print":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
					print(a);
				break;
				
			case "p":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
					print(a);
				break;
				
			case "query":
				if(command.length < 2  || command.length > 2)
					System.out.println("Command line unknown or incomplete!");
				else
					query(command[1], a);
				break;
			
			case "q":
				if(command.length < 2  || command.length > 2)
					System.out.println("Command line unknown or incomplete!");
				else
					query(command[1], a);
				break;
				
			case "quit":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
				{
					System.out.println("Saving data file to " + fileName + "...");
					saveData(fileName, a);
					System.out.println("Thank you, I enjoyed serving you!");
				}
				break;
				
			case "x":
				if(command.length > 1)
					System.out.println("Command line unknown or incomplete!");
				else
				{
					System.out.println("Saving data file to " + fileName + "...");
					saveData(fileName, a);
					System.out.println("Thank you, I enjoyed serving you!");
				}
				break;
				
			case "remove":
				if(command.length < 2  || command.length > 2)
					System.out.println("Command line unknown or incomplete!");
				else
					remove(command[1], a);
				break;
				
			case "r":
				if(command.length < 2  || command.length > 2)
					System.out.println("Command line unknown or incomplete!");
				else
					remove(command[1], a);
				break;
				
			case "total":
				totalAvg(a);
				break;
				
			case "t":
				totalAvg(a);
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
				ar.add(ar.size(), Double.parseDouble(numbers[i]));
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
    		System.out.println("Enter grade #" + (i+1));
    		double note = Double.parseDouble(s.nextLine());
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
    			System.out.println("Enter number of notes to add: ");
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
    
    public static int getMax(ArrayLinearListImproved<Student> a)
    {
    	int size = 0;
    	
    	for(int i = 0; i < a.size(); i++)
    	{
    		if(a.get(i).getGrades().size() > size)
    			size = a.get(i).getGrades().size();
    	}
    	
    	return size;
    }
    
    public static void setGPA(ArrayLinearListImproved<Student> a)
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
    
    public static void saveData(String fn, ArrayLinearListImproved<Student> a)
    {
    	try
		{
			PrintWriter pw = new PrintWriter(new File(fn));
			
			pw.println(a.size());
			for(int i = 0; i < a.size(); i++)
			{
				pw.println(a.get(i).getId() + "|" + a.get(i).getName() + "|" + a.get(i).getGpa());
				pw.println(a.get(i).getGrades().size());
				
				StringBuilder sb = new StringBuilder();
				for(double q : a.get(i).getGrades())
					sb.append(q + "|");
				
				pw.println(sb.toString().substring(0, sb.toString().length() - 1));
			}
			
			pw.close();
			
			System.out.println("Done!");
		}
    	catch(IOException e)
    	{
			System.err.println("An error ocurred while saving");
		}
    }

    public static ArrayLinearListImproved<Student> readData(String fn)
    {
    	ArrayLinearListImproved<Student> a = new ArrayLinearListImproved<>();
    	
    	try
    	{
    		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fn), "ASCII"));
    		String z;
    		
    		while((z = br.readLine()) != null)
    		{
    			int n = Integer.parseInt(z);
    			for(int i = 0; i < n; i++)
    			{
    				String[] l = br.readLine().split("\\|");
    				Student s = new Student(Integer.parseInt(l[0]), l[1], Double.parseDouble(l[2]));
    				int dSize = Integer.parseInt(br.readLine());
    				ArrayLinearListImproved<Double> d = new ArrayLinearListImproved<>();
    				l = br.readLine().split("\\|");
    				
    				for(String q : l)
    					d.add(d.size(), Double.parseDouble(q));
    				
    				s.setGrades(d);
    				
    				a.add(a.size(), s);
    			}
    		}
    		
    	}
    	catch(IOException e)
    	{
    		System.out.println();
    	}
    	
    	return a;
    }
    
    public static void main(String[] args)
	{
		ArrayLinearListImproved<Student> ar = new ArrayLinearListImproved<>();
//		ar.add(0, new Student());
//		ar.save(args[0]);
		if(checkArgs(args))
			if(checkFile(args[0]))
			{
				Scanner sn = new Scanner(System.in);
				fileName = args[0];
				ar = readData(fileName);
				
				String command = "";

				System.out.println("Welcome to the automated Grades System");
				System.out.println("Please enter one of the following commands");

				menu();

				do
				{
					System.out.println();
					System.out.print("command> ");
					command = sn.nextLine();
					String[] commandArray = command.split("\\s");
					
					commandArray[0] = commandArray[0].toLowerCase();
					commandParser(commandArray, ar);
				}
				while(!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("x"));
			}
	}
}

class Student implements Serializable, Comparable<Student>
{
	
	private static final long serialVersionUID = 1L;
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
		this.gpa = gpa;
	}

	public Student(int id, String name,double gpa) 
	{
		this.id = id;
		this.name = name;
		this.gpa = gpa;
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