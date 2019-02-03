package csi311;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PackageParser {
	
	private ArrayList<String> invalidPackages;
	private ArrayList<String> packagesExceedingLimit;
	private HashMap<String, Integer> validPackages;
	
	public PackageParser()
	{
		this.invalidPackages = new ArrayList<>();
		this.packagesExceedingLimit = new ArrayList<>();
		this.validPackages = new HashMap<>();
	}
	
	public HashMap<String, Integer> getValidPackages()
	{
		return this.validPackages;
	}
	public ArrayList<String> getInvalidPackages()
	{
		return this.invalidPackages;
	}
	
	public boolean getCommaCount(String currLine)
	{
		int i = 0, commaCount = 0;
		while(i < currLine.length()) {
			if(currLine.charAt(i) == ',')
				commaCount++;
			
			i++;
		}
		
		return commaCount == 2;
	}
	
	public boolean validateAddress(String address)
	{
		if(address.toLowerCase().endsWith("st") || address.toLowerCase().endsWith("st.") || address.toLowerCase().endsWith("street"))
		{
			return true;
		}
		else if(address.toLowerCase().endsWith("ave") || address.toLowerCase().endsWith("ave.") || address.toLowerCase().endsWith("avenue"))
			return true;
		else if(address.toLowerCase().endsWith("broadway") || address.toLowerCase().endsWith("bway") || address.toLowerCase().endsWith("b'way"))
			return true;
		
		return false;
	}
	
	public boolean validateTeam(String address)
	{
		if(address.contains("Ave") || address.contains("Avenue"))
		{
			return true;
		}
		else if(address.contains("Broadway"))
			return true;
		return false;
	}
	public boolean lineParser(String line) throws Exception
	{
		// Leading whitespace and trailing whitespace has already been trimmed.
		int i = 0;
		int currentPosition = 0;
		while(i < line.length())
		{
			if(currentPosition < 3)
			{
				if(Character.isDigit(line.charAt(i)))
				{
					currentPosition++;
					i++;
				}
				else if(Character.isWhitespace(line.charAt(i)))
					throw new Exception("Invalid");
				
				else if(!Character.isDigit(line.charAt(i)))
					throw new Exception("Invalid");
			}
			else if(currentPosition == 3 || currentPosition == 7)
			{
				if(line.charAt(i) == '-')
				{
					currentPosition++;
					i++;
				}
				else if(Character.isWhitespace(line.charAt(i)))
					throw new Exception("Invalid");
				else
					throw new Exception("Invalid");
			}
			else if(currentPosition > 3 && currentPosition < 7)
			{
				if(Character.isLetter(line.charAt(i)))
				{
					currentPosition++;
					i++;
				}
				else if(Character.isWhitespace(line.charAt(i)))
					throw new Exception("Invalid");
				else
					throw new Exception("Invalid");
			}
			else if(currentPosition > 7 && currentPosition < 12)
			{
				if(Character.isDigit(line.charAt(i)))
				{
					currentPosition++;
					i++;
				}
				else if(Character.isWhitespace(line.charAt(i)))
					throw new Exception("Invalid");
				
				else if(!Character.isDigit(line.charAt(i)))
				{
					throw new Exception("Invalid");
				}
			}
			else if(currentPosition > 12)
				throw new Exception("Invalid Package Id.");
		}
		return true;
	}
}
