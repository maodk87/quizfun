package quizfun.view.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quizfun.model.entity.Module;
import quizfun.view.servicelocator.ServiceLocator;
import quizfun.view.util.JSFUtils;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.matchers.TextMatcherEditor;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.selectinputtext.SelectInputText;

public abstract class ModuleSelectManagedBean {
	final Logger logger = LoggerFactory.getLogger(ModuleSelectManagedBean.class);
	
	protected ServiceLocator serviceLocator;
	
	protected Module module;
	
	protected List<SelectItem> moduleSelectItemList;
	
	protected SelectInputText moduleSelectInputText;
	
	protected String moduleCode;
	
	protected TextMatcherEditor<SelectItem> moduleMatcher;
	
	protected String selectedModule;
	
	protected HtmlInputText selectedModuleInputText;	
	
	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}	
	
	protected void initializeModuleSelectInput() {
		TextFilterator<SelectItem> selectItemFilterator = new TextFilterator<SelectItem>() {
			@Override
			public void getFilterStrings(List<String> baseList,
					SelectItem element) {
				baseList.add(element.getLabel());
			}
		};
		
		List<Module> list = serviceLocator.getModuleService().findAllModules();

		if (list != null && !list.isEmpty()) {
			moduleSelectItemList = new ArrayList<SelectItem>(list.size());
			
			for (Module module : list) {
				moduleSelectItemList.add(new SelectItem(module, module.getCode()));
			}
			
			moduleMatcher = new TextMatcherEditor<SelectItem>(selectItemFilterator);
			moduleSelectItemList = new FilterList<SelectItem>(GlazedLists.eventList(moduleSelectItemList), moduleMatcher);
		}
	}
	
	public void moduleValueChangeListener(ValueChangeEvent event) {
		SelectInputText selectInputText = (SelectInputText) event.getComponent();
		String value = (String) event.getNewValue();

		if (logger.isDebugEnabled()) {
			logger.debug("Module value change listener. Value: " + value);
		}

		moduleMatcher.setMode(TextMatcherEditor.STARTS_WITH);
		moduleMatcher.setFilterText((value == null) ? new String[] {} : new String[] { value });

		SelectItem selectedItem = selectInputText.getSelectedItem();
		if (selectedItem != null) {
			Module module = (Module) selectedItem.getValue();
			this.module = module;			
			selectedModule = JSFUtils.getStringFromBundle("question.selectedmodule.display.pattern", new Object[] {
					module.getCode(), module.getName() });
		} else {
			module = null;
			selectedModule = null;
		}
		selectedModuleInputText.resetValue();
	}
	

	
	public void clearModuleActionListener(ActionEvent event) {
		module = null;
		moduleCode = null;
		selectedModule = null;
		
		moduleSelectInputText.resetValue();
		selectedModuleInputText.resetValue();
		
		moduleMatcher.setFilterText(new String[] {});
		
		moduleSelectInputText.requestFocus();
	}	
	
	public SelectInputText getModuleSelectInputText() {
		return moduleSelectInputText;
	}

	public void setModuleSelectInputText(SelectInputText moduleSelectInputText) {
		this.moduleSelectInputText = moduleSelectInputText;
	}
	
	
	public List<SelectItem> getModuleSelectItemList() {
		return moduleSelectItemList;
	}

	public void setModuleSelectItemList(List<SelectItem> moduleSelectItemList) {
		this.moduleSelectItemList = moduleSelectItemList;
	}	

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	public String getSelectedModule() {
		return selectedModule;
	}

	public void setSelectedModule(String selectedModule) {
		this.selectedModule = selectedModule;
	}	
	
	public HtmlInputText getSelectedModuleInputText() {
		return selectedModuleInputText;
	}

	public void setSelectedModuleInputText(HtmlInputText selectedModuleInputText) {
		this.selectedModuleInputText = selectedModuleInputText;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
}
