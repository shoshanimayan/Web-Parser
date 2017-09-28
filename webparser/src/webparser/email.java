package webparser;

import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.util.Map.Entry;


public class email extends JScrollPane {
	String text;
	ArrayList<String> count;
	JPanel panel;
	
	email(Document doc){
		count = new ArrayList<String>();
		text = doc.body().text();
		countWords();
		panel = new JPanel(new GridLayout(count.size(), 1));
		for (String ent:count){
			JLabel word = new JLabel(ent);
			panel.add(word);
		}
		setViewportView(panel);
	    setPreferredSize(new Dimension(400,400));
	}
	
	

	private void countWords(){
		StringTokenizer st = new StringTokenizer(text);
		while( st.hasMoreTokens()){
			String word = st.nextToken();
			if(word.contains("@")){
				count.add(word);}	
			}
	}
	
}
