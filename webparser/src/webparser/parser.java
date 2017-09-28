package webparser;

import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import javax.swing.*;

import java.io.*;
import java.util.Scanner;
import java.awt.*;
public class parser extends JTabbedPane{
  Document docs;
  JScrollPane spane;
  parser(){
    try{
    	Scanner scan=new Scanner(System.in);
    	String url= scan.nextLine();
    docs = Jsoup.connect(url).get();
    }
    catch(IOException e){e.printStackTrace(); }
    getLinks();
    addTab("Links",spane);
    addTab("Images", new images(docs));
    addTab("Word Count", new WordCount(docs));
    addTab("emails", new email(docs));
    addTab("Phone Number", new phoneNumber(docs));

  }

  public void getLinks(){//retrieve links on page
    Elements links = docs.getElementsByTag("a"); //should all be links
    JPanel linkp = new JPanel();
  		linkp.setLayout(new GridLayout(links.size(), 1));
    for(Element link: links){
      String linkData= link.attr("href");
      if( linkData.length()>0){
        if(linkData.length()<4)// if less than four doesnt have the full link http;
          linkData = docs.baseUri()+linkData.substring(1);
        else if(!linkData.substring(0,4).equals("http"));
          linkData = docs.baseUri()+linkData.substring(1);
        clickableLink label = new clickableLink(link.text(),linkData);
        linkp.add(label);
      }
    }
    spane = new JScrollPane(linkp);
    spane.setPreferredSize(new Dimension(400,400));
  }

public static void main(String[] args){
  JFrame frame = new JFrame("website parser");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  parser p= new parser();
  frame.add(p);
  frame.setVisible(true);
  frame.setSize(500,500);
}
}
