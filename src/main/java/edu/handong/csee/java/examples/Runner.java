package edu.handong.csee.java.examples;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.File;

public class Runner {
	
	String path;
	boolean verbose;
	boolean help;
	String fullpath;

	public static void main(String[] args) {

		Runner myRunner = new Runner();
		myRunner.run(args);

	}

	private void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			
			if(path!=null) {
				findFile3(args);
			}
			
			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided \"" + path + "\" as the value of the option p");
			
			// TODO show the number of files in the path
			
			if(verbose) {
				// TODO list all files in the path
				findFile2(args);
				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");
			}
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("p");
			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("p").longOpt("path")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("verbose")
				.desc("Display detailed messages!")
				//.hasArg()     // this option is intended not to have an option value but just an option
				.argName("verbose option")
				//.required() // this is an optional option. So disabled required().
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());
		
		options.addOption(Option.builder("f").longOpt("fullpath")
				.desc("Display full path message")
				.hasArg()
				.argName("full path to display")
				.required()
				.build());
		
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}
	
	public void findFile(String name) {
		File file = new File("filefolder\\test.txt");
		int count;
		if(file.exists()) {
			try {
				System.out.println("getpath: " + file.getAbsolutePath());
			}catch(IOException e) {
				System.err.println(e);
			}
		}
	}
	
	public void findFile2(String name) {
		File file = new File(".");
		
		if(file.exists()&&file.isDirectory()) {
			
			String[] fList =file.list();
			
			for(int i=0; i<fList.length; i++) {
				System.out.println(fList[i]);
			}
		}
		else {
			System.out.println("해당 경로는 폴더가 아닙니다.");
		}
	}
	public void findFile3(String[] args) {
		File file = new File(".");
		
		if(file.exists()&&file.isDirectory()) {
			
			String[] fList =file.list();
				System.out.println(fList.length);
			}
		else {
			System.out.println("해당 경로는 폴더가 아닙니다.");
		}
	}

}
