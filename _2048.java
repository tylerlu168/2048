import java.util.ArrayList;

public class _2048
{
    private final int rows = 4; //instance variables
    private final int cols = 4;
    private int[][] board;
    private int[][] previousBoard;
    private int score;
    private int previousScore;

    /**
     * Initializes board and previousBoard using rows and cols.
     * Uses the generateTile method to add two random tiles to board.
     */
    public _2048()
    {
        this.board = new int[rows][cols];
        previousBoard = new int[rows][cols];

        generateTile(); //adds one random tile to the board
        generateTile(); //adds another random tile to the board

    }

    /**
     * Initializes the board of this object using the specified board.
     * Initializes previousBoard using rows and cols.
     *
     * Precondition: the specified board is a 4x4 2D Array.
     *
     * @param board
     */
    public _2048(int[][] board)
    {
        this.board = board;
        previousBoard = new int[rows][cols];
    }

    /**
     * Generates a tile and add it to an empty spot on the board.
     * 80% chance to generate a 2
     * 20% chance to generate a 4
     *
     * Does nothing if the board is full.
     */
    private void generateTile()
    {
        int check = 0;
        int contains = 0;

        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                if(board[r][c] != 0)
                {
                    check++;
                }
            }
        }
        if(check == (board.length * board[0].length))
        {
            return;
        }
        else
        {
            while(contains == 0)
            {
                int r = (int)(Math.random()*4);
                int c = (int)(Math.random()*4);
                if(board[r][c] == 0)
                {
                    int percentage = (int)(Math.random()*10);
                    if(percentage < 8)
                    {
                        board[r][c] = 2;
                    }
                    else
                    {
                        board[r][c] = 4;
                    }
                    contains++; //variable that breaks out of while loop

                }
            }

        }


    }

    /**
     * Returns false if the board contains a 0, true otherwise.
     * @return
     */
    private boolean full() //checks if board is full
    {
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                if(board[r][c] == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the board.
     * @return
     */
    public int[][] getBoard() //returns board
    {
        return this.board;
    }

    /**
     * Returns the score.
     * @return
     */
    public int getScore() //returns score
    {
        return score;
    }

    /**
     * Saves board into previousBoard and score into previousScore
     * then performs a move based on the specified direction:
     *
     * Valid directions (not case sensitive):
     *  up
     *  down
     *  left
     *  right
     *
     * Adds a new tile to the board using the generateTile method.
     *
     * @param direction
     */
    public void move(String direction)
    {
        for (int r = 0; r < previousBoard.length; r++)
        {
            for (int c = 0; c < previousBoard[r].length; c++)
            {
                previousBoard[r][c] = board[r][c];
            }
        }
        previousScore = score;

        if(direction == "up")
        {
            moveUp();
        }
        if(direction == "down")
        {
            moveDown();
        }
        if(direction == "left")
        {
            moveLeft();
        }
        if(direction == "right")
        {
            moveRight();
        }
        generateTile(); // generates one random tile after a move
    }

    /**
     * Shifts all the tiles up, combines like tiles that collide.
     */
    private void moveUp() //move up method
    {
        ArrayList<Integer> board2 = new ArrayList<>();

        int counter = 0;
        while(counter < 4)
        {
            for (int r = 0; r < board.length; r++)
            {
                board2.add(board[r][counter]);
            }
            for (int j = 0; j < board2.size(); j++) // removes all zeros in arraylist
            {
                if(board2.get(j).equals(0))
                {
                    board2.remove(j);
                    j--;
                }
            }
            for (int i = 0; i < board2.size() - 1; i++)
            {
                if(board2.get(i) > 0)
                {
                    if(board2.get(i).equals(board2.get(i+1)))
                    {
                        score += (board2.get(i) + board2.get(i+1));
                        board2.add(i, (board2.get(i) + board2.get(i+1)));

                        //board2.get(i).equals(board2.get(i) + board2.get(i+1));
                        board2.remove(board2.get(i+1));
                        board2.remove(board2.get(i+1));
                        board2.add(0);
                    }
                }
                //below is my initial thought process for this code
                /*if(board2.get(i).equals(0))
                {
                    for (int j = board2.size() - 1; j >= i; j--)
                    {
                            if (board2.get(j) == 0)
                            {
                                check = false;
                            }
                            else
                            {
                                check = true;
                                break;
                            }
                    }
                }
                if(check == true)
                {
                    if(board2.get(i).equals(0))
                    {
                        //board2.remove(i);
                        board2.add(0);
                        board2.remove(i);
                        i--;
                    }
                }*/

            }
            while(board2.size() != 4) // adds back zeros to array list
            {
                board2.add(0);
            }
            for (int i = 0; i < board.length; i++)
            {
                board[i][counter] = board2.get(i); //puts arraylist back into 2D array
            }

            counter++;
            for (int i = 0; i < board2.size(); i++)
            {
                board2.remove(i);
                i--;
            }

        }
    }

    /**
     * Shifts all the tiles down, combines like tiles that collide.
     */
    private void moveDown() //move down method
    {
        ArrayList<Integer> board2 = new ArrayList<>();
        int counter = 0;
        while (counter < 4)
        {
            for (int r = board.length - 1; r >= 0; r--)
            {
                board2.add(board[r][counter]);
            }
            for (int j = 0; j < board2.size(); j++)
            {
                if (board2.get(j).equals(0))
                {
                    board2.remove(j);
                    j--;
                }
            }
            for (int i = 0; i < board2.size() - 1; i++)
            {
                if (board2.get(i) > 0)
                {
                    if (board2.get(i).equals(board2.get(i + 1)))
                    {
                        score += (board2.get(i) + board2.get(i+1));
                        board2.add(i, (board2.get(i) + board2.get(i + 1)));


                        board2.remove(board2.get(i + 1));
                        board2.remove(board2.get(i + 1));
                        board2.add(0);
                    }
                }
            }
            while (board2.size() != 4)
            {
                board2.add(0);
            }

            for (int i = board.length - 1; i >= 0; i--)
            {
                board[i][counter] = board2.get(3 - i);
            }

            counter++;
            for (int i = 0; i < board2.size(); i++)
            {
                board2.remove(i);
                i--;
            }

        }
    }

    /**
     * Shifts all the tiles left, combines like tiles that collide.
     */
    private void moveLeft() //move left method
    {
        ArrayList<Integer> board2 = new ArrayList<>();
        int counter = 0;
        while(counter < 4)
        {
            for (int c = 0; c < board.length; c++)
            {
                board2.add(board[counter][c]);
            }
            for (int j = 0; j < board2.size(); j++)
            {
                if(board2.get(j).equals(0))
                {
                    board2.remove(j);
                    j--;
                }
            }
            for (int i = 0; i < board2.size() - 1; i++)
            {
                if(board2.get(i) > 0)
                {
                    if(board2.get(i).equals(board2.get(i+1)))
                    {
                        score += (board2.get(i) + board2.get(i+1));
                        board2.add(i, (board2.get(i) + board2.get(i+1)));


                        board2.remove(board2.get(i+1));
                        board2.remove(board2.get(i+1));
                        board2.add(0);
                    }
                }
            }
            while(board2.size() != 4)
            {
                board2.add(0);
            }
            for (int i = 0; i < board.length; i++)
            {
                board[counter][i] = board2.get(i);
            }

            counter++;
            for (int i = 0; i < board2.size(); i++)
            {
                board2.remove(i);
                i--;
            }

        }
    }

    /**
     * Shifts all the tiles right, combines like tiles that collide.
     */
    private void moveRight() //move right method
    {
        ArrayList<Integer> board2 = new ArrayList<>();
        int counter = 0;
        while (counter < 4)
        {
            for (int c = board.length - 1; c >= 0; c--)
            {
                board2.add(board[counter][c]);
            }
            for (int j = 0; j < board2.size(); j++)
            {
                if (board2.get(j).equals(0))
                {
                    board2.remove(j);
                    j--;
                }
            }
            for (int i = 0; i < board2.size() - 1; i++)
            {
                if (board2.get(i) > 0)
                {
                    if (board2.get(i).equals(board2.get(i + 1)))
                    {
                        score += (board2.get(i) + board2.get(i+1));

                        board2.add(i, (board2.get(i) + board2.get(i + 1)));
                        board2.remove(board2.get(i + 1));
                        board2.remove(board2.get(i + 1));
                        board2.add(0);
                    }
                }


            }
            while (board2.size() != 4)
            {
                board2.add(0);
            }

            for (int i = board.length - 1; i >= 0; i--)
            {
                board[counter][i] = board2.get(3 - i);
            }

            counter++;
            for (int i = 0; i < board2.size(); i++)
            {
                board2.remove(i);
                i--;
            }

        }
    }

    /**
     * Sets board to previousBoard and score to previousScore
     */
    public void undo()
    {
        for (int r = 0; r < previousBoard.length; r++)
        {
            for (int c = 0; c < previousBoard[r].length; c++)
            {
                board[r][c] = previousBoard[r][c];
            }
        }

        score = previousScore; // sets previous score to score
    }

    /**
     * Returns true if the game is over, false otherwise.
     * @return
     */
    public boolean gameOver() //determines whether game is over
    {
        move("up");
        for (int r = 0; r < previousBoard.length; r++)
        {
            for (int c = 0; c < previousBoard[r].length; c++)
            {
                if(previousBoard[r][c] != board[r][c])
                {
                    undo();
                    return false;
                }
            }
        }
        move("down");
        for (int r = 0; r < previousBoard.length; r++)
        {
            for (int c = 0; c < previousBoard[r].length; c++)
            {
                if(previousBoard[r][c] != board[r][c])
                {
                    undo();
                    return false;
                }
            }
        }
        move("left");
        for (int r = 0; r < previousBoard.length; r++)
        {
            for (int c = 0; c < previousBoard[r].length; c++)
            {
                if(previousBoard[r][c] != board[r][c])
                {
                    undo();
                    return false;
                }
            }
        }
        move("right");
        for (int r = 0; r < previousBoard.length; r++)
        {
            for (int c = 0; c < previousBoard[r].length; c++)
            {
                if(previousBoard[r][c] != board[r][c])
                {
                    undo();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a String representation of this object.
     */
    public String toString()
    {
        String rtn = "";

        for(int[] row : board)
        {
            rtn += "|";
            for(int num : row)
                if(num != 0)
                {
                    String str = num + "";
                    while(str.length() < 4)
                        str = " " + str;
                    rtn += str;

                }
                else
                    rtn += "    ";
            rtn += "|\n";
        }

        rtn += "Score: " + score + "\n";

        return rtn;
    }
}