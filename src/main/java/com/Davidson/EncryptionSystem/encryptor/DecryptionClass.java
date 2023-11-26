package com.Davidson.EncryptionSystem.encryptor;




import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.*;

public class DecryptionClass
{
	private String toDecrypt;
	private String key = "";

	public DecryptionClass(){}
	
	//Class Constructor with a String parameter
	public DecryptionClass(String encode )
	{
		toDecrypt = encode;
		key = "";
	}



	public void setToDecrypt(String toDecrypt) {
		this.toDecrypt = toDecrypt;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	//Method to get the text to decrypt
	public String getToDecrypt()
	{
		return toDecrypt;
	}

	public String unpadString(String toUnpad)
	{
		String unpadded = toUnpad.substring( 5, toUnpad.lastIndexOf("END"));
		return unpadded;
	}
	//Method to split string into two equal halves
	//and to perform recursive reversal of the strings
	public String splitString(String split)
	{
		String result = "";
		int length = split.length();
		
		if(length <= 2)//Terminate if length is <= 2
			return split;
		
		if((length % 2) == 1)
			return "Invalid Input; Please recopy the file, some texts are missing or documents contains alien characters.";
			
		int middle = length/2;//find the halves in the string
		length = length/2;
		String left = splitString( reverseString( split.substring( 0, middle ) ) );//recursively split first (left) half
		String right = splitString( reverseString( split.substring( middle ) ) );//recursively split second (right) half
		
		result += left + right;//combine all strings into one
		return result;//return the combined string
	}
		
		
	//Method to Reverse string
	public String reverseString( String ak )
	{
		String reversed = "";//empty string to hold the reversed string
		int s = ak.length() - 1;//variable to mark the index of the last character
		for(int i = 0; i < ak.length(); i++)//loop to move through the characters
		{
			reversed += ak.charAt( s );//String now hold the last to first characters of the string
			s--;
		}
		return reversed;//String returned from the method; reversed string
	}

	
	//Method to clean string (remove symbols)
	public String cleanString(String toDecrypt)
	{
		int length = toDecrypt.length();
		String result = "";
		int count = 0;
		while( count < length )
		{
			if(toDecrypt.charAt(count) == '~')
				count += 1;
				else if(toDecrypt.charAt(count) == '|')
					count += 1;
					else if(toDecrypt.charAt(count) == '`')
						count += 1;
						else
							{
								result += toDecrypt.charAt(count);
								count++;
							}
		}
		return result;
	}
	
	
	//another decryption method to decrypt smaller encrypted text
	//Alternative encryption algorithm to encrypt small texts
	public String mappedDecryption(String encrypted)
	{
		String toDecrypt = encrypted.substring( 2, encrypted.lastIndexOf( '%'));
		String decrypted = "";
		String mappedCap = "CDNOTESIAHMBJRXQPZKLVUYGWF";
		String mappedLow = "rxqpzklhmbjvuygwfcdnotesia";
		String origCap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String origLow = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
		
		for( int i = 0; i < toDecrypt.length(); i++ )
		{

			if( mappedCap.indexOf( toDecrypt.charAt( i )) >= 0 )
				decrypted += origCap.charAt( mappedCap.indexOf( toDecrypt.charAt( i )));
			else
				if( mappedLow.indexOf( toDecrypt.charAt( i )) >= 0 )
					decrypted += origLow.charAt( mappedLow.indexOf( toDecrypt.charAt( i )));
					else
						decrypted += toDecrypt.charAt( i );
		}
		return decrypted;
		
	}

	public String[] extractPassKey( String text )
	{
		DecryptionClass decClass = new DecryptionClass( );
		Scanner scanner = new Scanner(System.in);
		String[] result = new String[2];
		String enc = text;
		String keyDecrypt = enc.substring( 0, enc.indexOf( '?' ));
		System.out.println("Enter decryption key: ");
		String enteredKey = scanner.next();
		result[0] = decClass.mappedDecryption(keyDecrypt);
		result[1] = enteredKey;
		return result;
	}

	//Method to do the overall decryption
	public String decrypt( String toDecrypt )
	{
		String doublyDecrypted = "";
		if( !toDecrypt.substring( 0, 5).equals( "START" ))
			return "The text has not been encrypted/ or it was encrypted using another algorithm";
			else
			{
				String withoutPadding = unpadString( toDecrypt );
				String decrypted = "";
				decrypted = cleanString( splitString( withoutPadding) );				
				doublyDecrypted = mappedDecryption( decrypted );				
			}

		return doublyDecrypted;
	}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String enc = scanner.nextLine();
        System.out.println(new DecryptionClass(enc).decrypt(enc));
    }
	
}	