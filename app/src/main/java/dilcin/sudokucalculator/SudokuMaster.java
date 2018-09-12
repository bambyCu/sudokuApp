package dilcin.sudokucalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilcin on 28. 5. 2018.
 */

public class SudokuMaster {
    int sudokuField[][] = new int[9][9];
    ListUsedNums usedNums = new ListUsedNums();//lint of numbers added to sudokuField in order of calculation
    boolean fieldGuessed = false;//if there has been made any guess during calculation fieldGuessed will by TRUE
    GuessPyramid guessPyramid ;
    /*this variable consists of Lists of sudokuFields that have been guessed(correctli or incorrectly),it stores them in pyramid structure
    variable created mainly for optimalizing code */


    //functions for output:


    int guesLevel = 0;//used with guessPyramid
    ListUsedNums getUsedNums()
    {
        return usedNums;
    }
    List<List<int[][]>> getGuessPyramidList()
    {
        return guessPyramid.getFieldPyramid();
    }
    boolean isFieldGuessed()//if there has been made any guess during calculation fieldGuessed will return TRUE
    {
        return fieldGuessed;
    }


    // functions working with sudokuField:


    int [][] calculate(int input[][]) {
        guessPyramid = new GuessPyramid();
        boolean fieldGuessed = false;
        usedNums.clear();
        sudokuField = input;
        calculate();
        return sudokuField;
    }
    private int [][] calculate()
    {
        int[][] tempSudokuField = new int[9][9];
        boolean fieldChanges = true;
        while(fieldChanges) {
            fieldChanges = false;
            for (int i = 0; i < 9; i++)
            {
                for(int j = 0;j < 9;j++)
                {
                    tempSudokuField [i][j] = sudokuField[i][j];
                }
            }
            for (int num = 1; num <= 9; num++) {
                fillUnaviliableBoxesByNum(num);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (isBlockSuitableForNum(i, j, num)) {
                            loop:
                            for (int k = i * 3; k < (i * 3) + 3; k++) {
                                for (int l = j * 3; l < (j * 3) + 3; l++) {
                                    if (sudokuField[k][l] == 0) {
                                        sudokuField[k][l] = num;
                                        usedNums.addNumToList(num,k,l);
                                        break loop;
                                    }
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < 9; i++) {

                    if (isNumSuitableForLine(i, num)) {
                        for (int j = 0; j < 9; j++) {
                            if (sudokuField[i][j] == 0) {
                                sudokuField[i][j] = num;
                                usedNums.addNumToList(num,i,j);
                            }
                        }
                    }
                    if (isNumSuitableForColumn(i, num)) {
                        for (int j = 0; j < 9; j++) {
                            if (sudokuField[j][i] == 0) {
                                sudokuField[j][i] = num;
                                usedNums.addNumToList(num,j,i);
                            }
                        }
                    }
                }
                negativeToZero();
            }
            for (int i = 0; i < 9; i++)
            {
                for(int j = 0;j < 9;j++)
                {
                    if(tempSudokuField[i][j] != sudokuField[i][j])
                    {
                        fieldChanges = true;
                    }
                }
            }
        }
        if(isSudokuFinished())
        {
            return sudokuField;
        }
        guessNums();
        return sudokuField;
    }
    private void fillUnaviliableBoxesByNum(int num)//replaces 0 with -1 in sudokuField in places that are unusable for current num
    {
        for(int i = 0;i < 9;i++ )
        {
            if(isNumUsedInLine(num,i))
            {
                for(int j = 0;j < 9; j++)
                {
                    if(sudokuField[i][j] == 0)
                    {
                        sudokuField[i][j] = -1;
                    }
                }
            }
            if(isNumUsedInColumn(num,i))
            {
                for(int j = 0;j < 9; j++)
                {
                    if(sudokuField[j][i] == 0)
                    {
                        sudokuField[j][i] = -1;
                    }
                }
            }
        }
        for(int i = 0;i < 3;i++)
        {
            for(int j = 0;j < 3;j++)
            {
                fillUnaviliableBoxesInBlockByNum(i,j,num);
            }
        }
    }
    private void fillUnaviliableBoxesInBlockByNum(int line,int column,int num)
    {
        if(isNumUsedInBlock(line,column,num))
        {
            for (int i = line * 3; i < (line * 3) + 3; i++)
            {
                for (int j = (column * 3); j < (column * 3) + 3; j++)
                {
                    if(sudokuField[i][j] == 0)
                    {
                        sudokuField[i][j] = -1;
                    }
                }
            }
        }
    }
    void guessNums()
    {

        guesLevel++;
        fieldGuessed = true;
        int[][] backupSudokuField = new int[9][9];
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                backupSudokuField[i][j] = sudokuField[i][j];
            }
        }
        List<Integer> list = getBlockforGuessingNumbers();
        if (list.size()>0)
        {
            if(list.get(0) >= 0 && list.get(1) >= 0)
            {
                List<Integer> aviliableNums = getFreeNumsFromBlock(list.get(0), list.get(1));
                for (int i = (list.get(0) * 3); i < (list.get(0) * 3) + 3; i++)
                {
                    for (int j = (list.get(1) * 3); j < (list.get(1) * 3) + 3; j++)
                    {
                        if (sudokuField[i][j] == 0)
                        {
                            for (int k = 0; k < aviliableNums.size(); k++)
                            {
                                for (int l = 0; l < 9; l++)
                                {
                                    for (int m = 0; m < 9; m++)
                                    {
                                        sudokuField[l][m] = backupSudokuField[l][m];
                                    }
                                }
                                int tempUsedNumsSize = usedNums.getSize();
                                sudokuField[i][j] = aviliableNums.get(k);
                                usedNums.addNumToList(aviliableNums.get(k),i,j);
                                guessPyramid.addNewField(twoDimensionalArrayCopy(sudokuField),guesLevel);
                                calculate();
                                if (isSudokuFinished())
                                {
                                    break;
                                }
                                else if (mischiefFound())
                                {
                                    for (int l = 0; l < 9; l++)
                                    {
                                        for (int m = 0; m < 9; m++)
                                        {
                                            sudokuField[l][m] = backupSudokuField[l][m];
                                        }
                                    }
                                    usedNums.removeNumsFromList(tempUsedNumsSize);
                                }
                            }
                        }
                    }
                }
            }
            else if(list.get(0) <= 0 && list.get(1) >= 0)//if column has been selected as guesing place
            {
                List<Integer> aviliableNums = getListFreeNumsInColumn(list.get(1));
                for (int i = 0; i < 9; i++)
                {
                    if (sudokuField[i][list.get(1)] == 0)
                    {
                        for (int k = 0; k < aviliableNums.size(); k++)
                        {
                            for (int l = 0; l < 9; l++)
                            {
                                for (int m = 0; m < 9; m++)
                                {
                                    sudokuField[l][m] = backupSudokuField[l][m];
                                }
                            }
                            int tempUsedNumsSize = usedNums.getSize();
                            sudokuField[i][list.get(1)] = aviliableNums.get(k);
                            usedNums.addNumToList(aviliableNums.get(k),i,list.get(1));
                            guessPyramid.addNewField(twoDimensionalArrayCopy(sudokuField),guesLevel);
                            calculate();
                            if (isSudokuFinished())
                            {
                                break;
                            } else if (mischiefFound())
                            {
                                for (int l = 0; l < 9; l++)
                                {
                                    for (int m = 0; m < 9; m++)
                                    {
                                        sudokuField[l][m] = backupSudokuField[l][m];
                                    }
                                }
                                usedNums.removeNumsFromList(tempUsedNumsSize);
                            }
                        }
                    }
                }
            }

            else if(list.get(0) >= 0 && list.get(1) <= 0)//if line has been selected as guesing place
            {
                /*System.out.println(list.get(0));

                System.out.println("  ");
                System.out.println(list.get(1));
                System.out.println("22222222222222ddddddddddddddddddddddddddddddddddddsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssddddddddddddddddddd");
                */List<Integer> aviliableNums = getListFreeNumsInLine(list.get(0));
                for (int i = 0; i < 9; i++)
                {
                    if (sudokuField[list.get(0)][i] == 0)
                    {
                        for (int k = 0; k < aviliableNums.size(); k++)
                        {
                            for (int l = 0; l < 9; l++)
                            {
                                for (int m = 0; m < 9; m++)
                                {
                                    sudokuField[l][m] = backupSudokuField[l][m];
                                }
                            }
                            int temptempUsedNumsSize = usedNums.getSize();
                            sudokuField[list.get(0)][i] = aviliableNums.get(k);
                            usedNums.addNumToList(aviliableNums.get(k),list.get(0),i);
                            guessPyramid.addNewField(twoDimensionalArrayCopy(sudokuField),guesLevel);
                            calculate();
                            if (isSudokuFinished())
                            {
                                break;
                            }
                            else if (mischiefFound())
                            {
                                for (int l = 0; l < 9; l++)
                                {
                                    for (int m = 0; m < 9; m++)
                                    {
                                        sudokuField[l][m] = backupSudokuField[l][m];
                                    }
                                }
                                usedNums.removeNumsFromList(temptempUsedNumsSize);
                            }
                        }
                    }
                }
            }
        }
        guesLevel--;
    }


