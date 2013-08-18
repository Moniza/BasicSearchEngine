import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
public class Index {

	
	public File[] DocToDocID(String path,String did)
	{
		//File folder=new File("D://novels/");
		
		File folder=new File(path);
		File[] files=folder.listFiles();
		String filename="DocToDocID.txt";
		File f=new File(did+filename);
		try {
			if (!f.exists()) {
				
					f.createNewFile();
			}
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i=0;i<files.length;i++)
		{
			
			//System.out.print("File:"+files[i].getName());
			//System.out.println("docID:"+i);
			String Pagecontent=files[i].getName()+":D"+i+"\r\n";
			bw.write(Pagecontent);
		}
		bw.close();
		fw.close();
		}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			
		
		
		return files;
	
		
	}
	
	 
	 public String Stopwords(String sw)
	 	{
		 String stopword = null;
		 try {
			//FileInputStream fis=new FileInputStream("D://stopWords.txt");
			 FileInputStream fis=new FileInputStream(sw);
			DataInputStream in=new DataInputStream(fis);
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			
			try {
				String str;
				
				while((str=br.readLine()) != null)
				{
					
						stopword=stopword+str+"\n";
						
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 //System.out.println(stopword);
		return stopword;
				
	 }
	@SuppressWarnings("resource")
	public String readDocuments(File f) throws IOException
	{
		
			File file=f;
			System.out.println(file.getName());
			FileInputStream fstr;
			String FullFile="";
			try {
				fstr = new FileInputStream(file.getAbsolutePath());
			
			DataInputStream in=new DataInputStream(fstr);
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			String str;
			//System.out.println(stopword);
			
			
				
				while((str=br.readLine())!=null)
				{
					 FullFile=FullFile+str.toLowerCase()+"\n";
					
				}	 
								
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return FullFile;
			}
			

	public String removeStopWords(String FullFile,String stopword)
	{
				String newfile="";
				
				
				//BufferedReader reader = new BufferedReader(new StringReader(FullFile));
				Scanner scanner = new Scanner(FullFile);
				while (scanner.hasNextLine()) {
				  String line = scanner.nextLine();
						String[] splitline=line.split(" ");
					
					for(int i=0;i<splitline.length;i++)
					{
						if(stopword.contains(splitline[i]))
						{
							continue;
						}
						else
						{
							newfile=newfile+splitline[i]+"\r\n";
						}
						
						
					}
					
					}
								
					scanner.close();
					
				return newfile;	
				} 
				   
				public void WriteToFile(Map map,File filename,String path,int docID)
				{
					try{
					File fn=new File(filename.getName()+"index.txt");
					File f=new File(path+fn);
					
						if (!f.exists()) {
							
							f.createNewFile();
						}
						FileWriter fw = new FileWriter(f.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
					
					Set set=map.entrySet();
					 Iterator i=set.iterator();
					 while(i.hasNext())
					 {
						 @SuppressWarnings("rawtypes")
						Map.Entry me = (Map.Entry)i.next();
				        bw.write(me.getKey()+":D"+docID+"T"+me.getValue()+"\r\n");
					}
					bw.close();
					fw.close();
				}catch(IOException ex){
					System.out.println(ex);
				}
				}


				
				
	public static void main(String[] args) throws IOException 
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Path of files to be indexed:");
		String filesPath=in.next();
		System.out.println("Path for DocToDocID:");
		String did=in.next();
		System.out.println("path of stop word files:");
		String sw=in.next();
		System.out.println("path for Index construction:");
		String indexc=in.next();
		Index obj=new Index();
		//String path="D://Index/";
		File[] f=obj.DocToDocID(filesPath,did);
		stemmedfile sf=new stemmedfile();
		IndexConstruction ex=new IndexConstruction();
		
		for (int i=0;i<f.length;i++)
		{
			String tokens=obj.removeStopWords(obj.readDocuments(f[i]),obj.Stopwords(sw));
			String stemmed=sf.StemWords(tokens,i);
			Map map=ex.countWords(stemmed);
			//obj.WriteToFile(map,f[i],filesPath,i);
			obj.WriteToFile(map,f[i],indexc,i);
		}
		mergedIndex mi=new mergedIndex();
		mi.CompleteIndex(indexc);
		
	}
}

	
