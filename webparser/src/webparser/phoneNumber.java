package webparser;

import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.util.Map.Entry;


public class phoneNumber extends JScrollPane {
	String text;
	ArrayList<String> list;
	JPanel panel;
	
	phoneNumber(Document doc){
		list = new ArrayList<String>();
		text = doc.body().text();
		countWords();
		panel = new JPanel(new GridLayout(list.size(), 1));
		for (String ent:list){
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
			if(word.matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")){
				list.add(word);}	
			}
	}
	
}
