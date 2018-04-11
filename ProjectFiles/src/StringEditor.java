//TODO possibly just extend StringBuilder
public class StringEditor
{
	StringBuilder text;
	
	public StringEditor(String s)
	{
		text = new StringBuilder(s);
	}
	//target means text the user wants to remove
	//text is the text to remove from
	//includeSpace is if the removal requires the inserting of a space
	//runs through text, deleting target until none remain
	public void removeBasic(String target, boolean includeSpace)
	{
		while(true){
			int index = text.indexOf(target);
			if(index == -1){
				break;
			}
			for(int i = index; i != index + target.length(); i++){
				text.deleteCharAt(index);
			}
			if(includeSpace){
				text.insert(index, " ");
			}
		}
	}
	
	public void removeTag(String tagStart, boolean includeSpace)
	{
		while(true){
			int index = text.indexOf(tagStart);
			if(index == -1){
				break;
			}
			while(true)
			{
				if(text.charAt(index) == '>'){
					text.deleteCharAt(index);
					break;
				}
				text.deleteCharAt(index);
			}
			if(includeSpace){
				text.insert(index, " ");
			}
		}
	}
	
	public void replace(String oldString, String newString)
	{
		int index = 0;
		while(true){
			index = text.indexOf(oldString, index + 1);
			if(index == -1){
				break;
			}
			text.replace(index, index + oldString.length(), newString);
		}
	}
	
	public String toString()
	{
		return text.toString();
	}
	
	
}
