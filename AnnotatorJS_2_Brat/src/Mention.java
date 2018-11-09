

public class Mention {

	private String quote;
	private String category;
	private int start;
	private int end;
	private String note;
	private String URI;
	private int paragraph;
	
	public Mention()
	{
		quote = new String();
		category = new String();
		start = 0;
		end = 0;
		note = new String();
		URI = new String();
		paragraph = 0;
	}
	
	public Mention(String quote, String category, int start, int end, String note, String URI, int paragraph)
	{
		this.quote = quote;
		this.category = category;
		this.start = start;
		this.end = end;
		this.note = note;
		this.URI = URI;
		this.paragraph = paragraph;
	}
	
	public void setQuote(String quote)
	{
		this.quote = quote;
	}
	public String getQuote()
	{
		return quote;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	public String getCategory()
	{
		return category;
	}
	
	public void setStart(int start)
	{
		this.start = start;
	}
	public int getStart()
	{
		return start;
	}
	
	public void setEnd(int end)
	{
		this.end = end;
	}
	public int getEnd()
	{
		return end;
	}
	
	public void setNote(String note)
	{
		this.note = note;
	}
	public String getNote()
	{
		return note;
	}
	
	public void setURI(String URI)
	{
		this.URI = URI;
	}
	public String getURI()
	{
		return URI;
	}
	
	public void setParagraph(int paragraph)
	{
		this.paragraph = paragraph;
	}
	public int getParagraph()
	{
		return paragraph;
	}
}
