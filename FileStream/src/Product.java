import javax.naming.Name;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Product {
    private final String ID;
    private String name;
    private String description;
    private double cost;

    public static final int ID_Size=6;
    public static final int NAME_Size=35;
    public static final int DESCRIPTION_Size=75;
    public static final int Record_Size =
            (ID_Size + NAME_Size + DESCRIPTION_Size) * 2 + 8;

    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
    private String pad(String s, int length){
        if(s.length()>length){
            return s.substring(0,length);
        }
        return String.format("%-"+length+"s",s);
    }
    public void writeRandomAccess(RandomAccessFile raf) throws IOException{
        raf.writeChars(pad(ID, ID_Size));
        raf.writeChars(pad(name, NAME_Size));
        raf.writeChars(pad(description, DESCRIPTION_Size));
        raf.writeDouble(cost);
    }
    public static Product readRandomAccess(RandomAccessFile raf) throws IOException {
        String id = readFixedString(raf, ID_Size);
        String name = readFixedString(raf, NAME_Size);
        String description = readFixedString(raf, DESCRIPTION_Size);
        double cost = raf.readDouble();
        return new Product(id, name, description, cost);



    }
    public String getName() {
        return name;
    }
    public String toCSV(){
        return ID + "," + name + "," + description + "," + cost;
    }


    private static String readFixedString(RandomAccessFile raf, int size) throws IOException {
        StringBuilder sb = new StringBuilder(size);
        for(int i = 0; i < size; i++){
            sb.append(raf.readChar());
        }
        return sb.toString();
    }
}
