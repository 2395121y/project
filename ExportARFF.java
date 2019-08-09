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
				//"assr4.test.gz",
				//"assr5.test.gz",
				//"assr6.test.gz"
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
		ArrayList<ArrayList<ArrayList>> arffListComplete = new ArrayList<ArrayList<ArrayList>>();
		//ArrayList<ArrayList<String>> wordListComplete = new ArrayList<ArrayList<String>> ();
		ArrayList<ArrayList<Integer>> wordLexiconListComplete = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> tweetListComplete = new ArrayList<ArrayList<Integer>>();

		// How many scenarios are you loading? Change amount for SteppingStone!
		int steppingStone;

		//BufferedWriter bwa = new BufferedWriter(new FileWriter("ContinuingNewsIDs2.txt"));
		for (steppingStone = 0; steppingStone < 5; steppingStone++)
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

						case 6:
							if (line.equals("{\"eventid\": \"italyEarthquakes2012\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"queenslandFloods2013\","))
								foundFlood = false;
							break;

						case 4:
							if (line.equals("{\"eventid\": \"philipinnesFloods2012\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"albertaFloods2013\","))
								foundFlood = false;
							break;

						case 5:
							if (line.equals("{\"eventid\": \"albertaFloods2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"nepalEarthquake2015\","))
								foundFlood = false;
							break;

						case 3:
							if (line.equals("{\"eventid\": \"australiaBushfire2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"philipinnesFloods2012\","))
								foundFlood = false;
							break;

						case 2:
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
						}

						/*
						if (steppingStone == 0)
						{
							if (line.equals("{\"eventid\": \"guatemalaEarthquake2012\","))
								foundFlood = true;


							if (line.equals("{\"eventid\": \"bostonBombings2013\","))
								foundFlood = true;


							if (line.equals("{\"eventid\": \"flSchoolShooting2018\","))
								foundFlood = true;
						}
						else
						{
							if (line.equals("{\"eventid\": \"guatemalaEarthquake2012\","))
								foundFlood = false;
						}

						// Assr2

						if (steppingStone == 0)
						{
							if (line.equals("{\"eventid\": \"chileEarthquake2014\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"joplinTornado2011\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"typhoonYolanda2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"queenslandFloods2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"nepalEarthquake2015S3\","))
								foundFlood = true;
						}
						else
						{
							if (line.equals("{\"eventid\": \"chileEarthquake2014\","))
								foundFlood = false;
						}

						// Assr3

						if (steppingStone == 0)
						{
							if (line.equals("{\"eventid\": \"australiaBushfire2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"philipinnesFloods2012\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"albertaFloods2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"nepalEarthquake2015\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"typhoonHagupit2014S2\","))
								foundFlood = true;
						}
						else
						{
							if (line.equals("{\"eventid\": \"australiaBushfire2013\","))
								foundFlood = false;

							if (line.equals("{\"eventid\": \"philipinnesFloods2012\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"albertaFloods2013\","))
								foundFlood = false;
						}


						// Assr4

						if (steppingStone == 0)
						{
							if (line.equals("{\"eventid\": \"manilaFloods2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"parisAttacks2015\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"italyEarthquakes2012\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"queenslandFloods2013\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"fireColorado2012\","))
								foundFlood = true;
						}
						else
						{
							if (line.equals("{\"eventid\": \"manilaFloods2013\","))
								foundFlood = false;
						}

						// Assr5

						if (steppingStone == 0)
						{
							if (line.equals("{\"eventid\": \"typhoonHagupit2014\","))
								foundFlood = true;
						}
						else
						{
							if (line.equals("{\"eventid\": \"typhoonHagupit2014\","))
								foundFlood = false;
						}

						// Assr6

						if (steppingStone == 0)
						{
							if (line.equals("{\"eventid\": \"nepalEarthquake2015\","))
								foundFlood = true;

							if (line.equals("{\"eventid\": \"typhoonHagupit2014S1\","))
								foundFlood = true;
						}
						else
						{
							if (line.equals("{\"eventid\": \"nepalEarthquake2015\","))
								foundFlood = false;
						}
						 */

						/*
						if (foundFlood)
						{
							if (line.contains("eventid"))
								System.out.println(line);

							if(line.contains("postID"))
							{
								String [] extractIDString = line.split("\"postID\" : \"");
								extractIDString[1] = extractIDString[1].substring(0, extractIDString[1].length() - 2);
								//System.out.println(extractIDString[1]);
								possibleID = new BigInteger(extractIDString[1]);

							}


							if (line.contains("categories") && line.contains("ContinuingNews"))
							{
								continuingNewsID.add(possibleID);
								//isItContinue = true;
								//System.out.println(possibleID);
							}
						 */

						/*
							if (line.contains("categories") && line.contains("Donations"))
							{
								continuingNewsID.add(possibleID);
								isItContinue = true;
							}
						 */

						/*
							if (line.contains("categories") && line.contains("Official"))
							{
								continuingNewsID.add(possibleID);
								isItContinue = true;
							}
						 */

						/*
							if (line.contains("categories") && line.contains("Advice"))
							{
								continuingNewsID.add(possibleID);
								isItContinue = true;
							}
						 */

						/*
							if (line.contains("categories") && line.contains("MultimediaShare"))
							{
								continuingNewsID.add(possibleID);
								isItContinue = true;
							}
						 */

						/*
							if (line.contains("categories") && line.contains("GoodsServices"))
							{								
								continuingNewsID.add(possibleID);
								isItContinue = true;
							}
						 */

						/*
							if (line.contains("\"text\"") && isItContinue)
							{
								String [] extractTextString = line.split("\"text\" : \"");
								extractTextString[1] = extractTextString[1].substring(0, extractTextString[1].length() - 1);
								//System.out.println(extractTextString[1]);
								continuingNewsList.add(extractTextString[1]);
								isItContinue = false;
							}
						 */
					}
					//}
					br.close();
				}
				catch (Exception e) { e.printStackTrace(); }
			}	

			//continuingNewsIDList.add(continuingNewsID);
			System.out.println("Next one...");
		}
		
		//bwa.close();
		/*
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("ContinuingNewsIDs.txt"));

				for (int i = 0; i < continuingNewsID.size(); i++) {		
					bw.write(continuingNewsID.get(i) + "\n");

				}

				bw.close();
			} catch (Exception e) {e.printStackTrace();}
		 */

		/*
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("GoodsServicesList.txt"));

				for (int i = 0; i < continuingNewsList.size(); i++) {		
					bw.write(continuingNewsList.get(i) + "\n");

				}

				bw.close();
			} catch (Exception e) {e.printStackTrace();}
		 */



		//ArrayList<Integer> wordCountList = new ArrayList<Integer>();

		//ArrayList<String> continuingNewsList = new ArrayList<String>();
		int tokenCountTotal = 0;

		steppingStone = 0;
		
		//BufferedWriter bwb = new BufferedWriter(new FileWriter("FullIDs2.txt"));

		for (steppingStone = 0; steppingStone < 5; steppingStone++)
		{
			System.out.println("Currently in Stepping Stone.. #" + steppingStone);
			String[] files2;


			switch (steppingStone)
			{
			case 0: files2 = new String[] {"2011_Joplin_tornado.json.gz"}; break;
			case 1: files2 = new String[] {"2012_Guatemala_earthquake.json.gz"}; break;
			case 6: files2 = new String[] {"2012_Italy_earthquakes.json.gz"}; break;
			case 4: files2 = new String[] {"2012_Philipinnes_floods.json.gz"}; break;
			case 5: files2 = new String[] {"2013_Alberta_floods.json.gz"}; break;
			case 3: files2 = new String[] {"2013_Australia_bushfire.json.gz"}; break;
			case 2: files2 = new String[] {"2013_Boston_bombings.json.gz"}; break;
			case 7: files2 = new String[] {"2013_Manila_floods.json.gz"}; break;
			case 8: files2 = new String[] {"2013_Queensland_floods.json.gz"}; break;
			case 9: files2 = new String[] {"2013_Typhoon_Yolanda.json.gz"}; break;
			case 10: files2 = new String[] {"2014_Chile_Earthquake.json.gz"}; break;
			case 11: files2 = new String[] {"2014_Typhoon_Hagupit.json.gz"}; break;
			case 12: files2 = new String[] {"2015_Nepal_Earthquake.json.gz"}; break;
			case 13: files2 = new String[] {"2015_Paris_Attacks.json.gz"}; break;
			default: files2 = new String[] {"2018_FL_School_Shooting.json.gz"};

			}

			/*
			if (steppingStone == 0)
			{
				files2 = new String[]{

						"2011_Joplin_tornado.json.gz",
						"2012_Guatemala_earthquake.json.gz",					
						"2012_Italy_earthquakes.json.gz",
						//"2012_Philipinnes_floods.json.gz",					
						"2013_Alberta_floods.json.gz",
						"2013_Australia_bushfire.json.gz",
						"2013_Boston_bombings.json.gz",
						"2013_Manila_floods.json.gz",
						"2013_Queensland_floods.json.gz",
						"2013_Typhoon_Yolanda.json.gz",
						"2014_Chile_Earthquake.json.gz",
						"2014_Typhoon_Hagupit.json.gz",
						"2015_Nepal_Earthquake.json.gz",					
						"2015_Paris_Attacks.json.gz",
						"2018_FL_School_Shooting.json.gz"					 
				};
			}
			else
			{
				files2 = new String[]{											
						"2012_Philipinnes_floods.json.gz",														 
				};
			}
			 */

			ArrayList<ArrayList> arffList = new ArrayList<ArrayList>();		
			ArrayList<String> wordList = new ArrayList<String>();
			ArrayList<Integer> wordLexiconList = new ArrayList<Integer>();
			ArrayList<Integer> tweetList = new ArrayList<Integer>();

			int tweetCount = 0;

			for (String file : files2) {
				{

					try {
						BufferedReader br= new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))));
						String line;	

						while ((line = br.readLine())!=null) {

							boolean continuingNews = false;
							boolean isVerified = false;
							boolean hasMedia = false;
							boolean userDescription = false;

							ArrayList<String> repeatedTokens = new ArrayList<String>();
							String [] extractTextString = line.split("\"text\":\"");							
							String [] extractActualTextString = extractTextString[1].split("\",\"");							
							String actualText = extractActualTextString[0];
							StringTokenizer textTokenizer = new StringTokenizer(extractActualTextString[0]);

							String [] extractVerifiedString = line.split("\"verified\":");							
							String [] extractVerifiedTextString = extractVerifiedString[1].split(",");	

							String [] extractIDString = line.split("\"id_str\":");
							String [] extractActualIDString = extractIDString[1].split(",");
							extractActualIDString[0] = extractActualIDString[0].substring(1, extractActualIDString[0].length()-1);
							
							/*
							//if extractActualIDString[0].contains("}")
							int counti = 2;

							while (extractActualIDString[0].contains("}"))
							{
								//System.out.println(extractActualIDString[0]);
								extractActualIDString = extractIDString[counti].split(",");
								//System.out.println(extractActualIDString[0]);
								counti++;
							}
							*/
							
							//if extractActualIDString[0].contains("}")
							int counti = 2;
							int countii = 2;
							
							//if (steppingStone == 1)
							//{
							//	System.out.println(extractActualIDString[0]);
							//}

							while ((extractActualIDString[0].contains("}")) || (extractActualIDString[0].contains("\"")))
							{
								//if (steppingStone == 1)
								//{
								//	System.out.println(extractActualIDString[0]);
								//}
								
								if (counti == extractActualIDString.length)
								{
									counti = 2;
									extractActualIDString = extractIDString[countii].split(",");
									extractActualIDString[0] = extractActualIDString[0].substring(1, extractActualIDString[0].length()-1);
									countii++;
								}
								else
								{
									//System.out.println(extractActualIDString[0]);
									extractActualIDString = extractIDString[counti].split(",");
									//System.out.println(extractActualIDString[0]);
									counti++;
									extractActualIDString[0] = extractActualIDString[0].substring(1, extractActualIDString[0].length()-1);
								}
								
							}

							BigInteger testID = new BigInteger (extractActualIDString[0]);
							//bwb.write(String.valueOf(testID) + "\n");

							if (continuingNewsID.contains(testID))
								continuingNews = true;
							else
								continuingNews = false;

							if (line.contains("media_url"))
								hasMedia = true;


							String [] descriptionString = line.split("\"user\":");
							String [] descriptionTextString = descriptionString[1].split("\"description\":\"");	
							String [] descriptionTextString2 = descriptionTextString[1].split("\",");
							descriptionTextString2[0].toLowerCase();
							//System.out.println(descriptionTextString2[0]);						

							if (extractVerifiedTextString[0].equals("true"))
								isVerified = true;
							else
								isVerified = false;

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
										//System.out.println(token);
										String realString = new String();
										realString = UrlCleaner.unshortenUrl(token);
										StringTokenizer textTokenizerURL = new StringTokenizer(realString);
										//int indexWordCount = wordCountList.get(tweetCount);
										//wordCountList.set(tweetCount, indexWordCount - 1 + textTokenizerURL.countTokens());
										//String [] testURL = token.split("//");
										//System.out.println(testURL[1]);
										//String [] testURL2 = realString.split("//");
										//System.out.println(testURL2[1]);
										//if (!testURL[1].equals(testURL2[1]))
										//	System.out.println(testURL2[1]);

										String tokenURL = TextUtils.normaliseStringIgnore(textTokenizerURL.nextToken());
										String [] tokensURL = tokenURL.split(" ");

										for (int i = 0; i < tokensURL.length; i++)
										{	
											if (tokensURL[i]!=null && tokensURL[i].length()>0 && lexiconList.contains(tokensURL[i]) && !wordList.contains(tokensURL[i]))
											{
												wordList.add(tokensURL[i]);
												//wordCountList.add(1);
												tweetList.add(1);
												wordLexiconList.add(lexiconList.indexOf(tokensURL[i]));
											}
											else if (wordList.contains(tokensURL[i]))
											{
												int indexToken = wordList.indexOf(tokensURL[i]);
												//int currentCount = wordCountList.get(indexToken);
												//wordCountList.set(indexToken, currentCount + 1);

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
											//baa.write("ERROR: " + e.getMessage() + "\n");
											//baa.write("\n");
										}
									}										
									else if (lexiconList.contains(token))
									{
										//System.out.print(lexiconList.indexOf(token)+1 + " ");
										arr.add(lexiconList.indexOf(token));

										if (!wordList.contains(token))
										{
											wordList.add(token);
											//wordCountList.add(1);
											tweetList.add(1);
											wordLexiconList.add(lexiconList.indexOf(token));
										}
										else
										{
											int indexToken = wordList.indexOf(token);
											//int currentCount = wordCountList.get(indexToken);
											//wordCountList.set(indexToken, currentCount + 1);

											if (!repeatedTokens.contains(token))
											{
												repeatedTokens.add(token);
												int currentCountTweet = tweetList.get(indexToken);
												tweetList.set(indexToken, currentCountTweet + 1);
											}
										}

										//if (continuingNews)
										//	System.out.println(descriptionTextString2[0]);

										if ( (descriptionTextString2[0].contains("news")) || (descriptionTextString2[0].contains("journalism")) 
												|| (descriptionTextString2[0].contains("blogger")) || (descriptionTextString2[0].contains("coverage"))
												|| (descriptionTextString2[0].contains("global")))
											userDescription = true;
										/*
										if (continuingNews)
										{
											if (!wordList.contains(token))
											{
												wordList.add(token);
												wordCountList.add(1);
												wordLexiconList.add(lexiconList.indexOf(token)+1);
											}
											else
											{
												int indexToken = wordList.indexOf(token);
												int currentCount = wordCountList.get(indexToken);
												wordCountList.set(indexToken, currentCount + 1);
											}
										}
										 */
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

							//System.out.println();


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
							tweetCount++;

							//System.out.println(arr);

						}
						br.close();
					} catch (Exception e) { e.printStackTrace(); }
				}
			}
			arffListComplete.add(arffList);
			//wordListComplete.add(wordList);
			wordLexiconListComplete.add(wordLexiconList);
			tweetListComplete.add(tweetList);
		}
		
		System.exit(0);
		
		/*
		BufferedWriter wrt = new BufferedWriter(new FileWriter("WordTweetShort.txt"));
		
		for (int as = 0; as < 5; as++)
		{
			for (int bs = 0; bs < tweetListComplete.get(as).size(); bs++)
			{
				wrt.write(String.valueOf(wordLexiconListComplete.get(as).get(bs)) + "\t : " + tweetListComplete.get(as).get(bs) + "\n");
			}
			
			wrt.write("\n");
		}
		
		wrt.close();
		*/
		
		ArrayList<Integer> wordTempoList = new ArrayList<Integer>();
		ArrayList<Integer> tweetTempoList = new ArrayList<Integer>();
		
		wordTempoList = wordLexiconListComplete.get(0);
		tweetTempoList = tweetListComplete.get(0);
		
		for (int def = 1; def < 5; def++)
		{
			for (int abc = 0; abc < wordLexiconListComplete.get(def).size(); abc++)
			{
				if( wordTempoList.contains(wordLexiconListComplete.get(def).get(abc)))
				{
					int indexes = wordTempoList.indexOf(wordLexiconListComplete.get(def).get(abc));
					int originalNo = tweetTempoList.get(indexes);
					tweetTempoList.set(indexes, originalNo + tweetListComplete.get(def).get(abc));
				}
				else
				{
					wordTempoList.add(wordLexiconListComplete.get(def).get(abc));
					tweetTempoList.add(tweetListComplete.get(def).get(abc));
				}
			}
		}
		
		for (int sorting = 0; sorting < wordTempoList.size() - 1; sorting++)
		{
			int firstNo = wordTempoList.get(sorting);
			int firstTweet = tweetTempoList.get(sorting);
			int swapNo = sorting;
			int wordSwap = wordTempoList.get(sorting);
			int tweetSwap = tweetTempoList.get(sorting);
			
			for (int sortinga = sorting + 1; sortinga < wordTempoList.size(); sortinga++)
			{
				if (wordSwap < wordTempoList.get(sortinga))
				{
					swapNo = sortinga;
					wordSwap = wordTempoList.get(sortinga);
					tweetSwap = tweetTempoList.get(sortinga);
				}
			}
			
			wordTempoList.set(sorting, wordTempoList.get(swapNo));
			tweetTempoList.set(sorting, tweetTempoList.get(swapNo));
			wordTempoList.set(swapNo, firstNo);
			tweetTempoList.set(swapNo, firstTweet);
		}
		
		BufferedWriter wrt = new BufferedWriter(new FileWriter("WordTweetShortSort.txt"));
		
		for (int as = 0; as < wordTempoList.size(); as++)			
			wrt.write(String.valueOf(wordTempoList.get(as)) + "\t : " + String.valueOf(tweetTempoList.get(as)) + "\n");		
		
		wrt.close();
		//bwb.close();
		/*
			for (int a = 0; a < wordList.size(); a++)
			{
				//int maxCount = wordCountList.get(a);
				int swapOrder = a;
				String maxString = wordList.get(a);
				//int swapLexicon = wordLexiconList.get(a);
				int tweetswap = tweetList.get(a);

				for (int b = a + 1; b < wordList.size(); b++)
				{
					//int givenCount = wordCountList.get(b);
					//if (givenCount > maxCount)
					{
					//	maxCount = givenCount;
						swapOrder = b;
						maxString = wordList.get(b);
					//	swapLexicon = wordLexiconList.get(b);
						tweetswap = tweetList.get(b);
					}
				}

				wordList.set(swapOrder, wordList.get(a));
				//wordCountList.set(swapOrder, wordCountList.get(a));
				//wordLexiconList.set(swapOrder, wordLexiconList.get(a));
				tweetList.set(swapOrder, tweetList.get(a));

				wordList.set(a, maxString);
				//wordCountList.set(a, maxCount);
				//wordLexiconList.set(a, swapLexicon);
				tweetList.set(a, tweetswap);
			}
		 */

		//int sum = 0;
		//int topCountLexicon = wordLexiconList.get(0);
		//System.out.println("N = " + arffList.size());
		//for (int i = 0; i < wordList.size(); i++)
		//{
		//double tf = 1 + Math.log(1.0 * wordCountList.get(i)/tokenCountTotal);

		//double tf = 1.0 * wordCountList.get(i) / tokenCountTotal;
		//double idf = Math.log(1.0 * arffList.size() / tweetList.get(i));
		//double tfidf = tf*idf;
		//System.out.println(wordList.get(i));
		//System.out.println("Term frequency: " + wordCountList.get(i));
		//System.out.println("No of tweets that have it: " + tweetList.get(i));
		//System.out.println("Index associated in lexicon: " + wordLexiconList.get(i));
		//System.out.println("TF: " + tf);
		//System.out.println("IDF: " + idf);
		//System.out.println("TF-IDF: " + tfidf + "\n");

		//System.out.println(wordList.get(i) + ": \t" + wordCountList.get(i));
		//sum += wordCountList.get(i);
		//}

		//double average = sum * 1.0 / wordList.size();
		//System.out.println(average);
		steppingStone = 0;
		for (steppingStone = 4; steppingStone < 5; steppingStone++)
		{
			System.out.println("Currently writing in Stepping Stone.. #" + steppingStone);
			try {
				//BufferedWriter bw = new BufferedWriter(new FileWriter("ContinuingNewsCombo.arff"));
				String whatToWrite;

				switch (steppingStone)
				{
				case 0: whatToWrite = "2011PhilTrainShort.arff"; break;
				case 1: whatToWrite = "2012GuatemalaTrain.arff"; break;
				case 2: whatToWrite = "2012ItalyTrain.arff"; break;
				case 3: whatToWrite = "2012PhilTrainShort.arff"; break;
				case 4: whatToWrite = "2013PhilTrainShort.arff"; break;
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
				ArrayList<Integer> wordLexiconSelect = new ArrayList<Integer>();
				ArrayList<Integer> tweetSelect = new ArrayList<Integer>();
				for (int thearffs = 0; thearffs < 5; thearffs++)
				{
					if (thearffs != steppingStone)
						arffSelect.addAll(arffListComplete.get(thearffs));
				}

				boolean firstTimeDone = false;
				for (int searchThrough = 0; searchThrough < 5; searchThrough++)
				{
					if (!firstTimeDone)
					{
						if (searchThrough != steppingStone)
						{
							wordLexiconSelect = wordLexiconListComplete.get(searchThrough);
							tweetSelect = tweetListComplete.get(searchThrough);
							firstTimeDone = true;
						}					
					}
					else
					{
						if (searchThrough != steppingStone)
						{
							ArrayList<Integer> wordLexiconChosen = wordLexiconListComplete.get(searchThrough);
							ArrayList<Integer> tweetChosen = tweetListComplete.get(searchThrough);

							for (int scroll = 0; scroll < wordLexiconChosen.size(); scroll++)
							{
								if (wordLexiconSelect.contains(wordLexiconChosen.get(scroll)))
								{
									int indexChosen = wordLexiconSelect.indexOf(wordLexiconChosen.get(scroll));
									int tweetNumberChosen = tweetSelect.get(indexChosen);
									tweetSelect.set(indexChosen, tweetNumberChosen + tweetChosen.get(scroll));
								}
								else
								{
									wordLexiconSelect.add(wordLexiconChosen.get(scroll));
									tweetSelect.add(tweetChosen.get(scroll));
								}
							}
						}
					}

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

							int wordIndex = wordLexiconSelect.indexOf(no);

							// TF IDF 1 
							double tf = 1.0 * Collections.frequency(arffSelect.get(i), no);
							double idf = Math.log(1.0 * arffSelect.size() / tweetSelect.get(wordIndex));
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
				case 0: whatToWrite = "2011PhilTestShort.arff"; break;
				case 1: whatToWrite = "2012GuatemalaTest.arff"; break;
				case 2: whatToWrite = "2012ItalyTest.arff"; break;
				case 3: whatToWrite = "2012PhilTestShort.arff"; break;
				case 4: whatToWrite = "2013PhilTestShort.arff"; break;
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
				wordLexiconSelect = wordLexiconListComplete.get(steppingStone);
				tweetSelect = tweetListComplete.get(steppingStone);

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

							int wordIndex = wordLexiconSelect.indexOf(no);

							// TF IDF 1 
							double tf = 1.0 * Collections.frequency(arffSelect.get(i), no);
							double idf = Math.log(1.0 * arffSelect.size() / tweetSelect.get(wordIndex));
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
		for (steppingStone = 0; steppingStone < 1; steppingStone++)
		{
			String trainingData;
			String testData;
			String resultText;
			
			switch (steppingStone)
			{
				case 0: trainingData = "2011PhilTrainShort.arff"; break;
				case 1: trainingData = "2012GuatemalaTrain.arff"; break;
				case 2: trainingData = "2012ItalyTrain.arff"; break;
				case 3: trainingData = "2012PhilippineTrain.arff"; break;
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
				case 0: testData = "2011PhilTestShort.arff"; break;
				case 1: testData = "2012GuatemalaTest.arff"; break;
				case 2: testData = "2012ItalyTest.arff"; break;
				case 3: testData = "2012PhilippineTest.arff"; break;
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
				case 0: resultText = "2011PhilResultsShort.txt"; break;
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
