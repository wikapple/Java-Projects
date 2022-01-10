package bankmanagementsystem;

/*
 * interface Copeable 
 * Used to make a copy of an object with no matching reference variables 
 * Intended make project more immutable
 */
public interface Copeable<T> {

	T copy();
}
