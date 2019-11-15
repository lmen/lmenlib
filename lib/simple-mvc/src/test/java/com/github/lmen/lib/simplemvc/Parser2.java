package com.github.lmen.lib.simplemvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Parser2 {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		
		XMLEventReader xmlr = xmlif.createXMLEventReader(Parser2.class.getResourceAsStream("/dssd.xml"));
				
		while (xmlr.hasNext()) {
			XMLEvent myEvent = xmlr.nextEvent();
			if (myEvent.isStartElement()) {
				StartElement startElement = myEvent.asStartElement();
				String name = startElement.getName().getLocalPart();
				System.out.println("START:" + name);												
			}
			if (myEvent.isCharacters()) {
				String text = myEvent.asCharacters().getData();
				System.out.println("text: [" + text + "]");
			}
			if (myEvent.isEndElement()) {
				EndElement endElement = myEvent.asEndElement();
				String name = endElement.getName().getLocalPart();
				System.out.println("END: " + name);
			}
		}

	}

}
