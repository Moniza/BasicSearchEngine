import java.io.File;
import java.io.IOException;


public class stemmedfile {

	/**
	 * @param args
	 */
	
	public String StemWords(String tokens,int docID){
		PorterStemmer pt = new PorterStemmer();
		Index in=new Index();
		
		String textFromFile;
		String stemmed = "";
		
		
		String clean = pt.Clean(tokens);
		
		// DO NOT USE THIS CLASS. THIS IS ONLY TO GIVE YOU EXAMPLE OF HOW TO STEM
		PorterStemmer porStem = new PorterStemmer(); // because we have no static methods
		
		/*String stemmed =porStem.stem(clean);

		//now you can play with it
		System.out.println("After Stemming:"+stemmed);
		*/
		
		
		for(String word : clean.split(" "))
		{ 
			stemmed += porStem.stem(word)+" ";
		}
		//System.out.println(stemmed);
		// now you can play with it System.out.println(stemmed);
		
		//example ex=new example();
		
		//ex.countWords(stemmed,docID);
		return stemmed;
		
		
	}
	
	/*public static void main(String[] args) {
		
		
	*String textFromFile = "this is a string";
		

	*}*/
}
