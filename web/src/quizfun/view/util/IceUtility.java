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

import static quizfun.view.util.JSFUtils.getStringFromBundle;

import java.util.List;

import javax.faces.model.SelectItem;

import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import quizfun.view.util.Constants;

/**
 * 
 * @author Hiranya Mudunkotuwa
 *
 */
public class IceUtility {
	
	
	/**
	 * Retreive Properties By Key - resources.properties
	 * @param key
	 * @return
	 */
	public static String getResourcesProperty(String key) {
		return getStringFromBundle(Constants.BundleNames.RESOURCES, key);
	}
	
	/**
	 * @param key The key
	 * @param args Arguments
	 * @return String
	 */
	public static String getResourcesProperty(String key, Object[] args) {
		return getStringFromBundle(Constants.BundleNames.RESOURCES, key, args);
	}
	/**
	 * Create an editor for matching text
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public static TextMatcherEditor getMatcherUtility(){
    	TextMatcherEditor editor = new TextMatcherEditor(new TextFilterator() {

            public void getFilterStrings(List stringList, Object obj)
            {
                SelectItem item = (SelectItem) obj;
                stringList.add(item.getLabel());
            }
               
           });
    	return editor;
    }
   
    
}
