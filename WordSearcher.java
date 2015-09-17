package vocabulary;

import java.awt.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordSearcher extends JPanel{
    
    private JTextField forWord;
    private JTextField forMeaning;
    private static JButton ok;
    private JLabel labWord,labMeaning;

    public WordSearcher() {
        super();
        GridLayout lay=new GridLayout(0,2);
        setLayout(lay);
        forWord=new JTextField(10);
        forMeaning=new JTextField(10);
        ok=new JButton("Get Meaning");
        labWord=new JLabel(" Word ");
        labMeaning=new JLabel(" Meaning ");
        
        ActionListener searchWord=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(forWord.getText().equals(""))
                    forMeaning.setText("ENTER A WORD");
                else{
                    Word wordToSearch=new Word(forWord.getText(),forMeaning.getText());
                    Word theWord=Word.searchWord(wordToSearch.getWord());
                    forMeaning.setText(theWord.getMeaning());
                }
            }
        };
        
        add(labWord);
        add(forWord);
        add(labMeaning);
        add(forMeaning);
        ok.addActionListener(searchWord);
        setVisible(true);
    }
   
    public static void searchWord(){
        JFrame frame;
        frame=new JFrame("SEARCH WORD");
        frame.add(new WordSearcher());
        frame.getRootPane().setDefaultButton(ok);
        frame.add(ok,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.pack();
    }
}
