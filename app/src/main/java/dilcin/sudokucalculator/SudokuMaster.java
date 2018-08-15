package dilcin.sudokucalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilcin on 28. 5. 2018.
 */

public class SudokuMaster {
    /*int sudokuField[][] = { {0,0,3,1,0,8,4,0,0},
                            {0,1,0,3,4,6,0,5,0},
                            {0,0,9,0,0,0,1,0,0},
                            {0,0,4,6,5,1,8,0,0},
                            {1,3,0,0,0,0,0,6,4},
                            {0,0,5,4,2,3,9,0,0},
                            {0,0,6,0,0,0,7,0,0},
                            {0,2,0,9,6,7,0,8,0},
                            {0,0,7,5,0,4,6,0,0}};*/
    /*int sudokuField[][] = { {3,0,0,0,0,0,0,0,8},
                            {0,0,8,5,1,6,9,0,0},
                            {0,9,6,0,0,0,1,7,0},
                            {0,1,0,0,6,0,0,8,0},
                            {0,7,0,1,2,9,0,3,0},
                            {0,4,0,0,3,0,0,9,0},
                            {0,6,9,0,0,0,7,1,0},
                            {0,0,4,6,7,2,8,0,0},
                            {5,0,0,0,0,0,0,0,2}};*/
  /*  int sudokuField[][] = { {3,0,0,0,0,0,0,0,8},
                            {0,0,8,5,1,6,9,0,3},
                            {0,9,6,0,0,0,1,7,5},
                            {0,1,0,0,6,0,0,8,7},
                            {0,7,0,1,2,9,0,3,0},
                            {0,4,0,0,3,0,0,9,1},
                            {0,6,9,0,0,0,7,1,0},
                            {0,0,4,6,7,2,8,0,9},
                            {5,0,0,0,0,0,0,0,2}};*/

