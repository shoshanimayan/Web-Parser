package webparser;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.util.Map.Entry;
import java.net.*;

import javax.swing.JLabel;


public class clickableLink extends JLabel{
	String text;
	URI uri;
	
	public clickableLink (String textin, String uriIn){
		super();
		try {
			uri = new URI(uriIn);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		text = textin;
		setText(text, true);
		setToolTipText(uri.toString());
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				open(uri);
			}
			public void mouseEntered(MouseEvent e){
				setText(text, false);
			}
			public void mouseExited(MouseEvent e){
				setText(text, true);
			}
		});
	}
	private void setText(String text, boolean ul){
		String link = ul ? "<u>"+text+"<u>" : text;
		setText("<html><span style=\"color:#000099;\">"+link+"</span></html>");
	}
	private void open(URI uri){
		if(Desktop.isDesktopSupported()){
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(uri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
