package dilcin.sudokucalculator;

import java.util.ArrayList;
import java.util.List;

public class GuessPyramid {
    List<List<int[][]>> fieldPyramid = new ArrayList<>();

    void addNewField(int [][] field,int level)
    {
        System.out.println("adddddddded");
        if(level >= fieldPyramid.size())
        {
            fieldPyramid.add(new ArrayList<int[][]>());
        }
        fieldPyramid.get(level-1).add(field);
    }
    List<List<int[][]>> getFieldPyramid ()
    {
        return fieldPyramid;
    }
}
