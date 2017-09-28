package webparser;

import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.io.*;
import java.awt.*;
public class images extends JScrollPane{

  Elements imageSet;
  Document doc;
  JPanel panel;

  images(Document doc){
    this.doc=doc;
    imageSet=doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
    panel = new JPanel(new GridLayout(imageSet.size(), 1));
    grabImage();
    setViewportView(panel);
    setPreferredSize(new Dimension(400,400));
  }

  public void grabImage(){
    for (Element i: imageSet){
      String linkData= i.attr("src");
      if( linkData.length()>0){
        if(linkData.length()<4)// if less than four doesnt have the full link http;
          linkData = doc.baseUri()+linkData.substring(1);
        else if(!linkData.substring(0,4).equals("http"));
          linkData = doc.baseUri()+linkData.substring(1);
        clickableLink label = new clickableLink(linkData,linkData);
        panel.add(label);
      }
    }
  }
}

