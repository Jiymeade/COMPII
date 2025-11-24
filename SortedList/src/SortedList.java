import java.util.ArrayList;

public class SortedList {
    private ArrayList<String> data = new ArrayList<>();

    public void add(String value){
        int index = findInsertIndex(value);
        data.add(index, value);
    }
    private int findInsertIndex(String value){
        int low = 0;
        int high = data.size()-1;
        while(low <= high){
            int mid = (low+high)/2;
            String midVal = data.get(mid);
            int compareVal = midVal.compareTo(value);
            if(compareVal < 0){
                low = mid+1;
            }else if(compareVal > 0){
                high = mid-1;
            }else {
                return mid;
            }
        }
        return low;
    }
    public int binarySearch(String value){
        int low = 0;
        int high = data.size()-1;

        while(low <= high){
            int mid = (low+high)/2;
            String midVal = data.get(mid);
            int compareVal = midVal.compareTo(value);
            if(compareVal < 0){
                low = mid+1;
            }else if(compareVal > 0){
                high = mid-1;
            }else  {
                return mid;
            }
        }
        return -(low + 1);
    }
    public String toString(){
        return data.toString();
    }
    public ArrayList<String> getList(){
        return data;
    }
}
