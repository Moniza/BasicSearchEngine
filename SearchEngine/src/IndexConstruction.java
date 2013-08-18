import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map; 
import java.util.Set;
public class IndexConstruction {
	
	public Map countWords(String input) { 
		Map map = new HashMap();
		if (input != null) { 
			String[] separatedWords = input.split(" ");
			for (String str : separatedWords) {
				if (map.containsKey(str)) { 
					int count = Integer.parseInt((String) map.get(str));
					map.put(str, String.valueOf(count + 1)); } 
				else {
					map.put(str, "1");
					}
				} 
			
		
		}
		  return map;
		//writeToFile(map,docID);
	}
	
	public static void main(String[] args){
			

	}
	
	} 