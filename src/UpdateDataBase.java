//import java.sql.*;


class UpdateDataBase {
	//Statement st;
	void insertIntoDataBase(String domainName, String userName, String password)
	{
		if(domainName.isEmpty())
			domainName = new String(" ");
		if(password.isEmpty())
			password = new String(" ");
		if(userName.isEmpty())
			userName = new String(" ");
		String NA = new String(" ");
		try
		{
			TableData.st = TableData.con.createStatement();
			String insertCmd = new String("insert into result values('"+encryptIt(domainName)+"','"+encryptIt(userName)+"','"+encryptIt(password)+"','"+encryptIt(NA)+"','"+encryptIt(NA)+"','"+encryptIt(NA)+"','"+encryptIt(NA)+"')");
			TableData.st.executeUpdate(insertCmd);
			//st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	void insertIntoDataBase(String typeOfCard, String cardNumber, String cardName, String nameOnCard, String month, String year, String cvv, String pin)
	{
		if(cardName.isEmpty())
			cardName = " ";
		if(cardNumber.isEmpty())
			cardNumber = " ";
		if(nameOnCard.isEmpty())
			nameOnCard = " ";
		if(cvv.trim().length() == 0)
			cvv = " ";
		if(pin.trim().length() == 0)
			pin = " ";
		
		String expiryDate = new String(month+"/"+year);
		try
		{
			TableData.st = TableData.con.createStatement();
			String insertCmd = new String("insert into result values('"+encryptIt(cardNumber)+"','"+encryptIt(nameOnCard)+"','"+encryptIt(pin)+"','"+encryptIt(expiryDate)+"','"+encryptIt(cvv)+"','"+encryptIt(cardName)+"','"+encryptIt(typeOfCard)+"')");
			TableData.st.executeUpdate(insertCmd);
			//st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void deleteFromDataBase(String[] rowValues)
	{
		//System.out.println("delete from result where WEBSITEORCARD = '"+rowValues[0]+"' and USERNAME = '"+rowValues[1]+"' and PASSWORD = '"+rowValues[2]+"' and EXPIRYDATE = '"+rowValues[3]+"' and CVV = '"+rowValues[4]+"' and CARDNAME = '"+rowValues[5]+"' and TYPEOFCARD = '"+rowValues[6]+"'");

		try
		{
			TableData.st = TableData.con.createStatement();
			String deleteCmd = new String("delete from result where WEBSITEORCARD = '"+encryptIt(rowValues[0])+"' and USERNAME = '"+encryptIt(rowValues[1])+"' and PASSWORD = '"+encryptIt(rowValues[2])+"' and EXPIRYDATE = '"+encryptIt(rowValues[3])+"' and CVV = '"+encryptIt(rowValues[4])+"' and CARDNAME = '"+encryptIt(rowValues[5])+"' and TYPEOFCARD = '"+encryptIt(rowValues[6])+"'");
			TableData.st.executeUpdate(deleteCmd);
			TableData.con.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	String encryptIt(String str)
	{
		String result = new String();
		try
		{
			//System.out.println("Plain text: "+str);
			result = EncryptData.encrypt(str);
			//System.out.println("Encrpted text: "+result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return(result);
	}
}
