package quizfun.view.bean;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlPanelGroup;
import com.icesoft.faces.component.ext.HtmlSelectBooleanCheckbox;
import com.icesoft.faces.component.selectinputtext.SelectInputText;

import quizfun.model.entity.Question;
import quizfun.view.servicelocator.ServiceLocator;

public abstract class QuestionManagedBean {

	protected ServiceLocator serviceLocator;
	protected Question question;

	protected UIComponent tblAnswers;

	protected HtmlPanelGroup tblPanelGroup;
	protected HtmlInputTextarea quesInputTextArea;
	protected HtmlInputTextarea hintInputTextArea;
	protected HtmlInputTextarea refInputTextArea;
	protected HtmlInputTextarea answrInputTextArea;
	protected SelectInputText moduleCodeSelectInputText;
	protected SelectInputText moduleNameSelectInputText;
	protected HtmlSelectOneMenu typeSelectOneMenu;
	protected HtmlSelectOneMenu levelSelectOneMenu;
	protected HtmlSelectBooleanCheckbox correctSelectBooleanCheckbox;

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	protected void clearValues() {
		/*
		 * course.setCode(null); course.setName(null);
		 */
	}

	public void clearActionListener(ActionEvent event) {
		clearValues();
		/*
		 * codeInputText.resetValue(); nameInputText.resetValue();
		 */
	}
	
	public UIComponent getTblAnswers() {
		return tblAnswers;
	}

	public void setTblAnswers(UIComponent tblAnswers) {
		this.tblAnswers = tblAnswers;
	}

	public HtmlPanelGroup getTblPanelGroup() {
		return tblPanelGroup;
	}

	public void setTblPanelGroup(HtmlPanelGroup tblPanelGroup) {
		this.tblPanelGroup = tblPanelGroup;
	}

	public HtmlInputTextarea getQuesInputTextArea() {
		return quesInputTextArea;
	}

	public void setQuesInputTextArea(HtmlInputTextarea quesInputTextArea) {
		this.quesInputTextArea = quesInputTextArea;
	}

	public HtmlInputTextarea getHintInputTextArea() {
		return hintInputTextArea;
	}

	public void setHintInputTextArea(HtmlInputTextarea hintInputTextArea) {
		this.hintInputTextArea = hintInputTextArea;
	}

	public HtmlInputTextarea getRefInputTextArea() {
		return refInputTextArea;
	}

	public void setRefInputTextArea(HtmlInputTextarea refInputTextArea) {
		this.refInputTextArea = refInputTextArea;
	}

	public HtmlInputTextarea getAnswrInputTextArea() {
		return answrInputTextArea;
	}

	public void setAnswrInputTextArea(HtmlInputTextarea answrInputTextArea) {
		this.answrInputTextArea = answrInputTextArea;
	}

	public SelectInputText getModuleCodeSelectInputText() {
		return moduleCodeSelectInputText;
	}

	public void setModuleCodeSelectInputText(
			SelectInputText moduleCodeSelectInputText) {
		this.moduleCodeSelectInputText = moduleCodeSelectInputText;
	}

	public SelectInputText getModuleNameSelectInputText() {
		return moduleNameSelectInputText;
	}

	public void setModuleNameSelectInputText(
			SelectInputText moduleNameSelectInputText) {
		this.moduleNameSelectInputText = moduleNameSelectInputText;
	}

	public HtmlSelectOneMenu getTypeSelectOneMenu() {
		return typeSelectOneMenu;
	}

	public void setTypeSelectOneMenu(HtmlSelectOneMenu typeSelectOneMenu) {
		this.typeSelectOneMenu = typeSelectOneMenu;
	}

	public HtmlSelectOneMenu getLevelSelectOneMenu() {
		return levelSelectOneMenu;
	}

	public void setLevelSelectOneMenu(HtmlSelectOneMenu levelSelectOneMenu) {
		this.levelSelectOneMenu = levelSelectOneMenu;
	}

	public HtmlSelectBooleanCheckbox getCorrectSelectBooleanCheckbox() {
		return correctSelectBooleanCheckbox;
	}

	public void setCorrectSelectBooleanCheckbox(
			HtmlSelectBooleanCheckbox correctSelectBooleanCheckbox) {
		this.correctSelectBooleanCheckbox = correctSelectBooleanCheckbox;
	}
}
