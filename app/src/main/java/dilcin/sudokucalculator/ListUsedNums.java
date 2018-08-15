package dilcin.sudokucalculator;

import java.util.ArrayList;
import java.util.List;

public class ListUsedNums {
    private List<Integer> addedNums = new ArrayList<Integer>();
    private List<Integer> addedNumsLine = new ArrayList<Integer>();
    private List<Integer> addedNumsColumn = new ArrayList<Integer>();
    void addNumToList(int num, int line, int col)
    {
        addedNums.add(num);
        addedNumsLine.add(line);
        addedNumsColumn.add(col);
    }
    void removeNumsFromList(int desiredSize)
    {
        for(int i = addedNums.size()-1;i >= desiredSize; i--)
        {
            addedNums.remove(i);
            addedNumsLine.remove(i);
            addedNumsColumn.remove(i);
        }
    }
    public void clear()
    {
        addedNums.clear();
        addedNumsLine.clear();
        addedNumsColumn.clear();
    }
    public List<Integer> getAddedNums() {
        return addedNums;
    }
    public int getAddedNum(int i) {
        return addedNums.get(i);
    }
    public List<Integer> getAddedNumsLine() {
        return addedNumsLine;
    }
    public int getAddedNumLine(int i) {
        return addedNumsLine.get(i);
    }
    public List<Integer> getAddedNumsColumn() {
        return addedNumsColumn;
    }
    public int getAddedNumColumn(int i) {
        return addedNumsColumn.get(i);
    }
    public int getSize() {
        return addedNums.size();
    }

}
