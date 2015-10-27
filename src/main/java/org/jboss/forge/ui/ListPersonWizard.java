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
	PersonFilter filter;

	List<Person> allPerson;

	@Override
	public UICommandMetadata getMetadata(UIContext context) {
		allPerson = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			allPerson.add(new Person("person"+i));
		}

		return Metadata.forCommand(ListPersonWizard.class).name("Person: filter")
				.category(Categories.create("Person"));
	}

	@Override
	public void initializeUI(UIBuilder builder) throws Exception {
		name.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChanged(ValueChangeEvent event) {
				filter.getList().clear();
				String name = event.getNewValue().toString();
				if (name != null && !"".equals(name.toString())) {
					filterByName(name);
				}
			}

			private void filterByName(String name) {
				for (Person person : allPerson) {
					if(person.getName().contains(name)){
						filter.add(person);
					}
				}
			}
		});

		builder.add(name);
	}

	@Override
	public Result execute(UIExecutionContext context) throws Exception {
		return Results.success("Command 'PersonList' successfully executed!");
	}

	@Override
	public NavigationResult next(UINavigationContext context) throws Exception {
		 return context.navigateTo(ListPersonStep.class);
	}
}