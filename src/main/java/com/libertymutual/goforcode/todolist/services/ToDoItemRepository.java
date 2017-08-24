// ToDoItemRepository.java
package com.libertymutual.goforcode.todolist.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.libertymutual.goforcode.todolist.models.ToDoItem;

@Service
public class ToDoItemRepository {

    private int nextId = 1;

    /**
     * Get all the items from the file. 
     * @return A list of the items. If no items exist, returns an empty list.
     */
    public List<ToDoItem> getAll() {
        // Replace this with something meaningful
    	List<ToDoItem> listOfItems = new ArrayList<ToDoItem>();

		try (Reader in = new FileReader("to-do-list.csv")){
			Iterable<CSVRecord> records = null;
			records = CSVFormat.DEFAULT.parse(in);
    	for (CSVRecord record : records) {
    		ToDoItem item = new ToDoItem();
    		item.setId(Integer.parseInt(record.get(0)));
    		item.setText(record.get(1));
    		item.setComplete(Boolean.parseBoolean(record.get(2)));
    		listOfItems.add(item);
    		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Hit FileNotFoundException in getAll() function.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Hit IOException in getAll() function");
		}
        return listOfItems;
    }

    /**
     * Assigns a new id to the ToDoItem and saves it to the file.
     * @param item The to-do item to save to the file.
     */
    public void create(ToDoItem item) {
        // Fill this in with something meaningful
    	String[] stringArray = new String[3];
    	item.setId(nextId);
    	item.setComplete(false);
    	nextId += 1;
    	
    	Writer out = null;
		try {
			out = new FileWriter("to-do-list.csv", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Hit IOException when trying to set new FileWriter in create() function.");
		}
        try {
        	stringArray[0] = (Integer.toString(item.getId()));
        	stringArray[1] = (item.getText());
        	stringArray[2] = (Boolean.toString(item.isComplete()));
        	CSVPrinter printer = CSVFormat.DEFAULT.print(out);
        	
        	printer.printRecord(stringArray);
        	out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("hit IOException when trying to printRecord in create() function");
		}
    }

    /**
     * Gets a specific ToDoItem by its id.
     * @param id The id of the ToDoItem.
     * @return The ToDoItem with the specified id or null if none is found.
     */
    public ToDoItem getById(int id) {
        // Replace this with something meaningful
    	List<ToDoItem> currentListOfItems = getAll();
    	
    	for (ToDoItem tempItem : currentListOfItems) {
    		if (tempItem.getId() == id) {
    			return tempItem;
    		}
    	}
        return null;
    }

    /**
     * Updates the given to-do item in the file.
     * @param item The item to update.
     */
    public void update(ToDoItem item) {
        // Fill this in with something meaningful
    	List<ToDoItem> currentListOfItems = getAll();
    	List<ToDoItem> updatedListOfItems = new ArrayList<ToDoItem>();
    	String[] stringArray = new String[3];
    	for (ToDoItem tempItem : currentListOfItems) {
    		if (tempItem.getId() == item.getId()) {
    			updatedListOfItems.add(item);
    		} else {
    			updatedListOfItems.add(tempItem);
    		}
    	}
    	
    	int i = 0;
    	for (ToDoItem tempItem : updatedListOfItems) {
    		if (i == 0) {
    			i += 1;
	        	Writer out = null;
	    		try {
	    			out = new FileWriter("to-do-list.csv", false);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			System.out.println("Hit IOException when trying to set new FileWriter in first section of update() function.");
	    		}
	            try {
	            	stringArray[0] = (Integer.toString(tempItem.getId()));
	            	stringArray[1] = (tempItem.getText());
	            	stringArray[2] = (Boolean.toString(tempItem.isComplete()));
	            	CSVPrinter printer = CSVFormat.DEFAULT.print(out);
	            	
	            	printer.printRecord(stringArray);
	            	out.close();
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			System.out.println("hit IOException when trying to printRecord in first section of update() function");
	    		}
	    	} else {
	        	Writer out = null;
	    		try {
	    			out = new FileWriter("to-do-list.csv", true);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			System.out.println("Hit IOException when trying to set new FileWriter in second section of update() function.");
	    		}
	            try {
	            	stringArray[0] = (Integer.toString(tempItem.getId()));
	            	stringArray[1] = (tempItem.getText());
	            	stringArray[2] = (Boolean.toString(tempItem.isComplete()));
	            	CSVPrinter printer = CSVFormat.DEFAULT.print(out);
	            	
	            	printer.printRecord(stringArray);
	            	out.close();
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			System.out.println("hit IOException when trying to printRecord in second section of update() function");
	    		}
	    	}
    	}
    }
}
