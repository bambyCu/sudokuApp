package dilcin.sudokucalculator;

import java.util.ArrayList;
import java.util.List;

public class Database {
    List<int[][]> sudokuFields = new ArrayList<int[][]>();
    int[][][] storage={
    {
        {1,0,3,0,0,0,6,0,7},//0
        {0,0,2,0,5,0,3,0,0},
        {9,6,7,0,3,0,4,5,2},
        {0,0,0,5,0,9,0,0,0},
        {0,3,8,0,0,0,9,7,0},
        {0,0,0,2,0,3,0,0,0},
        {5,2,1,0,4,0,7,9,6},
        {0,0,9,0,6,0,1,0,0},
        {8,0,6,0,0,0,5,0,4}},
    {
        {0,5,0,0,2,0,0,7,0},//1
        {2,0,7,0,4,0,6,0,1},
        {0,8,0,0,0,0,0,4,0},
        {1,0,0,3,0,2,0,0,4},
        {0,7,0,0,0,0,0,2,0},
        {4,0,0,7,0,9,0,0,5},
        {0,2,0,0,0,0,0,8,0},
        {7,0,3,0,8,0,2,0,6},
        {0,1,0,0,3,0,0,5,0},},
    {                       //2
        {0,0,2,0,4,0,5,0,0},
        {8,0,0,6,0,5,0,0,9},
        {7,0,0,3,0,8,0,0,6},
        {0,6,0,0,0,0,0,7,0},
        {0,0,0,4,0,2,0,0,0},
        {0,2,0,0,0,0,0,5,0},
        {2,0,0,1,0,4,0,0,7},
        {5,0,0,9,0,3,0,0,8},
        {0,0,1,0,8,0,3,0,0},},
    {
         {8,0,0,0,0,0,0,0,0},
         {0,0,3,6,0,0,0,0,0},
         {0,7,0,0,9,0,2,0,0},
         {0,5,0,0,0,7,0,0,0},
         {0,0,0,0,4,5,7,0,0},
         {0,0,0,1,0,0,0,3,0},
         {0,0,1,0,0,0,0,6,8},
         {0,0,8,5,0,0,0,1,0},
         {0,9,0,0,0,0,4,0,0},},
        // hardest sudoku ever
    {
        {7,0,0,0,1,0,0,0,0},
        {0,0,0,0,0,0,0,4,0},
        {2,9,0,0,0,0,0,5,7},
        {0,0,9,8,0,6,1,0,0},
        {6,0,0,0,0,0,0,0,0},
        {0,2,8,0,4,0,0,0,0},
        {0,3,0,7,0,0,0,0,8},
        {0,8,2,0,5,0,0,0,0},
        {0,0,0,1,2,0,3,0,0},},//very very ha*/
    {
        {2,3,5,0,0,0,0,9,0},
        {0,6,0,9,7,0,0,2,0},
        {0,4,0,0,0,0,1,0,0},
        {0,7,0,1,0,0,6,0,0},
        {0,0,0,0,9,0,0,0,0},
        {0,0,6,0,0,4,0,8,0},
        {0,0,9,0,0,0,0,7,0},
        {0,2,0,0,3,6,0,4,0},
        {0,1,0,0,0,0,2,6,5},},
    {
        {0,0,8,0,0,0,2,0,0},
        {7,0,0,2,0,8,0,0,5},
        {0,6,0,0,7,0,0,9,0},
        {8,3,0,0,0,0,0,2,6},
        {0,7,1,0,5,0,4,8,0},
        {0,0,4,0,3,0,1,0,0},
        {0,8,0,9,0,6,0,3,0},
        {1,0,0,0,0,0,0,0,2},
        {0,0,0,5,1,2,0,0,0},},
    {
        {5,3,0,0,7,0,0,0,0},
        {6,0,0,0,0,0,0,0,0},
        {0,9,8,1,9,5,0,6,0},
        {8,0,0,0,6,0,0,0,3},
        {4,0,0,8,0,3,0,0,1},
        {7,0,0,0,2,0,0,0,6},
        {0,6,0,0,0,0,2,8,0},
        {0,0,0,4,1,9,0,0,5},
        {0,0,0,0,8,0,0,7,9},},
    {
        {0,5,0,0,2,0,0,7,0},
        {2,0,7,0,4,0,6,0,1},
        {0,8,0,0,0,0,0,4,0},
        {1,0,0,3,0,2,0,0,4},
        {0,7,0,0,0,0,0,2,0},
        {8,2,3,7,4,9,6,0,5},
        {0,2,0,0,0,0,0,8,0},
        {7,0,3,0,8,0,2,0,6},
        {0,1,0,0,3,0,0,5,0},},
    {
        {3,0,0,0,0,0,0,0,8},
        {0,0,8,5,1,6,9,0,3},
        {0,9,6,0,0,0,1,7,5},
        {0,1,0,0,6,0,0,8,7},
        {0,7,0,1,2,9,0,3,0},
        {0,4,0,0,3,0,0,9,1},
        {0,6,9,0,0,0,7,1,0},
        {0,0,4,6,7,2,8,0,9},
        {5,0,0,0,0,0,0,0,2}},

    /*{
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},},
    {
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},},
    {
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},}
*/};
    Database ()
    {
        transferStorageToList();
    }
    private void transferStorageToList()
    {
        for(int i = 0; i < storage.length;i++)
        {
            sudokuFields.add(storage[i]);
        }
    }
    List <int[][]> getSudokuFields()
    {
        return sudokuFields;
    }
     /*       {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},};*/


}
