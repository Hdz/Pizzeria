/**
 * 
 */
package dao;

import java.io.*;
import java.util.*;

import pizzeria.exception.TechnicalException;


public class GestionFichier
{

	public File file;
	public PrintWriter fileWriter;
	public BufferedReader fileReader;
	
	
	/**
	 * Constructor by default
	 * Use a "save.txt" file
	 */
	public GestionFichier (String nomFichier)
	{
		try
		{
			file = new File (nomFichier);
			
			if (file.exists () == false)
			{
				file.createNewFile ();
			}
		} 
		catch (IOException e)
		{
			throw new TechnicalException(e);

		}
	}
	
	public boolean isEmpty ()
	{
		return file.length() < 1;
	}
	
	/**
	 * ecriture : Prend une List <String> 
	 * pour écrire dans un fichier ligne par ligne
	 * @param listString
	 */
	public void ecriture (List <String> listString)
	{
		try
		{
			fileWriter = new PrintWriter (new FileWriter (file));
			
			for (String s : listString)
			{
				fileWriter.println (s);
			}
			
			fileWriter.flush ();
			fileWriter.close ();
		} 
		catch (IOException e)
		{
			throw new TechnicalException(e);

		}
	}
	
	/**
	 * lecture : Retourne une List <String> 
	 * contenant un fichier entier ligne par ligne
	 * @return
	 */
	public List <String> lecture () 
	{
		try
		{
			String line = null;
			List <String> records = new ArrayList <String> ();
			fileReader = new BufferedReader (new FileReader (file));	
			
			while ((line = fileReader.readLine ()) != null)
			{
				records.add(line);
			}
			
			fileReader.close ();
			return records;
		} 
		catch (IOException e)
		{
			throw new TechnicalException(e);
		} 
	}
}