    /*
    * int sudokuField[][] = { {0,5,0,0,2,0,0,7,0},
                            {2,0,7,0,4,0,6,0,1},
                            {0,8,0,0,0,0,0,4,0},
                            {1,0,0,3,0,2,0,0,4},
                            {0,7,0,0,0,0,0,2,0},
                            {8,2,3,7,4,9,6,0,5},
                            {0,2,0,0,0,0,0,8,0},
                            {7,0,3,0,8,0,2,0,6},
                            {0,1,0,0,3,0,0,5,0},};
                            */
    int sudokuField[][] = {
            {0,0,6,0,0,0,7,0,0},
            {0,0,0,6,0,3,0,0,0},
            {1,4,0,0,0,0,0,2,6},
            {4,6,0,0,5,0,0,7,8},
            {0,0,1,7,0,8,9,0,0},
            {8,7,0,0,2,0,0,6,3},
            {3,9,0,0,0,0,0,5,4},
            {0,0,0,5,0,6,0,0,0},
            {0,0,8,0,0,0,3,0,0},};
    ListUsedNums usedNums = new ListUsedNums();;
    // int[][] sudokuField = new int[9][9];
    ListUsedNums getUsedNums()
    {
        return usedNums;
    }
    List<Integer> getFreeNumsFromBlock(int line,int column)//riadok,stlpec alebo vyska sirka
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
    List<Integer> getUsedNumsFromBlock(int line,int column)//riadok,stlpec alebo vyska sirka
    {
        List<Integer> temp = new ArrayList<Integer>();
        for(int i = line*3;i < (line*3)+3;i++)
        {
            for(int j = column*3;j < (column*3)+3;j++)
            {
                if(sudokuField[i][j] != 0)
                {
                    temp.add(sudokuField[i][j]);
                }
            }
        }
        while(isSudokuFinished())
        {

        }
        return temp;
    }
    List<List<Integer>> getListUsedNumsInLines()
    {
        List<List<Integer>> temp = new ArrayList<List<Integer>>();
        for(int i = 0;i < 9;i++)
        {temp.add(new ArrayList<Integer>());
            for(int j = 0;j < 9;j++)
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
    List<Integer> getListFreeNumsInLine(int line)
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
    ListUsedNums getAddedNums()
    {
        return usedNums;
    }
    List<List<Integer>> getListFreeNumsInLines()
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
    List<List<Integer>> getListUsedNumsInColumns()
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
    List<List<Integer>> getListFreeNumsInColumns()
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
    List<Integer> getListFreeNumsInColumn(int column)
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
    List<Integer> getListUsedNumsInColumn(int j)
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
    List<Integer> getListUsedNumsInLine(int j)
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
    List<Integer>  getBlockforGuessingNumbers() //if it returns 2 nums greater than zero it means it found box
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
    void printUnasignedNumsFromBlocks()//riadok,stlpec alebo vyska sirka
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
    void printUnasignedNumsFromBlock(int line,int col)
    {
        List<Integer> list = getFreeNumsFromBlock(line,col);
        for(int i = 0;i < list.size();i++)
        {
            System.out.print(list.get(i));
        }
        System.out.println();
    }
    void printListFreeNumsInColumns()
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
    void printListFreeNumsInLines()
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
    void printListUsedNumsInColumn(int j)
    {
        List<Integer> temp = getListUsedNumsInColumn(j);
        for(int i = 0;i < temp.size();i++)
        {
            System.out.print(temp.get(i));
        }
    }
    void printListUsedNumsInLine(int j)
    {
        List<Integer> temp = getListUsedNumsInLine(j);
        for(int i = 0;i < temp.size();i++)
        {
            System.out.print(temp.get(i));
        }
    }
    void printListUsedNumsInColumns()
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
    int [][] calculate()
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
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    sudokuBlockCalculator(i, j);

                }
            }
            for(int i = 0;i<9;i++)
            {
                addNumbersToLine(i);
                addNumbersToColumn(i);
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
    int [][] calculate(int input[][])
    {
        usedNums.clear();
        sudokuField = input;
        int[][] tempSudokuField = new int[9][9];
        boolean fieldChanges = true;
        while(fieldChanges)
        {
            fieldChanges = false;
            for (int i = 0; i < 9; i++)
            {
                for(int j = 0;j < 9;j++)
                {
                    tempSudokuField [i][j] = sudokuField[i][j];
                }
            }
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    sudokuBlockCalculator(i, j);
                }
            }
            for(int i = 0;i<9;i++)
            {
                addNumbersToLine(i);
                addNumbersToColumn(i);
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
            if(isSudokuFinished())
            {
                return sudokuField;
            }
        }
         calculate();
        return sudokuField;
    }
    void guessNums()
    {
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

            if(list.get(0) <= 0 && list.get(1) >= 0)//if column has been selected as guesing place
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

            if(list.get(0) >= 0 && list.get(1) <= 0)//if line has been selected as guesing place
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
                                usedNums.removeNumsFromList(temptempUsedNumsSize);
                            }
                        }
                    }
                }
            }
        }
    }
    boolean isNumUsedInLine(int num,int line)
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
    boolean isNumUsedInColumn(int num,int column)
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
    void sudokuBlockCalculator(int line,int column)
    {

        List<Integer> nums = getFreeNumsFromBlock(line,column);
        List<List<Integer>> usedNumsInLines = getListUsedNumsInLines();
        List<List<Integer>> usedNumsInColumns = getListUsedNumsInColumns();
        for(int i = line*3;i < (line*3)+3;i++)//cicle for getting rid of unnecsesary numbers for testingin next stage
        {
            for(int j = 0;j < usedNumsInLines.get(i).size();j++)
            { boolean numFound = false;
                for(int k = 0;k < nums.size();k++)
                {
                    if(usedNumsInLines.get(i).get(j)==nums.get(k))
                    {
                        numFound = true;
                        k=100;
                    }
                }
                if (!numFound)
                {
                    usedNumsInLines.get(i).remove(j);
                    j--;
                }
            }
        }
        for(int i = column*3;i < (column*3)+3;i++)//cicle for getting rid of unnecsesary numbers for testingin next stage
        {
            for(int j = 0;j < usedNumsInColumns.get(i).size();j++)
            { boolean numFound = false;
                for(int k = 0;k < nums.size();k++)
                {
                    if(usedNumsInColumns.get(i).get(j)==nums.get(k))
                    {
                        numFound = true;
                        k=100;
                    }
                }
                if (!numFound)
                {
                    usedNumsInColumns.get(i).remove(j);
                    j--;
                }
            }
        }
        for(int i = 0;i < nums.size();i++)//changes between numbers that can be tested
        {
            for(int j = line*3;j < (line*3)+3;j++)
            {
                for(int k = 0;k < usedNumsInLines.get(j).size();k++)
                {
                    if (nums.get(i) == usedNumsInLines.get(j).get(k))
                    {
                        for(int l = column*3;l < (column*3)+3;l++)
                        {
                            if (sudokuField[j][l]==0)
                            {
                                sudokuField[j][l]=-1;
                            }
                        }
                    }
                }
            }

            for(int j = column*3;j < (column*3)+3;j++)
            {
                for(int k = 0;k < usedNumsInColumns.get(j).size();k++)
                {
                    if (nums.get(i) == usedNumsInColumns.get(j).get(k))
                    {
                        for(int l = line*3;l < (line*3)+3;l++)
                        {
                            if (sudokuField[l][j]==0)
                            {
                                sudokuField[l][j]=-1;
                            }
                        }
                    }
                }
            }

            int zeroCounter = 0;
            int memLine=0, memColumn=0;
            for(int j = line*3;j < (line*3)+3;j++)
            {
                for(int k = column*3;k < (column*3)+3;k++)
                {
                    if (sudokuField[j][k]==0)
                    {
                        memLine = j;
                        memColumn = k;
                        zeroCounter++;
                    }
                    if (sudokuField[j][k]==-1)
                    {
                        sudokuField[j][k]=0;
                    }
                }
            }
            if(zeroCounter == 1)
            {
                sudokuField[memLine][memColumn]= nums.get(i);
                usedNums.addNumToList(nums.get(i),memLine,memColumn);
            }
        }
        if(mischiefFound())
        {
            //System.out.println("This mischief has been caused by function sudokuBlockCalculator()");
        }
    }
    void addNumbersToLine(int line)// adds missing numbers to sudoku lines,line by line
    {
        List<Integer> freeNumsInLine = getListFreeNumsInLine(line);
        if(freeNumsInLine.size() == 1)
        {
            if(isNumUsedInLine(freeNumsInLine.get(0),line))
            {
                for(int col = 0;col < 9;col++)
                {
                    if(sudokuField[line][col] == 0)
                    {
                        sudokuField[line][col] = freeNumsInLine.get(freeNumsInLine.size()-1);
                        usedNums.addNumToList(freeNumsInLine.get(freeNumsInLine.size()-1),line,col);
                        col = 9;
                    }
                }
            }
        }
        else
        {
            for (int j = 0; j < freeNumsInLine.size(); j++)
            {
                for (int col = 0; col < 9; col++)
                {
                    if (sudokuField[line][col] == 0)
                    {

                        List<Integer> usedNumsInBlock = getUsedNumsFromBlock(line/3,col/3);
                        boolean numFonud = false;
                        for (int k = 0; k < usedNumsInBlock.size(); k++)
                        {
                            if (usedNumsInBlock.get(k) == freeNumsInLine.get(j))
                            {
                                numFonud = true;
                                k = 100;
                            }

                        }
                        if(!numFonud)
                        {
                            List<Integer> usedNumsInColumn = getListUsedNumsInColumn(col);
                            numFonud = false;
                            for (int k = 0; k < usedNumsInColumn.size(); k++)
                            {
                                if (usedNumsInColumn.get(k) == freeNumsInLine.get(j))
                                {
                                    numFonud = true;
                                    k = 100;
                                }
                            }
                        }
                        if (numFonud)
                        {
                            sudokuField[line][col] = -1;
                        }
                    }
                }
                if (zeroCounterInLine(line) == 1)
                {
                    for (int col = 0; col < 9; col++)
                    {
                        if (sudokuField[line][col] == 0)
                        {
                            sudokuField[line][col] = freeNumsInLine.get(j);
                            usedNums.addNumToList(freeNumsInLine.get(j),line,col);
                            col = 9;
                        }
                    }
                }
                negativeToZero();
            }
        }
        if(mischiefFound())
        {
            //System.out.println("This mischief has been caused by function addNumbersToLine()");
        }
    }
    void addNumbersToColumn(int col)// adds missing numbers to sudoku columns, column by column
    {
        List<Integer> freeNumsInColumn = getListFreeNumsInColumn(col);
        if(freeNumsInColumn.size() == 1)
        {
            for(int line = 0;line < 9;line++)
            {
                if(sudokuField[line][col] == 0)
                {
                    sudokuField[line][col] = freeNumsInColumn.get(freeNumsInColumn.size()-1);
                    usedNums.addNumToList(freeNumsInColumn.get(freeNumsInColumn.size()-1),line,col);
                    line = 9;
                }
            }
        }
        else if (freeNumsInColumn.size() <=3)
        {
            for (int j = 0; j < freeNumsInColumn.size(); j++)
            {
                for (int line = 0; line < 9; line++)
                {
                    if (sudokuField[line][col] == 0)
                    {

                        List<Integer> usedNumsInBlock = getUsedNumsFromBlock(line/3,col/3);
                        boolean numFonud = false;
                        for (int k = 0; k < usedNumsInBlock.size(); k++)
                        {
                            if (usedNumsInBlock.get(k) == freeNumsInColumn.get(j))
                            {
                                numFonud = true;
                                k = 100;
                            }
                        }
                        if(!numFonud)
                        {
                            List<Integer> usedNumsInLine = getListUsedNumsInLine(line);
                            numFonud = false;
                            for (int k = 0; k < usedNumsInLine.size(); k++)
                            {
                                if (usedNumsInLine.get(k) == freeNumsInColumn.get(j))
                                {
                                    numFonud = true;
                                }
                            }
                        }
                        if (numFonud)
                        {
                            sudokuField[line][col] = -1;
                        }
                    }
                }

                if (zeroCounterInColumn(col) == 1)
                {
                    for (int line = 0; line < 9; line++)
                    {
                        if (sudokuField[line][col] == 0)
                        {
                            sudokuField[line][col] = freeNumsInColumn.get(j);
                            usedNums.addNumToList(freeNumsInColumn.get(j),line,col);
                            line = 9;
                        }
                    }
                }
                negativeToZero();
            }
        }
        if(mischiefFound())
        {
            /*System.out.println("This mischief has been caused by function addNumbersToColumn() in column :" + col);
            printOutSudokuField();*/

        }
    }
    int zeroCounterInColumn(int column)
    {
        int zeroCounter = 0;
        for(int j = 0;j < 9;j++)
        {
            if(sudokuField[j][column] == 0)
            {
                zeroCounter++;
            }
        }
        return zeroCounter;
    }
    int zeroCounterInLine(int line )
    {
        int zeroCounter = 0;
        for(int j = 0;j < 9;j++)
        {
            if(sudokuField[line][j] == 0)
            {
                zeroCounter++;
            }
        }
        return zeroCounter;
    }
    boolean mischiefFoundInBlock(int line,int column)
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
    boolean mischiefFound()
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
    void negativeToZero()
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
    void printOutSudokuField()
    {
        printOutSudokuField(sudokuField);
    }
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
    boolean isSudokuFinished()
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
        System.out.println("Sudoku has been solved");
        return true;
    }
}
