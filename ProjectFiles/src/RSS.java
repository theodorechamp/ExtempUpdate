
public class RSS {
	String text;
	String returnText;
	int lastIndex;
	
	public RSS(String rssText)
	{
		text = rssText;
	}
	
	public RSS(URLReader u)
	{
		text = u.getContent();
	}
	
	public String getFirstTag(String tag)
	{
		int indexStart, indexEnd;
		indexStart = text.indexOf("<" + tag + ">");
		if(indexStart == -1)
		{
			return "Error";
		}
		indexStart = tag.length() + 2;
		indexEnd = text.indexOf("</" + tag + ">", indexStart);
		String myContent = "";
		for(int i = indexStart; i < indexEnd; i++){
			myContent += text.charAt(i);
		}
		lastIndex = indexStart;
		return myContent;
	}
	//Work on
	public String getNextTag(String tag)
	{
		int indexStart, indexEnd;
		indexStart = text.indexOf("<" + tag + ">", lastIndex + 1);
		if(indexStart == -1){
			return "Error";
		}
		indexStart = tag.length() + 2;
		indexEnd = text.indexOf("</" + tag + ">", indexStart);
		String myContent = "";
		for(int i = indexStart; i < indexEnd; i++){
			myContent += text.charAt(i);
		}
		lastIndex = indexStart;
		return myContent;
	}
	
	public String getIntTag(String tag, int tagNumber)
	{
		int indexStart, indexEnd;
		indexStart = 0;
		for(int i = 1; i <= tagNumber ; i++){
			indexStart = text.indexOf("<" + tag + ">", indexStart + 1);
			if(indexStart == -1){
				return("Tag not Found");
			}
		}
		indexEnd = text.indexOf("</" + tag + ">", indexStart);
		String content = "";
		for(int i = indexStart + tag.length() + 2; i < indexEnd; i++){
			content += text.charAt(i);
		}
		return content;
		
	}
}
