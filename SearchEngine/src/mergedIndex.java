import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map; 
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class mergedIndex {
	
	
	@SuppressWarnings("unchecked")
	public void CompleteIndex(String indexc) throws IOException { 
		File folder=new File("D://Index/");
		File[] files=folder.listFiles();
		Map map = new HashMap();
		for(int i=0;i<files.length;i++)
		{
		
		FileInputStream fis=new FileInputStream(files[i].getAbsolutePath());
		
		BufferedReader br=new BufferedReader(new InputStreamReader(fis));
		String newcontent="";
		String str;
		
		Pattern pt=Pattern.compile("[D][0-9][T][0-9]");
		try {
			while((str=br.readLine())!=null)
				{
					
					String[] separatedWords = str.split(":");	
					if(map.containsKey(separatedWords[0]))
					{
								
								Matcher match=pt.matcher(str);
								while(match.find())
								{ 
									
									newcontent =str.substring(match.start(),match.end());
									
								map.put(separatedWords[0],map.get(separatedWords[0])+newcontent);
						
								}
					}
					else
					  
					{
						String content=":"+separatedWords[1];
						map.put(separatedWords[0],content);
						
					}
					
				}
		} catch (IOException e) {
		//	TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		FileWriter fw=new FileWriter(indexc+"index.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		Map<String,String> Sorted=new TreeMap<String,String>();
		Sorted.putAll(map);
			 Set set=Sorted.entrySet();
			 Iterator i=set.iterator();
			 while(i.hasNext())
			 {
				 @SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry)i.next();
		        bw.write(me.getKey()+""+me.getValue()+"\r\n");
				 //System.out.print(me.getKey());
		        //System.out.println(me.getValue());
			 }
			 bw.close();
		}
			
		

	
	public static void main(String[] args) throws IOException
	{
		
		Scanner  in=new Scanner(System.in);
		System.out.println("Path of");
	}
	/*
	 * 	mergedIndex ex=new mergedIndex();
		try {
			ex.CompleteIndex();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
}