package vocabulary;

import java.awt.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordAdder extends JPanel{
    
    private JTextField forWord;
    private JTextField forMeaning;
    private static JButton ok;
    private JLabel labWord,labMeaning;

    public WordAdder() {
        super();
        GridLayout lay=new GridLayout(0,2);
        setLayout(lay);
        forWord=new JTextField(10);
        forMeaning=new JTextField(10);
        ok=new JButton("OK");
        labWord=new JLabel(" Word ");
        labMeaning=new JLabel(" Meaning ");
        
        ActionListener addWord=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(forWord.getText().equals(""))
                    forMeaning.setText("ENTER A WORD");
                else{
                Word wordToAdd=new Word(forWord.getText(),forMeaning.getText());
                //Word.searchWord(wordToAdd.getWord(),wordToAdd.getFileName());
                wordToAdd.save();
                forMeaning.setText("");
                forWord.setText("");
                }
            }
        };
        
        add(labWord);
        add(forWord);
        add(labMeaning);
        add(forMeaning);
        ok.addActionListener(addWord);
        setVisible(true);
    }
   
    public static void addWord(){
        JFrame frame;
        frame=new JFrame("ADD A WORD");
        frame.add(new WordAdder());
        frame.getRootPane().setDefaultButton(ok);
        frame.add(ok,BorderLayout.SOUTH);
        frame.getRootPane().setDefaultButton(ok);
        frame.setVisible(true);
        frame.pack();
    }
}
