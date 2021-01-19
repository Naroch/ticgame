package ticgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dataflair.ticgame.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private int grid_size;
    TableLayout gameBoard;
    TextView txt_turn;
    char [][] board;
    int round = 0;
    int row ;
    int col ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        grid_size = Integer.parseInt(getString(R.string.size_of_board));
        board = new char [grid_size][grid_size];
        gameBoard = (TableLayout) findViewById(R.id.mainBoard);
        txt_turn = (TextView) findViewById(R.id.turn);

        resetBoard();




        Button reset_btn = (Button) findViewById(R.id.reset);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent current = getIntent();
                finish();
                startActivity(current);
            }
        });
    }

    protected void resetBoard(){
        round = 0;
        for(int i = 0; i< grid_size; i++){
            for(int j = 0; j< grid_size; j++){
                board[i][j] = ' ';
            }
        }
    }

    protected int gameStatus(){

        //0 Continue
        //1 X Wins
        //2 O Wins
        //-1 Draw

        int rowX = 0, colX = 0, rowO = 0, colO = 0;
        for(int i = 0; i< grid_size; i++){
            if(check_Row_Equality(i,'X'))
                return 1;
            if(check_Column_Equality(i, 'X'))
                return 1;
            if(check_Row_Equality(i,'O'))
                return 2;
            if(check_Column_Equality(i,'O'))
                return 2;
            if(check_Diagonal('X'))
                return 1;
            if(check_Diagonal('O'))
                return 2;
        }

        boolean boardFull = true;
        for(int i = 0; i< grid_size; i++){
            for(int j = 0; j< grid_size; j++){
                if(board[i][j]==' ')
                    boardFull = false;
            }
        }
        if(boardFull)
            return -1;
        else return 0;
    }

    protected boolean check_Diagonal(char player){
        int count_Equal1 = 0,count_Equal2 = 0;
        for(int i = 0; i< grid_size; i++)
            if(board[i][i]==player)
                count_Equal1++;
        for(int i = 0; i< grid_size; i++)
            if(board[i][grid_size -1-i]==player)
                count_Equal2++;
        if(count_Equal1== grid_size || count_Equal2== grid_size)
            return true;
        else return false;
    }

    protected boolean check_Row_Equality(int r, char player){
        int count_Equal=0;
        for(int i = 0; i< grid_size; i++){
            if(board[r][i]==player)
                count_Equal++;
        }

        if(count_Equal== grid_size)
            return true;
        else
            return false;
    }

    protected boolean check_Column_Equality(int c, char player){
        int count_Equal=0;
        for(int i = 0; i< grid_size; i++){
            if(board[i][c]==player)
                count_Equal++;
        }

        if(count_Equal== grid_size)
            return true;
        else
            return false;
    }

    // Sprawdza czy pole jest puste
    protected boolean Cell_Set(int r, int c){
        return !(board[r][c]==' ');
    }

    protected void stopMatch(){
        for(int i = 0; i< gameBoard.getChildCount(); i++){
            TableRow row = (TableRow) gameBoard.getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                TextView tv = (TextView) row.getChildAt(j);
                tv.setOnClickListener(null);
            }
        }
    }
    void compMove(int a, int b){
        board[a][b] = 'O';
        TableRow row2 = (TableRow) gameBoard.getChildAt(a);
        TextView tv2 = (TextView) row2.getChildAt(b);
        tv2.setText("O");
    }
    void conditions(){
        char conmputer='O';
        char player='X';
        // jeśl gracz nie zaczął od środka to komputer wybierze środek
    if((board[1][1]==' '))
        compMove(1,1);
        // sprawdzanie czy można wygrać
        // wiersze
    else if (
            board[0][0] == conmputer &&
                    board[0][1] == conmputer &&
                    board[0][2] == ' '
    ) {
        compMove(0, 2);
    } else if (
            board[0][1] == conmputer &&
                    board[0][2] == conmputer &&
                    board[0][0] == ' '
    ) {
        compMove(0, 0);
    } else if (
            board[0][0] == conmputer &&
                    board[0][2] == conmputer &&
                    board[0][1] == ' '
    ) {
        compMove(0, 1);
    } else if (
            board[1][0] == conmputer &&
                    board[1][1] == conmputer &&
                    board[1][2] == ' '
    ) {
        compMove(1, 2);
    } else if (
            board[1][1] == conmputer &&
                    board[1][2] == conmputer &&
                    board[1][0] == ' '
    ) {
        compMove(1, 0);
    } else if (
            board[1][0] == conmputer &&
                    board[1][2] == conmputer &&
                    board[1][1] == ' '
    ) {
        compMove(1, 1);
    } else if (
            board[2][0] == conmputer &&
                    board[2][1] == conmputer &&
                    board[2][2] == ' '
    ) {
        compMove(2, 2);
    } else if (
            board[2][1] == conmputer &&
                    board[2][2] == conmputer &&
                    board[2][0] == ' '
    ) {
        compMove(2, 0);
    } else if (
            board[2][0] == conmputer &&
                    board[2][2] == conmputer &&
                    board[2][1] == ' '
    ) {
        compMove(2, 1);
    }
    // kolumny
    else if (
            board[0][0] == conmputer &&
                    board[1][0] == conmputer &&
                    board[2][0] == ' '
    ) {
        compMove(2, 0);
    } else if (
            board[1][0] == conmputer &&
                    board[2][0] == conmputer &&
                    board[0][0] ==  ' '
    ) {
        compMove(0, 0);
    } else if (
            board[0][0] == conmputer &&
                    board[2][0] == conmputer &&
                    board[1][0] ==  ' '
    ) {
        compMove(1, 0);
    } else if (
            board[0][1] == conmputer &&
                    board[1][1] == conmputer &&
                    board[2][1] == ' '
    ) {
        compMove(2, 1);
    } else if (
            board[1][1] == conmputer &&
                    board[2][1] == conmputer &&
                    board[0][1] == ' '
    ) {
        compMove(0, 1);
    } else if (
            board[0][1] == conmputer &&
                    board[2][1] == conmputer &&
                    board[1][1] == ' '
    ) {
        compMove(1, 1);
    } else if (
            board[0][2] == conmputer &&
                    board[1][2] == conmputer &&
                    board[2][2] ==  ' '
    ) {
        compMove(2, 2);
    } else if (
            board[1][2] == conmputer &&
                    board[2][2] == conmputer &&
                    board[0][2] == ' '
    ) {
        compMove(0, 2);
    } else if (
            board[0][2] == conmputer &&
                    board[2][2] == conmputer &&
                    board[1][2] == ' '
    ) {
        compMove(1, 2);
    }
    // na skos
    else if (
            board[0][0] == conmputer &&
                    board[1][1] == conmputer &&
                    board[2][2] == ' '
    ) {
        compMove(2, 2);
    } else if (
            board[1][1] == conmputer &&
                    board[2][2] == conmputer &&
                    board[0][0] == ' '
    ) {
        compMove(0, 0);
    } else if (
            board[0][0] == conmputer &&
                    board[2][2] == conmputer &&
                    board[1][1] == ' '
    ) {
        compMove(1, 1);
    } else if (
            board[0][2] == conmputer &&
                    board[1][1] == conmputer &&
                    board[2][0] == ' '
    ) {
        compMove(2, 0);
    } else if (
            board[1][1] == conmputer &&
                    board[2][0] == conmputer &&
                    board[0][2] == ' '
    ) {
        compMove(0, 2);
    } else if (
            board[0][2] == conmputer &&
                    board[2][0] == conmputer &&
                    board[1][1] == ' '
    ) {
        compMove(1, 1);
    }
    // jeśli nie można wygrać, to sprawdzanie czy można zablokować
    // wiersze
    else if (
            board[0][0] == ' ' &&
                    board[0][1] == player &&
                    board[0][2] == player
    ) {
        compMove(0, 0);
    } else if (
            board[0][1] == ' ' &&
                    board[0][0] == player &&
                    board[0][2] == player
    ) {
        compMove(0, 1);
    } else if (
            board[0][2] == ' ' &&
                    board[0][1] == player &&
                    board[0][0] == player
    ) {
        compMove(0, 2);
    } else if (
            board[1][0] == ' ' &&
                    board[1][1] == player &&
                    board[1][2] == player
    ) {
        compMove(1, 0);
    } else if (
            board[1][1] == ' ' &&
                    board[1][0] == player &&
                    board[1][2] == player
    ) {
        compMove(1, 1);
    } else if (
            board[1][2] == ' ' &&
                    board[1][1] == player &&
                    board[1][0] == player
    ) {
        compMove(1, 2);
    } else if (
            board[2][0] == ' ' &&
                    board[2][1] == player &&
                    board[2][2] == player
    ) {
        compMove(2, 0);
    } else if (
            board[2][1] == ' ' &&
                    board[2][0] == player &&
                    board[2][2] == player
    ) {
        compMove(2, 1);
    } else if (
            board[2][2] == ' ' &&
                    board[2][1] == player &&
                    board[2][0] == player
    ) {
        compMove(2, 2);
    }
    // kolumny
    else if (
            board[0][0] == ' ' &&
                    board[1][0] == player &&
                    board[2][0] == player
    ) {
        compMove(0, 0);
    } else if (
            board[1][0] == ' ' &&
                    board[0][0] == player &&
                    board[2][0] == player
    ) {
        compMove(1, 0);
    } else if (
            board[2][0] == ' ' &&
                    board[1][0] == player &&
                    board[0][0] == player
    ) {
        compMove(2, 0);
    } else if (
            board[0][1] == ' ' &&
                    board[1][1] == player &&
                    board[2][1] == player
    ) {
        compMove(0, 1);
    } else if (
            board[1][1] == ' ' &&
                    board[0][1] == player &&
                    board[2][1] == player
    ) {
        compMove(1, 1);
    } else if (
            board[2][1] == ' ' &&
                    board[1][1] == player &&
                    board[0][1] == player
    ) {
        compMove(2, 1);
    } else if (
            board[0][2] == ' ' &&
                    board[1][2] == player &&
                    board[2][2] == player
    ) {
        compMove(0, 2);
    } else if (
            board[1][2] == ' ' &&
                    board[0][2] == player &&
                    board[2][2] == player
    ) {
        compMove(1, 2);
    } else if (
            board[2][2] == ' ' &&
                    board[1][2] == player &&
                    board[0][2] == player
    ) {
        compMove(2, 2);
    }
    // na skos
    else if (
            board[0][0] == ' ' &&
                    board[1][1] == player &&
                    board[2][2] == player
    ) {
        compMove(0, 0);
    } else if (
            board[1][1] == ' ' &&
                    board[0][0] == player &&
                    board[2][2] == player
    ) {
        compMove(1, 1);
    } else if (
            board[2][2] == ' ' &&
                    board[1][1] == player &&
                    board[0][0] == player
    ) {
        compMove(2, 2);
    } else if (
            board[0][2] == ' ' &&
                    board[1][1] == player &&
                    board[2][0] == player
    ) {
        compMove(0, 2);
    } else if (
            board[1][1] == ' ' &&
                    board[0][2] == player &&
                    board[2][0] == player
    ) {
        compMove(1, 1);
    } else if (
            board[2][0] == ' ' &&
                    board[1][1] == player &&
                    board[0][2] == player
    ) {
        compMove(2, 0);
    } else if (
            (round == 1 && board[1][1] == player)||
                    (round == 3 && board[1][1] == player)
    ) {
        do {
            row = randomCorner();
            col = randomCorner();
        } while (board[row][col] != ' ');
        compMove(row, col);
    }
    else if (round == 8) {
        int a=0,b=0;
        while(board[a][b]==' ')
        {
            while(b<3)
            {
                if(board[a][b]==' ') {
                    compMove(a, b);
                    break;
                }
                b++;
            }
            a++;
        }

    }
    // jeśli nie można wygrać ani zablokować, to ruch jest losowy
    else {
        do {
            row = randomMove();
            col = randomMove();
        } while (board[row][col] != ' ');
        compMove(row, col);
    }

    }

    int randomMove() {
        Random random = new Random();
        int randomNumber;
        return randomNumber = random.nextInt(3);
    }

    int randomCorner() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        if(randomNumber == 1)randomNumber++;
        return randomNumber;
    }

    View.OnClickListener Move(final int r, final int c, final TextView tv){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Cell_Set(r,c)) {
                    board[r][c] = 'X';
                    if (round!=8) {
                        tv.setText("X");
                        round+=1;
                        conditions();
                        round+=1;
                    }
                    else
                    {
                        tv.setText("X");
                    }

                    if (gameStatus() == 1) {
                        txt_turn.setText("X Wins!");
                        stopMatch();
                    }
                    else if(gameStatus() == -1){
                        txt_turn.setText("This is a Draw match");
                        stopMatch();
                    }
                    else if(gameStatus() == 2){
                        txt_turn.setText("O Wins!");
                        stopMatch();
                    }
                }
                else{
                    txt_turn.setText(txt_turn.getText()+" Choose an Empty Call");
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}