import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.*;
import weka.classifiers.functions.SMO;

public class ExportARFF {

	public static void main (String [] args) throws Exception {

		// Files to be read in, containing the categories
		String[] files1 = {
				"assr1.test.gz",
				"assr2.test.gz",
				"assr3.test.gz",
				"assr4.test.gz",
				"assr5.test.gz",
				"assr6.test.gz"
		};

		// Loading in the lexicon and getting the size of the lexicon
		String lexicon = "event.lexicon";
		ArrayList<String> lexiconList = new ArrayList<String>();

		try {
			BufferedReader br= new BufferedReader(new FileReader(lexicon));
			String line;
			while ((line = br.readLine())!=null) {
				lexiconList.add(line);
			}
			br.close();
		} 
		catch (Exception e) { e.printStackTrace(); }

		int lexiconCount = lexiconList.size();

		// Preparing ArrayLists of each scenario....
		ArrayList<ArrayList<String>> arrayListScenario = new ArrayList<ArrayList<String>> ();
		//ArrayList<ArrayList<BigInteger>> continuingNewsIDList = new ArrayList<ArrayList<BigInteger>>();
		ArrayList<BigInteger> continuingNewsID = new ArrayList<BigInteger>();
		ArrayList<BigInteger> completeID = new ArrayList<BigInteger>();
		ArrayList<ArrayList<ArrayList>> arffListComplete = new ArrayList<ArrayList<ArrayList>>();
		//ArrayList<ArrayList<String>> wordListComplete = new ArrayList<ArrayList<String>> ();
		ArrayList<Integer> wordLexiconList = new ArrayList<Integer>();
		ArrayList<Integer> tweetList = new ArrayList<Integer>();
		ArrayList<String> wordList = new ArrayList<String>();

		// How many scenarios are you loading? Change amount for SteppingStone!
		int steppingStone;

		//BufferedWriter bwa = new BufferedWriter(new FileWriter("ContinuingNewsIDs2.txt"));
		for (steppingStone = 0; steppingStone < 15; steppingStone++)
		{

			//Set<String> lexiconList = new HashSet<String>();			
			


			for (String file: files1) {
				try
				{
					BufferedReader br= new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))));
					String line;					
					boolean foundFlood = false;
					//boolean isItContinue = false;

