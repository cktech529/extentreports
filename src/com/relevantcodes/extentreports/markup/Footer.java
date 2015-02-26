package com.relevantcodes.extentreports.markup;

import com.relevantcodes.extentreports.support.*;

class Footer extends Configuration implements IFooter {
	private String filePath;
	private Configuration instance;
	
	public Configuration useExtentFooter(Boolean use) {
		String start = MarkupFlag.get("PROJECTFOOTER");
		String end = MarkupFlag.get("/PROJECTFOOTER");
		String markup = FileReaderEx.readAllText(filePath);
		
		if (use) {
			markup = markup.replace(start + "<!--", start).replace("-->" + end, end);
			FileWriterEx.write(filePath, markup);
		}
		else {
			markup = markup.replace(start, start + "<!--").replace(end, "-->" + end);		
			FileWriterEx.write(filePath, markup);
		}

		return instance;
	}
	
	// ToDo
	// provide ability to add custom footers
	// 	addCustomFooter("MyCompanyName", [ "<a href=''>Link1</a>", "<a href=''>Link2</a>" ]);
	//		MyCompanyName
	//			+ Link1
	//			+ Link2
	// private void addCustomFooter(String header, String[] chilren) { }
	
	@Deprecated
	public void addExtentFooter() {
		String placeHolderStart = MarkupFlag.get("PROJECTFOOTER");
		String placeHolderEnd = MarkupFlag.get("/PROJECTFOOTER");
		String markup = FileReaderEx.readAllText(filePath);
		
		if (markup.contains(placeHolderStart + "<!--")) {
			markup = markup.replace(placeHolderStart + "<!--", placeHolderStart);
			markup = markup.replace("-->" + placeHolderEnd, placeHolderEnd);
			
			FileWriterEx.write(filePath, markup);
		}
	}
	
	@Deprecated
	public void removeExtentFooter() {
		String placeHolderStart = MarkupFlag.get("PROJECTFOOTER");
		String placeHolderEnd = MarkupFlag.get("/PROJECTFOOTER");
		String markup = FileReaderEx.readAllText(filePath);
		
		if (markup.contains(placeHolderStart + "<!--")) {
			return;
		}
		
		markup = markup.replace(placeHolderStart, placeHolderStart + "<!--");
		markup = markup.replace(placeHolderEnd, "-->" + placeHolderEnd);
		
		FileWriterEx.write(filePath, markup);
	}
	
	public Footer(String filePath, Configuration instance) {
		this.filePath = filePath;
		this.instance = instance;
	}
}
