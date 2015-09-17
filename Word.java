package vocabulary;
import java.io.*;

public class Word {
    private String word;
    private String meaning;
    
    public Word() {
        word="";
        meaning="";
    }

    public Word(String w, String m) {
        word=w;
        meaning=m;
    }
    
    public void save(){
        String fileName=(word.charAt(0)+".txt");
        try{
        FileWriter fw=new FileWriter(fileName,true);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter out=new PrintWriter(bw);
        
        out.println(word+":"+meaning);
        out.close();
        }
        catch(IOException e){
            
        }
    }
    
    public String getMeaning(){
        return meaning;
    }
    
    public String getWord(){
        return word;
    }
    
    public static Word searchWord(String word){
        String line="";
        String fileName=word.charAt(0)+".txt";
        Word theWord=new Word(); 
        try{  
            FileReader fr=new FileReader(fileName);
            BufferedReader br=new BufferedReader(fr);
            while((line=br.readLine())!=null){
                if (line.equals(""))
                    continue;
                if(formWord(line).getWord().equalsIgnoreCase(word))
                    theWord=formWord(line);
            }
        }
        catch(IOException e){
            
        }
        return theWord;
    } 
    
    public static Word formWord(String s){
        int i=0;
        Word word=new Word();
        for(;i<s.length();i++)
            if(s.charAt(i)==':')
                break;
        try{
        word=new Word(s.substring(0,i),s.substring(i+1, s.length()));
        }
        catch(StringIndexOutOfBoundsException e){
            return new Word("astute","shrewd"); //Return this word on exception! -- Handle better
        }
        return word;
    }
    
}
