package webparser;

import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.util.Map.Entry;


public class WordCount extends JScrollPane {
	String text;
	HashMap<String,Integer> count;
	JPanel panel;
	ArrayList<Entry<String,Integer>> sorted;
	
	WordCount(Document doc){
		count = new HashMap<String,Integer>();
		text = doc.body().text();
		countWords();
		sorted= sortedByValue(count); 
		panel = new JPanel(new GridLayout(count.size(), 2));
		for (Entry<String,Integer> ent:sorted){
			JLabel word = new JLabel(ent.getKey());
			JLabel val = new JLabel(ent.getValue().toString());
			panel.add(word);
			panel.add(val);
		}
		setViewportView(panel);
	    setPreferredSize(new Dimension(400,400));
	}
	
	

	private void countWords(){
		StringTokenizer st = new StringTokenizer(text);
		while( st.hasMoreTokens()){
			String word = st.nextToken();
			int c= count.containsKey(word)? count.get(word) : 0;
			count.put(word, c+1);
	}	
	}
	private ArrayList<Entry<String, Integer>> sortedByValue(
			HashMap<String, Integer> map) {
		ArrayList<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Comparator<Entry<String, Integer>> ValComp = new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> left, Entry<String,Integer> right){
				return right.getValue().compareTo(left.getValue());}};
		Collections.sort(list, ValComp);
		return list;
	}


}
