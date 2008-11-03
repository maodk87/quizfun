/*
 * QuizFun - A quiz game
 * Copyright (C) 2008
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package quizfun.view.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * @author M. Isuru Tharanga Chrishantha Perera
 */
public class JSFUtils {

	public static String getStringFromBundle(String key) {
		ResourceBundle bundle = getBundle();
		return bundle.getString(key);
	}

	public static FacesMessage getMessageFromBundle(String key, FacesMessage.Severity severity) {
		return getMessageFromBundle(key, severity, null);
	}

	public static FacesMessage getMessageFromBundle(String key, FacesMessage.Severity severity, Object[] args) {
		ResourceBundle bundle = getBundle();
		String summary = bundle.getString(key);
		if (args != null) {
			summary = MessageFormat.format(summary, args);
		}
		String detail = null;
		try {
			bundle.getString(key + "_detail");
		} catch (MissingResourceException e) {
			// ignore the exception
		}
		if (detail != null && args != null) {
			detail = MessageFormat.format(detail, args);
		}
		FacesMessage message = new FacesMessage(summary, detail);
		message.setSeverity(severity);
		return message;
	}

	private static ResourceBundle getBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot uiViewRoot = context.getViewRoot();
		Locale locale = uiViewRoot.getLocale();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return ResourceBundle.getBundle(context.getApplication().getMessageBundle(), locale, classLoader);
	}

	public static void storeOnSessionMap(String key, Object object) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put(key, object);
	}

	public static Object removeFromSessionMap(String key) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return sessionMap.remove(key);
	}

	public static Object getFromSessionMap(String key) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return sessionMap.get(key);
	}
	
    public static String getFromRequestParameterMap(String key) {
    	Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return requestParameterMap.get(key);
    }
    
	public static void storeOnRequestMap(String key, Object object) {
		Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		requestMap.put(key, object);
	}
    
	public static Object removeFromRequestMap(String key) {
		Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		return requestMap.remove(key);
	}
    
	public static Object getFromRequestMap(String key) {
		Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		return requestMap.get(key);
	}

	public static void addApplicationErrorMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, getMessageFromBundle("application.error", FacesMessage.SEVERITY_FATAL));
	}

	public static void addFacesInfoMessage(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, getMessageFromBundle(key, FacesMessage.SEVERITY_INFO));
	}

	public static void addFacesInfoMessage(String key, Object[] args) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, getMessageFromBundle(key, FacesMessage.SEVERITY_INFO, args));
	}

	public static void addFacesWarnMessage(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, getMessageFromBundle(key, FacesMessage.SEVERITY_WARN));
	}

	public static void addFacesWarnMessage(String key, Object[] args) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, getMessageFromBundle(key, FacesMessage.SEVERITY_WARN, args));
	}

	public static void addFacesErrorMessage(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, getMessageFromBundle(key, FacesMessage.SEVERITY_ERROR));
	}

	public static void addFacesErrorMessage(String key, Object[] args) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, getMessageFromBundle(key, FacesMessage.SEVERITY_ERROR, args));
	}

	public static void addFacesInfoMessage(UIComponent component, String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(component.getClientId(facesContext), getMessageFromBundle(key, FacesMessage.SEVERITY_INFO));
	}

	public static void addFacesInfoMessage(UIComponent component, String key, Object[] args) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(component.getClientId(facesContext), getMessageFromBundle(key, FacesMessage.SEVERITY_INFO, args));
	}

	public static void addFacesErrorMessage(UIComponent component, String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(component.getClientId(facesContext), getMessageFromBundle(key, FacesMessage.SEVERITY_ERROR));
	}

	public static void addFacesErrorMessage(UIComponent component, String key, Object[] args) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(component.getClientId(facesContext), getMessageFromBundle(key, FacesMessage.SEVERITY_ERROR, args));
	}
}
