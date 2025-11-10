import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagExtractor extends JFrame {
    private JTextArea output;
    private File textFile, stopWordFile;
    private Set<String> stopWords = new TreeSet<String>();
    private Map<String, Integer> tagMap = new TreeMap<String, Integer>();

    public TagExtractor(){
        super("Tag Extractor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);
        setLayout(new BorderLayout());

        output = new JTextArea();
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);

        JButton selectTextBtn = new JButton("Select Text File");
        JButton selectStopWordBtn = new JButton("Select Stop Word File");
        JButton extractBtn = new JButton("Extract Tags");
        JButton saveBtn = new JButton("Save Results");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectTextBtn);
        buttonPanel.add(selectStopWordBtn);
        buttonPanel.add(extractBtn);
        buttonPanel.add(saveBtn);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        selectTextBtn.addActionListener(e -> chooseTextFile());
        selectStopWordBtn.addActionListener(e -> chooseStopWordFile());
        extractBtn.addActionListener(e -> extractTags());
        saveBtn.addActionListener(e -> saveResults());
        setVisible(true);
    }

    private void chooseTextFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Text File");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            textFile = chooser.getSelectedFile();
            JOptionPane.showMessageDialog(this,"Selected Text File: " + textFile.getName());
        }

    }

    private void chooseStopWordFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Stop Word File");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            stopWordFile = chooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Selected Stop Word File" + stopWordFile.getName());
        }
    }
    private void extractTags(){
        if(textFile == null){
            JOptionPane.showMessageDialog(this,"Please Select Text File");
            return;
        }
        loadStopWords();
        tagMap.clear();



        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        try (Scanner scanner = new Scanner(textFile)){
            while (scanner.hasNextLine()){
                String rawWord = scanner.next().toLowerCase();
                Matcher matcher = pattern.matcher(rawWord);
                if (matcher.find()){
                    String Word = matcher.group();
                    if (!stopWords.contains(Word)){
                        tagMap.put(Word, tagMap.getOrDefault(Word, 0)+1 );
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        displayResults();
    }

    private void loadStopWords() {
        stopWords.clear();
        if (stopWordFile == null){
            return;
        }try (Scanner scanner = new Scanner(stopWordFile)){
            while (scanner.hasNextLine()){
                String rawWord = scanner.next().toLowerCase();
                if(!rawWord.isEmpty()) stopWords.add(rawWord);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private void displayResults(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tags extracted from file:\n\n");
        tagMap.forEach((word, freq)-> sb.append(String.format("%-20s : %d%n", word, freq)));
        output.setText(sb.toString());
    }
    private void saveResults(){
        if (tagMap.isEmpty()){
            JOptionPane.showMessageDialog(this,"No tags to save. Try Again!");
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Tag Output File");
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            File outputFile = chooser.getSelectedFile();
            try(PrintWriter writer = new PrintWriter(outputFile)){
                tagMap.forEach((word, freq)-> writer.println(word+ " : " + freq));
                JOptionPane.showMessageDialog(this,"Tags saved to:" + outputFile.getName());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
