import java.util.Scanner;

public class tictactoe
{
	public static String [][] ttt_board;
	public static void init()
	{
		ttt_board = new String[3][3];
		for(int y = 0; y < 3; y++)
			for(int x = 0; x < 3; x++)
				ttt_board[y][x] = "";
	}
	public static void drawBoard()
	{
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				if(ttt_board[y][x] == "")
					System.out.print("<" + Integer.toString(y*3+x+1) + ">");
				else
					System.out.print(" " + ttt_board[y][x] + " ");
					
				if(x < 2)
					System.out.print(" | ");
				else
					System.out.print("\n");
			}
			if(y < 2)
				System.out.println("-----------------");
		}
	}
	public static boolean selectMove(int player, int square)
	{
		String ttt_value = "X";
		if(player == 2)
			ttt_value = "O";
		
		if(square <= 0 || square >9)
		{
			return false;
		}
		
		int y = (square-1)/3; 
		int x = (square-1)%3;
		
		if(ttt_board[y][x] == "")
			ttt_board[y][x] = ttt_value;
		else
			return false;
		
		return true;
	}
	public static boolean playerHasWon(int player)
	{
		String player_char = "X";
		if(player == 2)
			player_char = "O";
		int [] match = new int[8];
		for(int y = 0; y < 3; y++)
		{
			if(ttt_board[y][0] == player_char)
				match[0]++;
			if(ttt_board[y][1] == player_char)
				match[1]++;
			if(ttt_board[y][2] == player_char)
				match[2]++;
		}
		
		for(int x = 0; x < 3; x++)
		{
			if(ttt_board[0][x] == player_char)
				match[3]++;
			if(ttt_board[1][x] == player_char)
				match[4]++;
			if(ttt_board[2][x] == player_char)
				match[5]++;
		}
		
		if(ttt_board[0][0] == player_char && ttt_board[1][1] == player_char && ttt_board[2][2] == player_char)
					match[6] = 3;
					
		if(ttt_board[0][2] == player_char && ttt_board[1][1] == player_char && ttt_board[2][0] == player_char)
					match[7] = 3;
					
		for(int i = 0; i < 8; i++)
		{
			if(match[i] == 3)
				return true;
		}
					
		return false;
	}
	public static void main(String [] args)
	{
		Scanner s = new Scanner(System.in);
		boolean game_continue = true;
		boolean game_isRunning = true;
		int current_player = 1;
		int square = 0;
		String play_again = " ";
		while(game_continue)
		{
			init();
			current_player = 1;
			
			while(game_isRunning)
			{
				drawBoard();
				System.out.print("\nPlayer " + current_player + " select a square:");
				square = Integer.parseInt(s.nextLine());
				while(!selectMove(current_player, square))
				{
					System.out.println("\nYOU CANT MAKE THAT MOVE!!!\n\n");
					drawBoard();
					System.out.print("Player " + current_player + ", Please Select an Empty Square: ");
					square = Integer.parseInt(s.nextLine());
				}
				
				System.out.println("\n");
				
				if(playerHasWon(current_player))
				{
					System.out.println("\nPLAYER " + current_player + " WINS!!!");
					break; 
				}
				switch(current_player)
				{
					case 1:
						current_player = 2;
						break;
					case 2:
						current_player = 1;
						break;
				}
			}
			System.out.print("\nDo you want to play again? (y/n): ");
			play_again = s.nextLine();
			
			if(play_again.charAt(0)!='Y' || play_again.charAt(0) !='y')
			{
				break;
			}
		}
	}
}

