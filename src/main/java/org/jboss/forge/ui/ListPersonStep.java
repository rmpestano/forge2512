package org.jboss.forge.ui;

import org.jboss.forge.Person;
import org.jboss.forge.PersonFilter;
import org.jboss.forge.addon.ui.command.AbstractUICommand;
import org.jboss.forge.addon.ui.context.UIBuilder;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.context.UIExecutionContext;
import org.jboss.forge.addon.ui.context.UINavigationContext;
import org.jboss.forge.addon.ui.hints.InputType;
import org.jboss.forge.addon.ui.input.UISelectMany;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.metadata.WithAttributes;
import org.jboss.forge.addon.ui.result.NavigationResult;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.result.Results;
import org.jboss.forge.addon.ui.util.Categories;
import org.jboss.forge.addon.ui.util.Metadata;
import org.jboss.forge.addon.ui.wizard.UIWizardStep;

import javax.inject.Inject;
import java.util.List;

public class ListPersonStep extends AbstractUICommand implements UIWizardStep {

    @Inject
    @WithAttributes(label = "Person list", type = InputType.DEFAULT)
    private UISelectMany<Person> personList;

    @Inject
    PersonFilter filter;

    @Override
    public UICommandMetadata getMetadata(UIContext context) {
        List<Person> filteredPerson = filter.getList();

        personList.setValueChoices(filteredPerson);
        personList.setValue(filteredPerson);
        personList.setNote("Person found " + filter.getList().size());

        return Metadata.forCommand(ListPersonStep.class).name("Person: filter")
                .category(Categories.create("Person"));
    }

    @Override
    public void initializeUI(UIBuilder builder) throws Exception {


        builder.add(personList);
    }

    @Override
    public Result execute(UIExecutionContext context) throws Exception {
        return Results.success("Command 'personList' successfully executed!");
    }

    @Override
    public NavigationResult next(UINavigationContext context) throws Exception {
        return null;
    }
}