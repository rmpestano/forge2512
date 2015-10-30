package org.jboss.forge.ui;

import org.jboss.forge.Person;
import org.jboss.forge.PersonFilter;
import org.jboss.forge.addon.ui.command.AbstractUICommand;
import org.jboss.forge.addon.ui.context.UIBuilder;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.context.UIExecutionContext;
import org.jboss.forge.addon.ui.context.UINavigationContext;
import org.jboss.forge.addon.ui.hints.InputType;
import org.jboss.forge.addon.ui.input.UIInput;
import org.jboss.forge.addon.ui.input.ValueChangeListener;
import org.jboss.forge.addon.ui.input.events.ValueChangeEvent;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.metadata.WithAttributes;
import org.jboss.forge.addon.ui.result.NavigationResult;
import org.jboss.forge.addon.ui.util.Metadata;
import org.jboss.forge.addon.ui.util.Categories;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.result.Results;
import org.jboss.forge.addon.ui.wizard.UIWizard;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ListPersonWizard extends AbstractUICommand implements UIWizard {

	@Inject
	@WithAttributes(label = "Name", description = "Filter by person name", type = InputType.DEFAULT)
	private UIInput<String> name;

	@Inject
	@WithAttributes(label = "Size", description = "Result list size", type = InputType.DEFAULT, defaultValue = "10")
	private UIInput<Integer> size;

	@Inject
	PersonFilter filter;

	List<Person> allPerson;

	@Override
	public UICommandMetadata getMetadata(UIContext context) {
		return Metadata.forCommand(ListPersonWizard.class).name("Person: filter")
				.category(Categories.create("Person"));
	}

	private void createListOfPerson() {
		allPerson = new ArrayList<>();
		int count = size.hasValue() ? size.getValue() : 10;

		for (int i = 0; i < count; i++) {
			allPerson.add(new Person("person"+i));
		}
	}

	@Override
	public void initializeUI(UIBuilder builder) throws Exception {
		builder.add(name).add(size);
	}

	@Override
	public Result execute(UIExecutionContext context) throws Exception {
		return Results.success("Command 'PersonList' successfully executed!");
	}

	@Override
	public NavigationResult next(UINavigationContext context) throws Exception {
		createListOfPerson();
		filterByName(name.getValue());
	    return context.navigateTo(ListPersonStep.class);
	}

	private void filterByName(String name) {
		filter.getList().clear();
		name = (name == null ? "": name);
		for (Person person : allPerson) {
			if(person.getName().toLowerCase().contains(name.toLowerCase())){
				filter.add(person);
			}
		}
	}
}