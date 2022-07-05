package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person p1 = new Person("Donald", "Duck", 12);
        Person p2 = new Person("Rick", "Sanchez", 65);
        Person p3 = new Person("Billy", "Butcher", 37);

        p1.introducePerson();
        p2.introducePerson();
        p3.introducePerson();
    }
}
