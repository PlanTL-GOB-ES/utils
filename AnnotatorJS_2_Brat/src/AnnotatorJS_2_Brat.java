import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AnnotatorJS_2_Brat {
	private String URIsFile;
	private String htmlFiles;
	private String output;
	private String websiteURL;
	
	private Map<String, String> URIs;
	
	public AnnotatorJS_2_Brat(String URIsFile, String htmlFiles, String websiteURL, String output)
	{
		this.URIsFile = URIsFile;
		this.htmlFiles = htmlFiles;
		this.websiteURL = websiteURL;
		this.output = output;
		
		URIs = new HashMap<String, String>();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String URIsFile = args[0];
		String htmlFiles = args[1];
		String websiteURL = args[2];
		String output = args[3];
		
		AnnotatorJS_2_Brat convert = new AnnotatorJS_2_Brat(URIsFile, htmlFiles, websiteURL, output);
		convert.start();
	}

	public void start() throws IOException
	{
		readURIs();
		Iterator<String> iter = URIs.keySet().iterator();
		while (iter.hasNext())
		{
			String URI = iter.next();
			String scieloID = URIs.get(URI);
			System.out.println("\tPrinting: " + scieloID);
			
			List<Mention> mentions = getAnnotations(URI);
			List<String> paragraphLengths = getParagraphLength(URI);
			writeANN(URI, mentions, paragraphLengths);				
		}
	}
	
	public void readURIs() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(URIsFile));
		String line = "";
		while ((line = reader.readLine()) != null)
		{
			String scieloID = line.split("\t")[0];
			String URI = line.split("\t")[1];
			URIs.put(URI, scieloID);
		}
		reader.close();
	}
	
	public List<Mention> getAnnotations(String URI)
	{
		List<Mention> mentions = new ArrayList<Mention>();		
		String command = new String("curl " + websiteURL + "/search?uri=" + URI + "&limit=2000000");
		try
		{
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("/tmp/" + URI + ".json"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine())!= null) 
            {
            	writer.write(line);
            }
            writer.close();
            reader.close();
			
			Object obj = new JSONParser().parse(new FileReader("/tmp/" + URI + ".json"));
			JSONObject jo = (JSONObject) obj;
			
			JSONArray rows = (JSONArray) jo.get("rows");
			Iterator iter = rows.iterator();
			while (iter.hasNext()) 
			{
				JSONObject row = (JSONObject) iter.next();
							
				String category = (String) row.get("category");			
				String quote = (String) row.get("quote");		
				if (!quote.equals(""))
				{
					String thisURI = (String) row.get("uri");
					String note = ((String) row.get("text")).toLowerCase().replace("\n", "");
					
					note = cleanNote(note);
					
					if (thisURI.equals(URI))
					{
						JSONArray ranges = (JSONArray) row.get("ranges");
						Iterator iter2 = ranges.iterator();
						while (iter2.hasNext()) 
						{
							JSONObject range = (JSONObject) iter2.next();
							
							Mention mention = new Mention();
							mention.setCategory(category);						
							mention.setURI(URI);
							mention.setNote(note);
							
							int start = Math.toIntExact((long) range.get("startOffset"));					
							int end = Math.toIntExact((long) range.get("endOffset"));
															
							mention.setEnd(end);
							mention.setStart(start);
							mention.setQuote(quote);
							
							String paragraphInfo = (String) range.get("start");
							int paragraphNum = 0;
							Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(paragraphInfo);
							while(m.find()) {
							    paragraphNum = Integer.parseInt(m.group(1));
							}
							mention.setParagraph(paragraphNum);
							
							mentions.add(mention);
						}
					}
					else
					{
						System.err.println("FATAL ERROR: " + URI + "vs" + thisURI);
					}
				}				
			}
			
			return mentions;
		}
		catch (Exception e)
		{
			System.err.println("Failed executing command:\t" + command);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getParagraphLength(String URI) throws IOException
	{
		String scieloID = URIs.get(URI);
				
		List<String> paragraphLengths = new ArrayList<String>();
		
		File input = new File(htmlFiles + File.separator + scieloID);
		Document doc = Jsoup.parse(input, "UTF-8");
		Element body = doc.body();
		Elements paragraphs = body.getElementsByTag("p");
		for (int i = 0; i < paragraphs.size(); i++)
		{
			String paragraph = paragraphs.get(i).text();
			paragraphLengths.add(paragraph);
		}
				
		return paragraphLengths;
	}
	
	public void writeANN(String URI, List<Mention> mentions, List<String> paragraphLengths) throws IOException
	{		
		String scieloID = URIs.get(URI);
		String text = "";
		for (int i = 0; i < paragraphLengths.size(); i++)
		{
			text = text + paragraphLengths.get(i) + "";
		}
		
		try
		{			
			BufferedWriter writer = new BufferedWriter(new FileWriter(output + File.separator + scieloID.replaceAll(".html", ".ann")));
			int numNote = 1;
			String previousAnnotation = new String();
			String previousNote = new String();
			if (mentions != null)
			{
				for (int i = 0; i < mentions.size(); i++)
				{
					Mention mention = mentions.get(i);
					String ID = "T" + (i + 1);
					String quote = mention.getQuote();
					String category = mention.getCategory();
					String note = mention.getNote();
				
					int paragraphNum = mention.getParagraph();					
					if (paragraphNum <= paragraphLengths.size())
					{
						int length = 0;
						for (int j = 0; j < paragraphNum - 1; j++)
						{
							length = length + paragraphLengths.get(j).length() + 1;
						}
						
						int start = mention.getStart() + length;
						int end = mention.getEnd() + length;	
						
						long offsetLength = end - start;
						if (quote.length() != offsetLength)
						{
							String textSpan = new String();							
							try
							{
								textSpan = text.substring(start, end);
							}
							catch (StringIndexOutOfBoundsException e)
							{
								textSpan = text.substring(start);
							}
							
							if (textSpan.startsWith(" "))
							{
								start++;
							}
							else if (textSpan.endsWith(" "))
							{
								end--;
							}
							else
							{
								System.err.println("WARNING");
							}
						}
						
						String currentAnnotation = category + " " + start + " " + end + "\t" + quote;
						
						if (!currentAnnotation.equals(previousAnnotation))
						{
							writer.write(ID + "\t" + currentAnnotation + "\n");							
						}	
						else
						{
							if (!note.equals(previousNote))
							{
								writer.write(ID + "\t" + currentAnnotation + "\n");	
							}
							else
							{
								System.err.println("Repeated annotation found: " + scieloID + " : " + currentAnnotation);
							}							
						}
						
						previousAnnotation = currentAnnotation;
						
						
						if (!note.equals(""))
						{
							writer.write("#" + numNote + "\t" + "AnnotatorNotes" + " " + ID + "\t" + note + "\n");
							numNote++;
						}
						
						previousNote = note;
					}					
				}
			}
			else
			{
				System.err.println("\tNo annotations found");
			}
			writer.close();
		}
		catch (Exception e)
		{
			System.err.println("Errors in clinical case " + scieloID);
			e.printStackTrace();
		}
	}
	
	public String cleanNote(String note)
	{		
		if (note.startsWith(" "))
		{
			note = note.substring(1);
		}
		
		if (note.endsWith(" ") || note.endsWith(","))
		{
			note = note.substring(0, note.length() - 1);
		}
		
		note = note.replace(",", "");
		
		return note;
	}
	
	private Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
