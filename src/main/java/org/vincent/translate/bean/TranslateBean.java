package org.vincent.translate.bean;

/**
 * 翻译文件中每一个翻译项
 * @author liuhy
 *
 */
public class TranslateBean {

	/** 待翻译项 键 */
	private String key;
	/** 待翻译项的中文 文本 */
	private String ChText;
	
	/** 待翻译项的英文文本 */
	private String EnText;

	
	@Override
	public String toString() {
		return "TranslateBean [key=" +( key==null ? "": key) + ", ChText=" + (ChText==null ? "": ChText) + ", EnText=" + (EnText==null ? "" : EnText) + "]";
	}

	public TranslateBean() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getChText() {
		return ChText;
	}

	public void setChText(String chText) {
		ChText = chText;
	}

	public String getEnText() {
		return EnText;
	}

	public void setEnText(String enText) {
		EnText = enText;
	}
	
	
}
