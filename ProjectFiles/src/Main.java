public class Main
{
	static int total = 0;
	static WorkingDialog wd;
	public static void main(String[] args)
	{
		int totalArticles = 300;
		wd = new WorkingDialog(totalArticles);
		
		logic("http://feeds.bbci.co.uk/news/rss.xml");
		logic("http://feeds.bbci.co.uk/news/world/rss.xml");
		logic("http://feeds.bbci.co.uk/news/business/rss.xml");
		logic("http://feeds.bbci.co.uk/news/politics/rss.xml");
		logic("http://feeds.bbci.co.uk/news/science_and_environment/rss.xml");
		wd.updateText("Done with BBC");
		
		logic("http://america.aljazeera.com/content/ajam/articles.rss");
		wd.updateText("Done with Al Jazeera");
		
		logic("http://www.npr.org/rss/rss.php?id=1001");
		logic("http://www.npr.org/rss/rss.php?id=1006");
		logic("http://www.npr.org/rss/rss.php?id=1014");
		logic("http://www.npr.org/rss/rss.php?id=1003");
		logic("http://www.npr.org/rss/rss.php?id=1004");
		wd.updateText("Done with NPR");
		 
		wd.dispose();
		@SuppressWarnings("unused")
		FinishDialog fd = new FinishDialog();
	}
	
	
	public static void logic(String url)
	{
		URLReader u = new URLReader(url);
		RSS r = new RSS(u.getContent());
		if(url.indexOf("aljazeera") != -1){AlJazeera(r);}
		else if(url.indexOf("bbc") != -1){BBC(r);}
		else if(url.indexOf("npr") != -1){NPR(r);}
		else{
			System.out.println("That was an unknown source, try again");
		}		
	}
	
	private static String combineStrings(ArticleFile af)
	{
		String completeStatement;
		String n = "\n\n";
		completeStatement = af.getPublication() + n + af.getTitle() + n + af.getDate() + n
				+ af.getDescription() + n + af.parseURLContent();
		return completeStatement;
	}
	private static String modifyDate(String date)
	{
		StringEditor se = new StringEditor(date);
		se.removeBasic("T", true);
		se.removeBasic("Z", true);
		date = se.toString();
		return date;
	}
	
	private static void AlJazeera(RSS r)
	{
		int i = 1;
		while(true){
			
			ArticleFile af = new ArticleFile();
			if(r.getIntTag("title", i + 1).equalsIgnoreCase("Tag not Found")){
				break;
			}
			af.setPublication("Al Jazeera");
			af.setTitle(r.getIntTag("title", i + 1));
			wd.updateTitle(af.getTitle());
			
			af.setDate(modifyDate(r.getIntTag("dc:date", i)));
			af.setDescription(r.getIntTag("description", i));
			af.setUrl(r.getIntTag("link", i + 1));
			
			//TODO regular expression to check title
			TxtFile tf = new TxtFile(af, System.getProperty("user.home") + "\\Documents\\Al_Jazeera\\");
			if(!tf.getExists()){
			tf.writeToFile(combineStrings(af));
			}
			i++;
			total++;
			wd.updateProgressBar(total);
		}
		System.out.println("done");
	}
	
	private static void BBC(RSS r)
	{
		int i = 1;
		while(true){
			
			ArticleFile af = new ArticleFile();
			//the "i +" statements indicate how many initial tags have to be ignored
			//Maybe eventually update to skip opening tags
			if(r.getIntTag("title", i + 2).equalsIgnoreCase("Tag not Found")){
				break;
			}
			af.setPublication("BBC");
			af.setTitle(r.getIntTag("title", i + 2));
			wd.updateTitle(af.getTitle());
			
			af.setDate(modifyDate(r.getIntTag("pubDate", i)));
			af.setDescription(r.getIntTag("description", i+1));
			af.setUrl(r.getIntTag("link", i + 2));
			
			//TODO regular expression to check title
			TxtFile tf = new TxtFile(af, System.getProperty("user.home") + "\\Documents\\BBC\\");
			
			tf.writeToFile(combineStrings(af));
			i++;
			total++;
			wd.updateProgressBar(total);
		}
		System.out.println("done");
	}
	
	private static void NPR(RSS r){
		int i = 1;
		while(true){
			
			ArticleFile af = new ArticleFile();
			//the "i +" statements indicate how many initial tags have to be ignored
			//Maybe eventually update to skip opening tags
			if(r.getIntTag("title", i + 2).equalsIgnoreCase("Tag not Found")){
				break;
			}
			af.setPublication("NPR");
			af.setTitle(r.getIntTag("title", i + 2));
			wd.updateTitle(af.getTitle());
			
			af.setDate(modifyDate(r.getIntTag("pubDate", i)));
			af.setDescription(r.getIntTag("description", i + 1));
			af.setUrl(r.getIntTag("link", i + 2));
			
			//TODO regular expression to check title
			TxtFile tf = new TxtFile(af, System.getProperty("user.home") + "\\Documents\\NPR\\");
			
			tf.writeToFile(combineStrings(af));
			i++;
			total++;
			wd.updateProgressBar(total);
		}
		System.out.println("done");
	}
}
