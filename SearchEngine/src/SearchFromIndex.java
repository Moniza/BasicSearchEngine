import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchFromIndex {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public String SearchTerm(String term) throws FileNotFoundException
	{
		Map map=new HashMap<>();
		String list="";
		FileInputStream fs=new FileInputStream("D://Index/index.txt");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(fs));
		String str="";
		
		try {
			
			while((str=br.readLine())!=null)
			{
				String[] word=str.split(":");
				
				if(word[0].equals(term))
				{
					String DidTid=":"+word[1];
					map.put(word[0],DidTid);
				}
			}br.close();
			//System.out.println(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set set=map.entrySet();
		Iterator it=set.iterator();
		 while(it.hasNext())
		 {
			 @SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry)it.next();
			list=list+" "+me.getKey()+me.getValue();
			
			
		
		 }
		return list;
		
		//return map;
	}
	
	
	
	
	
	public String SearchTerms(String[] terms) throws IOException
	{
		Map map=new HashMap<>();
		String[] string=terms;
		String list="";
		String str;
		int i=1;
		for(String s:string)
		{
			FileInputStream fs=new FileInputStream("D://Index/index.txt");
			BufferedReader br=new BufferedReader(new InputStreamReader(fs));
						
			while((str=br.readLine())!=null)
			{
				String[] word=str.split(":");
				if(word[0].equals(s))
				{
					
					
					String DidTid=":"+word[1];
					map.put(word[0],DidTid);
					
				}
				
			}br.close();
			
		}

		Set set=map.entrySet();
		Iterator it=set.iterator();
		 while(it.hasNext())
		 {
			 @SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry)it.next();
			list=list+" "+me.getKey()+me.getValue();
			
			
		
		 }
		return list;
		
	}
	
	public Map getTF(String list)
	{
		String[] ln=list.split(" ");
		//System.out.print("lengthof word:"+ln.length);
		//System.out.println("returned list:"+list);
		Map<String,Integer> map=new HashMap<>();
		String[] postings=null;
		String word="";
		String[] splitlist=list.split(" ");
		int length=splitlist.length-2;
		//System.out.println("length:"+length);
		
		
		int x,y;
		Pattern pt=Pattern.compile("[D][0-9][T][0-9]");
		Matcher match=pt.matcher(list);
		
		
		
		while(match.find()){
		String newString=list.substring(match.start(),match.end());
		word=word+" "+newString;
		postings=word.split(" ");
		}
		if(ln.length>2){
		for(int i=1;i<postings.length-1;i++)
		{
			//System.out.println("i:"+i+"Posting:"+postings[i]);
			int count = 0;
			int temp;
			String[] a=null;
			a=postings[i].split("T");
			for(int j=i+1;j<postings.length;j++)
			{
				//System.out.println("j:"+j+"Posting:"+postings[j]);
				
			String[] b=null;
			
			b=postings[j].split("T");
			
			if(a[0].equals(b[0]))
			{
				
				count++;
				//System.out.println(a[0]);
				//System.out.println(count);
				x=Integer.parseInt(a[1]);
				y=Integer.parseInt(b[1]);
				if(x<=y)
				{
					temp=x;
					//System.out.println(a[0]+" "+x);
					//map.put(a[0],x);
				}
				else
				{
					temp=y;
					//System.out.println(a[0]+" "+y);
					//map.put(a[0],y);
				}
				if (count==length)
				{
					map.put(a[0],temp);
									
				}
				
				
			}
			}
			
			
		}
		//System.out.println(map);
			
		
		}
		else
		{
		for(int i=1;i<postings.length;i++)
		{
			//System.out.println("word:"+postings[i+1]);
			
			
			String[] a=null;
			a=postings[i].split("T");
			int value=Integer.parseInt(a[1]);
			map.put(a[0], value);
			System.out.println(map);
		}
		
		}
		
		return map;
		
		
		
		
		
	}
	
	public float getIDF(Map map) throws IOException
	{
		System.out.println("IDF:"+map);
		float idf = 0;
		try {
			FileInputStream fs=new FileInputStream("D://Index/DocToDocID.txt");
			BufferedReader br=new BufferedReader(new InputStreamReader(fs));
			String str;
			String doc;
			int count=0;
			while((str=br.readLine())!=null)
			{
				count++;
			}
			int df=map.size();
			//System.out.print("DF:"+df);
			if(df>0){
			 idf=count/df;
			//System.out.println("IDF:"+idf);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idf;
		
		
	}
	
	
	public Map getTFIDF(Map map,float idf)
	{
		int tf;
		float tfidf;
		Set set=map.entrySet();
		Iterator it=set.iterator();
		 while(it.hasNext())
		 {
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry)it.next();
			tf=(int) me.getValue();
			//list=list+" "+me.getKey()+me.getValue();
			tfidf=tf*idf;
			map.put(me.getKey(),tfidf);
			//System.out.print("maping");
		
		 }	
		 Map<String,Integer> m1=new TreeMap<String, Integer>();
		 m1.putAll(map);
		 System.out.println(m1);
			return m1;
		
	}
	public void readDocuments(Map m1) throws FileNotFoundException 
	{
		
		
		String str;
		Set set=m1.entrySet();
		Iterator it=set.iterator();
		String listofdoc="";
		
		 while(it.hasNext())
		 {
			 FileInputStream fs=new FileInputStream("D://Index/DocToDocID.txt");
			BufferedReader br=new BufferedReader(new InputStreamReader(fs));
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry)it.next();
			try {
				while((str=br.readLine())!=null)
				{
					//System.out.println(str);
					String[] temp=str.split(":");
				
					if(temp[1].equals(me.getKey()))
					{
						listofdoc=listofdoc+"\r\n"+temp[0];
						
						break;
					}
					
				}br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		 }
		 System.out.println(listofdoc);
	}
	
	
	public static void main(String[] args) throws IOException {
		SearchFromIndex sf=new SearchFromIndex();
		Scanner in=new Scanner(System.in);
		System.out.println("Enter Keyword:");
		String str=in.nextLine();
		
		String[] terms=str.split(" ");
		if (terms.length>1)
		{
			String s=sf.SearchTerms(terms);
			Map map=sf.getTF(s);
			float f=sf.getIDF(map);
			Map m=sf.getTFIDF(map,f);
			sf.readDocuments(m);
			
		}
		else
		{
			//System.out.println(str);
			String list=sf.SearchTerm(str);
			Map map=sf.getTF(list);
			float f=sf.getIDF(map);
			Map m=sf.getTFIDF(map,f);
			sf.readDocuments(m);
			
		}
	
	}

}