					BigInteger possibleID = new BigInteger("0");
					while ((line = br.readLine())!=null) {

						// Assr1
						switch (steppingStone)
						{
						case 0:
							if (line.equals("{\"eventid\": \"joplinTornado2011\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"typhoonYolanda2013\","))
								foundFlood = false;
							break;

						case 1:
							if (line.equals("{\"eventid\": \"guatemalaEarthquake2012\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"bostonBombings2013\","))
								foundFlood = false;
							break;

						case 2:
							if (line.equals("{\"eventid\": \"italyEarthquakes2012\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"queenslandFloods2013\","))
								foundFlood = false;
							break;

						case 3:
							if (line.equals("{\"eventid\": \"philipinnesFloods2012\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"albertaFloods2013\","))
								foundFlood = false;
							break;

						case 4:
							if (line.equals("{\"eventid\": \"albertaFloods2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"nepalEarthquake2015\","))
								foundFlood = false;
							break;

						case 5:
							if (line.equals("{\"eventid\": \"australiaBushfire2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"philipinnesFloods2012\","))
								foundFlood = false;
							break;

						case 6:
							if (line.equals("{\"eventid\": \"bostonBombings2013\","))
								foundFlood = true;		

							if (line.equals("{\"eventid\": \"flSchoolShooting2018\","))
								foundFlood = false;
							break;

						case 7:
							if (line.equals("{\"eventid\": \"manilaFloods2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"parisAttacks2015\","))
								foundFlood = false;
							break;

						case 8:
							if (line.equals("{\"eventid\": \"queenslandFloods2013\","))
								foundFlood = true;
							
							if (line.equals("{\"eventid\": \"nepalEarthquake2015S3\","))
								foundFlood = false;
							
							if (line.equals("{\"eventid\": \"fireColorado2012\","))
								foundFlood = false;
							break;

						case 9:
							if (line.equals("{\"eventid\": \"typhoonYolanda2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"queenslandFloods2013\","))
								foundFlood = false;
							break;

						case 10:
							if (line.equals("{\"eventid\": \"chileEarthquake2014\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"joplinTornado2011\","))
								foundFlood = false;
							break;

						case 11:
							if (line.equals("{\"eventid\": \"typhoonHagupit2014S2\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"typhoonHagupit2014\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"typhoonHagupit2014S1\","))
								foundFlood = true;
							break;

						case 12:
							if (line.equals("{\"eventid\": \"nepalEarthquake2015S3\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"nepalEarthquake2015\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"typhoonHagupit2014S2\","))
								foundFlood = false;

							if (line.equals("{\"eventid\": \"nepalEarthquake2015\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"typhoonHagupit2014S1\","))
								foundFlood = false;
							break;

						case 13:
							if (line.equals("{\"eventid\": \"parisAttacks2015\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"italyEarthquakes2012\","))
								foundFlood = false;
							break;

						default:
							if (line.equals("{\"eventid\": \"flSchoolShooting2018\","))
								foundFlood = true;							

						}

						if (foundFlood)
						{
							if (line.contains("eventid"))
								System.out.println(line);

							if(line.contains("postID"))
							{
								String [] extractIDString = line.split("\"postID\" : \"");
								extractIDString[1] = extractIDString[1].substring(0, extractIDString[1].length() - 2);								
								possibleID = new BigInteger(extractIDString[1]);
							}

							if (line.contains("categories") && line.contains("ContinuingNews"))
							{
								continuingNewsID.add(possibleID);		
								//bwa.write(String.valueOf(possibleID) + "\n");
							}
							
							completeID.add(possibleID);
						}						


					}
					//}
					br.close();
				}
				catch (Exception e) { e.printStackTrace(); }
			}	

			//continuingNewsIDList.add(continuingNewsID);
			System.out.println("Next one...");
		}
		
		int tokenCountTotal = 0;

		steppingStone = 0;
		
		int tweetCount = 0;

		for (steppingStone = 0; steppingStone < 15; steppingStone++)
		{
			System.out.println("Currently in Stepping Stone.. #" + steppingStone);
			String[] files2;


			switch (steppingStone)
			{
			case 0: files2 = new String[] {"2011_Joplin_tornado.json.gz"}; break;
			case 1: files2 = new String[] {"2012_Guatemala_earthquake.json.gz"}; break;
			case 2: files2 = new String[] {"2012_Italy_earthquakes.json.gz"}; break;
			case 3: files2 = new String[] {"2012_Philipinnes_floods.json.gz"}; break;
			case 4: files2 = new String[] {"2013_Alberta_floods.json.gz"}; break;
			case 5: files2 = new String[] {"2013_Australia_bushfire.json.gz"}; break;
			case 6: files2 = new String[] {"2013_Boston_bombings.json.gz"}; break;
			case 7: files2 = new String[] {"2013_Manila_floods.json.gz"}; break;
			case 8: files2 = new String[] {"2013_Queensland_floods.json.gz"}; break;
			case 9: files2 = new String[] {"2013_Typhoon_Yolanda.json.gz"}; break;
			case 10: files2 = new String[] {"2014_Chile_Earthquake.json.gz"}; break;
			case 11: files2 = new String[] {"2014_Typhoon_Hagupit.json.gz"}; break;
			case 12: files2 = new String[] {"2015_Nepal_Earthquake.json.gz"}; break;
			case 13: files2 = new String[] {"2015_Paris_Attacks.json.gz"}; break;
			default: files2 = new String[] {"2018_FL_School_Shooting.json.gz"};

			}

			ArrayList<ArrayList> arffList = new ArrayList<ArrayList>();		
			
			

			
			
			JSONParser parser = new JSONParser();

			for (String file : files2) {
				{

					try {
						BufferedReader br= new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))));
						String line;	

						while ((line = br.readLine())!=null) {
							JSONObject obj = (JSONObject) parser.parse(line);
							String extractID = (String) obj.get("id_str");
							BigInteger testID = new BigInteger (extractID);
							if (completeID.contains(testID))
							{
								tweetCount++;
								boolean continuingNews = false;
								boolean isVerified = false;
								boolean hasMedia = false;
								boolean userDescription = false;
	
								ArrayList<String> repeatedTokens = new ArrayList<String>();
								
								String actualText = (String) obj.get("text");
								//System.out.println(actualText);
								StringTokenizer textTokenizer = new StringTokenizer(actualText);
								JSONObject userProfile = (JSONObject) obj.get("user");
								isVerified = (boolean) userProfile.get("verified");								
								
								if (continuingNewsID.contains(testID))
									continuingNews = true;
								else
									continuingNews = false;
	
								if (line.contains("media_url"))
									hasMedia = true;
	
								String userDesc = (String) userProfile.get("description");
								userDesc.toLowerCase();																				
	
								ArrayList arr = new ArrayList();	
								//wordCountList.add(textTokenizer.countTokens());
								while (textTokenizer.hasMoreTokens()) {
									String token = TextUtils.normaliseString(textTokenizer.nextToken());
	
									if (token!=null && token.length()>0) {
										tokenCountTotal++;
	
										if (token.startsWith("http"))
										{
											try
											{
											
											String realString = new String();
											realString = UrlCleaner.unshortenUrl(token);
											StringTokenizer textTokenizerURL = new StringTokenizer(realString);
											
	
											String tokenURL = TextUtils.normaliseStringIgnore(textTokenizerURL.nextToken());
											String [] tokensURL = tokenURL.split(" ");
	
											for (int i = 0; i < tokensURL.length; i++)
											{	
												if (tokensURL[i]!=null && tokensURL[i].length()>0 && lexiconList.contains(tokensURL[i]) && !wordList.contains(tokensURL[i]))
												{
													wordList.add(tokensURL[i]);
													tweetList.add(1);
													wordLexiconList.add(lexiconList.indexOf(tokensURL[i]));
												}
												else if (wordList.contains(tokensURL[i]))
												{
													int indexToken = wordList.indexOf(tokensURL[i]);
													
	
													if (!repeatedTokens.contains(tokensURL[i]))
													{
														repeatedTokens.add(tokensURL[i]);
														int currentCountTweet = tweetList.get(indexToken);
														tweetList.set(indexToken, currentCountTweet + 1);
													}
												}
											}
											}
											catch (Exception e)
											{
												e.printStackTrace();
												
											}
										}										
										else if (lexiconList.contains(token))
										{
										
											arr.add(lexiconList.indexOf(token));
	
											if (!wordList.contains(token))
											{
												wordList.add(token);
												
												tweetList.add(1);
												wordLexiconList.add(lexiconList.indexOf(token));
											}
											else
											{
												int indexToken = wordList.indexOf(token);
												
	
												if (!repeatedTokens.contains(token))
												{
													repeatedTokens.add(token);
													int currentCountTweet = tweetList.get(indexToken);
													tweetList.set(indexToken, currentCountTweet + 1);
												}
											}
	
											
											if ( (userDesc.contains("news")) || (userDesc.contains("journalism")) 
													|| (userDesc.contains("blogger")) || (userDesc.contains("coverage"))
													|| (userDesc.contains("global")))
												userDescription = true;
											
										}																		
									}							
								}
	
								Collections.sort(arr);
	
								if (isVerified)
									arr.add("verified");
								else
									arr.add("notverified");
	
								if (hasMedia)
									arr.add("hasmedia");
								else
									arr.add("nomedia");
	
								if (userDescription)
									arr.add("desc");
								else
									arr.add("notdesc");
	
	
								if (continuingNews)
									arr.add("continuingnews");
								else
									arr.add("notcontinuingnews");
	
								
	
	
								/*
								if (continuingNews)
									arr.add("donations");
								else
									arr.add("notdonations");
								 */
	
								/*
								if (continuingNews)
									arr.add("official");
								else
									arr.add("notofficial");
								 */
	
								/*
								if (continuingNews)
									arr.add("advice");
								else
									arr.add("notadvice");
								 */
	
								/*
								if (continuingNews)
									arr.add("multi");
								else
									arr.add("notmulti");
								 */
	
								/*
								if (continuingNews)
									arr.add("goods");
								else
									arr.add("notgoods");
								 */
	
								arffList.add(arr);															
	
							}
					}
						br.close();
					} catch (Exception e) { e.printStackTrace(); }
				}
			}
			arffListComplete.add(arffList);			
					
		}
				
		steppingStone = 0;
		for (steppingStone = 0; steppingStone < 15; steppingStone++)
		{
			System.out.println("Currently writing in Stepping Stone.. #" + steppingStone);
			try {
				//BufferedWriter bw = new BufferedWriter(new FileWriter("ContinuingNewsCombo.arff"));
				String whatToWrite;

				switch (steppingStone)
				{
				case 0: whatToWrite = "2011JoplinTrain.arff"; break;
				case 1: whatToWrite = "2012GuatemalaTrain.arff"; break;
				case 2: whatToWrite = "2012ItalyTrain.arff"; break;
				case 3: whatToWrite = "2012PhilipinneTrain.arff"; break;
				case 4: whatToWrite = "2013AlbertaTrain.arff"; break;
				case 5: whatToWrite = "2013AustraliaTrain.arff"; break;
				case 6: whatToWrite = "2013BostonTrain.arff"; break;
				case 7: whatToWrite = "2013ManilaTrain.arff"; break;
				case 8: whatToWrite = "2013QueensTrain.arff"; break;
				case 9: whatToWrite = "2013YolandaTrain.arff"; break;
				case 10: whatToWrite = "2014ChileTrain.arff"; break;
				case 11: whatToWrite = "2014HagupitTrain.arff"; break;
				case 12: whatToWrite = "2015NepalTrain.arff"; break;
				case 13: whatToWrite = "2015ParisTrain.arff"; break;
				default: whatToWrite = "2018FloridaTrain.arff"; 
				}

				ArrayList <ArrayList> arffSelect = new ArrayList<ArrayList>();
				
				for (int thearffs = 0; thearffs < 5; thearffs++)
				{
					if (thearffs != steppingStone)
						arffSelect.addAll(arffListComplete.get(thearffs));
				}

				

				/*
				if (steppingStone == 0)
					whatToWrite = "ContinuingNewsTFIDFTrainPhilippines.arff";
				else
					whatToWrite = "ContinuingNewsTFIDFTestPhilippines.arff";
				 */
				BufferedWriter bw = new BufferedWriter(new FileWriter(whatToWrite));
				//BufferedWriter bw = new BufferedWriter(new FileWriter("DonationsPrototype2.arff"));
				//BufferedWriter bw = new BufferedWriter(new FileWriter("OfficialPrototype.arff"));
				//BufferedWriter bw = new BufferedWriter(new FileWriter("AdvicePrototype.arff"));
				//BufferedWriter bw = new BufferedWriter(new FileWriter("MultimediaSharePrototype.arff"));
				//BufferedWriter bw = new BufferedWriter(new FileWriter("GoodsServicesPrototype.arff"));

				bw.write("@RELATION" + "\t" + "ContinuingNews" + "\n");
				//bw.write("@RELATION" + "\t" + "Donations" + "\n");
				//bw.write("@RELATION" + "\t" + "Official" + "\n");
				//bw.write("@RELATION" + "\t" + "Advice" + "\n");
				//bw.write("@RELATION" + "\t" + "MultimediaShare" + "\n");
				//bw.write("@RELATION" + "\t" + "GoodsServices" + "\n");

				for (int i = 0; i < lexiconList.size(); i++)
					bw.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");

				bw.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );
				bw.write("@ATTRIBUTE" + "\t" + "ContinuingNews" + "\t" + "{\"continuingnews\",\"notcontinuingnews\"}" + "\n" );
				//bw.write("@ATTRIBUTE" + "\t" + "Donations" + "\t" + "{\"donations\",\"notdonations\"}" + "\n" );
				//bw.write("@ATTRIBUTE" + "\t" + "Official" + "\t" + "{\"official\",\"notofficial\"}" + "\n" );
				//bw.write("@ATTRIBUTE" + "\t" + "Advice" + "\t" + "{\"advice\",\"notadvice\"}" + "\n" );
				//bw.write("@ATTRIBUTE" + "\t" + "MultimediaShare" + "\t" + "{\"multi\",\"notmulti\"}" + "\n" );
				//bw.write("@ATTRIBUTE" + "\t" + "GoodsServices" + "\t" + "{\"goods\",\"notgoods\"}" + "\n" );
				bw.write("@DATA" + "\n");

				for (int i = 0; i < arffSelect.size(); i++) {		
					bw.write("{");
					ArrayList<Integer> existingInteger = new ArrayList<Integer>();

					//if (i < 5)
					//	System.out.println(arffList.get(i));

					for (int j = 0; j < arffSelect.get(i).size() - 4; j++)
					{						
						int no = (int) arffSelect.get(i).get(j);						

						if (!existingInteger.contains(no))
						{
							bw.write(String.valueOf(no));
							bw.write("\t");

							int wordIndex = wordLexiconList.indexOf(no);

							// TF IDF 1 
							double tf = 1.0 * Collections.frequency(arffSelect.get(i), no);
							double idf = Math.log(1.0 * tweetCount / tweetList.get(wordIndex));
							double tfidf = tf*idf;

							// TF IDF 2
							//double tfidf = 1.0 + (1.0 * Math.log(Collections.frequency(arffList.get(i), no)));

							// TF IDF 3
							//double tf = 1.0 + (1.0 * Math.log(Collections.frequency(arffList.get(i), no)));
							//double idf = Math.log(1.0 * arffList.size() / tweetList.get(wordIndex));
							//double tfidf = tf*idf;

							//double tf = (1.0 * Collections.frequency(arffList.get(i), no) / wordCountList.get(i));
							//double tf = 1.0 * wordCountList.get(wordIndex) / tokenCountTotal;
							//tf = Math.round(tf * 100000.0) / 100000.0;
							//double idf = Math.log(1.0 * arffList.size() / tweetList.get(wordIndex));


							//if (i < 5)
							//System.out.println(lexiconList.get(no) + ": " + "(" + no + "): " + "TFIDF: (" + tfidf + ")");
							//System.out.println(lexiconList.get(no) + ": " + "(" + no + "): " + "TF: (" + tf + "), " + tfidf);
							tfidf = Math.round(tfidf * 100.0) / 100.0;
							bw.write(String.valueOf(tfidf) + ",");
							//bw.write("1.0,");

							/*
							if (!wordLexiconList.contains(no))
								bw.write("0.25,");
							else
							{
								if (no == topCountLexicon)
									bw.write("1.0,");
								else
								{
									int wordIndex = wordLexiconList.indexOf(no);
									int totalCount = wordCountList.get(wordIndex);

									if (totalCount > average)
										bw.write("0.75,");
									else
										bw.write("0.5,");
								}
							}
							 */
							bw.write("\t");
							existingInteger.add(no);
						}
					}

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 4).equals("verified"))
						bw.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 3).equals("hasmedia"))
						bw.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 2).equals("desc"))
						bw.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");

					bw.write(String.valueOf(lexiconList.size() + 3));
					bw.write("\t");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 1) == "continuingnews")
						bw.write("\"continuingnews\"}" + "\n");
					else
						bw.write("\"notcontinuingnews\"}" + "\n");


					/*
					if (arffList.get(i).get(arffList.get(i).size() - 1) == "donations")
						bw.write("\"donations\"}" + "\n");
					else
						bw.write("\"notdonations\"}" + "\n");
					 */	

					/*
					if (arffList.get(i).get(arffList.get(i).size() - 1) == "official")
						bw.write("\"official\"}" + "\n");
					else
						bw.write("\"notofficial\"}" + "\n");
					 */

					/*
					if (arffList.get(i).get(arffList.get(i).size() - 1) == "advice")
						bw.write("\"advice\"}" + "\n");
					else
						bw.write("\"notadvice\"}" + "\n");
					 */

					/*
					if (arffList.get(i).get(arffList.get(i).size() - 1) == "multi")
						bw.write("\"multi\"}" + "\n");
					else
						bw.write("\"notmulti\"}" + "\n");
					 */

					/*
					if (arffList.get(i).get(arffList.get(i).size() - 1) == "goods")
						bw.write("\"goods\"}" + "\n");
					else
						bw.write("\"notgoods\"}" + "\n");
					 */
				}

				bw.close();

				switch (steppingStone)
				{
				case 0: whatToWrite = "2011JoplinTest.arff"; break;
				case 1: whatToWrite = "2012GuatemalaTest.arff"; break;
				case 2: whatToWrite = "2012ItalyTest.arff"; break;
				case 3: whatToWrite = "2012PhilipinneTest.arff"; break;
				case 4: whatToWrite = "2013AlbertaTest.arff"; break;
				case 5: whatToWrite = "2013AustraliaTest.arff"; break;
				case 6: whatToWrite = "2013BostonTest.arff"; break;
				case 7: whatToWrite = "2013ManilaTest.arff"; break;
				case 8: whatToWrite = "2013QueensTest.arff"; break;
				case 9: whatToWrite = "2013YolandaTest.arff"; break;
				case 10: whatToWrite = "2014ChileTest.arff"; break;
				case 11: whatToWrite = "2014HagupitTest.arff"; break;
				case 12: whatToWrite = "2015NepalTest.arff"; break;
				case 13: whatToWrite = "2015ParisTest.arff"; break;
				default: whatToWrite = "2018FloridaTest.arff"; 
				}

				arffSelect.clear();
				arffSelect = arffListComplete.get(steppingStone);				

				bw = new BufferedWriter(new FileWriter(whatToWrite));		
				bw.write("@RELATION" + "\t" + "ContinuingNews" + "\n");

				for (int i = 0; i < lexiconList.size(); i++)
					bw.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");

				bw.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );
				bw.write("@ATTRIBUTE" + "\t" + "ContinuingNews" + "\t" + "{\"continuingnews\",\"notcontinuingnews\"}" + "\n" );				
				bw.write("@DATA" + "\n");

				for (int i = 0; i < arffSelect.size(); i++) {		
					bw.write("{");
					ArrayList<Integer> existingInteger = new ArrayList<Integer>();					

					for (int j = 0; j < arffSelect.get(i).size() - 4; j++)
					{						
						int no = (int) arffSelect.get(i).get(j);						

						if (!existingInteger.contains(no))
						{
							bw.write(String.valueOf(no));
							bw.write("\t");

							int wordIndex = wordLexiconList.indexOf(no);

							// TF IDF 1 
							double tf = 1.0 * Collections.frequency(arffSelect.get(i), no);
							double idf = Math.log(1.0 * tweetCount / tweetList.get(wordIndex));
							double tfidf = tf*idf;							
							tfidf = Math.round(tfidf * 100.0) / 100.0;
							bw.write(String.valueOf(tfidf) + ",");							
							bw.write("\t");
							existingInteger.add(no);
						}
					}

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 4).equals("verified"))
						bw.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 3).equals("hasmedia"))
						bw.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 2).equals("desc"))
						bw.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");

					bw.write(String.valueOf(lexiconList.size() + 3));
					bw.write("\t");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 1) == "continuingnews")
						bw.write("\"continuingnews\"}" + "\n");
					else
						bw.write("\"notcontinuingnews\"}" + "\n");

					
				}	
				bw.close();
			} catch (Exception e) {e.printStackTrace();}
		}

		steppingStone = 0;
		for (steppingStone = 0; steppingStone < 15; steppingStone++)
		{
			String trainingData;
			String testData;
			String resultText;
			
			switch (steppingStone)
			{
				case 0: trainingData = "2011JoplinTrain.arff"; break;
				case 1: trainingData = "2012GuatemalaTrain.arff"; break;
				case 2: trainingData = "2012ItalyTrain.arff"; break;
				case 3: trainingData = "2012PhilipinneTrain.arff"; break;
				case 4: trainingData = "2013AlbertaTrain.arff"; break;
				case 5: trainingData = "2013AustraliaTrain.arff"; break;
				case 6: trainingData = "2013BostonTrain.arff"; break;
				case 7: trainingData = "2013ManilaTrain.arff"; break;
				case 8: trainingData = "2013QueensTrain.arff"; break;
				case 9: trainingData = "2013YolandaTrain.arff"; break;
				case 10: trainingData = "2014ChileTrain.arff"; break;
				case 11: trainingData = "2014HagupitTrain.arff"; break;
				case 12: trainingData = "2015NepalTrain.arff"; break;
				case 13: trainingData = "2015ParisTrain.arff"; break;
				default: trainingData = "2018FloridaTrain.arff"; 
			}
			
			switch (steppingStone)
			{
				case 0: testData = "2011JoplinTest.arff"; break;
				case 1: testData = "2012GuatemalaTest.arff"; break;
				case 2: testData = "2012ItalyTest.arff"; break;
				case 3: testData = "2012PhilipinneTest.arff"; break;
				case 4: testData = "2013AlbertaTest.arff"; break;
				case 5: testData = "2013AustraliaTest.arff"; break;
				case 6: testData = "2013BostonTest.arff"; break;
				case 7: testData = "2013ManilaTest.arff"; break;
				case 8: testData = "2013QueensTest.arff"; break;
				case 9: testData = "2013YolandaTest.arff"; break;
				case 10: testData = "2014ChileTest.arff"; break;
				case 11: testData = "2014HagupitTest.arff"; break;
				case 12: testData = "2015NepalTest.arff"; break;
				case 13: testData = "2015ParisTest.arff"; break;
				default: testData = "2018FloridaTest.arff"; 
			}
			
			switch (steppingStone)
			{
				case 0: resultText = "2011JoplinResults.txt"; break;
				case 1: resultText = "2012GuatemalaResults.txt"; break;
				case 2: resultText = "2012ItalyResults.txt"; break;
				case 3: resultText = "2012PhilippineResults.txt"; break;
				case 4: resultText = "2013AlbertaResults.txt"; break;
				case 5: resultText = "2013AustraliaResults.txt"; break;
				case 6: resultText = "2013BostonResults.txt"; break;
				case 7: resultText = "2013ManilaResults.txt"; break;
				case 8: resultText = "2013QueensResults.txt"; break;
				case 9: resultText = "2013YolandaResults.txt"; break;
				case 10: resultText = "2014ChileResults.txt"; break;
				case 11: resultText = "2014HagupitResults.txt"; break;
				case 12: resultText = "2015NepalResults.txt"; break;
				case 13: resultText = "2015ParisResults.txt"; break;
				default: resultText = "2018FloridaResults.txt"; 
			}
			
			ConverterUtils.DataSource  loader1 = new ConverterUtils.DataSource(trainingData);
			ConverterUtils.DataSource  loader2 = new ConverterUtils.DataSource(testData);
			
			//ConverterUtils.DataSource  loader1 = new ConverterUtils.DataSource("ContinuingNewsTFIDFTrainPhilippines.arff");
			//ConverterUtils.DataSource  loader2 = new ConverterUtils.DataSource("ContinuingNewsTFIDFTestPhilippines.arff");
			try {
				//BufferedWriter bw = new BufferedWriter(new FileWriter("2012PhilippinesResults.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(resultText));
				Instances trainData = loader1.getDataSet();
				trainData.setClassIndex(trainData.numAttributes() - 1);

				Instances testingData = loader2.getDataSet();
				testingData.setClassIndex(testingData.numAttributes() - 1);

				Classifier cls1 = new NaiveBayes();					
				cls1.buildClassifier(trainData);			
				Evaluation eval1 = new Evaluation(trainData);
				eval1.evaluateModel(cls1, testingData);	
				bw.write("=== Summary of Naive Bayes ===");
				bw.write(eval1.toSummaryString());
				bw.write(eval1.toClassDetailsString());
				bw.write(eval1.toMatrixString());
				bw.write("\n");
				//System.out.println("=== Summary of Naive Bayes ===");
				//System.out.println(eval1.toSummaryString());
				//System.out.println(eval1.toClassDetailsString());			
				//System.out.println(eval1.toMatrixString());

				Classifier cls2 = new SMO();
				cls2.buildClassifier(trainData);
				Evaluation eval2 = new Evaluation(trainData);
				eval2.evaluateModel(cls2, testingData);
				bw.write("=== Summary of SMO ===");
				bw.write(eval2.toSummaryString());
				bw.write(eval2.toClassDetailsString());
				bw.write(eval2.toMatrixString());
				//System.out.println("=== Summary of SMO ===");
				//System.out.println(eval2.toSummaryString());
				//System.out.println(eval2.toClassDetailsString());
				//System.out.println(eval2.toMatrixString());
				bw.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
	}
}
