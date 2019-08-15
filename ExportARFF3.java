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

public class ExportARFF3 {

	public static void main (String [] args) throws Exception {

		// Loading in the lexicon and getting the size of the lexicon
		String lexicon = "event.lexicon";
		String category;
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

		// Files to be read in, containing the categories
		String[] files1 = {
				"assr1.test.gz",
				"assr2.test.gz",
				"assr3.test.gz",
				"assr4.test.gz",
				"assr5.test.gz",
				"assr6.test.gz"
		};

		// Preparing ArrayLists of each scenario....
		//ArrayList<ArrayList<BigInteger>> continuingNewsIDList = new ArrayList<ArrayList<BigInteger>>();
		ArrayList<BigInteger> continuingNewsID = new ArrayList<BigInteger>();
		ArrayList<BigInteger> donationsID = new ArrayList<BigInteger>();
		ArrayList<BigInteger> officialID = new ArrayList<BigInteger>();
		ArrayList<BigInteger> adviceID = new ArrayList<BigInteger>();
		ArrayList<BigInteger> multimediaID = new ArrayList<BigInteger>();
		ArrayList<BigInteger> goodsID = new ArrayList<BigInteger>();
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
								continuingNewsID.add(possibleID);	

							if (line.contains("categories") && line.contains("Donations"))
								donationsID.add(possibleID);

							if (line.contains("categories") && line.contains("Official"))
								officialID.add(possibleID);	

							if (line.contains("categories") && line.contains("Advice"))
								adviceID.add(possibleID);

							if (line.contains("categories") && line.contains("MultimediaShare"))
								multimediaID.add(possibleID);	

							if (line.contains("categories") && line.contains("GoodsServices"))
								goodsID.add(possibleID);



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
								boolean donations = false;
								boolean official = false;
								boolean advice = false;
								boolean mult = false;
								boolean goods = false;
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

								if (donationsID.contains(testID))
									donations = true;
								else
									donations = false;

								if (officialID.contains(testID))
									official = true;
								else
									official = false;

								if (adviceID.contains(testID))
									advice = true;
								else
									advice = false;

								if (multimediaID.contains(testID))
									mult = true;
								else
									mult = false;

								if (goodsID.contains(testID))
									goods = true;
								else
									goods = false;

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

								if (donations)
									arr.add("donations");
								else
									arr.add("notdonations");

								if (official)
									arr.add("official");
								else
									arr.add("notofficial");

								if (advice)
									arr.add("advice");
								else
									arr.add("notadvice");

								if (mult)
									arr.add("multi");
								else
									arr.add("notmulti");								

								if (goods)
									arr.add("goods");
								else
									arr.add("notgoods");

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
				String whatToWrite1, whatToWrite2, whatToWrite3, whatToWrite4, whatToWrite5, whatToWrite6;

				switch (steppingStone)
				{
				case 0: whatToWrite1 = "2011JoplinTrainCont.arff"; 
				whatToWrite2 = "2011JoplinTrainDona.arff";
				whatToWrite3 = "2011JoplinTrainOffi.arff";
				whatToWrite4 = "2011JoplinTrainAdvi.arff";
				whatToWrite5 = "2011JoplinTrainMult.arff";
				whatToWrite6 = "2011JoplinTrainGood.arff";
				break;
				case 1: whatToWrite1 = "2012GuatemalaTrainCont.arff";
				whatToWrite2 = "2012GuatemalaTrainDona.arff";
				whatToWrite3 = "2012GuatemalaTrainOffi.arff";
				whatToWrite4 = "2012GuatemalaTrainAdvi.arff";
				whatToWrite5 = "2012GuatemalaTrainMult.arff";
				whatToWrite6 = "2012GuatemalaTrainGood.arff";
				break;
				case 2: whatToWrite1 = "2012ItalyTrainCont.arff";
				whatToWrite2 = "2012ItalyTrainDona.arff";
				whatToWrite3 = "2012ItalyTrainOffi.arff";
				whatToWrite4 = "2012ItalyTrainAdvi.arff";
				whatToWrite5 = "2012ItalyTrainMult.arff";
				whatToWrite6 = "2012ItalyTrainGood.arff";
				break;
				case 3: whatToWrite1 = "2012PhilipinneTrainCont.arff";
				whatToWrite2 = "2012PhilipinneTrainDona.arff";
				whatToWrite3 = "2012PhilipinneTrainOffi.arff";
				whatToWrite4 = "2012PhilipinneTrainAdvi.arff";
				whatToWrite5 = "2012PhilipinneTrainMult.arff";
				whatToWrite6 = "2012PhilipinneTrainGood.arff";
				break;
				case 4: whatToWrite1 = "2013AlbertaTrainCont.arff";
				whatToWrite2 = "2013AlbertaTrainDona.arff";
				whatToWrite3 = "2013AlbertaTrainOffi.arff";
				whatToWrite4 = "2013AlbertaTrainAdvi.arff";
				whatToWrite5 = "2013AlbertaTrainMult.arff";
				whatToWrite6 = "2013AlbertaTrainGood.arff";
				break;
				case 5: whatToWrite1 = "2013AustraliaTrainCont.arff";
				whatToWrite2 = "2013AustraliaTrainDona.arff";
				whatToWrite3 = "2013AustraliaTrainOffi.arff";
				whatToWrite4 = "2013AustraliaTrainAdvi.arff";
				whatToWrite5 = "2013AustraliaTrainMult.arff";
				whatToWrite6 = "2013AustraliaTrainGood.arff";
				break;
				case 6: whatToWrite1 = "2013BostonTrainCont.arff";
				whatToWrite2 = "2013BostonTrainDona.arff";
				whatToWrite3 = "2013BostonTrainOffi.arff";
				whatToWrite4 = "2013BostonTrainAdvi.arff";
				whatToWrite5 = "2013BostonTrainMult.arff";
				whatToWrite6 = "2013BostonTrainGood.arff";
				break;
				case 7: whatToWrite1 = "2013ManilaTrainCont.arff";
				whatToWrite2 = "2013ManilaTrainDona.arff";
				whatToWrite3 = "2013ManilaTrainOffi.arff";
				whatToWrite4 = "2013ManilaTrainAdvi.arff";
				whatToWrite5 = "2013ManilaTrainMult.arff";
				whatToWrite6 = "2013ManilaTrainGood.arff";
				break;
				case 8: whatToWrite1 = "2013QueensTrainCont.arff"; 
				whatToWrite2 = "2013QueensTrainDona.arff";
				whatToWrite3 = "2013QueensTrainOffi.arff";
				whatToWrite4 = "2013QueensTrainAdvi.arff";
				whatToWrite5 = "2013QueensTrainMult.arff";
				whatToWrite6 = "2013QueensTrainGood.arff";
				break;
				case 9: whatToWrite1 = "2013YolandaTrainCont.arff";
				whatToWrite2 = "2013YolandaTrainDona.arff";
				whatToWrite3 = "2013YolandaTrainOffi.arff";
				whatToWrite4 = "2013YolandaTrainAdvi.arff";
				whatToWrite5 = "2013YolandaTrainMult.arff";
				whatToWrite6 = "2013YolandaTrainGood.arff";
				break;
				case 10: whatToWrite1 = "2014ChileTrainCont.arff";
				whatToWrite2 = "2014ChileTrainDona.arff";
				whatToWrite3 = "2014ChileTrainOffi.arff";
				whatToWrite4 = "2014ChileTrainAdvi.arff";
				whatToWrite5 = "2014ChileTrainMult.arff";
				whatToWrite6 = "2014ChileTrainGood.arff";
				break;
				case 11: whatToWrite1 = "2014HagupitTrainCont.arff";
				whatToWrite2 = "2014HagupitTrainDona.arff";
				whatToWrite3 = "2014HagupitTrainOffi.arff";
				whatToWrite4 = "2014HagupitTrainAdvi.arff";
				whatToWrite5 = "2014HagupitTrainMult.arff";
				whatToWrite6 = "2014HagupitTrainGood.arff";
				break;
				case 12: whatToWrite1 = "2015NepalTrainCont.arff";
				whatToWrite2 = "2015NepalTrainDona.arff";
				whatToWrite3 = "2015NepalTrainOffi.arff";
				whatToWrite4 = "2015NepalTrainAdvi.arff";
				whatToWrite5 = "2015NepalTrainMult.arff";
				whatToWrite6 = "2015NepalTrainGood.arff";
				break;
				case 13: whatToWrite1 = "2015ParisTrainCont.arff";
				whatToWrite2 = "2015ParisTrainDona.arff";
				whatToWrite3 = "2015ParisTrainOffi.arff";
				whatToWrite4 = "2015ParisTrainAdvi.arff";
				whatToWrite5 = "2015ParisTrainMult.arff";
				whatToWrite6 = "2015ParisTrainGood.arff";
				break;
				default: whatToWrite1 = "2018FloridaTrainCont.arff"; 
				whatToWrite2 = "2018FloridaTrainDona.arff";
				whatToWrite3 = "2018FloridaTrainOffi.arff";
				whatToWrite4 = "2018FloridaTrainAdvi.arff";
				whatToWrite5 = "2018FloridaTrainMult.arff";
				whatToWrite6 = "2018FloridaTrainGood.arff";
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
				BufferedWriter bw1 = new BufferedWriter(new FileWriter(whatToWrite1));
				BufferedWriter bw2 = new BufferedWriter(new FileWriter(whatToWrite2));
				BufferedWriter bw3 = new BufferedWriter(new FileWriter(whatToWrite3));
				BufferedWriter bw4 = new BufferedWriter(new FileWriter(whatToWrite4));
				BufferedWriter bw5 = new BufferedWriter(new FileWriter(whatToWrite5));
				BufferedWriter bw6 = new BufferedWriter(new FileWriter(whatToWrite6));

				bw1.write("@RELATION" + "\t" + "ContinuingNews" + "\n");
				bw2.write("@RELATION" + "\t" + "Donations" + "\n");
				bw3.write("@RELATION" + "\t" + "Official" + "\n");
				bw4.write("@RELATION" + "\t" + "Advice" + "\n");
				bw5.write("@RELATION" + "\t" + "MultimediaShare" + "\n");
				bw6.write("@RELATION" + "\t" + "GoodsServices" + "\n");

				for (int i = 0; i < lexiconList.size(); i++)
				{
					bw1.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw2.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw3.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw4.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw5.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw6.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
				}

				bw1.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw1.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw1.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw2.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw2.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw2.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw3.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw3.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw3.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw4.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw4.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw4.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw5.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw5.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw5.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw6.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw6.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw6.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw1.write("@ATTRIBUTE" + "\t" + "ContinuingNews" + "\t" + "{\"continuingnews\",\"notcontinuingnews\"}" + "\n" );
				bw2.write("@ATTRIBUTE" + "\t" + "Donations" + "\t" + "{\"donations\",\"notdonations\"}" + "\n" );
				bw3.write("@ATTRIBUTE" + "\t" + "Official" + "\t" + "{\"official\",\"notofficial\"}" + "\n" );
				bw4.write("@ATTRIBUTE" + "\t" + "Advice" + "\t" + "{\"advice\",\"notadvice\"}" + "\n" );
				bw5.write("@ATTRIBUTE" + "\t" + "MultimediaShare" + "\t" + "{\"multi\",\"notmulti\"}" + "\n" );
				bw6.write("@ATTRIBUTE" + "\t" + "GoodsServices" + "\t" + "{\"goods\",\"notgoods\"}" + "\n" );

				bw1.write("@DATA" + "\n");
				bw2.write("@DATA" + "\n");
				bw3.write("@DATA" + "\n");
				bw4.write("@DATA" + "\n");
				bw5.write("@DATA" + "\n");
				bw6.write("@DATA" + "\n");

				for (int i = 0; i < arffSelect.size(); i++) {		
					bw1.write("{");
					bw2.write("{");
					bw3.write("{");
					bw4.write("{");
					bw5.write("{");
					bw6.write("{");
					ArrayList<Integer> existingInteger = new ArrayList<Integer>();

					//if (i < 5)
					//	System.out.println(arffList.get(i));

					for (int j = 0; j < arffSelect.get(i).size() - 9; j++)
					{						
						int no = (int) arffSelect.get(i).get(j);						

						if (!existingInteger.contains(no))
						{
							bw1.write(String.valueOf(no));
							bw1.write("\t");
							bw2.write(String.valueOf(no));
							bw2.write("\t");
							bw3.write(String.valueOf(no));
							bw3.write("\t");
							bw4.write(String.valueOf(no));
							bw4.write("\t");
							bw5.write(String.valueOf(no));
							bw5.write("\t");
							bw6.write(String.valueOf(no));
							bw6.write("\t");

							int wordIndex = wordLexiconList.indexOf(no);

							// TF IDF 1 
							double tf = 1.0 * Collections.frequency(arffSelect.get(i), no);
							double idf = Math.log(1.0 * tweetCount / tweetList.get(wordIndex));
							double tfidf = tf*idf;


							tfidf = Math.round(tfidf * 100.0) / 100.0;
							bw1.write(String.valueOf(tfidf) + ",");
							bw2.write(String.valueOf(tfidf) + ",");
							bw3.write(String.valueOf(tfidf) + ",");
							bw4.write(String.valueOf(tfidf) + ",");
							bw5.write(String.valueOf(tfidf) + ",");
							bw6.write(String.valueOf(tfidf) + ",");

							bw1.write("\t");
							bw2.write("\t");
							bw3.write("\t");
							bw4.write("\t");
							bw5.write("\t");
							bw6.write("\t");
							existingInteger.add(no);
						}
					}

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 9).equals("verified"))
					{
						bw1.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw2.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw3.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw4.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw5.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw6.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
					}

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 8).equals("hasmedia"))
					{
						bw1.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw2.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw3.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw4.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw5.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw6.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
					}

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 7).equals("desc"))
					{
						bw1.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw2.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw3.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw4.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw5.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw6.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
					}

					bw1.write(String.valueOf(lexiconList.size() + 3));
					bw1.write("\t");
					bw2.write(String.valueOf(lexiconList.size() + 3));
					bw2.write("\t");
					bw3.write(String.valueOf(lexiconList.size() + 3));
					bw3.write("\t");
					bw4.write(String.valueOf(lexiconList.size() + 3));
					bw4.write("\t");
					bw5.write(String.valueOf(lexiconList.size() + 3));
					bw5.write("\t");
					bw6.write(String.valueOf(lexiconList.size() + 3));
					bw6.write("\t");				

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 6) == "continuingnews")
						bw1.write("\"continuingnews\"}" + "\n");
					else
						bw1.write("\"notcontinuingnews\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 5) == "donations")
						bw2.write("\"donations\"}" + "\n");
					else
						bw2.write("\"notdonations\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 4) == "official")
						bw3.write("\"official\"}" + "\n");
					else
						bw3.write("\"notofficial\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 3) == "advice")
						bw4.write("\"advice\"}" + "\n");
					else
						bw4.write("\"notadvice\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 2) == "multi")
						bw5.write("\"multi\"}" + "\n");
					else
						bw5.write("\"notmulti\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 1) == "goods")
						bw6.write("\"goods\"}" + "\n");
					else
						bw6.write("\"notgoods\"}" + "\n");

				}

				bw1.close();
				bw2.close();
				bw3.close();
				bw4.close();
				bw5.close();
				bw6.close();

				switch (steppingStone)
				{
				case 0: whatToWrite1 = "2011JoplinTestCont.arff"; 
				whatToWrite2 = "2011JoplinTestDona.arff";
				whatToWrite3 = "2011JoplinTestOffi.arff";
				whatToWrite4 = "2011JoplinTestAdvi.arff";
				whatToWrite5 = "2011JoplinTestMult.arff";
				whatToWrite6 = "2011JoplinTestGood.arff";
				break;
				case 1: whatToWrite1 = "2012GuatemalaTestCont.arff";
				whatToWrite2 = "2012GuatemalaTestDona.arff";
				whatToWrite3 = "2012GuatemalaTestOffi.arff";
				whatToWrite4 = "2012GuatemalaTestAdvi.arff";
				whatToWrite5 = "2012GuatemalaTestMult.arff";
				whatToWrite6 = "2012GuatemalaTestGood.arff";
				break;
				case 2: whatToWrite1 = "2012ItalyTestCont.arff";
				whatToWrite2 = "2012ItalyTestDona.arff";
				whatToWrite3 = "2012ItalyTestOffi.arff";
				whatToWrite4 = "2012ItalyTestAdvi.arff";
				whatToWrite5 = "2012ItalyTestMult.arff";
				whatToWrite6 = "2012ItalyTestGood.arff";
				break;
				case 3: whatToWrite1 = "2012PhilipinneTestCont.arff";
				whatToWrite2 = "2012PhilipinneTestDona.arff";
				whatToWrite3 = "2012PhilipinneTestOffi.arff";
				whatToWrite4 = "2012PhilipinneTestAdvi.arff";
				whatToWrite5 = "2012PhilipinneTestMult.arff";
				whatToWrite6 = "2012PhilipinneTestGood.arff";
				break;
				case 4: whatToWrite1 = "2013AlbertaTestCont.arff";
				whatToWrite2 = "2013AlbertaTestDona.arff";
				whatToWrite3 = "2013AlbertaTestOffi.arff";
				whatToWrite4 = "2013AlbertaTestAdvi.arff";
				whatToWrite5 = "2013AlbertaTestMult.arff";
				whatToWrite6 = "2013AlbertaTestGood.arff";
				break;
				case 5: whatToWrite1 = "2013AustraliaTestCont.arff";
				whatToWrite2 = "2013AustraliaTestDona.arff";
				whatToWrite3 = "2013AustraliaTestOffi.arff";
				whatToWrite4 = "2013AustraliaTestAdvi.arff";
				whatToWrite5 = "2013AustraliaTestMult.arff";
				whatToWrite6 = "2013AustraliaTestGood.arff";
				break;
				case 6: whatToWrite1 = "2013BostonTestCont.arff";
				whatToWrite2 = "2013BostonTestDona.arff";
				whatToWrite3 = "2013BostonTestOffi.arff";
				whatToWrite4 = "2013BostonTestAdvi.arff";
				whatToWrite5 = "2013BostonTestMult.arff";
				whatToWrite6 = "2013BostonTestGood.arff";
				break;
				case 7: whatToWrite1 = "2013ManilaTestCont.arff";
				whatToWrite2 = "2013ManilaTestDona.arff";
				whatToWrite3 = "2013ManilaTestOffi.arff";
				whatToWrite4 = "2013ManilaTestAdvi.arff";
				whatToWrite5 = "2013ManilaTestMult.arff";
				whatToWrite6 = "2013ManilaTestGood.arff";
				break;
				case 8: whatToWrite1 = "2013QueensTestCont.arff"; 
				whatToWrite2 = "2013QueensTestDona.arff";
				whatToWrite3 = "2013QueensTestOffi.arff";
				whatToWrite4 = "2013QueensTestAdvi.arff";
				whatToWrite5 = "2013QueensTestMult.arff";
				whatToWrite6 = "2013QueensTestGood.arff";
				break;
				case 9: whatToWrite1 = "2013YolandaTestCont.arff";
				whatToWrite2 = "2013YolandaTestDona.arff";
				whatToWrite3 = "2013YolandaTestOffi.arff";
				whatToWrite4 = "2013YolandaTestAdvi.arff";
				whatToWrite5 = "2013YolandaTestMult.arff";
				whatToWrite6 = "2013YolandaTestGood.arff";
				break;
				case 10: whatToWrite1 = "2014ChileTestCont.arff";
				whatToWrite2 = "2014ChileTestDona.arff";
				whatToWrite3 = "2014ChileTestOffi.arff";
				whatToWrite4 = "2014ChileTestAdvi.arff";
				whatToWrite5 = "2014ChileTestMult.arff";
				whatToWrite6 = "2014ChileTestGood.arff";
				break;
				case 11: whatToWrite1 = "2014HagupitTestCont.arff";
				whatToWrite2 = "2014HagupitTestDona.arff";
				whatToWrite3 = "2014HagupitTestOffi.arff";
				whatToWrite4 = "2014HagupitTestAdvi.arff";
				whatToWrite5 = "2014HagupitTestMult.arff";
				whatToWrite6 = "2014HagupitTestGood.arff";
				break;
				case 12: whatToWrite1 = "2015NepalTestCont.arff";
				whatToWrite2 = "2015NepalTestDona.arff";
				whatToWrite3 = "2015NepalTestOffi.arff";
				whatToWrite4 = "2015NepalTestAdvi.arff";
				whatToWrite5 = "2015NepalTestMult.arff";
				whatToWrite6 = "2015NepalTestGood.arff";
				break;
				case 13: whatToWrite1 = "2015ParisTestCont.arff";
				whatToWrite2 = "2015ParisTestDona.arff";
				whatToWrite3 = "2015ParisTestOffi.arff";
				whatToWrite4 = "2015ParisTestAdvi.arff";
				whatToWrite5 = "2015ParisTestMult.arff";
				whatToWrite6 = "2015ParisTestGood.arff";
				break;
				default: whatToWrite1 = "2018FloridaTestCont.arff"; 
				whatToWrite2 = "2018FloridaTestDona.arff";
				whatToWrite3 = "2018FloridaTestOffi.arff";
				whatToWrite4 = "2018FloridaTestAdvi.arff";
				whatToWrite5 = "2018FloridaTestMult.arff";
				whatToWrite6 = "2018FloridaTestGood.arff";
				}

				arffSelect.clear();
				arffSelect = arffListComplete.get(steppingStone);				

				bw1 = new BufferedWriter(new FileWriter(whatToWrite1));		
				bw2 = new BufferedWriter(new FileWriter(whatToWrite2));
				bw3 = new BufferedWriter(new FileWriter(whatToWrite3));
				bw4 = new BufferedWriter(new FileWriter(whatToWrite4));
				bw5 = new BufferedWriter(new FileWriter(whatToWrite5));
				bw6 = new BufferedWriter(new FileWriter(whatToWrite6));
				
				bw1.write("@RELATION" + "\t" + "ContinuingNews" + "\n");
				bw2.write("@RELATION" + "\t" + "Donations" + "\n");
				bw3.write("@RELATION" + "\t" + "Official" + "\n");
				bw4.write("@RELATION" + "\t" + "Advice" + "\n");
				bw5.write("@RELATION" + "\t" + "MultimediaShare" + "\n");
				bw6.write("@RELATION" + "\t" + "GoodsServices" + "\n");

				for (int i = 0; i < lexiconList.size(); i++)
				{
					bw1.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw2.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw3.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw4.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw5.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
					bw6.write("@ATTRIBUTE" + "\t" + "attr" + i + "\t" + "NUMERIC" + "\n");
				}

				bw1.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw1.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw1.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw2.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw2.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw2.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw3.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw3.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw3.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw4.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw4.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw4.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw5.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw5.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw5.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw6.write("@ATTRIBUTE" + "\t" + "attr" + lexiconList.size() + "\t" + "NUMERIC" + "\n" );
				bw6.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 1) + "\t" + "NUMERIC" + "\n" );
				bw6.write("@ATTRIBUTE" + "\t" + "attr" + (lexiconList.size() + 2) + "\t" + "NUMERIC" + "\n" );

				bw1.write("@ATTRIBUTE" + "\t" + "ContinuingNews" + "\t" + "{\"continuingnews\",\"notcontinuingnews\"}" + "\n" );
				bw2.write("@ATTRIBUTE" + "\t" + "Donations" + "\t" + "{\"donations\",\"notdonations\"}" + "\n" );
				bw3.write("@ATTRIBUTE" + "\t" + "Official" + "\t" + "{\"official\",\"notofficial\"}" + "\n" );
				bw4.write("@ATTRIBUTE" + "\t" + "Advice" + "\t" + "{\"advice\",\"notadvice\"}" + "\n" );
				bw5.write("@ATTRIBUTE" + "\t" + "MultimediaShare" + "\t" + "{\"multi\",\"notmulti\"}" + "\n" );
				bw6.write("@ATTRIBUTE" + "\t" + "GoodsServices" + "\t" + "{\"goods\",\"notgoods\"}" + "\n" );

				bw1.write("@DATA" + "\n");
				bw2.write("@DATA" + "\n");
				bw3.write("@DATA" + "\n");
				bw4.write("@DATA" + "\n");
				bw5.write("@DATA" + "\n");
				bw6.write("@DATA" + "\n");

				for (int i = 0; i < arffSelect.size(); i++) {		
					bw1.write("{");
					bw2.write("{");
					bw3.write("{");
					bw4.write("{");
					bw5.write("{");
					bw6.write("{");
					ArrayList<Integer> existingInteger = new ArrayList<Integer>();					

					for (int j = 0; j < arffSelect.get(i).size() - 9; j++)
					{						
						int no = (int) arffSelect.get(i).get(j);						

						if (!existingInteger.contains(no))
						{
							bw1.write(String.valueOf(no));
							bw1.write("\t");
							bw2.write(String.valueOf(no));
							bw2.write("\t");
							bw3.write(String.valueOf(no));
							bw3.write("\t");
							bw4.write(String.valueOf(no));
							bw4.write("\t");
							bw5.write(String.valueOf(no));
							bw5.write("\t");
							bw6.write(String.valueOf(no));
							bw6.write("\t");

							int wordIndex = wordLexiconList.indexOf(no);

							// TF IDF 1 
							double tf = 1.0 * Collections.frequency(arffSelect.get(i), no);
							double idf = Math.log(1.0 * tweetCount / tweetList.get(wordIndex));
							double tfidf = tf*idf;							
							tfidf = Math.round(tfidf * 100.0) / 100.0;
							bw1.write(String.valueOf(tfidf) + ",");
							bw2.write(String.valueOf(tfidf) + ",");
							bw3.write(String.valueOf(tfidf) + ",");
							bw4.write(String.valueOf(tfidf) + ",");
							bw5.write(String.valueOf(tfidf) + ",");
							bw6.write(String.valueOf(tfidf) + ",");

							bw1.write("\t");
							bw2.write("\t");
							bw3.write("\t");
							bw4.write("\t");
							bw5.write("\t");
							bw6.write("\t");
							existingInteger.add(no);
						}
					}

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 9).equals("verified"))
					{
						bw1.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw2.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw3.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw4.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw5.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
						bw6.write(String.valueOf(lexiconList.size()) + "\t" + "1.0," + "\t");
					}

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 8).equals("hasmedia"))
					{
						bw1.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw2.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw3.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw4.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw5.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
						bw6.write(String.valueOf(lexiconList.size() + 1) + "\t" + "1.0," + "\t");
					}

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 7).equals("desc"))
					{
						bw1.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw2.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw3.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw4.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw5.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
						bw6.write(String.valueOf(lexiconList.size() + 2) + "\t" + "1.0," + "\t");
					}

					bw1.write(String.valueOf(lexiconList.size() + 3));
					bw1.write("\t");
					bw2.write(String.valueOf(lexiconList.size() + 3));
					bw2.write("\t");
					bw3.write(String.valueOf(lexiconList.size() + 3));
					bw3.write("\t");
					bw4.write(String.valueOf(lexiconList.size() + 3));
					bw4.write("\t");
					bw5.write(String.valueOf(lexiconList.size() + 3));
					bw5.write("\t");
					bw6.write(String.valueOf(lexiconList.size() + 3));
					bw6.write("\t");				

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 6) == "continuingnews")
						bw1.write("\"continuingnews\"}" + "\n");
					else
						bw1.write("\"notcontinuingnews\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 5) == "donations")
						bw2.write("\"donations\"}" + "\n");
					else
						bw2.write("\"notdonations\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 4) == "official")
						bw3.write("\"official\"}" + "\n");
					else
						bw3.write("\"notofficial\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 3) == "advice")
						bw4.write("\"advice\"}" + "\n");
					else
						bw4.write("\"notadvice\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 2) == "multi")
						bw5.write("\"multi\"}" + "\n");
					else
						bw5.write("\"notmulti\"}" + "\n");

					if (arffSelect.get(i).get(arffSelect.get(i).size() - 1) == "goods")
						bw6.write("\"goods\"}" + "\n");
					else
						bw6.write("\"notgoods\"}" + "\n");


				}	
				bw1.close();
				bw2.close();
				bw3.close();
				bw4.close();
				bw5.close();
				bw6.close();
			} catch (Exception e) {e.printStackTrace();}
		}

		steppingStone = 0;
		for (steppingStone = 0; steppingStone < 90; steppingStone++)
		{
			String trainingData;
			String testData;
			String resultText;

			switch (steppingStone)
			{
			case 0: trainingData = "2011JoplinTrainCont.arff"; break;
			case 1: trainingData = "2011JoplinTrainDona.arff"; break;
			case 2: trainingData = "2011JoplinTrainOffi.arff"; break;
			case 3: trainingData = "2011JoplinTrainAdvi.arff"; break;
			case 4: trainingData = "2011JoplinTrainMult.arff"; break;
			case 5: trainingData = "2011JoplinTrainGood.arff"; break;
			case 6: trainingData = "2012GuatemalaTrainCont.arff"; break;
			case 7: trainingData = "2012GuatemalaTrainDona.arff"; break;
			case 8: trainingData = "2012GuatemalaTrainOffi.arff"; break;
			case 9: trainingData = "2012GuatemalaTrainAdvi.arff"; break;
			case 10: trainingData = "2012GuatemalaTrainMult.arff"; break;
			case 11: trainingData = "2012GuatemalaTrainGood.arff"; break;
			case 12: trainingData = "2012ItalyTrainCont.arff"; break;
			case 13: trainingData = "2012ItalyTrainDona.arff"; break;
			case 14: trainingData = "2012ItalyTrainOffi.arff"; break;
			case 15: trainingData = "2012ItalyTrainAdvi.arff"; break;
			case 16: trainingData = "2012ItalyTrainMult.arff"; break;
			case 17: trainingData = "2012ItalyTrainGood.arff"; break;
			case 18: trainingData = "2012PhilipinneTrainCont.arff"; break;
			case 19: trainingData = "2012PhilipinneTrainDona.arff"; break;
			case 20: trainingData = "2012PhilipinneTrainOffi.arff"; break;
			case 21: trainingData = "2012PhilipinneTrainAdvi.arff"; break;
			case 22: trainingData = "2012PhilipinneTrainMult.arff"; break;
			case 23: trainingData = "2012PhilipinneTrainGood.arff"; break;
			case 24: trainingData = "2013AlbertaTrainCont.arff"; break;
			case 25: trainingData = "2013AlbertaTrainDona.arff"; break;
			case 26: trainingData = "2013AlbertaTrainOffi.arff"; break;
			case 27: trainingData = "2013AlbertaTrainAdvi.arff"; break;
			case 28: trainingData = "2013AlbertaTrainMult.arff"; break;
			case 29: trainingData = "2013AlbertaTrainGood.arff"; break;
			case 30: trainingData = "2013AustraliaTrainCont.arff"; break;
			case 31: trainingData = "2013AustraliaTrainDona.arff"; break;
			case 32: trainingData = "2013AustraliaTrainOffi.arff"; break;
			case 33: trainingData = "2013AustraliaTrainAdvi.arff"; break;
			case 34: trainingData = "2013AustraliaTrainMult.arff"; break;
			case 35: trainingData = "2013AustraliaTrainGood.arff"; break;
			case 36: trainingData = "2013BostonTrainCont.arff"; break;
			case 37: trainingData = "2013BostonTrainDona.arff"; break;
			case 38: trainingData = "2013BostonTrainOffi.arff"; break;
			case 39: trainingData = "2013BostonTrainAdvi.arff"; break;
			case 40: trainingData = "2013BostonTrainMult.arff"; break;
			case 41: trainingData = "2013BostonTrainGood.arff"; break;
			case 42: trainingData = "2013ManilaTrainCont.arff"; break;
			case 43: trainingData = "2013ManilaTrainDona.arff"; break;
			case 44: trainingData = "2013ManilaTrainOffi.arff"; break;
			case 45: trainingData = "2013ManilaTrainAdvi.arff"; break;
			case 46: trainingData = "2013ManilaTrainMult.arff"; break;
			case 47: trainingData = "2013ManilaTrainGood.arff"; break;
			case 48: trainingData = "2013QueensTrainCont.arff"; break;
			case 49: trainingData = "2013QueensTrainDona.arff"; break;
			case 50: trainingData = "2013QueensTrainOffi.arff"; break;
			case 51: trainingData = "2013QueensTrainAdvi.arff"; break;
			case 52: trainingData = "2013QueensTrainMult.arff"; break;
			case 53: trainingData = "2013QueensTrainGood.arff"; break;
			case 54: trainingData = "2013YolandaTrainCont.arff"; break;
			case 55: trainingData = "2013YolandaTrainDona.arff"; break;
			case 56: trainingData = "2013YolandaTrainOffi.arff"; break;
			case 57: trainingData = "2013YolandaTrainAdvi.arff"; break;
			case 58: trainingData = "2013YolandaTrainMult.arff"; break;
			case 59: trainingData = "2013YolandaTrainGood.arff"; break;
			case 60: trainingData = "2014ChileTrainCont.arff"; break;
			case 61: trainingData = "2014ChileTrainDona.arff"; break;
			case 62: trainingData = "2014ChileTrainOffi.arff"; break;
			case 63: trainingData = "2014ChileTrainAdvi.arff"; break;
			case 64: trainingData = "2014ChileTrainMult.arff"; break;
			case 65: trainingData = "2014ChileTrainGood.arff"; break;
			case 66: trainingData = "2014HagupitTrainCont.arff"; break;
			case 67: trainingData = "2014HagupitTrainDona.arff"; break;
			case 68: trainingData = "2014HagupitTrainOffi.arff"; break;
			case 69: trainingData = "2014HagupitTrainAdvi.arff"; break;
			case 70: trainingData = "2014HagupitTrainMult.arff"; break;
			case 71: trainingData = "2014HagupitTrainGood.arff"; break;
			case 72: trainingData = "2015NepalTrainCont.arff"; break;
			case 73: trainingData = "2015NepalTrainDona.arff"; break;
			case 74: trainingData = "2015NepalTrainOffi.arff"; break;
			case 75: trainingData = "2015NepalTrainAdvi.arff"; break;
			case 76: trainingData = "2015NepalTrainMult.arff"; break;
			case 77: trainingData = "2015NepalTrainGood.arff"; break;
			case 78: trainingData = "2015ParisTrainCont.arff"; break;
			case 79: trainingData = "2015ParisTrainDona.arff"; break;
			case 80: trainingData = "2015ParisTrainOffi.arff"; break;
			case 81: trainingData = "2015ParisTrainAdvi.arff"; break;
			case 82: trainingData = "2015ParisTrainMult.arff"; break;
			case 83: trainingData = "2015ParisTrainGood.arff"; break;
			case 84: trainingData = "2018FloridaTrainCont.arff"; break;
			case 85: trainingData = "2018FloridaTrainDona.arff"; break;
			case 86: trainingData = "2018FloridaTrainOffi.arff"; break;
			case 87: trainingData = "2018FloridaTrainAdvi.arff"; break;
			case 88: trainingData = "2018FloridaTrainMult.arff"; break;
			default: trainingData = "2018FloridaTrainGood.arff";
			}

			switch (steppingStone)
			{
			case 0: testData = "2011JoplinTestCont.arff"; break;
			case 1: testData = "2011JoplinTestDona.arff"; break;
			case 2: testData = "2011JoplinTestOffi.arff"; break;
			case 3: testData = "2011JoplinTestAdvi.arff"; break;
			case 4: testData = "2011JoplinTestMult.arff"; break;
			case 5: testData = "2011JoplinTestGood.arff"; break;
			case 6: testData = "2012GuatemalaTestCont.arff"; break;
			case 7: testData = "2012GuatemalaTestDona.arff"; break;
			case 8: testData = "2012GuatemalaTestOffi.arff"; break;
			case 9: testData = "2012GuatemalaTestAdvi.arff"; break;
			case 10: testData = "2012GuatemalaTestMult.arff"; break;
			case 11: testData = "2012GuatemalaTestGood.arff"; break;
			case 12: testData = "2012ItalyTestCont.arff"; break;
			case 13: testData = "2012ItalyTestDona.arff"; break;
			case 14: testData = "2012ItalyTestOffi.arff"; break;
			case 15: testData = "2012ItalyTestAdvi.arff"; break;
			case 16: testData = "2012ItalyTestMult.arff"; break;
			case 17: testData = "2012ItalyTestGood.arff"; break;
			case 18: testData = "2012PhilipinneTestCont.arff"; break;
			case 19: testData = "2012PhilipinneTestDona.arff"; break;
			case 20: testData = "2012PhilipinneTestOffi.arff"; break;
			case 21: testData = "2012PhilipinneTestAdvi.arff"; break;
			case 22: testData = "2012PhilipinneTestMult.arff"; break;
			case 23: testData = "2012PhilipinneTestGood.arff"; break;
			case 24: testData = "2013AlbertaTestCont.arff"; break;
			case 25: testData = "2013AlbertaTestDona.arff"; break;
			case 26: testData = "2013AlbertaTestOffi.arff"; break;
			case 27: testData = "2013AlbertaTestAdvi.arff"; break;
			case 28: testData = "2013AlbertaTestMult.arff"; break;
			case 29: testData = "2013AlbertaTestGood.arff"; break;
			case 30: testData = "2013AustraliaTestCont.arff"; break;
			case 31: testData = "2013AustraliaTestDona.arff"; break;
			case 32: testData = "2013AustraliaTestOffi.arff"; break;
			case 33: testData = "2013AustraliaTestAdvi.arff"; break;
			case 34: testData = "2013AustraliaTestMult.arff"; break;
			case 35: testData = "2013AustraliaTestGood.arff"; break;
			case 36: testData = "2013BostonTestCont.arff"; break;
			case 37: testData = "2013BostonTestDona.arff"; break;
			case 38: testData = "2013BostonTestOffi.arff"; break;
			case 39: testData = "2013BostonTestAdvi.arff"; break;
			case 40: testData = "2013BostonTestMult.arff"; break;
			case 41: testData = "2013BostonTestGood.arff"; break;
			case 42: testData = "2013ManilaTestCont.arff"; break;
			case 43: testData = "2013ManilaTestDona.arff"; break;
			case 44: testData = "2013ManilaTestOffi.arff"; break;
			case 45: testData = "2013ManilaTestAdvi.arff"; break;
			case 46: testData = "2013ManilaTestMult.arff"; break;
			case 47: testData = "2013ManilaTestGood.arff"; break;
			case 48: testData = "2013QueensTestCont.arff"; break;
			case 49: testData = "2013QueensTestDona.arff"; break;
			case 50: testData = "2013QueensTestOffi.arff"; break;
			case 51: testData = "2013QueensTestAdvi.arff"; break;
			case 52: testData = "2013QueensTestMult.arff"; break;
			case 53: testData = "2013QueensTestGood.arff"; break;
			case 54: testData = "2013YolandaTestCont.arff"; break;
			case 55: testData = "2013YolandaTestDona.arff"; break;
			case 56: testData = "2013YolandaTestOffi.arff"; break;
			case 57: testData = "2013YolandaTestAdvi.arff"; break;
			case 58: testData = "2013YolandaTestMult.arff"; break;
			case 59: testData = "2013YolandaTestGood.arff"; break;
			case 60: testData = "2014ChileTestCont.arff"; break;
			case 61: testData = "2014ChileTestDona.arff"; break;
			case 62: testData = "2014ChileTestOffi.arff"; break;
			case 63: testData = "2014ChileTestAdvi.arff"; break;
			case 64: testData = "2014ChileTestMult.arff"; break;
			case 65: testData = "2014ChileTestGood.arff"; break;
			case 66: testData = "2014HagupitTestCont.arff"; break;
			case 67: testData = "2014HagupitTestDona.arff"; break;
			case 68: testData = "2014HagupitTestOffi.arff"; break;
			case 69: testData = "2014HagupitTestAdvi.arff"; break;
			case 70: testData = "2014HagupitTestMult.arff"; break;
			case 71: testData = "2014HagupitTestGood.arff"; break;
			case 72: testData = "2015NepalTestCont.arff"; break;
			case 73: testData = "2015NepalTestDona.arff"; break;
			case 74: testData = "2015NepalTestOffi.arff"; break;
			case 75: testData = "2015NepalTestAdvi.arff"; break;
			case 76: testData = "2015NepalTestMult.arff"; break;
			case 77: testData = "2015NepalTestGood.arff"; break;
			case 78: testData = "2015ParisTestCont.arff"; break;
			case 79: testData = "2015ParisTestDona.arff"; break;
			case 80: testData = "2015ParisTestOffi.arff"; break;
			case 81: testData = "2015ParisTestAdvi.arff"; break;
			case 82: testData = "2015ParisTestMult.arff"; break;
			case 83: testData = "2015ParisTestGood.arff"; break;
			case 84: testData = "2018FloridaTestCont.arff"; break;
			case 85: testData = "2018FloridaTestDona.arff"; break;
			case 86: testData = "2018FloridaTestOffi.arff"; break;
			case 87: testData = "2018FloridaTestAdvi.arff"; break;
			case 88: testData = "2018FloridaTestMult.arff"; break;
			default: testData = "2018FloridaTestGood.arff";
			}

			switch (steppingStone)
			{
			case 0: resultText = "2011JoplinResultsCont.txt"; break;
			case 1: resultText = "2011JoplinResultsDona.txt"; break;
			case 2: resultText = "2011JoplinResultsOffi.txt"; break;
			case 3: resultText = "2011JoplinResultsAdvi.txt"; break;
			case 4: resultText = "2011JoplinResultsMult.txt"; break;
			case 5: resultText = "2011JoplinResultsGood.txt"; break;
			case 6: resultText = "2012GuatemalaResultsCont.txt"; break;
			case 7: resultText = "2012GuatemalaResultsDona.txt"; break;
			case 8: resultText = "2012GuatemalaResultsOffi.txt"; break;
			case 9: resultText = "2012GuatemalaResultsAdvi.txt"; break;
			case 10: resultText = "2012GuatemalaResultsMult.txt"; break;
			case 11: resultText = "2012GuatemalaResultsGood.txt"; break;
			case 12: resultText = "2012ItalyResultsCont.txt"; break;
			case 13: resultText = "2012ItalyResultsDona.txt"; break;
			case 14: resultText = "2012ItalyResultsOffi.txt"; break;
			case 15: resultText = "2012ItalyResultsAdvi.txt"; break;
			case 16: resultText = "2012ItalyResultsMult.txt"; break;
			case 17: resultText = "2012ItalyResultsGood.txt"; break;
			case 18: resultText = "2012PhilipinneResultsCont.txt"; break;
			case 19: resultText = "2012PhilipinneResultsDona.txt"; break;
			case 20: resultText = "2012PhilipinneResultsOffi.txt"; break;
			case 21: resultText = "2012PhilipinneResultsAdvi.txt"; break;
			case 22: resultText = "2012PhilipinneResultsMult.txt"; break;
			case 23: resultText = "2012PhilipinneResultsGood.txt"; break;
			case 24: resultText = "2013AlbertaResultsCont.txt"; break;
			case 25: resultText = "2013AlbertaResultsDona.txt"; break;
			case 26: resultText = "2013AlbertaResultsOffi.txt"; break;
			case 27: resultText = "2013AlbertaResultsAdvi.txt"; break;
			case 28: resultText = "2013AlbertaResultsMult.txt"; break;
			case 29: resultText = "2013AlbertaResultsGood.txt"; break;
			case 30: resultText = "2013AustraliaResultsCont.txt"; break;
			case 31: resultText = "2013AustraliaResultsDona.txt"; break;
			case 32: resultText = "2013AustraliaResultsOffi.txt"; break;
			case 33: resultText = "2013AustraliaResultsAdvi.txt"; break;
			case 34: resultText = "2013AustraliaResultsMult.txt"; break;
			case 35: resultText = "2013AustraliaResultsGood.txt"; break;
			case 36: resultText = "2013BostonResultsCont.txt"; break;
			case 37: resultText = "2013BostonResultsDona.txt"; break;
			case 38: resultText = "2013BostonResultsOffi.txt"; break;
			case 39: resultText = "2013BostonResultsAdvi.txt"; break;
			case 40: resultText = "2013BostonResultsMult.txt"; break;
			case 41: resultText = "2013BostonResultsGood.txt"; break;
			case 42: resultText = "2013ManilaResultsCont.txt"; break;
			case 43: resultText = "2013ManilaResultsDona.txt"; break;
			case 44: resultText = "2013ManilaResultsOffi.txt"; break;
			case 45: resultText = "2013ManilaResultsAdvi.txt"; break;
			case 46: resultText = "2013ManilaResultsMult.txt"; break;
			case 47: resultText = "2013ManilaResultsGood.txt"; break;
			case 48: resultText = "2013QueensResultsCont.txt"; break;
			case 49: resultText = "2013QueensResultsDona.txt"; break;
			case 50: resultText = "2013QueensResultsOffi.txt"; break;
			case 51: resultText = "2013QueensResultsAdvi.txt"; break;
			case 52: resultText = "2013QueensResultsMult.txt"; break;
			case 53: resultText = "2013QueensResultsGood.txt"; break;
			case 54: resultText = "2013YolandaResultsCont.txt"; break;
			case 55: resultText = "2013YolandaResultsDona.txt"; break;
			case 56: resultText = "2013YolandaResultsOffi.txt"; break;
			case 57: resultText = "2013YolandaResultsAdvi.txt"; break;
			case 58: resultText = "2013YolandaResultsMult.txt"; break;
			case 59: resultText = "2013YolandaResultsGood.txt"; break;
			case 60: resultText = "2014ChileResultsCont.txt"; break;
			case 61: resultText = "2014ChileResultsDona.txt"; break;
			case 62: resultText = "2014ChileResultsOffi.txt"; break;
			case 63: resultText = "2014ChileResultsAdvi.txt"; break;
			case 64: resultText = "2014ChileResultsMult.txt"; break;
			case 65: resultText = "2014ChileResultsGood.txt"; break;
			case 66: resultText = "2014HagupitResultsCont.txt"; break;
			case 67: resultText = "2014HagupitResultsDona.txt"; break;
			case 68: resultText = "2014HagupitResultsOffi.txt"; break;
			case 69: resultText = "2014HagupitResultsAdvi.txt"; break;
			case 70: resultText = "2014HagupitResultsMult.txt"; break;
			case 71: resultText = "2014HagupitResultsGood.txt"; break;
			case 72: resultText = "2015NepalResultsCont.txt"; break;
			case 73: resultText = "2015NepalResultsDona.txt"; break;
			case 74: resultText = "2015NepalResultsOffi.txt"; break;
			case 75: resultText = "2015NepalResultsAdvi.txt"; break;
			case 76: resultText = "2015NepalResultsMult.txt"; break;
			case 77: resultText = "2015NepalResultsGood.txt"; break;
			case 78: resultText = "2015ParisResultsCont.txt"; break;
			case 79: resultText = "2015ParisResultsDona.txt"; break;
			case 80: resultText = "2015ParisResultsOffi.txt"; break;
			case 81: resultText = "2015ParisResultsAdvi.txt"; break;
			case 82: resultText = "2015ParisResultsMult.txt"; break;
			case 83: resultText = "2015ParisResultsGood.txt"; break;
			case 84: resultText = "2018FloridaResultsCont.txt"; break;
			case 85: resultText = "2018FloridaResultsDona.txt"; break;
			case 86: resultText = "2018FloridaResultsOffi.txt"; break;
			case 87: resultText = "2018FloridaResultsAdvi.txt"; break;
			case 88: resultText = "2018FloridaResultsMult.txt"; break;
			default: resultText = "2018FloridaResultsGood.txt";
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
