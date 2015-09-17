package vocabulary;
import java.awt.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

import java.text.AttributedString;

public class VocabularyLearner extends JPanel {
    private static JButton randWord;
    private JPanel panel;
    private JButton addWord;
    private JButton searchWord;
    private Font font;
    int count;
    
    public VocabularyLearner() {
        super();
        panel=new JPanel();
        addWord=new JButton("Add Word");
        searchWord=new JButton("Search Word");
        randWord=new JButton(getRandomWord().getWord());
        font=new Font("Comic sans ms", Font.BOLD, 25);
        randWord.setFont(font);
        count=0;
        
        ActionListener add=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                WordAdder.addWord();
            }
        };
        ActionListener search=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                WordSearcher.searchWord();
            }
        };
        ActionListener getRand=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setRandomWord(randWord);
            }
        };       
               
        addWord.addActionListener(add);
        searchWord.addActionListener(search);
        randWord.addActionListener(getRand);
        panel.add(addWord);
        panel.add(searchWord);
        add(panel,BorderLayout.SOUTH);
        add(randWord,BorderLayout.CENTER);
        
    }
    
    public void setRandomWord(JButton button){
        Word w=Word.searchWord(button.getText());
        Word randWord=getRandomWord();
        switch(count){
            case 0: button.setText(w.getWord()+":"+w.getMeaning());
                    count++;
                    break;
            case 1: button.setText(randWord.getWord());
                    count=0;
                    break;
        }
    }
    
    public Word getRandomWord(){
        String fileName=getRandomFileName();
        String randWord="";
        int length=getNumberOfLines(fileName);
        int rand=0;
        try{
        FileReader fr=new FileReader(fileName);
         BufferedReader br=new BufferedReader(fr);
             rand=(int)Math.ceil(Math.random()*(length-1));
            
            if(rand==1||rand==0)
              rand=2;
            else if(rand==length)
              rand--;
            //System.out.println(rand+"-"+length+"-"+fileName);
            int i=0;
            do{
                randWord=br.readLine();
              i++;
            }while(i<rand);
        }
        catch(IOException e){
            
        }

        return Word.formWord(randWord);
    }
   
    public int getNumberOfLines(String fileName){
        int lines=0;
        try{
        FileReader fr=new FileReader(fileName);
        BufferedReader br=new BufferedReader(fr);
            while(br.readLine()!=null)
            lines++;
        }    
        catch(IOException e){
            
        }
        return lines;
    }
    
    public String getRandomFileName(){
        char ch;
        int rand=96+(int)Math.ceil((Math.random())*26);
        ch=(char)rand;
        return (ch+".txt");
    }
    
    public static void main(String args[]){
        JFrame frame;
        frame=new JFrame("Vocabulary Learner");
        frame.add(new VocabularyLearner(),BorderLayout.SOUTH);
        frame.add(randWord,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(700,700);
    }
}
