package testcases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class TestRequest {
	
	public static void main(String[] args) throws IOException, FilloException {
		
		URL url = new URL ("https://reqres.in/api/users");	
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		String jsonInputString = null;
		
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection("C:\\MURALI\\WORKSPACE\\JAVA\\1\\Framework_Draft\\Support_Files\\RequestTemplate.xlsx");
		String strQuery="Select * from Sheet1 where TestCaseName='TC_001'";
		Recordset recordset=connection.executeQuery(strQuery);

		while(recordset.next()){
		System.out.println(recordset.getField("JSON_Request"));
		jsonInputString = recordset.getField("JSON_Request");
		}

		recordset.close();
		connection.close();
		
	
		try(OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input,0,input.length);
			
		//	os.write(input, 0, input.length)           
			}
		System.out.print(con.getResponseCode());
		
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				System.out.println("Response:"+response.toString());
				}
		}

}
