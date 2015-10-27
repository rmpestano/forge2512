package org.jboss.forge;

/**
 * Created by rafael-pestano on 27/10/2015.
 */

public class Person implements Comparable<Person>{

  private String name;

  public Person() {
  }

  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Person person = (Person) o;

    return !(name != null ? !name.equals(person.name) : person.name != null);

  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override
  public int compareTo(Person o) {

    return o.getName().compareTo(name);
  }
}