    //functions returning more information:


    private List<Integer> getFreeNumsFromBlock(int line,int column)//riadok,stlpec alebo vyska sirka
    {
        List<Integer> temp = new ArrayList<Integer>();
        for(int i = 1;i < 10;i++)
        {
            for(int j = line*3;j < (line*3)+3;j++)
            {
                for(int k = column*3;k < (column*3)+3;k++)
                {
                    if(i==sudokuField[j][k])
                    {
                        if(i<=9)
                        {
                            i++;
                            k=(column*3)-1;
                            j=(line*3);
                        }
                        else
                        {
                            k=100;
                            j=100;
                        }
                    }
                }
            }
            if(i<=9)
            {
                temp.add(i);
            }
        }
        return temp;
    }
    private List<Integer> getListFreeNumsInLine(int line)
    {
        List<Integer> temp = new ArrayList<>();
        for(int num = 1;num < 10;num++)
        {
            for (int i = 0; i < 9; i++)
            {
                if (num == sudokuField[line][i])
                {
                    num++;
                    i=-1;
                }
            }
            if(num < 10)
            {
                temp.add(num);
            }
        }
        return temp;
    }
    private List<List<Integer>> getListFreeNumsInLines()
    {
        List<List<Integer>> temp = new ArrayList<List<Integer>>();

        for (int j = 0; j < 9; j++)
        {
            temp.add(new ArrayList<Integer>());
            for(int num = 1;num < 10;num++)
            {
                for (int i = 0; i < 9; i++)
                {
                    if (num == sudokuField[j][i])
                    {
                        num++;
                        i=-1;
                    }
                }
                if(num < 10)
                {
                    temp.get(temp.size() - 1).add(num);
                }
            }
        }
        return temp;
    }
    private List<List<Integer>> getListUsedNumsInColumns()
    {
        List<List<Integer>> temp = new ArrayList<List<Integer>>();
        for(int j = 0;j < 9;j++)
        {temp.add(new ArrayList<Integer>());
            for(int i = 0;i < 9;i++)
            {
                if(sudokuField[i][j] != 0)
                {
                    temp.get(temp.size()-1).add(sudokuField[i][j]);
                    //  System.out.println(temp.get(temp.size()-1).get(temp.get(temp.size()-1).size()-1));

                }
            }
        }
        return temp;
    }
    private List<List<Integer>> getListFreeNumsInColumns()
    {
        List<List<Integer>> temp = new ArrayList<List<Integer>>();

        for (int j = 0; j < 9; j++)
        {
            temp.add(new ArrayList<Integer>());
            for(int num = 1;num < 10;num++)
            {
                for (int i = 0; i < 9; i++)
                {
                    if (num == sudokuField[i][j])
                    {
                        num++;
                        i=-1;
                    }
                }
                if(num < 10)
                {
                    temp.get(temp.size() - 1).add(num);
                }
            }
        }
        return temp;
    }
    private List<Integer> getListFreeNumsInColumn(int column)
    {
        List<Integer> temp = new ArrayList<>();

        for(int num = 1;num < 10;num++)
        {
            for (int i = 0; i < 9; i++)
            {
                if (num == sudokuField[i][column])
                {
                    num++;
                    i = -1;
                }
            }
            if (num < 10) {
                temp.add(num);
            }
        }

        return temp;
    }
    private List<Integer> getListUsedNumsInColumn(int j)
    {
        List<Integer> temp = new ArrayList<Integer>();
        for(int i = 0;i < 9;i++)
        {
            if(sudokuField[i][j] != 0)
            {
                temp.add(sudokuField[i][j]);
                //  System.out.println(temp.get(temp.size()-1).get(temp.get(temp.size()-1).size()-1));

            }
        }
        return temp;
    }
    private List<Integer> getListUsedNumsInLine(int j)
    {
        List<Integer> temp = new ArrayList<Integer>();
        for(int i = 0;i < 9;i++)
        {
            if(sudokuField[j][i] != 0)
            {
                temp.add(sudokuField[j][i]);
            }
        }
        return temp;
    }
    private List<Integer>  getBlockforGuessingNumbers() //if it returns 2 nums greater than zero it means it found box
                                                        //if it returns 2 nums of which first is below zero it found column
                                                        //if it returns 2 nums of which second is below zero it found line
    {
        List<Integer> temp = new ArrayList<>();
        int lowestAmounfOfFreeNums = 9;
        for(int i = 0;i < 3;i++)
        {
            for(int j = 0;j < 3;j++)
            {
                List<Integer> list = getFreeNumsFromBlock(i,j);
                if(list.size()==2)
                {
                    temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    return temp;
                }
                else if(list.size() < lowestAmounfOfFreeNums && list.size() > 2)
                {
                    temp = new ArrayList<>();
                    lowestAmounfOfFreeNums = list.size();
                    temp.add(i);
                    temp.add(j);
                }
            }
        }
        for(int i = 0;i < 9;i++)
        {
            List<Integer> list = getListFreeNumsInColumn(i);
            if(list.size()==2)
            {
                temp = new ArrayList<>();
                temp.add(-1);
                temp.add(i);
                return temp;
            }
            else if(list.size() < lowestAmounfOfFreeNums && list.size() > 2)
            {
                temp = new ArrayList<>();
                lowestAmounfOfFreeNums = list.size();
                temp.add(-1);
                temp.add(i);
            }
        }
        for(int i = 0;i < 9;i++)
        {
            List<Integer> list = getListFreeNumsInLine(i);
            if(list.size()==2)
            {
                temp = new ArrayList<>();
                temp.add(i);
                temp.add(-1);
                return temp;
            }
            else if(list.size() < lowestAmounfOfFreeNums && list.size() > 2)
            {
                temp = new ArrayList<>();
                lowestAmounfOfFreeNums = list.size();
                temp.add(i);
                temp.add(-1);
            }
        }
        return temp;
    }


