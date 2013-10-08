package unal.datastructures;

import java.io.*;
import java.util.*;

/**
 * TODO: Implement menu
 * TODO: Implement command parser
 * TODO: Implement miscellaneous methods (As described on Assignment requirements)
 * @author JhonAlx
 */


public class Notas
{
	private static ArrayLinearListImproved<Student> a;
	
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

	public static boolean checkIfExists(int id)
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
		System.out.println("add ID grade - Add a grade to a student");
		System.out.println("apply_rounding - Apply GPA for all registered students");
		System.out.println("create ID name - Create a new register for a student and prompt for grades information");
		System.out.println("create ID * - Create a new register and prompt for each field information");
		System.out.println("edit ID - Allows to modify all accounts fields");
		System.out.println("query ID - Print student information");
		System.out.println("menu - Print main menu");
		System.out.println("remove ID - Remove a student register");
		System.out.println("quit - Quit the system");
	}

	public static ArrayLinearListImproved<Student> loadData(String fn)
	{
		ArrayLinearListImproved<Student> a = new ArrayLinearListImproved<>();
		a.load(fn);

		return a;
	}

	public static void commandParser(String[] command)
	{
		switch(command[0])
		{
			case "apply_rounding":
				System.out.println("Rounding");
				break;
			case "a":
				System.out.println("Rounding");
				break;
			case "create":
				if(command.length == 4)
					createWithParameters(command);
				else
					if(command.length == 3 && command[2].equalsIgnoreCase("*"))
						createWithPrompt(command[1]);
					else
					{
						System.out.println("Command line unknown or incomplete!");
						menu();
					}
				break;
			case "c":
				System.out.println("Creating");
				break;
			case "quit":
				break;
			case "q":
				break;
			default:
				System.out.println("Invalid command");
				menu();
				break;
		}
	}

	public static void createWithParameters(String[] params)
	{
		for(String q : params)
			System.out.println(q);
	}
	
	public static void createWithPrompt(String param)
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
		}
		
		if(checkIfExists(id))
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
//	public static void applyRounding(ArrayLinearListImproved<Student> a)
//	{
//		for(int i = 0; i < a.size(); i++)
//		{
//			ArrayLinearListImproved<Double> d = a.get(i).getGrades();
//			
//			for(int j = 0; j < d.size(); j++)
//			{
//				if(d.get(j) == 2.9)
//					d.element[j] += 0.1;
//			}
//		}
//	}
	
	public static void main(String[] args)
	{
//		ArrayLinearListImproved<Double> d = new ArrayLinearListImproved<>();
//		d.add(0, 2.9);
//		d.add(1, 2.9);
//		d.add(2, 2.5);
//		d.add(3, 2.5);
//		d.add(4, 5.0);
//		d.add(5, 2.9);
//		
//		Student s = new Student(0, "pepito", d, 0);
//		
//		ArrayLinearListImproved<Student> a = new ArrayLinearListImproved<>();
//		
//		a.add(0, s);
//		
//		applyRounding(a);
//		
//		System.out.println(a);
		
		
		if(checkArgs(args))
			if(checkFile(args[0]))
			{
				Scanner sn = new Scanner(System.in);
				a = loadData(args[0]);
				String command = "";

				System.out.println("Welcome to the automated Grades System");
				System.out.println("Please enter one of the following commands");

				menu();

				System.out.print("command> ");
				command = sn.nextLine();

				String[] commandArray = command.split("\\s");
				commandParser(commandArray);
				
				for(Student q : a)
					System.out.println(q);
			}
	}
}

class Student implements Serializable, Comparable<Student>
{
	private static final long serialVersionUID = -8527297899691693432L;

	private int id;
	private String name;
	private ArrayLinearListImproved<Double> grades = new ArrayLinearListImproved<>();
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
	public int compareTo(Student arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}