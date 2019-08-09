import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class TestingARFF {
	public static void main (String [] args) throws Exception {
		String [] files1 = {
				"assr1.test.gz"
		};
		
		JSONParser parser = new JSONParser();
		String line;
		String combinedLine = "";
		for (String file: files1) {
			try
			{
				BufferedReader br= new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))));
				//BufferedWriter bw = new BufferedWriter(new FileWriter("TestJoplinIDs.txt"));

				while ((line = br.readLine())!=null)
				{
					//System.out.println(line);
					//JSONObject obj = (JSONObject) parser.parse(line);
					combinedLine += line;
					combinedLine += "\n";
					//String id = (String) obj.get("id_str");
					//bw.write(id + "\n");
				}
				br.close();
				
				JSONObject obj = (JSONObject) parser.parse(combinedLine);
				String tweets = (String) obj.get("events");
				System.out.println(tweets);
				//JSONArray array1 = (JSONArray) array.get(0);
				// Iterator iterator = array.iterator();
		         //   while (iterator.hasNext()) {
		        //        System.out.println(iterator.next());
		       //         
		       //     }
		       //     bw.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
				}
		/*
		String[] files2 = {
				"2011_Joplin_tornado.json.gz"		
		};
		
		JSONParser parser = new JSONParser();

		for (String file: files2) {
			try
			{
				BufferedReader br= new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))));
				BufferedWriter bw = new BufferedWriter(new FileWriter("TestJoplinIDs.txt"));
				String line;
				while ((line = br.readLine())!=null)
				{
					//System.out.println(line);
					JSONObject obj = (JSONObject) parser.parse(line);
					
					String id = (String) obj.get("id_str");
					bw.write(id + "\n");
				}
				br.close();
				bw.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
				}
	*/
	}
}
