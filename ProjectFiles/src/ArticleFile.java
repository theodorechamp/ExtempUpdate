
public class ArticleFile {
	String publication;
	String title;
	String author;
	String date;
	String description;
	String url;
	String content;
	
	public boolean setPublication(String myString)
	{
		if(publication != null){
			publication = myString;
			return true;
			
		}
		publication = myString;
		return false;
	}
	//returns true if title already exists
	public boolean setTitle(String myString)
	{
		if(title != null){
			title = myString;
			return true;
			
		}
		title = myString;
		return false;
	}
	
	public boolean setAuthor(String myString)
	{
		if(author != null){
			author = myString;
			return true;
			
		}
		author = myString;
		return false;
	}
	
	public boolean setDate(String myString)
	{
		if(date != null){
			date = myString;
			return true;
			
		}
		date = myString;
		return false;
	}
	
	public boolean setDescription(String myString)
	{
		if(description != null){
			description = myString;
			return true;
			
		}
		description = myString;
		return false;
	}
	
	public boolean setUrl(String myString)
	{
		if(url != null){
			url = myString;
			return true;
			
		}
		url = myString;
		return false;
	}
	
	public String getPublication()
	{
		return publication;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String parseURLContent()
	{
		//TODO ? in the code instead of quotation marks?
		//TODO exceptions
		if(url == null){
			return("Error: No url");
		}
		URLReader ur = new URLReader(url);
		String html = ur.getContent();
		content = ""; //set content to nothing
		int indexStart, indexEnd;
		indexStart = 0;
		
		while(true){
			content += "\t";
			indexStart = html.indexOf("<p>", indexStart + 1); //find paragraph tag
			if(indexStart == -1){
				break;
			}
			if(!(1 < html.indexOf("<b class=\"credit\">", indexStart) - indexStart
					&&  html.indexOf("<b class=\"credit\">", indexStart) - indexStart < 1000)){ //check for NPR photo caption
			
				indexEnd = html.indexOf("</p>", indexStart); //find end paragraph tag
				//set i to 3 after <p> (number of characters till text), then add all char
				//till paragraph end tag
				for(int i = indexStart + 3; i < indexEnd; i++){
					content += html.charAt(i);
				}
				content += "\n";
			}
		}
		if(url.indexOf("aljazeera") != -1){
			content = removeAlJa(content);
		}
		if(url.indexOf("bbc") != -1){
			content = removeBBC(content);
		}
		if(url.indexOf("npr") != -1){
			content = removeNPR(content);
		}
		else{
			content = removeNull(content);
		}
		return content;
	}
	
	//working, short of ? instead of "
	private String removeAlJa(String str)
	{
		StringEditor se = new StringEditor(str);
		se.removeBasic("&nbsp;", true);
		se.removeBasic("</a>", false);
		se.removeBasic("â", false);
		se.removeBasic("€", false);
		se.removeBasic("œ", false);
		se.removeBasic("<i>", false);
		se.removeBasic("</i>", false);
		se.removeBasic("&quot;", false);
		se.removeTag("<a href=", false);
		se.replace("™", "\'");
		str = se.toString();
		return str;
	}
	
	//working!
	private String removeBBC(String str){
		StringEditor se = new StringEditor(str);
		se.removeBasic("</a>", false);
		se.removeTag("<a", false);
		se.removeBasic("<span>", false);
		se.removeBasic("</span>", false);
		se.removeBasic("<i>", false);
		se.removeBasic("</i>", false);
		se.removeBasic("<strong>", false);
		se.removeBasic("</strong>", false);
		se.removeTag("<span", false);
		str = se.toString();
		return str;
	}
	
	private String removeNPR(String str){
		StringEditor se = new StringEditor(str);
		se.removeTag("<a", false);
		se.removeBasic("<b>", false);
		se.removeBasic("</b>", false);
		se.removeBasic("</a>", false);
		se.removeTag("<b", false);
		se.removeTag("<img", false);
		se.removeBasic("<em>", false);
		se.removeBasic("</em>", false);
		se.removeBasic("hide caption", false);
		se.removeBasic("â€\"", false);
		
		str = se.toString();
		return str;
	}
	
	//TODO work on
	private String removeNull(String str){
		return str;
	}
}
