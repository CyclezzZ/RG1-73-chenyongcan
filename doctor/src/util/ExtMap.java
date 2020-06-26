package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtMap {
	public static Map<String, List<String>> EXT_MAP = new HashMap<String, List<String>>();
	static {
		EXT_MAP.put("文档", StringUtil.list(".txt;.doc;.docx;.pdf;.xls;.xlsx;.html;.js;.css;.jsp;.hlp;.wps;.rtf", ";"));
		EXT_MAP.put("视频", StringUtil.list(".mp4;.avi;.mkv;.rm;.rmvb;.mpg;.mov;.swf", ";"));
		EXT_MAP.put("音频", StringUtil.list(".mp3;.aac;.flac;.wav;.aif;.au", ";"));
		EXT_MAP.put("图形", StringUtil.list(".bmp;.gif;.jpg;.pic;.png;.tif;.jpeg;.icon;.png", ";"));
	}

	public static boolean isType(String typeName, String ext) {
		List<String> templist = EXT_MAP.get(typeName);
		if (templist == null)
			return false;
		return templist.contains(ext);
	}

}
