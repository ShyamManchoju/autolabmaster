package com.autolab.autolabmaster.controllers;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("languageBean")
public class LanguageController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(LanguageController.class);
	private String localeCode;

	private static Map<String,Object> languages;
	static{
		languages = new LinkedHashMap<String,Object>();
		languages.put("English", Locale.ENGLISH); //label, value
		languages.put("Telugu", "te");
	}

	public Map<String, Object> getLanguagesInMap() {
		return languages;
	}


	public String getLocaleCode() {
		return localeCode;
	}


	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	//value change event listener
	public void languageLocaleCodeChanged(ValueChangeEvent e){

		String newLocaleValue = e.getNewValue().toString();

		if(logger.isDebugEnabled()){
			logger.debug("newLocaleValue :: " + newLocaleValue);
		}
		//loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : languages.entrySet()) {

			if(entry.getValue().toString().equals(newLocaleValue)){
				if(logger.isDebugEnabled()){
					logger.debug("newLocaleValue entry value :: " + (Locale)entry.getValue());
				}
				FacesContext.getCurrentInstance()
				.getViewRoot().setLocale((Locale)entry.getValue());

			}
		}
	}

}