    //proceduraly simple functions:
    //is suitable functionf return true if choosen number can be added to block
    private boolean isBlockSuitableForNum(int line ,int column,int num)
    {
        int zeroCounter = 0;
        for(int i = line*3;i < (line*3)+3;i++)
        {
            for(int j = column*3;j < (column*3)+3;j++)
            {
                if(sudokuField[i][j] == 0)
                {
                    zeroCounter++;
                    if(zeroCounter>1)
                    {
                        return false;
                    }
                }
                if(sudokuField[i][j] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isNumSuitableForLine(int line,int num)
    {
        int zeroCounter = 0;
        for(int column = 0;column < 9;column++)
        {

            if(sudokuField[line][column] == 0)
            {
                zeroCounter++;
                if(zeroCounter>1)
                {
                    return false;
                }
            }
            if(sudokuField[line][column] == num)
            {
                return false;
            }
        }
        return true;
    }
    private boolean isNumSuitableForColumn(int column,int num)
    {
        int zeroCounter = 0;
        for(int line = 0;line < 9;line++)
        {

            if(sudokuField[line][column] == 0)
            {
                zeroCounter++;
                if(zeroCounter>1)
                {
                    return false;
                }
            }
            if(sudokuField[line][column] == num)
            {
                return false;
            }
        }
        return true;
    }
    private boolean mischiefFound() // mischief happens when rules of sudoku are broken
    {
        for(int i = 0;i < 9;i++)
        {
            for(int num = 1;num < 10;num++)
            {
                int amountOfNum = 0;

                for(int j = 0;j < 9;j++)
                {
                    if(num == sudokuField[i][j])
                    {
                        amountOfNum++;
                    }
                }
                if(amountOfNum>1)
                {
                    return true;
                }
            }
        }
        for(int i = 0;i < 9;i++)
        {
            for(int num = 1;num < 10;num++)
            {
                int amountOfNum = 0;

                for(int j = 0;j < 9;j++)
                {
                    if(num == sudokuField[j][i])
                    {
                        amountOfNum++;
                    }
                }
                if(amountOfNum>1)
                {
                    return true;
                }
            }
        }
        for(int i = 0;i < 3;i++)
        {
            for(int j = 0;j < 3;j++)
            {
                if(mischiefFoundInBlock(i,j))
                {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean mischiefFoundInBlock(int line,int column)
    {
        for(int num = 1;num < 10;num++)
        {
            int amountOfNum = 0;
            for(int i = 0;i < 3;i++)
            {
                for(int j = 0;j < 3;j++)
                {
                    if(num == sudokuField[(line * 3) +i][(column*3)+j])
                    {
                        amountOfNum++;
                    }
                }
            }
            if(amountOfNum>1)
            {
                return true;
            }
        }
        return false;
    }
    private void negativeToZero()
    {
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                if(sudokuField[i][j]<0)
                {
                    sudokuField[i][j]=0;
                }
            }
        }
    }
    private boolean isSudokuFinished()
    {
        if(mischiefFound())
        {
            return false;
        }
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                if(sudokuField[i][j] == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isNumUsedInLine(int num,int line)
    {
        List<Integer> usedNumsInLine = getListUsedNumsInLine(line);
        for(int i = 0;i < usedNumsInLine.size();i++)
        {
            if(num == usedNumsInLine.get(i))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isNumUsedInColumn(int num,int column)
    {
        List<Integer> usedNumsInColumn = getListUsedNumsInColumn(column);
        for(int i = 0;i < usedNumsInColumn.size();i++)
        {
            if(num == usedNumsInColumn.get(i))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isNumUsedInBlock(int line,int column,int num)
    {
        for(int i = line*3;i < (line*3)+3;i++)
        {
            for(int j = (column*3);j < (column*3)+3;j++)
            {
                if(sudokuField[i][j] == num)
                {
                    return true;
                }
            }
        }
        return false;
    }
    private int[][] twoDimensionalArrayCopy(int[][] input) {
        if (input == null)
        {
            return null;
        }
        int[][] temp = new int[input.length][];
        for (int i = 0; i < input.length; i++)
        {
            temp[i] = input[i].clone();
        }
        return temp;
    }



    //debug tools:


    void printOutSudokuField(int inputSudokuField[][])
    {
        for(int i = 0;i < 9;i++)
        {
            if(i == 0)
            {
                for(int j =0;j<13;j++)
                {
                    System.out.print("-");
                }
                System.out.println();
            }
            for(int j = 0;j < 9;j++)
            {
                if(j == 0)
                {
                    System.out.print("|");
                }
                if(sudokuField[i][j]==-1)
                {
                    System.out.print("+");
                }
                else
                {
                    System.out.print(inputSudokuField[i][j]);
                }
                if((j+1) % 3 == 0)
                {
                    System.out.print("|");
                }

            }
            System.out.println();
            if((i+1) % 3 == 0 )
            {
                for(int j =0;j<13;j++)
                {
                    System.out.print("-");
                }
                System.out.println();
            }
        }

    }
    private void printUnasignedNumsFromBlocks()//riadok,stlpec alebo vyska sirka
    {
        int[][] temp = new int[9][9];
        System.out.println("Unasigned numbers in boxes :");
        for(int line = 0;line <3;line++)
        {
            for(int col = 0;col<3;col++)
            {
                List<Integer> square = getFreeNumsFromBlock(line,col);
                int numLine = 0;
                for(int i = line*3;i<(line*3)+3;i++)
                {
                    for(int j = col*3;j<(col*3)+3;j++)
                    {
                        if(square.size()>numLine)
                        {
                            temp[i][j] = square.get(numLine);
                            numLine++;
                        }
                        else
                        {
                            temp[i][j] = 0;
                        }
                    }
                }
            }
        }
        printOutSudokuField(temp);
        System.out.println("end of numbers used in boxes");
    }
    private void printUnasignedNumsFromBlock(int line,int col)
    {
        List<Integer> list = getFreeNumsFromBlock(line,col);
        for(int i = 0;i < list.size();i++)
        {
            System.out.print(list.get(i));
        }
        System.out.println();
    }
    private void printListFreeNumsInColumns()
    {
        List<List<Integer>> temp = getListFreeNumsInColumns();
        for(int i =0;i < temp.size();i++)
        {
            for(int j = 0;j<temp.get(i).size();j++)
            {
                System.out.print(temp.get(i).get(j));
            }
            System.out.println();
        }
    }
    private void printListFreeNumsInLines()
    {
        List<List<Integer>> temp = getListFreeNumsInLines();
        for(int i =0;i < temp.size();i++)
        {
            for(int j = 0;j<temp.get(i).size();j++)
            {
                System.out.print(temp.get(i).get(j));
            }
            System.out.println();
        }
    }
    private void printListUsedNumsInColumn(int j)
    {
        List<Integer> temp = getListUsedNumsInColumn(j);
        for(int i = 0;i < temp.size();i++)
        {
            System.out.print(temp.get(i));
        }
    }
    private void printListUsedNumsInLine(int j)
    {
        List<Integer> temp = getListUsedNumsInLine(j);
        for(int i = 0;i < temp.size();i++)
        {
            System.out.print(temp.get(i));
        }
    }
    private void printListUsedNumsInColumns()
    {
        List<List<Integer>> temp = getListUsedNumsInColumns();
        for(int i =0;i < temp.size();i++)
        {
            for(int j = 0;j<temp.get(i).size();j++)
            {
                System.out.print(temp.get(i).get(j));
            }
            System.out.println();
        }
    }
    void printGuessPyramidList()
    {
        for(int i = 0;i < guessPyramid.getFieldPyramid().size();i++)
        {
            for(int j = 0; j < guessPyramid.getFieldPyramid().get(i).size();j++)
            {
                System.out.println("level of deep :" + i);
                printOutSudokuField(guessPyramid.getFieldPyramid().get(i).get(j));
            }
        }
    }
    void printOutSudokuField()
    {
        printOutSudokuField(sudokuField);
    }
}
