package dilcin.sudokucalculator;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import static dilcin.sudokucalculator.R.string.allStep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*SudokuMaster sudokuMaster = new SudokuMaster();
        List<int[][]> data = new Database().getSudokuFields();
        sudokuMaster.calculate(data.get(3));
        sudokuMaster.printOutSudokuField();
        sudokuMaster.printGuessPyramidList();*/
        workWithDatabase();
}


void workWithDatabase() {
    SudokuMaster sudokuMaster = new SudokuMaster();
    List<int[][]> data = new Database().getSudokuFields();
    List<Boolean> difficulty = new ArrayList<Boolean>();

    List<Long> acomplishedTime = new ArrayList<Long>();
    System.out.println("datasize is " + data.size());
    for (int i = 0; i < data.size(); i++) {
        long tempTime = SystemClock.currentThreadTimeMillis();
        sudokuMaster.calculate(data.get(i));

        tempTime = SystemClock.currentThreadTimeMillis() - tempTime;
        difficulty.add(sudokuMaster.isFieldGuessed());

        acomplishedTime.add(tempTime);

        System.out.println("computing time of sudoku number :" + i);

        System.out.println(tempTime);
    }
    System.out.println("times : ");
    System.out.println("datasize is " + data.size());
    for (int i = 0; i < acomplishedTime.size(); i++) {
        if (difficulty.get(i)) {
            System.out.println(acomplishedTime.get(i) + ", was guessed, HARD");
        } else if (!difficulty.get(i)) {
            System.out.println(acomplishedTime.get(i) + ", was not guessed, EASY");
        }

    }
}
}
/*public class MainActivity extends AppCompatActivity {
    SudokuMaster sudokuMaster;
    List<EditText> boxes;


    /*int sudokuField[][] = { {3,0,0,0,0,0,0,0,8},
            {0,0,8,5,1,6,9,0,3},
            {0,9,6,0,0,0,1,7,5},
            {0,1,0,0,6,0,0,8,7},
            {0,7,0,1,2,9,0,3,0},
            {0,4,0,0,3,0,0,9,1},
            {0,6,9,0,0,0,7,1,0},
            {0,0,4,6,7,2,8,0,9},
            {5,0,0,0,0,0,0,0,2}};*/
    //int step = 0;
    /*int sudokuField[][] = { {1,0,3,0,0,0,6,0,7},
                            {0,0,2,0,5,0,3,0,0},
                            {9,6,7,0,3,0,4,5,2},
                            {0,0,0,5,0,9,0,0,0},
                            {0,3,8,0,0,0,9,7,0},
                            {0,0,0,2,0,3,0,0,0},
                            {5,2,1,0,4,0,7,9,6},
                            {0,0,9,0,6,0,1,0,0},
                            {8,0,6,0,0,0,5,0,4}};*/

    /*int sudokuField[][] = { {0,5,0,0,2,0,0,7,0},
            {2,0,7,0,4,0,6,0,1},
            {0,8,0,0,0,0,0,4,0},
            {1,0,0,3,0,2,0,0,4},
            {0,7,0,0,0,0,0,2,0},
            {4,0,0,7,0,9,0,0,5},
            {0,2,0,0,0,0,0,8,0},
            {7,0,3,0,8,0,2,0,6},
            {0,1,0,0,3,0,0,5,0},};*/
    /*
    * int sudokuField[][] = {
            {0,0,2,0,4,0,5,0,0},
            {8,0,0,6,0,5,0,0,9},
            {7,0,0,3,0,8,0,0,6},
            {0,6,0,0,0,0,0,7,0},
            {0,0,0,4,0,2,0,0,0},
            {0,2,0,0,0,0,0,5,0},
            {2,0,0,1,0,4,0,0,7},
            {5,0,0,9,0,3,0,0,8},
            {0,0,1,0,8,0,3,0,0},};*/
    /*int sudokuField[][] = {
            {8,0,0,0,0,0,0,0,0},
            {0,0,3,6,0,0,0,0,0},
            {0,7,0,0,9,0,2,0,0},
            {0,5,0,0,0,7,0,0,0},
            {0,0,0,0,4,5,7,0,0},
            {0,0,0,1,0,0,0,3,0},
            {0,0,1,0,0,0,0,6,8},
            {0,0,8,5,0,0,0,1,0},
            {0,9,0,0,0,0,4,0,0},};
             hardest sudoku ever*/
    /*int sudokuField[][] = {
            {7,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,0,4,0},
            {2,9,0,0,0,0,0,5,7},
            {0,0,9,8,0,6,1,0,0},
            {6,0,0,0,0,0,0,0,0},
            {0,2,8,0,4,0,0,0,0},
            {0,3,0,7,0,0,0,0,8},
            {0,8,2,0,5,0,0,0,0},
            {0,0,0,1,2,0,3,0,0},}; very very hard*/
    /*int sudokuField[][] = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},};*/
    /*int sudokuField[][] = {
            {2,3,5,0,0,0,0,9,0},
            {0,6,0,9,7,0,0,2,0},
            {0,4,0,0,0,0,1,0,0},
            {0,7,0,1,0,0,6,0,0},
            {0,0,0,0,9,0,0,0,0},
            {0,0,6,0,0,4,0,8,0},
            {0,0,9,0,0,0,0,7,0},
            {0,2,0,0,3,6,0,4,0},
            {0,1,0,0,0,0,2,6,5},};*/
    /*int sudokuField[][] = {
            {0,0,8,0,0,0,2,0,0},
            {7,0,0,2,0,8,0,0,5},
            {0,6,0,0,7,0,0,9,0},
            {8,3,0,0,0,0,0,2,6},
            {0,7,1,0,5,0,4,8,0},
            {0,0,4,0,3,0,1,0,0},
            {0,8,0,9,0,6,0,3,0},
            {1,0,0,0,0,0,0,0,2},
            {0,0,0,5,1,2,0,0,0},};
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boxes = new ArrayList<EditText>();
        sudokuMaster = new SudokuMaster();
        Text avaliableMoves;
        final RelativeLayout display = findViewById(R.id.Display);
        final GridLayout grid = findViewById(R.id.Grid);
        final Button calculateButton = findViewById(R.id.calculate);
        final Button StepForvard = findViewById(R.id.stepForward);
        final Button StepBackward = findViewById(R.id.stepBackward);
        final TextView currentStepView = findViewById(R.id.textView);
        final TextView allStepView = findViewById(R.id.textView1);
        final EditText countText = findViewById(R.id.editText);
        ImageButton countButton = findViewById(R.id.imageButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //printFieldOfSudoku();
                step = 0;

                calculateAndSetOutput(getFieldOfSudoku());
                allStepView.setText(getResources().getString(R.string.allStep) + sudokuMaster.getUsedNums().getSize());
            }
        });
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                step = Integer.parseInt(countText.getText().toString());
                ListUsedNums usedNums = sudokuMaster.getUsedNums();
                for(int i = 0;i < usedNums.getSize();i++)
                {
                    if(i < step)
                    {
                        setOutputOn(usedNums.getAddedNum(i),usedNums.getAddedNumLine(i),usedNums.getAddedNumColumn(i));
                    }
                    else
                    {
                        setOutputOn(55,usedNums.getAddedNumLine(i),usedNums.getAddedNumColumn(i));
                    }
                }
            }
        });
        StepForvard.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(step < sudokuMaster.getUsedNums().getSize())
                {
                    step++;
                }
                System.out.println("this is step num : " + step );
                setOutput(sudokuField);
                ListUsedNums usedNums = sudokuMaster.getUsedNums();
                countText.setText(Integer.toString(step));
                for(int i = 0;i < usedNums.getSize();i++)
                {
                    if(i < step)
                    {
                        setOutputOn(usedNums.getAddedNum(i),usedNums.getAddedNumLine(i),usedNums.getAddedNumColumn(i));
                    }
                    else
                    {
                        setOutputOn(55,usedNums.getAddedNumLine(i),usedNums.getAddedNumColumn(i));
                    }
                }
            }
        });
        StepBackward.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                setOutput(sudokuField);
                if(step >0)
                {
                    step--;
                }
                System.out.println("this is step num : " + step );
                ListUsedNums usedNums = sudokuMaster.getUsedNums();
                countText.setText(Integer.toString(step));
                for(int i = 0;i < usedNums.getSize();i++)
                {
                    if(i < step)
                    {
                        setOutputOn(usedNums.getAddedNum(i),usedNums.getAddedNumLine(i),usedNums.getAddedNumColumn(i));
                    }
                    else
                    {
                        setOutputOn(55,usedNums.getAddedNumLine(i),usedNums.getAddedNumColumn(i));
                    }
                }
            }
        });
        final Button clearButton = findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearOutput();
            }
        });
        for(int i=0;i<81;i++)
        {
            boxes.add(new EditText(this));
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ViewTreeObserver observer = display.getViewTreeObserver();
        if (observer.isAlive())
        {observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
                                            {
                                                @Override
                                                public void onGlobalLayout() {
                                                    display.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                                    int viewHeight = display.getHeight();
                                                    int viewWidth = display.getWidth();
                                                    RelativeLayout.LayoutParams praca = new RelativeLayout.LayoutParams(viewHeight,viewWidth);
                                                    praca.height = viewWidth;
                                                    praca.width = viewWidth ;
                                                    grid.setLayoutParams(praca);
                                                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                                                    int margin = 5;
                                                    for (int a=0;a<9;a++)
                                                    {
                                                        for(int b=0;b<9;b++)
                                                        {
                                                            params = new GridLayout.LayoutParams();
                                                            params.rowSpec = GridLayout.spec(a);
                                                            params.columnSpec = GridLayout.spec(b);
                                                            params.height = (viewWidth-130) / 9;
                                                            params.width = (viewWidth-130) / 9;
                                                            if ((b+1)%3==0)
                                                            {
                                                                params.setMargins(margin,margin,margin*5,margin);
                                                            }
                                                            else
                                                            {
                                                                params.setMargins(margin,margin,margin,margin);
                                                            }
                                                            if((a+1)%3==0)
                                                            {
                                                                params.bottomMargin = margin * 5;
                                                            }
                                                            /*InputFilter[] filters = new InputFilter[1];
                                                            filters[0] = new InputFilter.LengthFilter(1);
                                                            boxes.get(b+(a*9)).setFilters(filters);*/
    /*
                                                            boxes.get(b+(a*9)).setLayoutParams(params);
                                                            boxes.get(b+(a*9)).setBackgroundColor(getResources().getColor(R.color.box));
                                                            boxes.get(b+(a*9)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                                            boxes.get(b+(a*9)).setCursorVisible(false);
                                                            boxes.get(b+(a*9)).setInputType(InputType.TYPE_CLASS_NUMBER);
                                                            boxes.get(b+(a*9)).setPadding(0,0,0,0);
                                                            boxes.get(b+(a*9)).setTextColor(getResources().getColor(R.color.black));
                                                            //boxes.get(b+(a*9)).setOnFocusChangeListener(new isFocused(a,b));
                                                            //boxes.get(b+(a*9)).addTextChangedListener(new changeText(b+(a*9)));
                                                            grid.addView(boxes.get(b+(a*9)));
                                                        }
                                                    }
                                                }
                                            }
        );
        }setOutput(sudokuField);
    }
    /*void setOutput()
    {
        int [][]  temp= sudokuMaster.calculate();
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                if(temp[i][j]!=0)
                {
                    boxes.get((i*9)+j).setText(Integer.toString(temp[i][j]));
                }
            }
        }
    }*/
    /*
    void setOutput(int temp[][])
    {
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                if(temp[i][j]!=0)
                {
                    boxes.get((i*9)+j).setText(Integer.toString(temp[i][j]));
                }
            }
        }
    }
    void setOutputOn(int input,int line,int col)
    {
        if(input<=9 && input>=0)
        {
            boxes.get((line*9)+col).setText(Integer.toString(input));
            boxes.get((line*9)+col).setBackgroundColor(getResources().getColor(R.color.boxFocused));
        }
        else
        {
            boxes.get((line*9)+col).setText(" ");
            boxes.get((line*9)+col).setBackgroundColor(getResources().getColor(R.color.box));
        }

    }
    void calculateAndSetOutput(int input[][])
    {
        int[][] tempInput = new int[9][9];
        for(int a = 0;a<9;a++)
        {
            for(int b = 0;b<9;b++)
            {
                tempInput[a][b] = input[a][b];
            }
        }
        System.out.println("start time");
        System.out.println(SystemClock.currentThreadTimeMillis());

        int [][]  temp = sudokuMaster.calculate(input);
        System.out.println("end time");
        System.out.println(SystemClock.currentThreadTimeMillis());
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                if(input[i][j]!=tempInput[i][j])
                {
                    boxes.get((i*9)+j).setText(Integer.toString(temp[i][j]));
                    boxes.get((i*9)+j).setBackgroundColor(Color.RED);
                }
            }
        }
        step = 0;
    }
    int [][] getFieldOfSudoku()
    {
        int temp[][] = new int[9][9];
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                try
                {
                    temp [i][j] = Integer.parseInt(String.valueOf(boxes.get((i*9)+j).getText()));
                }
                catch (Exception e)
                {

                }

            }
        }
        return temp;
    }
    void printFieldOfSudoku()
    {
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                System.out.print(String.valueOf(boxes.get((i*9)+j).getText()));
            }
            System.out.println();
        }
    }
    void clearOutput()
    {
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                    boxes.get((i*9)+j).setText("");
                    boxes.get((i*9)+j).setBackgroundColor(getResources().getColor(R.color.box));
            }
        }
    }
    class isFocused implements View.OnFocusChangeListener
    {int i = 0,j = 0;
        isFocused(int inI,int inJ)
        {
            i = inI;
            j = inJ;
        }
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus)
            {
                boxes.get((i*9)+j).setBackgroundColor(getResources().getColor(R.color.boxFocused));
                int temp = 0;
                try
                {
                    temp = Integer.parseInt(boxes.get((i*9)+j).getText().toString());
                }
                catch (Exception e)
                {
                    temp = 0;
                }
                if(temp<9)
                {
                    temp++;
                    boxes.get((i*9)+j).setText(Integer.toString(temp));
                }
                else
                {
                    boxes.get((i*9)+j).setText("");
                }
            }
            else
            {
                boxes.get((i*9)+j).setBackgroundColor(getResources().getColor(R.color.box));
            }
        }
    }*/
  /*  class changeText implements TextWatcher {
        int numOfSquare = 0;

        changeText(int inNumOfSquare) {
            numOfSquare = inNumOfSquare;
        }

        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                boxes.get(numOfSquare).setText();
        }
    }
}*/
