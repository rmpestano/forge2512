package org.jboss.forge;

import org.jboss.forge.addon.ui.cdi.CommandScoped;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael-pestano on 27/10/2015.
 */
@CommandScoped
public class PersonFilter {

  private List<Person> list;

  @PostConstruct
  public void init(){
    list = new ArrayList<>();
  }

  public void add(Person p){
    list.add(p);
  }

  public List<Person> getList() {
    return list;
  }

  public void setList(List<Person> list) {
    this.list = list;
  }
}
