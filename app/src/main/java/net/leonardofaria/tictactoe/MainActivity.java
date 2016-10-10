package net.leonardofaria.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button newGame;
    private static String email = "leonardofaria@gmail.com";

    private int[][] table;
    private boolean xMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(MainActivity.this, "Let the game begin!", Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send an email
                String[] addresses = {email};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Tic Tac Toe");
                intent.putExtra(Intent.EXTRA_TEXT, "Please send your feedback about the game!");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        table = new int[3][3];
        xMove = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void makeMove(View v) {
        int x = 0;
        int y = 0;

        int id = v.getId();

        switch (id) {
            case R.id.button1:
                x = 0;
                y = 0;
                break;
            case R.id.button2:
                x = 0;
                y = 1;
                break;
            case R.id.button3:
                x = 0;
                y = 2;
                break;
            case R.id.button4:
                x = 1;
                y = 0;
                break;
            case R.id.button5:
                x = 1;
                y = 1;
                break;
            case R.id.button6:
                x = 1;
                y = 2;
                break;
            case R.id.button7:
                x = 2;
                y = 0;
                break;
            case R.id.button8:
                x = 2;
                y = 1;
                break;
            case R.id.button9:
                x = 2;
                y = 2;
                break;
        }

        if (table[x][y] != 0) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

            dlgAlert.setMessage("This cell is not empty!");
            dlgAlert.setTitle("Error");
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            return;
        }
        Button btn = (Button) findViewById(v.getId());
        if (xMove) {
            btn.setText("X");
            table[x][y] = 2;
            // Toast.makeText(MainActivity.this, "O move", Toast.LENGTH_SHORT).show();
            // Snackbar.make(v, "O move", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            xMove = false;
        } else {
            btn.setText("O");
            table[x][y] = 1;
            // Toast.makeText(MainActivity.this, "X move", Toast.LENGTH_SHORT).show();
            // Snackbar.make(v, "X move", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            xMove = true;
        }

        checkResult();
    }

    private void checkResult() {
        boolean empty = false;
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        for (int i = 0; i != 3; ++i) {
            for (int j = 0; j != 3; ++j) {
                if (table[i][j] == 0) {
                    empty = true;
                    break;
                }
            }
        }
        if (!empty) {
            dlgAlert.setMessage("Draw!");
            dlgAlert.setTitle("Draw");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                });
            dlgAlert.create().show();
        }
        //check horizontal lines
        for (int i = 0; i != 3; ++i) {
            if (table[i][0] == 1 && table[i][1] == 1 && table[i][2] == 1) {
                dlgAlert.setMessage("O Player wins!");
                dlgAlert.setTitle("Congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            resetGame();
                        }
                    });
                dlgAlert.create().show();

            }
            if (table[i][0] == 2 && table[i][1] == 2 && table[i][2] == 2) {
                dlgAlert.setMessage("X Player wins!");
                dlgAlert.setTitle("Congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            resetGame();
                        }
                    });
                dlgAlert.create().show();
            }
        }
        //check vertical lines
        for (int i = 0; i != 3; ++i) {
            if (table[0][i] == 1 && table[1][i] == 1 && table[2][i] == 1) {
                dlgAlert.setMessage("O Player wins!");
                dlgAlert.setTitle("Congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            resetGame();
                        }
                    });
                dlgAlert.create().show();

            }
            if (table[0][i] == 2 && table[1][i] == 2 && table[2][i] == 2) {
                dlgAlert.setMessage("X Player wins!");
                dlgAlert.setTitle("Congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            resetGame();
                        }
                    });
                dlgAlert.create().show();

            }
        }
        //check diagonals
        if (table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1) {
            dlgAlert.setMessage("O Player wins!");
            dlgAlert.setTitle("Congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                });
            dlgAlert.create().show();

        }
        if (table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2) {
            dlgAlert.setMessage("X Player wins!");
            dlgAlert.setTitle("Congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                });
            dlgAlert.create().show();
        }
        if (table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1) {
            dlgAlert.setMessage("O Player wins!");
            dlgAlert.setTitle("Congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                });
            dlgAlert.create().show();
        }
        if (table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2) {
            dlgAlert.setMessage("X Player wins!");
            dlgAlert.setTitle("Congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                });
            dlgAlert.create().show();
        }
    }

    private void resetGame() {
        table = new int[3][3];
        xMove = true;
        
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setText("");

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setText("");

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setText("");

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setText("");

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setText("");

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setText("");

        Button button7 = (Button) findViewById(R.id.button7);
        button7.setText("");

        Button button8 = (Button) findViewById(R.id.button8);
        button8.setText("");
        
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setText("");
    }
}
