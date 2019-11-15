package com.github.lmen.lib.simplemvc;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Parser {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

		XMLInputFactory xmlif = XMLInputFactory.newInstance();

		XMLStreamReader xmlr = xmlif.createXMLStreamReader(Parser2.class.getResourceAsStream("/dssd.xml"));

		// when XMLStreamReader is created,
		// it is positioned at START_DOCUMENT event.

		// check if there are more events
		// in the input stream
		while (xmlr.hasNext()) {

			int eventType = xmlr.getEventType();

			switch (eventType) {
			case XMLStreamConstants.START_ELEMENT: {
				String name = xmlr.getLocalName();
				System.out.println("Start " + name);
			}
				break;

			case XMLStreamConstants.END_ELEMENT: {
				String name = xmlr.getLocalName();
				System.out.println("END " + name);
			}
				break;

			case XMLStreamConstants.CHARACTERS: {
				System.out.println("CHARS " + xmlr.getText());
			}
				break;

			default:
				break;
			}
			
			xmlr.next();

		}

	}

}
