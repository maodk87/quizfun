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

package quizfun.view.bean;

import static quizfun.view.util.IceUtility.getResourcesProperty;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.model.SelectItem;

/**
 * 
 * @author Hiranya Mudunkotuwa
 *
 */
public class StaticListManagedBean {

	/**
	 * Create a SelectItem list for Question Type with out Empty
	 * 
	 * @return
	 */
	public Collection<SelectItem> getConvertedQuestionTypeUnitElementsWOEmpty() {
		
		Collection<SelectItem> convertedListData = new ArrayList<SelectItem>();
		convertedListData.add(new SelectItem(getResourcesProperty("question.select.value.mcq"), getResourcesProperty("question.select.label.mcq")));
		
		return convertedListData;
	}
	
	/**
	 * Create a SelectItem list for Question Level with out Empty
	 * 
	 * @return
	 */
	public Collection<SelectItem> getConvertedQuestionLevelUnitElementsWOEmpty() {
		
		Collection<SelectItem> convertedListData = new ArrayList<SelectItem>();
		convertedListData.add(new SelectItem(getResourcesProperty("question.select.value.easy"), getResourcesProperty("question.select.label.easy")));
		convertedListData.add(new SelectItem(getResourcesProperty("question.select.value.medium"), getResourcesProperty("question.select.label.medium")));
		convertedListData.add(new SelectItem(getResourcesProperty("question.select.value.hard"), getResourcesProperty("question.select.label.hard")));
		
		return convertedListData;
	}


}
