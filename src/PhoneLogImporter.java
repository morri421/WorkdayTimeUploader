import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PhoneLogImporter {
	
	String filepath = "C:\\Users\\u6064842\\AppData\\Roaming\\QPhoneCisco\\Logs\\Test.log";
	List<String> log_line_list = new ArrayList<>(); 
	List<String> partioned_list = new ArrayList<>();
	
	public void getLogFile() {
	
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath))) {

			//br returns as stream and convert it into a List
			log_line_list = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		moveStateLinesToNewList();
		
		for (String sorted_part : partioned_list) {
			System.out.println(sorted_part);
		}
		}
	
	private void moveStateLinesToNewList() {
		
		for (String a_line : log_line_list) {
			String[] split_line = a_line.split(" ");
			Collections.reverse(Arrays.asList(split_line)); //reverses line to cut down on iteration performance
			
			if (split_line.length >= 4 ) { //if the line is big enough and it has button in a particular spot, moves line to new arraylist
				if (split_line[1].equals("button")) {
					partioned_list.add(a_line);
				}
			}	
	}
}
}
