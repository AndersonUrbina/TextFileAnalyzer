import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TextFileAnalyzer {
    public static void main(String[] args) {
        //Declare a Scanner
        Scanner scan = new Scanner(System.in);

        //Define the files
        String txtFileName = "Turista.txt";
        String docxFileName = "Turista.docx";

        //Get the Char to count from the user
        System.out.println("Welcome to the Text File Analyzer");

        //
        System.out.print("\nEnter the character you want to count: ");
        String userInput = scan.nextLine();

        //Filter malicious input
        while (userInput.isEmpty()){
            System.out.println("Please enter at least one character: ");
            userInput = scan.nextLine();
        }

        char searchChar = userInput.charAt(0);

        //Open the files
        File txtFile = new File(txtFileName);
        File docxFile = new File(docxFileName);

        //Analyze the files
        try{
            //Check the text file
            int lineCount = countLines(txtFile);
            int wordCount = countWords(txtFile);
            int charCount = countCharacter(txtFile, searchChar);

            //check txt file
            System.out.println();
            System.out.println("File Name: " + txtFile.getName());
            System.out.println("File Size: " + txtFile.length() + " bytes");
            System.out.println("Line Count: " + lineCount);
            System.out.println("Word Count: " + wordCount);
            System.out.println("Character count for " + "'" + searchChar + "'" + " : " + charCount);

            //Check the docx file:
            System.out.println("\nDocx File Analysis: ");
            System.out.println("File Name: " + docxFile.getName());
            System.out.println("File Size: " + docxFile.length() + " bytes");
            System.out.println();

            //Comparison
            System.out.println("Comparison: ");
            if (docxFile.length() > txtFile.length())
                System.out.println("The DOCX file is larger than the TXT files!");
            else if (docxFile.length() < txtFile.length())
                System.out.println("The TXT files are smaller than the DOCX files!");
            else
                System.out.println("The files sizes are the same!");

        } catch (FileNotFoundException e) {
            System.out.println("File Error: " + e.getMessage());
            System.out.println("Make sure the text and Docx are in the same project directory.");
        }
    }
    //helper Methods

    //Method to count lines
    public static int countLines(File file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);
        int lineCount = 0;
        while (fileReader.hasNextLine()) {
            lineCount++;
            fileReader.nextLine();
        }

        fileReader.close();
        return lineCount;
    }

    //Method to count words
    public static int countWords(File file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);
        int wordCount = 0;

        //While loop to count words
        while (fileReader.hasNext()) {
            wordCount++;
            fileReader.next();
        }

        fileReader.close();
        return wordCount;
    }

    //Method to count Chars
    public static int countCharacter(File file, char searchCharacter) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);
        int charCount = 0;
        char lowerChar = Character.toLowerCase(searchCharacter);

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();

            for  (int i = 0; i < line.length(); i++) {
                char currentChar = Character.toLowerCase(line.charAt(i));
                if (lowerChar == currentChar)
                    charCount++;
            }

        }

        fileReader.close();
        return charCount;
    }
}
