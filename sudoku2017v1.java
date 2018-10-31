/*
sudoku - Japanese mathematical rule base. Creating own puzzles as well as 
helping to find solution for The Hindu, etc., puzzles
is the scope of this work. Solution and how to derive it 
mathematically for education purpose is the secondary objective.
Copy Right to My Gayathri July 15 2017
*/

package arcot.kathir.sudoku;

import java.lang.System;
import java.lang.*;
import java.io.IOException;
//import Java.lang;


class sudoku2017v1
{

/* four prime data structures defined and declared with namespace 
indicating its remembering purposes*/

static int[] puzzle = {
0,7,4,0,3,0,8,0,0,
8,0,0,0,0,0,7,0,0,
0,0,0,0,0,0,0,0,4,
5,0,9,7,0,0,0,6,0,
6,1,0,9,0,3,0,4,8,
0,3,0,0,0,6,1,0,7,
9,0,0,0,0,0,0,0,0,
0,0,2,0,0,0,0,0,1,
0,0,1,0,7,0,2,5,0
};// = new int[81];
static int[][][] puzzlepossiblevalues = new int[9][9][9];
//static int[][][] puzzlepossiblevaluesy = new int[9][9][9];

//static int[][][] puzzlepossibleroutes = new int[9][9][9];
//static int[][][] puzzlesolution = new int[][][];
static int sol[] = new int[10];
static int puzzle1[][] = new int[9][9];

static int[][][] x=new int[19][19][19];
static int[][][] x1=new int[19][19][19];




public static void main(String[] args)
{

/* five functions along with the above data structures to transform problem puzzle space numbers to other steps is
implicitly self-explanatory */





//getsudokuproblem(); /* handles puzzle namespace */

fillpossiblevalues(); /* uses puzzle namespace and produces new namespace puzzlepossiblevalues */

xtox1();
/*location dataspace from 1...81 of possible values structured as 9 partitions of aa to iai
in index2, index1 says number of each 1...9, index3 gives you numbers from 1...9 in bread slices*/

x1tosol();
/* x1 is in index2 tells first route of 1...9 in index3, index1 says 1...9 locations in aa to iai*/

/* solution has index3 of index2 of x1 routes data space*/
/*checkrowcol();  optimizes puzzlepossiblevalues 

fillpossibleroutes();  produces puzzlepossibleroutes 

checkuniquesudokuplacesandvalues(); produces puzzlesolution */

}




static void getsudokuproblem()
{
    // Bug Finding: System.in.read() accepts spaces but not enter key. 'enter key bug 10 -38'
int place =0;
int  a=0;

for(;;)
{
try{
    place = System.in.read();
//System.out.println(place);
}
catch (IOException e){};
if(place == 'a')
    break;
try{
    //System.out.println(place);
    place = place - '0';
//System.out.println(place);
a = System.in.read();
puzzle[place] = a - '0';

}
catch (IOException e){;};


}
}


static void fillpossiblevalues()
{

int row=0, col=0, values=0, values1=0, row1=0,col1=0,count1=0, index=0,  index3 = 0,i=0;
int a=0,b=0,c=0,a1=0;
int[][] index2 = new int[19][19];

for(count1=0;count1<81;count1++)
{
puzzle1[count1/9][count1%9] = puzzle[count1];
}
/*for each cell i am going with all row missing 1...9, all col missing 1..9, all 3X3 missing 1...9 
 sol[5] = 1; means 5 missing.
  */
for(row = 0; row < 9; row++)
for(col = 0; col < 9; col++)
{
    if (puzzle[row*9+col]!=0) {
        puzzlepossiblevalues[row][col][0] = puzzle[row*9+col];
    }
    else
    {
    for(row1 = 0; row1 < 9; row1++)
    {
        if (puzzle1[row1][col] != 0)
           sol[puzzle1[row1][col]] = 1;
    }

    for(col1 = 0; col1 < 9; col1++)
    {
        if (puzzle1[row][col1] != 0) 
               sol[puzzle1[row][col1]] = 1;
    }


    for(row1=0, index = 0;row1< 9;row1++, index++ )
    {

        index %= 3;

        if (puzzle1[row / 3  + row1%3 - index][col/3 + row1%3 - index] != 0) 
           sol[puzzle1[row/3 + row1%3 - index][col/3 + row1 % 3 - index]] = 1;
    }

    values = 0;
    for(values1 = 1; values1 < 10; values1++)
    {
    if(sol[values1] == 0)
    {
       puzzlepossiblevalues[row][col][values] = values1;
    //x[row][col][values1] = row * 9 + col;
       //finds 3x3 square numbers from 1...9 using row, col numbers
//    index3 = 3*(row < 3?0:(row >3 && row <6?1:2))+
 //   (col < 3?0:(col >3 && col <6?1:2));
 //   x[values1-1][index3][index2] = row * 9+col;
   
    }
    values++;
//    index2++;

        
    }
//    index2 = 0;
    }

for (i=0;i<10;i++) {
                sol[i] = 0;
    }

}
count1=0;

for (c=0;c<9;c++)
    for (b=0;b<9;b++)
        for (a=0;a<9;a++)
        {
            count1++;
                if(puzzlepossiblevalues[c][b][a] != 0) 
                {
                      a1 = 3*(c < 3?0:(c >3 && c <6?1:2))+(b < 3?0:(b >3 && b <6?1:2));

                    //  if (puzzlepossiblevalues[c][b][a]-1 < 9 && index2[puzzlepossiblevalues[c][b][a]-1][a1] < 9 && a1 < 9 && c < 9 && b < 9 && a < 9) {
                      
                      x[puzzlepossiblevalues[c][b][a] -1][a1][index2[puzzlepossiblevalues[c][b][a] - 1][a1]] = c * 9 + b;
                     // }
                       index2[puzzlepossiblevalues[c][b][a]-1][a1]++; 

                       System.out.println("c"+c);
                       System.out.println("b"+b);
                       System.out.println("a"+a);
                       System.out.println("a1"+a1);
                       System.out.println("index"+index2[puzzlepossiblevalues[c][b][a]-1][a1]);
                       System.out.println("index3"+puzzlepossiblevalues[c][b][a]);
                }
                System.out.println("loop counter"+count1);
        }

 

}



static void xtox1()
{
int count=0,count1=0,a=0,b=0,c=0;
/* x array diminesions a=9, b=9, c=9 forms 9*9*9 729 elements*/

for (c=0;c<9;c++)
    for(b=0;b<9;b++)
        for (a=0;a<9;a++)
        
    {
      /*  count1 = count;
        a = count1%10;
        count1 = count/10;

        b= count1%10;
        c= count1/10;*/
       
        x1[c][a][b] = x[c][b][a];
    }

}


static void x1tosol()
{
   
    int b[] = new int[9];
    int count=0, count1=0;
    int count2=0;
    int eachdigit =0;
    int bindex1=0, bindex2=0, bindex3=0, bindex4=0, bindex5=0, bindex6=0, bindex7=0, bindex8=0, bindex9=0;

    for (bindex9 = 0; bindex9 < 9; bindex9++)
        for (bindex8 = 0; bindex8 < 9; bindex8++)
            for (bindex7 = 0; bindex7 < 9; bindex7++)
                for (bindex6 = 0; bindex6 < 9; bindex6++)
                    for (bindex5 = 0; bindex5 < 9; bindex5++)
                        for (bindex4 = 0; bindex4 < 9; bindex4++)
                            for (bindex3 = 0; bindex3 < 9; bindex3++)
                                for (bindex2 = 0; bindex2 < 9; bindex2++)
                                    for (bindex1 = 0; bindex1 < 9; bindex1++)
    {

                                        if (compare(bindex1,bindex2,0,1) && compare(bindex2 , bindex3,1,2) && compare(bindex3 , bindex4,2,3) &&
                                            compare(bindex4,bindex5,3,4) && compare(bindex5 , bindex6,4,5) && compare(bindex6 , bindex7,5,6) &&
                                            compare(bindex7,bindex8,6,7) && compare(bindex8 , bindex9,7,8))
                                        {
                                            break;
                                        }
                                    }

                                    puzzlesolution(bindex1,bindex2,bindex3, bindex4, bindex5, bindex6, bindex7, bindex8, bindex9);

                                    /*
    
    for (count=0; count < 888888889; count++) {
        
        eachdigit = count;
        for (count1=0;count1<9;count1++) {
             b[count1] = eachdigit % 10;
             eachdigit = eachdigit / 10;

        }
        for (count1=0;count1<9;count1++) {
             if (compare(b[count1] , b[count1+1], count1, count1+1)) {
                   count2++;
        }

             if (count2 == 8) {
                 break;
             }
             
        }

    }
    puzzlesolution(count);                                 
                                    */ 
    
/*
for (count = 111; count < 999; count++)
    {
        count1 = count;
        a = count1%10;
        count1 = count/10;

        b= count1%10;
        c= count/10;

        b1=b;
        c1=c;
       
        for (count3 =0; count3 < 9 && c1 < 9; count3++)
        {
            for (count2 = 0; count2 < 9; count2++) {
                if (x1[c][b][count2] != x1 [c1+1][b1][count3])){
                   bindex = 1;

                }
                else
                {
                    bindex = 0;
                    b1++;
                    count3=0;
                    break;
                }
                
            }
        }
        
    if (bindex = 1) {
        sol[] = b;
    }
} 
*/ 

}

static boolean compare(int bindex1, int bindex2, int cindex1, int cindex2)
{
    int count2=0, count3=0;
        for (count3 =0; count3 < 9 ; count3++)
        {
            for (count2 = 0; count2 < 9; count2++) {
                if (x1[cindex1][bindex1][count2] != x1[cindex2][bindex2][count3]){
                   

                }
                else
                {
                    return false;
                }
                
            }
        }
        return true;

}

static void puzzlesolution(int bindex1,int bindex2, int bindex3, int bindex4,
                           int bindex5,int bindex6,int bindex7,int bindex8,int bindex9)
{
    int b=0;
    System.out.println(bindex1);
    System.out.println(bindex2);
    System.out.println(bindex3);
    System.out.println(bindex4);
    System.out.println(bindex5);
    System.out.println(bindex6);
    System.out.println(bindex7);
    System.out.println(bindex8);
    System.out.println(bindex9);
/*
    for (int count1 =0 ; count1 < 9; count1++) {
    
    for (int a = 0; a < 9; a++)  {
                                   System.out.println(x1[count1][bindex1][a]);
                               }
    for (int a = 0; a < 9; a++)  {
                                    
                                   System.out.println(x1[count1][bindex2][a]);
                               }
    for (int a = 0; a < 9; a++)  {
                                    System.out.println(x1[count1][bindex3][a]);
                               }
    for (int a = 0; a < 9; a++)  {
                                   System.out.println(x1[count1][bindex4][a]);
                               }
    for (int a = 0; a < 9; a++)  {
                                    System.out.println(x1[count1][bindex5][a]);
                               }
    for (int a = 0; a < 9; a++)  {
                                   System.out.println(x1[count1][bindex6][a]);
                               }
    for (int a = 0; a < 9; a++)  {
                                   System.out.println(x1[count1][bindex7][a]);
                               }
    for (int a = 0; a < 9; a++)  {
                                    System.out.println(x1[count1][bindex8][a]);
                               }
    for (int a = 0; a < 9; a++)  {
                                   System.out.println(x1[count1][bindex9][a]);
                               }
    }*/
} 
 

}

/*
checkrowcol()
{
}




fillpossibleroutes()
{
}

checkuniquesudokuplacesandvalues()
{
}
*/

