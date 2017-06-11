import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.SQLException;

class TableData {
	static Connection con;// = null;
	static Statement st = null;
	static ResultSet rs = null;
	static String uname,password;
	//Vector<String> colHeads = new Vector<String>();
	//Vector<Object> row = new Vector<Object>();
	Vector<Vector<Object>> data = new  Vector<Vector<Object>>();
	public void compute()
	{
		try
		{
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection("jdbc:oracle:thin:"+uname+"/"+password+"@localhost",uname,password);
			st = con.createStatement();
			rs = st.executeQuery("select * from result");
			
			while(rs.next()) {
				Vector<Object> row = new Vector<Object>();
				//row.add(rs.getInt("Sno"));
				for(int i = 1;i <= 7; i++)
				{
					String tableVal = rs.getString(i);
					//System.out.println(tableVal);
					if(tableVal.equals("null"))
						row.add("-");
					else
						row.add(tableVal);
					//System.out.println(row.get(i-1));
				}
				data.add(row);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		//catch (ClassNotFoundException e)
		//{
		//	e.printStackTrace();
		//}
		finally
		{
			try{
				EncryptData.password = password;
				
				//EncryptData.data = this.data;
				//EncryptData.mainEncryptData();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//EncryptData.setSecretKey(password);
			DisplayTable.data = decryptIt(data);
			DisplayTable.mainDisplayTable();
		}
	}
	Vector<Vector<Object>> decryptIt(Vector<Vector<Object>> temp)
	{
		String[][] encryptedTexts = new String[temp.size()][7];
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		
		try
		{
			for(int i = 0;i < temp.size();i++)
			{
				Vector<Object> row = new Vector<Object>();
				for(int j = 0;j < temp.get(i).size();j++)
				{
					encryptedTexts[i][j] = temp.get(i).get(j).toString();
					row.add(EncryptData.decrypt(encryptedTexts[i][j]));
				}
				result.add(row);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return(result);
	}
}