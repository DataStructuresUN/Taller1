package unal.datastructures;

import java.io.*;

/**
 * TODO: Implement menu
 * TODO: Implement command parser
 * TODO: Implement miscellaneous methods (As described on Assignment requirements)
 * @author JhonAlx
 */


public class Notas
{
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
	
	public static void menu()
	{
		System.out.println("----MENU----");
		System.out.println("add ID grade - Add a grade to a student");
		System.out.println("apply_gpa - Apply GPA for all registered students");
		System.out.println("create ID name - Create a new register for a student and prompt for grades information");
		System.out.println("create ID * - Create a new register and prompt for each field information");
		System.out.println("edit ID - Allows to modify all accounts fields");
		System.out.println("query ID - Print student information");
		System.out.println("menu - Print main menu");
		System.out.println("remove ID - Remove a student register");
		System.out.println("quit - Quit the system");
	}
	
	public static void commandParser()
	{
		
	}
	
	public static void main(String[] args)
	{
		if(checkArgs(args))
			if(checkFile(args[0]))
				menu(); //Testing methods
		
		//TODO: Erase this block of testing
		//Start : Testing Serialization
//		Student s = new Student();
//		ArrayLinearListImproved<Student> a = new ArrayLinearListImproved<Student>();
//		
//		a.add(0, s);
//		
//		a.save(args[0]);
		//End : Testing serialization 
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
		
		//TODO: Uncomment this for-each, I commented it to test properly serialization without Exceptions for having an empty arraylist
//		for(double q : grades)
//			sb.append("\n Grade " + (i++) + " : " + q);
		
		sb.append("\nGPA : " + gpa);
		
		return new String(sb);
	}

	@Override
	public int compareTo(Student arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}