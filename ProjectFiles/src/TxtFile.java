import java.io.File;
import java.io.PrintWriter;

//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       

public class TxtFile
{
	PrintWriter out;
	boolean exists;
	public TxtFile(ArticleFile af, String path)
	{
		StringEditor se = new StringEditor(af.getTitle());
		
		se.removeBasic("\\", false);
		se.removeBasic("/", false);
		se.removeBasic(":", false);
		se.removeBasic("*", false);
		se.removeBasic("?", false);
		se.removeBasic("\"", false);
		se.removeBasic("<", false);
		se.removeBasic(">", false);
		se.removeBasic("|", false);
		
		String title = se.toString();
		File f = new File(path + title + ".txt");
		exists = f.exists();
		if(!exists){
		try{
			out = new PrintWriter(f);
		}
		catch(Exception e){
			System.out.println(e);
		}
		}
	}
	
	public void writeToFile(String str)
	{
		if(!exists){
		out.write(str);
		out.close();
		}
	}
	
	public boolean getExists(){
		return exists;
	}
}