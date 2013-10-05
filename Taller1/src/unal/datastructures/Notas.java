package unal.datastructures;

import java.io.*;

/**
 * TODO: Implement methods to serialize / deserialize objects
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
	
	public static void main(String[] args)
	{
		if(checkArgs(args))
			if(checkFile(args[0]))
				System.out.println("OK"); //Testing methods
		
		//TODO: Erase this block of testing
		//Start : Testing Serialization
		Student s = new Student();
		try 
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(args[0]));
			oos.writeObject(s);
			oos.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Student q = new Student();
		
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]));
			q = (Student) ois.readObject();
			ois.close();
			System.out.println(q);
		}
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		//End : Testing serialization 
	}
}

class Student implements Serializable
{
	private static final long serialVersionUID = -8527297899691693432L;
	
	private int id;
	private String name;
	private ArrayLinearList<Double> grades = new ArrayLinearList<>();
	private double gpa;
	
	public Student() 
	{
		this.id = -1;
		this.name = "Unknown";
		this.grades = null;
		this.gpa = -1;
	}
	
	public Student(int id, String name, ArrayLinearList<Double> grades,	double gpa) 
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
	
	public ArrayLinearList<Double> getGrades() 
	{
		return grades;
	}
	
	public void setGrades(ArrayLinearList<Double> grades) 
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
}