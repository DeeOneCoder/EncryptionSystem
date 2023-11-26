package com.Davidson.EncryptionSystem.encryptor;

import java.util.*;

//Class Declaration
public class EncryptionClass
{
	private String toEncrypt;
	private String key;

	public EncryptionClass(){}

	//Class Constructor with a String parameter
	public EncryptionClass( String enc )
	{
		toEncrypt = enc;
		key = "";
	}

	public String getToEncrypt() {
		return toEncrypt;
	}

	public void setToEncrypt(String toEncrypt) {
		this.toEncrypt = toEncrypt;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	//Method to Reverse string
	public String reverseString( String ak )
	{
		String reversed = "";//empty String to hold the reversed string
		int s = ak.length() - 1;//variable to mark the index of the last character
		for(int i = 0; i < ak.length(); i++)//loop to move through the characters
		{
			reversed += ak.charAt( s );//String now hold the last to first characters of the string
			s--;
		}
		return reversed;//String returned from the method; reversed string
	}
	
	//Method to generate random characters
	public String randomChars( String string )
	{
		String random = "";
		Random ak = new Random();
		random += string.charAt( ak.nextInt( string.length() ) );
		return random;
	}
	
	//Method to split string into two equal halves
	//and to perform recursive reversal of the strings
	public String splitString(String split)
	{
		String result = "";
		int length = split.length();
		
		if(length <= 2)//Terminate if length is < 2
			return split;

		if((length % 2 ) == 1)//if character count is not even number
		{
			split += randomChars( "`|~" );//add symbol to the word
			length += 1;//increment word character count
		}
		int middle = length/2;//find the halves in the string
		length /= 2;
		String left = splitString( reverseString( split.substring( 0, middle ) ) );//recursively split first (left) half
		String right = splitString( reverseString( split.substring( middle ) ) );//recursively split second (right) half
		
		result += left + right;//combine all strings into one
		return result;//return the combined string
	//	return encodedString;
	}
	
	//Alternative encryption algorithm to encrypt small texts
	public String mappedEncryption(String encrypt)
	{
		String encrypted = "<%";
		String mappedCap = "CDNOTESIAHMBJRXQPZKLVUYGWF";
		String mappedLow = "rxqpzklhmbjvuygwfcdnotesia";
		String origCap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String origLow = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();

		for( int i = 0; i < encrypt.length(); i++ )
		{
			if( origCap.indexOf( encrypt.charAt( i)) >= 0 )
				encrypted += mappedCap.charAt( origCap.indexOf( encrypt.charAt( i )));
			else
				if( origLow.indexOf( encrypt.charAt( i )) >= 0 )
					encrypted += mappedLow.charAt( origLow.indexOf( encrypt.charAt( i )));
					else
						encrypted += encrypt.charAt( i );
		}
		return encrypted += "%>";
		
	}
	
	
	//Method to show the beggining and end of the encrypted data
	public String padOutput(String pad)
	{
		String padded = "START";
		padded += pad;
		padded += "END";
		return padded;
	}
	
//	public String encrypt( String toEncrypt )
//	{
//		String encrypted = "";
//		encrypted = padOutput(splitString( toEncrypt ));
//		return encrypted;
//	}
	//Method to do the overall encryption
	public String encrypt( String toEncrypt )
	{
		String encrypted = "";
		String doublyEncrypted = "";
		//A condition to know that the string is not already encrypted
		if(toEncrypt.substring( 0, 5 ).equals( "START" ) || toEncrypt.substring( 0, 2).equals( "<%"))
			return "Text is already encrypted. Can not encrypt twice. Click Back to Continue";
			else
			{
				encrypted = mappedEncryption( toEncrypt );
				doublyEncrypted = padOutput(splitString( encrypted ));
			}
		return doublyEncrypted;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your Text Below");
		String enc = scanner.nextLine();
		System.out.println(new EncryptionClass(enc).encrypt(enc));
	}

	
}