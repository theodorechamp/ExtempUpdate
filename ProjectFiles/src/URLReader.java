import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;


public class URLReader {
	String Content;
	
	public URLReader(String myString)
	{
		try{
			Content = parsetoString(myString);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public String getContent()
	{
		return Content;
	}
	
	public String parsetoString(String stringURL) throws Exception
	{
		URL url = new URL(stringURL);
		URLConnection con = url.openConnection();
		Reader r = new InputStreamReader(con.getInputStream());
		StringBuilder buf = new StringBuilder();
		while (true) {
		  int ch = r.read();
		  if (ch < 0)
		    break;
		  buf.append((char) ch);
		}
		return buf.toString();
	}
}
